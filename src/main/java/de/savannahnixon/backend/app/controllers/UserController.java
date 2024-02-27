package de.savannahnixon.backend.app.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.savannahnixon.backend.app.dtos.UserDto;
import de.savannahnixon.backend.app.models.UserEntity;
import de.savannahnixon.backend.app.repositories.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User")
@RestController
@RequestMapping(path = "/users")
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping
  @ResponseBody
  public List<UserDto> getAllUsers() {
    final List<UserEntity> userEntity = userRepository.findAll();

    return userEntity.stream()
        .map(user -> modelMapper.map(user, UserDto.class))
        .collect(Collectors.toList());
  }
}
