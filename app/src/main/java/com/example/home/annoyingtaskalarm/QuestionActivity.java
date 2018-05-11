package com.example.home.annoyingtaskalarm;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import handler.AlarmHandler;
import handler.TaskHandler;
import persistence.Task;

public class QuestionActivity extends AppCompatActivity {

    private EditText editTextAnswer;
    private TextView showQuestion;
    private TextView failedText;
    private Button btnAnswer;
    private Task currentTask;
    private TaskHandler taskHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_dialog);
        System.out.println("in QuestionOnCreate!!!!");

        editTextAnswer = (EditText) findViewById(R.id.alarmAnswer);
        showQuestion = (TextView) findViewById(R.id.alarmQuestion);
        failedText = (TextView) findViewById(R.id.failedText);
        btnAnswer = (Button) findViewById(R.id.btnQuestion);

        // listener for check button
        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(view);
            }
        });

        // get Alarmentity and insert Question here!!!
        taskHandler = TaskHandler.getInstance(getApplicationContext());
        currentTask = taskHandler.nextTask(getApplicationContext());
        showQuestion.setText(currentTask.getQuestion());

    }

    public void checkAnswer(View view) {
        // check answer with question answer
        String answer = editTextAnswer.getText().toString();
        Context context = getApplicationContext();
        //check anwer here!!!
        if(taskHandler.checkAnswer(answer)){
            AlarmReceiver.stopRingtone();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            failedText.setText("Wrong! Falsch! Wrong! Falsch!");
        }
    }
}
