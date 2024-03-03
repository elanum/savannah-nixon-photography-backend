package de.savannahnixon.backend.app.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Dto for a shooting package service info")
public class ShootingPackageServiceInfoDto {
  @Schema(nullable = false, description = "Content of the shooting package service info", example = "3 edited photos")
  @NotNull
  private String info;

  @Schema(nullable = false, description = "Order of the shooting package service info", example = "1")
  @NotNull
  private Integer info_order;
}
