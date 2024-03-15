package de.savannahnixon.backend.app.controllers;

import static de.savannahnixon.backend.app.configs.OpenApiDefinition.SHOOTINGS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.savannahnixon.backend.app.dtos.DeleteDto;
import de.savannahnixon.backend.app.dtos.ShootingCreateDto;
import de.savannahnixon.backend.app.dtos.ShootingDto;
import de.savannahnixon.backend.app.services.ShootingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = SHOOTINGS)
@RestController
@RequestMapping(path = "/shootings")
public class ShootingController {
  @Autowired
  private ShootingService shootingService;

  @GetMapping
  @Operation(summary = "Get all shootings", description = "Get all shootings", responses = {
      @ApiResponse(responseCode = "200", description = "All shooting categories", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ShootingDto.class)))),
  })
  public ResponseEntity<List<ShootingDto>> getShootings(
      @RequestParam(name = "slugs", required = false) final List<String> slugs) {
    try {
      final List<ShootingDto> shootings = shootingService.getShooting(slugs);

      return ResponseEntity.ok().body(shootings);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping
  @Operation(summary = "Create a new shooting", description = "Create a new shooting", responses = {
      @ApiResponse(responseCode = "200", description = "The created shooting", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShootingDto.class)))
  })
  public ResponseEntity<ShootingDto> postShooting(@RequestBody final ShootingCreateDto data) {
    try {
      final ShootingDto shooting = shootingService.createShooting(data);

      return ResponseEntity.status(HttpStatus.CREATED).body(shooting);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a shooting", description = "Delete a shooting", responses = {
      @ApiResponse(responseCode = "200", description = "The deleted shooting", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeleteDto.class)))
  })
  public ResponseEntity<DeleteDto> deleteShooting(@PathVariable(value = "id") final String id) {
    try {
      final DeleteDto deleteDto = shootingService.deleteShooting(id);

      return ResponseEntity.ok().body(deleteDto);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("/{id}")
  @Operation(summary = "Fetches a shooting by id", description = "Fetches a shooting by id", responses = {
      @ApiResponse(responseCode = "200", description = "The shooting", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShootingDto.class)))
  })
  public ResponseEntity<ShootingDto> getShootingById(@PathVariable(value = "id") final String id) {
    try {
      final ShootingDto shooting = shootingService.getShootingById(id);

      return ResponseEntity.ok().body(shooting);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("/slug/{slug}")
  @Operation(summary = "Fetches a shooting by slug", description = "Fetches a shooting by slug", responses = {
      @ApiResponse(responseCode = "200", description = "The shooting", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShootingDto.class)))
  })
  public ResponseEntity<ShootingDto> getShootingBySlug(@PathVariable(value = "slug") final String slug) {
    try {
      final ShootingDto shooting = shootingService.getShootingBySlug(slug);

      return ResponseEntity.ok().body(shooting);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
