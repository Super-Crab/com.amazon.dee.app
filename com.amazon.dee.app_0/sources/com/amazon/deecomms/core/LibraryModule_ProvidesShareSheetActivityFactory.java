package com.amazon.deecomms.core;

import com.amazon.deecomms.sharing.ShareSheetActivity;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesShareSheetActivityFactory implements Factory<ShareSheetActivity> {
    private final LibraryModule module;

    public LibraryModule_ProvidesShareSheetActivityFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesShareSheetActivityFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesShareSheetActivityFactory(libraryModule);
    }

    public static ShareSheetActivity provideInstance(LibraryModule libraryModule) {
        return (ShareSheetActivity) Preconditions.checkNotNull(libraryModule.providesShareSheetActivity(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ShareSheetActivity proxyProvidesShareSheetActivity(LibraryModule libraryModule) {
        return (ShareSheetActivity) Preconditions.checkNotNull(libraryModule.providesShareSheetActivity(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ShareSheetActivity mo10268get() {
        return provideInstance(this.module);
    }
}
