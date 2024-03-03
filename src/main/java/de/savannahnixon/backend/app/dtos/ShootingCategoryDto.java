package de.savannahnixon.backend.app.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Dto for a shooting category")
@AllArgsConstructor
@NoArgsConstructor
public class ShootingCategoryDto extends BaseDto {
  @Schema(description = "Title of the shooting category", nullable = false, example = "Fantasy Shootings")
  @NotNull
  private String title;

  @Schema(description = "Description of the shooting category", nullable = false, example = "Fantasy shootings are a special kind of shootings")
  @NotNull
  private String description;

  @Schema(description = "Additional information about the shooting category", example = "Some additional information about the fantasy shootings")
  private String info;

  @Schema(description = "Slug of the shooting category", nullable = false, example = "fantasy-shootings")
  @NotNull
  private String slug;

  @Schema(description = "List of shooting packages", defaultValue = "[]", nullable = false)
  @NotNull
  private List<ShootingPackageDto> shootingPackages;

  @Schema(description = "List of sub categories", defaultValue = "[]", nullable = false)
  @NotNull
  private List<ShootingCategoryDto> shootingCategories;

  @Schema(nullable = false, description = "Image of the shooting category")
  @NotNull
  private ImageDto image;
}
