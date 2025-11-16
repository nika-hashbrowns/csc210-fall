import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MultipleCreatureGUI {
    public static void main(String[] args) {
        // Sample creature list
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(new Creature("Draco", 450.0, "Green"));
        creatures.add(new Creature("Fang", 300.0, "Gray"));
        creatures.add(new Creature("Luna", 150.5, "Silver"));
        creatures.add(new Creature("Blaze", 220.0, "Red"));
        creatures.add(new Creature("Aqua", 180.0, "Blue"));

        // Main frame setup
        JFrame frame = new JFrame("Multiple Creatures Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout(10, 10));

        // ===== LEFT SIDE: JList of creatures =====
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Creature c : creatures) {
            listModel.addElement(c.getName());
        }

        JList<String> creatureList = new JList<>(listModel);
        creatureList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        creatureList.setBorder(BorderFactory.createTitledBorder("Creature List"));
        JScrollPane listScrollPane = new JScrollPane(creatureList);

        // ===== CENTER: Input fields =====
        JPanel editPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        editPanel.setBorder(BorderFactory.createTitledBorder("Edit Creature"));
        listScrollPane.setPreferredSize(new Dimension(150, 400)); 

        JTextField nameField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField colorField = new JTextField();

        editPanel.add(new JLabel("Name:"));
        editPanel.add(nameField);
        editPanel.add(new JLabel("Weight:"));
        editPanel.add(weightField);
        editPanel.add(new JLabel("Color:"));
        editPanel.add(colorField);

        editPanel.setPreferredSize(new Dimension(900, 400));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, editPanel);
        splitPane.setDividerLocation(150); // move divider for better balance
        splitPane.setResizeWeight(0.0);

        frame.add(splitPane, BorderLayout.CENTER);

        splitPane.setContinuousLayout(true);

        // 1. Split the Edit Panel and the Details Panel (HORIZONTALLY)
        JSplitPane centerSplitPane;
        centerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, editPanel, textScrollPane);
        centerSplitPane.setDividerLocation(0.65); // Give 65% of the center space to the Edit Panel
        centerSplitPane.setResizeWeight(0.65);

        // 2. Split the Creature List (WEST) and the combined center area (HORIZONTALLY)
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, centerSplitPane);
        mainSplitPane.setDividerLocation(150); // Fixed width for the list on the left
        mainSplitPane.setResizeWeight(0.0);    // Ensures the Edit/Detail panels expand when resized
        mainSplitPane.setContinuousLayout(true);

        // ===== RIGHT SIDE: Text area display =====
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane textScrollPane = new JScrollPane(displayArea);
        textScrollPane.setBorder(BorderFactory.createTitledBorder("Creature Details"));

        // ===== BOTTOM: Buttons =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save Changes");
        JButton growlButton = new JButton("Growl");
        JLabel statusLabel = new JLabel(" ");

        buttonPanel.add(statusLabel);
        buttonPanel.add(growlButton);
        buttonPanel.add(saveButton);

        // ===== LIST SELECTION HANDLER =====
        creatureList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int index = creatureList.getSelectedIndex();
                if (index >= 0) {
                    Creature selected = creatures.get(index);
                    nameField.setText(selected.getName());
                    weightField.setText(String.valueOf(selected.getWeight()));
                    colorField.setText(selected.getColor());
                    displayArea.setText(selected.toString());
                    statusLabel.setText("Editing " + selected.getName());
                }
            }
        });

        // ===== SAVE BUTTON HANDLER =====
        saveButton.addActionListener(e -> {
            int index = creatureList.getSelectedIndex();
            if (index >= 0) {
                try {
                    Creature selected = creatures.get(index);
                    selected.setName(nameField.getText());
                    selected.setWeight(Double.parseDouble(weightField.getText()));
                    selected.setColor(colorField.getText());
                    displayArea.setText(selected.toString());

                    // Update name in list if changed
                    listModel.set(index, selected.getName());
                    statusLabel.setText("Saved " + selected.getName());
                } catch (NumberFormatException ex) {
                    statusLabel.setText("Invalid weight value.");
                }
            } else {
                statusLabel.setText("Select a creature first.");
            }
        });

        // ===== GROWL BUTTON HANDLER =====
        growlButton.addActionListener(e -> {
            int index = creatureList.getSelectedIndex();
            if (index >= 0) {
                Creature selected = creatures.get(index);
                System.out.println(selected.getName() + " growls fiercely!");
                statusLabel.setText(selected.getName() + " growled!");
            } else {
                statusLabel.setText("Select a creature first.");
            }
        });

        // ===== ADD COMPONENTS TO FRAME =====
        frame.add(editPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
