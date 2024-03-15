package de.savannahnixon.backend.app.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Dto for a shooting service")
public class ShootingPackageDto {
  @Schema(nullable = false, description = "Title of the shooting package", example = "Fantasy Shooting contains")
  @NotNull
  private String title;

  @Schema(nullable = false, description = "Description of the shooting package", example = "199.99")
  @NotNull
  private Float price;

  @Schema(nullable = false, description = "The details")
  @NotNull
  private List<ShootingPackageDetailDto> details;
}
