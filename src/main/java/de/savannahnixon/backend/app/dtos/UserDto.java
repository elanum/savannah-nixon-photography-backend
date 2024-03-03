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
@Schema(description = "Dto for a user")
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {
  @Schema(description = "Username of the user", nullable = false, example = "user")
  @NotNull
  private String username;

  @Schema(hidden = true)
  @JsonIgnore
  private String password;
}
