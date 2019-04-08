package com.ipaixao.cargraphqlapi.util;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;

import java.util.Locale;
import java.util.Properties;

public final class MessageUtils {

  private static MessageSource messageSource;

  static {
    messageSource = messageSource();
  }

  private MessageUtils() {}

  public static String format(final String messageKey, final Object... args) {
    return messageSource.getMessage(messageKey, args, Locale.US);
  }

  private static MessageSource messageSource() {
    final var messageSource = new ResourceBundleMessageSource();
    messageSource.setCommonMessages(yamlProperties());

    return messageSource;
  }

  private static Properties yamlProperties() {
    final var yamlProperties = new YamlPropertiesFactoryBean();
    yamlProperties.setResources(new ClassPathResource("messages.yml"));

    return yamlProperties.getObject();
  }
}
