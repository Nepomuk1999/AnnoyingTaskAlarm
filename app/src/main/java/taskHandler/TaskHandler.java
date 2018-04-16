package taskHandler;

import android.content.Context;

import com.example.home.annoyingtaskalarm.NextQuestionAsyncTask;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
    private AnnoyingTaskAlarmDatabase annoyingTaskAlarmDatabase ;
    private List<Task> tasks = new LinkedList<>();
    private int taskCounter = 0;
    private Task currentTask;

    public TaskHandler getInstance(){
        if (instance == null){
            instance = new TaskHandler();
        }
        return instance;
    }

    public void getAllTasks (Context context){
        if (annoyingTaskAlarmDatabase == null){
            annoyingTaskAlarmDatabase = AnnoyingTaskAlarmDatabase.getInstance(context);
        }
        if (tasks.isEmpty()){
            tasks = annoyingTaskAlarmDatabase.taskDao().getAll();
        }
    }

    public Task nextTask() {
        NextQuestionAsyncTask nextQuestionAsyncTask = NextQuestionAsyncTask.getInstance();
        Task temp = null;
        try {
            temp = nextQuestionAsyncTask.execute(this).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * Call to get the task witch was not used the longest, and updates the use to current time in milsec
     * @return task witch was not used the longest
     */
    public Task getNextTask(){
        currentTask = annoyingTaskAlarmDatabase.taskDao().getNext();
        annoyingTaskAlarmDatabase.taskDao().updateTime(currentTask.getId(), (int)System.currentTimeMillis());
        return currentTask;
    }

    public boolean checkAnswer(String answer){
        return currentTask.getCorrectAnswer().equals(answer);
    }




}
