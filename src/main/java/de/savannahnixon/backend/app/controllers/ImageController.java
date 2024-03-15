package de.savannahnixon.backend.app.controllers;

import static de.savannahnixon.backend.app.configs.OpenApiDefinition.IMAGES;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import de.savannahnixon.backend.app.dtos.DeleteDto;
import de.savannahnixon.backend.app.dtos.ImageDto;
import de.savannahnixon.backend.app.services.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = IMAGES)
@RestController
@RequestMapping(path = "/images")
public class ImageController {
  @Autowired
  private ImageService imageService;

  @GetMapping()
  @Operation(summary = "Get all images", description = "Get all images", responses = {
      @ApiResponse(responseCode = "200", description = "All images", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ImageDto.class)))),
  })
  public ResponseEntity<List<ImageDto>> getImages() {
    try {
      final List<ImageDto> images = imageService.getImages();

      return ResponseEntity.ok().body(images);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Operation(summary = "Upload an image", description = "Upload an image", responses = {
      @ApiResponse(responseCode = "200", description = "All images", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImageDto.class))),
  })
  public ResponseEntity<ImageDto> postImage(@RequestPart(required = true) MultipartFile file,
      @RequestPart(name = "alt", required = true) String alt) {
    try {
      ImageDto response = imageService.uploadImage(file, alt);

      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @DeleteMapping(path = "/{id}")
  @Operation(summary = "Delete an image", description = "Delete an image", responses = {
      @ApiResponse(responseCode = "200", description = "The deleted image", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeleteDto.class))),
  })
  public ResponseEntity<DeleteDto> deleteImage(@PathVariable(value = "id", required = true) final String id) {
    try {
      imageService.deleteImage(id);

      return ResponseEntity.ok().body(DeleteDto.builder().id(id).message("Image deleted").build());
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
