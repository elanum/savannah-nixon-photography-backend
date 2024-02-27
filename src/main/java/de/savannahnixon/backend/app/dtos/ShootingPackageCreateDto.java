package de.savannahnixon.backend.app.dtos;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema
public class ShootingPackageCreateDto {
  @Schema(description = "Title of the shooting package", nullable = false)
  @NotNull
  private String title;

  @Schema(description = "Slug of the shooting package", nullable = false)
  @NotNull
  private String slug;

  @Schema(description = "Description of the shooting package", nullable = false)
  @NotNull
  private String description;

  @Schema(description = "List of services")
  private List<ShootingPackageServiceDto> services = new ArrayList<>();

  @Schema(description = "Additional information about the shooting package")
  private String info;
}
