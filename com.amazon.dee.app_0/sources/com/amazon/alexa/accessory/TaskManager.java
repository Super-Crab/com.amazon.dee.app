package com.amazon.alexa.accessory;
/* loaded from: classes.dex */
public interface TaskManager {
    public static final int PRIORITY_BACKGROUND = 2;
    public static final int PRIORITY_FOREGROUND = 4;
    public static final int PRIORITY_MAX = 10;
    public static final int PRIORITY_MIN = 0;
    public static final int PRIORITY_NONE = 0;
    public static final int PRIORITY_URGENT = 9;
    public static final int STATE_DISPOSED = 4;
    public static final int STATE_NONE = 0;
    public static final int STATE_PENDING = 1;
    public static final int STATE_RUNNING = 3;
    public static final int STATE_SUSPENDED = 2;

    /* loaded from: classes.dex */
    public interface Task {
        void onStateChanged(int i, int i2);
    }

    boolean dispose(Task task);

    boolean schedule(Task task, int i);
}
