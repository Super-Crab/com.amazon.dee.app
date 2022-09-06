package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.messaging.util.UnreadMessageCounter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesUnreadMessageCounterFactory implements Factory<UnreadMessageCounter> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesUnreadMessageCounterFactory(LibraryModule libraryModule, Provider<Context> provider) {
        this.module = libraryModule;
        this.contextProvider = provider;
    }

    public static LibraryModule_ProvidesUnreadMessageCounterFactory create(LibraryModule libraryModule, Provider<Context> provider) {
        return new LibraryModule_ProvidesUnreadMessageCounterFactory(libraryModule, provider);
    }

    public static UnreadMessageCounter provideInstance(LibraryModule libraryModule, Provider<Context> provider) {
        return (UnreadMessageCounter) Preconditions.checkNotNull(libraryModule.providesUnreadMessageCounter(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static UnreadMessageCounter proxyProvidesUnreadMessageCounter(LibraryModule libraryModule, Context context) {
        return (UnreadMessageCounter) Preconditions.checkNotNull(libraryModule.providesUnreadMessageCounter(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UnreadMessageCounter mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
