import java.util.Scanner;

public class QuizGame {
    public static void main(String[] args) {
        // Initialize the quiz
        String[] questions = {
                "What is the capital of France?",
                "What is the largest planet in our solar system?",
                "What is the currency used in Japan?",
                "Which of these is a type of programming language?",
                "Who is the current president of the United States?"
        };

        String[] options = {
                "A. Paris\nB. London\nC. Berlin\nD. Madrid",
                "A. Earth\nB. Jupiter\nC. Saturn\nD. Venus",
                "A. Dollar\nB. Euro\nC. Yen\nD. Pound",
                "A. Banana\nB. Python\nC. Hamburger\nD. Pencil",
                "A. Donald Trump\nB. Joe Biden\nC. Barack Obama\nD. George W. Bush"
        };

        char[] answers = {'A', 'B', 'C', 'B', 'B'};

        // Initialize the scanner and score
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        // Run the quiz
        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.println(options[i]);

            System.out.print("Enter your answer (A, B, C, or D): ");
            char userAnswer = scanner.nextLine().toUpperCase().charAt(0);

            if (userAnswer == answers[i]) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect.");
            }

            System.out.println();
        }

        // Calculate and display the final score
        double finalScore = (double) score / questions.length * 100;
        System.out.printf("Your final score is: %.2f%%\n", finalScore);
    }
}