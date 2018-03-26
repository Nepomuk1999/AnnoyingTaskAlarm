package taskHandler;

/**
 * Created by Home on 19.03.2018.
 */

public class TaskHandler {

    private TaskHandler instance;

    public TaskHandler getInstance(){
        if (instance != null){
            return instance;
        } else {
            instance = new TaskHandler();
            return instance;
        }
    }

    //test

    //TODO implement different tasks
    public String getTaskAsString(){
        return "1+1=";
    }

    //TODO implement real check
    public boolean checkResult(String result){
        if (result == "2"){
            return true;
        } else {
            return false;
        }
    }


}
