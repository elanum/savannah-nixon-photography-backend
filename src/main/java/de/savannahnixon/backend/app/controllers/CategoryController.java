package de.savannahnixon.backend.app.controllers;

import static de.savannahnixon.backend.app.configs.OpenApiDefinition.CATEGORIES;

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

import de.savannahnixon.backend.app.dtos.CategoryCreateDto;
import de.savannahnixon.backend.app.dtos.CategoryDto;
import de.savannahnixon.backend.app.dtos.DeleteDto;
import de.savannahnixon.backend.app.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = CATEGORIES)
@RestController
@RequestMapping(path = "/categories")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @GetMapping
  @Operation(summary = "Get all categories", description = "Get all categories", responses = {
      @ApiResponse(responseCode = "200", description = "All categories", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
  })
  public ResponseEntity<List<CategoryDto>> getCategories(
      @RequestParam(name = "slugs", required = false) final List<String> slugs) {
    try {
      final List<CategoryDto> categories = categoryService.getCategories(slugs);

      return ResponseEntity.ok().body(categories);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping
  @Operation(summary = "Create a new category", description = "Create a new category", responses = {
      @ApiResponse(responseCode = "200", description = "The created category", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))),
  })
  public ResponseEntity<CategoryDto> postCategory(
      @RequestBody final CategoryCreateDto data) {
    try {
      final CategoryDto category = categoryService.createCategory(data);

      return ResponseEntity.status(HttpStatus.CREATED).body(category);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a category", description = "Delete a category", responses = {
      @ApiResponse(responseCode = "200", description = "The deleted category", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeleteDto.class)))
  })
  public ResponseEntity<DeleteDto> deleteCategory(@PathVariable(value = "id") final String id) {
    try {
      final DeleteDto deleteDto = categoryService.deleteCategory(id);

      return ResponseEntity.ok().body(deleteDto);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("/{id}")
  @Operation(summary = "Fetches a category by id", description = "Fetches a category by id", responses = {
      @ApiResponse(responseCode = "200", description = "The category", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class)))
  })
  public ResponseEntity<CategoryDto> getCategoryById(
      @PathVariable(value = "id") final String id) {
    try {
      final CategoryDto category = categoryService.getCategoryById(id);

      return ResponseEntity.ok().body(category);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("/slug/{slug}")
  @Operation(summary = "Fetches a category by slug", description = "Fetches a category by slug", responses = {
      @ApiResponse(responseCode = "200", description = "The category", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class)))
  })
  public ResponseEntity<CategoryDto> getCategoryBySlug(
      @PathVariable(value = "slug") final String slug) {
    try {
      final CategoryDto category = categoryService.getCategoryBySlug(slug);

      return ResponseEntity.ok().body(category);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
