package com.amazon.alexa.voice.ui.onedesign.speech;
/* loaded from: classes11.dex */
public interface SpeechControlContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void cancel();

        void recognizeSpeech();

        boolean shouldEmbedVoiceIngress();
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void buttonClicked();

        void start();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void displayVoiceIngressButton(boolean z);
    }
}
