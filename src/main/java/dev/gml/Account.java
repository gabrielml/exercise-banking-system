/**
 * @file This is the class where I will be written my logic in.
 * @author gml <kickAssDeveloper@hugsForMyBugs.mock>
 */
package dev.gml;

public class Account {
    protected float balance;
    protected int numberOfDeposits;
    protected int numberOfWithdrawals;
    protected float annualRate;
    protected float monthlyFee;

    public Account(float balance, float annualRate){
        this.balance = balance;
        this.annualRate = annualRate;
        this.numberOfDeposits = 0;
        this.numberOfWithdrawals = 0;
        this.monthlyFee = 0;
    }

}
