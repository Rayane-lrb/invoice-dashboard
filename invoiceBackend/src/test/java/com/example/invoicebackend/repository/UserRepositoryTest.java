package com.example.invoicebackend.repository;

import com.example.invoicebackend.model.User;
import com.example.invoicebackend.model.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    void validFindUserByEmail_returnsUser() {
        em.persist(new User("test@gmail.com", "test12345", UserRole.ADMIN));
        em.flush();

        Optional<User> result = repository.findByEmail("test@gmail.com");
        assertTrue(result.isPresent());
    }

    @Test
    void invalidFindUserByEmail() {
        Optional<User> result = repository.findByEmail("invalid@gmail.com");
        assertTrue(result.isEmpty());
    }
}