package com.example.invoicebackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @NotBlank
    @Column(name = "normalized_name", nullable = false, unique = true, length = 255)
    private String normalizedName;

    @Column(name = "vat_number", unique = true, length = 32)
    private String vatNumber;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    protected Supplier() {
    }

    public Supplier(String name, String vatNumber) {
        this.name = name.trim();
        this.normalizedName = normalize(name);
        this.vatNumber = vatNumber;
    }

    @PrePersist
    void onCreate() {
        this.createdAt = Instant.now();
    }

    public static String normalize(String raw) {
        return raw.trim()
                .toLowerCase()
                .replaceAll("[.,]", "")
                .replaceAll("\\b(nv|bv|bvba|srl|sa|sprl|vzw|ltd|llc|inc|gmbh)\\b", "")
                .replaceAll("\\s+", " ")
                .trim();
    }
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNormalizedName() {
        return normalizedName;
    }

    public void setNormalizedName(String normalizedName) {
        this.normalizedName = normalizedName;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
