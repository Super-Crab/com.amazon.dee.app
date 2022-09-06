package com.amazon.alexa.wakeword.pryon;
/* loaded from: classes11.dex */
public interface WakeWordDetectionMetricsListener {
    void onLocaleMismatch();

    void onPryonErrorEvent(int i);

    void onPryonInitializationFailure(int i, String str);

    void onPryonInitializationSuccess(long j, String str);

    void onPryonReset(long j);

    void onWakeWordModelDownloadFailure(long j, String str, String str2);

    void onWakeWordModelDownloadInterrupted(long j);

    void onWakeWordModelDownloadSuccess(long j, String str);
}
