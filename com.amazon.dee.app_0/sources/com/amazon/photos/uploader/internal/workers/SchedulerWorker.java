package com.amazon.photos.uploader.internal.workers;

import android.content.Context;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkerParameters;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.uploader.Queue;
import com.amazon.photos.uploader.QueueManager;
import com.amazon.photos.uploader.UploadManager;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadState;
import com.amazon.photos.uploader.internal.NotificationUpdatesNotifier;
import com.amazon.photos.uploader.internal.SchedulerConfiguration;
import com.amazon.photos.uploader.internal.dagger.UploadManagerMap;
import com.amazon.photos.uploader.internal.dao.UploadRequestDao;
import com.amazon.photos.uploader.internal.metrics.UploadMetrics;
import com.amazon.photos.uploader.log.UploadLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SchedulerWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 T2\u00020\u0001:\u0002TUB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010@\u001a\u00020\u0018H\u0014J\u000e\u0010A\u001a\b\u0012\u0004\u0012\u00020C0BH\u0002J\b\u0010D\u001a\u00020\u000fH\u0014J\b\u0010E\u001a\u00020FH\u0014J\u0011\u0010G\u001a\u00020HH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010IJ\u0010\u0010J\u001a\u00020F2\u0006\u0010K\u001a\u00020LH\u0002J\u0010\u0010M\u001a\u00020F2\u0006\u0010K\u001a\u00020LH\u0002J\u0010\u0010N\u001a\u00020F2\u0006\u0010O\u001a\u00020\u0017H\u0002J\u0010\u0010P\u001a\u00020F2\u0006\u0010Q\u001a\u00020CH\u0002J\u0010\u0010R\u001a\u00020S2\u0006\u0010Q\u001a\u00020CH\u0002R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\u0010@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0007\u001a\u00020\u0018@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR(\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0007\u001a\u0004\u0018\u00010\u001e@QX\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R$\u0010%\u001a\u00020$2\u0006\u0010\u0007\u001a\u00020$@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R$\u0010+\u001a\u00020*2\u0006\u0010\u0007\u001a\u00020*@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R$\u00101\u001a\u0002002\u0006\u0010\u0007\u001a\u000200@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R4\u00106\u001a&\u0012\f\u0012\n 8*\u0004\u0018\u00010\u000f0\u000f 8*\u0012\u0012\u000e\b\u0001\u0012\n 8*\u0004\u0018\u00010\u000f0\u000f0707X\u0082\u0004¢\u0006\u0004\n\u0002\u00109R$\u0010;\u001a\u00020:2\u0006\u0010\u0007\u001a\u00020:@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006V"}, d2 = {"Lcom/amazon/photos/uploader/internal/workers/SchedulerWorker;", "Lcom/amazon/photos/uploader/internal/workers/BaseWorker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "<set-?>", "Lcom/amazon/photos/uploader/internal/workers/CoroutineWorkerUtil;", "coroutineWorkerUtil", "getCoroutineWorkerUtil", "()Lcom/amazon/photos/uploader/internal/workers/CoroutineWorkerUtil;", "setCoroutineWorkerUtil", "(Lcom/amazon/photos/uploader/internal/workers/CoroutineWorkerUtil;)V", "hashedDirectedId", "", "Lcom/amazon/photos/uploader/log/UploadLogger;", "logger", "getLogger", "()Lcom/amazon/photos/uploader/log/UploadLogger;", "setLogger", "(Lcom/amazon/photos/uploader/log/UploadLogger;)V", "maxWorkers", "", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "metrics", "getMetrics", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "setMetrics", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;", "notificationUpdatesNotifier", "getNotificationUpdatesNotifier$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;", "setNotificationUpdatesNotifier$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;)V", "Lcom/amazon/photos/uploader/QueueManager;", "queueManager", "getQueueManager", "()Lcom/amazon/photos/uploader/QueueManager;", "setQueueManager", "(Lcom/amazon/photos/uploader/QueueManager;)V", "Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao;", "requestDao", "getRequestDao", "()Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao;", "setRequestDao", "(Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao;)V", "Lcom/amazon/photos/uploader/internal/SchedulerConfiguration;", "schedulerConfiguration", "getSchedulerConfiguration", "()Lcom/amazon/photos/uploader/internal/SchedulerConfiguration;", "setSchedulerConfiguration", "(Lcom/amazon/photos/uploader/internal/SchedulerConfiguration;)V", "unblockedQueues", "", "kotlin.jvm.PlatformType", "[Ljava/lang/String;", "Landroidx/work/WorkManager;", "workManager", "getWorkManager", "()Landroidx/work/WorkManager;", "setWorkManager", "(Landroidx/work/WorkManager;)V", "getMetricsObj", "getPriorityOrderedUnblockedRequests", "Ljava/util/LinkedList;", "Lcom/amazon/photos/uploader/UploadRequest;", "getTag", "injectMethod", "", "mainTask", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recordCompletionMetric", "totalTimeSpendInMillis", "", "recordInterruptedMetric", "recordRequestCountMetric", "requestCount", "recordRequestDurationMetric", "request", "scheduleRequest", "Landroidx/work/OneTimeWorkRequest;", "Companion", "QueueComparator", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class SchedulerWorker extends BaseWorker {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "SchedulerWorker";
    @NotNull
    public CoroutineWorkerUtil coroutineWorkerUtil;
    private final String hashedDirectedId;
    @NotNull
    public UploadLogger logger;
    private int maxWorkers;
    @NotNull
    public Metrics metrics;
    @Nullable
    private NotificationUpdatesNotifier notificationUpdatesNotifier;
    @NotNull
    public QueueManager queueManager;
    @NotNull
    public UploadRequestDao requestDao;
    @NotNull
    public SchedulerConfiguration schedulerConfiguration;
    private final String[] unblockedQueues;
    @NotNull
    public WorkManager workManager;

    /* compiled from: SchedulerWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/internal/workers/SchedulerWorker$Companion;", "", "()V", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: SchedulerWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/photos/uploader/internal/workers/SchedulerWorker$QueueComparator;", "Ljava/util/Comparator;", "Lcom/amazon/photos/uploader/Queue;", "Lkotlin/Comparator;", "(Lcom/amazon/photos/uploader/internal/workers/SchedulerWorker;)V", "compare", "", "q1", "q2", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final class QueueComparator implements Comparator<Queue> {
        public QueueComparator() {
        }

        @Override // java.util.Comparator
        public int compare(@NotNull Queue q1, @NotNull Queue q2) {
            Intrinsics.checkParameterIsNotNull(q1, "q1");
            Intrinsics.checkParameterIsNotNull(q2, "q2");
            return Intrinsics.compare(q2.getPriority().getValue(), q1.getPriority().getValue());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SchedulerWorker(@NotNull Context appContext, @NotNull WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        Intrinsics.checkParameterIsNotNull(workerParams, "workerParams");
        this.hashedDirectedId = workerParams.getInputData().getString("HASHED_DIRECTED_ID_KEY");
        String[] stringArray = workerParams.getInputData().getStringArray(SchedulerWorkerKt.UNBLOCKED_QUEUES_KEY);
        this.unblockedQueues = stringArray == null ? new String[0] : stringArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LinkedList<UploadRequest> getPriorityOrderedUnblockedRequests() {
        List<Queue> sortedWith;
        String[] strArr = this.unblockedQueues;
        ArrayList arrayList = new ArrayList();
        for (String name : strArr) {
            QueueManager queueManager = this.queueManager;
            if (queueManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("queueManager");
            }
            Intrinsics.checkExpressionValueIsNotNull(name, "name");
            Queue queue = queueManager.getQueue(name);
            if (queue != null) {
                arrayList.add(queue);
            }
        }
        sortedWith = CollectionsKt___CollectionsKt.sortedWith(arrayList, new QueueComparator());
        LinkedList<UploadRequest> linkedList = new LinkedList<>();
        for (Queue queue2 : sortedWith) {
            UploadRequestDao uploadRequestDao = this.requestDao;
            if (uploadRequestDao == null) {
                Intrinsics.throwUninitializedPropertyAccessException("requestDao");
            }
            linkedList.addAll(uploadRequestDao.getPrioritizedRequestsForState(queue2.getName(), UploadState.ENQUEUED));
        }
        return linkedList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordCompletionMetric(double d) {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordSimpleEvent(TAG, SchedulerWorker$recordCompletionMetric$1.INSTANCE, new MetricRecordingType[0]);
        Metrics metrics2 = this.metrics;
        if (metrics2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics2.recordSimpleDuration(TAG, SchedulerWorker$recordCompletionMetric$2.INSTANCE, d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordInterruptedMetric(double d) {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordSimpleEvent(TAG, SchedulerWorker$recordInterruptedMetric$1.INSTANCE, new MetricRecordingType[0]);
        Metrics metrics2 = this.metrics;
        if (metrics2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics2.recordSimpleDuration(TAG, SchedulerWorker$recordInterruptedMetric$2.INSTANCE, d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordRequestCountMetric(int i) {
        UploadLogger uploadLogger = this.logger;
        if (uploadLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        uploadLogger.i$AndroidPhotosUploader_release(TAG, "Found " + i + " unblocked requests.");
        ClientMetric withTagName = new ClientMetric().addCounter(SchedulerWorker$recordRequestCountMetric$clientMetric$1.INSTANCE, i).withTagName(UploadWorker.TAG);
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordCustomMetric(UploadWorker.TAG, withTagName, new MetricRecordingType[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordRequestDurationMetric(final UploadRequest uploadRequest) {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordSimpleDuration(TAG, SchedulerWorker$recordRequestDurationMetric$1.INSTANCE, System.currentTimeMillis() - uploadRequest.getCreationTimeMillis());
        Metrics metrics2 = this.metrics;
        if (metrics2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics2.recordSimpleDuration(TAG, new MetricName() { // from class: com.amazon.photos.uploader.internal.workers.SchedulerWorker$recordRequestDurationMetric$2
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(UploadMetrics.UPLOAD_ENQUEUED_TO_WORK_MANAGER_TIME_FOR_ATTEMPT);
                outline107.append(UploadRequest.this.getAttemptCount());
                return outline107.toString();
            }
        }, System.currentTimeMillis() - uploadRequest.getCreationTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OneTimeWorkRequest scheduleRequest(UploadRequest uploadRequest) {
        UploadLogger uploadLogger = this.logger;
        if (uploadLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Scheduling request ");
        outline107.append(uploadRequest.getId());
        outline107.append(" with path = ");
        UploadLogger uploadLogger2 = this.logger;
        if (uploadLogger2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        outline107.append(uploadLogger2.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()));
        outline107.append(" on queue ");
        outline107.append(uploadRequest.getQueue());
        uploadLogger.i$AndroidPhotosUploader_release(TAG, outline107.toString());
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(SchedulerWorkerKt.QUEUE_TAG_PREFIX);
        outline1072.append(uploadRequest.getQueue());
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107(SchedulerWorkerKt.REQUEST_TAG_PREFIX);
        outline1073.append(uploadRequest.getId());
        OneTimeWorkRequest build = new OneTimeWorkRequest.Builder(UploadWorker.class).setInputData(new Data.Builder().putString("HASHED_DIRECTED_ID_KEY", this.hashedDirectedId).putLong(SchedulerWorkerKt.REQUEST_ID_KEY, uploadRequest.getId()).build()).addTag(UploadManager.WORK_MANAGER_TAG).addTag(SchedulerWorkerKt.UPLOAD_TAG).addTag(outline1072.toString()).addTag(outline1073.toString()).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "OneTimeWorkRequest.Build…\n                .build()");
        return build;
    }

    @NotNull
    public final CoroutineWorkerUtil getCoroutineWorkerUtil() {
        CoroutineWorkerUtil coroutineWorkerUtil = this.coroutineWorkerUtil;
        if (coroutineWorkerUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coroutineWorkerUtil");
        }
        return coroutineWorkerUtil;
    }

    @NotNull
    public final UploadLogger getLogger() {
        UploadLogger uploadLogger = this.logger;
        if (uploadLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        return uploadLogger;
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
    public final SchedulerConfiguration getSchedulerConfiguration() {
        SchedulerConfiguration schedulerConfiguration = this.schedulerConfiguration;
        if (schedulerConfiguration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("schedulerConfiguration");
        }
        return schedulerConfiguration;
    }

    @Override // com.amazon.photos.uploader.internal.workers.BaseWorker
    @NotNull
    protected String getTag() {
        return TAG;
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
            SchedulerConfiguration schedulerConfiguration = this.schedulerConfiguration;
            if (schedulerConfiguration == null) {
                Intrinsics.throwUninitializedPropertyAccessException("schedulerConfiguration");
            }
            this.maxWorkers = schedulerConfiguration.getMaxParallelUploads();
            return;
        }
        throw new IllegalArgumentException("No hashed directed id associated with worker.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @Override // com.amazon.photos.uploader.internal.workers.BaseWorker
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mainTask(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.amazon.photos.uploader.internal.workers.SchedulerWorker$mainTask$1
            if (r0 == 0) goto L13
            r0 = r5
            com.amazon.photos.uploader.internal.workers.SchedulerWorker$mainTask$1 r0 = (com.amazon.photos.uploader.internal.workers.SchedulerWorker$mainTask$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.photos.uploader.internal.workers.SchedulerWorker$mainTask$1 r0 = new com.amazon.photos.uploader.internal.workers.SchedulerWorker$mainTask$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            com.amazon.photos.uploader.internal.workers.SchedulerWorker r0 = (com.amazon.photos.uploader.internal.workers.SchedulerWorker) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L49
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            com.amazon.photos.uploader.internal.workers.SchedulerWorker$mainTask$2 r5 = new com.amazon.photos.uploader.internal.workers.SchedulerWorker$mainTask$2
            r2 = 0
            r5.<init>(r4, r2)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r5, r0)
            if (r5 != r1) goto L49
            return r1
        L49:
            java.lang.String r0 = "coroutineScope {\n       …   Result.success()\n    }"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.uploader.internal.workers.SchedulerWorker.mainTask(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Inject
    public final void setCoroutineWorkerUtil(@NotNull CoroutineWorkerUtil coroutineWorkerUtil) {
        Intrinsics.checkParameterIsNotNull(coroutineWorkerUtil, "<set-?>");
        this.coroutineWorkerUtil = coroutineWorkerUtil;
    }

    @Inject
    public final void setLogger(@NotNull UploadLogger uploadLogger) {
        Intrinsics.checkParameterIsNotNull(uploadLogger, "<set-?>");
        this.logger = uploadLogger;
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
    public final void setSchedulerConfiguration(@NotNull SchedulerConfiguration schedulerConfiguration) {
        Intrinsics.checkParameterIsNotNull(schedulerConfiguration, "<set-?>");
        this.schedulerConfiguration = schedulerConfiguration;
    }

    @Inject
    public final void setWorkManager(@NotNull WorkManager workManager) {
        Intrinsics.checkParameterIsNotNull(workManager, "<set-?>");
        this.workManager = workManager;
    }
}
