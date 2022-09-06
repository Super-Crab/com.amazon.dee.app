package com.amazon.alexa.voice.wakeword;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.amazon.alexa.voice.wakeword.VoiceOverWakeWordPrecondition;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class VoiceOverWakeWordPrecondition implements ObservableWakeWordPrecondition {
    private static final String TAG = "VoiceOverWakeWordPrecondition";
    @VisibleForTesting
    VoiceOverChecker voiceOverChecker;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class VoiceOverChecker implements ApplicationLifecycleObserver {
        private static final long DELAY = 120;
        private final ApplicationLifecycleService applicationLifecycleService;
        @VisibleForTesting
        Disposable disposable;
        private final BehaviorSubject<Boolean> isEnabled = BehaviorSubject.createDefault(true);
        private final VoiceOverUtility voiceOverUtility;

        VoiceOverChecker(VoiceOverUtility voiceOverUtility, ApplicationLifecycleService applicationLifecycleService) {
            this.voiceOverUtility = voiceOverUtility;
            this.applicationLifecycleService = applicationLifecycleService;
            this.applicationLifecycleService.addObserver(this);
            scheduleVoiceOverSpeakingPuller();
        }

        void cancelVoiceOverSpeakingPuller() {
            Disposable disposable = this.disposable;
            if (disposable != null) {
                disposable.dispose();
                this.disposable = null;
            }
            this.isEnabled.onNext(true);
        }

        public /* synthetic */ void lambda$scheduleVoiceOverSpeakingPuller$0$VoiceOverWakeWordPrecondition$VoiceOverChecker(Long l) throws Throwable {
            if (!this.voiceOverUtility.isVoiceOverServiceEnabled()) {
                cancelVoiceOverSpeakingPuller();
            } else {
                this.isEnabled.onNext(Boolean.valueOf(shouldEnableWakeWord()));
            }
        }

        @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver
        public void onStart() {
            scheduleVoiceOverSpeakingPuller();
        }

        @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver
        public void onStop() {
            cancelVoiceOverSpeakingPuller();
        }

        @VisibleForTesting
        void scheduleVoiceOverSpeakingPuller() {
            if (this.voiceOverUtility.isVoiceOverServiceEnabled()) {
                Disposable disposable = this.disposable;
                if (disposable != null && !disposable.isDisposed()) {
                    return;
                }
                this.disposable = Observable.timer(DELAY, TimeUnit.MILLISECONDS).repeat().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.voice.wakeword.-$$Lambda$VoiceOverWakeWordPrecondition$VoiceOverChecker$IE6ajMs89InnPecs1-HRlQyVrJ0
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Object obj) {
                        VoiceOverWakeWordPrecondition.VoiceOverChecker.this.lambda$scheduleVoiceOverSpeakingPuller$0$VoiceOverWakeWordPrecondition$VoiceOverChecker((Long) obj);
                    }
                });
            }
        }

        public boolean shouldEnableWakeWord() {
            return !this.voiceOverUtility.isVoiceOverCurrentlySpeaking() || this.voiceOverUtility.isWiredHeadsetConnected() || this.voiceOverUtility.isExternalAudioDeviceConnected();
        }
    }

    public VoiceOverWakeWordPrecondition(VoiceOverUtility voiceOverUtility, ApplicationLifecycleService applicationLifecycleService) {
        this.voiceOverChecker = new VoiceOverChecker(voiceOverUtility, applicationLifecycleService);
    }

    @Override // com.amazon.alexa.voice.wakeword.ObservableWakeWordPrecondition
    public Observable<Boolean> isWakeWordAllowed() {
        return this.voiceOverChecker.isEnabled;
    }
}
