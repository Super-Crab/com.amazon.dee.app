package com.amazon.alexa.wakeword.speakerverification.metrics;

import androidx.annotation.NonNull;
import com.amazon.alexa.wakeword.speakerverification.pryon.PryonOperation;
/* loaded from: classes11.dex */
public interface PryonMetricsListener {
    void onExampleAccepted();

    void onExampleRejected();

    void onPryonOperationFailure(@NonNull PryonOperation pryonOperation, int i);

    void onPryonOperationSuccess(@NonNull PryonOperation pryonOperation);
}
