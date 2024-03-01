package de.savannahnixon.backend.app.controllers;

import static de.savannahnixon.backend.app.configs.OpenApiDefinition.IMAGES;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import de.savannahnixon.backend.app.dtos.DeleteDto;
import de.savannahnixon.backend.app.dtos.ImageDto;
import de.savannahnixon.backend.app.models.ImageEntity;
import de.savannahnixon.backend.app.repositories.ImageRepository;
import de.savannahnixon.backend.app.services.ImageService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = IMAGES)
@RestController
@RequestMapping(path = "/images")
public class ImageController {
  @Autowired
  private ImageRepository imageRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private ImageService imageService;

  @GetMapping
  @ResponseBody
  public ResponseEntity<List<ImageDto>> getAllImages() {
    try {
      final List<ImageEntity> images = imageRepository.findAll();

      return ResponseEntity.ok().body(images.stream()
          .map(image -> modelMapper.map(image, ImageDto.class))
          .collect(Collectors.toList()));
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping(consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseBody
  @RequestBody(required = true)
  public ResponseEntity<ImageDto> uploadImage(@RequestPart(required = true) MultipartFile file) {
    try {
      ImageDto response = imageService.uploadImage(file);

      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      System.out.println(e);
      return ResponseEntity.internalServerError().build();
    }
  }

  @DeleteMapping(path = "/{id}")
  @ResponseBody
  public ResponseEntity<DeleteDto> deleteImage(@PathVariable(value = "id", required = true) final String id) {
    try {
      imageService.deleteImage(id);
      imageRepository.deleteById(id);

      return ResponseEntity.ok().body(DeleteDto.builder().id(id).message("Image deleted").build());
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
