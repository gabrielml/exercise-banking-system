package dev.gml;

public class SavingsAccount extends Account {

    protected boolean isActive;

    public SavingsAccount(float balance, float annualRate) {
        super(balance, annualRate);
        this.isActive = balance >= 10000.00f;
    }
    
}
