package com.ipaixao.cargraphqlapi.exception;

import com.ipaixao.cargraphqlapi.enumeration.Messages;
import com.ipaixao.cargraphqlapi.util.MessageUtils;

public class BusinessException extends RuntimeException {

  public BusinessException(final Messages msg, final Object... params) {
    super(MessageUtils.getMessage(msg.getValue(), params));
  }
}
