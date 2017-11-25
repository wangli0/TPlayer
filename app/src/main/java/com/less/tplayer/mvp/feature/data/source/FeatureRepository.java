package com.less.tplayer.mvp.feature.data.source;

import android.support.annotation.NonNull;

import com.less.tplayer.mvp.feature.data.Feature;
import com.less.tplayer.mvp.feature.data.source.local.FeatureLocalDataSource;
import com.less.tplayer.mvp.feature.data.source.remote.FeatureRemoteDataSource;

/**
 * Created by deeper on 2017/11/25.
 */

public class FeatureRepository implements FeatureDataSource {

    private static FeatureRepository INSTANCE = null;

    private final FeatureDataSource mRetemoDataSource;

    private final FeatureDataSource mLocalDataSource;

    public FeatureRepository() {
        this.mLocalDataSource = new FeatureLocalDataSource();
        this.mRetemoDataSource = new FeatureRemoteDataSource();
    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {

    }

    @Override
    public void saveTask(@NonNull Feature task) {

    }

    @Override
    public void completeTask(@NonNull Feature task) {

    }

    @Override
    public void completeTask(@NonNull String taskId) {

    }

    @Override
    public void activateTask(@NonNull Feature task) {

    }

    @Override
    public void activateTask(@NonNull String taskId) {

    }

    @Override
    public void clearCompletedTasks() {

    }

    @Override
    public void refreshTasks() {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteTask(@NonNull String taskId) {

    }
}
