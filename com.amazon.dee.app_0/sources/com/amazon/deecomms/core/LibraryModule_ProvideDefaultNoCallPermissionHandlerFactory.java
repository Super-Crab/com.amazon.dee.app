package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.calling.phonecallcontroller.DefaultNoCallPermissionHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvideDefaultNoCallPermissionHandlerFactory implements Factory<DefaultNoCallPermissionHandler> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvideDefaultNoCallPermissionHandlerFactory(LibraryModule libraryModule, Provider<Context> provider) {
        this.module = libraryModule;
        this.contextProvider = provider;
    }

    public static LibraryModule_ProvideDefaultNoCallPermissionHandlerFactory create(LibraryModule libraryModule, Provider<Context> provider) {
        return new LibraryModule_ProvideDefaultNoCallPermissionHandlerFactory(libraryModule, provider);
    }

    public static DefaultNoCallPermissionHandler provideInstance(LibraryModule libraryModule, Provider<Context> provider) {
        return (DefaultNoCallPermissionHandler) Preconditions.checkNotNull(libraryModule.provideDefaultNoCallPermissionHandler(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static DefaultNoCallPermissionHandler proxyProvideDefaultNoCallPermissionHandler(LibraryModule libraryModule, Context context) {
        return (DefaultNoCallPermissionHandler) Preconditions.checkNotNull(libraryModule.provideDefaultNoCallPermissionHandler(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultNoCallPermissionHandler mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
