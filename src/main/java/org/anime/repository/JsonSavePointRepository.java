package org.anime.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.POJONode;
import org.anime.exception.NotFoundException;
import org.anime.model.SavePoint;
import org.anime.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
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
    private final File jsonSource;
    private final ObjectMapper mapper = new ObjectMapper();
    private ObjectNode root;

    public JsonSavePointRepository(File savePointJson) {
        this.jsonSource = savePointJson;
        try {
            root = (ObjectNode) mapper.readTree(savePointJson);
        } catch (Throwable e) {
            try {
                root = createJsonStruct();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void insert(SavePoint obj) throws IOException {
        if (root.isEmpty()) {
            createJsonStruct();
            insert(obj);
            return;
        }
        obj.updateTime();
        final ArrayNode data = JsonUtils.extractData(root);
        data.addPOJO(obj);
        updateRoot();
        mapper.writeValue(jsonSource, root);
//    data.add()
    }

    private ObjectNode createJsonStruct() throws IOException {
        final ObjectNode objectNode = mapper.createObjectNode().put("last_update", new Date().toString());
        final ArrayNode arrayNode = mapper.createArrayNode();
        objectNode.put("data", arrayNode);
        mapper.writeValue(jsonSource, objectNode);
        return objectNode;
//    mapper.writeTree(savePointJson, jsonNode)
    }

    @Override
    public List<SavePoint> findAll() throws NotFoundException, IOException {
//    final ArrayNode jsonNodes = JsonUtils.extractData(mapper.readTree(savePointJson));
        final ArrayNode jsonNodes = JsonUtils.extractData(root);
        return StreamSupport.stream(jsonNodes.spliterator(), false)
                .map(jNode -> JsonUtils.savePointFromJsonNode(mapper, jNode))
                .sorted((s1, s2)->s2.compareTo(s1))
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
            updateRoot();
            return true;
        }
        return false;
    }

    private Optional<POJONode> findSavePointByTitleName(String titleName) throws IOException {
//    final ArrayNode jsonNodes = JsonUtils.extractData(mapper.readTree(savePointJson));
        final ArrayNode jsonNodes = JsonUtils.extractData(root);
        return JsonUtils.toStream(jsonNodes)
                .filter(jNode -> ((SavePoint) jNode.getPojo()).getTitleName().equals(titleName))
                .findFirst();
    }

    private int findSavePointFromArrayByTitleName(ArrayNode nodes, String titleName) {
        for (int i = 0; i < nodes.size(); i++) {
            String objTitleName = null;
            JsonNode jsonNode = nodes.get(i);

            if (jsonNode instanceof POJONode) {
                POJONode pojoNode = (POJONode) jsonNode;
                SavePoint pojo = (SavePoint) pojoNode.getPojo();
                objTitleName = pojo.getTitleName();
            } else if (jsonNode instanceof ObjectNode){
                ObjectNode objectNode = (ObjectNode) jsonNode;
                objTitleName = objectNode.get("titleName").asText();
            }
            if (objTitleName.equals(titleName))
                return i;
        }
        return -1;
    }

    @Override
    public boolean removeAll() throws IOException {
        updateRoot();
        return JsonUtils.extractData(mapper.readTree(jsonSource)).removeAll() == null;
    }
    @Override
    public void insertOrUpdate(SavePoint obj) throws IOException {
        final ArrayNode nodes = JsonUtils.extractData(root);
        obj.updateTime();
        final int ind = findSavePointFromArrayByTitleName(nodes, obj.getTitleName());
        if (ind == -1)
            insert(obj);
        else {
            nodes.setPOJO(ind, obj);
            mapper.writeValue(jsonSource, root);
        }
    }

    private void updateRoot(){
        root.put("last_update", new Date().toString());
    }

}
