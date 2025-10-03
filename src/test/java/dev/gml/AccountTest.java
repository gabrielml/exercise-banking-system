/**
 * @file This is the 'test class' where I will verify my logic.
 * @author gml <kickAssDeveloper@hugsForMyBugs.mock>
 * @since 1.0
 */
package dev.gml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <b> Unit test for the Account class </b>
 * <p>
 * This class contains a suite of unit test to verify the correct behavior of
 * the {@link Account} class and its methods, including the
 * {@link Account#Account constructor}, the {@link Account#deposit deposit},
 * and the {@link Account#withdraw withdraw} functionalities.
 */
public class AccountTest {

    @Test
    @DisplayName("1. It should create an object with its attributes set correctly.")
    public void testAccountConstructor() {
        // --- Given (Arrange) ---
        // A class Account with its constructor,
        // and two specific variables to assign to my 'account' object.
        float initialBalance = 100.0f;
        float initialAnnualRate = 5.0f;

        // --- When (Act) ---
        // I instantiate an object of this class (which is a specific object created in
        // memory),
        Account account = new Account(initialBalance, initialAnnualRate);

        // --- Then (Assert) ---
        // I can verify that its attributes have been initialized correctly.
        assertEquals(initialBalance, account.balance);
        assertEquals(initialAnnualRate, account.annualRate);

        assertEquals(0, account.numberOfDeposits);
        assertEquals(0, account.numberOfWithdrawals);
        assertEquals(0, account.monthlyFee);
    }

    @Test
    @DisplayName("2.It should be verified that the balance is updated after a deposit & that the number of deposits is increased.")
    public void testDeposit() {
        // --- Given (Arrange) ---
        // A new account, a deposit, an expected balance and an expected deposit,
        Account account = new Account(100.0f, 5.0f);
        float depositAmount = 50.0f;
        float expectedBalance = 150.0f;
        int expectedDeposits = 1;

        // --- When (Act) ---
        // I make the account deposit,
        account.deposit(depositAmount);

        // --- Then (Assert) ---
        // The balance in the account must be as expected,
        // The number of deposits made into the account must be one.
        assertEquals(expectedBalance, account.balance);
        assertEquals(expectedDeposits, account.numberOfDeposits);
    }

    @Test
    @DisplayName("3.1. It should ensure that a withdrawal correctly updates the account's balance and increments the number of withdrawals counter.")
    public void testWithdrawalWithSufficientFunds() {
        // --- Given (Arrange || Set up) ---
        Account account = new Account(200.0f, 5.0f);
        float withdrawalAmount = 50.0f;
        float expectedBalance = 150.0f;
        int expectedWithdrawals = 1;

        // --- When (Act || Do something) ---
        account.withdraw(withdrawalAmount);

        // --- Then (Assert || Check) ---
        assertEquals(expectedBalance, account.balance,
                "Balance should be updated correctly after a successful withdrawal.");
        assertEquals(expectedWithdrawals, account.numberOfWithdrawals, "Number of withdrawals should be incremented");
    }

    @Test
    @DisplayName("3.2. It should not perform a withdrawal when funds are insufficient.")
    public void testWithdrawalWithInsufficientFunds() {
        // --- Give (Arrange || Set up) ---
        Account account = new Account(50.0f, 5.0f);
        float withdrawalAmount = 100.0f;
        float expectedBalance = 50.0f;
        int expectedWithdrawals = 0;

        // --- When (Act || Do something) ---
        account.withdraw(withdrawalAmount);

        // --- Then (Assert || Check) ---
        assertEquals(expectedBalance, account.balance, "Balance should not change with insufficient funds.");
        assertEquals(expectedWithdrawals, account.numberOfWithdrawals,
                "Number of withdrawals should not be incremented.");
    }

    // TODO: Continue with the next TDD cycle for the `calculateMonthlyInterest` method.
}
