package com.groups.chat.groups_chat.controllers;

import com.groups.chat.groups_chat.schemas.HealthResponse;
import com.groups.chat.groups_chat.services.HealthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

  private final HealthService heathService;

  public HealthController(HealthService heathService) {
    this.heathService = heathService;
  }

  @RequestMapping("/ping")
  public ResponseEntity<HealthResponse> healthCheck() {
    return ResponseEntity.ok(heathService.checkHealth());
  }
}
