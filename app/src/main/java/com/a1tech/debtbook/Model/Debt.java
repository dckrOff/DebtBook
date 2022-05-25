package com.a1tech.debtbook.Model;

public class Debt {

    private String itemName;
    private int itemCount;
    private int itemAmount;
    private String debtDate;

    public Debt(String itemName, int itemCount, int itemAmount, String debtDate) {
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.itemAmount = itemAmount;
        this.debtDate = debtDate;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public String getDebtDate() {
        return debtDate;
    }

    public void setDebtDate(String debtDate) {
        this.debtDate = debtDate;
    }
}
