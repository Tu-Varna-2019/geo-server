package com.tuvarna.geo.service.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tuvarna.geo.exception.BadRequestError;
import com.tuvarna.geo.exception.ForbiddenError;
import com.tuvarna.geo.entity.UserType;
import com.tuvarna.geo.repository.UserRepository;
import com.tuvarna.geo.repository.UserTypeRepository;

@Service
public class UserValidateService {
    @Value("${spring.log.forbidden.user.type}")
    private String forbiddenUserTypeLogStore;

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;

    @Autowired
    public UserValidateService(UserRepository userRepository,
            UserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }

    public void validateUserDoesNotExist(String email) {
        if (userRepository.findByEmail(email) != null) {
            throw new BadRequestError("User already exists with email: " + email);
        }
    }

    public void validateUserExists(String email) {
        if (userRepository.findByEmail(email) == null) {
            throw new ForbiddenError("Incorrect email/password!");
        }
    }

    public void validatePasswordMatch(BCryptPasswordEncoder encodePassword, String actualPassword,
            String expectedPassword) {
        if (!encodePassword.matches(actualPassword, expectedPassword)) {
            throw new ForbiddenError("Incorrect email/password!");
        }
    }

    public void validateIsUserBlocked(Boolean isBlocked) {
        if (isBlocked.booleanValue()) {
            throw new ForbiddenError("User is blocked!");
        }
    }

    public void validateUserTypeExists(String userTypeString) {
        if (userTypeRepository.findByType(userTypeString) == null)
            throw new BadRequestError("UserType not found: " + userTypeString);
    }

    public void validateUseTypeExistWithoutSuperAdmin(String userType) {
        this.validateUserTypeExists(userType);
        if (userType.equals(forbiddenUserTypeLogStore))
            throw new BadRequestError("UserType is prohibited: " + userType);
    }

    public void validateEqualUserTypes(String userTypeDB, String userTypeRequest) {
        if (userTypeDB.equals(userTypeRequest))
            throw new BadRequestError("User is already assigned with type: " + userTypeRequest);
    }
}
