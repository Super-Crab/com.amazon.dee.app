package com.amazon.alexa.voiceui.chrome;

import com.amazon.alexa.voiceui.BackButtonPressHandler;
import com.amazon.alexa.voiceui.SaveInstanceStateHandler;
import com.amazon.alexa.voiceui.driveMode.DriveModeStateProvider;
import com.amazon.alexa.voiceui.events.UiEventReporter;
import com.amazon.alexa.voiceui.lockscreen.LockscreenController;
import com.amazon.alexa.voiceui.voice.AlexaUserInterfaceOptionsTracker;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceChromeInteractor_Factory implements Factory<VoiceChromeInteractor> {
    private final Provider<AlexaUserInterfaceOptionsTracker> alexaUserInterfaceOptionsTrackerProvider;
    private final Provider<BackButtonPressHandler> backButtonPressHandlerProvider;
    private final Provider<DriveModeStateProvider> driveModeStateProvider;
    private final Provider<LockscreenController> lockscreenControllerProvider;
    private final Provider<SaveInstanceStateHandler> saveInstanceStateHandlerProvider;
    private final Provider<UiEventReporter> uiEventReporterProvider;
    private final Provider<VoiceChromeModel> voiceChromeModelProvider;

    public VoiceChromeInteractor_Factory(Provider<VoiceChromeModel> provider, Provider<UiEventReporter> provider2, Provider<BackButtonPressHandler> provider3, Provider<SaveInstanceStateHandler> provider4, Provider<AlexaUserInterfaceOptionsTracker> provider5, Provider<DriveModeStateProvider> provider6, Provider<LockscreenController> provider7) {
        this.voiceChromeModelProvider = provider;
        this.uiEventReporterProvider = provider2;
        this.backButtonPressHandlerProvider = provider3;
        this.saveInstanceStateHandlerProvider = provider4;
        this.alexaUserInterfaceOptionsTrackerProvider = provider5;
        this.driveModeStateProvider = provider6;
        this.lockscreenControllerProvider = provider7;
    }

    public static VoiceChromeInteractor_Factory create(Provider<VoiceChromeModel> provider, Provider<UiEventReporter> provider2, Provider<BackButtonPressHandler> provider3, Provider<SaveInstanceStateHandler> provider4, Provider<AlexaUserInterfaceOptionsTracker> provider5, Provider<DriveModeStateProvider> provider6, Provider<LockscreenController> provider7) {
        return new VoiceChromeInteractor_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static VoiceChromeInteractor newVoiceChromeInteractor(Object obj, UiEventReporter uiEventReporter, BackButtonPressHandler backButtonPressHandler, SaveInstanceStateHandler saveInstanceStateHandler, AlexaUserInterfaceOptionsTracker alexaUserInterfaceOptionsTracker, DriveModeStateProvider driveModeStateProvider, LockscreenController lockscreenController) {
        return new VoiceChromeInteractor((VoiceChromeModel) obj, uiEventReporter, backButtonPressHandler, saveInstanceStateHandler, alexaUserInterfaceOptionsTracker, driveModeStateProvider, lockscreenController);
    }

    public static VoiceChromeInteractor provideInstance(Provider<VoiceChromeModel> provider, Provider<UiEventReporter> provider2, Provider<BackButtonPressHandler> provider3, Provider<SaveInstanceStateHandler> provider4, Provider<AlexaUserInterfaceOptionsTracker> provider5, Provider<DriveModeStateProvider> provider6, Provider<LockscreenController> provider7) {
        return new VoiceChromeInteractor(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceChromeInteractor mo10268get() {
        return provideInstance(this.voiceChromeModelProvider, this.uiEventReporterProvider, this.backButtonPressHandlerProvider, this.saveInstanceStateHandlerProvider, this.alexaUserInterfaceOptionsTrackerProvider, this.driveModeStateProvider, this.lockscreenControllerProvider);
    }
}
