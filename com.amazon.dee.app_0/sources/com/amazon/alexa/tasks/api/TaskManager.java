package com.amazon.alexa.tasks.api;

import java.util.concurrent.ExecutorService;
/* loaded from: classes10.dex */
public interface TaskManager {
    void addAfterUiTask(Runnable runnable);

    ExecutorService getExecutor(@TaskType int i);
}
