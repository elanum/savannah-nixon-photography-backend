package de.savannahnixon.backend.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.savannahnixon.backend.app.models.ShootingCategoryEntity;

public interface ShootingCategoryRepository extends JpaRepository<ShootingCategoryEntity, Integer> {
  ShootingCategoryEntity findBySlug(String slug);
}