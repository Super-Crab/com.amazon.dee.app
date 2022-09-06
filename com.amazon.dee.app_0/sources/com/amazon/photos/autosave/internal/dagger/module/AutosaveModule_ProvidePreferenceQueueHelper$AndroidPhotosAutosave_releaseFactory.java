package com.amazon.photos.autosave.internal.dagger.module;

import android.content.SharedPreferences;
import com.amazon.photos.autosave.internal.AutosaveOperations;
import com.amazon.photos.autosave.internal.dao.AutosaveBucketDao;
import com.amazon.photos.autosave.internal.db.AutosaveTransactionRunner;
import com.amazon.photos.autosave.internal.preferences.PreferenceUploadQueueHelper;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.uploader.UploadManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvidePreferenceQueueHelper$AndroidPhotosAutosave_releaseFactory implements Factory<PreferenceUploadQueueHelper> {
    private final Provider<AutosaveBucketDao> autosaveBucketDaoProvider;
    private final Provider<AutosaveOperations> autosaveOperationsProvider;
    private final Provider<Discovery> discoveryProvider;
    private final AutosaveModule module;
    private final Provider<SharedPreferences> sharedPreferencesProvider;
    private final Provider<AutosaveTransactionRunner> transactionRunnerProvider;
    private final Provider<UploadManager> uploadManagerProvider;

    public AutosaveModule_ProvidePreferenceQueueHelper$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule, Provider<UploadManager> provider, Provider<AutosaveOperations> provider2, Provider<Discovery> provider3, Provider<AutosaveBucketDao> provider4, Provider<AutosaveTransactionRunner> provider5, Provider<SharedPreferences> provider6) {
        this.module = autosaveModule;
        this.uploadManagerProvider = provider;
        this.autosaveOperationsProvider = provider2;
        this.discoveryProvider = provider3;
        this.autosaveBucketDaoProvider = provider4;
        this.transactionRunnerProvider = provider5;
        this.sharedPreferencesProvider = provider6;
    }

    public static AutosaveModule_ProvidePreferenceQueueHelper$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule, Provider<UploadManager> provider, Provider<AutosaveOperations> provider2, Provider<Discovery> provider3, Provider<AutosaveBucketDao> provider4, Provider<AutosaveTransactionRunner> provider5, Provider<SharedPreferences> provider6) {
        return new AutosaveModule_ProvidePreferenceQueueHelper$AndroidPhotosAutosave_releaseFactory(autosaveModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static PreferenceUploadQueueHelper providePreferenceQueueHelper$AndroidPhotosAutosave_release(AutosaveModule autosaveModule, UploadManager uploadManager, AutosaveOperations autosaveOperations, Discovery discovery, AutosaveBucketDao autosaveBucketDao, AutosaveTransactionRunner autosaveTransactionRunner, SharedPreferences sharedPreferences) {
        return (PreferenceUploadQueueHelper) Preconditions.checkNotNull(autosaveModule.providePreferenceQueueHelper$AndroidPhotosAutosave_release(uploadManager, autosaveOperations, discovery, autosaveBucketDao, autosaveTransactionRunner, sharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PreferenceUploadQueueHelper mo10268get() {
        return providePreferenceQueueHelper$AndroidPhotosAutosave_release(this.module, this.uploadManagerProvider.mo10268get(), this.autosaveOperationsProvider.mo10268get(), this.discoveryProvider.mo10268get(), this.autosaveBucketDaoProvider.mo10268get(), this.transactionRunnerProvider.mo10268get(), this.sharedPreferencesProvider.mo10268get());
    }
}
