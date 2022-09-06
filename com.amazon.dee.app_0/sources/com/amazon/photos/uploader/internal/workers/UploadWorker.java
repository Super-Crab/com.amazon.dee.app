package com.amazon.photos.uploader.internal.workers;

import amazon.speech.simclient.settings.Settings;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.VisibleForTesting;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkerParameters;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.photos.uploader.AbandonReason;
import com.amazon.photos.uploader.AbandonedRequestHandler;
import com.amazon.photos.uploader.Feature;
import com.amazon.photos.uploader.FileSizeCategoryProvider;
import com.amazon.photos.uploader.ResultMetadata;
import com.amazon.photos.uploader.SchedulingCallbackKt;
import com.amazon.photos.uploader.UploadCompleter;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadManager;
import com.amazon.photos.uploader.UploadProgressListener;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadResponse;
import com.amazon.photos.uploader.UploadState;
import com.amazon.photos.uploader.Uploader;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.cds.CdsUploader;
import com.amazon.photos.uploader.internal.InternalEvaluator;
import com.amazon.photos.uploader.internal.NotificationUpdatesNotifier;
import com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier;
import com.amazon.photos.uploader.internal.UploadWorkerConfiguration;
import com.amazon.photos.uploader.internal.UploaderTransactionRunner;
import com.amazon.photos.uploader.internal.contentsignature.ContentSignatureProvider;
import com.amazon.photos.uploader.internal.dagger.UploadManagerMap;
import com.amazon.photos.uploader.internal.dagger.component.UploadFrameworkComponent;
import com.amazon.photos.uploader.internal.dao.UploadRequestDao;
import com.amazon.photos.uploader.internal.metrics.UploadMetrics;
import com.amazon.photos.uploader.internal.utils.FileUtils;
import com.amazon.photos.uploader.internal.utils.PersistentLogger;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UploadWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¬\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u0000 ¹\u00012\u00020\u0001:\u0002¹\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010w\u001a\u00020x2\u0006\u0010y\u001a\u00020z2\u0006\u0010{\u001a\u00020|2\u0006\u0010}\u001a\u00020~H\u0002J\u0011\u0010\u007f\u001a\u00020x2\u0007\u0010\u0080\u0001\u001a\u00020DH\u0002J\u0019\u0010\u0081\u0001\u001a\u00030\u0082\u00012\u0007\u0010\u0083\u0001\u001a\u00020%H\u0001¢\u0006\u0003\b\u0084\u0001J\u0015\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0086\u00012\u0007\u0010\u0087\u0001\u001a\u00020DH\u0002J\u001f\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0089\u00012\u0007\u0010\u0083\u0001\u001a\u00020%2\b\u0010\u008a\u0001\u001a\u00030\u008b\u0001H\u0002J\t\u0010\u008c\u0001\u001a\u000207H\u0014J\t\u0010\u008d\u0001\u001a\u00020%H\u0014J\u001f\u0010\u008e\u0001\u001a\u0005\u0018\u00010\u0089\u00012\u0007\u0010\u0083\u0001\u001a\u00020%2\b\u0010\u008a\u0001\u001a\u00030\u008b\u0001H\u0002J\u0013\u0010\u008f\u0001\u001a\u00020x2\b\u0010\u0090\u0001\u001a\u00030\u0091\u0001H\u0002J\u0013\u0010\u0092\u0001\u001a\u00020x2\b\u0010\u0090\u0001\u001a\u00030\u0093\u0001H\u0002J\u0012\u0010\u0094\u0001\u001a\u00020x2\u0007\u0010\u0095\u0001\u001a\u00020(H\u0002J\u0013\u0010\u0096\u0001\u001a\u00020x2\b\u0010\u0095\u0001\u001a\u00030\u0097\u0001H\u0002J\u0013\u0010\u0098\u0001\u001a\u00020x2\b\u0010\u0090\u0001\u001a\u00030\u0099\u0001H\u0002J\t\u0010\u009a\u0001\u001a\u00020xH\u0014J\u0011\u0010\u009b\u0001\u001a\u00020#2\u0006\u0010C\u001a\u00020DH\u0002J,\u0010\u009c\u0001\u001a\u00020x2\u0007\u0010\u009d\u0001\u001a\u00020%2\u0007\u0010\u009e\u0001\u001a\u00020%2\u0006\u0010{\u001a\u00020|2\u0007\u0010\u009f\u0001\u001a\u00020zH\u0002J,\u0010 \u0001\u001a\u00020x2\u0007\u0010¡\u0001\u001a\u00020%2\u0007\u0010\u009e\u0001\u001a\u00020%2\u0006\u0010{\u001a\u00020|2\u0007\u0010\u009f\u0001\u001a\u00020zH\u0002J,\u0010¢\u0001\u001a\u00020x2\u0007\u0010¡\u0001\u001a\u00020%2\u0007\u0010\u009e\u0001\u001a\u00020%2\u0006\u0010{\u001a\u00020|2\u0007\u0010\u009f\u0001\u001a\u00020zH\u0002J\t\u0010£\u0001\u001a\u00020xH\u0002J\u0014\u0010¤\u0001\u001a\u00030¥\u0001H\u0094@ø\u0001\u0000¢\u0006\u0003\u0010¦\u0001J\u0012\u0010§\u0001\u001a\u00020x2\u0007\u0010¨\u0001\u001a\u00020LH\u0002J\t\u0010©\u0001\u001a\u00020xH\u0002J'\u0010ª\u0001\u001a\u00020x2\b\u0010\u0095\u0001\u001a\u00030\u0093\u00012\b\u0010«\u0001\u001a\u00030¬\u00012\b\u0010\u00ad\u0001\u001a\u00030¬\u0001H\u0002J-\u0010®\u0001\u001a\u00020x2\b\u0010¯\u0001\u001a\u00030°\u00012\u0007\u0010\u009e\u0001\u001a\u00020%2\u0007\u0010±\u0001\u001a\u00020z2\u0006\u0010{\u001a\u00020|H\u0002J-\u0010²\u0001\u001a\u00020x2\b\u0010¯\u0001\u001a\u00030°\u00012\u0007\u0010\u009e\u0001\u001a\u00020%2\u0007\u0010±\u0001\u001a\u00020z2\u0006\u0010{\u001a\u00020|H\u0002J-\u0010³\u0001\u001a\u00020x2\b\u0010¯\u0001\u001a\u00030°\u00012\u0007\u0010\u009e\u0001\u001a\u00020%2\u0007\u0010±\u0001\u001a\u00020z2\u0006\u0010{\u001a\u00020|H\u0002J\u0013\u0010´\u0001\u001a\u00020x2\b\u0010¯\u0001\u001a\u00030°\u0001H\u0002J\t\u0010µ\u0001\u001a\u00020#H\u0002J\u0013\u0010¶\u0001\u001a\u00020#2\b\u0010·\u0001\u001a\u00030\u0082\u0001H\u0002J\u0013\u0010¸\u0001\u001a\u00020xH\u0082@ø\u0001\u0000¢\u0006\u0003\u0010¦\u0001J\u001c\u0010¸\u0001\u001a\u00020x2\b\u0010¯\u0001\u001a\u00030°\u00012\u0007\u0010¨\u0001\u001a\u00020LH\u0002R(\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u000e@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u0016@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\u001c@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010&\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010*\u001a\u00020)2\u0006\u0010\u0007\u001a\u00020)@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R$\u00100\u001a\u00020/2\u0006\u0010\u0007\u001a\u00020/@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u000e\u00105\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R$\u00108\u001a\u0002072\u0006\u0010\u0007\u001a\u000207@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R(\u0010>\u001a\u0004\u0018\u00010=2\b\u0010\u0007\u001a\u0004\u0018\u00010=@QX\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u000e\u0010C\u001a\u00020DX\u0082.¢\u0006\u0002\n\u0000R$\u0010F\u001a\u00020E2\u0006\u0010\u0007\u001a\u00020E@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u000e\u0010K\u001a\u00020LX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010O\u001a\b\u0012\u0004\u0012\u00020Q0PX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010S\u001a\u00020R2\u0006\u0010\u0007\u001a\u00020R@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR$\u0010Y\u001a\u00020X2\u0006\u0010\u0007\u001a\u00020X@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R$\u0010_\u001a\u00020^2\u0006\u0010\u0007\u001a\u00020^@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010a\"\u0004\bb\u0010cR$\u0010e\u001a\u00020d2\u0006\u0010\u0007\u001a\u00020d@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010g\"\u0004\bh\u0010iR$\u0010k\u001a\u00020j2\u0006\u0010\u0007\u001a\u00020j@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010m\"\u0004\bn\u0010oR\u0010\u0010p\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010r\u001a\u00020q2\u0006\u0010\u0007\u001a\u00020q@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010t\"\u0004\bu\u0010v\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006º\u0001"}, d2 = {"Lcom/amazon/photos/uploader/internal/workers/UploadWorker;", "Lcom/amazon/photos/uploader/internal/workers/BaseWorker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "<set-?>", "Lcom/amazon/photos/uploader/AbandonedRequestHandler;", "abandonedRequestHandler", "getAbandonedRequestHandler", "()Lcom/amazon/photos/uploader/AbandonedRequestHandler;", "setAbandonedRequestHandler", "(Lcom/amazon/photos/uploader/AbandonedRequestHandler;)V", "Lcom/amazon/photos/uploader/internal/contentsignature/ContentSignatureProvider;", "contentSignatureProvider", "getContentSignatureProvider", "()Lcom/amazon/photos/uploader/internal/contentsignature/ContentSignatureProvider;", "setContentSignatureProvider", "(Lcom/amazon/photos/uploader/internal/contentsignature/ContentSignatureProvider;)V", "digests", "Lcom/amazon/photos/uploader/internal/contentsignature/ContentSignatureProvider$Digests;", "Lcom/amazon/photos/uploader/FileSizeCategoryProvider;", "fileSizeCategoryProvider", "getFileSizeCategoryProvider", "()Lcom/amazon/photos/uploader/FileSizeCategoryProvider;", "setFileSizeCategoryProvider", "(Lcom/amazon/photos/uploader/FileSizeCategoryProvider;)V", "Lcom/amazon/photos/uploader/internal/utils/FileUtils;", "fileUtils", "getFileUtils", "()Lcom/amazon/photos/uploader/internal/utils/FileUtils;", "setFileUtils", "(Lcom/amazon/photos/uploader/internal/utils/FileUtils;)V", "hasValidRequestId", "", "hashedDirectedId", "", "inProgressUploadFuture", "Lcom/google/common/util/concurrent/ListenableFuture;", "Lcom/amazon/photos/uploader/UploadResponse;", "Lcom/amazon/photos/uploader/internal/InternalEvaluator;", "internalEvaluator", "getInternalEvaluator", "()Lcom/amazon/photos/uploader/internal/InternalEvaluator;", "setInternalEvaluator", "(Lcom/amazon/photos/uploader/internal/InternalEvaluator;)V", "Lcom/amazon/photos/uploader/internal/utils/PersistentLogger;", "logger", "getLogger", "()Lcom/amazon/photos/uploader/internal/utils/PersistentLogger;", "setLogger", "(Lcom/amazon/photos/uploader/internal/utils/PersistentLogger;)V", "meanBytesPerMillis", "", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "metrics", "getMetrics", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "setMetrics", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;", "notificationUpdatesNotifier", "getNotificationUpdatesNotifier$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;", "setNotificationUpdatesNotifier$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;)V", "request", "Lcom/amazon/photos/uploader/UploadRequest;", "Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao;", "requestDao", "getRequestDao", "()Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao;", "setRequestDao", "(Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao;)V", "requestId", "", "shouldUpdateMd5", "shouldUpdateVisualDigest", "supportedFeatures", "", "Lcom/amazon/photos/uploader/Feature;", "Lcom/amazon/photos/uploader/internal/utils/SystemUtil;", "systemUtil", "getSystemUtil", "()Lcom/amazon/photos/uploader/internal/utils/SystemUtil;", "setSystemUtil", "(Lcom/amazon/photos/uploader/internal/utils/SystemUtil;)V", "Lcom/amazon/photos/uploader/internal/UploaderTransactionRunner;", "transactionRunner", "getTransactionRunner", "()Lcom/amazon/photos/uploader/internal/UploaderTransactionRunner;", "setTransactionRunner", "(Lcom/amazon/photos/uploader/internal/UploaderTransactionRunner;)V", "Lcom/amazon/photos/uploader/internal/UploadRequestUpdatesNotifier;", "uploadRequestUpdatesNotifier", "getUploadRequestUpdatesNotifier", "()Lcom/amazon/photos/uploader/internal/UploadRequestUpdatesNotifier;", "setUploadRequestUpdatesNotifier", "(Lcom/amazon/photos/uploader/internal/UploadRequestUpdatesNotifier;)V", "Lcom/amazon/photos/uploader/internal/UploadWorkerConfiguration;", "uploadWorkerConfiguration", "getUploadWorkerConfiguration", "()Lcom/amazon/photos/uploader/internal/UploadWorkerConfiguration;", "setUploadWorkerConfiguration", "(Lcom/amazon/photos/uploader/internal/UploadWorkerConfiguration;)V", "Lcom/amazon/photos/uploader/Uploader;", "uploader", "getUploader", "()Lcom/amazon/photos/uploader/Uploader;", "setUploader", "(Lcom/amazon/photos/uploader/Uploader;)V", "visualDigest", "Landroidx/work/WorkManager;", "workManager", "getWorkManager", "()Landroidx/work/WorkManager;", "setWorkManager", "(Landroidx/work/WorkManager;)V", "abandonUpload", "", "throwable", "", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", Settings.ListeningSettings.EXTRA_REASON, "Lcom/amazon/photos/uploader/AbandonReason;", "calculateContentSignatures", "uploadRequest", "createFileForPath", "Ljava/io/File;", "filePath", "createFileForPath$AndroidPhotosUploader_release", "getBlockerReason", "Lcom/amazon/photos/uploader/blockers/Blocker;", "blockedRequest", "getImageContentUri", "Landroid/net/Uri;", "contentResolver", "Landroid/content/ContentResolver;", "getMetricsObj", "getTag", "getVideoContentUri", "handleAcceptableError", "failedResponse", "Lcom/amazon/photos/uploader/UploadResponse$AcceptableFailure;", "handleFailedUpload", "Lcom/amazon/photos/uploader/UploadResponse$Failure;", "handleResponse", "response", "handleSuccessfulUpload", "Lcom/amazon/photos/uploader/UploadResponse$Success;", "handleUnrecoverableUpload", "Lcom/amazon/photos/uploader/UploadResponse$NoRetryFailure;", "injectMethod", "isRequestNotExistOrCancelled", "logErrorMetric", "metricToReport", "errorCode", "ex", "logFailedMetric", "metric", "logMetricForAbandonedUpload", "logTimeMetricForAbandonedUpload", "mainTask", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recordUploadStartMetrics", "startTime", "reportUploadBlocked", "retryFailedUpload", "attempts", "", "totalAttempts", "setAcceptableFailure", "uploadCompleter", "Lcom/amazon/photos/uploader/UploadCompleter;", "error", "setFailure", "setNoRetryFailure", "setRequestToRunning", "shouldCalculateMd5", "shouldCalculateVisualDigest", "file", "startUpload", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadWorker extends BaseWorker {
    @NotNull
    public static final String ABANDONED_PREFIX = "Abandoned";
    @NotNull
    public static final String CONTENT_SIGNATURE_COMPUTE_ERROR = "ContentSignatureComputeError";
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String FAILED_PREFIX = "Failed";
    @NotNull
    public static final String FILE_NOT_PRESENT = "FileNotPresent";
    @NotNull
    public static final String TAG = "UploadWorker";
    @NotNull
    public static final String UPLOADER_CRASH = "UploaderCrash";
    @Nullable
    private AbandonedRequestHandler abandonedRequestHandler;
    @NotNull
    public ContentSignatureProvider contentSignatureProvider;
    private ContentSignatureProvider.Digests digests;
    @NotNull
    public FileSizeCategoryProvider fileSizeCategoryProvider;
    @NotNull
    public FileUtils fileUtils;
    private final boolean hasValidRequestId;
    private final String hashedDirectedId;
    private ListenableFuture<UploadResponse> inProgressUploadFuture;
    @NotNull
    public InternalEvaluator internalEvaluator;
    @NotNull
    public PersistentLogger logger;
    private double meanBytesPerMillis;
    @NotNull
    public Metrics metrics;
    @Nullable
    private NotificationUpdatesNotifier notificationUpdatesNotifier;
    private UploadRequest request;
    @NotNull
    public UploadRequestDao requestDao;
    private final long requestId;
    private boolean shouldUpdateMd5;
    private boolean shouldUpdateVisualDigest;
    private final Set<Feature> supportedFeatures;
    @NotNull
    public SystemUtil systemUtil;
    @NotNull
    public UploaderTransactionRunner transactionRunner;
    @NotNull
    public UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier;
    @NotNull
    public UploadWorkerConfiguration uploadWorkerConfiguration;
    @NotNull
    public Uploader uploader;
    private String visualDigest;
    @NotNull
    public WorkManager workManager;

    /* compiled from: UploadWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/photos/uploader/internal/workers/UploadWorker$Companion;", "", "()V", "ABANDONED_PREFIX", "", "CONTENT_SIGNATURE_COMPUTE_ERROR", "FAILED_PREFIX", "FILE_NOT_PRESENT", "TAG", "UPLOADER_CRASH", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploadWorker(@NotNull Context appContext, @NotNull WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        Intrinsics.checkParameterIsNotNull(workerParams, "workerParams");
        this.requestId = workerParams.getInputData().getLong(SchedulerWorkerKt.REQUEST_ID_KEY, 0L);
        Data inputData = workerParams.getInputData();
        Intrinsics.checkExpressionValueIsNotNull(inputData, "workerParams.inputData");
        this.hasValidRequestId = inputData.getKeyValueMap().containsKey(SchedulerWorkerKt.REQUEST_ID_KEY);
        this.supportedFeatures = new LinkedHashSet();
        String string = workerParams.getInputData().getString("HASHED_DIRECTED_ID_KEY");
        if (string != null) {
            this.hashedDirectedId = string;
            return;
        }
        throw new IllegalArgumentException("No hashed directed id associated with worker.");
    }

    private final void abandonUpload(Throwable th, UploadErrorCategory uploadErrorCategory, AbandonReason abandonReason) {
        UploadRequest copy;
        UploadRequest uploadRequest = this.request;
        if (uploadRequest == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        int attemptCount = uploadRequest.getAttemptCount() + 1;
        UploadRequest uploadRequest2 = this.request;
        if (uploadRequest2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        int totalAttemptCount = uploadRequest2.getTotalAttemptCount() + 1;
        PersistentLogger persistentLogger = this.logger;
        if (persistentLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Abandoning request ");
        UploadRequest uploadRequest3 = this.request;
        if (uploadRequest3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        outline107.append(uploadRequest3.getId());
        outline107.append(" after ");
        outline107.append(totalAttemptCount);
        outline107.append(" attempts.");
        persistentLogger.iPersistent(TAG, outline107.toString());
        UploadRequestDao uploadRequestDao = this.requestDao;
        if (uploadRequestDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestDao");
        }
        UploadRequest uploadRequest4 = this.request;
        if (uploadRequest4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        uploadRequestDao.deleteByRequestId(uploadRequest4.getId());
        UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier = this.uploadRequestUpdatesNotifier;
        if (uploadRequestUpdatesNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequestUpdatesNotifier");
        }
        UploadRequest uploadRequest5 = this.request;
        if (uploadRequest5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        UploadState uploadState = UploadState.CANCELLED;
        UploadWorkerConfiguration uploadWorkerConfiguration = this.uploadWorkerConfiguration;
        if (uploadWorkerConfiguration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadWorkerConfiguration");
        }
        copy = uploadRequest5.copy((r49 & 1) != 0 ? uploadRequest5.id : 0L, (r49 & 2) != 0 ? uploadRequest5.filePath : null, (r49 & 4) != 0 ? uploadRequest5.uploadPath : null, (r49 & 8) != 0 ? uploadRequest5.contentDate : null, (r49 & 16) != 0 ? uploadRequest5.md5 : null, (r49 & 32) != 0 ? uploadRequest5.visualDigest : null, (r49 & 64) != 0 ? uploadRequest5.suppressDeduplication : false, (r49 & 128) != 0 ? uploadRequest5.renameOnNameConflict : false, (r49 & 256) != 0 ? uploadRequest5.uploadCategory : null, (r49 & 512) != 0 ? uploadRequest5.state : uploadState, (r49 & 1024) != 0 ? uploadRequest5.queue : null, (r49 & 2048) != 0 ? uploadRequest5.currentProgress : 0L, (r49 & 4096) != 0 ? uploadRequest5.maxProgress : 0L, (r49 & 8192) != 0 ? uploadRequest5.errorCode : null, (r49 & 16384) != 0 ? uploadRequest5.errorCategory : null, (r49 & 32768) != 0 ? uploadRequest5.blocker : null, (r49 & 65536) != 0 ? uploadRequest5.totalAttemptCount : totalAttemptCount, (r49 & 131072) != 0 ? uploadRequest5.attemptCount : attemptCount, (r49 & 262144) != 0 ? uploadRequest5.maxAttemptsExceeded : attemptCount >= uploadWorkerConfiguration.getMaxAttempts(), (r49 & 524288) != 0 ? uploadRequest5.creationTimeMillis : 0L, (r49 & 1048576) != 0 ? uploadRequest5.fileSize : 0L, (r49 & 2097152) != 0 ? uploadRequest5.priority : 0, (4194304 & r49) != 0 ? uploadRequest5.addToFamilyVault : false, (r49 & 8388608) != 0 ? uploadRequest5.appData : null, (r49 & 16777216) != 0 ? uploadRequest5.parentId : null, (r49 & 33554432) != 0 ? uploadRequest5.contentUri : null);
        uploadRequestUpdatesNotifier.onRequestAbandoned$AndroidPhotosUploader_release(copy, th, uploadErrorCategory, abandonReason);
    }

    public static final /* synthetic */ UploadRequest access$getRequest$p(UploadWorker uploadWorker) {
        UploadRequest uploadRequest = uploadWorker.request;
        if (uploadRequest == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        return uploadRequest;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void calculateContentSignatures(UploadRequest uploadRequest) throws ContentSignatureProvider.ContentSignatureException, FileNotFoundException {
        File createFileForPath$AndroidPhotosUploader_release = createFileForPath$AndroidPhotosUploader_release(uploadRequest.getFilePath());
        ContentSignatureProvider contentSignatureProvider = this.contentSignatureProvider;
        if (contentSignatureProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentSignatureProvider");
        }
        if (contentSignatureProvider.isFilePresent$AndroidPhotosUploader_release(createFileForPath$AndroidPhotosUploader_release)) {
            this.shouldUpdateVisualDigest = shouldCalculateVisualDigest(createFileForPath$AndroidPhotosUploader_release);
            this.shouldUpdateMd5 = shouldCalculateMd5();
            try {
                Uri contentUri = uploadRequest.getContentUri();
                Context applicationContext = getApplicationContext();
                Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
                InputStream openInputStream = applicationContext.getContentResolver().openInputStream(contentUri);
                if (openInputStream == null) {
                    PersistentLogger persistentLogger = this.logger;
                    if (persistentLogger == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("logger");
                    }
                    persistentLogger.w(TAG, "Unable to open file " + createFileForPath$AndroidPhotosUploader_release.getName());
                }
                if (openInputStream != null) {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(openInputStream);
                    if (this.shouldUpdateVisualDigest) {
                        ContentSignatureProvider contentSignatureProvider2 = this.contentSignatureProvider;
                        if (contentSignatureProvider2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("contentSignatureProvider");
                        }
                        String name = createFileForPath$AndroidPhotosUploader_release.getName();
                        Intrinsics.checkExpressionValueIsNotNull(name, "file.name");
                        this.digests = contentSignatureProvider2.computeAllDigests$AndroidPhotosUploader_release(bufferedInputStream, name);
                        ContentSignatureProvider.Digests digests = this.digests;
                        if (digests != null) {
                            this.visualDigest = digests.getVisualDigest();
                            Metrics metrics = this.metrics;
                            if (metrics == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("metrics");
                            }
                            metrics.recordSimpleEvent(TAG, UploadWorker$calculateContentSignatures$1$1$1.INSTANCE, new MetricRecordingType[0]);
                        } else {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                    } else if (this.shouldUpdateMd5) {
                        ContentSignatureProvider contentSignatureProvider3 = this.contentSignatureProvider;
                        if (contentSignatureProvider3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("contentSignatureProvider");
                        }
                        String name2 = createFileForPath$AndroidPhotosUploader_release.getName();
                        Intrinsics.checkExpressionValueIsNotNull(name2, "file.name");
                        this.digests = contentSignatureProvider3.computeMd5$AndroidPhotosUploader_release(bufferedInputStream, name2);
                        this.visualDigest = uploadRequest.getVisualDigest();
                        Metrics metrics2 = this.metrics;
                        if (metrics2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("metrics");
                        }
                        metrics2.recordSimpleEvent(TAG, UploadWorker$calculateContentSignatures$1$1$2.INSTANCE, new MetricRecordingType[0]);
                    }
                    Unit unit = Unit.INSTANCE;
                }
                CloseableKt.closeFinally(openInputStream, null);
                return;
            } catch (FileNotFoundException e) {
                Metrics metrics3 = this.metrics;
                if (metrics3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("metrics");
                }
                metrics3.recordSimpleErrorEvent(TAG, UploadWorker$calculateContentSignatures$2.INSTANCE, e);
                return;
            }
        }
        throw new FileNotFoundException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Blocker getBlockerReason(UploadRequest uploadRequest) {
        InternalEvaluator internalEvaluator = this.internalEvaluator;
        if (internalEvaluator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalEvaluator");
        }
        List<Blocker> globalBlockers = internalEvaluator.getGlobalBlockers();
        if (!globalBlockers.isEmpty()) {
            return (Blocker) CollectionsKt.first((List<? extends Object>) globalBlockers);
        }
        InternalEvaluator internalEvaluator2 = this.internalEvaluator;
        if (internalEvaluator2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalEvaluator");
        }
        UploadRequest uploadRequest2 = this.request;
        if (uploadRequest2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        List<Blocker> queueBlockers = internalEvaluator2.getQueueBlockers(uploadRequest2.getQueue());
        return queueBlockers.isEmpty() ^ true ? (Blocker) CollectionsKt.first((List<? extends Object>) queueBlockers) : uploadRequest.getBlocker();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Uri getImageContentUri(String str, ContentResolver contentResolver) {
        Cursor query = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{str}, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    Uri withAppendedId = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, query.getLong(query.getColumnIndex("_id")));
                    CloseableKt.closeFinally(query, null);
                    return withAppendedId;
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(query, null);
            } finally {
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Uri getVideoContentUri(String str, ContentResolver contentResolver) {
        Cursor query = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{str}, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    Uri withAppendedId = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, query.getLong(query.getColumnIndex("_id")));
                    CloseableKt.closeFinally(query, null);
                    return withAppendedId;
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(query, null);
            } finally {
            }
        }
        return null;
    }

    private final void handleAcceptableError(UploadResponse.AcceptableFailure acceptableFailure) {
        logMetricForAbandonedUpload(UploadMetrics.UPLOAD_ACCEPTABLE_FAILURE, acceptableFailure.getErrorCode(), acceptableFailure.getErrorCategory(), acceptableFailure.getEx());
        abandonUpload(acceptableFailure.getEx(), acceptableFailure.getErrorCategory(), AbandonReason.ACCEPTABLE_FAILURE);
    }

    private final void handleFailedUpload(UploadResponse.Failure failure) {
        int attemptCount;
        boolean z = true;
        if (failure.getResetAttempts()) {
            attemptCount = 0;
        } else {
            UploadRequest uploadRequest = this.request;
            if (uploadRequest == null) {
                Intrinsics.throwUninitializedPropertyAccessException("request");
            }
            attemptCount = uploadRequest.getAttemptCount() + 1;
        }
        UploadRequest uploadRequest2 = this.request;
        if (uploadRequest2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        final int totalAttemptCount = uploadRequest2.getTotalAttemptCount() + 1;
        logFailedMetric(UploadMetrics.UPLOAD_ERROR, failure.getErrorCode(), failure.getErrorCategory(), failure.getEx());
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.internal.workers.UploadWorker$handleFailedUpload$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(UploadMetrics.UPLOAD_ERROR_ON_ATTEMPT);
                outline107.append(totalAttemptCount);
                return outline107.toString();
            }
        }, new MetricRecordingType[0]);
        UploadWorkerConfiguration uploadWorkerConfiguration = this.uploadWorkerConfiguration;
        if (uploadWorkerConfiguration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadWorkerConfiguration");
        }
        boolean z2 = attemptCount >= uploadWorkerConfiguration.getMaxAttempts();
        if (z2) {
            AbandonedRequestHandler abandonedRequestHandler = this.abandonedRequestHandler;
            if (abandonedRequestHandler != null) {
                UploadRequest uploadRequest3 = this.request;
                if (uploadRequest3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("request");
                }
                z = abandonedRequestHandler.canAbandonRequest(uploadRequest3);
            }
            if (z) {
                logTimeMetricForAbandonedUpload();
                if (failure.getEx() instanceof SocketTimeoutException) {
                    Metrics metrics2 = this.metrics;
                    if (metrics2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("metrics");
                    }
                    metrics2.recordSimpleEvent(TAG, UploadWorker$handleFailedUpload$2.INSTANCE, new MetricRecordingType[0]);
                } else {
                    FileSizeCategoryProvider fileSizeCategoryProvider = this.fileSizeCategoryProvider;
                    if (fileSizeCategoryProvider == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("fileSizeCategoryProvider");
                    }
                    UploadRequest uploadRequest4 = this.request;
                    if (uploadRequest4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("request");
                    }
                    final FileSizeCategoryProvider.FileSizeCategory fileSizeCategory$AndroidPhotosUploader_release = fileSizeCategoryProvider.getFileSizeCategory$AndroidPhotosUploader_release(uploadRequest4.getFilePath());
                    logMetricForAbandonedUpload(UploadMetrics.UPLOAD_MAX_ATTEMPTS_EXCEEDED, failure.getErrorCode(), failure.getErrorCategory(), failure.getEx());
                    Metrics metrics3 = this.metrics;
                    if (metrics3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("metrics");
                    }
                    metrics3.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.internal.workers.UploadWorker$handleFailedUpload$3
                        @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                        @NotNull
                        public final String getEventName() {
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Abandoned_UPLOAD_SIZE_CATEGORY_MAX_ATTEMPTS_EXCEEDED_");
                            outline107.append(FileSizeCategoryProvider.FileSizeCategory.this.name());
                            return outline107.toString();
                        }
                    }, new MetricRecordingType[0]);
                    Metrics metrics4 = this.metrics;
                    if (metrics4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("metrics");
                    }
                    metrics4.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.internal.workers.UploadWorker$handleFailedUpload$4
                        @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                        @NotNull
                        public final String getEventName() {
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Abandoned_UPLOAD_SIZE_GROUP_CATEGORY_MAX_ATTEMPTS_EXCEEDED_");
                            outline107.append(FileSizeCategoryProvider.FileSizeCategory.this.getSizeGroupCategory$AndroidPhotosUploader_release().name());
                            return outline107.toString();
                        }
                    }, new MetricRecordingType[0]);
                }
                ClientMetric withTagName = new ClientMetric().addCounter(UploadWorker$handleFailedUpload$clientMetric$1.INSTANCE, totalAttemptCount).withTagName(TAG);
                Metrics metrics5 = this.metrics;
                if (metrics5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("metrics");
                }
                metrics5.recordCustomMetric(TAG, withTagName, new MetricRecordingType[0]);
                PersistentLogger persistentLogger = this.logger;
                if (persistentLogger == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("logger");
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Abandoned the request ");
                UploadRequest uploadRequest5 = this.request;
                if (uploadRequest5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("request");
                }
                outline107.append(uploadRequest5.getId());
                outline107.append(" with category = ");
                outline107.append(failure.getErrorCategory());
                outline107.append(" and ");
                outline107.append("FAILED_ATTEMPTS_EXCEEDED");
                persistentLogger.e(TAG, outline107.toString(), failure.getEx());
                abandonUpload(failure.getEx(), failure.getErrorCategory(), AbandonReason.FAILED_ATTEMPTS_EXCEEDED);
                return;
            }
        }
        if (z2) {
            Metrics metrics6 = this.metrics;
            if (metrics6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            metrics6.recordSimpleEvent(TAG, UploadWorker$handleFailedUpload$5.INSTANCE, new MetricRecordingType[0]);
            attemptCount = 0;
        }
        retryFailedUpload(failure, attemptCount, totalAttemptCount);
    }

    private final void handleResponse(UploadResponse uploadResponse) {
        String str;
        ClientMetric withTagName = new ClientMetric().addCounter(UploadWorker$handleResponse$clientMetric$1.INSTANCE, (int) this.meanBytesPerMillis).withTagName(TAG);
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordCustomMetric(TAG, withTagName, new MetricRecordingType[0]);
        if (uploadResponse instanceof UploadResponse.Success) {
            handleSuccessfulUpload((UploadResponse.Success) uploadResponse);
            str = "Success";
        } else if (uploadResponse instanceof UploadResponse.Failure) {
            handleFailedUpload((UploadResponse.Failure) uploadResponse);
            str = "Failure";
        } else if (uploadResponse instanceof UploadResponse.NoRetryFailure) {
            handleUnrecoverableUpload((UploadResponse.NoRetryFailure) uploadResponse);
            str = "NoRetryFailure";
        } else if (uploadResponse instanceof UploadResponse.AcceptableFailure) {
            handleAcceptableError((UploadResponse.AcceptableFailure) uploadResponse);
            str = "AcceptableFailure";
        } else {
            Metrics metrics2 = this.metrics;
            if (metrics2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            metrics2.recordSimpleEvent(TAG, UploadWorker$handleResponse$1.INSTANCE, new MetricRecordingType[0]);
            str = "Unknown";
        }
        PersistentLogger persistentLogger = this.logger;
        if (persistentLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Request ");
        UploadRequest uploadRequest = this.request;
        if (uploadRequest == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        outline107.append(uploadRequest.getId());
        outline107.append(Chars.SPACE);
        outline107.append(str);
        outline107.append(" after ");
        UploadRequest uploadRequest2 = this.request;
        if (uploadRequest2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        outline107.append(uploadRequest2.getTotalAttemptCount() + 1);
        outline107.append(" attempts ");
        outline107.append("with response ");
        PersistentLogger persistentLogger2 = this.logger;
        if (persistentLogger2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        outline107.append(persistentLogger2.obfuscate$AndroidPhotosUploader_release(uploadResponse.toString()));
        persistentLogger.iPersistent(TAG, outline107.toString());
    }

    private final void handleSuccessfulUpload(UploadResponse.Success success) {
        UploadRequest copy;
        boolean boolean$default = ResultMetadata.getBoolean$default(success.getResultMetadata(), CdsUploader.RESULT_SUCCESS_WITH_CONFLICT, false, 2, null);
        FileSizeCategoryProvider fileSizeCategoryProvider = this.fileSizeCategoryProvider;
        if (fileSizeCategoryProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fileSizeCategoryProvider");
        }
        UploadRequest uploadRequest = this.request;
        if (uploadRequest == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        final FileSizeCategoryProvider.FileSizeCategory fileSizeCategory$AndroidPhotosUploader_release = fileSizeCategoryProvider.getFileSizeCategory$AndroidPhotosUploader_release(uploadRequest.getFilePath());
        PersistentLogger persistentLogger = this.logger;
        if (persistentLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("handleSuccessfulUpload IS_SUCCESSFUL_WITH_CONFLICT ");
        sb.append(boolean$default);
        sb.append(Chars.SPACE);
        UploadRequest uploadRequest2 = this.request;
        if (uploadRequest2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        sb.append(uploadRequest2.getId());
        persistentLogger.v(TAG, sb.toString());
        UploadRequest uploadRequest3 = this.request;
        if (uploadRequest3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        final int totalAttemptCount = uploadRequest3.getTotalAttemptCount() + 1;
        long currentTimeMillis = System.currentTimeMillis();
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        UploadWorker$handleSuccessfulUpload$1 uploadWorker$handleSuccessfulUpload$1 = UploadWorker$handleSuccessfulUpload$1.INSTANCE;
        UploadRequest uploadRequest4 = this.request;
        if (uploadRequest4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        metrics.recordSimpleDuration(TAG, uploadWorker$handleSuccessfulUpload$1, currentTimeMillis - uploadRequest4.getCreationTimeMillis());
        Metrics metrics2 = this.metrics;
        if (metrics2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        MetricName metricName = new MetricName() { // from class: com.amazon.photos.uploader.internal.workers.UploadWorker$handleSuccessfulUpload$2
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(UploadMetrics.UPLOAD_SUCCEEDED_ON_ATTEMPT);
                outline107.append(totalAttemptCount);
                return outline107.toString();
            }
        };
        UploadRequest uploadRequest5 = this.request;
        if (uploadRequest5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        metrics2.recordSimpleDuration(TAG, metricName, currentTimeMillis - uploadRequest5.getCreationTimeMillis());
        UploadRequest uploadRequest6 = this.request;
        if (uploadRequest6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        UploadState uploadState = UploadState.SUCCEEDED;
        UploadRequest uploadRequest7 = this.request;
        if (uploadRequest7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        copy = uploadRequest6.copy((r49 & 1) != 0 ? uploadRequest6.id : 0L, (r49 & 2) != 0 ? uploadRequest6.filePath : null, (r49 & 4) != 0 ? uploadRequest6.uploadPath : null, (r49 & 8) != 0 ? uploadRequest6.contentDate : null, (r49 & 16) != 0 ? uploadRequest6.md5 : null, (r49 & 32) != 0 ? uploadRequest6.visualDigest : null, (r49 & 64) != 0 ? uploadRequest6.suppressDeduplication : false, (r49 & 128) != 0 ? uploadRequest6.renameOnNameConflict : false, (r49 & 256) != 0 ? uploadRequest6.uploadCategory : null, (r49 & 512) != 0 ? uploadRequest6.state : uploadState, (r49 & 1024) != 0 ? uploadRequest6.queue : null, (r49 & 2048) != 0 ? uploadRequest6.currentProgress : uploadRequest7.getMaxProgress(), (r49 & 4096) != 0 ? uploadRequest6.maxProgress : 0L, (r49 & 8192) != 0 ? uploadRequest6.errorCode : null, (r49 & 16384) != 0 ? uploadRequest6.errorCategory : null, (r49 & 32768) != 0 ? uploadRequest6.blocker : null, (r49 & 65536) != 0 ? uploadRequest6.totalAttemptCount : 0, (r49 & 131072) != 0 ? uploadRequest6.attemptCount : 0, (r49 & 262144) != 0 ? uploadRequest6.maxAttemptsExceeded : false, (r49 & 524288) != 0 ? uploadRequest6.creationTimeMillis : 0L, (r49 & 1048576) != 0 ? uploadRequest6.fileSize : 0L, (r49 & 2097152) != 0 ? uploadRequest6.priority : 0, (4194304 & r49) != 0 ? uploadRequest6.addToFamilyVault : false, (r49 & 8388608) != 0 ? uploadRequest6.appData : null, (r49 & 16777216) != 0 ? uploadRequest6.parentId : null, (r49 & 33554432) != 0 ? uploadRequest6.contentUri : null);
        UploadRequestDao uploadRequestDao = this.requestDao;
        if (uploadRequestDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestDao");
        }
        uploadRequestDao.updateRequest(copy);
        UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier = this.uploadRequestUpdatesNotifier;
        if (uploadRequestUpdatesNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequestUpdatesNotifier");
        }
        uploadRequestUpdatesNotifier.onUploadSucceeded$AndroidPhotosUploader_release(copy, success.getResultMetadata());
        Metrics metrics3 = this.metrics;
        if (metrics3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics3.recordSimpleEvent(TAG, UploadWorker$handleSuccessfulUpload$3.INSTANCE, new MetricRecordingType[0]);
        Metrics metrics4 = this.metrics;
        if (metrics4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics4.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.internal.workers.UploadWorker$handleSuccessfulUpload$4
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(UploadMetrics.UPLOAD_SIZE_CATEGORY_SUCCEEDED);
                outline107.append(FileSizeCategoryProvider.FileSizeCategory.this.name());
                return outline107.toString();
            }
        }, new MetricRecordingType[0]);
        Metrics metrics5 = this.metrics;
        if (metrics5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics5.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.internal.workers.UploadWorker$handleSuccessfulUpload$5
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(UploadMetrics.UPLOAD_SIZE_GROUP_CATEGORY_SUCCEEDED);
                outline107.append(FileSizeCategoryProvider.FileSizeCategory.this.getSizeGroupCategory$AndroidPhotosUploader_release().name());
                return outline107.toString();
            }
        }, new MetricRecordingType[0]);
        Metrics metrics6 = this.metrics;
        if (metrics6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics6.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.internal.workers.UploadWorker$handleSuccessfulUpload$6
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(UploadMetrics.UPLOAD_SUCCEEDED_ON_ATTEMPT);
                outline107.append(totalAttemptCount);
                return outline107.toString();
            }
        }, new MetricRecordingType[0]);
        ClientMetric withTagName = new ClientMetric().addCounter(UploadWorker$handleSuccessfulUpload$clientMetric$1.INSTANCE, totalAttemptCount).withTagName(TAG);
        Metrics metrics7 = this.metrics;
        if (metrics7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics7.recordCustomMetric(TAG, withTagName, new MetricRecordingType[0]);
    }

    private final void handleUnrecoverableUpload(final UploadResponse.NoRetryFailure noRetryFailure) {
        logTimeMetricForAbandonedUpload();
        logMetricForAbandonedUpload(UploadMetrics.UPLOAD_UNRECOVERABLE, noRetryFailure.getErrorCode(), noRetryFailure.getErrorCategory(), noRetryFailure.getEx());
        FileSizeCategoryProvider fileSizeCategoryProvider = this.fileSizeCategoryProvider;
        if (fileSizeCategoryProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fileSizeCategoryProvider");
        }
        UploadRequest uploadRequest = this.request;
        if (uploadRequest == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        final FileSizeCategoryProvider.FileSizeCategory fileSizeCategory$AndroidPhotosUploader_release = fileSizeCategoryProvider.getFileSizeCategory$AndroidPhotosUploader_release(uploadRequest.getFilePath());
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.internal.workers.UploadWorker$handleUnrecoverableUpload$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Abandoned_UPLOAD_UNRECOVERABLE_SIZE_CATEGORY_");
                outline107.append(UploadResponse.NoRetryFailure.this.getErrorCategory().name());
                outline107.append('_');
                outline107.append(fileSizeCategory$AndroidPhotosUploader_release.name());
                return outline107.toString();
            }
        }, new MetricRecordingType[0]);
        Metrics metrics2 = this.metrics;
        if (metrics2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics2.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.internal.workers.UploadWorker$handleUnrecoverableUpload$2
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Abandoned_UPLOAD_UNRECOVERABLE_SIZE_GROUP_CATEGORY_");
                outline107.append(UploadResponse.NoRetryFailure.this.getErrorCategory().name());
                outline107.append('_');
                outline107.append(fileSizeCategory$AndroidPhotosUploader_release.getSizeGroupCategory$AndroidPhotosUploader_release().name());
                return outline107.toString();
            }
        }, new MetricRecordingType[0]);
        PersistentLogger persistentLogger = this.logger;
        if (persistentLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Abandoned the request ");
        UploadRequest uploadRequest2 = this.request;
        if (uploadRequest2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        outline107.append(uploadRequest2.getId());
        outline107.append(" with category = ");
        outline107.append(noRetryFailure.getErrorCategory());
        outline107.append(" and ");
        outline107.append("NO_RETRY_FAILURE");
        persistentLogger.e(TAG, outline107.toString(), noRetryFailure.getEx());
        abandonUpload(noRetryFailure.getEx(), noRetryFailure.getErrorCategory(), AbandonReason.NO_RETRY_FAILURE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isRequestNotExistOrCancelled(UploadRequest uploadRequest) {
        return uploadRequest == null || uploadRequest.getState() == UploadState.CANCELLED;
    }

    private final void logErrorMetric(final String str, final String str2, final UploadErrorCategory uploadErrorCategory, final Throwable th) {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.internal.workers.UploadWorker$logErrorMetric$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return str;
            }
        }, new MetricRecordingType[0]);
        Metrics metrics2 = this.metrics;
        if (metrics2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics2.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.internal.workers.UploadWorker$logErrorMetric$2
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return str + JsonReaderKt.COLON + str2;
            }
        }, new MetricRecordingType[0]);
        Metrics metrics3 = this.metrics;
        if (metrics3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics3.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.internal.workers.UploadWorker$logErrorMetric$3
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return str + JsonReaderKt.COLON + th.getClass().getSimpleName();
            }
        }, new MetricRecordingType[0]);
        Metrics metrics4 = this.metrics;
        if (metrics4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics4.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.internal.workers.UploadWorker$logErrorMetric$4
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return str + "_CATEGORY_" + uploadErrorCategory.name();
            }
        }, new MetricRecordingType[0]);
    }

    private final void logFailedMetric(String str, String str2, UploadErrorCategory uploadErrorCategory, Throwable th) {
        logErrorMetric(GeneratedOutlineSupport1.outline72("Failed_", str), str2, uploadErrorCategory, th);
    }

    private final void logMetricForAbandonedUpload(String str, String str2, UploadErrorCategory uploadErrorCategory, Throwable th) {
        logErrorMetric(GeneratedOutlineSupport1.outline72("Abandoned_", str), str2, uploadErrorCategory, th);
    }

    private final void logTimeMetricForAbandonedUpload() {
        long currentTimeMillis = System.currentTimeMillis();
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        UploadWorker$logTimeMetricForAbandonedUpload$1 uploadWorker$logTimeMetricForAbandonedUpload$1 = UploadWorker$logTimeMetricForAbandonedUpload$1.INSTANCE;
        UploadRequest uploadRequest = this.request;
        if (uploadRequest == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        metrics.recordSimpleDuration(TAG, uploadWorker$logTimeMetricForAbandonedUpload$1, currentTimeMillis - uploadRequest.getCreationTimeMillis());
    }

    private final void recordUploadStartMetrics(long j) {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        UploadWorker$recordUploadStartMetrics$1 uploadWorker$recordUploadStartMetrics$1 = UploadWorker$recordUploadStartMetrics$1.INSTANCE;
        UploadRequest uploadRequest = this.request;
        if (uploadRequest == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        metrics.recordSimpleDuration(TAG, uploadWorker$recordUploadStartMetrics$1, j - uploadRequest.getCreationTimeMillis());
        Metrics metrics2 = this.metrics;
        if (metrics2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        MetricName metricName = new MetricName() { // from class: com.amazon.photos.uploader.internal.workers.UploadWorker$recordUploadStartMetrics$2
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(UploadMetrics.UPLOAD_STARTED_REQUEST_TIME_FOR_ATTEMPT);
                outline107.append(UploadWorker.access$getRequest$p(UploadWorker.this).getTotalAttemptCount());
                return outline107.toString();
            }
        };
        UploadRequest uploadRequest2 = this.request;
        if (uploadRequest2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        metrics2.recordSimpleDuration(TAG, metricName, j - uploadRequest2.getCreationTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reportUploadBlocked() {
        UploaderTransactionRunner uploaderTransactionRunner = this.transactionRunner;
        if (uploaderTransactionRunner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transactionRunner");
        }
        uploaderTransactionRunner.runInTransaction$AndroidPhotosUploader_release(new Runnable() { // from class: com.amazon.photos.uploader.internal.workers.UploadWorker$reportUploadBlocked$1

            /* compiled from: UploadWorker.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "getEventName"}, k = 3, mv = {1, 1, 16})
            /* renamed from: com.amazon.photos.uploader.internal.workers.UploadWorker$reportUploadBlocked$1$2  reason: invalid class name */
            /* loaded from: classes13.dex */
            static final class AnonymousClass2 implements MetricName {
                public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

                AnonymousClass2() {
                }

                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                @NotNull
                public final String getEventName() {
                    return UploadMetrics.UPLOAD_BLOCKED_UNKNOWN_REASON;
                }
            }

            /* compiled from: UploadWorker.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "getEventName"}, k = 3, mv = {1, 1, 16})
            /* renamed from: com.amazon.photos.uploader.internal.workers.UploadWorker$reportUploadBlocked$1$3  reason: invalid class name */
            /* loaded from: classes13.dex */
            static final class AnonymousClass3 implements MetricName {
                public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

                AnonymousClass3() {
                }

                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                @NotNull
                public final String getEventName() {
                    return UploadMetrics.UPLOAD_BLOCKED;
                }
            }

            @Override // java.lang.Runnable
            public final void run() {
                Blocker blockerReason;
                UploadRequest copy;
                UploadRequest requestById = UploadWorker.this.getRequestDao().getRequestById(UploadWorker.access$getRequest$p(UploadWorker.this).getId());
                if (requestById == null || requestById.getState() == UploadState.CANCELLED || requestById.getState() == UploadState.SUCCEEDED) {
                    return;
                }
                blockerReason = UploadWorker.this.getBlockerReason(requestById);
                copy = r3.copy((r49 & 1) != 0 ? r3.id : 0L, (r49 & 2) != 0 ? r3.filePath : null, (r49 & 4) != 0 ? r3.uploadPath : null, (r49 & 8) != 0 ? r3.contentDate : null, (r49 & 16) != 0 ? r3.md5 : null, (r49 & 32) != 0 ? r3.visualDigest : null, (r49 & 64) != 0 ? r3.suppressDeduplication : false, (r49 & 128) != 0 ? r3.renameOnNameConflict : false, (r49 & 256) != 0 ? r3.uploadCategory : null, (r49 & 512) != 0 ? r3.state : UploadState.BLOCKED, (r49 & 1024) != 0 ? r3.queue : null, (r49 & 2048) != 0 ? r3.currentProgress : 0L, (r49 & 4096) != 0 ? r3.maxProgress : 0L, (r49 & 8192) != 0 ? r3.errorCode : null, (r49 & 16384) != 0 ? r3.errorCategory : null, (r49 & 32768) != 0 ? r3.blocker : blockerReason, (r49 & 65536) != 0 ? r3.totalAttemptCount : 0, (r49 & 131072) != 0 ? r3.attemptCount : 0, (r49 & 262144) != 0 ? r3.maxAttemptsExceeded : false, (r49 & 524288) != 0 ? r3.creationTimeMillis : 0L, (r49 & 1048576) != 0 ? r3.fileSize : 0L, (r49 & 2097152) != 0 ? r3.priority : 0, (4194304 & r49) != 0 ? r3.addToFamilyVault : false, (r49 & 8388608) != 0 ? r3.appData : null, (r49 & 16777216) != 0 ? r3.parentId : null, (r49 & 33554432) != 0 ? UploadWorker.access$getRequest$p(UploadWorker.this).contentUri : null);
                UploadWorker.this.getRequestDao().updateRequest(copy);
                if (blockerReason != null) {
                    UploadWorker.this.getUploadRequestUpdatesNotifier().onUploadBlocked$AndroidPhotosUploader_release(copy, blockerReason);
                } else {
                    UploadWorker.this.getMetrics().recordSimpleEvent(UploadWorker.TAG, AnonymousClass2.INSTANCE, new MetricRecordingType[0]);
                }
                UploadWorker.this.getMetrics().recordSimpleEvent(UploadWorker.TAG, AnonymousClass3.INSTANCE, new MetricRecordingType[0]);
                PersistentLogger logger = UploadWorker.this.getLogger();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Request ");
                outline107.append(UploadWorker.access$getRequest$p(UploadWorker.this).getId());
                outline107.append(" became blocked due to ");
                outline107.append(blockerReason);
                logger.i(UploadWorker.TAG, outline107.toString());
            }
        });
    }

    private final void retryFailedUpload(UploadResponse.Failure failure, int i, int i2) {
        UploadRequest copy;
        UploadRequest copy2;
        UploadRequest revisedRequest = failure.getRevisedRequest();
        if (revisedRequest == null && (revisedRequest = this.request) == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        UploadRequest uploadRequest = revisedRequest;
        UploadState uploadState = UploadState.ENQUEUED;
        String errorCode = failure.getErrorCode();
        UploadErrorCategory errorCategory = failure.getErrorCategory();
        UploadRequest uploadRequest2 = this.request;
        if (uploadRequest2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        copy = uploadRequest.copy((r49 & 1) != 0 ? uploadRequest.id : 0L, (r49 & 2) != 0 ? uploadRequest.filePath : null, (r49 & 4) != 0 ? uploadRequest.uploadPath : null, (r49 & 8) != 0 ? uploadRequest.contentDate : null, (r49 & 16) != 0 ? uploadRequest.md5 : null, (r49 & 32) != 0 ? uploadRequest.visualDigest : null, (r49 & 64) != 0 ? uploadRequest.suppressDeduplication : false, (r49 & 128) != 0 ? uploadRequest.renameOnNameConflict : false, (r49 & 256) != 0 ? uploadRequest.uploadCategory : null, (r49 & 512) != 0 ? uploadRequest.state : uploadState, (r49 & 1024) != 0 ? uploadRequest.queue : null, (r49 & 2048) != 0 ? uploadRequest.currentProgress : 0L, (r49 & 4096) != 0 ? uploadRequest.maxProgress : 0L, (r49 & 8192) != 0 ? uploadRequest.errorCode : errorCode, (r49 & 16384) != 0 ? uploadRequest.errorCategory : errorCategory, (r49 & 32768) != 0 ? uploadRequest.blocker : null, (r49 & 65536) != 0 ? uploadRequest.totalAttemptCount : i2, (r49 & 131072) != 0 ? uploadRequest.attemptCount : i, (r49 & 262144) != 0 ? uploadRequest.maxAttemptsExceeded : false, (r49 & 524288) != 0 ? uploadRequest.creationTimeMillis : 0L, (r49 & 1048576) != 0 ? uploadRequest.fileSize : 0L, (r49 & 2097152) != 0 ? uploadRequest.priority : uploadRequest2.getPriority() - 1, (4194304 & r49) != 0 ? uploadRequest.addToFamilyVault : false, (r49 & 8388608) != 0 ? uploadRequest.appData : null, (r49 & 16777216) != 0 ? uploadRequest.parentId : null, (r49 & 33554432) != 0 ? uploadRequest.contentUri : null);
        UploadRequestDao uploadRequestDao = this.requestDao;
        if (uploadRequestDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestDao");
        }
        uploadRequestDao.updateRequest(copy);
        copy2 = copy.copy((r49 & 1) != 0 ? copy.id : 0L, (r49 & 2) != 0 ? copy.filePath : null, (r49 & 4) != 0 ? copy.uploadPath : null, (r49 & 8) != 0 ? copy.contentDate : null, (r49 & 16) != 0 ? copy.md5 : null, (r49 & 32) != 0 ? copy.visualDigest : null, (r49 & 64) != 0 ? copy.suppressDeduplication : false, (r49 & 128) != 0 ? copy.renameOnNameConflict : false, (r49 & 256) != 0 ? copy.uploadCategory : null, (r49 & 512) != 0 ? copy.state : UploadState.FAILED, (r49 & 1024) != 0 ? copy.queue : null, (r49 & 2048) != 0 ? copy.currentProgress : 0L, (r49 & 4096) != 0 ? copy.maxProgress : 0L, (r49 & 8192) != 0 ? copy.errorCode : null, (r49 & 16384) != 0 ? copy.errorCategory : null, (r49 & 32768) != 0 ? copy.blocker : null, (r49 & 65536) != 0 ? copy.totalAttemptCount : 0, (r49 & 131072) != 0 ? copy.attemptCount : 0, (r49 & 262144) != 0 ? copy.maxAttemptsExceeded : false, (r49 & 524288) != 0 ? copy.creationTimeMillis : 0L, (r49 & 1048576) != 0 ? copy.fileSize : 0L, (r49 & 2097152) != 0 ? copy.priority : 0, (4194304 & r49) != 0 ? copy.addToFamilyVault : false, (r49 & 8388608) != 0 ? copy.appData : null, (r49 & 16777216) != 0 ? copy.parentId : null, (r49 & 33554432) != 0 ? copy.contentUri : null);
        PersistentLogger persistentLogger = this.logger;
        if (persistentLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Retrying the failed upload ");
        outline107.append(copy2.getId());
        outline107.append(" exception = ");
        outline107.append(failure.getEx());
        outline107.append(Chars.SPACE);
        outline107.append(failure.getErrorCategory());
        persistentLogger.w(TAG, outline107.toString(), failure.getEx());
        UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier = this.uploadRequestUpdatesNotifier;
        if (uploadRequestUpdatesNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequestUpdatesNotifier");
        }
        uploadRequestUpdatesNotifier.onUploadFailed$AndroidPhotosUploader_release(copy2, failure.getEx(), failure.getErrorCategory());
        PersistentLogger persistentLogger2 = this.logger;
        if (persistentLogger2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        persistentLogger2.i(TAG, "Enqueuing new scheduling pass to re-evaluate failed request.");
        OneTimeWorkRequest build = new OneTimeWorkRequest.Builder(ReevaluateWorker.class).addTag(UploadManager.WORK_MANAGER_TAG).setInputData(getInputData()).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "OneTimeWorkRequest.Build…utData(inputData).build()");
        OneTimeWorkRequest oneTimeWorkRequest = build;
        WorkManager workManager = this.workManager;
        if (workManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        workManager.enqueueUniqueWork(SchedulingCallbackKt.SCHEDULER_CHAIN_UNIQUE_NAME, ExistingWorkPolicy.APPEND, oneTimeWorkRequest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAcceptableFailure(UploadCompleter uploadCompleter, String str, Throwable th, UploadErrorCategory uploadErrorCategory) {
        uploadCompleter.setResponse(new UploadResponse.AcceptableFailure(str, th, uploadErrorCategory));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFailure(UploadCompleter uploadCompleter, String str, Throwable th, UploadErrorCategory uploadErrorCategory) {
        uploadCompleter.setResponse(new UploadResponse.Failure(str, th, uploadErrorCategory, null, false, 24, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setNoRetryFailure(UploadCompleter uploadCompleter, String str, Throwable th, UploadErrorCategory uploadErrorCategory) {
        uploadCompleter.setResponse(new UploadResponse.NoRetryFailure(str, th, uploadErrorCategory));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRequestToRunning(final UploadCompleter uploadCompleter) {
        UploaderTransactionRunner uploaderTransactionRunner = this.transactionRunner;
        if (uploaderTransactionRunner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transactionRunner");
        }
        uploaderTransactionRunner.runInTransaction$AndroidPhotosUploader_release(new Runnable() { // from class: com.amazon.photos.uploader.internal.workers.UploadWorker$setRequestToRunning$1

            /* compiled from: UploadWorker.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "getEventName"}, k = 3, mv = {1, 1, 16})
            /* renamed from: com.amazon.photos.uploader.internal.workers.UploadWorker$setRequestToRunning$1$1  reason: invalid class name */
            /* loaded from: classes13.dex */
            static final class AnonymousClass1 implements MetricName {
                public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

                AnonymousClass1() {
                }

                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                @NotNull
                public final String getEventName() {
                    return UploadMetrics.BAIL_UPLOAD_REQUEST_CANCELLED;
                }
            }

            /* JADX WARN: Code restructure failed: missing block: B:8:0x00b0, code lost:
                if (r1 != false) goto L12;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void run() {
                /*
                    Method dump skipped, instructions count: 282
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.uploader.internal.workers.UploadWorker$setRequestToRunning$1.run():void");
            }
        });
    }

    private final boolean shouldCalculateMd5() {
        UploadRequest uploadRequest = this.request;
        if (uploadRequest == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        return uploadRequest.getMd5() == null;
    }

    private final boolean shouldCalculateVisualDigest(File file) {
        UploadRequest uploadRequest = this.request;
        if (uploadRequest == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        if (uploadRequest.getVisualDigest() == null && this.supportedFeatures.contains(Feature.VISUAL_DIGEST)) {
            ContentSignatureProvider contentSignatureProvider = this.contentSignatureProvider;
            if (contentSignatureProvider == null) {
                Intrinsics.throwUninitializedPropertyAccessException("contentSignatureProvider");
            }
            if (contentSignatureProvider.shouldComputeVisualDigest$AndroidPhotosUploader_release(file)) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    @NotNull
    public final File createFileForPath$AndroidPhotosUploader_release(@NotNull String filePath) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        return new File(filePath);
    }

    @Nullable
    public final AbandonedRequestHandler getAbandonedRequestHandler() {
        return this.abandonedRequestHandler;
    }

    @NotNull
    public final ContentSignatureProvider getContentSignatureProvider() {
        ContentSignatureProvider contentSignatureProvider = this.contentSignatureProvider;
        if (contentSignatureProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentSignatureProvider");
        }
        return contentSignatureProvider;
    }

    @NotNull
    public final FileSizeCategoryProvider getFileSizeCategoryProvider() {
        FileSizeCategoryProvider fileSizeCategoryProvider = this.fileSizeCategoryProvider;
        if (fileSizeCategoryProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fileSizeCategoryProvider");
        }
        return fileSizeCategoryProvider;
    }

    @NotNull
    public final FileUtils getFileUtils() {
        FileUtils fileUtils = this.fileUtils;
        if (fileUtils == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fileUtils");
        }
        return fileUtils;
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
    public final UploadRequestDao getRequestDao() {
        UploadRequestDao uploadRequestDao = this.requestDao;
        if (uploadRequestDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestDao");
        }
        return uploadRequestDao;
    }

    @NotNull
    public final SystemUtil getSystemUtil() {
        SystemUtil systemUtil = this.systemUtil;
        if (systemUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
        }
        return systemUtil;
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
    public final UploadWorkerConfiguration getUploadWorkerConfiguration() {
        UploadWorkerConfiguration uploadWorkerConfiguration = this.uploadWorkerConfiguration;
        if (uploadWorkerConfiguration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadWorkerConfiguration");
        }
        return uploadWorkerConfiguration;
    }

    @NotNull
    public final Uploader getUploader() {
        Uploader uploader = this.uploader;
        if (uploader == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploader");
        }
        return uploader;
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
        if (this.hasValidRequestId) {
            UploadFrameworkComponent component$AndroidPhotosUploader_release = UploadManagerMap.INSTANCE.getUploadManagerForAccount$AndroidPhotosUploader_release(this.hashedDirectedId).getComponent$AndroidPhotosUploader_release();
            component$AndroidPhotosUploader_release.inject(this);
            this.supportedFeatures.addAll(component$AndroidPhotosUploader_release.getSupportedFeatures());
            UploadRequestDao uploadRequestDao = this.requestDao;
            if (uploadRequestDao == null) {
                Intrinsics.throwUninitializedPropertyAccessException("requestDao");
            }
            this.request = uploadRequestDao.getRequestById(this.requestId);
            return;
        }
        throw new IllegalArgumentException("No request id associated with worker.");
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
            boolean r0 = r5 instanceof com.amazon.photos.uploader.internal.workers.UploadWorker$mainTask$1
            if (r0 == 0) goto L13
            r0 = r5
            com.amazon.photos.uploader.internal.workers.UploadWorker$mainTask$1 r0 = (com.amazon.photos.uploader.internal.workers.UploadWorker$mainTask$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.photos.uploader.internal.workers.UploadWorker$mainTask$1 r0 = new com.amazon.photos.uploader.internal.workers.UploadWorker$mainTask$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            com.amazon.photos.uploader.internal.workers.UploadWorker r0 = (com.amazon.photos.uploader.internal.workers.UploadWorker) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L49
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            com.amazon.photos.uploader.internal.workers.UploadWorker$mainTask$2 r5 = new com.amazon.photos.uploader.internal.workers.UploadWorker$mainTask$2
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
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.uploader.internal.workers.UploadWorker.mainTask(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Inject
    public final void setAbandonedRequestHandler(@Nullable AbandonedRequestHandler abandonedRequestHandler) {
        this.abandonedRequestHandler = abandonedRequestHandler;
    }

    @Inject
    public final void setContentSignatureProvider(@NotNull ContentSignatureProvider contentSignatureProvider) {
        Intrinsics.checkParameterIsNotNull(contentSignatureProvider, "<set-?>");
        this.contentSignatureProvider = contentSignatureProvider;
    }

    @Inject
    public final void setFileSizeCategoryProvider(@NotNull FileSizeCategoryProvider fileSizeCategoryProvider) {
        Intrinsics.checkParameterIsNotNull(fileSizeCategoryProvider, "<set-?>");
        this.fileSizeCategoryProvider = fileSizeCategoryProvider;
    }

    @Inject
    public final void setFileUtils(@NotNull FileUtils fileUtils) {
        Intrinsics.checkParameterIsNotNull(fileUtils, "<set-?>");
        this.fileUtils = fileUtils;
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
    public final void setRequestDao(@NotNull UploadRequestDao uploadRequestDao) {
        Intrinsics.checkParameterIsNotNull(uploadRequestDao, "<set-?>");
        this.requestDao = uploadRequestDao;
    }

    @Inject
    public final void setSystemUtil(@NotNull SystemUtil systemUtil) {
        Intrinsics.checkParameterIsNotNull(systemUtil, "<set-?>");
        this.systemUtil = systemUtil;
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
    public final void setUploadWorkerConfiguration(@NotNull UploadWorkerConfiguration uploadWorkerConfiguration) {
        Intrinsics.checkParameterIsNotNull(uploadWorkerConfiguration, "<set-?>");
        this.uploadWorkerConfiguration = uploadWorkerConfiguration;
    }

    @Inject
    public final void setUploader(@NotNull Uploader uploader) {
        Intrinsics.checkParameterIsNotNull(uploader, "<set-?>");
        this.uploader = uploader;
    }

    @Inject
    public final void setWorkManager(@NotNull WorkManager workManager) {
        Intrinsics.checkParameterIsNotNull(workManager, "<set-?>");
        this.workManager = workManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0040  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object startUpload(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.amazon.photos.uploader.internal.workers.UploadWorker$startUpload$1
            if (r0 == 0) goto L13
            r0 = r7
            com.amazon.photos.uploader.internal.workers.UploadWorker$startUpload$1 r0 = (com.amazon.photos.uploader.internal.workers.UploadWorker$startUpload$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.photos.uploader.internal.workers.UploadWorker$startUpload$1 r0 = new com.amazon.photos.uploader.internal.workers.UploadWorker$startUpload$1
            r0.<init>(r6, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L40
            if (r2 != r3) goto L38
            java.lang.Object r1 = r0.L$2
            com.google.common.util.concurrent.ListenableFuture r1 = (com.google.common.util.concurrent.ListenableFuture) r1
            java.lang.Object r1 = r0.L$1
            com.google.common.util.concurrent.ListenableFuture r1 = (com.google.common.util.concurrent.ListenableFuture) r1
            long r1 = r0.J$0
            java.lang.Object r0 = r0.L$0
            com.amazon.photos.uploader.internal.workers.UploadWorker r0 = (com.amazon.photos.uploader.internal.workers.UploadWorker) r0
            kotlin.ResultKt.throwOnFailure(r7)
            goto Lc4
        L38:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L40:
            kotlin.ResultKt.throwOnFailure(r7)
            com.amazon.photos.uploader.internal.utils.PersistentLogger r7 = r6.logger
            if (r7 != 0) goto L4c
            java.lang.String r2 = "logger"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L4c:
            java.lang.String r2 = "Uploading request "
            java.lang.StringBuilder r2 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r2)
            com.amazon.photos.uploader.UploadRequest r4 = r6.request
            if (r4 != 0) goto L5b
            java.lang.String r5 = "request"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
        L5b:
            long r4 = r4.getId()
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            java.lang.String r4 = "UploadWorker"
            r7.i(r4, r2)
            long r4 = java.lang.System.currentTimeMillis()
            r6.recordUploadStartMetrics(r4)
            com.amazon.photos.uploader.internal.workers.UploadWorker$startUpload$responseFuture$1 r7 = new com.amazon.photos.uploader.internal.workers.UploadWorker$startUpload$responseFuture$1
            r7.<init>()
            com.google.common.util.concurrent.ListenableFuture r7 = androidx.concurrent.futures.CallbackToFutureAdapter.getFuture(r7)
            java.lang.String r2 = "CallbackToFutureAdapter.… \" + request.id\n        }"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r2)
            r6.inProgressUploadFuture = r7
            boolean r2 = r7.isDone()
            if (r2 == 0) goto L96
            java.lang.Object r7 = r7.get()     // Catch: java.util.concurrent.ExecutionException -> L8d
            goto Lc3
        L8d:
            r7 = move-exception
            java.lang.Throwable r0 = r7.getCause()
            if (r0 == 0) goto L95
            r7 = r0
        L95:
            throw r7
        L96:
            r0.L$0 = r6
            r0.J$0 = r4
            r0.L$1 = r7
            r0.L$2 = r7
            r0.label = r3
            kotlinx.coroutines.CancellableContinuationImpl r2 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r0)
            r2.<init>(r4, r3)
            com.amazon.photos.uploader.internal.workers.UploadWorker$await$$inlined$suspendCancellableCoroutine$lambda$1 r3 = new com.amazon.photos.uploader.internal.workers.UploadWorker$await$$inlined$suspendCancellableCoroutine$lambda$1
            r3.<init>()
            androidx.work.DirectExecutor r4 = androidx.work.DirectExecutor.INSTANCE
            r7.addListener(r3, r4)
            java.lang.Object r7 = r2.getResult()
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r7 != r2) goto Lc0
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        Lc0:
            if (r7 != r1) goto Lc3
            return r1
        Lc3:
            r0 = r6
        Lc4:
            com.amazon.photos.uploader.UploadResponse r7 = (com.amazon.photos.uploader.UploadResponse) r7
            java.lang.String r1 = "response"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r1)
            r0.handleResponse(r7)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.uploader.internal.workers.UploadWorker.startUpload(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startUpload(UploadCompleter uploadCompleter, final long j) {
        UploadRequest uploadRequest = this.request;
        if (uploadRequest == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        uploadCompleter.startUploadMetric$AndroidPhotosUploader_release(j, uploadRequest.getFilePath());
        Uploader uploader = this.uploader;
        if (uploader == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploader");
        }
        UploadRequest uploadRequest2 = this.request;
        if (uploadRequest2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        uploader.startUpload(uploadRequest2, uploadCompleter, new UploadProgressListener() { // from class: com.amazon.photos.uploader.internal.workers.UploadWorker$startUpload$2
            @Override // com.amazon.photos.uploader.UploadProgressListener
            public void onProgressUpdate(long j2, long j3) {
                UploadRequest copy;
                long currentTimeMillis = System.currentTimeMillis() - j;
                UploadWorker.this.meanBytesPerMillis = currentTimeMillis == 0 ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : j2 / currentTimeMillis;
                UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier = UploadWorker.this.getUploadRequestUpdatesNotifier();
                copy = r4.copy((r49 & 1) != 0 ? r4.id : 0L, (r49 & 2) != 0 ? r4.filePath : null, (r49 & 4) != 0 ? r4.uploadPath : null, (r49 & 8) != 0 ? r4.contentDate : null, (r49 & 16) != 0 ? r4.md5 : null, (r49 & 32) != 0 ? r4.visualDigest : null, (r49 & 64) != 0 ? r4.suppressDeduplication : false, (r49 & 128) != 0 ? r4.renameOnNameConflict : false, (r49 & 256) != 0 ? r4.uploadCategory : null, (r49 & 512) != 0 ? r4.state : null, (r49 & 1024) != 0 ? r4.queue : null, (r49 & 2048) != 0 ? r4.currentProgress : j2, (r49 & 4096) != 0 ? r4.maxProgress : j3, (r49 & 8192) != 0 ? r4.errorCode : null, (r49 & 16384) != 0 ? r4.errorCategory : null, (r49 & 32768) != 0 ? r4.blocker : null, (r49 & 65536) != 0 ? r4.totalAttemptCount : 0, (r49 & 131072) != 0 ? r4.attemptCount : 0, (r49 & 262144) != 0 ? r4.maxAttemptsExceeded : false, (r49 & 524288) != 0 ? r4.creationTimeMillis : 0L, (r49 & 1048576) != 0 ? r4.fileSize : 0L, (r49 & 2097152) != 0 ? r4.priority : 0, (4194304 & r49) != 0 ? r4.addToFamilyVault : false, (r49 & 8388608) != 0 ? r4.appData : null, (r49 & 16777216) != 0 ? r4.parentId : null, (r49 & 33554432) != 0 ? UploadWorker.access$getRequest$p(UploadWorker.this).contentUri : null);
                uploadRequestUpdatesNotifier.onUploadProgressUpdate$AndroidPhotosUploader_release(copy, j2, j3);
            }
        });
    }
}
