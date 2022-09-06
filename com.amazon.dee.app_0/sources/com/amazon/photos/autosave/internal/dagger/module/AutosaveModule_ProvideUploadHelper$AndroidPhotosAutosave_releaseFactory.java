package com.amazon.photos.autosave.internal.dagger.module;

import com.amazon.photos.autosave.internal.db.AutosaveTransactionRunner;
import com.amazon.photos.autosave.internal.upload.UploadHelper;
import com.amazon.photos.autosave.internal.utils.DateUtils;
import com.amazon.photos.autosave.internal.utils.SystemUtil;
import com.amazon.photos.discovery.Discovery;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvideUploadHelper$AndroidPhotosAutosave_releaseFactory implements Factory<UploadHelper> {
    private final Provider<DateUtils> dateUtilsProvider;
    private final Provider<Discovery> discoveryProvider;
    private final AutosaveModule module;
    private final Provider<SystemUtil> systemUtilProvider;
    private final Provider<AutosaveTransactionRunner> transactionRunnerProvider;

    public AutosaveModule_ProvideUploadHelper$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule, Provider<SystemUtil> provider, Provider<AutosaveTransactionRunner> provider2, Provider<Discovery> provider3, Provider<DateUtils> provider4) {
        this.module = autosaveModule;
        this.systemUtilProvider = provider;
        this.transactionRunnerProvider = provider2;
        this.discoveryProvider = provider3;
        this.dateUtilsProvider = provider4;
    }

    public static AutosaveModule_ProvideUploadHelper$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule, Provider<SystemUtil> provider, Provider<AutosaveTransactionRunner> provider2, Provider<Discovery> provider3, Provider<DateUtils> provider4) {
        return new AutosaveModule_ProvideUploadHelper$AndroidPhotosAutosave_releaseFactory(autosaveModule, provider, provider2, provider3, provider4);
    }

    public static UploadHelper provideUploadHelper$AndroidPhotosAutosave_release(AutosaveModule autosaveModule, SystemUtil systemUtil, AutosaveTransactionRunner autosaveTransactionRunner, Discovery discovery, DateUtils dateUtils) {
        return (UploadHelper) Preconditions.checkNotNull(autosaveModule.provideUploadHelper$AndroidPhotosAutosave_release(systemUtil, autosaveTransactionRunner, discovery, dateUtils), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UploadHelper mo10268get() {
        return provideUploadHelper$AndroidPhotosAutosave_release(this.module, this.systemUtilProvider.mo10268get(), this.transactionRunnerProvider.mo10268get(), this.discoveryProvider.mo10268get(), this.dateUtilsProvider.mo10268get());
    }
}
