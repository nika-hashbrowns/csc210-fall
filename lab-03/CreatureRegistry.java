import java.io.*;
import java.util.ArrayList;

public class CreatureRegistry {
    private ArrayList<Creature> creatures = new ArrayList<>();
    private String filename;

    public CreatureRegistry(String filename) {
        this.filename = filename;
        loadFromFile();
    }

    public void addCreature(String name) {
        creatures.add(new Creature(name));
    }

    public void modifyCreature(int index, String newName) {
        if (index >= 0 && index < creatures.size()) {
            creatures.get(index).setName(newName);
        }
    }

    public void deleteCreature(int index) {
        if (index >= 0 && index < creatures.size()) {
            creatures.remove(index);
        }
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
        File file = new File(filename);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                creatures.add(new Creature(line.trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Optional: print all creatures
    public void printAll() {
        for (Creature c : creatures) {
            System.out.println(c.getName());
        }
    }
}
