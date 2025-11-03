public class CreatureCLI {
    public static void main(String[] args) {
        if (args.length < 2) {
            printHelp();
            System.exit(1);
        }

        String command = args[0].toLowerCase();
        String filename = "creature-data.csv";
        CreatureRegistry reg = new CreatureRegistry(filename);

        try {
            switch (command) {
                case "create": {
                    Creature newC = parseCreature(args[1]);
                    reg.addCreature(newC); // ensure registry supports this signature
                    System.out.println("Creature added: " + newC.getName());
                    break;
                }
                case "read": {
                    int readIndex = Integer.parseInt(args[1]);
                    Creature c = reg.getCreature(readIndex);
                    System.out.println("Read Creature: " + c);
                    break;
                }
                case "update": {
                    if (args.length < 3) 
                        throw new IllegalArgumentException("update needs index and data");
                    int updateIndex = Integer.parseInt(args[1]);
                    Creature updated = parseCreature(args[2]);
                    reg.updateCreature(updateIndex, updated);
                    System.out.println("Creature updated: " + updated.getName());
                    break;
                }
                case "delete": {
                    int delIndex = Integer.parseInt(args[1]);
                    reg.deleteCreature(delIndex);
                    System.out.println("Creature deleted at index " + delIndex);
                    break;
                }
                default:
                    printHelp();
                    System.exit(1);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static Creature parseCreature(String data) {
        String[] parts = data.trim().split("\\s+"); // split on any whitespace
        String name = "";
        String color = "";
        double weight = 0.0;

        for (String part : parts) {
            String[] kv = part.split(":", 2);
            if (kv.length == 2); {
                switch (kv[0].toLowerCase()) {
                    case "name":
                        name = kv[1];
                        break;
                    case "weight":
                        weight = Double.parseDouble(kv[1]);
                        break;
                    case "color":
                        color = kv[1];
                        break;
                }
            }
        }
        return new Creature(name, weight, color);
    }

    private static void printHelp() {
        System.out.println("Usage:");
        System.out.println("  java CreatureCLI create 'name:dragon weight:500 color:green'");
        System.out.println("  java CreatureCLI read <index>");
        System.out.println("  java CreatureCLI update <index> 'name:phoenix weight:12 color:orange'");
        System.out.println("  java CreatureCLI delete <index>");
    }
}
