package com.tuvarna.geo.service.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuvarna.geo.exception.BadRequestError;
import com.tuvarna.geo.entity.UserType;
import com.tuvarna.geo.repository.UserRepository;
import com.tuvarna.geo.repository.UserTypeRepository;

@Service
public class UserValidateService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;

    @Autowired
    public UserValidateService(UserRepository userRepository, UserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }

    public void validateUserDoesNotExist(String email) {
        if (userRepository.findByEmail(email) != null) {
            throw new BadRequestError("User already exists with email: " + email);
        }
    }

    public UserType validateUserTypeExists(String userTypeString) {
        return userTypeRepository.findByType(userTypeString)
                .orElseThrow(() -> new BadRequestError("UserType not found: " + userTypeString));
    }
}
