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
 * Unit test for the Account class.
 * <p>
 * This class contains a suite of unit test to verify the correct behavior of the
 * {@link Account} class and its methods, including the constructor, and the deposit,
 * and the withdrawal functionalities.
 * </p>
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
        // I instantiate an object of this class (which is a specific object created in memory),
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

        // ---  Then (Assert) ---
        // The balance in the account must be as expected,
        // The number of deposits made into the account must be one.
        assertEquals(expectedBalance, account.balance);
        assertEquals(expectedDeposits, account.numberOfDeposits);
    }
}
