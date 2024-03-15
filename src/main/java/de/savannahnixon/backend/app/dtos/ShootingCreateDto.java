package de.savannahnixon.backend.app.dtos;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Dto for creating a shooting")
public class ShootingCreateDto {
  @Schema(description = "Title of the shooting", nullable = false, example = "Fantasy Shooting")
  @NotNull
  private String title;

  @Schema(description = "Slug of the shooting", nullable = false, example = "fantasy-shooting")
  @NotNull
  private String slug;

  @Schema(description = "Description of the shooting", nullable = false, example = "Fantasy shootings are a special kind of shootings")
  @NotNull
  private String description;

  @Schema(description = "List of shooting packages")
  private List<ShootingPackageDto> shootingPackages = new ArrayList<ShootingPackageDto>();

  @Schema(description = "Additional information about the shooting", example = "Some additional information about the fantasy shootings")
  private String info;

  @Schema(description = "Is the shooting disabled", defaultValue = "false", nullable = false)
  @NotNull
  private Boolean disabled;

  @Schema(description = "List of image ids", nullable = false)
  private List<String> imageIds = new ArrayList<>();

  @Schema(description = "Cover image id", nullable = false, example = "123e4567-e89b-12d3-a456-426614174000", format = "uuid")
  @NotNull
  private String coverImageId;

  @Schema(description = "Category id", nullable = false, example = "123e4567-e89b-12d3-a456-426614174000", format = "uuid")
  @NotNull
  private String categoryId;
}
