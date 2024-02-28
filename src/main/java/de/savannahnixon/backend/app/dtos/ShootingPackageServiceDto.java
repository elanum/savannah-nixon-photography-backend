package de.savannahnixon.backend.app.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema
public class ShootingPackageServiceDto {
  @Schema(nullable = false)
  @NotNull
  private String title;

  @Schema(nullable = false)
  @NotNull
  private Float price;

  @Schema(nullable = false)
  @NotNull
  private List<ShootingPackageServiceInfoDto> info;
}
