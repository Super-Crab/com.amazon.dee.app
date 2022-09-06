package com.amazon.deecomms.core;

import com.amazon.deecomms.util.TimeoutHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvideTimeoutHelperFactory implements Factory<TimeoutHelper> {
    private final LibraryModule module;

    public LibraryModule_ProvideTimeoutHelperFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvideTimeoutHelperFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvideTimeoutHelperFactory(libraryModule);
    }

    public static TimeoutHelper provideInstance(LibraryModule libraryModule) {
        return (TimeoutHelper) Preconditions.checkNotNull(libraryModule.provideTimeoutHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static TimeoutHelper proxyProvideTimeoutHelper(LibraryModule libraryModule) {
        return (TimeoutHelper) Preconditions.checkNotNull(libraryModule.provideTimeoutHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TimeoutHelper mo10268get() {
        return provideInstance(this.module);
    }
}
