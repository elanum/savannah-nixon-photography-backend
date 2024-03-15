package de.savannahnixon.backend.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.savannahnixon.backend.app.dtos.UserDto;
import de.savannahnixon.backend.app.models.UserEntity;
import de.savannahnixon.backend.app.repositories.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  public List<UserDto> getUsers() {
    final List<UserEntity> userEntity = userRepository.findAll();

    return userEntity.stream()
        .map(user -> modelMapper.map(user, UserDto.class))
        .collect(Collectors.toList());
  }
}
