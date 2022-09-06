package com.amazon.alexa.accessory.notificationpublisher.transcriber;
/* loaded from: classes.dex */
interface TranscribeListener {
    void onTranscribeError(int i);

    void onTranscribeResult(String str, float f);
}
