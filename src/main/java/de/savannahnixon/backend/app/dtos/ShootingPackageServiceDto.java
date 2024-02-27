package de.savannahnixon.backend.app.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class ShootingPackageServiceDto {
  @Schema(nullable = false)
  private String title;

  @Schema(nullable = false)
  private Float price;

  @Schema(nullable = false)
  private List<String> info;
}
