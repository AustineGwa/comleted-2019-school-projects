package com.example.creditscore.models;

public class UserCreditData {

    private double salary;
    private double currentDebt;
    private  int loansPaid;
    private int lastLoanPaid;

    public UserCreditData() {
    }

    public UserCreditData(double salary, double currentDebt, int loansPaid, int lastLoanPaid) {
        this.salary = salary;
        this.currentDebt = currentDebt;
        this.loansPaid = loansPaid;
        this.lastLoanPaid = lastLoanPaid;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getCurrentDebt() {
        return currentDebt;
    }

    public void setCurrentDebt(double currentDebt) {
        this.currentDebt = currentDebt;
    }

    public int getLoansPaid() {
        return loansPaid;
    }

    public void setLoansPaid(int loansPaid) {
        this.loansPaid = loansPaid;
    }

    public int getLastLoanPaid() {
        return lastLoanPaid;
    }

    public void setLastLoanPaid(int lastLoanPaid) {
        this.lastLoanPaid = lastLoanPaid;
    }
}
