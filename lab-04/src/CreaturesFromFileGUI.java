import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreaturesFromFileGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CreaturesFromFileGUI().createAndShowGUI());
    }

    private ProcessCreatureFile fileProcessor;
    private ArrayList<Creature> creatures;
    private DefaultListModel<String> listModel;
    private JList<String> creatureList;
    private JTextField nameField, weightField, colorField;
    private JTextArea displayArea;

    private void createAndShowGUI() {

        // ===== Load creatures from file =====
        fileProcessor = new ProcessCreatureFile("creatures.txt");
        creatures = fileProcessor.loadCreatures();

        JFrame frame = new JFrame("Creatures From File Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout(10, 10));

        // ===== LEFT: List of creatures =====
        listModel = new DefaultListModel<>();
        for (Creature c : creatures) listModel.addElement(c.getName());

        creatureList = new JList<>(listModel);
        creatureList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane listScroll = new JScrollPane(creatureList);
        listScroll.setPreferredSize(new Dimension(200, 400));
        listScroll.setBorder(BorderFactory.createTitledBorder("Creature List"));

        // ===== MIDDLE: Edit panel =====
        JPanel editPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        editPanel.setBorder(BorderFactory.createTitledBorder("Edit Creature"));

        nameField = new JTextField();
        weightField = new JTextField();
        colorField = new JTextField();

        editPanel.add(new JLabel("Name:"));
        editPanel.add(nameField);
        editPanel.add(new JLabel("Weight:"));
        editPanel.add(weightField);
        editPanel.add(new JLabel("Color:"));
        editPanel.add(colorField);

        // ===== RIGHT: Display Area =====
        displayArea = new JTextArea(12, 40);
        displayArea.setEditable(false);
        JScrollPane displayScroll = new JScrollPane(displayArea);
        displayScroll.setBorder(BorderFactory.createTitledBorder("Creature Details"));

        // ===== Buttons =====
        JButton saveButton = new JButton("Save Changes");
        JButton addButton = new JButton("Add Creature");
        JButton removeButton = new JButton("Remove Creature");
        JButton growlButton = new JButton("Growl");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(growlButton);

        // ===== Right combined panel =====
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(editPanel, BorderLayout.NORTH);
        rightPanel.add(displayScroll, BorderLayout.CENTER);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        // ===== Split Pane =====
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScroll, rightPanel);
        splitPane.setDividerLocation(200);

        frame.add(splitPane, BorderLayout.CENTER);

        // =====================================================
        // EVENT HANDLERS
        // =====================================================

        // List selection
        creatureList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int index = creatureList.getSelectedIndex();
                if (index >= 0) {
                    Creature c = creatures.get(index);
                    nameField.setText(c.getName());
                    weightField.setText(String.valueOf(c.getWeight()));
                    colorField.setText(c.getColor());
                    displayArea.setText(c.toString());
                }
            }
        });

        // SAVE CHANGES
        saveButton.addActionListener(e -> {
            int index = creatureList.getSelectedIndex();
            if (index >= 0) {
                try {
                    String n = nameField.getText();
                    double w = Double.parseDouble(weightField.getText());
                    String col = colorField.getText();

                    Creature updated = new Creature(n, w, col);

                    creatures.set(index, updated);
                    listModel.set(index, n);

                    fileProcessor.saveCreatures(creatures);

                    displayArea.setText(updated.toString());

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input.");
                }
            }
        });

        // ADD NEW CREATURE
        addButton.addActionListener(e -> {
            try {
                String n = nameField.getText();
                double w = Double.parseDouble(weightField.getText());
                String c = colorField.getText();

                Creature newCreature = new Creature(n, w, c);

                creatures.add(newCreature);
                listModel.addElement(n);

                fileProcessor.saveCreatures(creatures);

                displayArea.setText("Added: " + newCreature);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input.");
            }
        });

        // REMOVE SELECTED CREATURE
        removeButton.addActionListener(e -> {
            int index = creatureList.getSelectedIndex();
            if (index >= 0) {
                creatures.remove(index);
                listModel.remove(index);

                fileProcessor.saveCreatures(creatures);

                displayArea.setText("Creature removed.");

                nameField.setText("");
                weightField.setText("");
                colorField.setText("");
            }
        });

        // GROWL BUTTON
        growlButton.addActionListener(e -> {
            int index = creatureList.getSelectedIndex();
            if (index >= 0) {
                Creature c = creatures.get(index);
                displayArea.append("\n" + c.getName() + " growls!");
            }
        });

        frame.setVisible(true);
    }
}
