package com.amazon.photos.uploader;

import android.app.Notification;
import android.app.NotificationManager;
import androidx.annotation.WorkerThread;
import androidx.work.Operation;
import androidx.work.WorkManager;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.deecomms.common.Constants;
import com.amazon.photos.uploader.blockers.BackoffBlockerEvaluator;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.blockers.PauseResumeState;
import com.amazon.photos.uploader.internal.InternalEvaluator;
import com.amazon.photos.uploader.internal.NotificationUpdatesNotifier;
import com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier;
import com.amazon.photos.uploader.internal.UploadSummaryTracker;
import com.amazon.photos.uploader.internal.dagger.component.UploadFrameworkComponent;
import com.amazon.photos.uploader.internal.dao.UploadRequestDao;
import com.amazon.photos.uploader.internal.metrics.UploadMetrics;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import com.amazon.photos.uploader.internal.workers.SchedulerWorkerKt;
import com.amazon.photos.uploader.log.UploadLogger;
import com.amazon.photos.uploader.observables.AbandonedRequestInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploaderOperations.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0098\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 ´\u00012\u00020\u0001:\u0002´\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010d\u001a\b\u0012\u0004\u0012\u00020f0e2\f\u0010g\u001a\b\u0012\u0004\u0012\u00020h0eH\u0002J\u0014\u0010i\u001a\b\u0012\u0004\u0012\u00020k0j2\u0006\u0010l\u001a\u00020hJ \u0010m\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020h\u0012\u0004\u0012\u00020k0n0j2\u0006\u0010o\u001a\u00020pJ\u0016\u0010q\u001a\u00020r2\f\u0010g\u001a\b\u0012\u0004\u0012\u00020h0eH\u0002J\u0016\u0010s\u001a\u00020r2\f\u0010t\u001a\b\u0012\u0004\u0012\u00020u0eH\u0002J&\u0010v\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020h\u0012\u0004\u0012\u00020k0n0j2\f\u0010g\u001a\b\u0012\u0004\u0012\u00020h0eJ&\u0010w\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020h\u0012\u0004\u0012\u00020k0n0j2\f\u0010x\u001a\b\u0012\u0004\u0012\u00020p0eJ\u0014\u0010y\u001a\b\u0012\u0004\u0012\u00020r0j2\u0006\u0010z\u001a\u00020pJ\u001c\u0010{\u001a\b\u0012\u0004\u0012\u00020r0j2\u0006\u0010z\u001a\u00020p2\u0006\u0010|\u001a\u00020kJ\u001a\u0010}\u001a\b\u0012\u0004\u0012\u00020r0j2\f\u0010~\u001a\b\u0012\u0004\u0012\u00020p0eJ\"\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020r0j2\f\u0010~\u001a\b\u0012\u0004\u0012\u00020p0e2\u0006\u0010|\u001a\u00020kJ\r\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020r0jJ\r\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020r0jJ\u000f\u0010\u0082\u0001\u001a\u00020rH\u0001¢\u0006\u0003\b\u0083\u0001J\u001c\u0010\u0084\u0001\u001a\b\u0012\u0004\u0012\u00020u0e2\r\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020p0eJ-\u0010\u0086\u0001\u001a\u00020r2\f\u0010g\u001a\b\u0012\u0004\u0012\u00020h0e2\u0014\u0010\u0087\u0001\u001a\u000f\u0012\u0004\u0012\u00020h\u0012\u0004\u0012\u00020k0\u0088\u0001H\u0002J\b\u0010\u0089\u0001\u001a\u00030\u008a\u0001J#\u0010\u008b\u0001\u001a\u000e\u0012\u0004\u0012\u00020h\u0012\u0004\u0012\u00020k0n2\f\u0010t\u001a\b\u0012\u0004\u0012\u00020u0eH\u0002J#\u0010\u008c\u0001\u001a\u000e\u0012\u0004\u0012\u00020h\u0012\u0004\u0012\u00020k0n2\f\u0010g\u001a\b\u0012\u0004\u0012\u00020h0eH\u0002J\r\u0010\u008d\u0001\u001a\b\u0012\u0004\u0012\u00020r0jJ\u001c\u0010\u008e\u0001\u001a\u00020r2\u0007\u0010\u008f\u0001\u001a\u00020p2\b\u0010\u0090\u0001\u001a\u00030\u0091\u0001H\u0002J\u001b\u0010\u0092\u0001\u001a\u00020r2\u0007\u0010\u008f\u0001\u001a\u00020p2\u0007\u0010\u0093\u0001\u001a\u00020pH\u0002J\r\u0010\u0094\u0001\u001a\b\u0012\u0004\u0012\u00020r0jJ\u0011\u0010\u0095\u0001\u001a\n\u0012\u0005\u0012\u00030\u0097\u00010\u0096\u0001H\u0003J/\u0010\u0098\u0001\u001a\u0017\u0012\u0005\u0012\u00030\u0099\u0001\u0012\f\u0012\n\u0012\u0005\u0012\u00030\u0097\u00010\u009a\u00010n2\u000f\u0010\u009b\u0001\u001a\n\u0012\u0005\u0012\u00030\u0099\u00010\u009a\u0001H\u0003J/\u0010\u009c\u0001\u001a\u0017\u0012\u0005\u0012\u00030\u0099\u0001\u0012\f\u0012\n\u0012\u0005\u0012\u00030\u0097\u00010\u009a\u00010n2\u000f\u0010\u009b\u0001\u001a\n\u0012\u0005\u0012\u00030\u0099\u00010\u009a\u0001H\u0003J\r\u0010\u009d\u0001\u001a\b\u0012\u0004\u0012\u00020r0jJ\r\u0010\u009e\u0001\u001a\b\u0012\u0004\u0012\u00020r0jJ\t\u0010\u009f\u0001\u001a\u00020rH\u0007J-\u0010 \u0001\u001a\b\u0012\u0004\u0012\u00020h0j2\u0007\u0010¡\u0001\u001a\u00020u2\u0007\u0010¢\u0001\u001a\u00020p2\n\b\u0002\u0010£\u0001\u001a\u00030¤\u0001H\u0007J9\u0010¥\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020h0e0j2\r\u0010¦\u0001\u001a\b\u0012\u0004\u0012\u00020u0e2\u0007\u0010¢\u0001\u001a\u00020p2\n\b\u0002\u0010£\u0001\u001a\u00030¤\u0001H\u0007J1\u0010§\u0001\u001a\b\u0012\u0004\u0012\u00020u0e2\r\u0010¦\u0001\u001a\b\u0012\u0004\u0012\u00020u0e2\u0007\u0010¢\u0001\u001a\u00020p2\b\u0010£\u0001\u001a\u00030¤\u0001H\u0002J1\u0010¨\u0001\u001a\b\u0012\u0004\u0012\u00020h0e2\r\u0010¦\u0001\u001a\b\u0012\u0004\u0012\u00020u0e2\u0007\u0010¢\u0001\u001a\u00020p2\b\u0010£\u0001\u001a\u00030¤\u0001H\u0002J\u0013\u0010©\u0001\u001a\u00020r2\b\u0010ª\u0001\u001a\u00030«\u0001H\u0002J\t\u0010¬\u0001\u001a\u00020rH\u0002J0\u0010\u00ad\u0001\u001a\u00020\u000f2\b\u0010®\u0001\u001a\u00030¯\u00012\b\u0010°\u0001\u001a\u00030\u0091\u00012\b\u0010±\u0001\u001a\u00030²\u00012\t\b\u0002\u0010³\u0001\u001a\u00020\u000fR$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\u0010@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u0016@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\u001c@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R$\u0010#\u001a\u00020\"2\u0006\u0010\u0007\u001a\u00020\"@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010)\u001a\u00020(2\u0006\u0010\u0007\u001a\u00020(@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R$\u0010/\u001a\u00020.2\u0006\u0010\u0007\u001a\u00020.@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R$\u00105\u001a\u0002042\u0006\u0010\u0007\u001a\u000204@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R$\u0010;\u001a\u00020:2\u0006\u0010\u0007\u001a\u00020:@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R$\u0010A\u001a\u00020@2\u0006\u0010\u0007\u001a\u00020@@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER$\u0010G\u001a\u00020F2\u0006\u0010\u0007\u001a\u00020F@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR$\u0010M\u001a\u00020L2\u0006\u0010\u0007\u001a\u00020L@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR$\u0010S\u001a\u00020R2\u0006\u0010\u0007\u001a\u00020R@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR$\u0010Y\u001a\u00020X2\u0006\u0010\u0007\u001a\u00020X@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R$\u0010_\u001a\u00020^2\u0006\u0010\u0007\u001a\u00020^@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010a\"\u0004\bb\u0010c¨\u0006µ\u0001"}, d2 = {"Lcom/amazon/photos/uploader/UploaderOperations;", "", JsonFields.COMPONENT, "Lcom/amazon/photos/uploader/internal/dagger/component/UploadFrameworkComponent;", "operationsExecutor", "Ljava/util/concurrent/ExecutorService;", "(Lcom/amazon/photos/uploader/internal/dagger/component/UploadFrameworkComponent;Ljava/util/concurrent/ExecutorService;)V", "<set-?>", "Lcom/amazon/photos/uploader/blockers/BackoffBlockerEvaluator;", "backoffBlockerEvaluator", "getBackoffBlockerEvaluator$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/blockers/BackoffBlockerEvaluator;", "setBackoffBlockerEvaluator$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/blockers/BackoffBlockerEvaluator;)V", "destroyed", "", "Lcom/amazon/photos/uploader/internal/InternalEvaluator;", "internalEvaluator", "getInternalEvaluator$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/internal/InternalEvaluator;", "setInternalEvaluator$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/internal/InternalEvaluator;)V", "Lcom/amazon/photos/uploader/log/UploadLogger;", "logger", "getLogger$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/log/UploadLogger;", "setLogger$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/log/UploadLogger;)V", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "metrics", "getMetrics$AndroidPhotosUploader_release", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "setMetrics$AndroidPhotosUploader_release", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;", "notificationUpdatesNotifier", "getNotificationUpdatesNotifier$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;", "setNotificationUpdatesNotifier$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;)V", "Lcom/amazon/photos/uploader/blockers/PauseResumeState;", "pauseResumeState", "getPauseResumeState$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/blockers/PauseResumeState;", "setPauseResumeState$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/blockers/PauseResumeState;)V", "Lcom/amazon/photos/uploader/QueueManager;", "queueManager", "getQueueManager$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/QueueManager;", "setQueueManager$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/QueueManager;)V", "Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao;", "requestDao", "getRequestDao$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao;", "setRequestDao$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao;)V", "Lcom/amazon/photos/uploader/SchedulingCallback;", "schedulingCallback", "getSchedulingCallback$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/SchedulingCallback;", "setSchedulingCallback$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/SchedulingCallback;)V", "Lcom/amazon/photos/uploader/internal/utils/SystemUtil;", "systemUtil", "getSystemUtil$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/internal/utils/SystemUtil;", "setSystemUtil$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/internal/utils/SystemUtil;)V", "Lcom/amazon/photos/uploader/UploadFrameworkContext;", "uploadFrameworkContext", "getUploadFrameworkContext$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/UploadFrameworkContext;", "setUploadFrameworkContext$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/UploadFrameworkContext;)V", "Lcom/amazon/photos/uploader/internal/UploadRequestUpdatesNotifier;", "uploadRequestUpdatesNotifier", "getUploadRequestUpdatesNotifier$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/internal/UploadRequestUpdatesNotifier;", "setUploadRequestUpdatesNotifier$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/internal/UploadRequestUpdatesNotifier;)V", "Lcom/amazon/photos/uploader/internal/UploadSummaryTracker;", "uploadSummaryTracker", "getUploadSummaryTracker$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/internal/UploadSummaryTracker;", "setUploadSummaryTracker$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/internal/UploadSummaryTracker;)V", "Lcom/amazon/photos/uploader/Uploader;", "uploader", "getUploader$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/Uploader;", "setUploader$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/Uploader;)V", "Landroidx/work/WorkManager;", "workManager", "getWorkManager$AndroidPhotosUploader_release", "()Landroidx/work/WorkManager;", "setWorkManager$AndroidPhotosUploader_release", "(Landroidx/work/WorkManager;)V", "cancelLimitedUploadHelper", "", "Landroidx/work/Operation;", "requestIds", "", "cancelUpload", "Lcom/amazon/photos/uploader/UploadOperation;", "Lcom/amazon/photos/uploader/UploadState;", "requestId", "cancelUploadByFilePath", "", "filepath", "", "cancelUploadByIdHelper", "", "cancelUploadRequestHelper", "requests", "Lcom/amazon/photos/uploader/UploadRequest;", "cancelUploads", "cancelUploadsByFilePath", "filepaths", "cancelUploadsForQueue", "queueName", "cancelUploadsForQueueWithState", "state", "cancelUploadsForQueues", "queueNames", "cancelUploadsForQueuesWithState", "clearBackoff", "clearCompletedUploadRecords", "destroy", "destroy$AndroidPhotosUploader_release", "getExistingUploadRequests", "filePaths", "getLimitedRequestStates", "requestStates", "", "getPauseResumeState", "Lcom/amazon/photos/uploader/PauseResume;", "getRequestStates", "getRequestStatesByIds", "invalidateBlockers", "logMetricWithCount", "metric", "count", "", "logMetricWithMetadata", "metadata", "pauseAllUploads", "queryGlobalBlockers", "", "Lcom/amazon/photos/uploader/blockers/Blocker;", "queryQueueBlockers", "Lcom/amazon/photos/uploader/Queue;", "", "queues", "queryRequestBlockers", "reevaluteBlockers", "resumeAllUploads", "reupdateBlockersSync", "scheduleUpload", "uploadRequest", "queue", "strategy", "Lcom/amazon/photos/uploader/UploadStrategy;", "scheduleUploads", "uploadRequests", "schedulerLimitUploadsHelper", "schedulerUploadsHelper", "submitToExecutorIfNotShutdown", "task", "Ljava/lang/Runnable;", "throwDestroyedIfNeeded", "updateNotification", "notificationManager", "Landroid/app/NotificationManager;", Constants.BUNDLE_KEY_NOTIFICATION_ID, "notification", "Landroid/app/Notification;", "isLastNotification", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploaderOperations {
    public static final int BULK_CANCEL_THRESHOLD = 50;
    public static final int CANCEL_BATCH_SIZE = 500;
    @NotNull
    private static final Set<UploadState> COMPLETED_STATES;
    public static final Companion Companion = new Companion(null);
    public static final int INSERT_BATCH_SIZE = 200;
    public static final int LOG_CLEARED_REQUEST_THRESHOLD = 20;
    @NotNull
    public static final String TAG = "UploadOperations";
    @NotNull
    public BackoffBlockerEvaluator backoffBlockerEvaluator;
    private boolean destroyed;
    @NotNull
    public InternalEvaluator internalEvaluator;
    @NotNull
    public UploadLogger logger;
    @NotNull
    public Metrics metrics;
    @NotNull
    public NotificationUpdatesNotifier notificationUpdatesNotifier;
    private final ExecutorService operationsExecutor;
    @NotNull
    public PauseResumeState pauseResumeState;
    @NotNull
    public QueueManager queueManager;
    @NotNull
    public UploadRequestDao requestDao;
    @NotNull
    public SchedulingCallback schedulingCallback;
    @NotNull
    public SystemUtil systemUtil;
    @NotNull
    public UploadFrameworkContext uploadFrameworkContext;
    @NotNull
    public UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier;
    @NotNull
    public UploadSummaryTracker uploadSummaryTracker;
    @NotNull
    public Uploader uploader;
    @NotNull
    public WorkManager workManager;

    /* compiled from: UploaderOperations.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0080T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/photos/uploader/UploaderOperations$Companion;", "", "()V", "BULK_CANCEL_THRESHOLD", "", "CANCEL_BATCH_SIZE", "COMPLETED_STATES", "", "Lcom/amazon/photos/uploader/UploadState;", "getCOMPLETED_STATES$AndroidPhotosUploader_release", "()Ljava/util/Set;", "INSERT_BATCH_SIZE", "LOG_CLEARED_REQUEST_THRESHOLD", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Set<UploadState> getCOMPLETED_STATES$AndroidPhotosUploader_release() {
            return UploaderOperations.COMPLETED_STATES;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[UploadState.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[UploadState.CANCELLED.ordinal()] = 1;
            $EnumSwitchMapping$0[UploadState.SUCCEEDED.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[UploadStrategy.values().length];
            $EnumSwitchMapping$1[UploadStrategy.ERROR.ordinal()] = 1;
            $EnumSwitchMapping$1[UploadStrategy.REPLACE.ordinal()] = 2;
            $EnumSwitchMapping$1[UploadStrategy.KEEP_EXISTING.ordinal()] = 3;
        }
    }

    static {
        Set<UploadState> of;
        of = SetsKt__SetsKt.setOf((Object[]) new UploadState[]{UploadState.CANCELLED, UploadState.SUCCEEDED});
        COMPLETED_STATES = of;
    }

    public UploaderOperations(@NotNull UploadFrameworkComponent component, @NotNull ExecutorService operationsExecutor) {
        Intrinsics.checkParameterIsNotNull(component, "component");
        Intrinsics.checkParameterIsNotNull(operationsExecutor, "operationsExecutor");
        this.operationsExecutor = operationsExecutor;
        component.inject(this);
    }

    private final List<Operation> cancelLimitedUploadHelper(List<Long> list) {
        List<UploadState> listOf;
        int collectionSizeOrDefault;
        UploadRequest copy;
        UploadRequestDao uploadRequestDao = this.requestDao;
        if (uploadRequestDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestDao");
        }
        Map<UploadState, List<UploadRequest>> cancelUploadRequests = uploadRequestDao.cancelUploadRequests(list);
        ArrayList<UploadRequest> arrayList = new ArrayList();
        ArrayList<UploadRequest> arrayList2 = new ArrayList();
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new UploadState[]{UploadState.ENQUEUED, UploadState.RUNNING, UploadState.BLOCKED, UploadState.FAILED});
        for (UploadState uploadState : listOf) {
            List<UploadRequest> list2 = cancelUploadRequests.get(uploadState);
            if (list2 != null) {
                arrayList2.addAll(list2);
                if (uploadState != UploadState.ENQUEUED) {
                    arrayList.addAll(list2);
                }
            }
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
        ArrayList<Number> arrayList3 = new ArrayList(collectionSizeOrDefault);
        for (UploadRequest uploadRequest : arrayList) {
            arrayList3.add(Long.valueOf(uploadRequest.getId()));
        }
        ArrayList arrayList4 = new ArrayList();
        for (Number number : arrayList3) {
            long longValue = number.longValue();
            WorkManager workManager = this.workManager;
            if (workManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("workManager");
            }
            Operation cancelAllWorkByTag = workManager.cancelAllWorkByTag(SchedulerWorkerKt.REQUEST_TAG_PREFIX + longValue);
            Intrinsics.checkExpressionValueIsNotNull(cancelAllWorkByTag, "workManager.cancelAllWor…\"$REQUEST_TAG_PREFIX$it\")");
            arrayList4.add(cancelAllWorkByTag);
        }
        ArrayList arrayList5 = new ArrayList();
        for (UploadRequest uploadRequest2 : arrayList2) {
            copy = uploadRequest2.copy((r49 & 1) != 0 ? uploadRequest2.id : 0L, (r49 & 2) != 0 ? uploadRequest2.filePath : null, (r49 & 4) != 0 ? uploadRequest2.uploadPath : null, (r49 & 8) != 0 ? uploadRequest2.contentDate : null, (r49 & 16) != 0 ? uploadRequest2.md5 : null, (r49 & 32) != 0 ? uploadRequest2.visualDigest : null, (r49 & 64) != 0 ? uploadRequest2.suppressDeduplication : false, (r49 & 128) != 0 ? uploadRequest2.renameOnNameConflict : false, (r49 & 256) != 0 ? uploadRequest2.uploadCategory : null, (r49 & 512) != 0 ? uploadRequest2.state : UploadState.CANCELLED, (r49 & 1024) != 0 ? uploadRequest2.queue : null, (r49 & 2048) != 0 ? uploadRequest2.currentProgress : 0L, (r49 & 4096) != 0 ? uploadRequest2.maxProgress : 0L, (r49 & 8192) != 0 ? uploadRequest2.errorCode : null, (r49 & 16384) != 0 ? uploadRequest2.errorCategory : null, (r49 & 32768) != 0 ? uploadRequest2.blocker : null, (r49 & 65536) != 0 ? uploadRequest2.totalAttemptCount : 0, (r49 & 131072) != 0 ? uploadRequest2.attemptCount : 0, (r49 & 262144) != 0 ? uploadRequest2.maxAttemptsExceeded : false, (r49 & 524288) != 0 ? uploadRequest2.creationTimeMillis : 0L, (r49 & 1048576) != 0 ? uploadRequest2.fileSize : 0L, (r49 & 2097152) != 0 ? uploadRequest2.priority : 0, (4194304 & r49) != 0 ? uploadRequest2.addToFamilyVault : false, (r49 & 8388608) != 0 ? uploadRequest2.appData : null, (r49 & 16777216) != 0 ? uploadRequest2.parentId : null, (r49 & 33554432) != 0 ? uploadRequest2.contentUri : null);
            arrayList5.add(new AbandonedRequestInfo(copy, null, UploadErrorCategory.APPLICATION_CANCELLED, AbandonReason.APPLICATION_CANCELLED));
        }
        UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier = this.uploadRequestUpdatesNotifier;
        if (uploadRequestUpdatesNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequestUpdatesNotifier");
        }
        uploadRequestUpdatesNotifier.onRequestsAbandoned$AndroidPhotosUploader_release(arrayList5);
        return arrayList4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelUploadByIdHelper(List<Long> list) {
        Iterable withIndex;
        int collectionSizeOrDefault;
        if (list.isEmpty()) {
            return;
        }
        Uploader uploader = this.uploader;
        if (uploader == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploader");
        }
        uploader.updateUploaderState(UploaderState.CANCELLED);
        SystemUtil systemUtil = this.systemUtil;
        if (systemUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
        }
        long currentTimeMillis = systemUtil.currentTimeMillis();
        withIndex = CollectionsKt___CollectionsKt.withIndex(list);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : withIndex) {
            Integer valueOf = Integer.valueOf(((IndexedValue) obj).getIndex() / 500);
            Object obj2 = linkedHashMap.get(valueOf);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(valueOf, obj2);
            }
            ((List) obj2).add(obj);
        }
        ArrayList<List<Long>> arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Iterable<IndexedValue> iterable = (Iterable) entry.getValue();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10);
            ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
            for (IndexedValue indexedValue : iterable) {
                arrayList2.add(Long.valueOf(((Number) indexedValue.getValue()).longValue()));
            }
            arrayList.add(arrayList2);
        }
        ArrayList<Operation> arrayList3 = new ArrayList();
        for (List<Long> list2 : arrayList) {
            arrayList3.addAll(cancelLimitedUploadHelper(list2));
        }
        for (Operation operation : arrayList3) {
            operation.getResult().get();
        }
        if (list.size() > 50) {
            SchedulingCallback schedulingCallback = this.schedulingCallback;
            if (schedulingCallback == null) {
                Intrinsics.throwUninitializedPropertyAccessException("schedulingCallback");
            }
            schedulingCallback.reevaluateBlockers();
        }
        SystemUtil systemUtil2 = this.systemUtil;
        if (systemUtil2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
        }
        double currentTimeMillis2 = systemUtil2.currentTimeMillis() - currentTimeMillis;
        ClientMetric withTagName = new ClientMetric().addCounter(UploaderOperations$cancelUploadByIdHelper$clientMetric$1.INSTANCE, 1).addCounter(UploaderOperations$cancelUploadByIdHelper$clientMetric$2.INSTANCE, list.size()).addTimer(UploaderOperations$cancelUploadByIdHelper$clientMetric$3.INSTANCE, currentTimeMillis2).addTimer(UploaderOperations$cancelUploadByIdHelper$clientMetric$4.INSTANCE, currentTimeMillis2 / list.size()).withTagName(TAG);
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordCustomMetric(TAG, withTagName, new MetricRecordingType[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelUploadRequestHelper(List<UploadRequest> list) {
        int collectionSizeOrDefault;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (UploadRequest uploadRequest : list) {
            arrayList.add(Long.valueOf(uploadRequest.getId()));
        }
        cancelUploadByIdHelper(arrayList);
    }

    private final void getLimitedRequestStates(List<Long> list, Map<Long, UploadState> map) {
        UploadState[] values;
        int collectionSizeOrDefault;
        for (UploadState uploadState : UploadState.values()) {
            UploadRequestDao uploadRequestDao = this.requestDao;
            if (uploadRequestDao == null) {
                Intrinsics.throwUninitializedPropertyAccessException("requestDao");
            }
            List<UploadRequest> requestsForStateAndIds = uploadRequestDao.getRequestsForStateAndIds(uploadState, list);
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(requestsForStateAndIds, 10);
            ArrayList<Number> arrayList = new ArrayList(collectionSizeOrDefault);
            for (UploadRequest uploadRequest : requestsForStateAndIds) {
                arrayList.add(Long.valueOf(uploadRequest.getId()));
            }
            for (Number number : arrayList) {
                map.put(Long.valueOf(number.longValue()), uploadState);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<Long, UploadState> getRequestStates(List<UploadRequest> list) {
        int collectionSizeOrDefault;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (UploadRequest uploadRequest : list) {
            arrayList.add(Long.valueOf(uploadRequest.getId()));
        }
        return getRequestStatesByIds(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<Long, UploadState> getRequestStatesByIds(List<Long> list) {
        Iterable withIndex;
        int collectionSizeOrDefault;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (list.size() <= UploadState.values().length) {
            UploadRequestDao uploadRequestDao = this.requestDao;
            if (uploadRequestDao == null) {
                Intrinsics.throwUninitializedPropertyAccessException("requestDao");
            }
            for (UploadRequest uploadRequest : uploadRequestDao.getRequestsByIds(list)) {
                linkedHashMap.put(Long.valueOf(uploadRequest.getId()), uploadRequest.getState());
            }
        } else {
            withIndex = CollectionsKt___CollectionsKt.withIndex(list);
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            for (Object obj : withIndex) {
                Integer valueOf = Integer.valueOf(((IndexedValue) obj).getIndex() / 500);
                Object obj2 = linkedHashMap2.get(valueOf);
                if (obj2 == null) {
                    obj2 = new ArrayList();
                    linkedHashMap2.put(valueOf, obj2);
                }
                ((List) obj2).add(obj);
            }
            ArrayList<List<Long>> arrayList = new ArrayList(linkedHashMap2.size());
            for (Map.Entry entry : linkedHashMap2.entrySet()) {
                Iterable<IndexedValue> iterable = (Iterable) entry.getValue();
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10);
                ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
                for (IndexedValue indexedValue : iterable) {
                    arrayList2.add(Long.valueOf(((Number) indexedValue.getValue()).longValue()));
                }
                arrayList.add(arrayList2);
            }
            for (List<Long> list2 : arrayList) {
                getLimitedRequestStates(list2, linkedHashMap);
            }
        }
        logMetricWithCount(UploadMetrics.QUERY_REQUEST_STATE_COUNT, list.size());
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void logMetricWithCount(final String str, int i) {
        ClientMetric withTagName = new ClientMetric().addCounter(new MetricName() { // from class: com.amazon.photos.uploader.UploaderOperations$logMetricWithCount$clientMetric$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return str;
            }
        }, i).withTagName(TAG);
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordCustomMetric(TAG, withTagName, new MetricRecordingType[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void logMetricWithMetadata(String str, String str2) {
        ClientMetric withTagName = new ClientMetric().addMetadata(str, str2).withTagName(TAG);
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordCustomMetric(TAG, withTagName, new MetricRecordingType[0]);
    }

    @WorkerThread
    private final Set<Blocker> queryGlobalBlockers() {
        InternalEvaluator internalEvaluator = this.internalEvaluator;
        if (internalEvaluator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalEvaluator");
        }
        return internalEvaluator.queryGlobalBlockers();
    }

    @WorkerThread
    private final Map<Queue, Collection<Blocker>> queryQueueBlockers(Collection<Queue> collection) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Queue queue : collection) {
            InternalEvaluator internalEvaluator = this.internalEvaluator;
            if (internalEvaluator == null) {
                Intrinsics.throwUninitializedPropertyAccessException("internalEvaluator");
            }
            linkedHashMap.put(queue, internalEvaluator.queryQueueBlockers(queue));
        }
        return linkedHashMap;
    }

    @WorkerThread
    private final Map<Queue, Collection<Blocker>> queryRequestBlockers(Collection<Queue> collection) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Queue queue : collection) {
            UploadRequestDao uploadRequestDao = this.requestDao;
            if (uploadRequestDao == null) {
                Intrinsics.throwUninitializedPropertyAccessException("requestDao");
            }
            for (UploadRequest uploadRequest : uploadRequestDao.getPrioritizedPendingRequestsForQueue(queue.getName())) {
                InternalEvaluator internalEvaluator = this.internalEvaluator;
                if (internalEvaluator == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("internalEvaluator");
                }
                linkedHashMap.put(queue, internalEvaluator.queryRequestBlockers(uploadRequest));
            }
        }
        return linkedHashMap;
    }

    public static /* synthetic */ UploadOperation scheduleUpload$default(UploaderOperations uploaderOperations, UploadRequest uploadRequest, String str, UploadStrategy uploadStrategy, int i, Object obj) {
        if ((i & 4) != 0) {
            uploadStrategy = UploadStrategy.REPLACE;
        }
        return uploaderOperations.scheduleUpload(uploadRequest, str, uploadStrategy);
    }

    public static /* synthetic */ UploadOperation scheduleUploads$default(UploaderOperations uploaderOperations, List list, String str, UploadStrategy uploadStrategy, int i, Object obj) {
        if ((i & 4) != 0) {
            uploadStrategy = UploadStrategy.REPLACE;
        }
        return uploaderOperations.scheduleUploads(list, str, uploadStrategy);
    }

    private final List<UploadRequest> schedulerLimitUploadsHelper(List<UploadRequest> list, String str, UploadStrategy uploadStrategy) {
        UploadRequest copy;
        UploadStrategy uploadStrategy2;
        UploadLogger uploadLogger = this.logger;
        if (uploadLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("schedulerLimitUploadsHelper trying to adding ");
        outline107.append(list.size());
        outline107.append(" requests.");
        uploadLogger.d$AndroidPhotosUploader_release(TAG, outline107.toString());
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (UploadRequest uploadRequest : list) {
            arrayList2.add(uploadRequest.getFilePath());
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        UploadRequestDao uploadRequestDao = this.requestDao;
        if (uploadRequestDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestDao");
        }
        for (UploadRequest uploadRequest2 : uploadRequestDao.getRequestsByFilePaths(arrayList2)) {
            linkedHashMap.put(uploadRequest2.getFilePath(), uploadRequest2);
        }
        ArrayList arrayList3 = new ArrayList();
        for (UploadRequest uploadRequest3 : list) {
            String filePath = uploadRequest3.getFilePath();
            SystemUtil systemUtil = this.systemUtil;
            if (systemUtil == null) {
                Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
            }
            copy = uploadRequest3.copy((r49 & 1) != 0 ? uploadRequest3.id : 0L, (r49 & 2) != 0 ? uploadRequest3.filePath : null, (r49 & 4) != 0 ? uploadRequest3.uploadPath : null, (r49 & 8) != 0 ? uploadRequest3.contentDate : null, (r49 & 16) != 0 ? uploadRequest3.md5 : null, (r49 & 32) != 0 ? uploadRequest3.visualDigest : null, (r49 & 64) != 0 ? uploadRequest3.suppressDeduplication : false, (r49 & 128) != 0 ? uploadRequest3.renameOnNameConflict : false, (r49 & 256) != 0 ? uploadRequest3.uploadCategory : null, (r49 & 512) != 0 ? uploadRequest3.state : null, (r49 & 1024) != 0 ? uploadRequest3.queue : str, (r49 & 2048) != 0 ? uploadRequest3.currentProgress : 0L, (r49 & 4096) != 0 ? uploadRequest3.maxProgress : 0L, (r49 & 8192) != 0 ? uploadRequest3.errorCode : null, (r49 & 16384) != 0 ? uploadRequest3.errorCategory : null, (r49 & 32768) != 0 ? uploadRequest3.blocker : null, (r49 & 65536) != 0 ? uploadRequest3.totalAttemptCount : 0, (r49 & 131072) != 0 ? uploadRequest3.attemptCount : 0, (r49 & 262144) != 0 ? uploadRequest3.maxAttemptsExceeded : false, (r49 & 524288) != 0 ? uploadRequest3.creationTimeMillis : System.currentTimeMillis(), (r49 & 1048576) != 0 ? uploadRequest3.fileSize : systemUtil.getFileSize(filePath), (r49 & 2097152) != 0 ? uploadRequest3.priority : 0, (4194304 & r49) != 0 ? uploadRequest3.addToFamilyVault : false, (r49 & 8388608) != 0 ? uploadRequest3.appData : null, (r49 & 16777216) != 0 ? uploadRequest3.parentId : null, (r49 & 33554432) != 0 ? uploadRequest3.contentUri : null);
            UploadRequest uploadRequest4 = (UploadRequest) linkedHashMap.get(filePath);
            if (uploadRequest4 != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[uploadRequest4.getState().ordinal()];
                if (i != 1) {
                    if (i == 2) {
                        Metrics metrics = this.metrics;
                        if (metrics == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("metrics");
                        }
                        metrics.recordSimpleEvent(TAG, UploaderOperations$schedulerLimitUploadsHelper$3$strategyToUse$2.INSTANCE, new MetricRecordingType[0]);
                    }
                    uploadStrategy2 = uploadStrategy;
                } else {
                    Metrics metrics2 = this.metrics;
                    if (metrics2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("metrics");
                    }
                    metrics2.recordSimpleEvent(TAG, UploaderOperations$schedulerLimitUploadsHelper$3$strategyToUse$1.INSTANCE, new MetricRecordingType[0]);
                    uploadStrategy2 = UploadStrategy.REPLACE;
                }
                int i2 = WhenMappings.$EnumSwitchMapping$1[uploadStrategy2.ordinal()];
                if (i2 == 1) {
                    throw new IllegalArgumentException("Request with given filepath is already queued.");
                }
                if (i2 == 2) {
                    UploadLogger uploadLogger2 = this.logger;
                    if (uploadLogger2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("logger");
                    }
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("schedulerLimitUploadsHelper replace add ");
                    outline1072.append(copy.getId());
                    uploadLogger2.d$AndroidPhotosUploader_release(TAG, outline1072.toString());
                    arrayList3.add(copy);
                } else if (i2 != 3) {
                    throw new NoWhenBranchMatchedException();
                } else {
                    UploadLogger uploadLogger3 = this.logger;
                    if (uploadLogger3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("logger");
                    }
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("schedulerLimitUploadsHelper keep no add ");
                    outline1073.append(copy.getId());
                    uploadLogger3.d$AndroidPhotosUploader_release(TAG, outline1073.toString());
                    arrayList.add(uploadRequest4);
                }
            } else {
                arrayList3.add(copy);
            }
        }
        UploadRequestDao uploadRequestDao2 = this.requestDao;
        if (uploadRequestDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestDao");
        }
        List<Long> insertRequests = uploadRequestDao2.insertRequests(arrayList3);
        UploadRequestDao uploadRequestDao3 = this.requestDao;
        if (uploadRequestDao3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestDao");
        }
        arrayList.addAll(uploadRequestDao3.getRequestsByIds(insertRequests));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Long> schedulerUploadsHelper(List<UploadRequest> list, String str, UploadStrategy uploadStrategy) {
        Iterable withIndex;
        int collectionSizeOrDefault;
        int collectionSizeOrDefault2;
        throwDestroyedIfNeeded();
        ArrayList<UploadRequest> arrayList = new ArrayList();
        withIndex = CollectionsKt___CollectionsKt.withIndex(list);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : withIndex) {
            Integer valueOf = Integer.valueOf(((IndexedValue) obj).getIndex() / 200);
            Object obj2 = linkedHashMap.get(valueOf);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(valueOf, obj2);
            }
            ((List) obj2).add(obj);
        }
        ArrayList<List<UploadRequest>> arrayList2 = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Iterable<IndexedValue> iterable = (Iterable) entry.getValue();
            collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10);
            ArrayList arrayList3 = new ArrayList(collectionSizeOrDefault2);
            for (IndexedValue indexedValue : iterable) {
                arrayList3.add((UploadRequest) indexedValue.getValue());
            }
            arrayList2.add(arrayList3);
        }
        for (List<UploadRequest> list2 : arrayList2) {
            arrayList.addAll(schedulerLimitUploadsHelper(list2, str, uploadStrategy));
        }
        SchedulingCallback schedulingCallback = this.schedulingCallback;
        if (schedulingCallback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("schedulingCallback");
        }
        schedulingCallback.reevaluateBlockers().getResult().get();
        for (UploadRequest uploadRequest : arrayList) {
            UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier = this.uploadRequestUpdatesNotifier;
            if (uploadRequestUpdatesNotifier == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uploadRequestUpdatesNotifier");
            }
            uploadRequestUpdatesNotifier.onRequestAdded$AndroidPhotosUploader_release(uploadRequest);
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
        ArrayList arrayList4 = new ArrayList(collectionSizeOrDefault);
        for (UploadRequest uploadRequest2 : arrayList) {
            arrayList4.add(Long.valueOf(uploadRequest2.getId()));
        }
        return arrayList4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void submitToExecutorIfNotShutdown(Runnable runnable) {
        UploaderOperationsKt.submitIfNotShutdown(this.operationsExecutor, runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void throwDestroyedIfNeeded() {
        if (!this.destroyed) {
            return;
        }
        throw new IllegalStateException("Cannot perform operation. UploadManager was destroyed.".toString());
    }

    public static /* synthetic */ boolean updateNotification$default(UploaderOperations uploaderOperations, NotificationManager notificationManager, int i, Notification notification, boolean z, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            z = false;
        }
        return uploaderOperations.updateNotification(notificationManager, i, notification, z);
    }

    @NotNull
    public final UploadOperation<UploadState> cancelUpload(long j) {
        throwDestroyedIfNeeded();
        return new UploadOperation<>(new UploaderOperations$cancelUpload$1(this, j));
    }

    @NotNull
    public final UploadOperation<Map<Long, UploadState>> cancelUploadByFilePath(@NotNull String filepath) {
        List<String> listOf;
        Intrinsics.checkParameterIsNotNull(filepath, "filepath");
        listOf = CollectionsKt__CollectionsJVMKt.listOf(filepath);
        return cancelUploadsByFilePath(listOf);
    }

    @NotNull
    public final UploadOperation<Map<Long, UploadState>> cancelUploads(@NotNull List<Long> requestIds) {
        Intrinsics.checkParameterIsNotNull(requestIds, "requestIds");
        throwDestroyedIfNeeded();
        return new UploadOperation<>(new UploaderOperations$cancelUploads$1(this, requestIds));
    }

    @NotNull
    public final UploadOperation<Map<Long, UploadState>> cancelUploadsByFilePath(@NotNull List<String> filepaths) {
        Intrinsics.checkParameterIsNotNull(filepaths, "filepaths");
        throwDestroyedIfNeeded();
        return new UploadOperation<>(new UploaderOperations$cancelUploadsByFilePath$1(this, filepaths));
    }

    @NotNull
    public final UploadOperation<Unit> cancelUploadsForQueue(@NotNull String queueName) {
        List<String> listOf;
        Intrinsics.checkParameterIsNotNull(queueName, "queueName");
        throwDestroyedIfNeeded();
        listOf = CollectionsKt__CollectionsJVMKt.listOf(queueName);
        return cancelUploadsForQueues(listOf);
    }

    @NotNull
    public final UploadOperation<Unit> cancelUploadsForQueueWithState(@NotNull String queueName, @NotNull UploadState state) {
        List<String> listOf;
        Intrinsics.checkParameterIsNotNull(queueName, "queueName");
        Intrinsics.checkParameterIsNotNull(state, "state");
        throwDestroyedIfNeeded();
        listOf = CollectionsKt__CollectionsJVMKt.listOf(queueName);
        return cancelUploadsForQueuesWithState(listOf, state);
    }

    @NotNull
    public final UploadOperation<Unit> cancelUploadsForQueues(@NotNull List<String> queueNames) {
        Intrinsics.checkParameterIsNotNull(queueNames, "queueNames");
        throwDestroyedIfNeeded();
        return new UploadOperation<>(new UploaderOperations$cancelUploadsForQueues$1(this, queueNames));
    }

    @NotNull
    public final UploadOperation<Unit> cancelUploadsForQueuesWithState(@NotNull List<String> queueNames, @NotNull UploadState state) {
        Intrinsics.checkParameterIsNotNull(queueNames, "queueNames");
        Intrinsics.checkParameterIsNotNull(state, "state");
        throwDestroyedIfNeeded();
        return new UploadOperation<>(new UploaderOperations$cancelUploadsForQueuesWithState$1(this, queueNames, state));
    }

    @NotNull
    public final UploadOperation<Unit> clearBackoff() {
        throwDestroyedIfNeeded();
        return new UploadOperation<>(new UploaderOperations$clearBackoff$1(this));
    }

    @NotNull
    public final UploadOperation<Unit> clearCompletedUploadRecords() {
        throwDestroyedIfNeeded();
        return new UploadOperation<>(new UploaderOperations$clearCompletedUploadRecords$1(this));
    }

    @WorkerThread
    public final void destroy$AndroidPhotosUploader_release() {
        UploadLogger uploadLogger = this.logger;
        if (uploadLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        uploadLogger.i$AndroidPhotosUploader_release(TAG, "destroy Uploader Operations Start");
        WorkManager workManager = this.workManager;
        if (workManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        Operation cancelAllWorkByTag = workManager.cancelAllWorkByTag(UploadManager.WORK_MANAGER_TAG);
        Intrinsics.checkExpressionValueIsNotNull(cancelAllWorkByTag, "workManager.cancelAllWor…Manager.WORK_MANAGER_TAG)");
        cancelAllWorkByTag.getResult().get();
        WorkManager workManager2 = this.workManager;
        if (workManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        workManager2.pruneWork();
        this.destroyed = true;
        UploadLogger uploadLogger2 = this.logger;
        if (uploadLogger2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        uploadLogger2.i$AndroidPhotosUploader_release(TAG, "destroy Uploader Operations End");
    }

    @NotNull
    public final BackoffBlockerEvaluator getBackoffBlockerEvaluator$AndroidPhotosUploader_release() {
        BackoffBlockerEvaluator backoffBlockerEvaluator = this.backoffBlockerEvaluator;
        if (backoffBlockerEvaluator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backoffBlockerEvaluator");
        }
        return backoffBlockerEvaluator;
    }

    @NotNull
    public final List<UploadRequest> getExistingUploadRequests(@NotNull List<String> filePaths) {
        Intrinsics.checkParameterIsNotNull(filePaths, "filePaths");
        UploadRequestDao uploadRequestDao = this.requestDao;
        if (uploadRequestDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestDao");
        }
        return uploadRequestDao.getRequestsByFilePaths(filePaths);
    }

    @NotNull
    public final InternalEvaluator getInternalEvaluator$AndroidPhotosUploader_release() {
        InternalEvaluator internalEvaluator = this.internalEvaluator;
        if (internalEvaluator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalEvaluator");
        }
        return internalEvaluator;
    }

    @NotNull
    public final UploadLogger getLogger$AndroidPhotosUploader_release() {
        UploadLogger uploadLogger = this.logger;
        if (uploadLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        return uploadLogger;
    }

    @NotNull
    public final Metrics getMetrics$AndroidPhotosUploader_release() {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        return metrics;
    }

    @NotNull
    public final NotificationUpdatesNotifier getNotificationUpdatesNotifier$AndroidPhotosUploader_release() {
        NotificationUpdatesNotifier notificationUpdatesNotifier = this.notificationUpdatesNotifier;
        if (notificationUpdatesNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("notificationUpdatesNotifier");
        }
        return notificationUpdatesNotifier;
    }

    @NotNull
    public final PauseResume getPauseResumeState() {
        PauseResumeState pauseResumeState = this.pauseResumeState;
        if (pauseResumeState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pauseResumeState");
        }
        return PauseResume.valueOf(pauseResumeState.getPauseResumeState());
    }

    @NotNull
    public final PauseResumeState getPauseResumeState$AndroidPhotosUploader_release() {
        PauseResumeState pauseResumeState = this.pauseResumeState;
        if (pauseResumeState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pauseResumeState");
        }
        return pauseResumeState;
    }

    @NotNull
    public final QueueManager getQueueManager$AndroidPhotosUploader_release() {
        QueueManager queueManager = this.queueManager;
        if (queueManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("queueManager");
        }
        return queueManager;
    }

    @NotNull
    public final UploadRequestDao getRequestDao$AndroidPhotosUploader_release() {
        UploadRequestDao uploadRequestDao = this.requestDao;
        if (uploadRequestDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestDao");
        }
        return uploadRequestDao;
    }

    @NotNull
    public final SchedulingCallback getSchedulingCallback$AndroidPhotosUploader_release() {
        SchedulingCallback schedulingCallback = this.schedulingCallback;
        if (schedulingCallback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("schedulingCallback");
        }
        return schedulingCallback;
    }

    @NotNull
    public final SystemUtil getSystemUtil$AndroidPhotosUploader_release() {
        SystemUtil systemUtil = this.systemUtil;
        if (systemUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
        }
        return systemUtil;
    }

    @NotNull
    public final UploadFrameworkContext getUploadFrameworkContext$AndroidPhotosUploader_release() {
        UploadFrameworkContext uploadFrameworkContext = this.uploadFrameworkContext;
        if (uploadFrameworkContext == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadFrameworkContext");
        }
        return uploadFrameworkContext;
    }

    @NotNull
    public final UploadRequestUpdatesNotifier getUploadRequestUpdatesNotifier$AndroidPhotosUploader_release() {
        UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier = this.uploadRequestUpdatesNotifier;
        if (uploadRequestUpdatesNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequestUpdatesNotifier");
        }
        return uploadRequestUpdatesNotifier;
    }

    @NotNull
    public final UploadSummaryTracker getUploadSummaryTracker$AndroidPhotosUploader_release() {
        UploadSummaryTracker uploadSummaryTracker = this.uploadSummaryTracker;
        if (uploadSummaryTracker == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadSummaryTracker");
        }
        return uploadSummaryTracker;
    }

    @NotNull
    public final Uploader getUploader$AndroidPhotosUploader_release() {
        Uploader uploader = this.uploader;
        if (uploader == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploader");
        }
        return uploader;
    }

    @NotNull
    public final WorkManager getWorkManager$AndroidPhotosUploader_release() {
        WorkManager workManager = this.workManager;
        if (workManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        return workManager;
    }

    @NotNull
    public final UploadOperation<Unit> invalidateBlockers() {
        throwDestroyedIfNeeded();
        return new UploadOperation<>(new UploaderOperations$invalidateBlockers$1(this));
    }

    @NotNull
    public final UploadOperation<Unit> pauseAllUploads() {
        throwDestroyedIfNeeded();
        return new UploadOperation<>(new UploaderOperations$pauseAllUploads$1(this));
    }

    @NotNull
    public final UploadOperation<Unit> reevaluteBlockers() {
        throwDestroyedIfNeeded();
        return new UploadOperation<>(new UploaderOperations$reevaluteBlockers$1(this));
    }

    @NotNull
    public final UploadOperation<Unit> resumeAllUploads() {
        throwDestroyedIfNeeded();
        return new UploadOperation<>(new UploaderOperations$resumeAllUploads$1(this));
    }

    @WorkerThread
    public final void reupdateBlockersSync() {
        Set<Blocker> queryGlobalBlockers = queryGlobalBlockers();
        QueueManager queueManager = this.queueManager;
        if (queueManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("queueManager");
        }
        Collection<Queue> queues = queueManager.getQueues();
        Map<Queue, Collection<Blocker>> queryQueueBlockers = queryQueueBlockers(queues);
        Map<Queue, Collection<Blocker>> queryRequestBlockers = queryRequestBlockers(queues);
        UploadSummaryTracker uploadSummaryTracker = this.uploadSummaryTracker;
        if (uploadSummaryTracker == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadSummaryTracker");
        }
        uploadSummaryTracker.setBlockerSummary(queryGlobalBlockers, queryQueueBlockers, queryRequestBlockers);
    }

    @JvmOverloads
    @NotNull
    public final UploadOperation<Long> scheduleUpload(@NotNull UploadRequest uploadRequest, @NotNull String str) {
        return scheduleUpload$default(this, uploadRequest, str, null, 4, null);
    }

    @JvmOverloads
    @NotNull
    public final UploadOperation<Long> scheduleUpload(@NotNull UploadRequest uploadRequest, @NotNull String queue, @NotNull UploadStrategy strategy) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        Intrinsics.checkParameterIsNotNull(strategy, "strategy");
        throwDestroyedIfNeeded();
        return new UploadOperation<>(new UploaderOperations$scheduleUpload$1(this, uploadRequest, queue, strategy));
    }

    @JvmOverloads
    @NotNull
    public final UploadOperation<List<Long>> scheduleUploads(@NotNull List<UploadRequest> list, @NotNull String str) {
        return scheduleUploads$default(this, list, str, null, 4, null);
    }

    @JvmOverloads
    @NotNull
    public final UploadOperation<List<Long>> scheduleUploads(@NotNull List<UploadRequest> uploadRequests, @NotNull String queue, @NotNull UploadStrategy strategy) {
        Intrinsics.checkParameterIsNotNull(uploadRequests, "uploadRequests");
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        Intrinsics.checkParameterIsNotNull(strategy, "strategy");
        throwDestroyedIfNeeded();
        return new UploadOperation<>(new UploaderOperations$scheduleUploads$1(this, uploadRequests, queue, strategy));
    }

    @Inject
    public final void setBackoffBlockerEvaluator$AndroidPhotosUploader_release(@NotNull BackoffBlockerEvaluator backoffBlockerEvaluator) {
        Intrinsics.checkParameterIsNotNull(backoffBlockerEvaluator, "<set-?>");
        this.backoffBlockerEvaluator = backoffBlockerEvaluator;
    }

    @Inject
    public final void setInternalEvaluator$AndroidPhotosUploader_release(@NotNull InternalEvaluator internalEvaluator) {
        Intrinsics.checkParameterIsNotNull(internalEvaluator, "<set-?>");
        this.internalEvaluator = internalEvaluator;
    }

    @Inject
    public final void setLogger$AndroidPhotosUploader_release(@NotNull UploadLogger uploadLogger) {
        Intrinsics.checkParameterIsNotNull(uploadLogger, "<set-?>");
        this.logger = uploadLogger;
    }

    @Inject
    public final void setMetrics$AndroidPhotosUploader_release(@NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "<set-?>");
        this.metrics = metrics;
    }

    @Inject
    public final void setNotificationUpdatesNotifier$AndroidPhotosUploader_release(@NotNull NotificationUpdatesNotifier notificationUpdatesNotifier) {
        Intrinsics.checkParameterIsNotNull(notificationUpdatesNotifier, "<set-?>");
        this.notificationUpdatesNotifier = notificationUpdatesNotifier;
    }

    @Inject
    public final void setPauseResumeState$AndroidPhotosUploader_release(@NotNull PauseResumeState pauseResumeState) {
        Intrinsics.checkParameterIsNotNull(pauseResumeState, "<set-?>");
        this.pauseResumeState = pauseResumeState;
    }

    @Inject
    public final void setQueueManager$AndroidPhotosUploader_release(@NotNull QueueManager queueManager) {
        Intrinsics.checkParameterIsNotNull(queueManager, "<set-?>");
        this.queueManager = queueManager;
    }

    @Inject
    public final void setRequestDao$AndroidPhotosUploader_release(@NotNull UploadRequestDao uploadRequestDao) {
        Intrinsics.checkParameterIsNotNull(uploadRequestDao, "<set-?>");
        this.requestDao = uploadRequestDao;
    }

    @Inject
    public final void setSchedulingCallback$AndroidPhotosUploader_release(@NotNull SchedulingCallback schedulingCallback) {
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "<set-?>");
        this.schedulingCallback = schedulingCallback;
    }

    @Inject
    public final void setSystemUtil$AndroidPhotosUploader_release(@NotNull SystemUtil systemUtil) {
        Intrinsics.checkParameterIsNotNull(systemUtil, "<set-?>");
        this.systemUtil = systemUtil;
    }

    @Inject
    public final void setUploadFrameworkContext$AndroidPhotosUploader_release(@NotNull UploadFrameworkContext uploadFrameworkContext) {
        Intrinsics.checkParameterIsNotNull(uploadFrameworkContext, "<set-?>");
        this.uploadFrameworkContext = uploadFrameworkContext;
    }

    @Inject
    public final void setUploadRequestUpdatesNotifier$AndroidPhotosUploader_release(@NotNull UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier) {
        Intrinsics.checkParameterIsNotNull(uploadRequestUpdatesNotifier, "<set-?>");
        this.uploadRequestUpdatesNotifier = uploadRequestUpdatesNotifier;
    }

    @Inject
    public final void setUploadSummaryTracker$AndroidPhotosUploader_release(@NotNull UploadSummaryTracker uploadSummaryTracker) {
        Intrinsics.checkParameterIsNotNull(uploadSummaryTracker, "<set-?>");
        this.uploadSummaryTracker = uploadSummaryTracker;
    }

    @Inject
    public final void setUploader$AndroidPhotosUploader_release(@NotNull Uploader uploader) {
        Intrinsics.checkParameterIsNotNull(uploader, "<set-?>");
        this.uploader = uploader;
    }

    @Inject
    public final void setWorkManager$AndroidPhotosUploader_release(@NotNull WorkManager workManager) {
        Intrinsics.checkParameterIsNotNull(workManager, "<set-?>");
        this.workManager = workManager;
    }

    public final boolean updateNotification(@NotNull NotificationManager notificationManager, int i, @NotNull Notification notification, boolean z) {
        Intrinsics.checkParameterIsNotNull(notificationManager, "notificationManager");
        Intrinsics.checkParameterIsNotNull(notification, "notification");
        throwDestroyedIfNeeded();
        NotificationUpdatesNotifier notificationUpdatesNotifier = this.notificationUpdatesNotifier;
        if (notificationUpdatesNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("notificationUpdatesNotifier");
        }
        return notificationUpdatesNotifier.onNotificationUpdated$AndroidPhotosUploader_release(notificationManager, i, notification, z);
    }
}
