package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.blockers.unauthorizedaccess.UnauthorizedAccessBlockerEvaluator;
import com.amazon.photos.uploader.blockers.unauthorizedaccess.UnauthorizedAccessPersistence;
import com.amazon.photos.uploader.observables.UploadRequestObservable;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideUnauthorizedAccessBlockerEvaluator$AndroidPhotosUploader_releaseFactory implements Factory<UnauthorizedAccessBlockerEvaluator> {
    private final ConfigurationModule module;
    private final Provider<UnauthorizedAccessPersistence> persistenceProvider;
    private final Provider<UploadRequestObservable> uploadRequestObservableProvider;

    public ConfigurationModule_ProvideUnauthorizedAccessBlockerEvaluator$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<UnauthorizedAccessPersistence> provider, Provider<UploadRequestObservable> provider2) {
        this.module = configurationModule;
        this.persistenceProvider = provider;
        this.uploadRequestObservableProvider = provider2;
    }

    public static ConfigurationModule_ProvideUnauthorizedAccessBlockerEvaluator$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<UnauthorizedAccessPersistence> provider, Provider<UploadRequestObservable> provider2) {
        return new ConfigurationModule_ProvideUnauthorizedAccessBlockerEvaluator$AndroidPhotosUploader_releaseFactory(configurationModule, provider, provider2);
    }

    public static UnauthorizedAccessBlockerEvaluator provideUnauthorizedAccessBlockerEvaluator$AndroidPhotosUploader_release(ConfigurationModule configurationModule, UnauthorizedAccessPersistence unauthorizedAccessPersistence, UploadRequestObservable uploadRequestObservable) {
        return (UnauthorizedAccessBlockerEvaluator) Preconditions.checkNotNull(configurationModule.provideUnauthorizedAccessBlockerEvaluator$AndroidPhotosUploader_release(unauthorizedAccessPersistence, uploadRequestObservable), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UnauthorizedAccessBlockerEvaluator mo10268get() {
        return provideUnauthorizedAccessBlockerEvaluator$AndroidPhotosUploader_release(this.module, this.persistenceProvider.mo10268get(), this.uploadRequestObservableProvider.mo10268get());
    }
}
