package de.savannahnixon.backend.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import de.savannahnixon.backend.app.models.ShootingPackage;
import de.savannahnixon.backend.app.repositories.ShootingPackageRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/shooting-packages")
public class ShootingPackageController {
  @Autowired
  private ShootingPackageRepository shootingPackageRepository;

  @GetMapping
  public @ResponseBody Iterable<ShootingPackage> getAllShootingPackages() {
    return shootingPackageRepository.findAll();
  }

  @PostMapping
  public @ResponseBody ShootingPackage addShootingPackage(@RequestBody ShootingPackage body) {
    ShootingPackage shootingPackage = new ShootingPackage();
    shootingPackage.setTitle(body.getTitle());
    shootingPackage.setDescription(body.getDescription());
    shootingPackage.setInfo(body.getInfo());
    shootingPackage.setShootingCategory(body.getShootingCategory());
    shootingPackage.setSlug(body.getTitle());

    return shootingPackageRepository.save(shootingPackage);
  }
}
