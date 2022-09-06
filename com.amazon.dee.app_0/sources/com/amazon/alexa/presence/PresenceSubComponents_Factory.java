package com.amazon.alexa.presence;

import android.content.Context;
import com.amazon.alexa.presence.alarm.PresenceAlarmManager;
import com.amazon.alexa.presence.receiver.AlexaPresenceBluetoothReceiver;
import com.amazon.alexa.presence.receiver.ScanCheckAlarmReceiver;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceSubComponents_Factory implements Factory<PresenceSubComponents> {
    private final Provider<AlexaPresenceBluetoothReceiver> alexaPresenceBluetoothReceiverProvider;
    private final Provider<ApplicationLifecycleService> applicationLifecycleServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<PresenceAlarmManager> presenceAlarmManagerProvider;
    private final Provider<PresenceApplicationLifecycleObserver> presenceApplicationLifecycleObserverProvider;
    private final Provider<ScanCheckAlarmReceiver> scanCheckAlarmReceiverProvider;

    public PresenceSubComponents_Factory(Provider<ApplicationLifecycleService> provider, Provider<PresenceApplicationLifecycleObserver> provider2, Provider<AlexaPresenceBluetoothReceiver> provider3, Provider<PresenceAlarmManager> provider4, Provider<ScanCheckAlarmReceiver> provider5, Provider<Context> provider6) {
        this.applicationLifecycleServiceProvider = provider;
        this.presenceApplicationLifecycleObserverProvider = provider2;
        this.alexaPresenceBluetoothReceiverProvider = provider3;
        this.presenceAlarmManagerProvider = provider4;
        this.scanCheckAlarmReceiverProvider = provider5;
        this.contextProvider = provider6;
    }

    public static PresenceSubComponents_Factory create(Provider<ApplicationLifecycleService> provider, Provider<PresenceApplicationLifecycleObserver> provider2, Provider<AlexaPresenceBluetoothReceiver> provider3, Provider<PresenceAlarmManager> provider4, Provider<ScanCheckAlarmReceiver> provider5, Provider<Context> provider6) {
        return new PresenceSubComponents_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static PresenceSubComponents newPresenceSubComponents(Lazy<ApplicationLifecycleService> lazy, Lazy<PresenceApplicationLifecycleObserver> lazy2, Lazy<AlexaPresenceBluetoothReceiver> lazy3, Lazy<PresenceAlarmManager> lazy4, Lazy<ScanCheckAlarmReceiver> lazy5, Context context) {
        return new PresenceSubComponents(lazy, lazy2, lazy3, lazy4, lazy5, context);
    }

    public static PresenceSubComponents provideInstance(Provider<ApplicationLifecycleService> provider, Provider<PresenceApplicationLifecycleObserver> provider2, Provider<AlexaPresenceBluetoothReceiver> provider3, Provider<PresenceAlarmManager> provider4, Provider<ScanCheckAlarmReceiver> provider5, Provider<Context> provider6) {
        return new PresenceSubComponents(DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), provider6.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PresenceSubComponents mo10268get() {
        return provideInstance(this.applicationLifecycleServiceProvider, this.presenceApplicationLifecycleObserverProvider, this.alexaPresenceBluetoothReceiverProvider, this.presenceAlarmManagerProvider, this.scanCheckAlarmReceiverProvider, this.contextProvider);
    }
}
