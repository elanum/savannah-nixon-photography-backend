package de.savannahnixon.backend.app.repositories;

import org.springframework.data.repository.CrudRepository;

import de.savannahnixon.backend.app.models.ShootingCategoryEntity;

public interface ShootingCategoryRepository extends CrudRepository<ShootingCategoryEntity, Integer> {
}