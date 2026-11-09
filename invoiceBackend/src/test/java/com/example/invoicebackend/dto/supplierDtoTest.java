package com.example.invoicebackend.dto;

import com.example.invoicebackend.dto.supplier.SupplierRequestDto;
import com.example.invoicebackend.dto.supplier.SupplierResponseDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class supplierDtoTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void validRequest_NoViolationExpected() {
        SupplierRequestDto dto = new SupplierRequestDto();
        dto.setName("test_supplier");
        dto.setVatNumber("BE0639779831");

        Set<ConstraintViolation<SupplierRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void validResponse_NoViolationExpected() {
        SupplierResponseDto dto = new SupplierResponseDto(UUID.randomUUID(), "test_name", "BE0639779831", Instant.now());

        Set<ConstraintViolation<SupplierResponseDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void requestInvalidVatNumberSize_ViolationExpected() {
        SupplierRequestDto dto = new SupplierRequestDto();
        dto.setName("test_name");
        dto.setVatNumber("1".repeat(33));

        Set<ConstraintViolation<SupplierRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void requestInvalidName_ViolationExpected() {
        SupplierRequestDto dto = new SupplierRequestDto();
        dto.setName(" ");
        dto.setVatNumber("BE0639779831");

        Set<ConstraintViolation<SupplierRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }
}
