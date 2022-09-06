package com.amazon.deecomms.core;

import com.amazon.deecomms.oobe.util.ThemingUpdateUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesThemingUpdateUtilFactory implements Factory<ThemingUpdateUtil> {
    private final LibraryModule module;

    public LibraryModule_ProvidesThemingUpdateUtilFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesThemingUpdateUtilFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesThemingUpdateUtilFactory(libraryModule);
    }

    public static ThemingUpdateUtil provideInstance(LibraryModule libraryModule) {
        return (ThemingUpdateUtil) Preconditions.checkNotNull(libraryModule.providesThemingUpdateUtil(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ThemingUpdateUtil proxyProvidesThemingUpdateUtil(LibraryModule libraryModule) {
        return (ThemingUpdateUtil) Preconditions.checkNotNull(libraryModule.providesThemingUpdateUtil(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ThemingUpdateUtil mo10268get() {
        return provideInstance(this.module);
    }
}
