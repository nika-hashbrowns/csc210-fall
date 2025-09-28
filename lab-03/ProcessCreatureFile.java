import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessCreatureFile {

    private final List<Creature> creatures = new ArrayList<>();
    private final String filename;

    public ProcessCreatureFile() {
        this("creatures.txt");
        creatures.add(new Creature("Mark"));
        creatures.add(new Creature("Alice"));
        creatures.add(new Creature("Foxy"));
        creatures.add(new Creature("Monty"));
        creatures.add(new Creature("Rex"));
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
        creatures.set(index, new Creature(newName));
    }

    public void deleteCreature(int index) {
        creatures.remove(index);
    }

    public void addCreature(String name) {
        creatures.add(new Creature(name));
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

    public static void main(String[] args) {
        ProcessCreatureFile pcf = new ProcessCreatureFile();
        System.out.println("Creature count: " + pcf.getCreatureCount());
        pcf.printAllCreatureNames();
    }
}

class Creature {
    private String name;

    public Creature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}