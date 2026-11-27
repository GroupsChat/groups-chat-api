package com.groups.chat.groups_chat.model.entity;

import com.groups.chat.groups_chat.model.BaseEntity;
import com.groups.chat.groups_chat.model.enums.InstanceStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "instances")
@Getter
@Setter
@NoArgsConstructor
public class InstanceEntity extends BaseEntity {

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private InstanceStatus status;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @OneToOne(mappedBy = "instance", fetch = FetchType.LAZY)
  private InstanceSettingsEntity settings;

  @PrePersist
  void setCreatedAt() {
    if (createdAt == null) {
      createdAt = LocalDateTime.now();
    }
  }
}
