package com.amazon.clouddrive.cdasdk.util;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface SystemUtil {
    public static final int STANDARD_BASE_64_FLAGS = 3;

    long currentTimeMillis();

    long elapsedRealtime();

    @NonNull
    String getBase64(@NonNull String str, int i);

    void interrupt();

    int randomNextInt(int i);

    @NonNull
    String replaceUriPath(@NonNull String str, @NonNull String str2);

    void sleep(long j) throws InterruptedException;
}
