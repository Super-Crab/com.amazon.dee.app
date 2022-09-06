package com.amazon.alexa.voiceui.voice;

import android.app.Activity;
import com.amazon.alexa.voice.ui.speech.SpeechRecognizer;
import com.amazon.alexa.voiceui.AlexaServicesApis;
import com.amazon.alexa.voiceui.accessibility.AccessibilityHandler;
import com.amazon.alexa.voiceui.cards.CardInteractor;
import com.amazon.alexa.voiceui.chrome.VoiceChromeInteractor;
import com.amazon.alexa.voiceui.driveMode.DriveModeStateProvider;
import com.amazon.alexa.voiceui.events.UiEventReporter;
import com.amazon.alexa.voiceui.lockscreen.LockscreenController;
import com.amazon.alexa.voiceui.screen.ScreenLockManager;
import com.amazon.alexa.voiceui.window.WindowManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceInteractor_Factory implements Factory<VoiceInteractor> {
    private final Provider<AccessibilityHandler> accessibilityHandlerProvider;
    private final Provider<Activity> activityProvider;
    private final Provider<AlexaServicesApis> alexaServicesApisProvider;
    private final Provider<CardInteractor> cardInteractorProvider;
    private final Provider<DriveModeStateProvider> driveModeStateProvider;
    private final Provider<LockscreenController> lockscreenControllerProvider;
    private final Provider<ScreenLockManager> screenLockManagerProvider;
    private final Provider<SpeechRecognizer> speechRecognizerProvider;
    private final Provider<UiEventReporter> uiEventReporterProvider;
    private final Provider<VoiceChromeInteractor> voiceChromeInteractorProvider;
    private final Provider<WindowManager> windowManagerProvider;

    public VoiceInteractor_Factory(Provider<Activity> provider, Provider<AlexaServicesApis> provider2, Provider<LockscreenController> provider3, Provider<SpeechRecognizer> provider4, Provider<ScreenLockManager> provider5, Provider<VoiceChromeInteractor> provider6, Provider<CardInteractor> provider7, Provider<AccessibilityHandler> provider8, Provider<UiEventReporter> provider9, Provider<WindowManager> provider10, Provider<DriveModeStateProvider> provider11) {
        this.activityProvider = provider;
        this.alexaServicesApisProvider = provider2;
        this.lockscreenControllerProvider = provider3;
        this.speechRecognizerProvider = provider4;
        this.screenLockManagerProvider = provider5;
        this.voiceChromeInteractorProvider = provider6;
        this.cardInteractorProvider = provider7;
        this.accessibilityHandlerProvider = provider8;
        this.uiEventReporterProvider = provider9;
        this.windowManagerProvider = provider10;
        this.driveModeStateProvider = provider11;
    }

    public static VoiceInteractor_Factory create(Provider<Activity> provider, Provider<AlexaServicesApis> provider2, Provider<LockscreenController> provider3, Provider<SpeechRecognizer> provider4, Provider<ScreenLockManager> provider5, Provider<VoiceChromeInteractor> provider6, Provider<CardInteractor> provider7, Provider<AccessibilityHandler> provider8, Provider<UiEventReporter> provider9, Provider<WindowManager> provider10, Provider<DriveModeStateProvider> provider11) {
        return new VoiceInteractor_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public static VoiceInteractor newVoiceInteractor(Activity activity, AlexaServicesApis alexaServicesApis, LockscreenController lockscreenController, SpeechRecognizer speechRecognizer, ScreenLockManager screenLockManager, VoiceChromeInteractor voiceChromeInteractor, CardInteractor cardInteractor, AccessibilityHandler accessibilityHandler, UiEventReporter uiEventReporter, WindowManager windowManager, DriveModeStateProvider driveModeStateProvider) {
        return new VoiceInteractor(activity, alexaServicesApis, lockscreenController, speechRecognizer, screenLockManager, voiceChromeInteractor, cardInteractor, accessibilityHandler, uiEventReporter, windowManager, driveModeStateProvider);
    }

    public static VoiceInteractor provideInstance(Provider<Activity> provider, Provider<AlexaServicesApis> provider2, Provider<LockscreenController> provider3, Provider<SpeechRecognizer> provider4, Provider<ScreenLockManager> provider5, Provider<VoiceChromeInteractor> provider6, Provider<CardInteractor> provider7, Provider<AccessibilityHandler> provider8, Provider<UiEventReporter> provider9, Provider<WindowManager> provider10, Provider<DriveModeStateProvider> provider11) {
        return new VoiceInteractor(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get(), provider11.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceInteractor mo10268get() {
        return provideInstance(this.activityProvider, this.alexaServicesApisProvider, this.lockscreenControllerProvider, this.speechRecognizerProvider, this.screenLockManagerProvider, this.voiceChromeInteractorProvider, this.cardInteractorProvider, this.accessibilityHandlerProvider, this.uiEventReporterProvider, this.windowManagerProvider, this.driveModeStateProvider);
    }
}
