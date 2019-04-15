package com.ipaixao.cargraphqlapi.exception;

import com.ipaixao.cargraphqlapi.util.MessageUtils;

import static com.ipaixao.cargraphqlapi.enumeration.Messages.NOT_FIND_ENTITY;

public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(final Class clazz) {
    super(MessageUtils.getMessage(NOT_FIND_ENTITY.getValue(), clazz.getName()));
  }
}
