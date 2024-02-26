package de.savannahnixon.backend.app.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Schema
@AllArgsConstructor
public class ShootingCategoryDto extends BaseDto {
  private String title;

  private String description;

  private String info;

  private List<ShootingPackageDto> shootingPackages;
}
