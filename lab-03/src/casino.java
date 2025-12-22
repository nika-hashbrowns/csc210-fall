public class casino {
    
}

class Customer {
    private String wallet;

    public Customer(String wallet) {
        this.wallet = wallet;
    }
    public String getWallet() {
        return wallet;
    }
    public void setWallet(String wallet) {
        this.wallet = wallet;
    }
    
    public double walletAmount() {
        return 0.0;
    }
    public double receive(){
        return 0.0;
    }
    public double checkWallet(){
        return 0.0; 
    }
}   

class SlotMachine {
String[] symbols = {"smiley face", "heart", "seven 7"};
double moneyPot = 1000000.00; 
double pulllever(){
    return 0.0;
    }
public String toString(){
    return "smiley face" + "heart" + "seven 7";
}

public double getMoneyPot() {
        return moneyPot;
    }

}

class GoodCasino{
    SlotMachine slotMachine = new SlotMachine();
    Customer customer = new Customer("100.00");
    double checkWallet(){
        return 0.0; 
    }
    public static double play(Customer customer, SlotMachine machine, double amount) {
        return 0.0;
    }

}
