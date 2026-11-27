package com.groups.chat.groups_chat.repository;

import com.groups.chat.groups_chat.model.entity.InstanceEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InstanceRepository extends JpaRepository<InstanceEntity, UUID> {
  @Query(value = "SELECT * FROM instances ORDER BY created_at ASC, id ASC LIMIT 1", nativeQuery = true)
  Optional<InstanceEntity> findBootstrapInstance();
}
