package de.savannahnixon.backend.app.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Dto for the creation of a category")
public class CategoryCreateDto {
  @Schema(description = "Title of the category", nullable = false, example = "Fantasy Shootings")
  @NotNull
  private String title;

  @Schema(description = "Slug of the category", nullable = false, example = "fantasy-shootings")
  @NotNull
  private String slug;

  @Schema(description = "Description of the category", nullable = false, example = "Fantasy shootings are a special kind of shootings")
  @NotNull
  private String description;

  @Schema(description = "Additional information about the category", example = "Some additional information about the fantasy shootings")
  private String info;

  @Schema(description = "Image id of the category", nullable = false, format = "uuid", example = "123e4567-e89b-12d3-a456-426614174000")
  @NotNull
  private String imageId;
}
