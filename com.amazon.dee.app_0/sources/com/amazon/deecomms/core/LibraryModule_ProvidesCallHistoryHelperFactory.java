package com.amazon.deecomms.core;

import com.amazon.deecomms.calling.util.CallHistoryHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCallHistoryHelperFactory implements Factory<CallHistoryHelper> {
    private final LibraryModule module;

    public LibraryModule_ProvidesCallHistoryHelperFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesCallHistoryHelperFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesCallHistoryHelperFactory(libraryModule);
    }

    public static CallHistoryHelper provideInstance(LibraryModule libraryModule) {
        return (CallHistoryHelper) Preconditions.checkNotNull(libraryModule.providesCallHistoryHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CallHistoryHelper proxyProvidesCallHistoryHelper(LibraryModule libraryModule) {
        return (CallHistoryHelper) Preconditions.checkNotNull(libraryModule.providesCallHistoryHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallHistoryHelper mo10268get() {
        return provideInstance(this.module);
    }
}
