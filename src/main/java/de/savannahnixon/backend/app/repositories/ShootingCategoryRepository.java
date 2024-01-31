package de.savannahnixon.backend.app.repositories;

import org.springframework.data.repository.CrudRepository;

import de.savannahnixon.backend.app.models.ShootingCategory;

public interface ShootingCategoryRepository extends CrudRepository<ShootingCategory, Integer> {
}