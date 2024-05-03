package de.savannahnixon.backend.app.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.github.slugify.Slugify;

import de.savannahnixon.backend.app.dtos.ImageDto;
import de.savannahnixon.backend.app.models.ImageEntity;
import de.savannahnixon.backend.app.repositories.ImageRepository;

@Service
public class ImageService {
  @Autowired
  private ImageRepository imageRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Value("${backend.url}")
  private String backendUrl;

  private final String uploadPath = "files/images";

  private String getFilename(MultipartFile multipartFile) {
    final Slugify slg = Slugify.builder().build();
    String originalFilename = multipartFile.getOriginalFilename();
    if (originalFilename != null) {
      int lastDotIndex = originalFilename.lastIndexOf('.');
      String nameWithoutExtension = lastDotIndex != -1 ? originalFilename.substring(0, lastDotIndex) : originalFilename;
      String cleanedName = slg.slugify(nameWithoutExtension);

      if (lastDotIndex != -1) {
        String extension = originalFilename.substring(lastDotIndex);
        return cleanedName + extension;
      } else {
        return cleanedName;
      }
    }
    return originalFilename;
  }

  private String getUniqueFilename(String filename, File folder) {
    File file = new File(folder, filename);
    int counter = 1;
    while (file.exists()) {
      String nameWithoutExtension = filename.lastIndexOf('.') != -1
          ? filename.substring(0, filename.lastIndexOf('.'))
          : filename;
      String extension = filename.lastIndexOf('.') != -1 ? filename.substring(filename.lastIndexOf('.')) : "";
      filename = nameWithoutExtension + "_" + counter + extension;
      file = new File(folder, filename);
      counter++;
    }
    return filename;
  }

  public ImageDto uploadImage(MultipartFile file, String alt)
      throws FileNotFoundException, URISyntaxException, IOException {
    if (file.isEmpty()) {
      throw new RuntimeException("File is empty");
    }

    if (alt.isEmpty()) {
      throw new RuntimeException("Alt is empty");
    }

    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    File staticFolder = new File(uploadPath);

    if (!staticFolder.exists()) {
      if (!staticFolder.mkdirs()) {
        throw new RuntimeException("Could not create the directory");
      }
    }

    final String fileName = getFilename(file);
    final String uniqueFileName = getUniqueFilename(fileName, staticFolder);

    Path filePath = Path.of(staticFolder.getAbsolutePath(), uniqueFileName);

    try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
      fos.write(file.getBytes());
    }

    ImageDto imageDto = new ImageDto();
    imageDto.setAlt(alt);
    imageDto.setType(file.getContentType());
    imageDto.setFilename(uniqueFileName);

    ImageEntity imageEntity = imageRepository.save(modelMapper.map(imageDto, ImageEntity.class));

    return modelMapper.map(imageEntity, ImageDto.class);
  }

  public void deleteImage(String id) throws FileNotFoundException {
    Optional<ImageEntity> imageEntity = imageRepository.findById(id);

    if (!imageEntity.isPresent()) {
      throw new RuntimeException("Image " + id + " not found");
    }

    imageRepository.deleteById(id);

    File staticFolder = ResourceUtils.getFile(uploadPath);
    Path filePath = Paths.get(staticFolder.getAbsolutePath(), imageEntity.get().getFilename());

    if (!filePath.toFile().exists()) {
      throw new RuntimeException("Imagepath " + filePath + " does not exists");
    }

    if (!filePath.toFile().delete()) {
      throw new RuntimeException("Could not delete the file");
    }
  }

  public List<ImageDto> getImages() {
    final List<ImageEntity> images = imageRepository.findAll();

    return images.stream()
        .map(image -> modelMapper.map(image, ImageDto.class))
        .collect(Collectors.toList());
  }

  public List<ImageDto> sortImages(List<ImageDto> images) {
    Collections.sort(images, (a, b) -> {
      Integer orderA = a.getOrder() != null ? a.getOrder() : 0;
      Integer orderB = b.getOrder() != null ? b.getOrder() : 0;

      return orderA.compareTo(orderB);
    });

    return images;
  }
};