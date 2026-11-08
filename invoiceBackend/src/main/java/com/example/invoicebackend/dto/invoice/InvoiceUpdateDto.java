package com.example.invoicebackend.dto.invoice;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceUpdateDto {

    @Size(max = 255)
    String supplier;

    @Size(max = 64)
    String invoiceNumber;

    @PastOrPresent
    LocalDate invoiceDate;

    @Positive
    @Digits(integer = 10, fraction = 2)
    BigDecimal totalAmount;

    @Pattern(regexp = "^[A-Za-z]{3}$")
    String currency;

    @PositiveOrZero
    @Digits(integer = 10, fraction = 2)
    BigDecimal vatAmount;

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(BigDecimal vatAmount) {
        this.vatAmount = vatAmount;
    }
}
