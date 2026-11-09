package com.example.invoicebackend.dto;

import com.example.invoicebackend.dto.user.UserRequestDto;
import com.example.invoicebackend.model.UserRole;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.exception.spi.ViolatedConstraintNameExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDtoTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void validRequest_NoViolationExpected() {
        UserRequestDto dto = new UserRequestDto();
        dto.setEmail("test@gmail.com");
        dto.setPassword("test12345");
        dto.setRole(UserRole.ADMIN);

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"       ",  "testwithworngemail"})
    void requestInvalidEmail_ViolationExpected(String arg) {
        UserRequestDto dto = new UserRequestDto();
        dto.setEmail(arg);
        dto.setPassword("test12345");
        dto.setRole(UserRole.ADMIN);

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"              ", "test"})
    void requestInvalidPassword_ViolationExpected(String arg) {
        UserRequestDto dto = new UserRequestDto();
        dto.setEmail("test@gmail.com");
        dto.setPassword(arg);
        dto.setRole(UserRole.ADMIN);

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }
}
