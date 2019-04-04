package com.chrisfetz.gogetter;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;

/**
 * Executor pools for Gogetter
 */
public class BackendExecutors {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static BackendExecutors sInstance;
    private final Executor diskIO;
    private final Executor mainThread;
    private final Executor networkIO;

    /**
     * Creates three Executors to do tasks off the main ui thread
     * @param diskIO Disk writes executor, makes changes on disk
     * @param networkIO Makes calls to the network
     * @param mainThread Makes calls on the main UI thread
     */
    private BackendExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    /**
     * Singleton constructor of BackendExecutors
     * @return a new instance of BackendExecutors if one doesn't already exist, or the current existing one
     */
    public static BackendExecutors getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new BackendExecutors(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MainThreadExecutor());
            }
        }
        return sInstance;
    }

    /**
     * @return the disk writes Executor
     */
    public Executor diskIO() {
        return diskIO;
    }

    /**
     * @return the main thread executor
     */
    public Executor mainThread() {
        return mainThread;
    }

    /**
     * @return the network executor
     */
    public Executor networkIO() {
        return networkIO;
    }

    /**
     * If a task needs to be performed on the main thread, another thread can pass
     * off a task to this Executor.
     */
    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
