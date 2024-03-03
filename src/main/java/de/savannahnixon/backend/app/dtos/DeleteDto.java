package de.savannahnixon.backend.app.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "Dto for the deletion of an entity")
@Builder
public class DeleteDto {
  @Schema(description = "Id of the deleted entity", nullable = false)
  @NotNull
  private String id;

  @Schema(description = "Message of the deleted entity", nullable = false)
  @NotNull
  private String message;
}
