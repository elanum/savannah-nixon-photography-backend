package de.savannahnixon.backend.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.savannahnixon.backend.app.models.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, String> {
  Optional<ImageEntity> findByFilename(String filename);
}
