import javax.imageio.ImageIO;
import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.util.EventObject;


public class ComprehensionQuizGUI extends JFrame {
    private JTextArea passageArea;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton submitButton;
    private ButtonGroup optionGroup;
    private JLabel imageLabel;
    private int correctCount;
    private JLabel correctCountLabel;


    private Quiz quiz;


    public ComprehensionQuizGUI(Quiz quiz) {
        this.quiz = quiz;


        setTitle("Comprehension Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);


        // Passage area
        passageArea = new JTextArea(quiz.getPassage());
        passageArea.setEditable(false);
        passageArea.setFont(new Font("Arial", Font.PLAIN,15));
        JScrollPane passageScrollPane = new JScrollPane(passageArea);
        passageScrollPane.setPreferredSize(new Dimension(850, 600));
        add(passageScrollPane, BorderLayout.WEST);


        // Question panel
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BorderLayout());

        // Correct count label
        correctCount = 0;
        correctCountLabel = new JLabel("Correct: 0");
        add(correctCountLabel, BorderLayout.SOUTH);

        // Question label
        questionLabel = new JLabel(quiz.getCurrentQuestion());
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionPanel.add(questionLabel, BorderLayout.NORTH);
        
        JPanel imageOptionsPanel = new JPanel(new BorderLayout());
        // Image label

        imageLabel = new JLabel();
        imageOptionsPanel.add(imageLabel,BorderLayout.EAST);
        
        // Options
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 2));
        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();


        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton(quiz.getCurrentOptions()[i]);
            optionGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }


        // questionPanel.add(optionsPanel, BorderLayout.CENTER);
        // add(questionPanel, BorderLayout.CENTER);

        imageOptionsPanel.add(optionsPanel, BorderLayout.CENTER);
        questionPanel.add(imageOptionsPanel, BorderLayout.CENTER);
        add(questionPanel, BorderLayout.CENTER);
        // Submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        add(submitButton, BorderLayout.SOUTH);
        
        updateImage();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateImage() {
        String imagePath = quiz.getCurrentImagePath();

        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                Image image = ImageIO.read(new File(imagePath));
                image = image.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
                imageLabel.setIcon(new ImageIcon(image));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            imageLabel.setIcon(null);
        }
    }


    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedOption = "";


            for (JRadioButton option : options) {
                if (option.isSelected()) {
                    selectedOption = option.getText().substring(0, 1);
                    break;
                }
            }

             for (JRadioButton option : options) {
            option.setEnabled(false);
        }


            boolean isCorrect = quiz.checkAnswer(selectedOption);


            if (isCorrect) {
                JOptionPane.showMessageDialog(null, "Correct answer!");
                correctCount++;
                correctCountLabel.setText("Correct: " + correctCount);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect answer!");
            }


            boolean hasNextQuestion = quiz.nextQuestion();


            if (hasNextQuestion) {
                questionLabel.setText(quiz.getCurrentQuestion());


                for (int i = 0; i < options.length; i++) {
                    options[i].setText(quiz.getCurrentOptions()[i]);
                    options[i].setSelected(false);
                    options[i].setEnabled(true);
                    
                }
                updateImage();
            } else {
                JOptionPane.showMessageDialog(null, "Quiz completed!\nCorrect answers: " + correctCount);
                dispose();
            }
        }
    }
    
}


