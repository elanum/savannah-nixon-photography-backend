package de.savannahnixon.backend.app.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ShootingPackageServiceInfoEntity {
  private String info;
  private Integer info_order;
}