package com.groups.chat.groups_chat.model.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UsernameTest {

  @ParameterizedTest
  @ValueSource(strings = {"abc", "Thiago", "user123", "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456"})
  void acceptsValidUsernames(String value) {
    assertThat(new Username(value).value()).isEqualTo(value);
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "ab",
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567",
        "",
        "user_name",
        "user.name",
        "user-name",
        "user name",
        "joão",
        "user😀"
      })
  void rejectsInvalidUsernames(String value) {
    assertThatThrownBy(() -> new Username(value)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void rejectsNull() {
    assertThatThrownBy(() -> new Username(null)).isInstanceOf(NullPointerException.class);
  }

  @Test
  void canonicalIsLowercase() {
    assertThat(new Username("ThIaGo").canonical()).isEqualTo("thiago");
  }

  @Test
  void differentCasesShareCanonicalForm() {
    assertThat(new Username("THIAGO").canonical()).isEqualTo(new Username("thiago").canonical());
  }
}
