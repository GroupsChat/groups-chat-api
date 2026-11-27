package com.groups.chat.groups_chat.model.valueobject;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public record Username(String value) {
  private static final Pattern ALLOWED = Pattern.compile("^[A-Za-z0-9]{3,32}$");

  public Username {
    Objects.requireNonNull(value, "username não pode ser nulo");
    if (!ALLOWED.matcher(value).matches()) {
      throw new IllegalArgumentException(
          "username deve ter de 3 a 32 caracteres, apenas letras e números");
    }
  }

  public String canonical() {
    return value.toLowerCase(Locale.ROOT);
  }
}
