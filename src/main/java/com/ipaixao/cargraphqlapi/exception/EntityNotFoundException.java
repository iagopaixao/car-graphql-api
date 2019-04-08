package com.ipaixao.cargraphqlapi.exception;

import static com.ipaixao.cargraphqlapi.util.MessageUtils.format;

public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(final Class clazz) {
    super(format("msg-error.not-find-entity", clazz.getName()));
  }
}
