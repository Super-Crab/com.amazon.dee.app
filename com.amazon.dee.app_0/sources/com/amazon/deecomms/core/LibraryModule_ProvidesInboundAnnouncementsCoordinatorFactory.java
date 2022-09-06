package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.accessories.CommsAccessorySessionListener;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.common.metrics.MetricsService;
import com.amazon.deecomms.media.audio.AudioRecorder;
import com.amazon.deecomms.notifications.InboundAnnouncementCoordinator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesInboundAnnouncementsCoordinatorFactory implements Factory<InboundAnnouncementCoordinator> {
    private final Provider<AudioRecorder> audioRecorderProvider;
    private final Provider<CallManager> callManagerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsAccessorySessionListener> commsAccessorySessionListenerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesInboundAnnouncementsCoordinatorFactory(LibraryModule libraryModule, Provider<Context> provider, Provider<CapabilitiesManager> provider2, Provider<CommsAccessorySessionListener> provider3, Provider<CallManager> provider4, Provider<AudioRecorder> provider5, Provider<MetricsService> provider6, Provider<CommsIdentityManager> provider7) {
        this.module = libraryModule;
        this.contextProvider = provider;
        this.capabilitiesManagerProvider = provider2;
        this.commsAccessorySessionListenerProvider = provider3;
        this.callManagerProvider = provider4;
        this.audioRecorderProvider = provider5;
        this.metricsServiceProvider = provider6;
        this.commsIdentityManagerProvider = provider7;
    }

    public static LibraryModule_ProvidesInboundAnnouncementsCoordinatorFactory create(LibraryModule libraryModule, Provider<Context> provider, Provider<CapabilitiesManager> provider2, Provider<CommsAccessorySessionListener> provider3, Provider<CallManager> provider4, Provider<AudioRecorder> provider5, Provider<MetricsService> provider6, Provider<CommsIdentityManager> provider7) {
        return new LibraryModule_ProvidesInboundAnnouncementsCoordinatorFactory(libraryModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static InboundAnnouncementCoordinator provideInstance(LibraryModule libraryModule, Provider<Context> provider, Provider<CapabilitiesManager> provider2, Provider<CommsAccessorySessionListener> provider3, Provider<CallManager> provider4, Provider<AudioRecorder> provider5, Provider<MetricsService> provider6, Provider<CommsIdentityManager> provider7) {
        return (InboundAnnouncementCoordinator) Preconditions.checkNotNull(libraryModule.providesInboundAnnouncementsCoordinator(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static InboundAnnouncementCoordinator proxyProvidesInboundAnnouncementsCoordinator(LibraryModule libraryModule, Context context, CapabilitiesManager capabilitiesManager, CommsAccessorySessionListener commsAccessorySessionListener, CallManager callManager, AudioRecorder audioRecorder, MetricsService metricsService, CommsIdentityManager commsIdentityManager) {
        return (InboundAnnouncementCoordinator) Preconditions.checkNotNull(libraryModule.providesInboundAnnouncementsCoordinator(context, capabilitiesManager, commsAccessorySessionListener, callManager, audioRecorder, metricsService, commsIdentityManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public InboundAnnouncementCoordinator mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.capabilitiesManagerProvider, this.commsAccessorySessionListenerProvider, this.callManagerProvider, this.audioRecorderProvider, this.metricsServiceProvider, this.commsIdentityManagerProvider);
    }
}
