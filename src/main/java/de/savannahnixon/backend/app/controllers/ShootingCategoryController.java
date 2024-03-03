package de.savannahnixon.backend.app.controllers;

import static de.savannahnixon.backend.app.configs.OpenApiDefinition.SHOOTING_CATEGORIES;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.savannahnixon.backend.app.dtos.ShootingCategoryCreateDto;
import de.savannahnixon.backend.app.dtos.ShootingCategoryDto;
import de.savannahnixon.backend.app.models.ShootingCategoryEntity;
import de.savannahnixon.backend.app.repositories.ShootingCategoryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = SHOOTING_CATEGORIES)
@RestController
@RequestMapping(path = "/shooting-categories")
public class ShootingCategoryController {
  @Autowired
  private ShootingCategoryRepository shootingCategoryRepository;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping
  @Operation(summary = "Get all shooting categories", description = "Get all shooting categories", responses = {
      @ApiResponse(responseCode = "200", description = "All shooting categories", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ShootingCategoryDto.class)))),
  })
  public ResponseEntity<List<ShootingCategoryDto>> getAllShootingCategories(
      @RequestParam(name = "slugs", required = false) final List<String> slugs) {
    try {
      if (slugs != null && !slugs.isEmpty()) {
        final List<ShootingCategoryEntity> shootingCategoryEntity = shootingCategoryRepository.findBySlugIn(slugs);

        return ResponseEntity.ok().body(shootingCategoryEntity.stream()
            .map(shootingCategory -> modelMapper.map(shootingCategory, ShootingCategoryDto.class))
            .collect(Collectors.toList()));
      }

      final List<ShootingCategoryEntity> shootingCategoryEntity = shootingCategoryRepository.findAll();

      return ResponseEntity.ok().body(shootingCategoryEntity.stream()
          .map(shootingCategory -> modelMapper.map(shootingCategory, ShootingCategoryDto.class))
          .collect(Collectors.toList()));
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping
  @ResponseBody
  public ShootingCategoryDto addShootingCategory(
      @RequestBody @NonNull final ShootingCategoryCreateDto data) {
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    ShootingCategoryEntity shootingCategory = modelMapper.map(data, ShootingCategoryEntity.class);

    if (data.getParentCategoryId() != null) {
      final Optional<ShootingCategoryEntity> parentCategory = shootingCategoryRepository
          .findById(data.getParentCategoryId());

      if (parentCategory.isPresent()) {
        shootingCategory.setParentCategory(parentCategory.get());
      }
    }

    final ShootingCategoryEntity response = shootingCategoryRepository.save(shootingCategory);

    return modelMapper.map(response, ShootingCategoryDto.class);
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  public String deleteShootingCategoryById(@PathVariable(value = "id") final String id) {
    shootingCategoryRepository.deleteById(id);

    return "Shooting category with id " + id + " has been deleted";
  }

  @GetMapping("/{slug}")
  @ResponseBody
  public ResponseEntity<ShootingCategoryDto> getShootingCategoryBySlug(
      @PathVariable(value = "slug") final String slug) {
    final Optional<ShootingCategoryEntity> shootingCategoryEntity = shootingCategoryRepository.findBySlug(slug);

    if (!shootingCategoryEntity.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(modelMapper.map(shootingCategoryEntity, ShootingCategoryDto.class));

  }
}
