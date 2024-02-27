package de.savannahnixon.backend.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.savannahnixon.backend.app.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
