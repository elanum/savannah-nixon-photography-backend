package de.savannahnixon.backend.app.controllers;

import static de.savannahnixon.backend.app.OpenApiDefinition.SHOOTING_CATEGORIES;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.savannahnixon.backend.app.dtos.ShootingCategoryCreateDto;
import de.savannahnixon.backend.app.dtos.ShootingCategoryDto;
import de.savannahnixon.backend.app.models.ShootingCategoryEntity;
import de.savannahnixon.backend.app.repositories.ShootingCategoryRepository;
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
  @ResponseBody
  public List<ShootingCategoryDto> getAllShootingCategories() {
    final List<ShootingCategoryEntity> shootingCategoryEntity = shootingCategoryRepository.findAll();

    return shootingCategoryEntity.stream()
        .map(shootingCategory -> modelMapper.map(shootingCategory, ShootingCategoryDto.class))
        .collect(Collectors.toList());
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

  @GetMapping("/{slug}")
  @ResponseBody
  public ShootingCategoryDto getShootingCategoryBySlug(
      @PathVariable(value = "slug") final String slug) {
    final ShootingCategoryEntity shootingCategoryEntity = shootingCategoryRepository.findBySlug(slug);

    return modelMapper.map(shootingCategoryEntity, ShootingCategoryDto.class);
  }
}
