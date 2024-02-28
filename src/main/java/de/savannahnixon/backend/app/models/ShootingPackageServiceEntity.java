package de.savannahnixon.backend.app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
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
@Table(name = "shooting_package_services")
public class ShootingPackageServiceEntity extends BaseEntity {
  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private Float price;

  @ElementCollection(targetClass = ShootingPackageServiceInfoEntity.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "shooting_package_service_info", joinColumns = @JoinColumn(name = "shooting_package_service_id"))
  private List<ShootingPackageServiceInfoEntity> info;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "shooting_package_id")
  private ShootingPackageEntity shootingPackage;
}
