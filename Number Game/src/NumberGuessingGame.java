import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuessingGame extends JFrame {

    private final JLabel label = new JLabel("Guess a number between 1 and 100");
    private final JTextField textField = new JTextField(10);
    private final JButton button = new JButton("Guess");
    private final JLabel resultLabel = new JLabel(" ");
    private int numberToGuess;
    private int attemptsLeft = 5;


    public NumberGuessingGame() {
        super("Number Guessing Game");
        setLayout(new FlowLayout());
        setSize(1000, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(label);
        add(textField);
        add(button);
        add(resultLabel);

        numberToGuess = (int) (Math.random() * 100) + 1;

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int guess;
                try {
                    guess = Integer.parseInt(textField.getText());
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter number between 1 and 100");
                    return;
                }
                attemptsLeft--;
                if (guess == numberToGuess) {
                    resultLabel.setText("Congratulations! You guessed the number in " + (10 - attemptsLeft) + " attempts.");
                    button.setEnabled(false);
                } else if (guess < numberToGuess) {
                    resultLabel.setText("Too low. Attempts left: " + attemptsLeft);
                } else {
                    resultLabel.setText("Too high. Attempts left: " + attemptsLeft);
                }

                if (attemptsLeft == 0) {
                    resultLabel.setText("Game over! The number was " + numberToGuess + ".");
                    button.setEnabled(false);
                }
            }
        });
    }
}
