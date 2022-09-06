package com.amazon.deecomms.core;

import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerContextProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesMessagingControllerContextProviderFactory implements Factory<MessagingControllerContextProvider> {
    private final LibraryModule module;

    public LibraryModule_ProvidesMessagingControllerContextProviderFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesMessagingControllerContextProviderFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesMessagingControllerContextProviderFactory(libraryModule);
    }

    public static MessagingControllerContextProvider provideInstance(LibraryModule libraryModule) {
        return (MessagingControllerContextProvider) Preconditions.checkNotNull(libraryModule.providesMessagingControllerContextProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static MessagingControllerContextProvider proxyProvidesMessagingControllerContextProvider(LibraryModule libraryModule) {
        return (MessagingControllerContextProvider) Preconditions.checkNotNull(libraryModule.providesMessagingControllerContextProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MessagingControllerContextProvider mo10268get() {
        return provideInstance(this.module);
    }
}
