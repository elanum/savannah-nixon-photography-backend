package de.savannahnixon.backend.app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "shooting_packages")
public class ShootingPackageEntity extends BaseEntity {
  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String slug;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String description;

  @Column(nullable = true)
  private String info;

  @JsonManagedReference
  @OneToMany(mappedBy = "shootingPackage", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ShootingPackageServiceEntity> services;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "shooting_category_id")
  private ShootingCategoryEntity shootingCategory;
}
