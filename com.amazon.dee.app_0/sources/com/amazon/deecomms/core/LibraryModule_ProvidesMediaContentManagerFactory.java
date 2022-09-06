package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.media.photos.MediaContentManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesMediaContentManagerFactory implements Factory<MediaContentManager> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesMediaContentManagerFactory(LibraryModule libraryModule, Provider<Context> provider) {
        this.module = libraryModule;
        this.contextProvider = provider;
    }

    public static LibraryModule_ProvidesMediaContentManagerFactory create(LibraryModule libraryModule, Provider<Context> provider) {
        return new LibraryModule_ProvidesMediaContentManagerFactory(libraryModule, provider);
    }

    public static MediaContentManager provideInstance(LibraryModule libraryModule, Provider<Context> provider) {
        return (MediaContentManager) Preconditions.checkNotNull(libraryModule.providesMediaContentManager(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static MediaContentManager proxyProvidesMediaContentManager(LibraryModule libraryModule, Context context) {
        return (MediaContentManager) Preconditions.checkNotNull(libraryModule.providesMediaContentManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MediaContentManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
