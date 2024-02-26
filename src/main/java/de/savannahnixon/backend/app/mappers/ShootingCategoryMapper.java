package de.savannahnixon.backend.app.mappers;

import java.util.stream.Collectors;

import de.savannahnixon.backend.app.dtos.ShootingCategoryDto;
import de.savannahnixon.backend.app.models.ShootingCategoryEntity;

public class ShootingCategoryMapper {
  public static ShootingCategoryDto toDto(ShootingCategoryEntity entity) {
    return new ShootingCategoryDto(
        entity.getTitle(),
        entity.getDescription(),
        entity.getInfo(),
        entity.getShootingPackages().stream().map(ShootingPackageMapper::toDto).collect(Collectors.toList()));
  }

  public static ShootingCategoryEntity toEntity(ShootingCategoryDto dto) {
    return new ShootingCategoryEntity(
        dto.getTitle(),
        dto.getDescription(),
        dto.getInfo(),
        dto.getShootingPackages().stream().map(ShootingPackageMapper::toEntity).collect(Collectors.toList()));
  }
}
