package com.amazon.clouddrive.android.core.interfaces;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import java.util.List;
/* loaded from: classes11.dex */
public interface LogsCollector<TValue> {
    @Nullable
    @WorkerThread
    List<LogsCollectionEntry<TValue>> collectLogs();
}
