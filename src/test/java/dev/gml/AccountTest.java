/**
 * @file This is the 'test class' where I will verify my logic.
 * @author gml <kickAssDeveloper@hugsForMyBugs.mock>
 */
package dev.gml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(initialAnnualRate, account.annualrate);

        assertEquals(0, account.numberOfDeposits);
        assertEquals(0, account.numberOfWithdrawals);
        assertEquals(0, account.monthlyFee);
    }
}
