package de.savannahnixon.backend.app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {
  @Column(nullable = false)
  private String title;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String description;

  @Column(nullable = true)
  private String info;

  @Column(nullable = false, unique = true)
  private String slug;

  @JsonManagedReference
  @OneToMany(mappedBy = "category")
  private List<ShootingEntity> shootings;

  @JsonManagedReference
  @OneToMany(mappedBy = "parentCategory")
  private List<CategoryEntity> categories;

  @JsonBackReference
  @ManyToOne
  private CategoryEntity parentCategory;

  @OneToOne
  private ImageEntity image;
}
