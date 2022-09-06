package com.amazon.photos.autosave.internal.workers;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.autosave.internal.dagger.AutosaveManagerMap;
import com.amazon.photos.autosave.internal.db.AutosaveTransactionRunner;
import com.amazon.photos.autosave.internal.metrics.AutosaveMetricEvents;
import com.amazon.photos.autosave.internal.metrics.MetricsHelper;
import com.amazon.photos.autosave.internal.upload.AutosaveUploadConfigurationProvider;
import com.amazon.photos.autosave.internal.upload.UploadHelper;
import com.amazon.photos.uploader.UploadManager;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadState;
import com.amazon.photos.uploader.UploaderOperations;
import com.amazon.photos.uploader.dao.RequestDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CancelAndRescheduleWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 12\u00020\u0001:\u00011B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010*\u001a\u00020\fH\u0014J\b\u0010+\u001a\u00020\nH\u0014J\b\u0010,\u001a\u00020-H\u0014J\u0011\u0010.\u001a\u00020/H\u0094@ø\u0001\u0000¢\u0006\u0002\u00100R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u0012@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\u0018@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR$\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u000b\u001a\u00020\u001e@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R$\u0010%\u001a\u00020$2\u0006\u0010\u000b\u001a\u00020$@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)\u0082\u0002\u0004\n\u0002\b\u0019¨\u00062"}, d2 = {"Lcom/amazon/photos/autosave/internal/workers/CancelAndRescheduleWorker;", "Lcom/amazon/photos/autosave/internal/workers/BaseWorker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "addToFamily", "", "hashedDirectedId", "", "<set-?>", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "metrics", "getMetrics", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "setMetrics", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "Lcom/amazon/photos/autosave/internal/metrics/MetricsHelper;", "metricsHelper", "getMetricsHelper", "()Lcom/amazon/photos/autosave/internal/metrics/MetricsHelper;", "setMetricsHelper", "(Lcom/amazon/photos/autosave/internal/metrics/MetricsHelper;)V", "Lcom/amazon/photos/autosave/internal/db/AutosaveTransactionRunner;", "transactionRunner", "getTransactionRunner", "()Lcom/amazon/photos/autosave/internal/db/AutosaveTransactionRunner;", "setTransactionRunner", "(Lcom/amazon/photos/autosave/internal/db/AutosaveTransactionRunner;)V", "Lcom/amazon/photos/autosave/internal/upload/UploadHelper;", "uploadHelper", "getUploadHelper", "()Lcom/amazon/photos/autosave/internal/upload/UploadHelper;", "setUploadHelper", "(Lcom/amazon/photos/autosave/internal/upload/UploadHelper;)V", "Lcom/amazon/photos/uploader/UploadManager;", "uploadManager", "getUploadManager", "()Lcom/amazon/photos/uploader/UploadManager;", "setUploadManager", "(Lcom/amazon/photos/uploader/UploadManager;)V", "getMetricsObj", "getTag", "injectMethod", "", "mainTask", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CancelAndRescheduleWorker extends BaseWorker {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "CancelAndRescheduleWorker";
    private final boolean addToFamily;
    private final String hashedDirectedId;
    @NotNull
    public Metrics metrics;
    @NotNull
    public MetricsHelper metricsHelper;
    @NotNull
    public AutosaveTransactionRunner transactionRunner;
    @NotNull
    public UploadHelper uploadHelper;
    @NotNull
    public UploadManager uploadManager;

    /* compiled from: CancelAndRescheduleWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/autosave/internal/workers/CancelAndRescheduleWorker$Companion;", "", "()V", "TAG", "", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CancelAndRescheduleWorker(@NotNull Context appContext, @NotNull WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        Intrinsics.checkParameterIsNotNull(workerParams, "workerParams");
        String string = workerParams.getInputData().getString("HASHED_DIRECTED_ID_KEY");
        if (string != null) {
            this.hashedDirectedId = string;
            this.addToFamily = workerParams.getInputData().getBoolean(CancelAndRescheduleWorkerKt.ADD_TO_FAMILY, false);
            return;
        }
        throw new IllegalArgumentException("No hashed directed id associated with worker.");
    }

    @NotNull
    public final Metrics getMetrics() {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        return metrics;
    }

    @NotNull
    public final MetricsHelper getMetricsHelper() {
        MetricsHelper metricsHelper = this.metricsHelper;
        if (metricsHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metricsHelper");
        }
        return metricsHelper;
    }

    @Override // com.amazon.photos.autosave.internal.workers.BaseWorker
    @NotNull
    protected Metrics getMetricsObj() {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        return metrics;
    }

    @Override // com.amazon.photos.autosave.internal.workers.BaseWorker
    @NotNull
    protected String getTag() {
        return TAG;
    }

    @NotNull
    public final AutosaveTransactionRunner getTransactionRunner() {
        AutosaveTransactionRunner autosaveTransactionRunner = this.transactionRunner;
        if (autosaveTransactionRunner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transactionRunner");
        }
        return autosaveTransactionRunner;
    }

    @NotNull
    public final UploadHelper getUploadHelper() {
        UploadHelper uploadHelper = this.uploadHelper;
        if (uploadHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadHelper");
        }
        return uploadHelper;
    }

    @NotNull
    public final UploadManager getUploadManager() {
        UploadManager uploadManager = this.uploadManager;
        if (uploadManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadManager");
        }
        return uploadManager;
    }

    @Override // com.amazon.photos.autosave.internal.workers.BaseWorker
    protected void injectMethod() {
        AutosaveManagerMap.INSTANCE.getAutosaveManagerForAccount$AndroidPhotosAutosave_release(this.hashedDirectedId).getComponent$AndroidPhotosAutosave_release().inject(this);
    }

    @Override // com.amazon.photos.autosave.internal.workers.BaseWorker
    @Nullable
    protected Object mainTask(@NotNull Continuation<? super ListenableWorker.Result> continuation) {
        AutosaveTransactionRunner autosaveTransactionRunner = this.transactionRunner;
        if (autosaveTransactionRunner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transactionRunner");
        }
        autosaveTransactionRunner.runInTransaction$AndroidPhotosAutosave_release(new Runnable() { // from class: com.amazon.photos.autosave.internal.workers.CancelAndRescheduleWorker$mainTask$2
            @Override // java.lang.Runnable
            public final void run() {
                Set<String> of;
                Set<? extends UploadState> of2;
                Set<String> of3;
                Set<? extends UploadState> of4;
                int collectionSizeOrDefault;
                int collectionSizeOrDefault2;
                boolean z;
                int collectionSizeOrDefault3;
                int collectionSizeOrDefault4;
                boolean z2;
                RequestDao requestDao = CancelAndRescheduleWorker.this.getUploadManager().getDaos().getRequestDao();
                of = SetsKt__SetsJVMKt.setOf(AutosaveUploadConfigurationProvider.PHOTOS_QUEUE);
                of2 = SetsKt__SetsKt.setOf((Object[]) new UploadState[]{UploadState.BLOCKED, UploadState.ENQUEUED, UploadState.RUNNING});
                List<UploadRequest> requestsForStates = requestDao.getRequestsForStates(of, of2);
                CancelAndRescheduleWorker.this.getMetricsHelper().logMetricWithCount$AndroidPhotosAutosave_release(CancelAndRescheduleWorker.TAG, AutosaveMetricEvents.COUNT_UPLOADS_CANCELLED_RESCHEDULED_ADD_TO_FAMILY, requestsForStates.size());
                if (!requestsForStates.isEmpty()) {
                    UploaderOperations operations = CancelAndRescheduleWorker.this.getUploadManager().getOperations();
                    collectionSizeOrDefault3 = CollectionsKt__IterablesKt.collectionSizeOrDefault(requestsForStates, 10);
                    ArrayList arrayList = new ArrayList(collectionSizeOrDefault3);
                    for (UploadRequest uploadRequest : requestsForStates) {
                        arrayList.add(Long.valueOf(uploadRequest.getId()));
                    }
                    operations.cancelUploads(arrayList);
                    collectionSizeOrDefault4 = CollectionsKt__IterablesKt.collectionSizeOrDefault(requestsForStates, 10);
                    ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault4);
                    for (UploadRequest uploadRequest2 : requestsForStates) {
                        UploadHelper uploadHelper = CancelAndRescheduleWorker.this.getUploadHelper();
                        z2 = CancelAndRescheduleWorker.this.addToFamily;
                        arrayList2.add(uploadHelper.createModifiedCopyRequest$AndroidPhotosAutosave_release(uploadRequest2, z2));
                    }
                    UploaderOperations.scheduleUploads$default(CancelAndRescheduleWorker.this.getUploadManager().getOperations(), arrayList2, AutosaveUploadConfigurationProvider.PHOTOS_QUEUE, null, 4, null).waitForResult();
                }
                RequestDao requestDao2 = CancelAndRescheduleWorker.this.getUploadManager().getDaos().getRequestDao();
                of3 = SetsKt__SetsJVMKt.setOf(AutosaveUploadConfigurationProvider.VIDEOS_QUEUE);
                of4 = SetsKt__SetsKt.setOf((Object[]) new UploadState[]{UploadState.BLOCKED, UploadState.ENQUEUED, UploadState.RUNNING});
                List<UploadRequest> requestsForStates2 = requestDao2.getRequestsForStates(of3, of4);
                CancelAndRescheduleWorker.this.getMetricsHelper().logMetricWithCount$AndroidPhotosAutosave_release(CancelAndRescheduleWorker.TAG, AutosaveMetricEvents.COUNT_UPLOADS_CANCELLED_RESCHEDULED_ADD_TO_FAMILY, requestsForStates2.size());
                if (!requestsForStates2.isEmpty()) {
                    UploaderOperations operations2 = CancelAndRescheduleWorker.this.getUploadManager().getOperations();
                    collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(requestsForStates2, 10);
                    ArrayList arrayList3 = new ArrayList(collectionSizeOrDefault);
                    for (UploadRequest uploadRequest3 : requestsForStates2) {
                        arrayList3.add(Long.valueOf(uploadRequest3.getId()));
                    }
                    operations2.cancelUploads(arrayList3);
                    collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(requestsForStates2, 10);
                    ArrayList arrayList4 = new ArrayList(collectionSizeOrDefault2);
                    for (UploadRequest uploadRequest4 : requestsForStates2) {
                        UploadHelper uploadHelper2 = CancelAndRescheduleWorker.this.getUploadHelper();
                        z = CancelAndRescheduleWorker.this.addToFamily;
                        arrayList4.add(uploadHelper2.createModifiedCopyRequest$AndroidPhotosAutosave_release(uploadRequest4, z));
                    }
                    UploaderOperations.scheduleUploads$default(CancelAndRescheduleWorker.this.getUploadManager().getOperations(), arrayList4, AutosaveUploadConfigurationProvider.VIDEOS_QUEUE, null, 4, null).waitForResult();
                }
            }
        });
        ListenableWorker.Result success = ListenableWorker.Result.success();
        Intrinsics.checkExpressionValueIsNotNull(success, "Result.success()");
        return success;
    }

    @Inject
    public final void setMetrics(@NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "<set-?>");
        this.metrics = metrics;
    }

    @Inject
    public final void setMetricsHelper(@NotNull MetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(metricsHelper, "<set-?>");
        this.metricsHelper = metricsHelper;
    }

    @Inject
    public final void setTransactionRunner(@NotNull AutosaveTransactionRunner autosaveTransactionRunner) {
        Intrinsics.checkParameterIsNotNull(autosaveTransactionRunner, "<set-?>");
        this.transactionRunner = autosaveTransactionRunner;
    }

    @Inject
    public final void setUploadHelper(@NotNull UploadHelper uploadHelper) {
        Intrinsics.checkParameterIsNotNull(uploadHelper, "<set-?>");
        this.uploadHelper = uploadHelper;
    }

    @Inject
    public final void setUploadManager(@NotNull UploadManager uploadManager) {
        Intrinsics.checkParameterIsNotNull(uploadManager, "<set-?>");
        this.uploadManager = uploadManager;
    }
}
