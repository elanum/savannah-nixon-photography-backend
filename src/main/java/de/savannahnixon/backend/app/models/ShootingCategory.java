package de.savannahnixon.backend.app.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ShootingCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public List<ShootingPackage> getShootingPackages() {
    return shootingPackages;
  }

  public void setShootingPackages(List<ShootingPackage> shootingPackages) {
    this.shootingPackages = shootingPackages;
  }

  private String title;

  private String description;

  @Column(nullable = true)
  private String info;

  @OneToMany(mappedBy = "shootingCategory")
  private List<ShootingPackage> shootingPackages;
}
