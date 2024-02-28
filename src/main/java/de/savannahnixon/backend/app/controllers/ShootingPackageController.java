package de.savannahnixon.backend.app.controllers;

import static de.savannahnixon.backend.app.OpenApiDefinition.SHOOTING_PACKAGES;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.savannahnixon.backend.app.dtos.ShootingPackageCreateDto;
import de.savannahnixon.backend.app.dtos.ShootingPackageDto;
import de.savannahnixon.backend.app.models.ShootingPackageEntity;
import de.savannahnixon.backend.app.models.ShootingPackageServiceEntity;
import de.savannahnixon.backend.app.repositories.ShootingPackageRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = SHOOTING_PACKAGES)
@RestController
@RequestMapping(path = "/shooting-packages")
public class ShootingPackageController {
  @Autowired
  private ShootingPackageRepository shootingPackageRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Operation(summary = "Fetches all shooting packages")
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<ShootingPackageDto> getAllShootingPackages() {
    final List<ShootingPackageEntity> shootingPackageEntity = shootingPackageRepository.findAll();

    return shootingPackageEntity.stream()
        .map(shootingPackage -> modelMapper.map(shootingPackage, ShootingPackageDto.class))
        .collect(Collectors.toList());
  }

  @PostMapping
  @ResponseBody
  public ShootingPackageDto addShootingPackage(
      @RequestBody ShootingPackageCreateDto data) {
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    ShootingPackageEntity shootingPackage = modelMapper.map(data, ShootingPackageEntity.class);

    for (ShootingPackageServiceEntity service : shootingPackage.getServices()) {
      service.setShootingPackage(shootingPackage);
    }

    ShootingPackageEntity response = shootingPackageRepository.save(shootingPackage);

    return modelMapper.map(response, ShootingPackageDto.class);
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  public String deleteShootingPackage(@PathVariable(value = "id") final String id) {
    shootingPackageRepository.deleteById(id);

    return "Shooting package with id " + id + " has been deleted";
  }

  @PutMapping("/{id}")
  @ResponseBody
  public ShootingPackageDto updateShootingPackage(@PathVariable(value = "id") final String id,
      @RequestBody ShootingPackageCreateDto data) {
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    ShootingPackageEntity shootingPackage = modelMapper.map(data, ShootingPackageEntity.class);

    for (ShootingPackageServiceEntity service : shootingPackage.getServices()) {
      service.setShootingPackage(shootingPackage);
    }

    ShootingPackageEntity response = shootingPackageRepository.save(shootingPackage);

    return modelMapper.map(response, ShootingPackageDto.class);
  }

  @GetMapping("/{slug}")
  @ResponseBody
  public ResponseEntity<ShootingPackageDto> getShootingPackage(@PathVariable(value = "slug") final String slug) {
    final Optional<ShootingPackageEntity> shootingPackage = shootingPackageRepository.findBySlug(slug);

    if (!shootingPackage.isPresent()) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<ShootingPackageDto>(modelMapper.map(shootingPackage, ShootingPackageDto.class),
        HttpStatus.OK);
  }
}
