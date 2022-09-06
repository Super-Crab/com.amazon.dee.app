package com.amazon.device.utils.thirdparty;

import android.os.Process;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class ThreadUtil {
    public static final int BACKGROUND_THREAD_PRIORITY = 4;
    private static final String TAG = "ThreadUtil";

    public static void setCurrentThreadPriorityToBackground() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("setCurrentThreadPriorityToBackground: Lowered priority of ");
        outline107.append(Thread.currentThread().toString());
        outline107.append(" to background.");
        outline107.toString();
        Thread.currentThread().setPriority(4);
    }

    public static void tryToBackgroundAllThreads() {
        Process.setThreadPriority(10);
        setCurrentThreadPriorityToBackground();
        tryToBackgroundAllThreadsInParentThreadGroup();
    }

    public static void tryToBackgroundAllThreadsInParentThreadGroup() {
        Thread[] threadArr = new Thread[Thread.currentThread().getThreadGroup().getParent().activeCount() * 2];
        Thread.currentThread().getThreadGroup().getParent().enumerate(threadArr, true);
        for (Thread thread : threadArr) {
            if (thread != null) {
                try {
                    String str = "tryToBackgroundAllThreadsInParentThreadGroup: Lowering thread priority of " + thread.toString() + " to 4";
                    thread.setPriority(4);
                } catch (SecurityException unused) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("tryToBackgroundAllThreadsInParentThreadGroup: SecurityException while setting thread priority to background for ");
                    outline107.append(thread.toString());
                    outline107.toString();
                }
            }
        }
    }
}
