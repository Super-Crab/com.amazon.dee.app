package com.amazon.clouddrive.internal.utils;
/* loaded from: classes11.dex */
public class ThreadUtil {
    public static void checkIfInterrupted() throws InterruptedException {
        if (!Thread.interrupted()) {
            return;
        }
        throw new InterruptedException();
    }
}
