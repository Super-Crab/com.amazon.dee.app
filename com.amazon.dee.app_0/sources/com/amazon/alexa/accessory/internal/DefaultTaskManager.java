package com.amazon.alexa.accessory.internal;

import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.LoggerUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.ArrayDeque;
import java.util.Iterator;
/* loaded from: classes.dex */
public final class DefaultTaskManager implements TaskManager {
    private boolean released;
    private boolean running;
    private TaskInstance runningInstance;
    private final ArrayDeque<TaskInstance> tasks;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class TaskInstance {
        private final int priority;
        private int state = 0;
        private final TaskManager.Task task;

        public TaskInstance(TaskManager.Task task, int i) {
            this.task = task;
            this.priority = i;
        }

        private void changeState(int i) {
            int i2 = this.state;
            if (i2 == i) {
                return;
            }
            LoggerUtils.taskState(this.task, getStateName(i2), getStateName(i), this.priority);
            int i3 = this.state;
            this.state = i;
            this.task.onStateChanged(i3, i);
        }

        private static String getStateName(int i) {
            return i == 0 ? "STATE_NONE" : i == 1 ? "STATE_PENDING" : i == 2 ? "STATE_SUSPENDED" : i == 3 ? "STATE_RUNNING" : i == 4 ? "STATE_DISPOSED" : "INVALID_STATE";
        }

        public void dispose() {
            if (this.state != 0) {
                changeState(4);
            }
        }

        public int getPriority() {
            return this.priority;
        }

        public boolean is(TaskManager.Task task) {
            return this.task == task;
        }

        public void pending() {
            if (this.state == 0) {
                changeState(1);
            }
        }

        public void run() {
            if (this.state != 4) {
                changeState(3);
            }
        }

        public void suspend() {
            if (this.state == 3) {
                changeState(2);
            }
        }

        public String toString() {
            return String.format("'%s' (priority: %d)", this.task, Integer.valueOf(this.priority));
        }
    }

    public DefaultTaskManager() {
        Preconditions.mainThread();
        this.tasks = new ArrayDeque<>();
    }

    private void checkNotReleased() {
        if (!this.released) {
            return;
        }
        throw new IllegalStateException("Task manager is released and should not be used!");
    }

    private TaskInstance findHighPriorityInstance() {
        Iterator<TaskInstance> it2 = this.tasks.iterator();
        TaskInstance taskInstance = null;
        while (it2.hasNext()) {
            TaskInstance next = it2.next();
            if (taskInstance == null || taskInstance.getPriority() < next.getPriority()) {
                taskInstance = next;
            }
        }
        return taskInstance;
    }

    private TaskInstance findInstanceByTask(TaskManager.Task task) {
        Iterator<TaskInstance> it2 = this.tasks.iterator();
        while (it2.hasNext()) {
            TaskInstance next = it2.next();
            if (next.is(task)) {
                return next;
            }
        }
        return null;
    }

    private boolean isPriorityInBounds(int i) {
        return i >= 0 && i <= 10;
    }

    @Override // com.amazon.alexa.accessory.TaskManager
    public boolean dispose(TaskManager.Task task) {
        TaskInstance findInstanceByTask;
        Preconditions.mainThread();
        if (this.released || task == null || (findInstanceByTask = findInstanceByTask(task)) == null) {
            return false;
        }
        this.tasks.remove(findInstanceByTask);
        findInstanceByTask.dispose();
        if (this.runningInstance != findInstanceByTask) {
            return true;
        }
        this.runningInstance = findHighPriorityInstance();
        TaskInstance taskInstance = this.runningInstance;
        if (taskInstance == null || !this.running) {
            return true;
        }
        taskInstance.run();
        return true;
    }

    public void release() {
        Preconditions.mainThread();
        if (this.released) {
            return;
        }
        this.released = true;
        this.running = false;
        TaskInstance taskInstance = this.runningInstance;
        if (taskInstance != null) {
            taskInstance.dispose();
            this.runningInstance = null;
        }
        while (true) {
            TaskInstance poll = this.tasks.poll();
            if (poll == null) {
                return;
            }
            poll.dispose();
        }
    }

    @Override // com.amazon.alexa.accessory.TaskManager
    public boolean schedule(TaskManager.Task task, int i) {
        Preconditions.mainThread();
        Preconditions.notNull(task, "task");
        boolean isPriorityInBounds = isPriorityInBounds(i);
        Preconditions.precondition(isPriorityInBounds, "Invalid priority " + i);
        if (this.released) {
            Logger.d("Task manager is released and cannot schedule tasks!");
            return false;
        }
        TaskInstance taskInstance = new TaskInstance(task, i);
        TaskInstance taskInstance2 = this.runningInstance;
        if (taskInstance2 != null && taskInstance2.getPriority() >= taskInstance.getPriority()) {
            if (taskInstance.getPriority() >= 9) {
                return false;
            }
            Logger.d("There is an existing task running, %s. Adding %s to the current scheduled queue: %s", this.runningInstance, taskInstance, this.tasks);
            this.tasks.add(taskInstance);
            taskInstance.pending();
        } else if (!this.running && taskInstance.getPriority() >= 9) {
            return false;
        } else {
            this.tasks.add(taskInstance);
            TaskInstance taskInstance3 = this.runningInstance;
            this.runningInstance = taskInstance;
            if (taskInstance3 != null) {
                taskInstance3.suspend();
            }
            if (this.running) {
                taskInstance.run();
            } else {
                taskInstance.pending();
            }
        }
        return true;
    }

    public void start() {
        checkNotReleased();
        Preconditions.mainThread();
        if (this.running) {
            return;
        }
        this.running = true;
        TaskInstance taskInstance = this.runningInstance;
        if (taskInstance == null) {
            return;
        }
        taskInstance.run();
    }

    public void stop() {
        Preconditions.mainThread();
        if (this.released || !this.running) {
            return;
        }
        this.running = false;
        TaskInstance taskInstance = this.runningInstance;
        if (taskInstance == null || taskInstance.getPriority() >= 9) {
            return;
        }
        this.runningInstance.suspend();
    }
}
