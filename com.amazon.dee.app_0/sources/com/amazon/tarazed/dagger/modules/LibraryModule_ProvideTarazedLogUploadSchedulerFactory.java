package com.amazon.tarazed.dagger.modules;

import androidx.work.WorkManager;
import com.amazon.tarazed.core.logging.TarazedLogger;
import com.amazon.tarazed.worker.logging.upload.TarazedLogUploadScheduler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class LibraryModule_ProvideTarazedLogUploadSchedulerFactory implements Factory<TarazedLogUploadScheduler> {
    private final Provider<TarazedLogger> loggerProvider;
    private final LibraryModule module;
    private final Provider<WorkManager> workManagerProvider;

    public LibraryModule_ProvideTarazedLogUploadSchedulerFactory(LibraryModule libraryModule, Provider<WorkManager> provider, Provider<TarazedLogger> provider2) {
        this.module = libraryModule;
        this.workManagerProvider = provider;
        this.loggerProvider = provider2;
    }

    public static LibraryModule_ProvideTarazedLogUploadSchedulerFactory create(LibraryModule libraryModule, Provider<WorkManager> provider, Provider<TarazedLogger> provider2) {
        return new LibraryModule_ProvideTarazedLogUploadSchedulerFactory(libraryModule, provider, provider2);
    }

    public static TarazedLogUploadScheduler provideInstance(LibraryModule libraryModule, Provider<WorkManager> provider, Provider<TarazedLogger> provider2) {
        return proxyProvideTarazedLogUploadScheduler(libraryModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static TarazedLogUploadScheduler proxyProvideTarazedLogUploadScheduler(LibraryModule libraryModule, WorkManager workManager, TarazedLogger tarazedLogger) {
        return (TarazedLogUploadScheduler) Preconditions.checkNotNull(libraryModule.provideTarazedLogUploadScheduler(workManager, tarazedLogger), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TarazedLogUploadScheduler mo10268get() {
        return provideInstance(this.module, this.workManagerProvider, this.loggerProvider);
    }
}
