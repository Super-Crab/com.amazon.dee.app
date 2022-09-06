package com.amazon.deecomms.core;

import com.amazon.deecomms.alexa.CommsAudioInteraction;
import com.amazon.deecomms.alexa.CommsAudioInteractionScheduler;
import com.amazon.deecomms.alexa.CommsEventSender;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCommsEventSenderFactory implements Factory<CommsEventSender> {
    private final Provider<CommsAudioInteraction> commsAudioInteractionProvider;
    private final Provider<CommsAudioInteractionScheduler> commsAudioInteractionSchedulerProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesCommsEventSenderFactory(LibraryModule libraryModule, Provider<CommsAudioInteraction> provider, Provider<CommsAudioInteractionScheduler> provider2) {
        this.module = libraryModule;
        this.commsAudioInteractionProvider = provider;
        this.commsAudioInteractionSchedulerProvider = provider2;
    }

    public static LibraryModule_ProvidesCommsEventSenderFactory create(LibraryModule libraryModule, Provider<CommsAudioInteraction> provider, Provider<CommsAudioInteractionScheduler> provider2) {
        return new LibraryModule_ProvidesCommsEventSenderFactory(libraryModule, provider, provider2);
    }

    public static CommsEventSender provideInstance(LibraryModule libraryModule, Provider<CommsAudioInteraction> provider, Provider<CommsAudioInteractionScheduler> provider2) {
        return (CommsEventSender) Preconditions.checkNotNull(libraryModule.providesCommsEventSender(provider.mo10268get(), provider2.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CommsEventSender proxyProvidesCommsEventSender(LibraryModule libraryModule, CommsAudioInteraction commsAudioInteraction, CommsAudioInteractionScheduler commsAudioInteractionScheduler) {
        return (CommsEventSender) Preconditions.checkNotNull(libraryModule.providesCommsEventSender(commsAudioInteraction, commsAudioInteractionScheduler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsEventSender mo10268get() {
        return provideInstance(this.module, this.commsAudioInteractionProvider, this.commsAudioInteractionSchedulerProvider);
    }
}
