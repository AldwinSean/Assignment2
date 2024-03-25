//Group 12: Aldwin Eje, Lady Rose Alarcon
//Assignment 2: Who is the player
//Device used: Pixel 3 API 25

package com.example.assignment_02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;
import android.widget.ScrollView;

import static com.example.assignment_02.R.id;
import static com.example.assignment_02.R.layout;

public class MainActivity extends AppCompatActivity {

    Question[] questions = new Question[5]; // Index range is 0-4

    Button op1, op2, op3, op4, prev, next, nextLevel;
    ImageView imgViewCelebrity;
    TextView scoreCount;
    int score = 0, questionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        Button helpButton = (Button) findViewById(id.helpButton);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Game Instruction");

                // Create a ScrollView to contain the message
                ScrollView scrollView = new ScrollView(MainActivity.this);

                // Create a TextView to hold the message
                TextView messageTextView = new TextView(MainActivity.this);


                builder.setMessage("Welcome to Guess the Celebrity Picture!\n" +
                        "\nTo play, simply choose the correct answer by clicking on one of the button options.\n" +
                        "\nRemember, you only have one chance to select an answer.\n" +
                        "\nAfter answering, you can click on NEXT to proceed to the next question.\n" +
                        "\nEach correct answer earns you 1 point.\n" +
                        "\nIf you want to skip answering, you can also click on NEXT.\n" +
                        "\nTo restart the game at any time, just click on RESET.\n" +
                        "\nLet's see how many celebrities you can guess!");

                // Set padding for the TextView
                int padding = getResources().getDimensionPixelSize(R.dimen.alert_dialog_padding);
                messageTextView.setPadding(padding, padding, padding, padding);

                // Add the TextView to the ScrollView
                scrollView.addView(messageTextView);


                // Set the ScrollView as the message of the AlertDialog
                builder.setView(scrollView);


                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();

            }
        });

        op1 = findViewById(id.btn_Option1);
        op2 = findViewById(id.btn_Option2);
        op3 = findViewById(id.btn_Option3);
        op4 = findViewById(id.btn_Option4);

        prev = findViewById(id.btn_Previous);
        next = findViewById(id.btn_Next);

        imgViewCelebrity = findViewById(id.imgView_Celebrity);

        scoreCount = findViewById(id.scoreView);

        questions[0] = new Question(
                "Taylor Swift",
                "Adele",
                "Beyoncé",
                "Lady Gaga",
                "Taylor Swift",
                R.drawable.taylorswift);

        questions[1] = new Question(
                "Chris Hemsworth",
                "Robert Downey Jr.",
                "Tom Holland",
                "Chris Evans",
                "Robert Downey Jr.",
                R.drawable.robertdownyjr);

        questions[2] = new Question(
                "Brad Pitt",
                "Leonardo DiCaprio",
                "Johnny Depp",
                "George Clooney",
                "Leonardo DiCaprio",
                R.drawable.leo);

        questions[3] = new Question(
                "Emma Watson",
                "Jennifer Lawrence",
                "Scarlett Johansson",
                "Angelina Jolie",
                "Scarlett Johansson",
                R.drawable.scarlet);

        questions[4] = new Question(
                "Dwayne Johnson",
                "Vin Diesel",
                "Jason Statham",
                "Chris Pratt",
                "Dwayne Johnson",
                R.drawable.dwayne);

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("score");
            questionIndex = savedInstanceState.getInt("questionIndex");
        }
        loadQuestions();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score", score);
        outState.putInt("questionIndex", questionIndex);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        score = savedInstanceState.getInt("score");
        questionIndex = savedInstanceState.getInt("questionIndex");
        loadQuestions();
    }

    public void loadQuestions() {
        // Load image for the current question
        imgViewCelebrity.setImageResource(questions[questionIndex].imageResourceId);
        op1.setText(questions[questionIndex].option1);
        op2.setText(questions[questionIndex].option2);
        op3.setText(questions[questionIndex].option3);
        op4.setText(questions[questionIndex].option4);
        //scoreCount.setText(Integer.toString(score));
        scoreCount.setText(String.format(Locale.getDefault(), "%d", score));
    }

    public void nextQuestion(View view){
        if (questionIndex == 4) Toast.makeText(this, "You're done with the questions.", Toast.LENGTH_SHORT).show();
        else{
            questionIndex++;
            loadQuestions();
        }
    }

    public void previousQuestion(View view){
        if (questionIndex == 0) Toast.makeText(this, "This is the first question.", Toast.LENGTH_SHORT).show();
        else{
            questionIndex--;
            loadQuestions();
        }
    }

    public void resetGame(View view) {
        for (Question question : questions) {
            question.answered = false;
        }
        score = 0;
        questionIndex = 0;
        loadQuestions();
        Toast.makeText(this, "Game Reset", Toast.LENGTH_SHORT).show();
    }

    public void pickAnswer(View view){
        Button button = (Button) view;
        if (button.getText() == questions[questionIndex].correctAnswer){
            if (!questions[questionIndex].answered) score++;
            Toast.makeText(this, "You've got it right! go to Next Question.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Incorrect, go to Next Question", Toast.LENGTH_SHORT).show();
        }
        questions[questionIndex].answered = true;
        loadQuestions();
    }
}