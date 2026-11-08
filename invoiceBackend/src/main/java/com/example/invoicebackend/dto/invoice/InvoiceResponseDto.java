package com.example.invoicebackend.dto.invoice;

import com.example.invoicebackend.model.InvoiceStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record InvoiceResponseDto(
        UUID id,
        SupplierSummary supplier,
        String supplierNameRaw,
        String invoiceNumber,
        LocalDate invoiceDate,
        BigDecimal totalAmount,
        String currency,
        BigDecimal vatAmount,
        String emailSubject,
        InvoiceStatus status,
        boolean hasFile,
        String fileName,
        String reviewedBy,
        Instant reviewedAt,
        Instant createdAt,
        Instant updatedAt
) {

    public record SupplierSummary(UUID id, String name) {}
}
