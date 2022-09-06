package com.amazon.device.nos;

import android.app.AlarmManager;
import android.app.PendingIntent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes12.dex */
public class AlarmManagerMethods {
    protected static Method sSetWindowMethod;

    static {
        try {
            sSetWindowMethod = AlarmManager.class.getMethod("setWindow", Integer.TYPE, Long.TYPE, Long.TYPE, PendingIntent.class);
        } catch (NoSuchMethodException unused) {
        }
    }

    protected AlarmManagerMethods() {
    }

    public static boolean isSetWindowAvailable() {
        return sSetWindowMethod != null;
    }

    public static void setWindow(AlarmManager alarmManager, int i, long j, long j2, PendingIntent pendingIntent) {
        if (alarmManager != null) {
            if (isSetWindowAvailable()) {
                try {
                    sSetWindowMethod.invoke(alarmManager, Integer.valueOf(i), Long.valueOf(j), Long.valueOf(j2), pendingIntent);
                    return;
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    Throwable cause = e2.getCause();
                    if (cause instanceof RuntimeException) {
                        throw ((RuntimeException) cause);
                    }
                    throw new RuntimeException(cause);
                }
            }
            throw new RuntimeException("AlarmManager has no setWindow() method on this platform");
        }
        throw new IllegalArgumentException("AlarmManager must not be null");
    }
}
