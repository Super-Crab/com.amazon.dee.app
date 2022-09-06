package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.media.audio.AudioContentManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesAudioContentManagerFactory implements Factory<AudioContentManager> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesAudioContentManagerFactory(LibraryModule libraryModule, Provider<Context> provider) {
        this.module = libraryModule;
        this.contextProvider = provider;
    }

    public static LibraryModule_ProvidesAudioContentManagerFactory create(LibraryModule libraryModule, Provider<Context> provider) {
        return new LibraryModule_ProvidesAudioContentManagerFactory(libraryModule, provider);
    }

    public static AudioContentManager provideInstance(LibraryModule libraryModule, Provider<Context> provider) {
        return (AudioContentManager) Preconditions.checkNotNull(libraryModule.providesAudioContentManager(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static AudioContentManager proxyProvidesAudioContentManager(LibraryModule libraryModule, Context context) {
        return (AudioContentManager) Preconditions.checkNotNull(libraryModule.providesAudioContentManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AudioContentManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
