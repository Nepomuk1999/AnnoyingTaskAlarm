package com.example.home.annoyingtaskalarm;

import android.os.AsyncTask;

import handler.TaskHandler;
import persistence.Task;

public class NextQuestionAsyncTask extends AsyncTask<TaskHandler, Task, Task> {

    @Override
    protected Task doInBackground(TaskHandler... taskHandlers) {
        TaskHandler taskHandler = taskHandlers[0];
        return taskHandler.getNextTask();
    }

    @Override
    protected void onPostExecute(Task task) {
        AlarmActivity.getTextView2().setText(task.getQuestion());
    }
}
