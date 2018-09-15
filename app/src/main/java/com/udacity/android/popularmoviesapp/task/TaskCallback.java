package com.udacity.android.popularmoviesapp.task;

import java.util.List;

public interface TaskCallback<T> {
    void onTaskStart();
    void onTaskComplete(List<T> result);
}
