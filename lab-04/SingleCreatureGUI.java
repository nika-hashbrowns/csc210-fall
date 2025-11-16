import java.awt.*;
import javax.swing.*;

public class SingleCreatureGUI {
    public static void main(String[] args) {
        // Hardcoded creature
        Creature c = new Creature("Draco", 450.0, "Emerald Green");

        // Create frame
        JFrame frame = new JFrame("Single Creature Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout(10, 10));

        // ===== LEFT SIDE - INPUT FIELDS =====
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Edit Creature"));

        JTextField nameField = new JTextField(c.getName());
        JTextField weightField = new JTextField(String.valueOf(c.getWeight()));
        JTextField colorField = new JTextField(c.getColor());

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Weight:"));
        inputPanel.add(weightField);
        inputPanel.add(new JLabel("Color:"));
        inputPanel.add(colorField);

        // ===== RIGHT SIDE - TEXT AREA =====
        JTextArea displayArea = new JTextArea(c.toString());
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Creature Details"));

        // ===== BOTTOM RIGHT - BUTTONS =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton saveButton = new JButton("Save");
        JButton growlButton = new JButton("Growl");

        JLabel actionLabel = new JLabel(" "); // Displays action feedback

        buttonPanel.add(actionLabel);
        buttonPanel.add(growlButton);
        buttonPanel.add(saveButton);

        // ===== ACTIONS =====
        saveButton.addActionListener(e -> {
            try {
                String newName = nameField.getText();
                double newWeight = Double.parseDouble(weightField.getText());
                String newColor = colorField.getText();

                c.setName(newName);
                c.setWeight(newWeight);
                c.setColor(newColor);

                displayArea.setText(c.toString());
                actionLabel.setText("Creature saved successfully!");
            } catch (NumberFormatException ex) {
                actionLabel.setText("Invalid weight value.");
            }
        });

        growlButton.addActionListener(e -> {
            // Example creature behavior
            System.out.println(c.getName() + " growls loudly!");
            actionLabel.setText(c.getName() + " growled!");
        });

        // ===== LAYOUT =====
        frame.add(inputPanel, BorderLayout.WEST);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // ===== SHOW =====
        frame.setVisible(true);
    }
}
