package com.amazon.alexa.handsfree.protocols.utils;

import android.content.Context;
import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public interface CrashReportRecorder {
    void reportNonFatalCrash(@NonNull Context context, @NonNull String str, @NonNull Throwable th);
}
