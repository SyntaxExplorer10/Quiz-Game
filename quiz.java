package Quiz;

import java.sql.*;
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

public class quiz {

    static final String URL = "jdbc:mysql://localhost:3306/quiz_db";
    static final String USER = "root";
    static final String PASS = "";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("MySQL Driver not found!");
        }

        while (true) {
            System.out.println("\n===== QUIZ GAME MENU =====");
            System.out.println("1. Play Quiz");
            System.out.println("2. Add New Question");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> playQuiz();
                case 2 -> addQuestion();
                case 3 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // -------------------- PLAY QUIZ ---------------------
    static void playQuiz() {
        ArrayList<Question> questions = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM questions");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String text = rs.getString("question_text");
                String[] opts = {
                    rs.getString("option_a"),
                    rs.getString("option_b"),
                    rs.getString("option_c"),
                    rs.getString("option_d")
                };
                char ans = rs.getString("correct_answer").charAt(0);

                questions.add(new Question(text, opts, ans));
            }

        } catch (SQLException e) {
            printSQLError(e);
            return;
        }

        if (questions.isEmpty()) {
            System.out.println("No questions available!");
            return;
        }

        int score = 0;
        int qNo = 1;

        for (Question q : questions) {
            System.out.println("\nQ" + qNo + ": " + q.questionText);
            System.out.println("A) " + q.options[0]);
            System.out.println("B) " + q.options[1]);
            System.out.println("C) " + q.options[2]);
            System.out.println("D) " + q.options[3]);

            System.out.print("Enter answer (A/B/C/D): ");
            char userAns = sc.next().toUpperCase().charAt(0);

            if (userAns == q.correctAnswer) {
                score++;
            }

            qNo++;
        }

        System.out.println("\n--- Final Score: " + score + "/" + questions.size() + " ---");
    }

    // -------------------- ADD QUESTION ---------------------
    static void addQuestion() {
        sc.nextLine();

        System.out.print("Enter Question Text: ");
        String qText = sc.nextLine();

        System.out.print("Option A: ");
        String a = sc.nextLine();

        System.out.print("Option B: ");
        String b = sc.nextLine();

        System.out.print("Option C: ");
        String c = sc.nextLine();

        System.out.print("Option D: ");
        String d = sc.nextLine();

        System.out.print("Correct Answer (A/B/C/D): ");
        char correct = sc.next().toUpperCase().charAt(0);

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO questions(question_text, option_a, option_b, option_c, option_d, correct_answer) VALUES (?,?,?,?,?,?)");

            ps.setString(1, qText);
            ps.setString(2, a);
            ps.setString(3, b);
            ps.setString(4, c);
            ps.setString(5, d);
            ps.setString(6, String.valueOf(correct));

            ps.executeUpdate();

            System.out.println("Question Added Successfully!");

        } catch (SQLException e) {
            printSQLError(e);
        }
    }

    // ---------------- SQL ERROR LOG ----------------
    static void printSQLError(SQLException e) {
        System.out.println("\n--- SQL ERROR ---");
        System.out.println("Message: " + e.getMessage());
        System.out.println("State: " + e.getSQLState());
        System.out.println("Code: " + e.getErrorCode());
        e.printStackTrace();
        System.out.println("------------------\n");
    }
}
