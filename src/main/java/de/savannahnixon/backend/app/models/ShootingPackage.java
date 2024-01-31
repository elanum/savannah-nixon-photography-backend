package de.savannahnixon.backend.app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.github.slugify.Slugify;

@Entity
public class ShootingPackage {
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

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    final Slugify slg = Slugify.builder().build();
    this.slug = slg.slugify(slug);
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

  public ShootingCategory getShootingCategory() {
    return shootingCategory;
  }

  public void setShootingCategory(ShootingCategory shootingCategory) {
    this.shootingCategory = shootingCategory;
  }

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String slug;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String info;

  @ManyToOne
  @JoinColumn(name = "shooting_category_id")
  private ShootingCategory shootingCategory;
}
