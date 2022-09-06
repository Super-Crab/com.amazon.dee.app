package com.amazon.photos.uploader.internal.workers;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.WorkManager;
import androidx.work.WorkerParameters;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.uploader.Queue;
import com.amazon.photos.uploader.QueueManager;
import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadState;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.internal.InternalEvaluator;
import com.amazon.photos.uploader.internal.NotificationUpdatesNotifier;
import com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier;
import com.amazon.photos.uploader.internal.UploadSummaryTracker;
import com.amazon.photos.uploader.internal.UploaderTransactionRunner;
import com.amazon.photos.uploader.internal.dagger.UploadManagerMap;
import com.amazon.photos.uploader.internal.dao.UploadRequestDao;
import com.amazon.photos.uploader.internal.utils.PersistentLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BlockerEvaluatorWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u0000 b2\u00020\u0001:\u0001bB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010L\u001a\u00020M2\f\u0010N\u001a\b\u0012\u0004\u0012\u00020\b0OH\u0001¢\u0006\u0004\bP\u0010QJ\b\u0010R\u001a\u00020\u0016H\u0014J\b\u0010S\u001a\u00020\bH\u0014J\b\u0010T\u001a\u00020UH\u0014J\u0011\u0010V\u001a\u00020MH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010WJ\u0016\u0010X\u001a\b\u0012\u0004\u0012\u00020Z0Y2\u0006\u0010[\u001a\u00020\\H\u0002J\u0018\u0010]\u001a\u00020U2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020aH\u0002R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\u0016@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR(\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\t\u001a\u0004\u0018\u00010\u001c@QX\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R$\u0010#\u001a\u00020\"2\u0006\u0010\t\u001a\u00020\"@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R$\u0010)\u001a\u00020(2\u0006\u0010\t\u001a\u00020(@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R$\u0010/\u001a\u00020.2\u0006\u0010\t\u001a\u00020.@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R$\u00105\u001a\u0002042\u0006\u0010\t\u001a\u000204@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R$\u0010;\u001a\u00020:2\u0006\u0010\t\u001a\u00020:@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R$\u0010A\u001a\u00020@2\u0006\u0010\t\u001a\u00020@@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER$\u0010G\u001a\u00020F2\u0006\u0010\t\u001a\u00020F@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010K\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006c"}, d2 = {"Lcom/amazon/photos/uploader/internal/workers/BlockerEvaluatorWorker;", "Lcom/amazon/photos/uploader/internal/workers/BaseWorker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "hashedDirectedId", "", "<set-?>", "Lcom/amazon/photos/uploader/internal/InternalEvaluator;", "internalEvaluator", "getInternalEvaluator", "()Lcom/amazon/photos/uploader/internal/InternalEvaluator;", "setInternalEvaluator", "(Lcom/amazon/photos/uploader/internal/InternalEvaluator;)V", "Lcom/amazon/photos/uploader/internal/utils/PersistentLogger;", "logger", "getLogger", "()Lcom/amazon/photos/uploader/internal/utils/PersistentLogger;", "setLogger", "(Lcom/amazon/photos/uploader/internal/utils/PersistentLogger;)V", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "metrics", "getMetrics", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "setMetrics", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;", "notificationUpdatesNotifier", "getNotificationUpdatesNotifier$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;", "setNotificationUpdatesNotifier$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;)V", "Lcom/amazon/photos/uploader/QueueManager;", "queueManager", "getQueueManager", "()Lcom/amazon/photos/uploader/QueueManager;", "setQueueManager", "(Lcom/amazon/photos/uploader/QueueManager;)V", "Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao;", "requestDao", "getRequestDao", "()Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao;", "setRequestDao", "(Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao;)V", "Lcom/amazon/photos/uploader/SchedulingCallback;", "schedulingCallback", "getSchedulingCallback", "()Lcom/amazon/photos/uploader/SchedulingCallback;", "setSchedulingCallback", "(Lcom/amazon/photos/uploader/SchedulingCallback;)V", "Lcom/amazon/photos/uploader/internal/UploaderTransactionRunner;", "transactionRunner", "getTransactionRunner", "()Lcom/amazon/photos/uploader/internal/UploaderTransactionRunner;", "setTransactionRunner", "(Lcom/amazon/photos/uploader/internal/UploaderTransactionRunner;)V", "Lcom/amazon/photos/uploader/internal/UploadRequestUpdatesNotifier;", "uploadRequestUpdatesNotifier", "getUploadRequestUpdatesNotifier", "()Lcom/amazon/photos/uploader/internal/UploadRequestUpdatesNotifier;", "setUploadRequestUpdatesNotifier", "(Lcom/amazon/photos/uploader/internal/UploadRequestUpdatesNotifier;)V", "Lcom/amazon/photos/uploader/internal/UploadSummaryTracker;", "uploadSummaryTracker", "getUploadSummaryTracker", "()Lcom/amazon/photos/uploader/internal/UploadSummaryTracker;", "setUploadSummaryTracker", "(Lcom/amazon/photos/uploader/internal/UploadSummaryTracker;)V", "Landroidx/work/WorkManager;", "workManager", "getWorkManager", "()Landroidx/work/WorkManager;", "setWorkManager", "(Landroidx/work/WorkManager;)V", "generateResult", "Landroidx/work/ListenableWorker$Result;", "queues", "", "generateResult$AndroidPhotosUploader_release", "([Ljava/lang/String;)Landroidx/work/ListenableWorker$Result;", "getMetricsObj", "getTag", "injectMethod", "", "mainTask", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processRequests", "", "Lcom/amazon/photos/uploader/blockers/Blocker;", "queue", "Lcom/amazon/photos/uploader/Queue;", "recordBlockerEvaluationMetric", "totalBlockedRequests", "", "startTime", "", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class BlockerEvaluatorWorker extends BaseWorker {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "BlockerEvaluatorWorker";
    private final String hashedDirectedId;
    @NotNull
    public InternalEvaluator internalEvaluator;
    @NotNull
    public PersistentLogger logger;
    @NotNull
    public Metrics metrics;
    @Nullable
    private NotificationUpdatesNotifier notificationUpdatesNotifier;
    @NotNull
    public QueueManager queueManager;
    @NotNull
    public UploadRequestDao requestDao;
    @NotNull
    public SchedulingCallback schedulingCallback;
    @NotNull
    public UploaderTransactionRunner transactionRunner;
    @NotNull
    public UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier;
    @NotNull
    public UploadSummaryTracker uploadSummaryTracker;
    @NotNull
    public WorkManager workManager;

    /* compiled from: BlockerEvaluatorWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/internal/workers/BlockerEvaluatorWorker$Companion;", "", "()V", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BlockerEvaluatorWorker(@NotNull Context appContext, @NotNull WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        Intrinsics.checkParameterIsNotNull(workerParams, "workerParams");
        this.hashedDirectedId = workerParams.getInputData().getString("HASHED_DIRECTED_ID_KEY");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Collection<Blocker> processRequests(final Queue queue) {
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        UploaderTransactionRunner uploaderTransactionRunner = this.transactionRunner;
        if (uploaderTransactionRunner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transactionRunner");
        }
        uploaderTransactionRunner.runInTransaction$AndroidPhotosUploader_release(new Runnable() { // from class: com.amazon.photos.uploader.internal.workers.BlockerEvaluatorWorker$processRequests$1
            @Override // java.lang.Runnable
            public final void run() {
                Iterator it2;
                List<Blocker> list;
                UploadRequest copy;
                HashSet hashSet;
                Iterator it3 = BlockerEvaluatorWorker.this.getRequestDao().getPrioritizedPendingRequestsForQueue(queue.getName()).iterator();
                while (it3.hasNext()) {
                    UploadRequest uploadRequest = (UploadRequest) it3.next();
                    List<Blocker> requestBlockers = BlockerEvaluatorWorker.this.getInternalEvaluator().getRequestBlockers(uploadRequest);
                    if (!requestBlockers.isEmpty()) {
                        BlockerEvaluatorWorker.this.getRequestDao().updateRequestState(uploadRequest.getId(), UploadState.BLOCKED);
                        BlockerEvaluatorWorker.this.getRequestDao().updateRequestBlocker(uploadRequest.getId(), requestBlockers.get(0).getName());
                        UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier = BlockerEvaluatorWorker.this.getUploadRequestUpdatesNotifier();
                        it2 = it3;
                        copy = uploadRequest.copy((r49 & 1) != 0 ? uploadRequest.id : 0L, (r49 & 2) != 0 ? uploadRequest.filePath : null, (r49 & 4) != 0 ? uploadRequest.uploadPath : null, (r49 & 8) != 0 ? uploadRequest.contentDate : null, (r49 & 16) != 0 ? uploadRequest.md5 : null, (r49 & 32) != 0 ? uploadRequest.visualDigest : null, (r49 & 64) != 0 ? uploadRequest.suppressDeduplication : false, (r49 & 128) != 0 ? uploadRequest.renameOnNameConflict : false, (r49 & 256) != 0 ? uploadRequest.uploadCategory : null, (r49 & 512) != 0 ? uploadRequest.state : UploadState.BLOCKED, (r49 & 1024) != 0 ? uploadRequest.queue : null, (r49 & 2048) != 0 ? uploadRequest.currentProgress : 0L, (r49 & 4096) != 0 ? uploadRequest.maxProgress : 0L, (r49 & 8192) != 0 ? uploadRequest.errorCode : null, (r49 & 16384) != 0 ? uploadRequest.errorCategory : null, (r49 & 32768) != 0 ? uploadRequest.blocker : requestBlockers.get(0), (r49 & 65536) != 0 ? uploadRequest.totalAttemptCount : 0, (r49 & 131072) != 0 ? uploadRequest.attemptCount : 0, (r49 & 262144) != 0 ? uploadRequest.maxAttemptsExceeded : false, (r49 & 524288) != 0 ? uploadRequest.creationTimeMillis : 0L, (r49 & 1048576) != 0 ? uploadRequest.fileSize : 0L, (r49 & 2097152) != 0 ? uploadRequest.priority : 0, (4194304 & r49) != 0 ? uploadRequest.addToFamilyVault : false, (r49 & 8388608) != 0 ? uploadRequest.appData : null, (r49 & 16777216) != 0 ? uploadRequest.parentId : null, (r49 & 33554432) != 0 ? uploadRequest.contentUri : null);
                        hashSet = CollectionsKt___CollectionsKt.toHashSet(requestBlockers);
                        uploadRequestUpdatesNotifier.onUploadBlocked$AndroidPhotosUploader_release(copy, hashSet);
                        WorkManager workManager = BlockerEvaluatorWorker.this.getWorkManager();
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(SchedulerWorkerKt.REQUEST_TAG_PREFIX);
                        outline107.append(uploadRequest.getId());
                        workManager.cancelAllWorkByTag(outline107.toString());
                        PersistentLogger logger = BlockerEvaluatorWorker.this.getLogger();
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Request[");
                        outline1072.append(uploadRequest.getId());
                        outline1072.append("] blocked by ");
                        list = requestBlockers;
                        outline1072.append(list);
                        logger.iPersistent(BlockerEvaluatorWorker.TAG, outline1072.toString());
                    } else {
                        it2 = it3;
                        list = requestBlockers;
                        if (uploadRequest.getState() == UploadState.BLOCKED) {
                            BlockerEvaluatorWorker.this.getRequestDao().updateRequestState(uploadRequest.getId(), UploadState.ENQUEUED);
                            BlockerEvaluatorWorker.this.getRequestDao().updateRequestBlocker(uploadRequest.getId(), null);
                            PersistentLogger logger2 = BlockerEvaluatorWorker.this.getLogger();
                            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Request[");
                            outline1073.append(uploadRequest.getId());
                            outline1073.append("] unblocked");
                            logger2.i(BlockerEvaluatorWorker.TAG, outline1073.toString());
                        }
                    }
                    linkedHashSet.addAll(list);
                    it3 = it2;
                }
            }
        });
        return linkedHashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordBlockerEvaluationMetric(int i, long j) {
        ClientMetric withTagName = new ClientMetric().addCounter(BlockerEvaluatorWorker$recordBlockerEvaluationMetric$clientMetric$1.INSTANCE, i).withTagName(UploadWorker.TAG);
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordCustomMetric(UploadWorker.TAG, withTagName, new MetricRecordingType[0]);
        Metrics metrics2 = this.metrics;
        if (metrics2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics2.recordSimpleDuration(TAG, BlockerEvaluatorWorker$recordBlockerEvaluationMetric$1.INSTANCE, System.currentTimeMillis() - j);
    }

    @VisibleForTesting
    @NotNull
    public final ListenableWorker.Result generateResult$AndroidPhotosUploader_release(@NotNull String[] queues) {
        Intrinsics.checkParameterIsNotNull(queues, "queues");
        ListenableWorker.Result success = ListenableWorker.Result.success(new Data.Builder().putStringArray(SchedulerWorkerKt.UNBLOCKED_QUEUES_KEY, queues).build());
        Intrinsics.checkExpressionValueIsNotNull(success, "Result.success(Data.Buil…UES_KEY, queues).build())");
        return success;
    }

    @NotNull
    public final InternalEvaluator getInternalEvaluator() {
        InternalEvaluator internalEvaluator = this.internalEvaluator;
        if (internalEvaluator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalEvaluator");
        }
        return internalEvaluator;
    }

    @NotNull
    public final PersistentLogger getLogger() {
        PersistentLogger persistentLogger = this.logger;
        if (persistentLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        return persistentLogger;
    }

    @NotNull
    public final Metrics getMetrics() {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        return metrics;
    }

    @Override // com.amazon.photos.uploader.internal.workers.BaseWorker
    @NotNull
    protected Metrics getMetricsObj() {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        return metrics;
    }

    @Override // com.amazon.photos.uploader.internal.workers.BaseWorker
    @Nullable
    public NotificationUpdatesNotifier getNotificationUpdatesNotifier$AndroidPhotosUploader_release() {
        return this.notificationUpdatesNotifier;
    }

    @NotNull
    public final QueueManager getQueueManager() {
        QueueManager queueManager = this.queueManager;
        if (queueManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("queueManager");
        }
        return queueManager;
    }

    @NotNull
    public final UploadRequestDao getRequestDao() {
        UploadRequestDao uploadRequestDao = this.requestDao;
        if (uploadRequestDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestDao");
        }
        return uploadRequestDao;
    }

    @NotNull
    public final SchedulingCallback getSchedulingCallback() {
        SchedulingCallback schedulingCallback = this.schedulingCallback;
        if (schedulingCallback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("schedulingCallback");
        }
        return schedulingCallback;
    }

    @Override // com.amazon.photos.uploader.internal.workers.BaseWorker
    @NotNull
    protected String getTag() {
        return TAG;
    }

    @NotNull
    public final UploaderTransactionRunner getTransactionRunner() {
        UploaderTransactionRunner uploaderTransactionRunner = this.transactionRunner;
        if (uploaderTransactionRunner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transactionRunner");
        }
        return uploaderTransactionRunner;
    }

    @NotNull
    public final UploadRequestUpdatesNotifier getUploadRequestUpdatesNotifier() {
        UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier = this.uploadRequestUpdatesNotifier;
        if (uploadRequestUpdatesNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequestUpdatesNotifier");
        }
        return uploadRequestUpdatesNotifier;
    }

    @NotNull
    public final UploadSummaryTracker getUploadSummaryTracker() {
        UploadSummaryTracker uploadSummaryTracker = this.uploadSummaryTracker;
        if (uploadSummaryTracker == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadSummaryTracker");
        }
        return uploadSummaryTracker;
    }

    @NotNull
    public final WorkManager getWorkManager() {
        WorkManager workManager = this.workManager;
        if (workManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        return workManager;
    }

    @Override // com.amazon.photos.uploader.internal.workers.BaseWorker
    protected void injectMethod() {
        UploadManagerMap uploadManagerMap = UploadManagerMap.INSTANCE;
        String str = this.hashedDirectedId;
        if (str != null) {
            uploadManagerMap.getUploadManagerForAccount$AndroidPhotosUploader_release(str).getComponent$AndroidPhotosUploader_release().inject(this);
            return;
        }
        throw new IllegalArgumentException("No hashed directed id associated with worker.");
    }

    @Override // com.amazon.photos.uploader.internal.workers.BaseWorker
    @Nullable
    protected Object mainTask(@NotNull Continuation<? super ListenableWorker.Result> continuation) {
        return CoroutineScopeKt.coroutineScope(new BlockerEvaluatorWorker$mainTask$2(this, null), continuation);
    }

    @Inject
    public final void setInternalEvaluator(@NotNull InternalEvaluator internalEvaluator) {
        Intrinsics.checkParameterIsNotNull(internalEvaluator, "<set-?>");
        this.internalEvaluator = internalEvaluator;
    }

    @Inject
    public final void setLogger(@NotNull PersistentLogger persistentLogger) {
        Intrinsics.checkParameterIsNotNull(persistentLogger, "<set-?>");
        this.logger = persistentLogger;
    }

    @Inject
    public final void setMetrics(@NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "<set-?>");
        this.metrics = metrics;
    }

    @Override // com.amazon.photos.uploader.internal.workers.BaseWorker
    @Inject
    public void setNotificationUpdatesNotifier$AndroidPhotosUploader_release(@Nullable NotificationUpdatesNotifier notificationUpdatesNotifier) {
        this.notificationUpdatesNotifier = notificationUpdatesNotifier;
    }

    @Inject
    public final void setQueueManager(@NotNull QueueManager queueManager) {
        Intrinsics.checkParameterIsNotNull(queueManager, "<set-?>");
        this.queueManager = queueManager;
    }

    @Inject
    public final void setRequestDao(@NotNull UploadRequestDao uploadRequestDao) {
        Intrinsics.checkParameterIsNotNull(uploadRequestDao, "<set-?>");
        this.requestDao = uploadRequestDao;
    }

    @Inject
    public final void setSchedulingCallback(@NotNull SchedulingCallback schedulingCallback) {
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "<set-?>");
        this.schedulingCallback = schedulingCallback;
    }

    @Inject
    public final void setTransactionRunner(@NotNull UploaderTransactionRunner uploaderTransactionRunner) {
        Intrinsics.checkParameterIsNotNull(uploaderTransactionRunner, "<set-?>");
        this.transactionRunner = uploaderTransactionRunner;
    }

    @Inject
    public final void setUploadRequestUpdatesNotifier(@NotNull UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier) {
        Intrinsics.checkParameterIsNotNull(uploadRequestUpdatesNotifier, "<set-?>");
        this.uploadRequestUpdatesNotifier = uploadRequestUpdatesNotifier;
    }

    @Inject
    public final void setUploadSummaryTracker(@NotNull UploadSummaryTracker uploadSummaryTracker) {
        Intrinsics.checkParameterIsNotNull(uploadSummaryTracker, "<set-?>");
        this.uploadSummaryTracker = uploadSummaryTracker;
    }

    @Inject
    public final void setWorkManager(@NotNull WorkManager workManager) {
        Intrinsics.checkParameterIsNotNull(workManager, "<set-?>");
        this.workManager = workManager;
    }
}
