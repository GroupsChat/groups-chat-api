package com.groups.chat.groups_chat.bootstrap;

public record BootstrapInput(String masterUsername, String masterPassword, String instanceName) {

  @Override
  public String toString() {
    return "BootstrapInput[masterUsername=%s, masterPassword=[REDACTED], ".formatted(masterUsername)
        + "instanceName=%s]".formatted(instanceName);
  }
}
