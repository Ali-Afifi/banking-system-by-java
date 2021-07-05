package uni.project;

import java.time.LocalDate;

public class User {
    protected String name;
    protected int id;
    protected float balance;
    protected boolean indebted = false;
    protected float lastPurchase;
    protected LocalDate dateOfLastPurchase;
    protected LocalDate paymentDueDate;

    User(String name, float balance) {
        this.name = name;
        this.balance = balance;
        
        CSV file = new CSV("accounts.csv");
        
        try {
            String[][] data = file.dataForTable();
            int prevId = Integer.parseInt(data[data.length - 1][0]);
            this.id = prevId + 1;
        } catch (IndexOutOfBoundsException e) {
            this.id = 101;
        }

    }

    User(String name, float balance, int id) {
        this.name = name;
        this.balance = balance;
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getBalance() {
        return balance;
    }

    public void setIndebted(boolean indebted) {
        this.indebted = indebted;

    }

    public boolean isIndebted() {
        return indebted;
    }

    public void setLastPurchase(float lastPurchase) {
        this.lastPurchase = lastPurchase;
        this.dateOfLastPurchase = LocalDate.now();
        this.paymentDueDate = this.dateOfLastPurchase.plusMonths(2);

        if (lastPurchase > this.balance) {
            this.setIndebted(true);
        }
    }

    public void setLastPurchase(float lastPurchase, LocalDate dateOfLastPurchase) {
        this.lastPurchase = lastPurchase;
        
        setDateOfLastPurchase(dateOfLastPurchase);

        if (lastPurchase > this.balance) {
            this.setIndebted(true);
        }
    }

    public void freeLastPurchase() {
        this.lastPurchase = 0;
        this.indebted = false;
        this.freeDateOfLastPurchase();
    }

    public float getLastPurchase() {
        return lastPurchase;
    }
    
    public void setDateOfLastPurchase(LocalDate dateOfLastPurchase) {
        this.dateOfLastPurchase = dateOfLastPurchase;
        this.paymentDueDate = this.dateOfLastPurchase.plusMonths(2);
    }

    public LocalDate getDateOfLastPurchase() {
        return dateOfLastPurchase;
    }

    private void freeDateOfLastPurchase() {
        this.dateOfLastPurchase = null;
        this.paymentDueDate = null;
    }

    public LocalDate getPaymentDueDate() {
        return paymentDueDate;
    }

    public String readyForCSV() {
        String status = "";
        String date1 = "";
        String date2 = "";

        if (isIndebted()) {
            status = "indebted";
        } else {
            status = "clear";
        }

        if (this.dateOfLastPurchase == null) {
            date1 = " ";
        } else {
            date1 = this.dateOfLastPurchase.toString();
        }

        if (this.paymentDueDate == null) {
            date2 = " ";
        } else {
            date2 = this.paymentDueDate.toString();
        }
        
        String row = this.id + "," + this.name + ",Personal," + this.balance + "," + status + "," + this.lastPurchase + "," + date1 + "," + date2;
        return row;
    }
}
