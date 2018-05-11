package com.example.home.annoyingtaskalarm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    private EditText editTextAnswer;
    private TextView showQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_dialog);
        System.out.println("in QuestionOnCreate!!!!");
    }
}
