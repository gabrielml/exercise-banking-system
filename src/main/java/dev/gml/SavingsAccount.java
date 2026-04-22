/**
 * @file This is the class where I will be written my logic in.
 * @author gml <kickAssDeveloper@hugsForMyBugs.mock>
 * @since 1.0
 */
package dev.gml;

/**
 * Represents a savings account in the banking system.
 * <p>
 *  This class extends {@link Account} with an active/inactive state that
 *  controls whether deposits and withdrawals are permitted. The account
 *  is considered active when its balance is at or above {@code 10000.00},
 *  and inactive when it dalls below that threshold.
 * </p>
 * 
 * <p>The active/inactive state is evaluated automatically in two places:</p>
 * <ul>
 *  <li>At construction time, based on the opening balance.</li>
 *  <li>
 *      At the end of {@code monthlyStatement()}, based on the balance
 *      after fees and interest have been applied.
 *  </li>
 * </ul>
 */
public class SavingsAccount extends Account {

    /**
     * Indicates whether this savings account is currently active.
     * <p>
     *  An account is active when {@code balance >= 10000.00}, and inactive
     *  when {@code balance < 10000.00}. Deposits and withdrawals are only
     *  permitted while the account is active.
     * </p>
     */
    protected boolean isActive;

    /**
     * Constructs a new Savingsaccount with the given opening balance and annual interest rate.
     * <p>
     *  Delegates attribute initialisation to {@link Account#Account(float, float)}
     *  and then evaluates the active/inactive state based on the opening balance.
     * </p>
     * 
     * @param balance       The opening balance of the account.
     * @param annualRate    The annual interest rate as a percentage.
     */
    public SavingsAccount(float balance, float annualRate) {
        super(balance, annualRate);
        this.isActive = balance >= 10000.00f;
    }

    public void deposit(float amount) {
        if(this.isActive) {
            super.deposit(amount);
        }
    }
    
}
