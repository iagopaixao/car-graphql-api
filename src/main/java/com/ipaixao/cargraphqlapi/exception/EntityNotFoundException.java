package com.ipaixao.cargraphqlapi.exception;

public class EntityNotFoundException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "Could not find entity: %s";

  public EntityNotFoundException(final Class clazz) {
    super(String.format(DEFAULT_MESSAGE, clazz.getName()));
  }
}
