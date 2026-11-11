package com.example.invoicebackend.repository;

import com.example.invoicebackend.model.Supplier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SupplierRepositoryTest {

    @Autowired
    private SupplierRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    void findByVatNumber_returnsSupplier() {
        em.persist(new Supplier("test_name", "BE0737471303"));
        em.flush();

        Optional<Supplier> result = repository.findByVatNumber("BE0737471303");

        assertTrue(result.isPresent());
        assertEquals("test_name", result.get().getName());
    }

    @Test
    void invalid_findByVatNumber_violationExpected() {
        Optional<Supplier> result = repository.findByVatNumber("BE000000000000");
        assertTrue(result.isEmpty());
    }

    @Test
    void save_duplicateVatNumber_violatesUniqueConstraint() {
        repository.saveAndFlush(new Supplier("Acme", "BE0123456789"));

        assertThatThrownBy(() ->
                repository.saveAndFlush(new Supplier("Other", "BE0123456789")))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void validFindByName_returnsSupplier() {
        em.persist(new Supplier("aaze", "BE646876879844"));
        em.flush();

        List<Supplier> result = repository.findByNameContainingIgnoreCase("aaze");
        assertFalse(result.isEmpty());
    }

    @Test
    void invalidFindByName_violationsExpected() {
        List<Supplier> result = repository.findByNameContainingIgnoreCase("ezfdzeljfn");
        assertTrue(result.isEmpty());
    }
}