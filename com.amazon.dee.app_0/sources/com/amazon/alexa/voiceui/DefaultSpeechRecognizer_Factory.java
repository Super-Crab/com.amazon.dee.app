package com.amazon.alexa.voiceui;

import com.amazon.alexa.voice.ui.permissions.AndroidPermissionsChecker;
import com.amazon.alexa.voiceui.voice.AlexaUserInterfaceOptionsTracker;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class DefaultSpeechRecognizer_Factory implements Factory<DefaultSpeechRecognizer> {
    private final Provider<AlexaServicesApis> alexaServicesApisProvider;
    private final Provider<AlexaUserInterfaceOptionsTracker> alexaUserInterfaceOptionsTrackerProvider;
    private final Provider<AndroidPermissionsChecker> androidPermissionsCheckerProvider;

    public DefaultSpeechRecognizer_Factory(Provider<AndroidPermissionsChecker> provider, Provider<AlexaServicesApis> provider2, Provider<AlexaUserInterfaceOptionsTracker> provider3) {
        this.androidPermissionsCheckerProvider = provider;
        this.alexaServicesApisProvider = provider2;
        this.alexaUserInterfaceOptionsTrackerProvider = provider3;
    }

    public static DefaultSpeechRecognizer_Factory create(Provider<AndroidPermissionsChecker> provider, Provider<AlexaServicesApis> provider2, Provider<AlexaUserInterfaceOptionsTracker> provider3) {
        return new DefaultSpeechRecognizer_Factory(provider, provider2, provider3);
    }

    public static DefaultSpeechRecognizer newDefaultSpeechRecognizer(AndroidPermissionsChecker androidPermissionsChecker, AlexaServicesApis alexaServicesApis, AlexaUserInterfaceOptionsTracker alexaUserInterfaceOptionsTracker) {
        return new DefaultSpeechRecognizer(androidPermissionsChecker, alexaServicesApis, alexaUserInterfaceOptionsTracker);
    }

    public static DefaultSpeechRecognizer provideInstance(Provider<AndroidPermissionsChecker> provider, Provider<AlexaServicesApis> provider2, Provider<AlexaUserInterfaceOptionsTracker> provider3) {
        return new DefaultSpeechRecognizer(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultSpeechRecognizer mo10268get() {
        return provideInstance(this.androidPermissionsCheckerProvider, this.alexaServicesApisProvider, this.alexaUserInterfaceOptionsTrackerProvider);
    }
}
