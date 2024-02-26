package de.savannahnixon.backend.app.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema
public class ShootingCategoryCreateDto {
  @Schema(description = "Title of the shooting category", nullable = false)
  @NotNull
  private String title;

  @Schema(description = "Description of the shooting category", nullable = false)
  @NotNull
  private String description;

  @Schema(description = "Additional information about the shooting category")
  private String info;
}
