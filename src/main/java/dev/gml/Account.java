/**
 * @file This is the class where I will be written my logic in.
 * @author gml <kickAssDeveloper@hugsForMyBugs.mock>
 * @since 1.0
 */
package dev.gml;

/**
 * The Account class represents a basic bank account.
 * <p>
 * This class models a generic bank account with core functionalities such as
 * deposits, withdrawals, and monthly statement calculations. It serves as a
 * base class for more specialized accounts like Savings and Checking accounts.
 * All attributes are protected to allow for direct access by child classes
 * while maintaining encapsulation from external classes.
 * </p>
 */
public class Account {
    protected float balance;
    protected int numberOfDeposits;
    protected int numberOfWithdrawals;
    protected float annualRate;
    protected float monthlyFee;

    /**
     * Constructs a new Account instance with a specified initial balance and annual rate.
     * <p>
     * Initializes the account with the provided balance and annual  rate.
     * All transaction counters (deposits, withdrawals) and fees are set to their default of zero.
     * </p>
     *
     * @param balance    The initial balance of the account.
     * @param annualRate The annual interest rate as a percentage.
     */
    public Account(float balance, float annualRate) {
        this.balance = balance;
        this.annualRate = annualRate;
        this.numberOfDeposits = 0;
        this.numberOfWithdrawals = 0;
        this.monthlyFee = 0;
    }

    /**
     * Deposits a specified amount of money into the account.
     * <p>
     * This method increases the account's balance by the deposit amount
     * and increments the number of deposits transaction counter.
     * </p>
     *
     * @param amount The amount of money to be deposited. Must be a positive value.
     */
    public void deposit(float amount) {
        // TODO: (optimize) amount must be a positive value!
        this.balance += amount;
        this.numberOfDeposits++;
    }

    /**
     * Withdraws a specified amount of money from the account.
     * <p>
     * This method decreases the account's balance by the withdrawal amount
     * and increments the number of withdrawals counter, provided the withdrawal
     * amount does not exceed the current balance.
     * </p>
     *
     * @param amount The amount of money to be withdrawn.
     */
    public void withdraw(float amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            this.numberOfWithdrawals++;
        }
    }

    /**
     * Calculates the monthly interest and adds it to the account balance.
     * <p>
     *     The monthly interest is derived from the annual rate by dividing it by 12
     *     to get the monthly rate, then dividing by 100 to convert the percentage
     *     into a decimal factor. The resulting interest amount is added directly
     *     to the balance.
     * </p>
     *
     * <p>Formula: {@code balance += balance * ((annualRate / 12) / 100)}</p>
     *
     * <p>Edge cases:</p>
     * <ul>
     *     <li>If the balance is zero, no interest is generated.</li>
     *     <li>If the annual rate is zero, the balance remains unchanged.</li>
     * </ul>
     */
    public void calculateMonthlyInterest() {
        float monthlyInterestRate = (this.annualRate / 12) / 100;
        float monthlyInterest = this.balance * monthlyInterestRate;
        this.balance += monthlyInterest;
    }
    
    /**
     * Generates the monthly statement for the account.
     * <p>
     *    This method applies two operations in strict order:
     * </p>
     * <ol>
     *   <li>Deducts the {@code monthlyFee} from the current balance.</li>
     *   <li>Calls {@link #calculateMonthlyInterest()} to apply interest
     *       on the already-reduced balance.</li>
     * </ol>
     * 
     * <p>
     *      The order matters: interest is always calculated <em>after</em> the
     *      fee is deducted, so the fee reduces the principal on which interest
     *      accrues that month.
     * </p>
     * 
     * <p>Edge cases:</p>
     * <ul>
     *      <li>If {@code monthlyFee} is zero, this method behaves identically
     *          to calling {@link #calculateMonthlyInterest()} directly.</li>
     *      <li>If {@code monthlyFee} equals the full balance, the balance
     *          reaches zero and no interest is generated.</li>
     * </ul>
     * 
     * <p>
     *      <strong>Subclass contract:</strong> subclasses that override this
     *      method should apply their own commission rules to {@code monthlyFee}
     *      <em>before</em> invoking {@code super.monthlyStatement()}, so that
     *      the adjusted fee flows correctly into both steps above.
     * </p>
     */
    public void monthlyStatement() {
        this.balance -= this.monthlyFee;
        calculateMonthlyInterest();
    }
}

