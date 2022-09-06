package com.amazon.clouddrive.android.core.interfaces;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
/* loaded from: classes11.dex */
public interface TokenAndCookieProvider {
    @Nullable
    @WorkerThread
    String getAccessTokenBlocking(@NonNull Context context);

    @Nullable
    @WorkerThread
    String[] getCookiesBlocking(@NonNull Context context, @NonNull String str);
}
