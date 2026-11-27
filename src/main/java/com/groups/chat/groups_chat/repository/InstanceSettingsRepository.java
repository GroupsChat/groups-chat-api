package com.groups.chat.groups_chat.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.groups.chat.groups_chat.model.entity.InstanceSettingsEntity;

public interface InstanceSettingsRepository extends JpaRepository<InstanceSettingsEntity, UUID> {
  @Query(value = "SELECT * FROM instance_settings WHERE instance_id = ?1", nativeQuery = true)
  Optional<InstanceSettingsEntity> existsByInstanceId(UUID instanceId);
}
