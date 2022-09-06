package com.amazon.alexa.voice.wakeword;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
/* loaded from: classes11.dex */
public class AppFocusWakeWordPrecondition implements ObservableWakeWordPrecondition {
    private final AppFocusObserver appFocusObserver;

    /* loaded from: classes11.dex */
    static class AppFocusObserver implements ApplicationLifecycleObserver {
        private final BehaviorSubject<Boolean> focusState = BehaviorSubject.createDefault(false);

        AppFocusObserver() {
        }

        BehaviorSubject<Boolean> getFocusState() {
            return this.focusState;
        }

        @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver
        public void onStart() {
            this.focusState.onNext(true);
        }

        @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver
        public void onStop() {
            this.focusState.onNext(false);
        }
    }

    public AppFocusWakeWordPrecondition(ApplicationLifecycleService applicationLifecycleService) {
        this(applicationLifecycleService, new AppFocusObserver());
    }

    @Override // com.amazon.alexa.voice.wakeword.ObservableWakeWordPrecondition
    public Observable<Boolean> isWakeWordAllowed() {
        return this.appFocusObserver.getFocusState();
    }

    @VisibleForTesting
    AppFocusWakeWordPrecondition(ApplicationLifecycleService applicationLifecycleService, AppFocusObserver appFocusObserver) {
        this.appFocusObserver = appFocusObserver;
        applicationLifecycleService.addObserver(appFocusObserver);
    }
}
