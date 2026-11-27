package com.groups.chat.groups_chat.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.groups.chat.groups_chat.bootstrap.BootstrapInput;
import org.junit.jupiter.api.Test;

class BootstrapInputValidatorTest {
  private final BootstrapInputValidator validator = new BootstrapInputValidator();

  @Test
  void validatesAndCanonicalizesInput() {
    String password = "p".repeat(64);
    BootstrapInput input = new BootstrapInput("User.Name-1_", password, "Groups Chat");

    BootstrapInput validated = validator.validate(input);

    assertEquals("user.name-1_", validated.masterUsername());
    assertEquals(password, validated.masterPassword());
    assertEquals("Groups Chat", validated.instanceName());
  }

  @Test
  void acceptsUsernameBoundaryLengths() {
    assertEquals(
        "abc",
        validator.validate(new BootstrapInput("ABC", "p".repeat(15), "Instance")).masterUsername());
    assertEquals(
        "a".repeat(32),
        validator
            .validate(new BootstrapInput("A".repeat(32), "p".repeat(15), "Instance"))
            .masterUsername());
  }

  @Test
  void rejectsInvalidUsernames() {
    for (String username : new String[] {"ab", "a".repeat(33), "user name", "éuser"}) {
      assertThrows(
          InvalidBootstrapInputException.class,
          () -> validator.validate(new BootstrapInput(username, "p".repeat(15), "Instance")));
    }
  }

  @Test
  void enforcesMinimumPasswordLengthWithoutTrimming() {
    assertThrows(
        InvalidBootstrapInputException.class,
        () -> validator.validate(new BootstrapInput("admin", "p".repeat(14), "Instance")));
    assertThrows(
        InvalidBootstrapInputException.class,
        () -> validator.validate(new BootstrapInput("admin", null, "Instance")));

    String passwordWithSpaces = " ".repeat(14) + "p";
    assertEquals(
        passwordWithSpaces,
        validator
            .validate(new BootstrapInput("admin", passwordWithSpaces, "Instance"))
            .masterPassword());
  }

  @Test
  void doesNotExposePasswordInInputString() {
    String password = "sensitive-password";

    assertFalse(new BootstrapInput("admin", password, "Instance").toString().contains(password));
  }

  @Test
  void rejectsMissingOrTooLongInstanceNames() {
    for (String instanceName : new String[] {null, "", "   ", "a".repeat(101)}) {
      assertThrows(
          InvalidBootstrapInputException.class,
          () -> validator.validate(new BootstrapInput("admin", "p".repeat(15), instanceName)));
    }
  }

  @Test
  void acceptsInstanceNameAtMaximumLength() {
    String instanceName = "a".repeat(100);

    assertEquals(
        instanceName,
        validator
            .validate(new BootstrapInput("admin", "p".repeat(15), instanceName))
            .instanceName());
  }
}
