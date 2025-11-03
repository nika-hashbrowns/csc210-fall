import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessCreatureFile {

    private final List<Creature> creatures = new ArrayList<>();
    private final String filename;

    public ProcessCreatureFile() {
        this("creatures.txt");
        creatures.add(new Creature("Mark", 120.0, "Blue"));
        creatures.add(new Creature("Alice", 100.0, "Pink"));
        creatures.add(new Creature("Foxy", 80.0, "Red"));
        creatures.add(new Creature("Monty", 200.0, "Green"));
        creatures.add(new Creature("Rex", 500.0, "Brown"));

    }

    public ProcessCreatureFile(String filename) {
        this.filename = filename;
        loadFromFile();
    }

    public int getCreatureCount() {
        return creatures.size();
    }

    public void printAllCreatureNames() {
        for (Creature c : creatures) {
            System.out.println(c.getName());
        }
    }

    public void modifyCreature(int index, String newName) {
        creatures.set(index, new Creature(newName, index, newName));
    }

    public void deleteCreature(int index) {
        creatures.remove(index);
    }

    public void addCreature(String name) {
        creatures.add(new Creature(name, 0.0, "unknown")); // match (String,double,String)
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Creature c : creatures) {
                pw.println(c.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                Creature c = Creature.fromCSV(line.trim()); // use the parsing helper
                if (c != null) creatures.add(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}