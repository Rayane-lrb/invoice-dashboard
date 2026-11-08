package com.example.invoicebackend.dto.supplier;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SupplierRequestDto {

    @NotBlank
    private String name;
    @Size(max = 32)
    private String vatNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }
}
