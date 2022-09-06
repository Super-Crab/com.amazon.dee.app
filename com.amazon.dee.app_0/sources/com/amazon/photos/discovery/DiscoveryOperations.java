package com.amazon.photos.discovery;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.provider.MediaStore;
import androidx.annotation.AnyThread;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.SystemUtil;
import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import com.amazon.photos.discovery.dedupe.DedupeStage;
import com.amazon.photos.discovery.internal.ConstantsKt;
import com.amazon.photos.discovery.internal.observers.ContentChangeNotifier;
import com.amazon.photos.discovery.internal.receiver.MediaScannerReceiver;
import com.amazon.photos.discovery.internal.util.WorkerHelper;
import com.amazon.photos.discovery.internal.worker.DedupeWorker;
import com.amazon.photos.discovery.internal.worker.DedupeWorkerKt;
import com.amazon.photos.discovery.internal.worker.MediaStoreChangeWorker;
import com.amazon.photos.discovery.internal.worker.MonitorWorker;
import com.amazon.photos.discovery.internal.worker.ScanAddedWorker;
import com.amazon.photos.discovery.internal.worker.ScanDeletedWorker;
import com.amazon.photos.discovery.observers.LocalContentChangeObserver;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.util.concurrent.ListenableFuture;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DiscoveryOperations.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010(\u001a\u00020\u00102\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0007J\b\u0010-\u001a\u00020.H\u0007J\b\u0010/\u001a\u00020.H\u0007J\r\u00100\u001a\u00020.H\u0001¢\u0006\u0002\b1J\u0010\u00102\u001a\u00020.2\u0006\u00103\u001a\u000204H\u0007J\b\u00105\u001a\u00020.H\u0007J\b\u00106\u001a\u00020.H\u0007J\u0012\u00107\u001a\u00020.2\b\b\u0002\u00108\u001a\u00020\u0010H\u0007J\u0012\u00109\u001a\u00020:2\b\b\u0002\u00103\u001a\u000204H\u0002J\b\u0010;\u001a\u000204H\u0002J\b\u0010<\u001a\u00020:H\u0003J\u0010\u0010=\u001a\u00020:2\u0006\u0010>\u001a\u00020\u0014H\u0002J\u0010\u0010?\u001a\u00020:2\u0006\u0010@\u001a\u00020\u0010H\u0002J\b\u0010A\u001a\u00020:H\u0002J\u0014\u0010B\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020E0D0CH\u0007J\u0012\u0010F\u001a\u00020G2\b\b\u0002\u0010@\u001a\u00020\u0010H\u0003J\u0010\u0010H\u001a\u00020\u00102\u0006\u0010)\u001a\u00020*H\u0007J\b\u0010I\u001a\u00020GH\u0007J\b\u0010J\u001a\u00020GH\u0007J\b\u0010K\u001a\u00020\u0010H\u0007J\u0010\u0010L\u001a\u00020.2\u0006\u0010M\u001a\u00020NH\u0003J\u0015\u0010O\u001a\u00020.2\u0006\u0010M\u001a\u00020NH\u0000¢\u0006\u0002\bPJ\u0010\u0010Q\u001a\u00020.2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\f\u0010R\u001a\u00020S*\u00020SH\u0002J\u0014\u0010R\u001a\u00020T*\u00020T2\u0006\u0010U\u001a\u00020VH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u00020\u00178\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001c\u001a\u00020\u001d8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001e\u0010\"\u001a\u00020#8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006W"}, d2 = {"Lcom/amazon/photos/discovery/DiscoveryOperations;", "", "discoveryConfiguration", "Lcom/amazon/photos/discovery/DiscoveryConfiguration;", "(Lcom/amazon/photos/discovery/DiscoveryConfiguration;)V", "addedWorkerId", "", "contentChangeNotifier", "Lcom/amazon/photos/discovery/internal/observers/ContentChangeNotifier;", "getContentChangeNotifier$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/observers/ContentChangeNotifier;", "setContentChangeNotifier$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/observers/ContentChangeNotifier;)V", "deletedWorkerId", "discoveryTag", "enabled", "", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "monitorStartOffset", "", "monitorTag", "systemUtil", "Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;", "getSystemUtil$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;", "setSystemUtil$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;)V", "workManager", "Landroidx/work/WorkManager;", "getWorkManager$AndroidPhotosDiscovery_release", "()Landroidx/work/WorkManager;", "setWorkManager$AndroidPhotosDiscovery_release", "(Landroidx/work/WorkManager;)V", "workerHelper", "Lcom/amazon/photos/discovery/internal/util/WorkerHelper;", "getWorkerHelper$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/util/WorkerHelper;", "setWorkerHelper$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/util/WorkerHelper;)V", "addContentChangeObserver", "observer", "Lcom/amazon/photos/discovery/observers/LocalContentChangeObserver;", "scheduler", "Lio/reactivex/rxjava3/core/Scheduler;", DeviceStateModule.CANCEL, "", "cancelMonitor", "clearObservers", "clearObservers$AndroidPhotosDiscovery_release", "dedupeOperation", "dedupeStage", "", "disable", "enable", "enqueueMonitorWorkIfNeeded", "immediate", "getDedupeWorkRequest", "Landroidx/work/OneTimeWorkRequest;", "getFirstDedupeStageId", "getMediaStoreChangeWorkRequest", "getMonitorWorkRequest", "delayTimeMs", "getScanAddedWorkRequest", "fullScan", "getScanDeletedWorkRequest", "getWorkInfo", "Lcom/google/common/util/concurrent/ListenableFuture;", "", "Landroidx/work/WorkInfo;", "performScan", "Landroidx/work/Operation;", "removeContentChangeObserver", "rescan", "scan", "scanWorkPlanned", "scheduleMediaStoreChangeWorker", "policy", "Landroidx/work/ExistingWorkPolicy;", "scheduleMediaStoreWorkerIfNeeded", "scheduleMediaStoreWorkerIfNeeded$AndroidPhotosDiscovery_release", "setMediaScanReceiverEnabledSetting", "withDefaultParams", "Landroidx/work/Data$Builder;", "Landroidx/work/OneTimeWorkRequest$Builder;", "inputData", "Landroidx/work/Data;", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DiscoveryOperations {
    private final String addedWorkerId;
    @Inject
    @NotNull
    public ContentChangeNotifier contentChangeNotifier;
    private final String deletedWorkerId;
    private final DiscoveryConfiguration discoveryConfiguration;
    private final String discoveryTag;
    private boolean enabled;
    private final Logger logger;
    private final long monitorStartOffset;
    private final String monitorTag;
    @Inject
    @NotNull
    public SystemUtil systemUtil;
    @Inject
    @NotNull
    public WorkManager workManager;
    @Inject
    @NotNull
    public WorkerHelper workerHelper;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[WorkInfo.State.values().length];

        static {
            $EnumSwitchMapping$0[WorkInfo.State.RUNNING.ordinal()] = 1;
            $EnumSwitchMapping$0[WorkInfo.State.ENQUEUED.ordinal()] = 2;
        }
    }

    public DiscoveryOperations(@NotNull DiscoveryConfiguration discoveryConfiguration) {
        Intrinsics.checkParameterIsNotNull(discoveryConfiguration, "discoveryConfiguration");
        this.discoveryConfiguration = discoveryConfiguration;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("added_worker_");
        outline107.append(this.discoveryConfiguration.getHashedDirectedId());
        this.addedWorkerId = outline107.toString();
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("deleted_worker_");
        outline1072.append(this.discoveryConfiguration.getHashedDirectedId());
        this.deletedWorkerId = outline1072.toString();
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107(DiscoveryOperationsKt.DISCOVERY_TAG_PREFIX);
        outline1073.append(this.discoveryConfiguration.getHashedDirectedId());
        this.discoveryTag = outline1073.toString();
        StringBuilder outline1074 = GeneratedOutlineSupport1.outline107(DiscoveryOperationsKt.MONITOR_TAG_PREFIX);
        outline1074.append(this.discoveryConfiguration.getHashedDirectedId());
        this.monitorTag = outline1074.toString();
        this.logger = this.discoveryConfiguration.getLogger();
        this.monitorStartOffset = Math.abs(new Random().nextLong()) % TimeUnit.HOURS.toMillis(2L);
    }

    public static /* synthetic */ void enqueueMonitorWorkIfNeeded$default(DiscoveryOperations discoveryOperations, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        discoveryOperations.enqueueMonitorWorkIfNeeded(z);
    }

    private final OneTimeWorkRequest getDedupeWorkRequest(int i) {
        Object obj;
        Pair pair;
        boolean z;
        Data build = withDefaultParams(new Data.Builder()).putInt(DedupeWorkerKt.PARAM_DEDUPE_STAGE_ID, i).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Data.Builder()\n         …age)\n            .build()");
        Iterator<T> it2 = this.discoveryConfiguration.getDedupeStages().iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj = null;
                break;
            }
            obj = it2.next();
            if (((DedupeStage) obj).getStageId() == i) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                break;
            }
        }
        DedupeStage dedupeStage = (DedupeStage) obj;
        if (dedupeStage == null || (pair = TuplesKt.to(dedupeStage.getConstraints(), dedupeStage.getName())) == null) {
            pair = TuplesKt.to(Constraints.NONE, "Unknown");
        }
        OneTimeWorkRequest.Builder builder = new OneTimeWorkRequest.Builder(DedupeWorker.class);
        withDefaultParams(builder, build);
        builder.addTag(this.discoveryTag);
        builder.addTag((String) pair.component2());
        builder.setConstraints((Constraints) pair.component1());
        builder.setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 5L, TimeUnit.SECONDS);
        OneTimeWorkRequest build2 = builder.build();
        Intrinsics.checkExpressionValueIsNotNull(build2, "OneTimeWorkRequest.Build…ECONDS)\n        }.build()");
        return build2;
    }

    static /* synthetic */ OneTimeWorkRequest getDedupeWorkRequest$default(DiscoveryOperations discoveryOperations, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = discoveryOperations.getFirstDedupeStageId();
        }
        return discoveryOperations.getDedupeWorkRequest(i);
    }

    private final int getFirstDedupeStageId() {
        DedupeStage dedupeStage = (DedupeStage) CollectionsKt.firstOrNull((List<? extends Object>) this.discoveryConfiguration.getDedupeStages());
        if (dedupeStage != null) {
            return dedupeStage.getStageId();
        }
        return -1;
    }

    @TargetApi(24)
    private final OneTimeWorkRequest getMediaStoreChangeWorkRequest() {
        Data build = withDefaultParams(new Data.Builder()).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Data.Builder()\n         …ms()\n            .build()");
        Constraints.Builder builder = new Constraints.Builder();
        builder.addContentUriTrigger(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true);
        builder.addContentUriTrigger(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true);
        Constraints build2 = builder.build();
        Intrinsics.checkExpressionValueIsNotNull(build2, "Constraints.Builder().ap…, true)\n        }.build()");
        OneTimeWorkRequest.Builder builder2 = new OneTimeWorkRequest.Builder(MediaStoreChangeWorker.class);
        withDefaultParams(builder2, build);
        builder2.addTag(ConstantsKt.MEDIA_STORE_CHANGE);
        builder2.setConstraints(build2);
        builder2.setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 5L, TimeUnit.SECONDS);
        OneTimeWorkRequest build3 = builder2.build();
        Intrinsics.checkExpressionValueIsNotNull(build3, "OneTimeWorkRequest.Build…ECONDS)\n        }.build()");
        return build3;
    }

    private final OneTimeWorkRequest getMonitorWorkRequest(long j) {
        Data build = withDefaultParams(new Data.Builder()).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Data.Builder()\n         …ms()\n            .build()");
        OneTimeWorkRequest.Builder builder = new OneTimeWorkRequest.Builder(MonitorWorker.class);
        withDefaultParams(builder, build);
        builder.addTag(ConstantsKt.MONITOR_WORKER);
        builder.setInitialDelay(j, TimeUnit.MILLISECONDS);
        builder.setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 30L, TimeUnit.MINUTES);
        OneTimeWorkRequest build2 = builder.build();
        Intrinsics.checkExpressionValueIsNotNull(build2, "OneTimeWorkRequest.Build…INUTES)\n        }.build()");
        return build2;
    }

    private final OneTimeWorkRequest getScanAddedWorkRequest(boolean z) {
        Data.Builder putBoolean = new Data.Builder().putBoolean(DiscoveryOperationsKt.FULL_SCAN, z);
        Intrinsics.checkExpressionValueIsNotNull(putBoolean, "Data.Builder()\n         …lean(FULL_SCAN, fullScan)");
        Data build = withDefaultParams(putBoolean).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Data.Builder()\n         …ms()\n            .build()");
        OneTimeWorkRequest.Builder builder = new OneTimeWorkRequest.Builder(ScanAddedWorker.class);
        withDefaultParams(builder, build);
        builder.addTag(ConstantsKt.SCAN_ADDED_WORKER);
        builder.addTag(this.discoveryTag);
        builder.setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 5L, TimeUnit.SECONDS);
        OneTimeWorkRequest build2 = builder.build();
        Intrinsics.checkExpressionValueIsNotNull(build2, "OneTimeWorkRequest.Build…ECONDS)\n        }.build()");
        return build2;
    }

    private final OneTimeWorkRequest getScanDeletedWorkRequest() {
        Data build = withDefaultParams(new Data.Builder()).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Data.Builder()\n         …ms()\n            .build()");
        OneTimeWorkRequest.Builder builder = new OneTimeWorkRequest.Builder(ScanDeletedWorker.class);
        withDefaultParams(builder, build);
        builder.addTag(this.discoveryTag);
        builder.addTag(ConstantsKt.SCAN_DELETED_WORKER);
        builder.setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 5L, TimeUnit.SECONDS);
        OneTimeWorkRequest build2 = builder.build();
        Intrinsics.checkExpressionValueIsNotNull(build2, "OneTimeWorkRequest.Build…ECONDS)\n        }.build()");
        return build2;
    }

    @SuppressLint({"EnqueueWork"})
    private final Operation performScan(boolean z) {
        List<? extends WorkContinuation> listOf;
        if (!this.enabled) {
            enable();
        }
        cancel();
        this.logger.d("DiscoveryOperations", "Enqueuing all discovery work");
        WorkManager workManager = this.workManager;
        if (workManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        WorkContinuation beginUniqueWork = workManager.beginUniqueWork(this.addedWorkerId, ExistingWorkPolicy.REPLACE, getScanAddedWorkRequest(z));
        Intrinsics.checkExpressionValueIsNotNull(beginUniqueWork, "workManager.beginUniqueW…dedWorkRequest(fullScan))");
        WorkManager workManager2 = this.workManager;
        if (workManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        WorkContinuation beginUniqueWork2 = workManager2.beginUniqueWork(this.deletedWorkerId, ExistingWorkPolicy.REPLACE, getScanDeletedWorkRequest());
        Intrinsics.checkExpressionValueIsNotNull(beginUniqueWork2, "workManager.beginUniqueW…ScanDeletedWorkRequest())");
        WorkerHelper workerHelper = this.workerHelper;
        if (workerHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workerHelper");
        }
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new WorkContinuation[]{beginUniqueWork, beginUniqueWork2});
        Operation enqueue = workerHelper.combineWorkContinuations(listOf).then(getDedupeWorkRequest$default(this, 0, 1, null)).enqueue();
        Intrinsics.checkExpressionValueIsNotNull(enqueue, "workerHelper.combineWork…))\n            .enqueue()");
        return enqueue;
    }

    static /* synthetic */ Operation performScan$default(DiscoveryOperations discoveryOperations, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return discoveryOperations.performScan(z);
    }

    @TargetApi(24)
    private final void scheduleMediaStoreChangeWorker(ExistingWorkPolicy existingWorkPolicy) {
        this.logger.d("DiscoveryOperations", "Enqueuing MediaStore change work");
        WorkManager workManager = this.workManager;
        if (workManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        workManager.enqueueUniqueWork("media_store", existingWorkPolicy, getMediaStoreChangeWorkRequest());
    }

    private final void setMediaScanReceiverEnabledSetting(boolean z) {
        int i = z ? 1 : 2;
        Context applicationContext = this.discoveryConfiguration.getApplicationContext();
        applicationContext.getPackageManager().setComponentEnabledSetting(new ComponentName(applicationContext, MediaScannerReceiver.class), i, 1);
    }

    private final Data.Builder withDefaultParams(@NotNull Data.Builder builder) {
        builder.putString("account_id", this.discoveryConfiguration.getHashedDirectedId());
        SystemUtil systemUtil = this.systemUtil;
        if (systemUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
        }
        builder.putLong(DiscoveryOperationsKt.ENQUEUED_TIME, systemUtil.elapsedRealTimeMillis());
        return builder;
    }

    @AnyThread
    public final boolean addContentChangeObserver(@NotNull LocalContentChangeObserver observer, @NotNull Scheduler scheduler) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Intrinsics.checkParameterIsNotNull(scheduler, "scheduler");
        ContentChangeNotifier contentChangeNotifier = this.contentChangeNotifier;
        if (contentChangeNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentChangeNotifier");
        }
        return contentChangeNotifier.addObserver(observer, scheduler);
    }

    @AnyThread
    public final void cancel() {
        this.logger.d("DiscoveryOperations", "Cancelling all discovery work");
        WorkManager workManager = this.workManager;
        if (workManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        workManager.cancelAllWorkByTag(this.discoveryTag);
        WorkManager workManager2 = this.workManager;
        if (workManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        workManager2.pruneWork();
    }

    @AnyThread
    public final void cancelMonitor() {
        this.logger.d("DiscoveryOperations", "Cancelling monitor work");
        WorkManager workManager = this.workManager;
        if (workManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        workManager.cancelAllWorkByTag(this.monitorTag);
    }

    @AnyThread
    public final void clearObservers$AndroidPhotosDiscovery_release() {
        ContentChangeNotifier contentChangeNotifier = this.contentChangeNotifier;
        if (contentChangeNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentChangeNotifier");
        }
        contentChangeNotifier.clear$AndroidPhotosDiscovery_release();
    }

    @AnyThread
    public final void dedupeOperation(int i) {
        Logger logger = this.logger;
        logger.d("DiscoveryOperations", "Enqueuing Dedupe stage: " + i);
        WorkManager workManager = this.workManager;
        if (workManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        workManager.enqueueUniqueWork(GeneratedOutlineSupport1.outline49(DiscoveryOperationsKt.DISCOVERY_TAG_DEDUPE, i), ExistingWorkPolicy.APPEND, getDedupeWorkRequest(i));
    }

    @AnyThread
    public final void disable() {
        this.logger.i("DiscoveryOperations", "Disabling Discovery");
        this.enabled = false;
        setMediaScanReceiverEnabledSetting(false);
        WorkManager workManager = this.workManager;
        if (workManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        workManager.cancelAllWorkByTag("DiscoveryOperations");
    }

    @AnyThread
    public final void enable() {
        this.logger.i("DiscoveryOperations", "Enabling Discovery");
        this.enabled = true;
        setMediaScanReceiverEnabledSetting(true);
        scheduleMediaStoreWorkerIfNeeded$AndroidPhotosDiscovery_release(ExistingWorkPolicy.KEEP);
        enqueueMonitorWorkIfNeeded$default(this, false, 1, null);
    }

    @AnyThread
    public final void enqueueMonitorWorkIfNeeded(boolean z) {
        if (!this.discoveryConfiguration.getUseMonitor() || this.discoveryConfiguration.getOnlyScanCamera()) {
            return;
        }
        this.logger.d("DiscoveryOperations", "Enqueuing monitor work");
        long j = this.monitorStartOffset;
        WorkerHelper workerHelper = this.workerHelper;
        if (workerHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workerHelper");
        }
        long timeDifferenceForNextAvailableTarget$default = j + WorkerHelper.getTimeDifferenceForNextAvailableTarget$default(workerHelper, 3, 0, 0, 0, 14, null);
        WorkManager workManager = this.workManager;
        if (workManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        String str = this.monitorTag;
        ExistingWorkPolicy existingWorkPolicy = ExistingWorkPolicy.REPLACE;
        if (z) {
            timeDifferenceForNextAvailableTarget$default = 0;
        }
        workManager.enqueueUniqueWork(str, existingWorkPolicy, getMonitorWorkRequest(timeDifferenceForNextAvailableTarget$default));
    }

    @NotNull
    public final ContentChangeNotifier getContentChangeNotifier$AndroidPhotosDiscovery_release() {
        ContentChangeNotifier contentChangeNotifier = this.contentChangeNotifier;
        if (contentChangeNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentChangeNotifier");
        }
        return contentChangeNotifier;
    }

    @NotNull
    public final SystemUtil getSystemUtil$AndroidPhotosDiscovery_release() {
        SystemUtil systemUtil = this.systemUtil;
        if (systemUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
        }
        return systemUtil;
    }

    @AnyThread
    @NotNull
    public final ListenableFuture<List<WorkInfo>> getWorkInfo() {
        WorkManager workManager = this.workManager;
        if (workManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        ListenableFuture<List<WorkInfo>> workInfosByTag = workManager.getWorkInfosByTag("DiscoveryOperations");
        Intrinsics.checkExpressionValueIsNotNull(workInfosByTag, "workManager.getWorkInfosByTag(TAG)");
        return workInfosByTag;
    }

    @NotNull
    public final WorkManager getWorkManager$AndroidPhotosDiscovery_release() {
        WorkManager workManager = this.workManager;
        if (workManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        return workManager;
    }

    @NotNull
    public final WorkerHelper getWorkerHelper$AndroidPhotosDiscovery_release() {
        WorkerHelper workerHelper = this.workerHelper;
        if (workerHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workerHelper");
        }
        return workerHelper;
    }

    @AnyThread
    public final boolean removeContentChangeObserver(@NotNull LocalContentChangeObserver observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        ContentChangeNotifier contentChangeNotifier = this.contentChangeNotifier;
        if (contentChangeNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentChangeNotifier");
        }
        return contentChangeNotifier.removeObserver(observer);
    }

    @AnyThread
    @NotNull
    public final Operation rescan() {
        return performScan(true);
    }

    @AnyThread
    @NotNull
    public final Operation scan() {
        return performScan(false);
    }

    @AnyThread
    public final boolean scanWorkPlanned() {
        boolean z;
        WorkManager workManager = this.workManager;
        if (workManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        List<WorkInfo> scanWorkers = workManager.getWorkInfosByTag(ConstantsKt.SCAN_ADDED_WORKER).get();
        WorkManager workManager2 = this.workManager;
        if (workManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        List<WorkInfo> list = workManager2.getWorkInfosByTag(ConstantsKt.SCAN_DELETED_WORKER).get();
        Intrinsics.checkExpressionValueIsNotNull(list, "workManager.getWorkInfos…CAN_DELETED_WORKER).get()");
        scanWorkers.addAll(list);
        Intrinsics.checkExpressionValueIsNotNull(scanWorkers, "scanWorkers");
        if (scanWorkers.isEmpty()) {
            return false;
        }
        for (WorkInfo workInfo : scanWorkers) {
            Intrinsics.checkExpressionValueIsNotNull(workInfo, "workInfo");
            int i = WhenMappings.$EnumSwitchMapping$0[workInfo.getState().ordinal()];
            if (i == 1 || i == 2) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final void scheduleMediaStoreWorkerIfNeeded$AndroidPhotosDiscovery_release(@NotNull ExistingWorkPolicy policy) {
        Intrinsics.checkParameterIsNotNull(policy, "policy");
        int i = Build.VERSION.SDK_INT;
        scheduleMediaStoreChangeWorker(policy);
    }

    public final void setContentChangeNotifier$AndroidPhotosDiscovery_release(@NotNull ContentChangeNotifier contentChangeNotifier) {
        Intrinsics.checkParameterIsNotNull(contentChangeNotifier, "<set-?>");
        this.contentChangeNotifier = contentChangeNotifier;
    }

    public final void setSystemUtil$AndroidPhotosDiscovery_release(@NotNull SystemUtil systemUtil) {
        Intrinsics.checkParameterIsNotNull(systemUtil, "<set-?>");
        this.systemUtil = systemUtil;
    }

    public final void setWorkManager$AndroidPhotosDiscovery_release(@NotNull WorkManager workManager) {
        Intrinsics.checkParameterIsNotNull(workManager, "<set-?>");
        this.workManager = workManager;
    }

    public final void setWorkerHelper$AndroidPhotosDiscovery_release(@NotNull WorkerHelper workerHelper) {
        Intrinsics.checkParameterIsNotNull(workerHelper, "<set-?>");
        this.workerHelper = workerHelper;
    }

    private final OneTimeWorkRequest.Builder withDefaultParams(@NotNull OneTimeWorkRequest.Builder builder, Data data) {
        builder.addTag("DiscoveryOperations");
        builder.setInputData(data);
        return builder;
    }
}
