package com.estudos.gestao_vagas_frontend.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FormatErrorMessage {

  public static String formatErrorMessage(String message) {
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      JsonNode rootNode = objectMapper.readTree(message);
      if (rootNode.isArray()) {
        return formatArrayErrorMessage(rootNode);
      }
      return rootNode.asText();
    } catch (Exception e) {
      return "- " + message;
    }
  }

  public static String formatArrayErrorMessage(JsonNode arrayError) {
    StringBuilder formatedMessager = new StringBuilder();

    for (JsonNode node : arrayError) {
      formatedMessager.append("- ").append(node.get("message").asText()).append("\n");

    }
    return formatedMessager.toString();
  }
}
