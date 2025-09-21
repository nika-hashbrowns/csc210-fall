public class Creature {
    private String name;
    private final String color;
    private double weight;

    public Creature(String n, String c, double wt) {
        System.out.println("Constructor called");
        this.name = n;
        this.color = c;
        this.weight = wt;
    }

    public void setweight (double wt){
        System.out.println("setweight called");
        this.weight = wt;
    }

    public void setName (String n){
        System.out.println("setName called");
        this.name = n;
    }

    public String toString(){
        return "Name: " + name + ", Color: " + color + ", Weight: " + weight;
    }
    
    public static void main(String[] args){
        System.out.println("Program started");
        Creature c = new Creature("Adam", "Black", 500);
        double w = 100;
        System.out.println("Creature c: " + c);
        c.setweight(w);
        System.out.println("Creature c: " + c);
        String newName = "Mark";
        System.out.println("Creature c: " + c);
        c.setName(newName);
    }
}
