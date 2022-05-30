package org.anime.utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.POJONode;
import org.anime.model.SavePoint;
import org.apache.http.client.utils.DateUtils;

import java.util.Date;

/**
 * @author Karimov Evgeniy
 * 30.05.2022
 */
public class NodeAdapter <T extends ObjectNode> extends POJONode {
  private T node;
  public NodeAdapter(T node, boolean a) {
    super(node);
    this.node = node;
  }

  @Override
  public SavePoint getPojo() {
    final SavePoint savePoint = new SavePoint(
        node.get("titleName").asText(),
        node.get("seriesNum").asInt(),
        new SavePoint.MyDuration(node.get("seriesDuration").asText()),
        node.get("dubName").asText(),
        DateUtils.parseDate(node.get("updateTime").asText()),
        node.get("videoUri").asText()
    );
    return savePoint;
  }

  public NodeAdapter(Object v) {
    super(v);
  }
}
