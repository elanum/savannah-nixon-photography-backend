package de.savannahnixon.backend.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.savannahnixon.backend.app.models.ShootingEntity;

public interface ShootingRepository extends JpaRepository<ShootingEntity, String> {
  Optional<ShootingEntity> findBySlug(String slug);

  List<ShootingEntity> findBySlugIn(List<String> slug);
}