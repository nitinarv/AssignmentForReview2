package games.xeed.hackerrank.com.assignment2.controller;


import games.xeed.hackerrank.com.assignment2.model.TaskResult;

/**
 * Created by  nitinraj.arvind on 06/07/15.
 */
public interface OperationCallback {


    public abstract void processException(Exception e);
    public abstract void onProgressStarted();
    public abstract void onProgressEnded();
    public abstract void onOperationCancelled();
    public abstract void onProgressUpdated(int progressPercent);
    public abstract void processFinalResult(Object object);
    public abstract void useStringResult(String result);
    public abstract void storeTaskResult(TaskResult taskResult);

}
