package com.amazon.communication;
/* loaded from: classes12.dex */
public interface PowerManagerWrapper {
    public static final int FULL_WAKE_LOCK = 26;
    public static final int PARTIAL_WAKE_LOCK = 1;
    public static final int SCREEN_BRIGHT_WAKE_LOCK = 10;
    public static final int SCREEN_DIM_WAKE_LOCK = 6;

    /* loaded from: classes12.dex */
    public interface WakeLock {
        void acquire();

        void acquire(long j);

        boolean isHeld();

        void release();

        void setReferenceCounted(boolean z);
    }

    WakeLock newWakeLock(int i, String str);
}
