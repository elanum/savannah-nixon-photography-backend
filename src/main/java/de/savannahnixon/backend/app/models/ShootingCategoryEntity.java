package de.savannahnixon.backend.app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "shooting_categories")
public class ShootingCategoryEntity extends BaseEntity {
  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String description;

  @Column(nullable = true)
  private String info;

  @Column(nullable = false, unique = true)
  private String slug;

  @JsonManagedReference
  @OneToMany(mappedBy = "shootingCategory")
  private List<ShootingPackageEntity> shootingPackages;

  @JsonManagedReference
  @OneToMany(mappedBy = "parentCategory")
  private List<ShootingCategoryEntity> shootingCategories;

  @JsonBackReference
  @ManyToOne
  private ShootingCategoryEntity parentCategory;
}
