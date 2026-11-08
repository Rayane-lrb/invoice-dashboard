package com.example.invoicebackend.dto.invoice;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceRequestDto {

    @NotBlank
    @Size(max = 64)
    private String gmailMessageId;

    @Size(max = 255)
    private String supplier;

    @Size(max = 64)
    private String invoiceNumber;

    @PastOrPresent
    private LocalDate invoiceDate;

    @Positive
    @Digits(integer = 10, fraction = 2)
    private BigDecimal totalAmount;

    @Pattern(regexp = "^[A-Z]{3}$", message = "must be a 3-letter currency code")
    private String currency;

    @PositiveOrZero
    @Digits(integer = 10, fraction = 2)
    private BigDecimal vatAmount;

    @Size(max = 500)
    private String emailSubject;

    public String getGmailMessageId() { return gmailMessageId; }
    public void setGmailMessageId(String gmailMessageId) { this.gmailMessageId = blankToNull(gmailMessageId); }

    public String getSupplier() { return supplier; }
    public void setSupplier(String supplier) { this.supplier = blankToNull(supplier); }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = blankToNull(invoiceNumber); }

    public LocalDate getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(LocalDate invoiceDate) { this.invoiceDate = invoiceDate; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) {
        String c = blankToNull(currency);
        this.currency = (c == null) ? null : c.toUpperCase();
    }

    public BigDecimal getVatAmount() { return vatAmount; }
    public void setVatAmount(BigDecimal vatAmount) { this.vatAmount = vatAmount; }

    public String getEmailSubject() { return emailSubject; }
    public void setEmailSubject(String emailSubject) { this.emailSubject = blankToNull(emailSubject); }

    private static String blankToNull(String s) {
        return (s == null || s.isBlank()) ? null : s.trim();
    }
}