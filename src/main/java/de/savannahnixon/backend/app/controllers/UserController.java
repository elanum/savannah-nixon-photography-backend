package de.savannahnixon.backend.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.savannahnixon.backend.app.dtos.UserDto;
import de.savannahnixon.backend.app.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User")
@RestController
@RequestMapping(path = "/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping
  @Operation(summary = "Fetches all users", description = "Fetches all users", responses = {
      @ApiResponse(responseCode = "200", description = "All Users", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDto.class))))
  })
  public ResponseEntity<List<UserDto>> getUsers() {
    try {
      final List<UserDto> users = userService.getUsers();

      return ResponseEntity.ok().body(users);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
