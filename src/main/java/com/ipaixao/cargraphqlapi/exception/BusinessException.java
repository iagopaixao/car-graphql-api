package com.ipaixao.cargraphqlapi.exception;

import static com.ipaixao.cargraphqlapi.util.MessageUtils.format;

public class BusinessException extends RuntimeException {

  public BusinessException(final String msg, final Object... params) {
    super(format(msg, params));
  }
}
