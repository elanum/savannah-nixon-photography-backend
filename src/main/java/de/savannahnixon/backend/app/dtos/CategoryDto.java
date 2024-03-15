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
@Schema(description = "Dto for a category")
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto extends BaseDto {
  @Schema(description = "Title of the category", nullable = false, example = "Fantasy Shootings")
  @NotNull
  private String title;

  @Schema(description = "Description of the category", nullable = false, example = "Fantasy shootings are a special kind of shootings")
  @NotNull
  private String description;

  @Schema(description = "Additional information about the category", example = "Some additional information about the fantasy shootings")
  private String info;

  @Schema(description = "Slug of the category", nullable = false, example = "fantasy-shootings")
  @NotNull
  private String slug;

  @Schema(description = "List of shootings", nullable = false)
  @NotNull
  private List<ShootingDto> shootings;

  @Schema(description = "List of sub categories", nullable = false)
  @NotNull
  private List<CategoryDto> categories;

  @Schema(nullable = false, description = "Image of the category")
  @NotNull
  private ImageDto image;

  @Schema(hidden = true)
  @JsonIgnore
  private CategoryDto parentCategory;

  @Schema
  private String parentCategoryId;

  public String getParentCategoryId() {
    return parentCategory != null ? parentCategory.getId() : null;
  }
}
