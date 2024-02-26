package de.savannahnixon.backend.app.mappers;

import java.util.stream.Collectors;

import de.savannahnixon.backend.app.dtos.ShootingPackageDto;
import de.savannahnixon.backend.app.models.ShootingPackageEntity;

public class ShootingPackageMapper {
  public static ShootingPackageDto toDto(ShootingPackageEntity entity) {
    return new ShootingPackageDto(
        entity.getTitle(),
        entity.getDescription(),
        entity.getInfo(),
        ShootingCategoryMapper.toDto(entity.getShootingCategory())
  }

  public static ShootingPackageEntity toEntity(ShootingPackageDto dto) {
    return new ShootingPackageEntity(
        dto.getTitle(),
        dto.getDescription(),
        dto.getInfo(),
        dto.getShootingPackages().stream().map(ShootingCategoryMapper::toEntity).collect(Collectors.toList()));
  }
}
