package de.savannahnixon.backend.app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class ShootingCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  public Integer getId() {
    return id;
  }

  private String title;

  private String description;

  @Column(nullable = true)
  private String info;

  @JsonManagedReference
  @OneToMany(mappedBy = "shootingCategory")
  private List<ShootingPackage> shootingPackages;
}
