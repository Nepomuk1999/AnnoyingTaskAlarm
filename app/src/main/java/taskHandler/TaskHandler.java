package taskHandler;

import android.content.Context;

import com.example.home.annoyingtaskalarm.NextQuestionAsyncTask;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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

    private static TaskHandler instance;
    private static AnnoyingTaskAlarmDatabase annoyingTaskAlarmDatabase ;
    private List<Task> tasks = new LinkedList<>();
    private int taskCounter = 0;
    private Task currentTask;

    public static TaskHandler getInstance(Context context){
        if (instance == null){
            instance = new TaskHandler();
            annoyingTaskAlarmDatabase = AnnoyingTaskAlarmDatabase.getInstance(context);
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

    public Task nextTask(Context context) {
        NextQuestionAsyncTask nextQuestionAsyncTask = NextQuestionAsyncTask.getInstance(context);
        Task temp = new Task();
        try {
            temp = nextQuestionAsyncTask.execute(this).get(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * Call to get the task witch was not used the longest, and updates the use to current time in milsec
     * @return task witch was not used the longest
     */
    public Task getNextTask(){
        Task temp = annoyingTaskAlarmDatabase.taskDao().getNext();
        currentTask = temp;

        annoyingTaskAlarmDatabase.taskDao().updateTime(currentTask.getId(), (int)System.currentTimeMillis());
        return currentTask;
    }

    public boolean checkAnswer(String answer){
        return currentTask.getCorrectAnswer().equals(answer);
    }


}
