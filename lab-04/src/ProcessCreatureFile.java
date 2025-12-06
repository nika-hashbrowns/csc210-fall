import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ProcessCreatureFile {

    private final String filePath;

    public ProcessCreatureFile(String filePath) {
        this.filePath = filePath;
    }

    // Load creatures from file
    public ArrayList<Creature> loadCreatures() {
        ArrayList<Creature> creatures = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Creature c = Creature.fromCSV(line.trim());
                if (c != null) creatures.add(c);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return creatures;
    }

    // Save creatures to file
    public void saveCreatures(ArrayList<Creature> creatures) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (Creature c : creatures) {
                pw.println(c.toCSV());
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}
