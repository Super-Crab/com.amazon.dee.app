package com.amazon.blueshift.bluefront.android;

import android.os.Bundle;
import android.speech.RecognitionListener;
/* loaded from: classes11.dex */
public interface SpeechRequestListener<T> extends RecognitionListener {

    /* loaded from: classes11.dex */
    public static class NullSpeechRequestListener<T> implements SpeechRequestListener<T> {
        @Override // android.speech.RecognitionListener
        public void onBeginningOfSpeech() {
        }

        @Override // com.amazon.blueshift.bluefront.android.SpeechRequestListener
        public void onBluefrontResults(T t) {
        }

        @Override // android.speech.RecognitionListener
        public void onBufferReceived(byte[] bArr) {
        }

        @Override // com.amazon.blueshift.bluefront.android.SpeechRequestListener, android.speech.RecognitionListener
        public void onEndOfSpeech() {
        }

        @Override // android.speech.RecognitionListener
        public void onError(int i) {
        }

        @Override // com.amazon.blueshift.bluefront.android.SpeechRequestListener
        public void onError(SpeechClientException speechClientException) {
        }

        @Override // android.speech.RecognitionListener
        public void onEvent(int i, Bundle bundle) {
        }

        @Override // com.amazon.blueshift.bluefront.android.SpeechRequestListener
        public void onMaxSpeechTimeout() {
        }

        @Override // com.amazon.blueshift.bluefront.android.SpeechRequestListener
        public void onNoSpeechTimeout() {
        }

        @Override // android.speech.RecognitionListener
        public void onPartialResults(Bundle bundle) {
        }

        @Override // android.speech.RecognitionListener
        public void onReadyForSpeech(Bundle bundle) {
        }

        @Override // android.speech.RecognitionListener
        public void onResults(Bundle bundle) {
        }

        @Override // android.speech.RecognitionListener
        public void onRmsChanged(float f) {
        }

        @Override // com.amazon.blueshift.bluefront.android.SpeechRequestListener
        public void onSilenceDetected() {
        }
    }

    void onBluefrontResults(T t);

    @Override // android.speech.RecognitionListener
    void onEndOfSpeech();

    void onError(SpeechClientException speechClientException);

    void onMaxSpeechTimeout();

    void onNoSpeechTimeout();

    void onSilenceDetected();
}
