package com.example.invoicebackend.repository;

import com.example.invoicebackend.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
}
