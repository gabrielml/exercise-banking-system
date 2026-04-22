/**
 * @file This is the 'test class' where I will verify my logic.
 * @author gml <kickAssDeveloper@hugsForMyBugs.mock>
 * @since 1.0
 */
package dev.gml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link SavingsAccount} class.
 * <p>
 *  Tests are grouped by method and follow the Red -> Green -> Refactor
 *  TDD cycle. Each test uses the Given/When/Then structure for clarity
 *  and maintainability.
 * </p>
 * 
 * <p>
 *  Boundary value for the active/inactive rule: a balance of 
 *  {@code 10000.00f} is considered active; anything below is
 *  inactive.
 * </p>
 */
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

    // =========================================
    // 2. deposit() Tests
    // =========================================

    @Test
    @DisplayName("2.1. It should allow a deposit and update the balance when the account is active.")
    public void testDepositOnActiveAccount() {
        // --- Given (Arrange) ---
        // An active account (balance >= 10000) and a deposit amount.
        // After the deposit the balance and counter must both update.
        float initialBalance = 10000.0f;
        float annualRate = 6.0f;
        float depositAmount = 500.0f;
        float expectedBalance = 10500.0f;
        int expectedDeposits = 1;
        
        // Instantiate the account.
        SavingsAccount account = new SavingsAccount(initialBalance, annualRate);

        // --- When (Act) ---
        // The bank deposit is made.
        account.deposit(depositAmount);

        // --- Then (Assert) ---
        // Balance should increase after deposit on an active account,
        // And deposit counter should be incremented after a successful deposit.
        assertEquals(expectedBalance, account.balance,
            "Balance should increase after deposit on an active account.");
        
        assertEquals(expectedDeposits, account.numberOfDeposits,
            "Deposit counter should be incremented after a successful deposit.");
    }

    @Test
    @DisplayName("2.2. It should block a deposit and leave balance unchanged when the account is inactive.")
    public void testDepositOnInactiveAccount(){
        // --- Given (Arrange) ---
        // An inactive account (balance < 10000) -- the deposit must be
        // silently ignored and neither the balance nor the counter may change.
        float initialBalance = 9999.99f;
        float annualRate = 6.0f;
        float depositAmount = 500.0f;
        float expectedBalance = 9999.99f;
        int expectedDeposits = 0;

        // Instantiate the account:
        SavingsAccount account = new SavingsAccount(initialBalance, annualRate);

        // --- When (Act) ---
        // The bank deposit is made in an inactive account
        account.deposit(depositAmount);

        // --- Then (Assert) ---
        // Balance should remain unchanged.
        // Deposit counter should NOT be incremented on an inactive account.
        assertEquals(expectedBalance, account.balance, 0.001f,
            "Balance should remain unchanged when depositing into an inactive account.");
        
        assertEquals(expectedDeposits, account.numberOfDeposits, 
            "Deposit counter should NOT be incremented on an inactive account.");
    }
}
