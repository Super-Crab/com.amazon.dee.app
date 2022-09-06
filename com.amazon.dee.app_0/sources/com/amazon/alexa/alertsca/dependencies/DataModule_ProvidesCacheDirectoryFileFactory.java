package com.amazon.alexa.alertsca.dependencies;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class DataModule_ProvidesCacheDirectoryFileFactory implements Factory<File> {
    private final Provider<Context> contextProvider;
    private final DataModule module;

    public DataModule_ProvidesCacheDirectoryFileFactory(DataModule dataModule, Provider<Context> provider) {
        this.module = dataModule;
        this.contextProvider = provider;
    }

    public static DataModule_ProvidesCacheDirectoryFileFactory create(DataModule dataModule, Provider<Context> provider) {
        return new DataModule_ProvidesCacheDirectoryFileFactory(dataModule, provider);
    }

    public static File provideInstance(DataModule dataModule, Provider<Context> provider) {
        return proxyProvidesCacheDirectoryFile(dataModule, provider.mo10268get());
    }

    public static File proxyProvidesCacheDirectoryFile(DataModule dataModule, Context context) {
        return (File) Preconditions.checkNotNull(dataModule.providesCacheDirectoryFile(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public File mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
