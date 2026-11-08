package com.example.invoicebackend.dto.supplier;

import java.time.Instant;
import java.util.UUID;

public record SupplierResponseDto(
        UUID id,
        String name,
        String vatNumber,
        Instant createdAt
) {
}
