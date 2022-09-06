package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.messaging.tracking.ConversationMessageTracker;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesConversationMessageTrackerFactory implements Factory<ConversationMessageTracker> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesConversationMessageTrackerFactory(LibraryModule libraryModule, Provider<Context> provider) {
        this.module = libraryModule;
        this.contextProvider = provider;
    }

    public static LibraryModule_ProvidesConversationMessageTrackerFactory create(LibraryModule libraryModule, Provider<Context> provider) {
        return new LibraryModule_ProvidesConversationMessageTrackerFactory(libraryModule, provider);
    }

    public static ConversationMessageTracker provideInstance(LibraryModule libraryModule, Provider<Context> provider) {
        return (ConversationMessageTracker) Preconditions.checkNotNull(libraryModule.providesConversationMessageTracker(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ConversationMessageTracker proxyProvidesConversationMessageTracker(LibraryModule libraryModule, Context context) {
        return (ConversationMessageTracker) Preconditions.checkNotNull(libraryModule.providesConversationMessageTracker(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ConversationMessageTracker mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
