package com.groups.chat.groups_chat.services;

import com.groups.chat.groups_chat.repositories.HealthRepository;
import com.groups.chat.groups_chat.schemas.HealthResponse;
import org.springframework.stereotype.Service;

@Service
public class HealthService {

  private final HealthRepository healthRepository;

  public HealthService(HealthRepository healthRepository) {
    this.healthRepository = healthRepository;
  }

  public HealthResponse checkHealth() {

    return new HealthResponse(healthRepository.check());
  }
}
