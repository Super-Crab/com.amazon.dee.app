package com.amazon.deecomms.core;

import com.amazon.alexa.eventbus.api.EventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesEventBusFactory implements Factory<EventBus> {
    private final LibraryModule module;

    public LibraryModule_ProvidesEventBusFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesEventBusFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesEventBusFactory(libraryModule);
    }

    public static EventBus provideInstance(LibraryModule libraryModule) {
        return (EventBus) Preconditions.checkNotNull(libraryModule.providesEventBus(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static EventBus proxyProvidesEventBus(LibraryModule libraryModule) {
        return (EventBus) Preconditions.checkNotNull(libraryModule.providesEventBus(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBus mo10268get() {
        return provideInstance(this.module);
    }
}
