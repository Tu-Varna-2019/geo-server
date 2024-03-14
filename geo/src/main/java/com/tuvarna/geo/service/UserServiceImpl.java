package com.tuvarna.geo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuvarna.geo.entity.User;
import com.tuvarna.geo.entity.UserType;
import com.tuvarna.geo.repository.UserRepository;
import com.tuvarna.geo.repository.UserTypeRepository;
import com.tuvarna.geo.service.dto.UserDTO;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserTypeRepository userTypeRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    @Transactional
    public void registerUser(UserDTO userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword()); // Consider encrypting this!
        user.setisblocked(userDto.getIsblocked());

        if (userDto.getUsertype() != null) {
            UserType userType = userTypeRepository.findByType(userDto.getUsertype())
                    .orElseThrow(
                            () -> new EntityNotFoundException("UserType not found with id " + userDto.getUsertype()));
            user.setUserType(userType);
        }

        userRepository.save(user);
    }
}
