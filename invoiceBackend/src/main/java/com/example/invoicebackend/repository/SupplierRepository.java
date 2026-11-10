package com.example.invoicebackend.repository;

import com.example.invoicebackend.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier , UUID> {
}
