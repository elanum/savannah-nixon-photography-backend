package de.savannahnixon.backend.app.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@Schema
@EqualsAndHashCode(callSuper = true)
public class ShootingPackageDto extends BaseDto {
  private String title;

  private String slug;

  private String description;

  private String info;

  @Schema(hidden = true)
  private ShootingCategoryDto shootingCategory;
}
