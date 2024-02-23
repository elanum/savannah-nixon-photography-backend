package de.savannahnixon.backend.app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ShootingPackage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  public Integer getId() {
    return id;
  }

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String slug;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String info;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "shooting_category_id")
  @Schema(hidden = true)
  private ShootingCategory shootingCategory;
}
