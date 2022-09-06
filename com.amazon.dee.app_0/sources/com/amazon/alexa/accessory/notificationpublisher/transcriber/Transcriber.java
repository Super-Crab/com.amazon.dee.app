package com.amazon.alexa.accessory.notificationpublisher.transcriber;

import android.os.Handler;
/* loaded from: classes.dex */
interface Transcriber {
    void cancelTranscribe();

    boolean hasRecordAudioPermission();

    void onManualSpeechEndPointing();

    boolean startTranscribe(TranscribeListener transcribeListener, Handler handler);
}
