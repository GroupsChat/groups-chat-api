package com.groups.chat.groups_chat.service;

import com.groups.chat.groups_chat.bootstrap.BootstrapInput;
import java.util.Locale;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class BootstrapInputValidator {
  private static final Pattern MASTER_USERNAME_PATTERN = Pattern.compile("[A-Za-z0-9_.-]{3,32}");
  private static final int MIN_MASTER_PASSWORD_LENGTH = 15;
  private static final int MAX_INSTANCE_NAME_LENGTH = 100;

  public BootstrapInput validate(BootstrapInput input) {
    if (input == null) {
      throw new InvalidBootstrapInputException("Bootstrap input cannot be null");
    }

    if (input.masterUsername() == null
        || !MASTER_USERNAME_PATTERN.matcher(input.masterUsername()).matches()) {
      throw new InvalidBootstrapInputException(
          "Master username must contain 3 to 32 ASCII letters, numbers, '_', '-' or '.'");
    }

    if (input.masterPassword() == null
        || input.masterPassword().codePointCount(0, input.masterPassword().length())
            < MIN_MASTER_PASSWORD_LENGTH) {
      throw new InvalidBootstrapInputException(
          "Master password must contain at least 15 characters");
    }

    if (input.instanceName() == null || input.instanceName().isBlank()) {
      throw new InvalidBootstrapInputException("Instance name cannot be null or blank");
    }

    if (input.instanceName().codePointCount(0, input.instanceName().length())
        > MAX_INSTANCE_NAME_LENGTH) {
      throw new InvalidBootstrapInputException("Instance name cannot exceed 100 characters");
    }

    return new BootstrapInput(
        input.masterUsername().toLowerCase(Locale.ROOT),
        input.masterPassword(),
        input.instanceName());
  }
}
