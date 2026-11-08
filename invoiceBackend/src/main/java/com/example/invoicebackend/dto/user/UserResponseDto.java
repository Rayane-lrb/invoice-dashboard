package com.example.invoicebackend.dto.user;

import com.example.invoicebackend.model.UserRole;

import java.time.Instant;
import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String email,
        UserRole role,
        Instant createdAt
) {
}
