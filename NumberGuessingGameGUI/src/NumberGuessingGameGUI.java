import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI {
    protected final JFrame frame = new JFrame("Number Guessing Game");
    protected final JTextField guessField;
    private final JLabel messageLabel;
    private final JLabel computerGuessLabel;
    private final JLabel userGuessLabel;
    private int randomNumber;
    private int attempts;
    private int score = 0;
    private int computerGuess;
    private int computerMin = 1;
    private int computerMax = 100;

    public NumberGuessingGameGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 1));

        messageLabel = new JLabel("Guess a number between 1 and 100", SwingConstants.CENTER);
        frame.add(messageLabel);

        guessField = new JTextField();
        frame.add(guessField);

        JButton guessButton = new JButton("Guess");
        frame.add(guessButton);

        userGuessLabel = new JLabel("Your guess: ", SwingConstants.CENTER);
        frame.add(userGuessLabel);

        computerGuessLabel = new JLabel("Computer's guess: ", SwingConstants.CENTER);
        frame.add(computerGuessLabel);

        guessButton.addActionListener(new GuessButtonListener());

        frame.setVisible(true);

        startNewGame();
    }

    private void startNewGame() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        attempts = 0;
        messageLabel.setText("Guess a number between 1 and 100");
        userGuessLabel.setText("Your guess: ");
        computerGuessLabel.setText("Computer's guess: ");
        guessField.setText("");
        computerMin = 1;
        computerMax = 100;
    }

    private void computerMakeGuess() {
        computerGuess = (computerMin + computerMax) / 2;
        computerGuessLabel.setText("Computer's guess: " + computerGuess);
        if (computerGuess < randomNumber) {
            computerMin = computerGuess + 1;
        } else if (computerGuess > randomNumber) {
            computerMax = computerGuess - 1;
        }
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String guessText = guessField.getText();
            if (!guessText.isEmpty()) {
                int userGuess = Integer.parseInt(guessText);
                attempts++;
                userGuessLabel.setText("Your guess: " + userGuess);

                int maxAttempts = 10;
                if (userGuess == randomNumber) {
                    int points = (maxAttempts - attempts + 1) * 10;
                    score += points;
                    messageLabel.setText("Correct! Score: " + score);
                    JOptionPane.showMessageDialog(frame, "Congratulations! You guessed the number in " + attempts + " attempts.\nYou earned " + points + " points. Total score: " + score, "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    startNewGame();
                } else if (userGuess < randomNumber) {
                    messageLabel.setText("The number is higher. Attempts left: " + (maxAttempts - attempts));
                } else {
                    messageLabel.setText("The number is lower. Attempts left: " + (maxAttempts - attempts));
                }

                computerMakeGuess();
                if (computerGuess == randomNumber) {
                    messageLabel.setText("Computer guessed the number! The number was: " + randomNumber);
                    JOptionPane.showMessageDialog(frame, "The computer guessed the number in " + attempts + " attempts.\nGame Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    startNewGame();
                } else if (attempts >= maxAttempts) {
                    JOptionPane.showMessageDialog(frame, "You've used all your attempts. The number was: " + randomNumber, "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    startNewGame();
                }

                guessField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGuessingGameGUI::new);
    }
}