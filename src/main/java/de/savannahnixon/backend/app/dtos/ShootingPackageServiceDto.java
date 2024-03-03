package de.savannahnixon.backend.app.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Dto for a shooting package service")
public class ShootingPackageServiceDto {
  @Schema(nullable = false, description = "Title of the shooting package service", example = "Fantasy Shooting contains")
  @NotNull
  private String title;

  @Schema(nullable = false, description = "Description of the shooting package service", example = "199.99")
  @NotNull
  private Float price;

  @Schema(nullable = false, description = "The services list")
  @NotNull
  private List<ShootingPackageServiceInfoDto> info;
}
