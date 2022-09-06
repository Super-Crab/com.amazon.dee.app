package com.amazon.dee.app.services.metrics.kinesis.client;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public interface KinesisEventRecorder {
    long getSizeOnDisk();

    void recordEvent(@NonNull String str);

    void submitEvents();
}
