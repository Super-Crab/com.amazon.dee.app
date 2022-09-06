package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.calling.phonecallcontroller.MakeNativeCallHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesMakeNativeCallHandlerFactory implements Factory<MakeNativeCallHandler> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesMakeNativeCallHandlerFactory(LibraryModule libraryModule, Provider<Context> provider) {
        this.module = libraryModule;
        this.contextProvider = provider;
    }

    public static LibraryModule_ProvidesMakeNativeCallHandlerFactory create(LibraryModule libraryModule, Provider<Context> provider) {
        return new LibraryModule_ProvidesMakeNativeCallHandlerFactory(libraryModule, provider);
    }

    public static MakeNativeCallHandler provideInstance(LibraryModule libraryModule, Provider<Context> provider) {
        return (MakeNativeCallHandler) Preconditions.checkNotNull(libraryModule.providesMakeNativeCallHandler(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static MakeNativeCallHandler proxyProvidesMakeNativeCallHandler(LibraryModule libraryModule, Context context) {
        return (MakeNativeCallHandler) Preconditions.checkNotNull(libraryModule.providesMakeNativeCallHandler(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MakeNativeCallHandler mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
