package de.savannahnixon.backend.app.repositories;

import org.springframework.data.repository.CrudRepository;

import de.savannahnixon.backend.app.models.ShootingPackageEntity;

public interface ShootingPackageRepository extends CrudRepository<ShootingPackageEntity, Integer> {
}