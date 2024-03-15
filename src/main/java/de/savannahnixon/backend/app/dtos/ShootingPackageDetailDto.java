package de.savannahnixon.backend.app.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Dto for a shooting package service info")
public class ShootingPackageDetailDto {
  @Schema(nullable = false, description = "Content of the shooting package detail", example = "3 edited photos")
  @NotNull
  private String detail;

  @Schema(nullable = false, description = "Order of the shooting package detail", example = "1")
  @NotNull
  private Integer detail_order;
}
