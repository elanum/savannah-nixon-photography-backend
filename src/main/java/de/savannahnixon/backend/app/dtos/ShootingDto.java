package de.savannahnixon.backend.app.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Schema(description = "Dto for a shooting")
@NoArgsConstructor
@AllArgsConstructor
public class ShootingDto extends BaseDto {
  @Schema(description = "Title of the shooting", nullable = false, example = "Fantasy shooting")
  @NotNull
  private String title;

  @Schema(description = "Slug of the shooting", nullable = false, example = "fantasy-shooting-package")
  @NotNull
  private String slug;

  @Schema(description = "Description of the shooting", nullable = false, example = "Fantasy shooting page is special")
  @NotNull
  private String description;

  @Schema(description = "Additional information about the shooting", example = "Some additional information about the fantasy shooting")
  private String info;

  @Schema(description = "Is the shooting disabled", defaultValue = "false", nullable = false)
  @NotNull
  private Boolean disabled;

  @Schema(description = "List of shooting packages", nullable = false)
  @NotNull
  private List<ShootingPackageDto> shootingPackages;

  @Schema(hidden = true)
  @JsonIgnore
  private CategoryDto category;

  @Schema(description = "Id of the category", nullable = false, format = "uuid", example = "123e4567-e89b-12d3-a456-426614174000")
  private String categoryId;

  @Schema(description = "List of images", nullable = false)
  @NotNull
  private List<ImageDto> images;

  @Schema(description = "Cover image of the shooting", nullable = false)
  @NotNull
  private ImageDto coverImage;

  public String getCategoryId() {
    return category != null ? category.getId() : null;
  }
}
