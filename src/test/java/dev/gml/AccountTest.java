/**
 * @file This is the 'test class' where I will verify my logic.
 * @author gml <kickAssDeveloper@hugsForMyBugs.mock>
 * @since 1.0
 */
package dev.gml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    @DisplayName("3.1. It should validate a successful withdrawal where the balance is sufficient.")
    public void testWithdrawalWithSufficientFunds() {
        // --- Given (aka Arrange) ---
        // a parameterized account object, a withdrawal amount,
        // an expected balance, and the total number of withdrawals.
        Account account = new Account(200.0f, 5.0f);
        float withdrawalAmount = 50.0f;
        float expectedBalance = 150.0f;
        int expectedWithdrawals = 1;

        // --- When (aka Act) ---
        // the withdrawal is executed.
        account.withdraw(withdrawalAmount);

        // --- Then (aka Assert) ---
        // it correctly updates the account's balance,
        // and increments the withdrawal count number.
        assertEquals(expectedBalance, account.balance, "Balance should be updated correctly after a successful withdrawal.");
        assertEquals(expectedWithdrawals, account.numberOfWithdrawals, "Number of withdrawals should be incremented.");
    }

    @Test
    @DisplayName("3.2. It should NOT validate a withdrawal where the balance is insufficient.")
    public void testWithdrawalWithInsufficientFunds() {
        // --- Given (aka Prepare) ---
        // a parameterized account object, a withdrawal amount,
        // an expected balance, and the total number of withdrawals.
        Account account = new Account(50.0f, 5.0f);
        float withdrawalAmount = 100.0f;
        float expectedBalance = 50.0f;
        int expectedWithdrawals = 0;

        // --- When (aka Act) ---
        // the withdrawal is executed.
        account.withdraw(withdrawalAmount);

        // --- Then (aka Assert) ---
        // as the withdrawal amount exceeds the current balance,
        // it verifies that the balance and the number of withdrawals remain unchanged.
        assertEquals(expectedBalance, account.balance, "Balance should not change with insufficient funds.");
        assertEquals(expectedWithdrawals, account.numberOfWithdrawals, "Number of withdrawals should NOT be incremented.");
    }

    @Test
    @DisplayName("4. It should correctly calculate the monthly interest and add it to the balance")
    public void testCalculateMonthlyInterest() {
        // --- Given ( aka Arrange || Set up || Prepare ) ---
        float initialBalance = 1000.0f;
        float annualRate = 6.0f;
        float expectedFinalBalance = 1005.0f;

        Account account = new Account(initialBalance, annualRate);

        // --- When (aka Act || Execute ) ---
        account.calculateMonthlyInterest();

        // --- Then (aka Assert || Verify ) ---
        // In this test we use a delta (0.001f) for float comparison to account for potential precision errors.
        assertEquals(expectedFinalBalance,account.balance, 0.001f, "Balance should be updated with the calculated monthly interest.");
    }

    @Test
    @DisplayName("4.1. It should NOT change the balance when the balance is zero.")
    public void testCalculateMonthlyInterestWithZeroBalance() {
        // --- Given (Arrange) ---
        // An account with a zero balance - multiplying by zero should
        // produce zero interest, leaving the balance unchanged.
        float initialBalance = 0.0f;
        float annualRate = 6.0f;
        float expectedFinalBalance = 0.0f;

        Account account = new Account(initialBalance, annualRate);

        // --- When (Act) ---
        account.calculateMonthlyInterest();

        // --- Then (Assert) ---
        assertEquals(expectedFinalBalance, account.balance, 0.001f,
            "Balance should remain zero when there are no funds to accrue interest on.");
    }

    @Test
    @DisplayName("4.2. It should NOT change the balance when the annual rate is zero.")
    public void testCalculateMonthlyInterestWithZeroAnnualRate() {
        // --- Given (Arrange) ---
        // An account with a 0% annual rate - no interest should
        // be generated regardless of the balance.
        float initialBalance = 1000.0f;
        float annualRate = 0.0f;
        float expectedFinalBalance = 1000.0f;

        Account account = new Account(initialBalance, annualRate);

        // --- When (Act) ---
        account.calculateMonthlyInterest();

        // --- Then (Assert) ---
        assertEquals(expectedFinalBalance, account.balance, 0.001f,
            "Balance should remain unchanged when the annual rate is zero.");
    }

    @Test
    @DisplayName("5.1. It should only apply monthly interest when the monthly fee is zero.")
    public void testMonthlyStatementWithZeroFee() {
        // --- Given (Arrange) ---
        // An account with no monthly fee set -- The statement should
        // behave identically to calling "calculateMonthlyInterest()" directly.
        // balance = 1000.0f, annualRate = 6.0f, monthlyFee = 0.0f (default)
        // Step 1: 1000.0 - 0.0 = 1000.0
        // Step 2: 1000.0 * (6.0 / 12 / 100) = 1000.0 * 0.005 = 5.0
        // Final: 1000.0 + 5.0 = 1005.0
        float initialBalance = 1000.0f;
        float annualRate = 6.0f;
        float expectedFinalBalance = 1005.0f;

        Account account = new Account(initialBalance, annualRate);

        // --- When (Act) ---
        account.monthlyStatement();

        // --- Then (Assert) ---
        assertEquals(expectedFinalBalance, account.balance, 0.001f,
            "Balance should only reflect interest when monthly fee is zero."
        );
    }

    @Test
    @DisplayName("5.2. It should deduct the monthly fee first, then apply interest on the reduced balance.")
    public void testMonthlyStatementWithNonZeroFee() {
        // --- Given (Arrange) ---
        // An account with a monthly fee -- the fee must be subtracted before
        // interest is calculated, so interest accrues on the reduced balance.
        // balance = 1000.0f, annualRate = 6.0f. monthlyFee = 50.0f
        // Step 1: 1000.0 - 50.0 = 950.0
        // Step 2:  950.0 * (6.0 / 12 / 100) = 950.0 * 0.005 = 4.75
        // Final: 950.0 + 4.75 = 954.75
        float initialBalance = 1000.0f;
        float annualRate = 6.0f;
        float monthlyFee = 50.0f;
        float expectedFinalBalance = 954.75f;

        Account account = new Account(initialBalance, annualRate);
        account.monthlyFee = monthlyFee;

        // --- When (Act) ---
        account.monthlyStatement();

        // --- Then (Assert) ---
        assertEquals(expectedFinalBalance, account.balance, 0.001f,
            "Fee should be deducted first, then interest applied on the reduced balance."
        );
    }

    @Test
    @DisplayName("5.3. It should produce a zero balance when the monthly fee equals the full balance.")
    public void testMonthlyStatementWhenFeeEqualsBalance() {
        // --- Given (Arrange) ---
        // An account where the monthly fee consumes the entire balance.
        // After deduction the balance is zero, so no interest is generated.
        // balance = 1000.0f, annualRate = 6.0f, monthlyFee = 1000.0f
        // Step 1: 1000.0 - 1000.0 = 0.0
        // Step 2: 0.0 * 0.005 = 0.0
        // Final: 0.0
        float initialBalance = 1000.0f;
        float annualRate = 6.0f;
        float monthlyFee = 1000.0f;
        float expectedFinalBalance = 0.0f;

        Account account = new Account(initialBalance, annualRate);
        account.monthlyFee = monthlyFee;

        // --- When (Act) ---
        account.monthlyStatement();

        // --- Then (Assert) ---
        assertEquals(expectedFinalBalance, account.balance, 0.001f,
            "Balance should be zero when the fee consumes the entire balance."
        );
    }

    @Test
    @DisplayName("6.1. It should return a string containing all attributes at their initial values.")
    public void testPrintDefaultState(){
        // --- Given (Arrange) ---
        // A freshly constructed account -- all five attributes should appear
        // in the returned string with their constructor/default values.
        Account account = new Account(1000.0f, 6.0f);

        // --- When (Act) ---
        String result = account.print();

        // --- Then (Assert) ---
        // Each attribute value must be present somewhere in the output string.
        // We use contains() so the test is not tied to a specific format.
        assertTrue(result.contains("1000"), "Output should contain the balance.");
        assertTrue(result.contains("6"), "Output should contain the annual rate.");
        assertTrue(result.contains("0"), "Output should contain the deposit counter.");
    }

    @Test
    @DisplayName("6.2. It should reflect updated counters after a deposit and a withdrawal.")
    public void testPrintAfterTransactions() {
        // --- Given (Arrange) ---
        // An account that has had one deposit and one withdrawal.
        // The counters must be visible in the printed output.
        Account account = new Account(1000.0f, 6.0f);
        account.deposit(200.0f);
        account.withdraw(100.0f);

        // --- When (Act) ---
        String result = account.print();

        // --- Then (Assert) ---
        // The updated balance (1100.0), deposit counter (1),
        // and withdrawal counter (1) must all appear in the output.
        assertTrue(result.contains("1100"), "Output should contain the updated balance.");
        assertTrue(result.contains("1"), "Output should contain the deposit/withdrawal counters.");
    }

    @Test
    @DisplayName("6.3. It should reflect the monthly fee when it has been set to a non-zero value.")
    public void testPrintWithNonZeroFee() {
        // --- Given (Arrange) ---
        // An account where monthlyFee has been explicitly set,
        // the fee value must appear in the printed output.
        Account account = new Account(1000.0f, 6.0f);
        account.monthlyFee = 50.0f;

        // --- When (Act) ---
        String result = account.print();

        // --- Then (Assert) ---
        assertTrue(result.contains("50"), "Output should contain the monthly fee.");
    }
}
