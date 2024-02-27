package de.savannahnixon.backend.app.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class ShootingPackageUpdateDto {
  @Schema(description = "Title of the shooting package")
  private String title;

  @Schema(description = "Slug of the shooting package")
  private String slug;

  @Schema(description = "Description of the shooting package")
  private String description;

  @Schema(description = "Additional information about the shooting package")
  private String info;
}
