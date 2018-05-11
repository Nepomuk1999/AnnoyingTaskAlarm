package com.example.home.annoyingtaskalarm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    private EditText editTextAnswer;
    private TextView showQuestion;
    private Button btnAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_dialog);
        System.out.println("in QuestionOnCreate!!!!");

        editTextAnswer = (EditText) findViewById(R.id.alarmAnswer);
        showQuestion = (TextView) findViewById(R.id.alarmQuestion);

        btnAnswer = (Button) findViewById(R.id.btnQuestion);

        // get Alarmentity and insert Question here!!!
        //showQuestion.setText();

        String answer = editTextAnswer.getText().toString();



        // check answer with question answer
    }
}
