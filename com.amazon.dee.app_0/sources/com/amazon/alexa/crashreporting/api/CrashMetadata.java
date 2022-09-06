package com.amazon.alexa.crashreporting.api;

import androidx.annotation.NonNull;
/* loaded from: classes6.dex */
public interface CrashMetadata {
    void put(@NonNull String str, double d);

    void put(@NonNull String str, float f);

    void put(@NonNull String str, int i);

    void put(@NonNull String str, long j);

    void put(@NonNull String str, @NonNull String str2);

    void put(@NonNull String str, boolean z);
}
