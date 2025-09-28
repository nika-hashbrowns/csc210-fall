public class Creature {

        private final String name;

    public Creature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void speak() {
        System.out.println("I am " + name + "!");
    }

    public static void main(String[] args) {
        Creature c = new Creature("Unnamed Creature");
        c.speak();
    }
}
