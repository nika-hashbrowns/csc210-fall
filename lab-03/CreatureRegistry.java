import java.io.*;
import java.util.ArrayList;

public class CreatureRegistry {
    private ArrayList<Creature> creatures = new ArrayList<>();
    private String filePath;

    // Constructor
    public CreatureRegistry(String filePath) {
        this.filePath = filePath;
        loadFromFile();
    }

    /** Load creatures from CSV file into memory. */
    private void loadFromFile() {
        creatures.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Creature c = Creature.fromCSV(line.trim());
                if (c != null) creatures.add(c);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /** Write all creatures back to the CSV file. */
    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (Creature c : creatures) {
                pw.println(c.toCSV());
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    /** Add a new creature and save to file. */
    public void addCreature(Creature c) {
        creatures.add(c);
        saveToFile();
    }

    /** Return a copy of a creature by index. */
    public Creature getCreature(int index) {
        if (index < 0 || index >= creatures.size())
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        return new Creature(creatures.get(index));
    }

    /** Update an existing creature. */
    public void updateCreature(int index, Creature updated) {
        if (index < 0 || index >= creatures.size())
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        creatures.set(index, updated);
        saveToFile();
    }

    /** Delete a creature by index. */
    public void deleteCreature(int index) {
        if (index < 0 || index >= creatures.size())
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        creatures.remove(index);
        saveToFile();
    }

    /** Return number of creatures. */
    public int getCount() {
        return creatures.size();
    }

    /** Simple test main. */
    public static void main(String[] args) {
        CreatureRegistry reg = new CreatureRegistry("creature-data.csv");
        System.out.println("Initial creatures: " + reg.getCount());
        reg.addCreature(new Creature("Hydra", 400.0, "Blue"));
        System.out.println("After add: " + reg.getCount());
    }
}
