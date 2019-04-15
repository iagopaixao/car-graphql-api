package com.ipaixao.cargraphqlapi.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Messages {
  NOT_FIND_ENTITY("msg-error.not-find-entity"),
  MODEL_NAME_DUPLICATION("msg-error.model-name-duplication");

  private String value;
}
