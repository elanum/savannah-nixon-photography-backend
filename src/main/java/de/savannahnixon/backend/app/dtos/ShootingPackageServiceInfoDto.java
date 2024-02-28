package de.savannahnixon.backend.app.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema
public class ShootingPackageServiceInfoDto {
  @Schema(nullable = false)
  @NotNull
  private String info;

  @Schema(nullable = false)
  @NotNull
  private Integer info_order;
}
