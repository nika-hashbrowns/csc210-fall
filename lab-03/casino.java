package lab-03;

public class casino {
    
}

class Casino {
    private String name;
    private int totalGamesPlayed;
    private double totalEarnings;

    public Casino(String name) {
        this.name = name;
        this.totalGamesPlayed = 0;
        this.totalEarnings = 0.0;
    }

    public String getName() {
        return name;
    }

    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    public double getTotalEarnings() {
        return totalEarnings;
    }

    public void recordGame(double earnings) {
        totalGamesPlayed++;
        totalEarnings += earnings;
    }
}

class SlotMachine {
    private String name;
    private double payoutPercentage;

    public SlotMachine(String name, double payoutPercentage) {
        this.name = name;
        this.payoutPercentage = payoutPercentage;
    }

    public String getName() {
        return name;
    }

    public double getPayoutPercentage() {
        return payoutPercentage;
    }

    public double play(double betAmount) {
        // Simulate a slot machine play
        double winnings = betAmount * (payoutPercentage / 100);
        return winnings - betAmount; // Return net earnings (winnings - bet)
    }
}

class GoodCasino extends Casino {
    public GoodCasino(String name) {
        super(name);
    }

    @Override
    public void recordGame(double earnings) {
        super.recordGame(earnings * 1.1); // Good casino gives a 10% bonus on earnings
    }
}