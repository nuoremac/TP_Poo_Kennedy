package service;

import java.util.Objects;

public class AuthService {
    private static final String DEFAULT_ADMIN_USERNAME = "admin";
    private static String adminPassword = "securePassword123"; // Removed final modifier

    // In a real application, store hashed passwords only!
    private String hashedPassword; // Example for future hashing implementation

    public AuthService() {
        // Initialize with default or load from secure configuration
        this.hashedPassword = hashPassword(adminPassword);
    }

    public boolean authenticate(String username, String password) {
        return DEFAULT_ADMIN_USERNAME.equals(username) &&
                verifyPassword(password, this.hashedPassword);
    }

    public boolean isPasswordStrong(String password) {
        // Minimum 8 chars, with at least 1 digit, 1 letter, and 1 special char
        return password != null &&
                password.length() >= 8 &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[a-zA-Z].*") &&
                password.matches(".*[!@#$%^&*].*");
    }

    public void changeAdminPassword(String currentPassword, String newPassword) {
        if (!verifyPassword(currentPassword, this.hashedPassword)) {
            throw new SecurityException("Current password is incorrect");
        }

        if (!isPasswordStrong(newPassword)) {
            throw new IllegalArgumentException("Password does not meet strength requirements");
        }

        this.hashedPassword = hashPassword(newPassword);
        // In real app: persist the new hashed password to secure storage
    }

    // --- Password Security Utilities ---
    private String hashPassword(String plainText) {
        // Placeholder for actual hashing implementation
        // In real app: use PBKDF2, BCrypt, or Argon2
        return "hashed_" + plainText; // Replace with real hashing
    }

    private boolean verifyPassword(String plainText, String hashed) {
        // Placeholder for actual verification
        // In real app: use secure password matching
        return Objects.equals(hashPassword(plainText), hashed);
    }
}