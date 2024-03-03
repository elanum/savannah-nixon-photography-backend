package de.savannahnixon.backend.app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "images")
public class ImageEntity extends BaseEntity {
  @Column(nullable = false)
  private String filename;

  @Column(nullable = false)
  private String type;

  @Column(nullable = false)
  private String alt;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "package_id")
  private ShootingPackageEntity shootingPackage;
}
