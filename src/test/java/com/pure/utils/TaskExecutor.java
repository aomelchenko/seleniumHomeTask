package com.pure.utils;

import java.util.concurrent.Callable;

public class TaskExecutor {

    public static <T> T executeTask(Callable<T> task, int attempts, String assertionMessage) {
        return executeTask(task, attempts, 1000, assertionMessage);
    }

    public static <T> T executeTask(Callable<T> task, String assertionMessage) {
        return executeTask(task, 3, 1000, assertionMessage);
    }

    public static <T> T executeTask(Callable<T> task, int attempts, int delay, String assertionMessage) {
        T result =  executeTask(task, attempts, delay);
        if (result != null) {
            return result;
        }
        throw new AssertionError(assertionMessage);
    }

    private static <T> T executeTask(Callable<T> task, int attempts, int attemptDelay) {
        int attempt = 1;
        T result = executor(task);
        while (result == null && attempt < attempts) {
            result = executor(task);
            attempt++;
            sleep(attemptDelay);
        }
        return result;
    }

    private static <T> T executor(Callable<T> task) {
        T result = null;

        try {
            result = task.call();
        } catch (Exception | AssertionError e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
