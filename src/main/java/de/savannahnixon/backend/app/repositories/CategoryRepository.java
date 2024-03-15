package de.savannahnixon.backend.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.savannahnixon.backend.app.models.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
  Optional<CategoryEntity> findBySlug(String slug);

  List<CategoryEntity> findBySlugIn(List<String> slug);
}