package de.savannahnixon.backend.app.controllers;

import static de.savannahnixon.backend.app.OpenApiDefinition.SHOOTING_PACKAGES;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.savannahnixon.backend.app.models.ShootingPackage;
import de.savannahnixon.backend.app.repositories.ShootingPackageRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = SHOOTING_PACKAGES)
@RestController
@RequestMapping(path = "/shooting-packages")
public class ShootingPackageController {
  @Autowired
  private ShootingPackageRepository shootingPackageRepository;

  @Operation(summary = "Fetches all shooting packages")
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<ShootingPackage> getAllShootingPackages() {
    List<ShootingPackage> shootingPackages = new ArrayList<ShootingPackage>();
    shootingPackageRepository.findAll().forEach(shootingPackages::add);
    return shootingPackages;
  }

  @PostMapping
  public @ResponseBody ShootingPackage addShootingPackage(@RequestBody @NonNull ShootingPackage shootingPackage) {
    return shootingPackageRepository.save(shootingPackage);
  }
}
