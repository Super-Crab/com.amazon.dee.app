package com.amazon.device.crashmanager.utils;
/* loaded from: classes12.dex */
public interface CrashDescriptorDedupeUtil {
    void clearAll();

    boolean contains(String str, String str2);

    int getCount(String str, String str2);

    void increaseCounter(String str, String str2);

    void persistCrashDescriptors();

    void prune(String str, String str2);

    void prune(String str, String str2, int i);
}
