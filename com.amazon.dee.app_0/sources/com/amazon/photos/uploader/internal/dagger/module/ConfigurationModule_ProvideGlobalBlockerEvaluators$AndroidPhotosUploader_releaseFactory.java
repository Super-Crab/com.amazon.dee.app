package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.blockers.BackoffBlockerEvaluator;
import com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator;
import com.amazon.photos.uploader.blockers.NetworkGlobalBlockerEvaluator;
import com.amazon.photos.uploader.blockers.PauseGlobalBlockerEvaluator;
import com.amazon.photos.uploader.blockers.unauthorizedaccess.UnauthorizedAccessBlockerEvaluator;
import com.amazon.photos.uploader.internal.device.PermissionBlockerEvaluator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideGlobalBlockerEvaluators$AndroidPhotosUploader_releaseFactory implements Factory<Set<GlobalBlockerEvaluator>> {
    private final Provider<BackoffBlockerEvaluator> backoffBlockerEvaluatorProvider;
    private final ConfigurationModule module;
    private final Provider<NetworkGlobalBlockerEvaluator> networkGlobalBlockerEvaluatorProvider;
    private final Provider<PauseGlobalBlockerEvaluator> pauseResumeGlobalBlockerEvaluatorProvider;
    private final Provider<PermissionBlockerEvaluator> permissionBlockerEvaluatorProvider;
    private final Provider<UnauthorizedAccessBlockerEvaluator> unauthorizedAccessBlockerEvaluatorProvider;

    public ConfigurationModule_ProvideGlobalBlockerEvaluators$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<PauseGlobalBlockerEvaluator> provider, Provider<BackoffBlockerEvaluator> provider2, Provider<NetworkGlobalBlockerEvaluator> provider3, Provider<PermissionBlockerEvaluator> provider4, Provider<UnauthorizedAccessBlockerEvaluator> provider5) {
        this.module = configurationModule;
        this.pauseResumeGlobalBlockerEvaluatorProvider = provider;
        this.backoffBlockerEvaluatorProvider = provider2;
        this.networkGlobalBlockerEvaluatorProvider = provider3;
        this.permissionBlockerEvaluatorProvider = provider4;
        this.unauthorizedAccessBlockerEvaluatorProvider = provider5;
    }

    public static ConfigurationModule_ProvideGlobalBlockerEvaluators$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<PauseGlobalBlockerEvaluator> provider, Provider<BackoffBlockerEvaluator> provider2, Provider<NetworkGlobalBlockerEvaluator> provider3, Provider<PermissionBlockerEvaluator> provider4, Provider<UnauthorizedAccessBlockerEvaluator> provider5) {
        return new ConfigurationModule_ProvideGlobalBlockerEvaluators$AndroidPhotosUploader_releaseFactory(configurationModule, provider, provider2, provider3, provider4, provider5);
    }

    public static Set<GlobalBlockerEvaluator> provideGlobalBlockerEvaluators$AndroidPhotosUploader_release(ConfigurationModule configurationModule, PauseGlobalBlockerEvaluator pauseGlobalBlockerEvaluator, BackoffBlockerEvaluator backoffBlockerEvaluator, NetworkGlobalBlockerEvaluator networkGlobalBlockerEvaluator, PermissionBlockerEvaluator permissionBlockerEvaluator, UnauthorizedAccessBlockerEvaluator unauthorizedAccessBlockerEvaluator) {
        return (Set) Preconditions.checkNotNull(configurationModule.provideGlobalBlockerEvaluators$AndroidPhotosUploader_release(pauseGlobalBlockerEvaluator, backoffBlockerEvaluator, networkGlobalBlockerEvaluator, permissionBlockerEvaluator, unauthorizedAccessBlockerEvaluator), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public Set<GlobalBlockerEvaluator> mo10268get() {
        return provideGlobalBlockerEvaluators$AndroidPhotosUploader_release(this.module, this.pauseResumeGlobalBlockerEvaluatorProvider.mo10268get(), this.backoffBlockerEvaluatorProvider.mo10268get(), this.networkGlobalBlockerEvaluatorProvider.mo10268get(), this.permissionBlockerEvaluatorProvider.mo10268get(), this.unauthorizedAccessBlockerEvaluatorProvider.mo10268get());
    }
}
