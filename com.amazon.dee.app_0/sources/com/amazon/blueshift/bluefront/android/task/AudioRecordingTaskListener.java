package com.amazon.blueshift.bluefront.android.task;

import com.amazon.blueshift.bluefront.android.SpeechClientException;
/* loaded from: classes11.dex */
public interface AudioRecordingTaskListener {
    void onBeginningOfSpeech();

    void onBufferReceived(byte[] bArr);

    void onError(SpeechClientException speechClientException);

    void onMaxSpeechTimeout();

    void onNoSpeechTimeout();

    void onReadyForSpeech();

    void onRmsChanged(float f);

    void onSilenceDetected();
}
