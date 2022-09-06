package com.amazon.deecomms.core;

import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesPCCContextProviderFactory implements Factory<PCCContextProvider> {
    private final LibraryModule module;

    public LibraryModule_ProvidesPCCContextProviderFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesPCCContextProviderFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesPCCContextProviderFactory(libraryModule);
    }

    public static PCCContextProvider provideInstance(LibraryModule libraryModule) {
        return (PCCContextProvider) Preconditions.checkNotNull(libraryModule.providesPCCContextProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static PCCContextProvider proxyProvidesPCCContextProvider(LibraryModule libraryModule) {
        return (PCCContextProvider) Preconditions.checkNotNull(libraryModule.providesPCCContextProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PCCContextProvider mo10268get() {
        return provideInstance(this.module);
    }
}
