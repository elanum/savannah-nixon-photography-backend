package de.savannahnixon.backend.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.savannahnixon.backend.app.models.ShootingPackageEntity;

public interface ShootingPackageRepository extends JpaRepository<ShootingPackageEntity, String> {
  Optional<ShootingPackageEntity> findBySlug(String slug);
}