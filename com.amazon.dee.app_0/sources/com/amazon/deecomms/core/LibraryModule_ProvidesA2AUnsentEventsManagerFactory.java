package com.amazon.deecomms.core;

import com.amazon.deecomms.alexa.unsent.event.a2a.A2AQueuedEvents;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesA2AUnsentEventsManagerFactory implements Factory<A2AQueuedEvents> {
    private final LibraryModule module;

    public LibraryModule_ProvidesA2AUnsentEventsManagerFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesA2AUnsentEventsManagerFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesA2AUnsentEventsManagerFactory(libraryModule);
    }

    public static A2AQueuedEvents provideInstance(LibraryModule libraryModule) {
        return (A2AQueuedEvents) Preconditions.checkNotNull(libraryModule.providesA2AUnsentEventsManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static A2AQueuedEvents proxyProvidesA2AUnsentEventsManager(LibraryModule libraryModule) {
        return (A2AQueuedEvents) Preconditions.checkNotNull(libraryModule.providesA2AUnsentEventsManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public A2AQueuedEvents mo10268get() {
        return provideInstance(this.module);
    }
}
