package com.amazon.blueshift.bluefront.android.audio;
/* loaded from: classes11.dex */
public interface AudioSourceListener {

    /* loaded from: classes11.dex */
    public static class NullListener implements AudioSourceListener {
        @Override // com.amazon.blueshift.bluefront.android.audio.AudioSourceListener
        public void onBeginningOfSpeech() {
        }

        @Override // com.amazon.blueshift.bluefront.android.audio.AudioSourceListener
        public void onBufferReceived(byte[] bArr) {
        }

        @Override // com.amazon.blueshift.bluefront.android.audio.AudioSourceListener
        public void onMaxSpeechTimeout() {
        }

        @Override // com.amazon.blueshift.bluefront.android.audio.AudioSourceListener
        public void onNoSpeechTimeout() {
        }

        @Override // com.amazon.blueshift.bluefront.android.audio.AudioSourceListener
        public void onReadyForSpeech() {
        }

        @Override // com.amazon.blueshift.bluefront.android.audio.AudioSourceListener
        public void onRmsChanged(float f) {
        }

        @Override // com.amazon.blueshift.bluefront.android.audio.AudioSourceListener
        public void onSilenceDetected() {
        }
    }

    void onBeginningOfSpeech();

    void onBufferReceived(byte[] bArr);

    void onMaxSpeechTimeout();

    void onNoSpeechTimeout();

    void onReadyForSpeech();

    void onRmsChanged(float f);

    void onSilenceDetected();
}
