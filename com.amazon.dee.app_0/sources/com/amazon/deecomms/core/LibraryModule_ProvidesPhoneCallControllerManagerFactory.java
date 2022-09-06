package com.amazon.deecomms.core;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.deecomms.alexa.CommsAudioInteractionScheduler;
import com.amazon.deecomms.alexa.unsent.event.pcc.PCCQueuedEvents;
import com.amazon.deecomms.calling.phonecallcontroller.CallStateListener;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.calling.phonecallcontroller.PhoneCallControllerManager;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesPhoneCallControllerManagerFactory implements Factory<PhoneCallControllerManager> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<CallStateListener> callStateListenerProvider;
    private final Provider<CommsAudioInteractionScheduler> commsAudioInteractionSchedulerProvider;
    private final Provider<Context> contextProvider;
    private final LibraryModule module;
    private final Provider<PCCContextProvider> pccContextProvider;
    private final Provider<PCCQueuedEvents> pccUnsentEventsManagerProvider;
    private final Provider<TelephonyManager> telephonyManagerProvider;

    public LibraryModule_ProvidesPhoneCallControllerManagerFactory(LibraryModule libraryModule, Provider<AlexaServicesConnection> provider, Provider<TelephonyManager> provider2, Provider<CallStateListener> provider3, Provider<Context> provider4, Provider<PCCContextProvider> provider5, Provider<PCCQueuedEvents> provider6, Provider<CommsAudioInteractionScheduler> provider7) {
        this.module = libraryModule;
        this.alexaServicesConnectionProvider = provider;
        this.telephonyManagerProvider = provider2;
        this.callStateListenerProvider = provider3;
        this.contextProvider = provider4;
        this.pccContextProvider = provider5;
        this.pccUnsentEventsManagerProvider = provider6;
        this.commsAudioInteractionSchedulerProvider = provider7;
    }

    public static LibraryModule_ProvidesPhoneCallControllerManagerFactory create(LibraryModule libraryModule, Provider<AlexaServicesConnection> provider, Provider<TelephonyManager> provider2, Provider<CallStateListener> provider3, Provider<Context> provider4, Provider<PCCContextProvider> provider5, Provider<PCCQueuedEvents> provider6, Provider<CommsAudioInteractionScheduler> provider7) {
        return new LibraryModule_ProvidesPhoneCallControllerManagerFactory(libraryModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static PhoneCallControllerManager provideInstance(LibraryModule libraryModule, Provider<AlexaServicesConnection> provider, Provider<TelephonyManager> provider2, Provider<CallStateListener> provider3, Provider<Context> provider4, Provider<PCCContextProvider> provider5, Provider<PCCQueuedEvents> provider6, Provider<CommsAudioInteractionScheduler> provider7) {
        return (PhoneCallControllerManager) Preconditions.checkNotNull(libraryModule.providesPhoneCallControllerManager(DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7)), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static PhoneCallControllerManager proxyProvidesPhoneCallControllerManager(LibraryModule libraryModule, Lazy<AlexaServicesConnection> lazy, Lazy<TelephonyManager> lazy2, Lazy<CallStateListener> lazy3, Lazy<Context> lazy4, Lazy<PCCContextProvider> lazy5, Lazy<PCCQueuedEvents> lazy6, Lazy<CommsAudioInteractionScheduler> lazy7) {
        return (PhoneCallControllerManager) Preconditions.checkNotNull(libraryModule.providesPhoneCallControllerManager(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PhoneCallControllerManager mo10268get() {
        return provideInstance(this.module, this.alexaServicesConnectionProvider, this.telephonyManagerProvider, this.callStateListenerProvider, this.contextProvider, this.pccContextProvider, this.pccUnsentEventsManagerProvider, this.commsAudioInteractionSchedulerProvider);
    }
}
