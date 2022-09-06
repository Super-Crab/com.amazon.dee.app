package com.amazon.photos.uploader.blockers;

import androidx.annotation.VisibleForTesting;
import androidx.work.Operation;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.uploader.ResultMetadata;
import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier;
import com.amazon.photos.uploader.internal.utils.PersistentLogger;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import com.amazon.photos.uploader.observables.AbandonedRequestInfo;
import com.amazon.photos.uploader.observables.UploadRequestObservable;
import com.amazon.photos.uploader.observables.UploadRequestObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt__MathJVMKt;
import kotlin.random.Random;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BackoffBlockerEvaluator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 A2\u00020\u00012\u00020\u0002:\u0001AB-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u0004\u0018\u00010 J\u001d\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001eH\u0001¢\u0006\u0002\b#J\n\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020'H\u0002J\u0010\u0010(\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020*H\u0016J\u0016\u0010+\u001a\u00020\u001b2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-H\u0016J\u0018\u0010/\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020*2\u0006\u00100\u001a\u00020%H\u0016J\"\u00101\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020*2\b\u00102\u001a\u0004\u0018\u0001032\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J \u00104\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020*2\u0006\u00105\u001a\u00020\u000f2\u0006\u00106\u001a\u00020\u000fH\u0016J\u0010\u00107\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020*H\u0016J\u0018\u00108\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020*2\u0006\u00109\u001a\u00020:H\u0016J\n\u0010;\u001a\u0004\u0018\u00010%H\u0016J\u0015\u0010<\u001a\u00020\u001b2\u0006\u0010=\u001a\u00020\u000fH\u0001¢\u0006\u0002\b>J\u0010\u0010?\u001a\u00020'2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010@\u001a\u00020'2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lcom/amazon/photos/uploader/blockers/BackoffBlockerEvaluator;", "Lcom/amazon/photos/uploader/blockers/GlobalBlockerEvaluator;", "Lcom/amazon/photos/uploader/observables/UploadRequestObserver;", "logger", "Lcom/amazon/photos/uploader/internal/utils/PersistentLogger;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "schedulingCallback", "Lcom/amazon/photos/uploader/SchedulingCallback;", "uploadRequestUpdatesNotifier", "Lcom/amazon/photos/uploader/internal/UploadRequestUpdatesNotifier;", "systemUtil", "Lcom/amazon/photos/uploader/internal/utils/SystemUtil;", "(Lcom/amazon/photos/uploader/internal/utils/PersistentLogger;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/uploader/SchedulingCallback;Lcom/amazon/photos/uploader/internal/UploadRequestUpdatesNotifier;Lcom/amazon/photos/uploader/internal/utils/SystemUtil;)V", "backOffUntil", "", "currentBackOffTier", "", "failureHistory", "", "getLogger", "()Lcom/amazon/photos/uploader/internal/utils/PersistentLogger;", "getMetrics", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "getSchedulingCallback", "()Lcom/amazon/photos/uploader/SchedulingCallback;", "addToHistoryIfRequired", "", "currentTime", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "clearBackoffPolicy", "Landroidx/work/Operation;", "computeBackoffDuration", "backOffTier", "computeBackoffDuration$AndroidPhotosUploader_release", "getBlocker", "Lcom/amazon/photos/uploader/blockers/Blocker;", "isInsideBackoffWindow", "", "onRequestAdded", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "onRequestsAbandoned", "abandonedRequestInfoList", "", "Lcom/amazon/photos/uploader/observables/AbandonedRequestInfo;", "onUploadBlocked", "blocker", "onUploadFailed", "ex", "", "onUploadProgressUpdate", "currentProgress", "maxProgress", "onUploadStarted", "onUploadSucceeded", "resultMetadata", "Lcom/amazon/photos/uploader/ResultMetadata;", "queryBlocker", "scheduleWakeup", "backOffTime", "scheduleWakeup$AndroidPhotosUploader_release", "shouldBackOffForHistory", "shouldBackOffImmediately", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class BackoffBlockerEvaluator implements GlobalBlockerEvaluator, UploadRequestObserver {
    public static final long FIFTEEN_MINUTES = 900000;
    private static final long FIVE_SECONDS = 5000;
    public static final long LONG_BACKOFF = 900000;
    public static final long LONG_BACKOFF_METERED_OR_WEAK_CONNECTION = 3600000;
    public static final int MAX_BACKOFF_TIER = 5;
    public static final int MAX_CONSECUTIVE_ERRORS = 3;
    public static final long ONE_HOUR = 3600000;
    private static final long ONE_MINUTE = 60000;
    private static final long ONE_SECOND = 1000;
    @NotNull
    public static final String TAG = "BackoffBlockerEvaluator";
    private long backOffUntil;
    private int currentBackOffTier;
    private final List<Long> failureHistory;
    @NotNull
    private final PersistentLogger logger;
    @NotNull
    private final Metrics metrics;
    @NotNull
    private final SchedulingCallback schedulingCallback;
    private final SystemUtil systemUtil;
    public static final Companion Companion = new Companion(null);
    private static final long MAX_TIME_WINDOW = Companion.calculateMaxBackOffMillis(5);

    /* compiled from: BackoffBlockerEvaluator.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\tH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/photos/uploader/blockers/BackoffBlockerEvaluator$Companion;", "", "()V", "FIFTEEN_MINUTES", "", "FIVE_SECONDS", "LONG_BACKOFF", "LONG_BACKOFF_METERED_OR_WEAK_CONNECTION", "MAX_BACKOFF_TIER", "", "MAX_CONSECUTIVE_ERRORS", "MAX_TIME_WINDOW", "getMAX_TIME_WINDOW", "()J", "ONE_HOUR", "ONE_MINUTE", "ONE_SECOND", "TAG", "", "calculateMaxBackOffMillis", "tier", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final long calculateMaxBackOffMillis(int i) {
            long roundToLong;
            roundToLong = MathKt__MathJVMKt.roundToLong(Math.pow(2.0d, i));
            return roundToLong * 1000;
        }

        public final long getMAX_TIME_WINDOW() {
            return BackoffBlockerEvaluator.MAX_TIME_WINDOW;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[UploadErrorCategory.values().length];

        static {
            $EnumSwitchMapping$0[UploadErrorCategory.INVALID_PARAMETER.ordinal()] = 1;
            $EnumSwitchMapping$0[UploadErrorCategory.INVALID_PARAMETER_FILE_LENGTH_MISMATCH.ordinal()] = 2;
            $EnumSwitchMapping$0[UploadErrorCategory.SERVER_ERROR.ordinal()] = 3;
            $EnumSwitchMapping$0[UploadErrorCategory.UNKNOWN_ERROR.ordinal()] = 4;
            $EnumSwitchMapping$0[UploadErrorCategory.OTHER_KNOWN_ERROR.ordinal()] = 5;
            $EnumSwitchMapping$0[UploadErrorCategory.TOO_MANY_REQUESTS.ordinal()] = 6;
        }
    }

    public BackoffBlockerEvaluator(@NotNull PersistentLogger logger, @NotNull Metrics metrics, @NotNull SchedulingCallback schedulingCallback, @NotNull UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier, @NotNull SystemUtil systemUtil) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        Intrinsics.checkParameterIsNotNull(uploadRequestUpdatesNotifier, "uploadRequestUpdatesNotifier");
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        this.logger = logger;
        this.metrics = metrics;
        this.schedulingCallback = schedulingCallback;
        this.systemUtil = systemUtil;
        UploadRequestObservable.DefaultImpls.addObserver$default(uploadRequestUpdatesNotifier, this, null, 2, null);
        this.failureHistory = new ArrayList();
    }

    private final void addToHistoryIfRequired(long j, UploadErrorCategory uploadErrorCategory) {
        switch (WhenMappings.$EnumSwitchMapping$0[uploadErrorCategory.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                this.failureHistory.add(Long.valueOf(j));
                return;
            default:
                return;
        }
    }

    private final boolean isInsideBackoffWindow() {
        return this.systemUtil.currentTimeMillis() < this.backOffUntil;
    }

    private final boolean shouldBackOffForHistory(UploadErrorCategory uploadErrorCategory) {
        return (uploadErrorCategory == UploadErrorCategory.INVALID_PARAMETER || uploadErrorCategory == UploadErrorCategory.INVALID_PARAMETER_FILE_LENGTH_MISMATCH) && this.failureHistory.size() >= 3;
    }

    private final boolean shouldBackOffImmediately(UploadErrorCategory uploadErrorCategory) {
        return uploadErrorCategory == UploadErrorCategory.SERVER_ERROR || uploadErrorCategory == UploadErrorCategory.UNKNOWN_ERROR || uploadErrorCategory == UploadErrorCategory.OTHER_KNOWN_ERROR || uploadErrorCategory == UploadErrorCategory.TOO_MANY_REQUESTS;
    }

    @Nullable
    public final Operation clearBackoffPolicy() {
        this.logger.iPersistent(TAG, "Resetting backoff state.");
        boolean isInsideBackoffWindow = isInsideBackoffWindow();
        if (this.currentBackOffTier > 0) {
            this.metrics.recordCustomMetric(TAG, new ClientMetric().addCounter(BackoffBlockerEvaluator$clearBackoffPolicy$clientMetric$1.INSTANCE, this.currentBackOffTier).withTagName(TAG), new MetricRecordingType[0]);
        }
        this.failureHistory.clear();
        this.currentBackOffTier = 0;
        this.backOffUntil = 0L;
        if (isInsideBackoffWindow) {
            return this.schedulingCallback.reevaluateBlockers();
        }
        return null;
    }

    @VisibleForTesting
    public final long computeBackoffDuration$AndroidPhotosUploader_release(int i, @NotNull UploadErrorCategory errorCategory) {
        Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
        NetworkState networkState = this.systemUtil.getNetworkState();
        boolean z = networkState.isMetered() || (!networkState.isWifi() && !networkState.isEthernet());
        if (errorCategory == UploadErrorCategory.TOO_MANY_REQUESTS) {
            this.metrics.recordSimpleEvent(TAG, BackoffBlockerEvaluator$computeBackoffDuration$1.INSTANCE, new MetricRecordingType[0]);
            long nextLong = Random.Default.nextLong(900000L, 3600000L);
            PersistentLogger persistentLogger = this.logger;
            persistentLogger.iPersistent(TAG, "Server throttling the request - backing off for " + nextLong + " ms.");
            return nextLong;
        } else if (z && i > 5) {
            this.metrics.recordSimpleEvent(TAG, BackoffBlockerEvaluator$computeBackoffDuration$2.INSTANCE, new MetricRecordingType[0]);
            this.logger.iPersistent(TAG, "Exceeded max backoff tier for metered or weak connection - backing off for 3600000 ms.");
            return 3600000L;
        } else if (!z && i > 5) {
            this.metrics.recordSimpleEvent(TAG, BackoffBlockerEvaluator$computeBackoffDuration$3.INSTANCE, new MetricRecordingType[0]);
            this.logger.iPersistent(TAG, "Exceeded max backoff tier - backing off for 900000 ms.");
            return 900000L;
        } else {
            this.metrics.recordSimpleEvent(TAG, BackoffBlockerEvaluator$computeBackoffDuration$4.INSTANCE, new MetricRecordingType[0]);
            long calculateMaxBackOffMillis = Companion.calculateMaxBackOffMillis(i);
            long nextLong2 = Random.Default.nextLong(calculateMaxBackOffMillis);
            PersistentLogger persistentLogger2 = this.logger;
            persistentLogger2.i(TAG, "Escalating backoff tier to " + i + ".Backing off for " + nextLong2 + " ms. (max: " + calculateMaxBackOffMillis + ')');
            return nextLong2;
        }
    }

    @Override // com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator
    @Nullable
    public Blocker getBlocker() {
        return queryBlocker();
    }

    @NotNull
    public final PersistentLogger getLogger() {
        return this.logger;
    }

    @NotNull
    public final Metrics getMetrics() {
        return this.metrics;
    }

    @NotNull
    public final SchedulingCallback getSchedulingCallback() {
        return this.schedulingCallback;
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onRequestAdded(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onRequestsAbandoned(@NotNull List<AbandonedRequestInfo> abandonedRequestInfoList) {
        Intrinsics.checkParameterIsNotNull(abandonedRequestInfoList, "abandonedRequestInfoList");
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadBlocked(@NotNull UploadRequest uploadRequest, @NotNull Blocker blocker) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(blocker, "blocker");
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadBlocked(@NotNull UploadRequest uploadRequest, @NotNull Set<? extends Blocker> blockers) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(blockers, "blockers");
        UploadRequestObserver.DefaultImpls.onUploadBlocked(this, uploadRequest, blockers);
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadFailed(@NotNull UploadRequest uploadRequest, @Nullable Throwable th, @NotNull UploadErrorCategory errorCategory) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
        long currentTimeMillis = this.systemUtil.currentTimeMillis();
        List<Long> list = this.failureHistory;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it2 = list.iterator();
        while (true) {
            boolean z = true;
            if (!it2.hasNext()) {
                break;
            }
            Object next = it2.next();
            if (((Number) next).longValue() <= currentTimeMillis - MAX_TIME_WINDOW) {
                z = false;
            }
            if (z) {
                arrayList.add(next);
            }
        }
        if (this.failureHistory.isEmpty()) {
            clearBackoffPolicy();
        }
        this.logger.iPersistent(TAG, "Failure detected - category " + errorCategory + " - consecutive failures: " + this.failureHistory.size());
        if (shouldBackOffImmediately(errorCategory) || shouldBackOffForHistory(errorCategory)) {
            this.failureHistory.clear();
            this.currentBackOffTier++;
            long computeBackoffDuration$AndroidPhotosUploader_release = computeBackoffDuration$AndroidPhotosUploader_release(this.currentBackOffTier, errorCategory);
            this.backOffUntil = currentTimeMillis + computeBackoffDuration$AndroidPhotosUploader_release;
            scheduleWakeup$AndroidPhotosUploader_release(computeBackoffDuration$AndroidPhotosUploader_release);
        }
        addToHistoryIfRequired(currentTimeMillis, errorCategory);
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadProgressUpdate(@NotNull UploadRequest uploadRequest, long j, long j2) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadStarted(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadSucceeded(@NotNull UploadRequest uploadRequest, @NotNull ResultMetadata resultMetadata) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(resultMetadata, "resultMetadata");
        if ((!this.failureHistory.isEmpty()) || this.currentBackOffTier > 0) {
            this.logger.iPersistent(TAG, "Clearing backoff due to successful upload.");
            clearBackoffPolicy();
        }
    }

    @Override // com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator
    @Nullable
    public Blocker queryBlocker() {
        if (isInsideBackoffWindow()) {
            return BackoffBlocker.INSTANCE;
        }
        return null;
    }

    @VisibleForTesting
    public final void scheduleWakeup$AndroidPhotosUploader_release(long j) {
        if (j < 5000) {
            BuildersKt__Builders_commonKt.async$default(GlobalScope.INSTANCE, null, null, new BackoffBlockerEvaluator$scheduleWakeup$1(this, j, null), 3, null);
        } else {
            this.schedulingCallback.reevaluateBlockers$AndroidPhotosUploader_release(j);
        }
    }
}
