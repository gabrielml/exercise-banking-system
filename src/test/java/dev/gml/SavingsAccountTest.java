package dev.gml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SavingsAccountTest {

    // =========================================
    // 1. Constructor Tests
    // =========================================

    @Test
    @DisplayName("1.1 It should set `isActive` to `true` when opening balance is exactly $10000.00")
    public void testConstructorActivatesAccountAtThreshold() {
        // --- Given (Arrange) ---
        // The spec. defines the boundary: "balance >= 10000 -> active".
        // We test the exact threshold to confirm the boundary is inclusive.
        float initialBalance = 10000.00f;
        float annualRate = 6.0f;

        // --- When (Act) ---
        SavingsAccount account = new SavingsAccount(initialBalance, annualRate);

        // --- Then (Assert) ---
        assertTrue(account.isActive,
            "Account should be active when opening balance is exactly $10000.00");

    }

    @Test
    @DisplayName("1.2 It should set `isActive` to `false` when opening balance is below $10000.00")
    public void testConstructorDeactivatesAccountBelowThreshold() {
        // --- Given (Arrange) ---
        // A balance just below the threshold -- the account must start inactive.
        float initialBalance = 9999.99f;
        float annualRate = 6.0f;

        // --- When (Act) ---
        // Instantiate with a balance of $9999.99, which is just below the activation threshold.
        SavingsAccount account = new SavingsAccount(initialBalance, annualRate);

        // --- Then (Assert) ---
        // It should set `is Active` to `false` when opening balance is below $10000.00
        assertFalse(account.isActive,
            "Account should be inactive when opening balance is below $10000.00");
    }
}
