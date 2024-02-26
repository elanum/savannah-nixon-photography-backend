package de.savannahnixon.backend.app.controllers;

import static de.savannahnixon.backend.app.OpenApiDefinition.SHOOTING_CATEGORIES;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

  @GetMapping
  @ResponseBody
  public List<ShootingCategoryDto> getAllShootingCategories() {
    Iterable<ShootingCategoryEntity> shootingCategoryEntity = shootingCategoryRepository.findAll();

  }

  @PostMapping
  @ResponseBody
  public ShootingCategoryEntity addShootingCategory(
      @RequestBody @NonNull final ShootingCategoryEntity shootingCategory) {
    return shootingCategoryRepository.save(shootingCategory);
  }

  @GetMapping("/{id}")
  @ResponseBody
  public ShootingCategoryEntity getShootingCategoryById(
      @PathVariable(value = "id") @NonNull final Integer id) {
    return shootingCategoryRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No foundi"));
  }
}
