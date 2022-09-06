package com.amazon.photos.uploader.internal.workers;

import androidx.work.WorkManager;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.uploader.AbandonedRequestHandler;
import com.amazon.photos.uploader.FileSizeCategoryProvider;
import com.amazon.photos.uploader.Uploader;
import com.amazon.photos.uploader.internal.InternalEvaluator;
import com.amazon.photos.uploader.internal.NotificationUpdatesNotifier;
import com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier;
import com.amazon.photos.uploader.internal.UploadWorkerConfiguration;
import com.amazon.photos.uploader.internal.UploaderTransactionRunner;
import com.amazon.photos.uploader.internal.contentsignature.ContentSignatureProvider;
import com.amazon.photos.uploader.internal.dao.UploadRequestDao;
import com.amazon.photos.uploader.internal.utils.FileUtils;
import com.amazon.photos.uploader.internal.utils.PersistentLogger;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class UploadWorker_MembersInjector implements MembersInjector<UploadWorker> {
    private final Provider<UploadRequestDao> p0Provider;
    private final Provider<ContentSignatureProvider> p0Provider10;
    private final Provider<UploaderTransactionRunner> p0Provider11;
    private final Provider<SystemUtil> p0Provider12;
    private final Provider<FileUtils> p0Provider13;
    private final Provider<FileSizeCategoryProvider> p0Provider14;
    private final Provider<NotificationUpdatesNotifier> p0Provider15;
    private final Provider<Uploader> p0Provider2;
    private final Provider<WorkManager> p0Provider3;
    private final Provider<UploadRequestUpdatesNotifier> p0Provider4;
    private final Provider<Metrics> p0Provider5;
    private final Provider<PersistentLogger> p0Provider6;
    private final Provider<InternalEvaluator> p0Provider7;
    private final Provider<AbandonedRequestHandler> p0Provider8;
    private final Provider<UploadWorkerConfiguration> p0Provider9;

    public UploadWorker_MembersInjector(Provider<UploadRequestDao> provider, Provider<Uploader> provider2, Provider<WorkManager> provider3, Provider<UploadRequestUpdatesNotifier> provider4, Provider<Metrics> provider5, Provider<PersistentLogger> provider6, Provider<InternalEvaluator> provider7, Provider<AbandonedRequestHandler> provider8, Provider<UploadWorkerConfiguration> provider9, Provider<ContentSignatureProvider> provider10, Provider<UploaderTransactionRunner> provider11, Provider<SystemUtil> provider12, Provider<FileUtils> provider13, Provider<FileSizeCategoryProvider> provider14, Provider<NotificationUpdatesNotifier> provider15) {
        this.p0Provider = provider;
        this.p0Provider2 = provider2;
        this.p0Provider3 = provider3;
        this.p0Provider4 = provider4;
        this.p0Provider5 = provider5;
        this.p0Provider6 = provider6;
        this.p0Provider7 = provider7;
        this.p0Provider8 = provider8;
        this.p0Provider9 = provider9;
        this.p0Provider10 = provider10;
        this.p0Provider11 = provider11;
        this.p0Provider12 = provider12;
        this.p0Provider13 = provider13;
        this.p0Provider14 = provider14;
        this.p0Provider15 = provider15;
    }

    public static MembersInjector<UploadWorker> create(Provider<UploadRequestDao> provider, Provider<Uploader> provider2, Provider<WorkManager> provider3, Provider<UploadRequestUpdatesNotifier> provider4, Provider<Metrics> provider5, Provider<PersistentLogger> provider6, Provider<InternalEvaluator> provider7, Provider<AbandonedRequestHandler> provider8, Provider<UploadWorkerConfiguration> provider9, Provider<ContentSignatureProvider> provider10, Provider<UploaderTransactionRunner> provider11, Provider<SystemUtil> provider12, Provider<FileUtils> provider13, Provider<FileSizeCategoryProvider> provider14, Provider<NotificationUpdatesNotifier> provider15) {
        return new UploadWorker_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15);
    }

    public static void injectSetAbandonedRequestHandler(UploadWorker uploadWorker, AbandonedRequestHandler abandonedRequestHandler) {
        uploadWorker.setAbandonedRequestHandler(abandonedRequestHandler);
    }

    public static void injectSetContentSignatureProvider(UploadWorker uploadWorker, ContentSignatureProvider contentSignatureProvider) {
        uploadWorker.setContentSignatureProvider(contentSignatureProvider);
    }

    public static void injectSetFileSizeCategoryProvider(UploadWorker uploadWorker, FileSizeCategoryProvider fileSizeCategoryProvider) {
        uploadWorker.setFileSizeCategoryProvider(fileSizeCategoryProvider);
    }

    public static void injectSetFileUtils(UploadWorker uploadWorker, FileUtils fileUtils) {
        uploadWorker.setFileUtils(fileUtils);
    }

    public static void injectSetInternalEvaluator(UploadWorker uploadWorker, InternalEvaluator internalEvaluator) {
        uploadWorker.setInternalEvaluator(internalEvaluator);
    }

    public static void injectSetLogger(UploadWorker uploadWorker, PersistentLogger persistentLogger) {
        uploadWorker.setLogger(persistentLogger);
    }

    public static void injectSetMetrics(UploadWorker uploadWorker, Metrics metrics) {
        uploadWorker.setMetrics(metrics);
    }

    public static void injectSetRequestDao(UploadWorker uploadWorker, UploadRequestDao uploadRequestDao) {
        uploadWorker.setRequestDao(uploadRequestDao);
    }

    public static void injectSetSystemUtil(UploadWorker uploadWorker, SystemUtil systemUtil) {
        uploadWorker.setSystemUtil(systemUtil);
    }

    public static void injectSetTransactionRunner(UploadWorker uploadWorker, UploaderTransactionRunner uploaderTransactionRunner) {
        uploadWorker.setTransactionRunner(uploaderTransactionRunner);
    }

    public static void injectSetUploadRequestUpdatesNotifier(UploadWorker uploadWorker, UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier) {
        uploadWorker.setUploadRequestUpdatesNotifier(uploadRequestUpdatesNotifier);
    }

    public static void injectSetUploadWorkerConfiguration(UploadWorker uploadWorker, UploadWorkerConfiguration uploadWorkerConfiguration) {
        uploadWorker.setUploadWorkerConfiguration(uploadWorkerConfiguration);
    }

    public static void injectSetUploader(UploadWorker uploadWorker, Uploader uploader) {
        uploadWorker.setUploader(uploader);
    }

    public static void injectSetWorkManager(UploadWorker uploadWorker, WorkManager workManager) {
        uploadWorker.setWorkManager(workManager);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(UploadWorker uploadWorker) {
        injectSetRequestDao(uploadWorker, this.p0Provider.mo10268get());
        injectSetUploader(uploadWorker, this.p0Provider2.mo10268get());
        injectSetWorkManager(uploadWorker, this.p0Provider3.mo10268get());
        injectSetUploadRequestUpdatesNotifier(uploadWorker, this.p0Provider4.mo10268get());
        injectSetMetrics(uploadWorker, this.p0Provider5.mo10268get());
        injectSetLogger(uploadWorker, this.p0Provider6.mo10268get());
        injectSetInternalEvaluator(uploadWorker, this.p0Provider7.mo10268get());
        injectSetAbandonedRequestHandler(uploadWorker, this.p0Provider8.mo10268get());
        injectSetUploadWorkerConfiguration(uploadWorker, this.p0Provider9.mo10268get());
        injectSetContentSignatureProvider(uploadWorker, this.p0Provider10.mo10268get());
        injectSetTransactionRunner(uploadWorker, this.p0Provider11.mo10268get());
        injectSetSystemUtil(uploadWorker, this.p0Provider12.mo10268get());
        injectSetFileUtils(uploadWorker, this.p0Provider13.mo10268get());
        injectSetFileSizeCategoryProvider(uploadWorker, this.p0Provider14.mo10268get());
        uploadWorker.setNotificationUpdatesNotifier$AndroidPhotosUploader_release(this.p0Provider15.mo10268get());
    }
}
