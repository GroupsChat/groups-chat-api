package com.groups.chat.groups_chat.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class HealthRepository {

  public String check() {
    return "pong";
  }
}
