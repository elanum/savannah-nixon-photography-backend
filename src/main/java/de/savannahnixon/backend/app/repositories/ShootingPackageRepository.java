package de.savannahnixon.backend.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.savannahnixon.backend.app.models.ShootingPackageEntity;

public interface ShootingPackageRepository extends JpaRepository<ShootingPackageEntity, Integer> {
}