package com.amazon.tarazed.dagger.modules;

import androidx.work.WorkManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class LibraryModule_ProvideWorkManagerFactory implements Factory<WorkManager> {
    private final LibraryModule module;

    public LibraryModule_ProvideWorkManagerFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvideWorkManagerFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvideWorkManagerFactory(libraryModule);
    }

    public static WorkManager provideInstance(LibraryModule libraryModule) {
        return proxyProvideWorkManager(libraryModule);
    }

    public static WorkManager proxyProvideWorkManager(LibraryModule libraryModule) {
        return (WorkManager) Preconditions.checkNotNull(libraryModule.provideWorkManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WorkManager mo10268get() {
        return provideInstance(this.module);
    }
}
