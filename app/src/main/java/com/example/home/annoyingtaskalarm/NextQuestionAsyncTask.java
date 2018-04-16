package com.example.home.annoyingtaskalarm;

import android.os.AsyncTask;

import persistence.Task;
import taskHandler.TaskHandler;

public class NextQuestionAsyncTask extends AsyncTask<TaskHandler, Void, Task> {

    private static NextQuestionAsyncTask instance;

    public static NextQuestionAsyncTask getInstance() {
        if (instance == null){
            instance = new NextQuestionAsyncTask();
        }
        return instance;
    }

    @Override
    protected Task doInBackground(TaskHandler... taskHandlers) {
        TaskHandler taskHandler = taskHandlers[0];
        return taskHandler.getNextTask();
    }
}
