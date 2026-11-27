package com.groups.chat.groups_chat.model;

import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
  @Id
  @Column(name = "id", nullable = false, updatable = false)
  protected UUID id;

  @PrePersist
  void prePersist() {
    if (id == null) {
      id = UuidCreator.getTimeOrderedEpoch();
    }
  }
}
