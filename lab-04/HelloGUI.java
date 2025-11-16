import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class HelloGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new BorderLayout());

        JLabel greetingLabel = new JLabel("", SwingConstants.CENTER);
        greetingLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton greetButton = new JButton("Greet");
        greetButton.setFont(new Font("Arial", Font.BOLD, 14));

        String[] greetings = {
            "Hello!",
            "Hi there!",
            "Greetings!",
            "Salutations!",
            "Hey!"
        };

        Random rand = new Random();

        greetButton.addActionListener(e -> {
            String randomGreeting = greetings[rand.nextInt(greetings.length)];
            greetingLabel.setText(randomGreeting);
        });

        frame.add(greetingLabel, BorderLayout.CENTER);
        frame.add(greetButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
