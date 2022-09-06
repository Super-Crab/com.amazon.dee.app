package com.amazon.deecomms.core;

import com.amazon.deecomms.common.ui.helper.ActivitiesManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesActivitiesManagerFactory implements Factory<ActivitiesManager> {
    private final LibraryModule module;

    public LibraryModule_ProvidesActivitiesManagerFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesActivitiesManagerFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesActivitiesManagerFactory(libraryModule);
    }

    public static ActivitiesManager provideInstance(LibraryModule libraryModule) {
        return (ActivitiesManager) Preconditions.checkNotNull(libraryModule.providesActivitiesManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ActivitiesManager proxyProvidesActivitiesManager(LibraryModule libraryModule) {
        return (ActivitiesManager) Preconditions.checkNotNull(libraryModule.providesActivitiesManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ActivitiesManager mo10268get() {
        return provideInstance(this.module);
    }
}
