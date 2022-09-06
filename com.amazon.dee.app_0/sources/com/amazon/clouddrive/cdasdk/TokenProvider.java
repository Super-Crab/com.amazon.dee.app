package com.amazon.clouddrive.cdasdk;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
/* loaded from: classes11.dex */
public interface TokenProvider {
    @Nullable
    @WorkerThread
    String getAccessTokenBlocking(@NonNull Context context);
}
