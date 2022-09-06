package com.amazon.clouddrive.android.core.interfaces;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface Logger {
    void d(@NonNull String str, @NonNull String str2);

    void d(@NonNull String str, @NonNull String str2, @NonNull Throwable th);

    void e(@NonNull String str, @NonNull String str2);

    void e(@NonNull String str, @NonNull String str2, @NonNull Throwable th);

    void i(@NonNull String str, @NonNull String str2);

    void i(@NonNull String str, @NonNull String str2, @NonNull Throwable th);

    void logCustomerEvent(@NonNull String str);

    void v(@NonNull String str, @NonNull String str2);

    void v(@NonNull String str, @NonNull String str2, @NonNull Throwable th);

    void w(@NonNull String str, @NonNull String str2);

    void w(@NonNull String str, @NonNull String str2, @NonNull Throwable th);

    void wtf(@NonNull String str, @NonNull String str2);

    void wtf(@NonNull String str, @NonNull String str2, @NonNull Throwable th);
}
