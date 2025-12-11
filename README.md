Project Description: MCQ Quiz Game in Java

The MCQ Quiz Game is a console-based Java application that allows users to attempt a 5-question multiple-choice quiz. Each question contains four options (A, B, C, and D), and the user must select the correct option. The program evaluates the responses, calculates the final score, and provides an option to replay the quiz.

🎯 Objective

The main objective of this project is to practice:

Creating and using classes and objects

Storing data using ArrayList

Handling user input

Implementing basic game logic

Using loops and conditions

Building an interactive console application

🧱 Features
1. Question Class

A custom Question class is created which contains:

questionText → The question asked

options[] → Four answer choices

correctAnswer → The correct option (A/B/C/D)

This class helps in organizing and storing each question clearly.

2. Storing Questions

All questions are stored inside an ArrayList<Question>, making it easy to loop through them one by one.

3. Displaying Questions

The program displays each question in a clear format:

Q1: What is the capital of France?
A) Berlin   B) London   C) Paris   D) Rome
Enter your answer:


The user inputs A, B, C, or D.

4. Checking Answers & Scoring

Each time the user selects the correct answer, the score increases by 1.

After all five questions, the program shows:

--- Final Score: 4/5 ---

5. Play Again Option

After the score is displayed, the program asks:

Do you want to play again? (Y/N)


Y → Quiz restarts

N → Program ends with a thank-you message
