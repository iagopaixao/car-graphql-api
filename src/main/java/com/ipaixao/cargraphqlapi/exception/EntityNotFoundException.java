package com.ipaixao.cargraphqlapi.exception;

import java.util.function.Supplier;

public class EntityNotFoundException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "Could not find entity: %s";

  public EntityNotFoundException(final Supplier<Class> clazz) {
    super(String.format(DEFAULT_MESSAGE, clazz.get().getName()));
  }
}
