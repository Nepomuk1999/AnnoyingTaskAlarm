package taskHandler;

import android.content.Context;

import java.util.List;

import persistence.AnnoyingTaskAlarmDatabase;
import persistence.Task;

/**
 * Created by Christoph Bauer on 19.03.2018.
 *
 *          ,/
 *        ./(\
 * -`___-'  |`
 * ''-(  -`--)
 *     7/`
 *     \\
 */

public class TaskHandler {

    private TaskHandler instance;
    private AnnoyingTaskAlarmDatabase annoyingTaskAlarmDatabase;
    private List<Task> tasks;
    private int taskCounter = 0;
    private Task currentTask;

    public TaskHandler getInstance(){
        if (instance == null){
            instance = new TaskHandler();
        }
        return instance;
    }

    public List<Task> getAllTasks (Context context){
        if (annoyingTaskAlarmDatabase == null){
            AnnoyingTaskAlarmDatabase check=
            annoyingTaskAlarmDatabase = AnnoyingTaskAlarmDatabase.getInstance(context);
        }
        if (tasks.isEmpty()){
            tasks = annoyingTaskAlarmDatabase.taskDao().getAll();
        }
        return tasks;
    }

    public Task getNextTask(){
        if (tasks.size() == taskCounter-1){
            taskCounter = 0;
        }
        currentTask = tasks.get(taskCounter);

        taskCounter++;
        return currentTask;
    }

    public boolean checkAnswer(String answer){
        return currentTask.getCorrectAnswer().equals(answer);
    }




}
