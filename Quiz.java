public class Quiz {
    private String passage;
    private String[] questions;
    private String[][] options;
    private String[] correctAnswers;
    private String[] imagePaths;


    private int currentQuestionIndex;


    public Quiz(String passage, String[] questions, String[][] options, String[] correctAnswers,String[] imagePaths) {
        this.passage = passage;
        this.questions = questions;
        this.options = options;
        this.correctAnswers = correctAnswers;
        this.imagePaths = imagePaths;
        this.currentQuestionIndex = 0;
    }


    public String getPassage() {
        return passage;
    }


    public String getCurrentQuestion() {
        return questions[currentQuestionIndex];
    }


    public String[] getCurrentOptions() {
        return options[currentQuestionIndex];
    }

    public String getCurrentImagePath() {
        return imagePaths[currentQuestionIndex];
    }
    
    public boolean checkAnswer(String selectedOption) {
        return selectedOption.equals(correctAnswers[currentQuestionIndex]);
    }


    public boolean nextQuestion() {
        currentQuestionIndex++;
        return currentQuestionIndex < questions.length;
    }
}








