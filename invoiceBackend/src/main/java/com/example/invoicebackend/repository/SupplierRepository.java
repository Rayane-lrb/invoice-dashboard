package com.example.invoicebackend.repository;

import com.example.invoicebackend.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier , UUID> {
    Optional<Supplier> findByVatNumber(String vatNumber);
    List<Supplier> findByNameContainingIgnoreCase(String name);
    boolean existsByVatNumber(String vatNumber);
}
