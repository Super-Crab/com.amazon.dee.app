package com.amazon.alexa.handsfree.uservoicerecognition.edgesv.metrics;

import androidx.annotation.NonNull;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener;
/* loaded from: classes8.dex */
public class EdgeSVWakeWordDetectionMetricsListener implements WakeWordDetectionMetricsListener {
    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener
    public void onLocaleMismatch() {
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener
    public void onPryonErrorEvent(int i) {
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener
    public void onPryonInitializationFailure(int i, @NonNull String str) {
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener
    public void onPryonInitializationSuccess(long j, @NonNull String str) {
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener
    public void onPryonReset(long j) {
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener
    public void onWakeWordModelDownloadFailure(long j, @NonNull String str, @NonNull String str2) {
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener
    public void onWakeWordModelDownloadInterrupted(long j) {
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener
    public void onWakeWordModelDownloadSuccess(long j, @NonNull String str) {
    }
}
