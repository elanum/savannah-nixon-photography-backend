package de.savannahnixon.backend.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.savannahnixon.backend.app.models.ShootingCategory;
import de.savannahnixon.backend.app.repositories.ShootingCategoryRepository;

@Controller
@RequestMapping(path = "/shooting-categories")
public class ShootingCategoryController {
  @Autowired
  private ShootingCategoryRepository shootingCategoryRepository;

  @GetMapping
  public @ResponseBody Iterable<ShootingCategory> getAllShootingCategories() {
    return shootingCategoryRepository.findAll();
  }

  @PostMapping
  public @ResponseBody ShootingCategory addShootingCategory(@RequestBody ShootingCategory body) {
    ShootingCategory shooting = new ShootingCategory();
    shooting.setTitle(body.getTitle());
    shooting.setDescription(body.getDescription());
    shooting.setInfo(body.getInfo());

    return shootingCategoryRepository.save(shooting);
  }

  @GetMapping("/{id}")
  public @ResponseBody ShootingCategory getShootingCategoryById(@PathVariable(value = "id") Integer id) {
    ShootingCategory shooting = shootingCategoryRepository.findById(Integer.valueOf(id)).orElseThrow();
    return shooting;
  }
}
