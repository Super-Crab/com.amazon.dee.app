package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.presence.PresenceApplicationLifecycleObserver;
import com.amazon.alexa.presence.PresenceSubComponents;
import com.amazon.alexa.presence.alarm.PresenceAlarmManager;
import com.amazon.alexa.presence.receiver.AlexaPresenceBluetoothReceiver;
import com.amazon.alexa.presence.receiver.ScanCheckAlarmReceiver;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvideComponentInitializationFactory implements Factory<PresenceSubComponents> {
    private final Provider<AlexaPresenceBluetoothReceiver> alexaPresenceBluetoothReceiverProvider;
    private final Provider<ApplicationLifecycleService> applicationLifecycleServiceProvider;
    private final PresenceModule module;
    private final Provider<PresenceAlarmManager> presenceAlarmManagerProvider;
    private final Provider<PresenceApplicationLifecycleObserver> presenceApplicationLifecycleObserverProvider;
    private final Provider<ScanCheckAlarmReceiver> scanCheckAlarmReceiverProvider;

    public PresenceModule_ProvideComponentInitializationFactory(PresenceModule presenceModule, Provider<ApplicationLifecycleService> provider, Provider<PresenceApplicationLifecycleObserver> provider2, Provider<AlexaPresenceBluetoothReceiver> provider3, Provider<PresenceAlarmManager> provider4, Provider<ScanCheckAlarmReceiver> provider5) {
        this.module = presenceModule;
        this.applicationLifecycleServiceProvider = provider;
        this.presenceApplicationLifecycleObserverProvider = provider2;
        this.alexaPresenceBluetoothReceiverProvider = provider3;
        this.presenceAlarmManagerProvider = provider4;
        this.scanCheckAlarmReceiverProvider = provider5;
    }

    public static PresenceModule_ProvideComponentInitializationFactory create(PresenceModule presenceModule, Provider<ApplicationLifecycleService> provider, Provider<PresenceApplicationLifecycleObserver> provider2, Provider<AlexaPresenceBluetoothReceiver> provider3, Provider<PresenceAlarmManager> provider4, Provider<ScanCheckAlarmReceiver> provider5) {
        return new PresenceModule_ProvideComponentInitializationFactory(presenceModule, provider, provider2, provider3, provider4, provider5);
    }

    public static PresenceSubComponents provideInstance(PresenceModule presenceModule, Provider<ApplicationLifecycleService> provider, Provider<PresenceApplicationLifecycleObserver> provider2, Provider<AlexaPresenceBluetoothReceiver> provider3, Provider<PresenceAlarmManager> provider4, Provider<ScanCheckAlarmReceiver> provider5) {
        return proxyProvideComponentInitialization(presenceModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5));
    }

    public static PresenceSubComponents proxyProvideComponentInitialization(PresenceModule presenceModule, Lazy<ApplicationLifecycleService> lazy, Lazy<PresenceApplicationLifecycleObserver> lazy2, Lazy<AlexaPresenceBluetoothReceiver> lazy3, Lazy<PresenceAlarmManager> lazy4, Lazy<ScanCheckAlarmReceiver> lazy5) {
        return (PresenceSubComponents) Preconditions.checkNotNull(presenceModule.provideComponentInitialization(lazy, lazy2, lazy3, lazy4, lazy5), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PresenceSubComponents mo10268get() {
        return provideInstance(this.module, this.applicationLifecycleServiceProvider, this.presenceApplicationLifecycleObserverProvider, this.alexaPresenceBluetoothReceiverProvider, this.presenceAlarmManagerProvider, this.scanCheckAlarmReceiverProvider);
    }
}
