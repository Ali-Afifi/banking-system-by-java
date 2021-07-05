package uni.project;

import java.time.LocalDate;

public class VIP extends User{
    VIP(String name, float balance) {
        super(name, balance);
    }

    VIP(String name, float balance, int id) {
        super(name, balance, id);
    }

    
    @Override
    public void setLastPurchase(float lastPurchase) {
        this.lastPurchase = lastPurchase;
        this.dateOfLastPurchase = LocalDate.now();
        this.paymentDueDate = this.dateOfLastPurchase.plusMonths(4);
        
        if (lastPurchase > this.balance) {
            this.setIndebted(true);
        }
    }
    
    @Override
    public void setDateOfLastPurchase(LocalDate dateOfLastPurchase) {
        this.dateOfLastPurchase = dateOfLastPurchase;
        this.paymentDueDate = this.dateOfLastPurchase.plusMonths(4);
    }
    
    @Override
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
        
        String row = this.id + "," + this.name + ",VIP," + this.balance + "," + status + "," + this.lastPurchase + "," + date1 + "," + date2;
        return row;
    }
}
