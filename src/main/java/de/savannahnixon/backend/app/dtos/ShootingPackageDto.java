package de.savannahnixon.backend.app.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
  @Schema(description = "Title of the shooting package", nullable = false)
  @NotNull
  private String title;

  @Schema(description = "Slug of the shooting package", nullable = false)
  @NotNull
  private String slug;

  @Schema(description = "Description of the shooting package", nullable = false)
  @NotNull
  private String description;

  @Schema(description = "Additional information about the shooting package")
  private String info;

  @Schema(description = "List of services", defaultValue = "[]", nullable = false)
  @NotNull
  private List<ShootingPackageServiceDto> services;

  @Schema(hidden = true)
  private ShootingCategoryDto shootingCategory;
}
