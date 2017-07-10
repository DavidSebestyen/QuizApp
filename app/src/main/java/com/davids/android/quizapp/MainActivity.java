package com.davids.android.quizapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int finalScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * this method responds to the Submit button by displaying you score on the screen
     * @param view
     */
    public void submitAnswers(View view) {

        Button submitButton = (Button) findViewById(R.id.submit_button_button);


        // the radio button for the coorect answer in Question 1
        RadioButton questionOneAnswer = (RadioButton) findViewById(R.id.bulbasaur_radio_button);
        boolean isBulbasaur = questionOneAnswer.isChecked();

        // the radio button for the correct answer in Question 2
        RadioButton questionTwoAnswer = (RadioButton) findViewById(R.id.electricity_radio_button);
        boolean isElectricity = questionTwoAnswer.isChecked();

        // the checkboxes for the correct answers in question 3
        CheckBox questionThreeAnswerOne = (CheckBox) findViewById(R.id.charmander_check_box);
        boolean isCharmander = questionThreeAnswerOne.isChecked();


        CheckBox questionThreeAnswerTwo = (CheckBox) findViewById(R.id.magmar_check_box);
        boolean isMagmar = questionThreeAnswerTwo.isChecked();

        CheckBox questionThreeWrongAnswerOne = (CheckBox) findViewById(R.id.weedle_check_box);
        boolean isWeedle = questionThreeWrongAnswerOne.isChecked();


        CheckBox questionThreeWrongAnswerTwo = (CheckBox) findViewById(R.id.squirtle_check_box);
        boolean isSquirtle = questionThreeWrongAnswerTwo.isChecked();

        // the checkboxes for the correct answers in question 4
        CheckBox questionFourAnswerOne = (CheckBox) findViewById(R.id.pidgeot_check_box);
        boolean isPidgeot = questionFourAnswerOne.isChecked();

        CheckBox questionFourAnswerTwo = (CheckBox) findViewById(R.id.charizard_check_box);
        boolean isCharizard = questionFourAnswerTwo.isChecked();

        CheckBox questionFourWrongAnswerOne = (CheckBox) findViewById(R.id.paras_check_box);
        boolean isParas = questionFourWrongAnswerOne.isChecked();

        CheckBox questionFourWrongAnswerTwo = (CheckBox) findViewById(R.id.machomp_check_box);
        boolean isMachomp = questionFourWrongAnswerTwo.isChecked();


        //The radio button for the correct answer in question 5
        RadioButton questionFiveAnswer = (RadioButton) findViewById(R.id.pikachu_one_radio_button);
        boolean isPikachu = questionFiveAnswer.isChecked();


        //The EditTextView in Question 6
        EditText pokemonName = (EditText) findViewById(R.id.edit_text_box);
        String questionSixAnswer = pokemonName.getText().toString();


        int score = calculateScore(isBulbasaur, isElectricity,
                isCharmander, isMagmar,
                isWeedle, isSquirtle, isParas,isMachomp,
                isPidgeot, isCharizard, isPikachu, questionSixAnswer);

        if (score < 6) {
            Toast.makeText(MainActivity.this, "You have only achieved " + score + " out of 6, please try again!", Toast.LENGTH_SHORT).show();

        }

        if (score == 6) {

            Button shareResultButton = (Button) findViewById(R.id.share_button);
            shareResultButton.setVisibility(View.VISIBLE);



        }

        ((ViewGroup) submitButton.getParent()).removeView(submitButton);
        createScoreText(score);


    }

    /**
     *This method calculates the final score
     *
     * @param questionSixAnswer
     * @param isPikachu
     * @param isCharizard
     * @param isPidgeot
     * @param isCharmander
     * @param isMagmar
     * @param isBulbasaur
     * @param isElectricity
     * @return
     */
    private int calculateScore(boolean isBulbasaur, boolean isElectricity,
                               boolean isCharmander, boolean isMagmar, boolean isWeedle,
                               boolean isSquirtle, boolean isParas, boolean isMachomp,
                               boolean isPidgeot, boolean isCharizard, boolean isPikachu, String questionSixAnswer){

        int questionOneScore = 0;
        String answerSix = "Raichu";

        // adds 1 point to the final score if isBulbasaur selected in question 1
        if (isBulbasaur){
            questionOneScore = questionOneScore + 1;

        }

        // adds 1 point to the final score if isElectricity selected in question 2
        if (isElectricity){
            questionOneScore = questionOneScore + 1;

        }

        // adds 1 point to the final score if both the correct answers has been selected in question 3
        if (isCharmander && isMagmar && !isWeedle && !isSquirtle) {
            questionOneScore = questionOneScore + 1;
        }

        // adds 1 point to the final score if both the correct answers has been selected in question 4
        if (isPidgeot && isCharizard && !isParas && !isMachomp){
            questionOneScore = questionOneScore + 1;
        }

        //adds 1 point if isPikachu selected in question 5
        if (isPikachu){
            questionOneScore = questionOneScore + 1;
        }

        //adds 1 point if the text in the EditText view is matches "Raichu"
        if (questionSixAnswer.equals(answerSix)){
            questionOneScore = questionOneScore + 1;

        }


        return finalScore + questionOneScore;
    }

    /**
     * Creating the final score text
     * @param finalScore
     * @return
     */
    private String createScoreText(int finalScore) {
        TextView scoreTextView = (TextView) findViewById(
                R.id.score_text_view);
        if (finalScore == 5){
        scoreTextView.setText("Your score is: " + finalScore + " out of 6" + "\nWell done! You answered all the questions correctly.");
        } else {
            scoreTextView.setText("Your score is: " + finalScore + " out of 6");
        }
        scoreTextView.setVisibility(View.VISIBLE);
        String finalMessage = scoreTextView.getText().toString();
        return finalMessage;

    }

    /**
     * Share your score
     * @param view
     */
    public void shareResults(View view){


           String winningMessage = "I've just completed the Pokemon Quiz and scored 6 out of 6!! :D";
            Intent shareScore = new Intent(Intent.ACTION_SEND);
            shareScore.putExtra(Intent.EXTRA_TEXT, winningMessage);
            shareScore.setType("text/plain");
            startActivity(shareScore);

    }


    /**
     * Restarts the activity
     */
    public void restartActivity(View view){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
