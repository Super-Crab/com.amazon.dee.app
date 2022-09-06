package com.amazon.alexa.voiceui.chrome;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaUserInterfaceOptions;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.voice.ui.onedesign.util.Properties;
import com.amazon.alexa.voice.ui.util.AlexaState;
import com.amazon.alexa.voiceui.BackButtonPressHandler;
import com.amazon.alexa.voiceui.SaveInstanceStateHandler;
import com.amazon.alexa.voiceui.driveMode.DriveModeStateProvider;
import com.amazon.alexa.voiceui.events.UiEventReporter;
import com.amazon.alexa.voiceui.lockscreen.LockscreenController;
import com.amazon.alexa.voiceui.voice.AlexaUserInterfaceOptionsTracker;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class VoiceChromeInteractor {
    private final Observable<AlexaState> alexaStateObservable;
    private final AlexaUserInterfaceOptionsTracker alexaUserInterfaceOptionsTracker;
    private final BackButtonPressHandler backButtonPressHandler;
    private final DriveModeStateProvider driveModeStateProvider;
    private final LockscreenController lockscreenController;
    private final SaveInstanceStateHandler saveInstanceStateHandler;
    private final UiEventReporter uiEventReporter;
    private final VoiceChromeModel voiceChromeModel;
    private final List<VoiceChromeDismissedListener> voiceChromeDismissedListeners = new ArrayList();
    private final BehaviorSubject<AlexaUserInterfaceOptions> userInterfaceOptionsObservable = BehaviorSubject.create();

    @Inject
    public VoiceChromeInteractor(VoiceChromeModel voiceChromeModel, UiEventReporter uiEventReporter, @Named("voiceChromeBackPressHandler") BackButtonPressHandler backButtonPressHandler, @Named("voiceChromeSaveInstanceState") SaveInstanceStateHandler saveInstanceStateHandler, AlexaUserInterfaceOptionsTracker alexaUserInterfaceOptionsTracker, DriveModeStateProvider driveModeStateProvider, LockscreenController lockscreenController) {
        this.voiceChromeModel = voiceChromeModel;
        this.uiEventReporter = uiEventReporter;
        this.backButtonPressHandler = backButtonPressHandler;
        this.saveInstanceStateHandler = saveInstanceStateHandler;
        this.alexaStateObservable = Properties.toObservable(voiceChromeModel.alexaState());
        this.alexaUserInterfaceOptionsTracker = alexaUserInterfaceOptionsTracker;
        this.driveModeStateProvider = driveModeStateProvider;
        this.lockscreenController = lockscreenController;
    }

    public boolean backButtonPressed() {
        return this.backButtonPressHandler.onBackButtonPressed();
    }

    public void deregisterForAlexaStateUpdates() {
        this.voiceChromeModel.release();
    }

    public Observable<AlexaUserInterfaceOptions> getUserInterfaceOptionsObservable() {
        return this.userInterfaceOptionsObservable;
    }

    public boolean isAlexaActive() {
        return this.voiceChromeModel.alexaState().get(AlexaState.IDLE) != AlexaState.IDLE;
    }

    public Observable<Boolean> onAlexaIdle() {
        return this.alexaStateObservable.map(new Function<AlexaState, Boolean>() { // from class: com.amazon.alexa.voiceui.chrome.VoiceChromeInteractor.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public Boolean mo10358apply(@NonNull AlexaState alexaState) throws Exception {
                return Boolean.valueOf(AlexaState.IDLE == alexaState);
            }
        }).distinctUntilChanged();
    }

    public Observable<Boolean> onAlexaListeningOrThinking() {
        return this.alexaStateObservable.map(new Function<AlexaState, Boolean>() { // from class: com.amazon.alexa.voiceui.chrome.VoiceChromeInteractor.3
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public Boolean mo10358apply(@NonNull AlexaState alexaState) throws Exception {
                return Boolean.valueOf(AlexaState.LISTENING == alexaState || AlexaState.THINKING == alexaState);
            }
        }).distinctUntilChanged();
    }

    public Observable<Boolean> onAlexaThinking() {
        return this.alexaStateObservable.map(new Function<AlexaState, Boolean>() { // from class: com.amazon.alexa.voiceui.chrome.VoiceChromeInteractor.2
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public Boolean mo10358apply(@NonNull AlexaState alexaState) throws Exception {
                return Boolean.valueOf(AlexaState.THINKING == alexaState);
            }
        }).distinctUntilChanged();
    }

    public Observable<Boolean> onDriveModeEnabled() {
        return this.driveModeStateProvider.onDriveModeEnabled();
    }

    public void recordVoiceChromeLatency() {
        this.uiEventReporter.sendEvent(UiEventName.ALEXA_UI_SHOWN, null);
    }

    public void registerForAlexaStateUpdates() {
        this.voiceChromeModel.initialize();
    }

    public void registerVoiceChromeDismissedListener(VoiceChromeDismissedListener voiceChromeDismissedListener) {
        synchronized (this.voiceChromeDismissedListeners) {
            this.voiceChromeDismissedListeners.add(voiceChromeDismissedListener);
        }
    }

    public void saveInstanceState(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        this.saveInstanceStateHandler.onSaveInstanceState(bundle2);
        bundle.putParcelable(VoiceChromeModule.VOICE_CHROME_ROUTER, bundle2);
    }

    public void setUserInterfaceOptions(AlexaUserInterfaceOptions alexaUserInterfaceOptions) {
        this.alexaUserInterfaceOptionsTracker.setAlexaUserInterfaceOptions(alexaUserInterfaceOptions);
        this.userInterfaceOptionsObservable.onNext(alexaUserInterfaceOptions);
    }

    public boolean showTypingIngressOnLockScreen() {
        return !this.lockscreenController.isOnLockScreen();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void voiceChromeDismissed() {
        synchronized (this.voiceChromeDismissedListeners) {
            for (VoiceChromeDismissedListener voiceChromeDismissedListener : this.voiceChromeDismissedListeners) {
                voiceChromeDismissedListener.onVoiceChromeDismissed();
            }
        }
    }
}
