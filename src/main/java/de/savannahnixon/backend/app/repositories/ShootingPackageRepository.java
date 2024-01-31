package de.savannahnixon.backend.app.repositories;

import org.springframework.data.repository.CrudRepository;

import de.savannahnixon.backend.app.models.ShootingPackage;

public interface ShootingPackageRepository extends CrudRepository<ShootingPackage, Integer> {
}