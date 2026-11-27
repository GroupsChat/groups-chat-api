package com.groups.chat.groups_chat.model.entity;

import com.groups.chat.groups_chat.model.BaseEntity;
import com.groups.chat.groups_chat.model.enums.JoinPolicy;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "instance_settings")
@AttributeOverride(
    name = "id",
    column = @Column(name = "instance_id", nullable = false, updatable = false))
@Getter
@Setter
@NoArgsConstructor
public class InstanceSettingsEntity extends BaseEntity {

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Column(name = "description", nullable = false, length = 500)
  private String description;

  @Column(name = "icon", length = 255)
  private String icon;

  @Enumerated(EnumType.STRING)
  @Column(name = "join_policy", nullable = false)
  private JoinPolicy joinPolicy;

  @Column(name = "version", nullable = false, length = 20)
  private String version;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "instance_id")
  private InstanceEntity instance;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  @PrePersist
  @PreUpdate
  void updateTimestamp() {
    updatedAt = LocalDateTime.now();
  }
}
