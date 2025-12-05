import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class MultipleCreatureGUI {

    public static void main(String[] args) {

        // ===== SAMPLE DATA =====
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(new Creature("Draco", 450.0, "Green"));
        creatures.add(new Creature("Fang", 300.0, "Gray"));
        creatures.add(new Creature("Luna", 150.5, "Silver"));
        creatures.add(new Creature("Blaze", 220.0, "Red"));
        creatures.add(new Creature("Aqua", 180.0, "Blue"));

        // ===== MAIN FRAME =====
        JFrame frame = new JFrame("Multiple Creatures Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout(10, 10));

        // ===== LEFT SIDE: Creature list =====
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Creature c : creatures) listModel.addElement(c.getName());

        JList<String> creatureList = new JList<>(listModel);
        creatureList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        creatureList.setBorder(BorderFactory.createTitledBorder("Creature List"));

        JScrollPane listScrollPane = new JScrollPane(creatureList);
        listScrollPane.setPreferredSize(new Dimension(150, 400));

        // ===== CENTER: Edit Panel =====
        JPanel editPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        editPanel.setBorder(BorderFactory.createTitledBorder("Edit Creature"));

        JTextField nameField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField colorField = new JTextField();

        editPanel.add(new JLabel("Name:"));
        editPanel.add(nameField);
        editPanel.add(new JLabel("Weight:"));
        editPanel.add(weightField);
        editPanel.add(new JLabel("Color:"));
        editPanel.add(colorField);

        // ===== RIGHT SIDE: Display Area =====
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane textScrollPane = new JScrollPane(displayArea);

        // ===== SPLIT PANE STRUCTURE =====
        // Center split: editPanel (left) + textScrollPane (right)
        JSplitPane centerSplitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                editPanel,
                textScrollPane
        );
        centerSplitPane.setDividerLocation(0.5);
        centerSplitPane.setResizeWeight(0.5);

        // Main split: listScrollPane (left) + centerSplitPane (right)
        JSplitPane mainSplitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                listScrollPane,
                centerSplitPane
        );
        mainSplitPane.setDividerLocation(150);
        mainSplitPane.setResizeWeight(0.0);
        mainSplitPane.setContinuousLayout(true);

        // Add to frame
        frame.add(mainSplitPane, BorderLayout.CENTER);

        // ===== BUTTON BAR =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save Changes");
        JButton growlButton = new JButton("Growl");
        JLabel statusLabel = new JLabel(" ");

        buttonPanel.add(statusLabel);
        buttonPanel.add(growlButton);
        buttonPanel.add(saveButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

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

        // ===== SAVE BUTTON =====
        saveButton.addActionListener(e -> {
            int index = creatureList.getSelectedIndex();
            if (index >= 0) {
                try {
                    Creature selected = creatures.get(index);
                    selected.setName(nameField.getText());
                    selected.setWeight(Double.parseDouble(weightField.getText()));
                    selected.setColor(colorField.getText());

                    displayArea.setText(selected.toString());
                    listModel.set(index, selected.getName());
                    statusLabel.setText("Saved " + selected.getName());

                } catch (NumberFormatException ex) {
                    statusLabel.setText("Invalid weight value.");
                }
            } else {
                statusLabel.setText("Select a creature first.");
            }
        });

        // ===== GROWL BUTTON =====
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

        frame.setVisible(true);
    }
}
class Creature {
    private String name;
    private double weight;
    private String color;

    public Creature(String name, double weight, String color) {
        this.name = name;
        this.weight = weight;
        this.color = color;
    }

    public String getName() { return name; }
    public double getWeight() { return weight; }
    public String getColor() { return color; }

    public void setName(String name) { this.name = name; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setColor(String color) { this.color = color; }

    @Override
    public String toString() {
        return "Creature {\n" +
               "  Name: " + name + "\n" +
               "  Weight: " + weight + "\n" +
               "  Color: " + color + "\n" +
               "}";
    }
}
