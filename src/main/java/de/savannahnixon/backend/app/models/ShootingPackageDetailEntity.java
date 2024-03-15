package de.savannahnixon.backend.app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ShootingPackageDetailEntity {
  @Column(nullable = false)
  private String info;

  @Column(nullable = false)
  private Integer info_order;
}