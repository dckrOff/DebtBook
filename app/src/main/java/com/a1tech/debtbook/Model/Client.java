package com.a1tech.debtbook.Model;

public class Client {

    private String clientName;
    private int debtAmount;
    private String debtDate;

    public Client(String clientName, int debtAmount, String debtDate) {
        this.clientName = clientName;
        this.debtAmount = debtAmount;
        this.debtDate = debtDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(int debtAmount) {
        this.debtAmount = debtAmount;
    }

    public String getDebtDate() {
        return debtDate;
    }

    public void setDebtDate(String debtDate) {
        this.debtDate = debtDate;
    }
}
