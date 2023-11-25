package ru.kata.spring.boot_security.demo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    @DisplayName("Test to verify the 'getId' method when the 'id' field is set")
    public void testGetIdWhenIdIsSetThenReturnId() {
        // Arrange
        int expectedId = 1;
        user.setId(expectedId);

        // Act
        long actualId = user.getId();

        // Assert
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Test to verify the 'getId' method when the 'id' field is not set")
    public void testGetIdWhenIdIsNotSetThenReturnDefaultId() {
        // Arrange
        int expectedId = 0;

        // Act
        long actualId = user.getId();

        // Assert
        assertThat(actualId).isEqualTo(expectedId);
    }
}