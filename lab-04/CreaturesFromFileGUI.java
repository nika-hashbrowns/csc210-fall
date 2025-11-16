import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
        // Load creatures from file
        fileProcessor = new ProcessCreatureFile("creatures.txt");
        creatures = fileProcessor.getCreatures();

        JFrame frame = new JFrame("Creatures From File Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout(10, 10));

        // ===== LEFT SIDE: Creature List =====
        listModel = new DefaultListModel<>();
        for (Creature c : creatures) listModel.addElement(c.getName());

        creatureList = new JList<>(listModel);
        creatureList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(creatureList);
        listScrollPane.setPreferredSize(new Dimension(200, 400));
        listScrollPane.setBorder(BorderFactory.createTitledBorder("Creature List"));

        // ===== RIGHT SIDE: Edit & Info =====
        JPanel editPanel = new JPanel(new GridLayout(4, 2, 10, 10));
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

        JButton saveButton = new JButton("Save Changes");
        JButton addButton = new JButton("Add Creature");
        JButton removeButton = new JButton("Remove Creature");
        JButton growlButton = new JButton("Growl");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(growlButton);

        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane displayScroll = new JScrollPane(displayArea);
        displayScroll.setBorder(BorderFactory.createTitledBorder("Creature Details"));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(editPanel, BorderLayout.NORTH);
        rightPanel.add(displayScroll, BorderLayout.CENTER);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        // ===== Combine Panels =====
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, rightPanel);
        splitPane.setDividerLocation(200);
        splitPane.setResizeWeight(0.2);
        frame.add(splitPane, BorderLayout.CENTER);

        // ===== Behavior =====

        // Show creature details when selected
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

        // Save button updates creature
        saveButton.addActionListener(e -> {
            int index = creatureList.getSelectedIndex();
            if (index >= 0) {
                try {
                    String newName = nameField.getText();
                    double newWeight = Double.parseDouble(weightField.getText());
                    String newColor = colorField.getText();

                    fileProcessor.updateCreature(index, new Creature(newName, newWeight, newColor));
                    creatures = fileProcessor.getCreatures();

                    listModel.set(index, newName);
                    displayArea.setText(creatures.get(index).toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input.");
                }
            }
        });

        // Add button creates new creature
        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                double weight = Double.parseDouble(weightField.getText());
                String color = colorField.getText();

                Creature newC = new Creature(name, weight, color);
                fileProcessor.addCreature(newC);
                creatures = fileProcessor.getCreatures();

                listModel.addElement(newC.getName());
                displayArea.setText("Added: " + newC);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input.");
            }
        });

        // Remove button deletes selected creature
        removeButton.addActionListener(e -> {
            int index = creatureList.getSelectedIndex();
            if (index >= 0) {
                fileProcessor.deleteCreature(index);
                creatures = fileProcessor.getCreatures();

                listModel.remove(index);
                displayArea.setText("Creature removed.");
                nameField.setText("");
                weightField.setText("");
                colorField.setText("");
            }
        });

        // Growl button
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

