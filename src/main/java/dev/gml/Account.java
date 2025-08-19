/**
 * @file This is the class where I will be written my logic in.
 * @author gml <kickAssDeveloper@hugsForMyBugs.mock>
 * @since 1.0
 */
package dev.gml;

/**
 * The Account class represents a basic bank account.
 * <p>
 * This class modes a generic bank account with core functionalities such as
 * deposits, withdrawals, amd monthly statement calculations. It serves as a
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

}
