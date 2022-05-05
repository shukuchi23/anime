package org.anime.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.POJONode;
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
  private JsonNode root;

  @Autowired
  public JsonSavePointRepository(File savePointJson) {
    this.savePointJson = savePointJson;
    try {
      root = mapper.readTree(savePointJson);
      if (root.isEmpty())
        createJsonStruct();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void insert(SavePoint obj) throws IOException {
//    final JsonNode jsonNode = mapper.readTree(savePointJson);
    if (root.isEmpty()) {
      createJsonStruct(obj);
      return;
    }
    final ObjectNode objectNode = (ObjectNode) root;
    obj.updateTime();
    objectNode.put("last_update", obj.getUpdateTime().toString());
    final ArrayNode data = JsonUtils.extractData(objectNode);
    data.addPOJO(obj);
    mapper.writeValue(savePointJson, objectNode);
//    data.add()
  }

  private void createJsonStruct(SavePoint savePoint) throws IOException {

    final ObjectNode objectNode = mapper.createObjectNode().put("last_update", new Date().toString());
    final ArrayNode arrayNode = mapper.createArrayNode().addPOJO(savePoint);
    objectNode.put("data", arrayNode);
    mapper.writeValue(savePointJson, objectNode);
//    mapper.writeTree(savePointJson, jsonNode)
  }
  private void createJsonStruct() throws IOException {
    final ObjectNode objectNode = mapper.createObjectNode().put("last_update", new Date().toString());
    final ArrayNode arrayNode = mapper.createArrayNode();
    objectNode.put("data", arrayNode);
    mapper.writeValue(savePointJson, objectNode);
    root = objectNode;
//    mapper.writeTree(savePointJson, jsonNode)
  }

  @Override
  public List<SavePoint> findAll() throws NotFoundException, IOException {
//    final ArrayNode jsonNodes = JsonUtils.extractData(mapper.readTree(savePointJson));
    final ArrayNode jsonNodes = JsonUtils.extractData(root);
    return StreamSupport.stream(jsonNodes.spliterator(), false)
        .map(jNode -> JsonUtils.savePointFromJsonNode(mapper, jNode))
        .collect(Collectors.toList());
  }

  @Override
  public Optional<SavePoint> findOne(String titleName) throws NotFoundException, IOException {
//    final ArrayNode jsonNodes = JsonUtils.extractData(mapper.readTree(savePointJson));
    final ArrayNode jsonNodes = JsonUtils.extractData(root);
    return Optional.ofNullable(findSavePointByTitleName(titleName)
        .map(jNode -> JsonUtils.savePointFromJsonNode(mapper, jNode))
        .orElseThrow(() -> new NotFoundException("Запись '%s' не найдена", titleName)));
  }

  @Override
  public boolean remove(String titleName) throws IOException {
//    final ArrayNode jsonNodes = JsonUtils.extractData(mapper.readTree(savePointJson));
    final ArrayNode jsonNodes = JsonUtils.extractData(root);
    final int ind = findSavePointFromArrayByTitleName(jsonNodes, titleName);
    if (ind != -1) {
      jsonNodes.remove(ind);
      return true;
    }
    return false;
  }

  private Optional<POJONode> findSavePointByTitleName(String titleName) throws IOException {
//    final ArrayNode jsonNodes = JsonUtils.extractData(mapper.readTree(savePointJson));
    final ArrayNode jsonNodes = JsonUtils.extractData(root);
    return JsonUtils.toStream(jsonNodes)
        .filter(jNode -> ((SavePoint)jNode.getPojo()).getTitleName().equals(titleName))
        .findFirst();
  }

  private int findSavePointFromArrayByTitleName(ArrayNode nodes, String titleName) {
    for (int i = 0; i < nodes.size(); i++) {
      POJONode jsonNode = (POJONode)nodes.get(i);
      SavePoint pojo = (SavePoint) jsonNode.getPojo();
      if (pojo != null && pojo.getTitleName().equals(titleName))
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
//    final ObjectNode root = (ObjectNode) mapper.readTree(savePointJson);
    final ObjectNode objectNode = (ObjectNode) root;

    final ArrayNode nodes = JsonUtils.extractData(objectNode);
    nextValue.updateTime();
    final int ind = findSavePointFromArrayByTitleName(nodes, nextValue.getTitleName());
    if (ind == -1)
      insert(nextValue);
    else {
      nodes.setPOJO(ind, nextValue);
      objectNode.put("last_update", nextValue.getUpdateTime().toString());
      mapper.writeValue(savePointJson, objectNode);
    }
  }
}
