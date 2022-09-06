package com.facebook.datasource;

import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public interface DataSource<T> {
    boolean close();

    @Nullable
    Map<String, Object> getExtras();

    @Nullable
    Throwable getFailureCause();

    float getProgress();

    @Nullable
    /* renamed from: getResult */
    T mo6898getResult();

    boolean hasFailed();

    boolean hasMultipleResults();

    boolean hasResult();

    boolean isClosed();

    boolean isFinished();

    void subscribe(DataSubscriber<T> dataSubscriber, Executor executor);
}
