package Quiz;
import java.util.*;

class Question {
    String questionText;
    String[] options;
    char correctAnswer;

    Question(String questionText, String[] options, char correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {

            ArrayList<Question> questions = new ArrayList<>();

            // Adding 5 Questions
            questions.add(new Question(
                    "What is the capital of France?",
                    new String[]{"Berlin", "London", "Paris", "Rome"},
                    'C'
            ));

            questions.add(new Question(
                    "Which planet is known as the Red Planet?",
                    new String[]{"Earth", "Mars", "Jupiter", "Saturn"},
                    'B'
            ));

            questions.add(new Question(
                    "Who invented Java programming?",
                    new String[]{"James Gosling", "Dennis Ritchie", "Guido van Rossum", "Bjarne Stroustrup"},
                    'A'
            ));

            questions.add(new Question(
                    "Which data type stores true or false?",
                    new String[]{"int", "boolean", "char", "float"},
                    'B'
            ));

            questions.add(new Question(
                    "Which keyword is used to create a class in Java?",
                    new String[]{"class", "struct", "define", "object"},
                    'A'
            ));

            int score = 0;
            int qNo = 1;

            // Loop through questions
            for (Question q : questions) {
                System.out.println("\nQ" + qNo + ": " + q.questionText);
                System.out.println("A) " + q.options[0]);
                System.out.println("B) " + q.options[1]);
                System.out.println("C) " + q.options[2]);
                System.out.println("D) " + q.options[3]);

                System.out.print("Enter your answer (A/B/C/D): ");
                char userAnswer = sc.next().toUpperCase().charAt(0);

                if (userAnswer == q.correctAnswer) {
                    score++;
                }

                qNo++;
            }

            // Final Score
            System.out.println("\n--- Final Score: " + score + "/5 ---");

            // Play Again?
            System.out.print("Do you want to play again? (Y/N): ");
            char choice = sc.next().toUpperCase().charAt(0);

            if (choice == 'N') {
                System.out.println("Thank you for playing!");
                break;
            }
        }

        sc.close();
    }
}

