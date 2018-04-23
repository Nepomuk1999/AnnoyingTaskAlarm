package com.example.home.annoyingtaskalarm;

import android.content.Context;
import android.os.AsyncTask;

import persistence.AnnoyingTaskAlarmDatabase;
import persistence.Task;
import taskHandler.TaskHandler;

public class NextQuestionAsyncTask extends AsyncTask<TaskHandler, Task, Task> {

    private static NextQuestionAsyncTask instance;
    private static AnnoyingTaskAlarmDatabase database;
    private Task currentTask;

    public static NextQuestionAsyncTask getInstance(Context context) {
        if (instance == null){
            instance = new NextQuestionAsyncTask();
            database = AnnoyingTaskAlarmDatabase.getInstance(context);
        }
        return instance;
    }


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
