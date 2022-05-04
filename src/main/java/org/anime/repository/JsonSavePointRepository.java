package org.anime.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.anime.exception.NotFoundException;
import org.anime.model.SavePoint;
import org.anime.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Karimov Evgeniy
 * 04.05.2022
 */
@Repository
public class JsonSavePointRepository implements SavePointRepository {
  private final File savePointJson;
  private final ObjectMapper mapper = new ObjectMapper();

  @Autowired
  public JsonSavePointRepository(File savePointJson) {
    this.savePointJson = savePointJson;
  }

  @Override
  public void insert(SavePoint obj) throws IOException {
    final JsonNode jsonNode = mapper.readTree(savePointJson);
    if (jsonNode.isEmpty()) {
      createJsonStruct(obj);
      return;
    }
    final ObjectNode root = (ObjectNode) jsonNode;
    obj.updateTime();
    root.put("last_update", obj.getUpdateTime().toString());
    final ArrayNode data = JsonUtils.extractData(jsonNode);
    data.addPOJO(obj);
    mapper.writeValue(savePointJson, jsonNode);
//    data.add()
  }

  private void createJsonStruct(SavePoint savePoint) throws IOException {

    final ObjectNode objectNode = mapper.createObjectNode().put("last_update", new Date().toString());
    final ArrayNode arrayNode = mapper.createArrayNode().addPOJO(savePoint);
    objectNode.put("data", arrayNode);
    mapper.writeValue(savePointJson, objectNode);
//    mapper.writeTree(savePointJson, jsonNode)
  }

  @Override
  public List<SavePoint> findAll() throws NotFoundException, IOException {
    final ArrayNode jsonNodes = JsonUtils.extractData(mapper.readTree(savePointJson));
    return StreamSupport.stream(jsonNodes.spliterator(), false)
        .map(jNode -> JsonUtils.savePointFromJsonNode(mapper, jNode))
        .collect(Collectors.toList());
  }

  @Override
  public Optional<SavePoint> findOne(String titleName) throws NotFoundException, IOException {
    final ArrayNode jsonNodes = JsonUtils.extractData(mapper.readTree(savePointJson));
    return Optional.ofNullable(findSavePointByTitleName(titleName)
        .map(jNode -> JsonUtils.savePointFromJsonNode(mapper, jNode))
        .orElseThrow(() -> new NotFoundException("Запись '%s' не найдена", titleName)));
  }

  @Override
  public boolean remove(String titleName) throws IOException {
    final ArrayNode jsonNodes = JsonUtils.extractData(mapper.readTree(savePointJson));
    final int ind = findSavePointFromArrayByTitleName(jsonNodes, titleName);
    if (ind != -1) {
      jsonNodes.remove(ind);
      return true;
    }
    return false;
  }

  private Optional<JsonNode> findSavePointByTitleName(String titleName) throws IOException {
    final ArrayNode jsonNodes = JsonUtils.extractData(mapper.readTree(savePointJson));
    return JsonUtils.toStream(jsonNodes)
        .filter(jNode -> jNode.get("titleName").textValue().equals(titleName))
        .findFirst();
  }

  private int findSavePointFromArrayByTitleName(ArrayNode nodes, String titleName) {
    for (int i = 0; i < nodes.size(); i++) {
      final JsonNode jsonNode = nodes.get(i);
      if (jsonNode.get("titleName").textValue().equals(titleName))
        return i;
    }
    return -1;
  }

  @Override
  public boolean removeAll() throws IOException {
    return JsonUtils.extractData(mapper.readTree(savePointJson)).removeAll() == null;
  }

  @Override
  public void update(SavePoint nextValue) throws NotFoundException, IOException {
    final ObjectNode root = (ObjectNode) mapper.readTree(savePointJson);

    final ArrayNode nodes = JsonUtils.extractData(root);
    nextValue.updateTime();
    final int ind = findSavePointFromArrayByTitleName(nodes, nextValue.getTitleName());
    if (ind == -1)
      insert(nextValue);
    else {
      nodes.setPOJO(ind, nextValue);
      root.put("last_update", nextValue.getUpdateTime().toString());
      mapper.writeValue(savePointJson, root);
    }
  }
}
