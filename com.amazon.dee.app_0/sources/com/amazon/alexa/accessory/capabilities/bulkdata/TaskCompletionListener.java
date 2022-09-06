package com.amazon.alexa.accessory.capabilities.bulkdata;

import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.TaskManager.Task;
/* loaded from: classes.dex */
public interface TaskCompletionListener<T extends TaskManager.Task> {
    void onTaskComplete(T t);
}
