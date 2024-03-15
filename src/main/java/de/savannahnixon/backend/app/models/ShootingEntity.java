package de.savannahnixon.backend.app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "shootings")
public class ShootingEntity extends BaseEntity {
  @Column(nullable = false)
  private String title;

  @Column(nullable = false, unique = true)
  private String slug;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String description;

  @Column(nullable = true)
  private String info;

  @Column
  @Builder.Default
  private Boolean disabled = false;

  @JsonManagedReference
  @OneToMany(mappedBy = "shooting", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ShootingPackageEntity> shootingPackages;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "category_id")
  private CategoryEntity category;

  @JsonManagedReference
  @OneToMany(mappedBy = "shooting")
  private List<ImageEntity> images;

  @OneToOne(optional = false)
  private ImageEntity coverImage;

  public void addImage(ImageEntity image) {
    images.add(image);
    image.setShooting(this);
  }
}
