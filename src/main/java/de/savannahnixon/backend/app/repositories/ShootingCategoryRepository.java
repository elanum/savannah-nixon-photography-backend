package de.savannahnixon.backend.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.savannahnixon.backend.app.models.ShootingCategoryEntity;

public interface ShootingCategoryRepository extends JpaRepository<ShootingCategoryEntity, String> {
  Optional<ShootingCategoryEntity> findBySlug(String slug);
}