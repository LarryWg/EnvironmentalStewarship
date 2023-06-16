import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComprehensionQuizGUI extends JFrame {
    private JTextArea passageArea;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton submitButton;
    private ButtonGroup optionGroup;

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
        JScrollPane passageScrollPane = new JScrollPane(passageArea);
        passageScrollPane.setPreferredSize(new Dimension(500, 400));
        add(passageScrollPane, BorderLayout.WEST);

        // Question panel
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BorderLayout());

        // Question label
        questionLabel = new JLabel(quiz.getCurrentQuestion());
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionPanel.add(questionLabel, BorderLayout.NORTH);

        // Options
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();

        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton(quiz.getCurrentOptions()[i]);
            optionGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }

        questionPanel.add(optionsPanel, BorderLayout.CENTER);
        add(questionPanel, BorderLayout.CENTER);

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        add(submitButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
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

            boolean isCorrect = quiz.checkAnswer(selectedOption);

            if (isCorrect) {
                JOptionPane.showMessageDialog(null, "Correct answer!");
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect answer!");
            }

            boolean hasNextQuestion = quiz.nextQuestion();

            if (hasNextQuestion) {
                questionLabel.setText(quiz.getCurrentQuestion());

                for (int i = 0; i < options.length; i++) {
                    options[i].setText(quiz.getCurrentOptions()[i]);
                    options[i].setSelected(false);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Quiz completed!");
                dispose();
            }
        }
    }

    public static void main(String[] args) {
        String passage = "          Environmental Stewardship – Impact of Technology\n\n" +
                "The appearance of technology has been one of the most revolutionary innovations in human history. " +
                "It has brought us many new benefits and opportunities and helped in the creation and advancements in various fields. \n\n" +
                "\nWhile technology has rapidly become an essential part of our modern life, its widespread has negatively affected human health and the environment. " +
                "To point out these impacts, the term environmental stewardship was introduced in the 1880s. " +
                "It highlights the responsible and sustainable use of natural resources to minimize environmental and human health harm. " +
                "Environmental stewardship’s foundation revolves around the three R's: Reduce, Reuse, and Recycle. \n\n" +
                "These practices help us with the conservation of energy and the use of renewable resources.\n\n" +
                "To better understand environmental stewardship, we must recognize the harm technology has brought to the environment. " +
                "The continuous industrialization for technological advancements made in countries worldwide is all associated with the following negative impacts:\n\n" +
                "Pollution: Pollution is caused by technology manufacturing processes releasing various pollutants into the atmosphere. " +
                "These pollutants include carbon dioxide, carbon monoxide, methane, nitric oxide, and other harmful gases. " +
                "Air pollution brings many consequences to humans, animals, and global warming. " +
                "It increases the amount of greenhouse gases trapped in the atmosphere and causes global temperature to rise. " +
                "It can also cause problems in the humans respiratory system and cardiovascular system.\n\n" +
                "Depletion of natural resources: To advance technology, we need the necessary resources to do so. " +
                "As a result, these resources are consumed faster than they can replenish, causing a depletion. " +
                "And to acquire these minerals, fossil fuels, and rare elements, we mine.  Mining leads to deforestation, habitat destruction, soil erosion, and water pollution. " +
                "This resulted in the loss of trees that remove carbon dioxide from our atmosphere and the biodiversity of plants and animals that depend on them.\n\n" +
                "Electronic waste: Unused electronics, such as cell phones and computers, contains hazardous materials like lead and mercury. " +
                "These electronic devices can cause water, air, and soil pollution if they are not properly disposed of and recycled, leading to risks of human health and environmental issues.\n\n" +
                "Technologies were not developed to harm humans or the environment. " +
                "Aside from its negative effects, they can be beneficial in many more ways. " +
                "Some of the key positive impacts include:\n\n" +
                "Healthcare: Technologies have allowed us to discover and develop better disease treatments, diagnoses, and medical equipment. " +
                "These advancements have saved countless lives and improved human health.\n\n" +
                "Jobs: New industries, such as software development, IT analysis, and information technology, have emerged from technological advancements, " +
                "which brought many new opportunities for employment.\n\n" +
                "Renewable energy: Modern technology has enabled us to acquire renewable energy, such as wind, rain, and solar energy, " +
                "allowing us to convert it into electricity through solar panels, wind and water turbines.\n\n" +
                "It is evident that we do not want technology to harm humans and the environment, so here are some ways in which we can reduce the number of negative impacts:\n\n" +
                "Electronic waste:\n\n" +
                "Properly dispose and recycle electronic devices\n" +
                "Create a longer lifespan for your device by taking care of it, for example, protecting them from physical damage\n" +
                "Be mindful of technology usage and try to avoid unnecessary purchases or upgrades on your current device\n\n" +
                "Pollution:\n\n" +
                "Unplug or turn off devices when you are not using them to minimize unnecessary power consumption.\n" +
                "Share car rides to reduce the number of vehicles on the road; this can lower emissions, but if possible, consider taking public transportation.\n" +
                "Look for products that are made from recycled materials, and have minimal packaging.\n\n" +
                "Health:\n\n" +
                "Develop good habits, for example, taking regular breaks from your devices, doing physical exercise, interacting in-person rather than online\n" +
                "Make sure your desk or workstation is ergonomically designed to maintain good posture. " +
                "Invest in an adjustable chair and monitor stand to reduce eye strain\n" +
                "Use technology as a tool to manage stress and mental health.\n\n" +
                "To mention these impacts of technology on humans and the environment, many local communities, government programs and initiatives, " +
                "and private organizations were established to prompt environmental stewardship. Some programs and initiatives in local communities are:\n\n" +
                "Tree planting programs: This program aims to engage community members in planting trees across their city. " +
                "This reduces the number of greenhouse gasses in the atmosphere, and restores degraded forests.\n\n" +
                "Adopt-a-Park: This initiative is located in the city of Hamilton and it aims to create and preserve green spaces and safe parks for everyone. " +
                "They help to clean litter, plant and weed gardens, and report vandalism.\n\n" +
                "Ocean Ambassadors Canada: A program in West Vancouver to reconnect with nature, care for the ocean and learn ways they can help to restore and protect it.\n\n" +
                "Governments around the world have established programs and initiatives to promote environmental stewardship:\n\n" +
                "Funding programs: The Canadian government has created many funding programs for different environmental and climate change issues. " +
                "For example, Low Carbon Economy Fund supports projects that will reduce emissions, create jobs, and save money.\n\n" +
                "Bill S-5: Strengthening Environmental Protection for a Healthier Canada Act. " +
                "This act protects vulnerable populations, assesses real-life exposures, supports the shift to safer chemicals, and much more.\n\n" +
                "BioPreferred: A program managed by the United States Department of Agriculture. " +
                "Their goal is to increase the purchase and use of biobased products, which are products derived from renewable raw materials.\n\n" +
                "Some examples of private organizations that promote environmental stewardship include:\n\n" +
                "Greenpeace: It is an international organization that actively dedicates to preserving endangered species and preventing environmental abuses.\n\n" +
                "Conservation International: An organization that aims to secure nature's critical benefits to humanity. " +
                "They partner with communities, governments, and businesses to protect biodiversity and help fight climate change.\n\n" +
                "In conclusion, even though technology has brought significant positive changes to the world, it also has a downside. " +
                "We are responsible for working together to preserve our nature and promote environmental stewardship " +
                "so that more people can be mindful of their actions.";

        Quiz quiz = new Quiz(passage,
                new String[]{"Question 1: What is the main concept emphasized in object-oriented programming?",
                        "Question 2: Which of the following is not a key principle in object-oriented programming?",
                        "Question 3: What are instances of classes called in object-oriented programming?"},
                new String[][]{{"A. Inheritance", "B. Encapsulation", "C. Polymorphism", "D. Objects"},
                        {"A. Inheritance", "B. Encapsulation", "C. Polymorphism", "D. Iteration"},
                        {"A. Methods", "B. Parameters", "C. Objects", "D. Variables"}},
                new String[]{"D", "D", "C"});

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ComprehensionQuizGUI(quiz);
            }
        });
    }
}