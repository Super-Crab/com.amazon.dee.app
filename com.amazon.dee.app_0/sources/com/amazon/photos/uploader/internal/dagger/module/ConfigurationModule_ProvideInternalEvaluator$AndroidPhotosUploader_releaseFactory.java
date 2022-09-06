package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.QueueManager;
import com.amazon.photos.uploader.blockers.BlockerEvaluatorProvider;
import com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator;
import com.amazon.photos.uploader.blockers.QueueBlockerEvaluator;
import com.amazon.photos.uploader.internal.InternalEvaluator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideInternalEvaluator$AndroidPhotosUploader_releaseFactory implements Factory<InternalEvaluator> {
    private final Provider<BlockerEvaluatorProvider> blockerEvaluatorProvider;
    private final Provider<Set<GlobalBlockerEvaluator>> globalBlockerEvaluatorsProvider;
    private final ConfigurationModule module;
    private final Provider<Set<QueueBlockerEvaluator>> queueBlockerEvaluatorsProvider;
    private final Provider<QueueManager> queueManagerProvider;

    public ConfigurationModule_ProvideInternalEvaluator$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<BlockerEvaluatorProvider> provider, Provider<QueueManager> provider2, Provider<Set<GlobalBlockerEvaluator>> provider3, Provider<Set<QueueBlockerEvaluator>> provider4) {
        this.module = configurationModule;
        this.blockerEvaluatorProvider = provider;
        this.queueManagerProvider = provider2;
        this.globalBlockerEvaluatorsProvider = provider3;
        this.queueBlockerEvaluatorsProvider = provider4;
    }

    public static ConfigurationModule_ProvideInternalEvaluator$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<BlockerEvaluatorProvider> provider, Provider<QueueManager> provider2, Provider<Set<GlobalBlockerEvaluator>> provider3, Provider<Set<QueueBlockerEvaluator>> provider4) {
        return new ConfigurationModule_ProvideInternalEvaluator$AndroidPhotosUploader_releaseFactory(configurationModule, provider, provider2, provider3, provider4);
    }

    public static InternalEvaluator provideInternalEvaluator$AndroidPhotosUploader_release(ConfigurationModule configurationModule, BlockerEvaluatorProvider blockerEvaluatorProvider, QueueManager queueManager, Set<GlobalBlockerEvaluator> set, Set<QueueBlockerEvaluator> set2) {
        return (InternalEvaluator) Preconditions.checkNotNull(configurationModule.provideInternalEvaluator$AndroidPhotosUploader_release(blockerEvaluatorProvider, queueManager, set, set2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public InternalEvaluator mo10268get() {
        return provideInternalEvaluator$AndroidPhotosUploader_release(this.module, this.blockerEvaluatorProvider.mo10268get(), this.queueManagerProvider.mo10268get(), this.globalBlockerEvaluatorsProvider.mo10268get(), this.queueBlockerEvaluatorsProvider.mo10268get());
    }
}
