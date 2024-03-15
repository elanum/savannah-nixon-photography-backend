package de.savannahnixon.backend.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.savannahnixon.backend.app.dtos.DeleteDto;
import de.savannahnixon.backend.app.dtos.ShootingCreateDto;
import de.savannahnixon.backend.app.dtos.ShootingDto;
import de.savannahnixon.backend.app.models.CategoryEntity;
import de.savannahnixon.backend.app.models.ImageEntity;
import de.savannahnixon.backend.app.models.ShootingEntity;
import de.savannahnixon.backend.app.models.ShootingPackageEntity;
import de.savannahnixon.backend.app.repositories.CategoryRepository;
import de.savannahnixon.backend.app.repositories.ImageRepository;
import de.savannahnixon.backend.app.repositories.ShootingRepository;

@Service
public class ShootingService {
  @Autowired
  private ShootingRepository shootingRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ImageRepository imageRepository;

  @Autowired
  private ModelMapper modelMapper;

  public List<ShootingDto> getShooting(final List<String> slugs) {
    if (slugs != null && !slugs.isEmpty()) {
      final List<ShootingEntity> shootingEntity = shootingRepository.findBySlugIn(slugs);

      return shootingEntity.stream()
          .map(shootingPackage -> modelMapper.map(shootingPackage, ShootingDto.class))
          .collect(Collectors.toList());
    }

    final List<ShootingEntity> shootingEntity = shootingRepository.findAll();

    return shootingEntity.stream()
        .map(shootingPackage -> modelMapper.map(shootingPackage, ShootingDto.class))
        .collect(Collectors.toList());
  }

  public ShootingDto createShooting(final ShootingCreateDto shootingDto) {
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    ShootingEntity shooting = modelMapper.map(shootingDto, ShootingEntity.class);

    final CategoryEntity categoryEntity = categoryRepository
        .findById(shootingDto.getCategoryId()).orElseThrow();

    shooting.setCategory(categoryEntity);

    final ImageEntity image = imageRepository.findById(shootingDto.getCoverImageId()).orElseThrow();

    shooting.setCoverImage(image);

    for (ShootingPackageEntity shootingPackage : shooting.getShootingPackages()) {
      shootingPackage.setShooting(shooting);
    }

    List<ImageEntity> images = new ArrayList<ImageEntity>();

    for (String imageId : shootingDto.getImageIds()) {
      final ImageEntity imageEntity = imageRepository.findById(imageId).orElseThrow();
      imageEntity.setShooting(shooting);
      images.add(imageEntity);
    }

    shooting.setImages(images);

    shooting = shootingRepository.save(shooting);

    return modelMapper.map(shooting, ShootingDto.class);
  }

  public DeleteDto deleteShooting(final String id) {
    final ShootingEntity shooting = shootingRepository.findById(id).orElseThrow();

    for (ImageEntity images : shooting.getImages()) {
      images.setShooting(null);
    }

    shootingRepository.deleteById(id);

    return DeleteDto.builder().id(id).message("Shooting deleted").build();
  }

  public ShootingDto getShootingById(final String id) {
    final ShootingEntity shooting = shootingRepository.findById(id).orElseThrow();

    return modelMapper.map(shooting, ShootingDto.class);
  }

  public ShootingDto getShootingBySlug(final String slug) {
    final ShootingEntity shooting = shootingRepository.findBySlug(slug).orElseThrow();

    return modelMapper.map(shooting, ShootingDto.class);
  }
}
