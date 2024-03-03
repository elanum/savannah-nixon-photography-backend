package de.savannahnixon.backend.app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Schema
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto extends BaseDto {
  @Schema(description = "Filename of the image", nullable = false)
  @NotNull
  private String filename;

  @Schema(description = "Type of the image", nullable = false)
  @NotNull
  private String type;

  @Schema(description = "Alt text of the image", nullable = false)
  @NotNull
  private String alt;

  @Schema(hidden = true)
  @JsonIgnore
  private ShootingPackageDto shootingPackage;
}
