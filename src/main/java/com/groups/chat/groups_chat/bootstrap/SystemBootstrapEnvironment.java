package com.groups.chat.groups_chat.bootstrap;

import org.springframework.stereotype.Component;

@Component
public class SystemBootstrapEnvironment implements BootstrapEnvironment {
  private static final String MASTER_USERNAME = "BOOTSTRAP_MASTER_USERNAME";
  private static final String MASTER_PASSWORD = "BOOTSTRAP_MASTER_PASSWORD";
  private static final String INSTANCE_NAME = "BOOTSTRAP_INSTANCE_NAME";

  @Override
  public BootstrapInput read() {
    return new BootstrapInput(
        System.getenv(MASTER_USERNAME),
        System.getenv(MASTER_PASSWORD),
        System.getenv(INSTANCE_NAME));
  }
}
