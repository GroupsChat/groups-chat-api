package com.groups.chat.groups_chat.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.groups.chat.groups_chat.bootstrap.BootstrapEnvironment;
import com.groups.chat.groups_chat.bootstrap.BootstrapInput;
import com.groups.chat.groups_chat.model.entity.InstanceEntity;
import com.groups.chat.groups_chat.model.entity.InstanceSettingsEntity;
import com.groups.chat.groups_chat.model.enums.InstanceStatus;
import com.groups.chat.groups_chat.repository.InstanceRepository;
import com.groups.chat.groups_chat.repository.InstanceSettingsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BootstrapService {
  private final InstanceRepository instanceRepository;
  private final InstanceSettingsRepository settingsRepository;
  private final BootstrapEnvironment bootstrapEnvironment;
  private final BootstrapInputValidator inputValidator;

  public void initialize() {
    Optional<InstanceEntity> existing = instanceRepository.findBootstrapInstance();

    if (existing.isPresent()) {
      InstanceEntity instance = existing.get();

      Optional<InstanceSettingsEntity> hasSettings = settingsRepository.existsByInstanceId(instance.getId());

      if (instance.getStatus() == InstanceStatus.READY && hasSettings.isPresent()) {
        return;
      }

      throw new IllegalStateException(
          "O estado persistido do bootstrap está incompleto");
    }

    BootstrapInput input = inputValidator.validate(bootstrapEnvironment.read());
  }
}
