package com.example.invoicebackend.dto;

import com.example.invoicebackend.dto.invoice.InvoiceRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvoiceDtoTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    private InvoiceRequestDto validDto() {
        InvoiceRequestDto dto = new InvoiceRequestDto();
        dto.setGmailMessageId("18c2f3a9b7d41e55");
        dto.setSupplier("test_supplier");
        dto.setInvoiceNumber("INV-2026-001");
        dto.setInvoiceDate(LocalDate.now().minusDays(1));
        dto.setTotalAmount(new BigDecimal("121.00"));
        dto.setCurrency("EUR");
        dto.setVatAmount(new BigDecimal("21.00"));
        dto.setEmailSubject("Your invoice");
        return dto;
    }

    @Test
    void validRequest_NoViolationExpected() {
        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(validDto());
        assertTrue(violations.isEmpty());
    }

    @Test
    void requestOnlyGmailMessageId_NoViolationExpected() {
        InvoiceRequestDto dto = new InvoiceRequestDto();
        dto.setGmailMessageId("abc123");

        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    // ---------- gmailMessageId ----------

    @Test
    void requestBlankGmailMessageId_ViolationExpected() {
        InvoiceRequestDto dto = validDto();
        dto.setGmailMessageId(" ");

        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void requestGmailMessageIdTooLong_ViolationExpected() {
        InvoiceRequestDto dto = validDto();
        dto.setGmailMessageId("a".repeat(65));

        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void requestGmailMessageIdAtLimit_NoViolationExpected() {
        InvoiceRequestDto dto = validDto();
        dto.setGmailMessageId("a".repeat(64));

        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    // ---------- size limits ----------

    @Test
    void requestSupplierTooLong_ViolationExpected() {
        InvoiceRequestDto dto = validDto();
        dto.setSupplier("s".repeat(256));

        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void requestInvoiceNumberTooLong_ViolationExpected() {
        InvoiceRequestDto dto = validDto();
        dto.setInvoiceNumber("n".repeat(65));

        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void requestEmailSubjectTooLong_ViolationExpected() {
        InvoiceRequestDto dto = validDto();
        dto.setEmailSubject("x".repeat(501));

        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    // ---------- invoiceDate ----------

    @Test
    void requestInvoiceDateInFuture_ViolationExpected() {
        InvoiceRequestDto dto = validDto();
        dto.setInvoiceDate(LocalDate.now().plusDays(1));

        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void requestInvoiceDateToday_NoViolationExpected() {
        InvoiceRequestDto dto = validDto();
        dto.setInvoiceDate(LocalDate.now());

        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    // ---------- amounts ----------

    @Test
    void requestTotalAmountZero_ViolationExpected() {
        InvoiceRequestDto dto = validDto();
        dto.setTotalAmount(BigDecimal.ZERO);

        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void requestTotalAmountNegative_ViolationExpected() {
        InvoiceRequestDto dto = validDto();
        dto.setTotalAmount(new BigDecimal("-1"));

        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void requestTotalAmountTooManyDecimals_ViolationExpected() {
        InvoiceRequestDto dto = validDto();
        dto.setTotalAmount(new BigDecimal("1.234"));

        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void requestTotalAmountTooManyIntegerDigits_ViolationExpected() {
        InvoiceRequestDto dto = validDto();
        dto.setTotalAmount(new BigDecimal("10000000000.00"));

        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void requestVatAmountZero_NoViolationExpected() {
        InvoiceRequestDto dto = validDto();
        dto.setVatAmount(BigDecimal.ZERO);

        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void requestVatAmountNegative_ViolationExpected() {
        InvoiceRequestDto dto = validDto();
        dto.setVatAmount(new BigDecimal("-0.01"));

        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void requestVatAmountTooManyDecimals_ViolationExpected() {
        InvoiceRequestDto dto = validDto();
        dto.setVatAmount(new BigDecimal("21.005"));

        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    // ---------- currency ----------

    @Test
    void requestCurrencyTooShort_ViolationExpected() {
        InvoiceRequestDto dto = validDto();
        dto.setCurrency("EU");

        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void requestCurrencyWithDigit_ViolationExpected() {
        InvoiceRequestDto dto = validDto();
        dto.setCurrency("E1R");

        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void requestCurrencyLowercase_IsUppercasedAndNoViolationExpected() {
        InvoiceRequestDto dto = validDto();
        dto.setCurrency("  eur ");

        assertEquals("EUR", dto.getCurrency());
        Set<ConstraintViolation<InvoiceRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    // ---------- normalizing setters ----------

    @Test
    void requestBlankStrings_BecomeNull() {
        InvoiceRequestDto dto = new InvoiceRequestDto();
        dto.setSupplier("   ");
        dto.setInvoiceNumber("");
        dto.setEmailSubject("\t");
        dto.setCurrency(" ");

        assertNull(dto.getSupplier());
        assertNull(dto.getInvoiceNumber());
        assertNull(dto.getEmailSubject());
        assertNull(dto.getCurrency());
    }

    @Test
    void requestStrings_AreTrimmed() {
        InvoiceRequestDto dto = new InvoiceRequestDto();
        dto.setSupplier("  ACME NV ");
        dto.setInvoiceNumber(" INV-1\t");
        dto.setGmailMessageId(" abc ");

        assertEquals("ACME NV", dto.getSupplier());
        assertEquals("INV-1", dto.getInvoiceNumber());
        assertEquals("abc", dto.getGmailMessageId());
    }
}