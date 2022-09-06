package com.amazon.alexa.voiceui.chrome;

import android.annotation.SuppressLint;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaUserInterfaceOptions;
import io.reactivex.rxjava3.functions.Consumer;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class VoiceChromePresenter {

    @VisibleForTesting
    /* loaded from: classes11.dex */
    static class EventListener implements VoiceChromeEventListener {
        private final VoiceChromeInteractor voiceChromeInteractor;

        @Override // com.amazon.alexa.voiceui.chrome.VoiceChromeEventListener
        public void onVoiceChromeDismissed() {
            this.voiceChromeInteractor.voiceChromeDismissed();
        }

        @Override // com.amazon.alexa.voiceui.chrome.VoiceChromeEventListener
        public void onVoiceChromeShown() {
            this.voiceChromeInteractor.recordVoiceChromeLatency();
        }

        private EventListener(VoiceChromeInteractor voiceChromeInteractor) {
            this.voiceChromeInteractor = voiceChromeInteractor;
        }
    }

    @Inject
    @SuppressLint({"CheckResult"})
    public VoiceChromePresenter(final VoiceChromeInteractor voiceChromeInteractor, final VoiceChromeView voiceChromeView) {
        voiceChromeView.addVoiceChromeEventListener(new EventListener(voiceChromeInteractor));
        voiceChromeInteractor.getUserInterfaceOptionsObservable().subscribe(new Consumer<AlexaUserInterfaceOptions>() { // from class: com.amazon.alexa.voiceui.chrome.VoiceChromePresenter.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(AlexaUserInterfaceOptions alexaUserInterfaceOptions) throws Exception {
                voiceChromeView.setAlexaUserInterfaceOptions(alexaUserInterfaceOptions);
            }
        });
        voiceChromeInteractor.onAlexaThinking().subscribe(new Consumer<Boolean>() { // from class: com.amazon.alexa.voiceui.chrome.VoiceChromePresenter.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                if (bool.booleanValue()) {
                    voiceChromeView.setHintsEnabled(false);
                }
            }
        });
        voiceChromeInteractor.onDriveModeEnabled().subscribe(new Consumer<Boolean>() { // from class: com.amazon.alexa.voiceui.chrome.VoiceChromePresenter.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                voiceChromeView.setTypingIngressEnabled(!bool.booleanValue() && voiceChromeInteractor.showTypingIngressOnLockScreen());
            }
        });
    }
}
