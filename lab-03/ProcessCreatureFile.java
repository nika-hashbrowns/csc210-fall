import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessCreatureFile {

    private final List<Creature> creatures = new ArrayList<>();
    private final String filename;

    // No-arg constructor delegates to filename constructor
    public ProcessCreatureFile() {
        this("creatures.txt");
    }

    public ProcessCreatureFile(String filename) {
        this.filename = filename;
        loadFromFile();
    }

    // Return the live list (GUI code expects to iterate / read it)
    public List<Creature> getCreatures() {
        return creatures;
    }

    public int getCreatureCount() {
        return creatures.size();
    }

    public void printAllCreatureNames() {
        for (Creature c : creatures) {
            System.out.println(c.getName());
        }
    }

    // Add by object and persist
    public void addCreature(Creature c) {
        creatures.add(c);
        saveToFile();
    }

    // Convenience add by name
    public void addCreature(String name) {
        creatures.add(new Creature(name, 0.0, "unknown"));
        saveToFile();
    }

    // Replace creature at index with a new one (keeps weight/color if only changing name)
    public void modifyCreature(int index, String newName) {
        if (index < 0 || index >= creatures.size())
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        Creature old = creatures.get(index);
        creatures.set(index, new Creature(newName, old.getWeight(), old.getColor()));
        saveToFile();
    }

    // Replace whole Creature
    public void updateCreature(int index, Creature updated) {
        if (index < 0 || index >= creatures.size())
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        creatures.set(index, updated);
        saveToFile();
    }

    public Creature getCreature(int index) {
        if (index < 0 || index >= creatures.size())
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        return new Creature(creatures.get(index)); // return copy
    }

    public void deleteCreature(int index) {
        if (index < 0 || index >= creatures.size())
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        creatures.remove(index);
        saveToFile();
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Creature c : creatures) {
                // write as CSV: name,weight,color
                pw.println(c.getName() + "," + c.getWeight() + "," + c.getColor());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        creatures.clear();
        File f = new File(filename);
        if (!f.exists()) return; // nothing to load

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                // expect CSV: name,weight,color
                String[] parts = line.split(",", -1);
                String name = parts.length > 0 ? parts[0].trim() : "";
                double weight = 0.0;
                String color = parts.length > 2 ? parts[2].trim() : "unknown";
                if (parts.length > 1 && !parts[1].trim().isEmpty()) {
                    try {
                        weight = Double.parseDouble(parts[1].trim());
                    } catch (NumberFormatException ex) {
                        // keep default weight = 0.0
                    }
                }
                if (!name.isEmpty()) {
                    creatures.add(new Creature(name, weight, color));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}