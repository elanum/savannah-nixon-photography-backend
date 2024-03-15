package de.savannahnixon.backend.app.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import de.savannahnixon.backend.app.dtos.CategoryCreateDto;
import de.savannahnixon.backend.app.dtos.CategoryDto;
import de.savannahnixon.backend.app.dtos.DeleteDto;
import de.savannahnixon.backend.app.models.CategoryEntity;
import de.savannahnixon.backend.app.models.ImageEntity;
import de.savannahnixon.backend.app.repositories.CategoryRepository;
import de.savannahnixon.backend.app.repositories.ImageRepository;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ImageRepository imageRepository;

  @Autowired
  private ModelMapper modelMapper;

  public List<CategoryDto> getCategories(final List<String> slugs) {
    if (slugs != null && !slugs.isEmpty()) {
      final List<CategoryEntity> categoryEntity = categoryRepository.findBySlugIn(slugs);

      return categoryEntity.stream()
          .map(shootingCategory -> modelMapper.map(shootingCategory, CategoryDto.class))
          .collect(Collectors.toList());
    }

    final List<CategoryEntity> categoryEntity = categoryRepository.findAll();

    return categoryEntity.stream()
        .map(shootingCategory -> modelMapper.map(shootingCategory, CategoryDto.class))
        .collect(Collectors.toList());
  }

  public CategoryDto createCategory(final CategoryCreateDto categoryDto) {
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    CategoryEntity category = modelMapper.map(categoryDto, CategoryEntity.class);

    if (categoryDto.getParentCategoryId() != null) {
      final Optional<CategoryEntity> parentCategory = categoryRepository
          .findById(categoryDto.getParentCategoryId());

      if (parentCategory.isPresent()) {
        category.setParentCategory(parentCategory.get());
      }
    }

    if (categoryDto.getImageId() != null) {
      final Optional<ImageEntity> image = imageRepository.findById(categoryDto.getImageId());

      if (!image.isPresent()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
      }

      category.setImage(image.get());

    }

    final CategoryEntity response = categoryRepository.save(category);

    return modelMapper.map(response, CategoryDto.class);
  }

  public DeleteDto deleteCategory(final String id) {
    categoryRepository.deleteById(id);

    return DeleteDto.builder().id(id).message("Category deleted").build();
  }

  public CategoryDto getCategoryById(final String id) {
    final Optional<CategoryEntity> category = categoryRepository.findById(id);

    if (category.isPresent()) {
      return modelMapper.map(category.get(), CategoryDto.class);
    }

    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
  }

  public CategoryDto getCategoryBySlug(final String slug) {
    final Optional<CategoryEntity> category = categoryRepository.findBySlug(slug);

    if (category.isPresent()) {
      return modelMapper.map(category.get(), CategoryDto.class);
    }

    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
  }
}
