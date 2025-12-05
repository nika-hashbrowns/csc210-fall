public class Creature {
    private String name;
    private double weight;
    private String color;

    // Main constructor
    public Creature(String name, double weight, String color) {
        this.name = name;
        this.weight = weight;
        this.color = color;
    }

    // Copy constructor
    public Creature(Creature other) {
        this.name = other.name;
        this.weight = other.weight;
        this.color = other.color;
    }

    // Optional name-only constructor
    public Creature(String name) {
        this(name, 0.0, "unknown");
    }

    // Getters
    public String getName() { return name; }
    public double getWeight() { return weight; }
    public String getColor() { return color; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setColor(String color) { this.color = color; }

    // Convert to CSV
    public String toCSV() {
        return name + "," + weight + "," + color;
    }

    // Parse from CSV
    public static Creature fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length != 3) return null;

        String name = parts[0].trim();
        double weight = Double.parseDouble(parts[1].trim());
        String color = parts[2].trim();

        return new Creature(name, weight, color);
    }

    @Override
    public String toString() {
        return "Creature{name='" + name + "', weight=" + weight + ", color='" + color + "'}";
    }
}
