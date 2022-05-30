package org.anime.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.POJONode;
import org.anime.model.SavePoint;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Karimov Evgeniy
 * 04.05.2022
 */
public class JsonUtils {

    public static String readJsonFile(String filepath) {
        try (FileInputStream reader = new FileInputStream(filepath)) {
            return new String(reader.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayNode extractData(JsonNode root) {
        return (ArrayNode) root.get("data");
    }

    public static SavePoint savePointFromJsonNode(ObjectMapper mapper, JsonNode node) {
        SavePoint rez = null;
        try {
            rez = mapper.treeToValue(node, SavePoint.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rez;
    }

    public static Stream<POJONode> toStream(ArrayNode arrayNode) {
        return StreamSupport.stream(arrayNode.spliterator(), false)
            .map(n -> new NodeAdapter((ObjectNode) n, false));
    }
}
