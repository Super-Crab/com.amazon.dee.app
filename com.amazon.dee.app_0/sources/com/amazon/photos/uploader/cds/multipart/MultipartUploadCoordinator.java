package com.amazon.photos.uploader.cds.multipart;

import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadProgressListener;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadResponse;
import com.amazon.photos.uploader.internal.utils.RxUtilsKt;
import com.amazon.photos.uploader.log.UploadLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.Lists;
import io.reactivex.rxjava3.disposables.Disposable;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: MultipartUploadCoordinator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 k2\u00020\u0001:\u0001kB]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017¢\u0006\u0002\u0010\u0018J\b\u0010H\u001a\u00020\u001fH\u0002J\u0010\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020\u001fH\u0002J\u0006\u0010L\u001a\u00020JJ\u0006\u0010M\u001a\u00020JJ\u0006\u0010N\u001a\u00020JJ\b\u0010O\u001a\u00020JH\u0002JL\u0010P\u001a\u00020J2!\u0010Q\u001a\u001d\u0012\u0013\u0012\u00110S¢\u0006\f\bT\u0012\b\bU\u0012\u0004\b\b(V\u0012\u0004\u0012\u00020J0R2!\u0010W\u001a\u001d\u0012\u0013\u0012\u00110S¢\u0006\f\bT\u0012\b\bU\u0012\u0004\b\b(X\u0012\u0004\u0012\u00020J0RJ\u0006\u0010Y\u001a\u00020JJL\u0010Z\u001a\u00020J2!\u0010Q\u001a\u001d\u0012\u0013\u0012\u00110S¢\u0006\f\bT\u0012\b\bU\u0012\u0004\b\b(V\u0012\u0004\u0012\u00020J0R2!\u0010W\u001a\u001d\u0012\u0013\u0012\u00110S¢\u0006\f\bT\u0012\b\bU\u0012\u0004\b\b(X\u0012\u0004\u0012\u00020J0RJ\b\u0010[\u001a\u00020\u001aH\u0002JT\u0010\\\u001a\u00020J2\u0006\u0010]\u001a\u00020B2!\u0010Q\u001a\u001d\u0012\u0013\u0012\u00110'¢\u0006\f\bT\u0012\b\bU\u0012\u0004\b\b(V\u0012\u0004\u0012\u00020J0R2!\u0010W\u001a\u001d\u0012\u0013\u0012\u00110S¢\u0006\f\bT\u0012\b\bU\u0012\u0004\b\b(X\u0012\u0004\u0012\u00020J0RJ \u0010^\u001a\u00020J2\u0006\u0010_\u001a\u00020\u001a2\u0006\u0010`\u001a\u00020\u001a2\u0006\u0010a\u001a\u00020\u001aH\u0016JL\u0010b\u001a\u00020J2!\u0010Q\u001a\u001d\u0012\u0013\u0012\u00110S¢\u0006\f\bT\u0012\b\bU\u0012\u0004\b\b(V\u0012\u0004\u0012\u00020J0R2!\u0010W\u001a\u001d\u0012\u0013\u0012\u00110S¢\u0006\f\bT\u0012\b\bU\u0012\u0004\b\b(X\u0012\u0004\u0012\u00020J0RJn\u0010c\u001a\u00020J2\u0006\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020g2\u0006\u0010h\u001a\u00020\u001a2\u0006\u0010i\u001a\u00020\u00012!\u0010Q\u001a\u001d\u0012\u0013\u0012\u00110S¢\u0006\f\bT\u0012\b\bU\u0012\u0004\b\b(V\u0012\u0004\u0012\u00020J0R2!\u0010W\u001a\u001d\u0012\u0013\u0012\u00110S¢\u0006\f\bT\u0012\b\bU\u0012\u0004\b\b(X\u0012\u0004\u0012\u00020J0RH\u0002JL\u0010j\u001a\u00020J2!\u0010Q\u001a\u001d\u0012\u0013\u0012\u00110S¢\u0006\f\bT\u0012\b\bU\u0012\u0004\b\b(V\u0012\u0004\u0012\u00020J0R2!\u0010W\u001a\u001d\u0012\u0013\u0012\u00110S¢\u0006\f\bT\u0012\b\bU\u0012\u0004\b\b(X\u0012\u0004\u0012\u00020J0RR\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u001e\u001a\u00020\u001f8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010&\u001a\u00020'8\u0000@\u0000X\u0081.¢\u0006\u0014\n\u0000\u0012\u0004\b(\u0010!\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0.X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020201008\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b3\u0010!\u001a\u0004\b4\u00105R$\u00106\u001a\u00020'8\u0000@\u0000X\u0081.¢\u0006\u0014\n\u0000\u0012\u0004\b7\u0010!\u001a\u0004\b8\u0010*\"\u0004\b9\u0010,R\u000e\u0010:\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010<\u001a\b\u0012\u0004\u0012\u00020201X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010=\u001a\u00020\u001f8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b>\u0010!\u001a\u0004\b?\u0010#\"\u0004\b@\u0010%R$\u0010A\u001a\u00020B8\u0000@\u0000X\u0081.¢\u0006\u0014\n\u0000\u0012\u0004\bC\u0010!\u001a\u0004\bD\u0010E\"\u0004\bF\u0010G¨\u0006l"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadCoordinator;", "Lcom/amazon/photos/uploader/cds/multipart/PartProgressListener;", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "partInfoDao", "Lcom/amazon/photos/uploader/cds/multipart/PartInfoDao;", "multipartUploadRequestMetadataDao", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadRequestMetadataDao;", "multipartUploadInitiator", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadInitiator;", "partUploader", "Lcom/amazon/photos/uploader/cds/multipart/PartUploader;", "multipartUploadCompleter", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadCompleter;", "multipartUploadVerifier", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadVerifier;", "multipartUploadNodeFetcher", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadNodeFetcher;", "progressListener", "Lcom/amazon/photos/uploader/UploadProgressListener;", "multipartTransactionRunner", "Lcom/amazon/photos/uploader/cds/multipart/MultipartTransactionRunner;", "(Lcom/amazon/photos/uploader/log/UploadLogger;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/uploader/cds/multipart/PartInfoDao;Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadRequestMetadataDao;Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadInitiator;Lcom/amazon/photos/uploader/cds/multipart/PartUploader;Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadCompleter;Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadVerifier;Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadNodeFetcher;Lcom/amazon/photos/uploader/UploadProgressListener;Lcom/amazon/photos/uploader/cds/multipart/MultipartTransactionRunner;)V", "completedBytes", "", "disposables", "", "Lio/reactivex/rxjava3/disposables/Disposable;", "isDestroyed", "", "isDestroyed$annotations", "()V", "isDestroyed$AndroidPhotosUploader_release", "()Z", "setDestroyed$AndroidPhotosUploader_release", "(Z)V", AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, "", "nodeId$annotations", "getNodeId$AndroidPhotosUploader_release", "()Ljava/lang/String;", "setNodeId$AndroidPhotosUploader_release", "(Ljava/lang/String;)V", "segmentProgressHashMap", "Ljava/util/concurrent/ConcurrentHashMap;", "segmentStack", "Ljava/util/Stack;", "", "Lcom/amazon/photos/uploader/cds/multipart/PartInfo;", "segmentStack$annotations", "getSegmentStack$AndroidPhotosUploader_release", "()Ljava/util/Stack;", "serviceId", "serviceId$annotations", "getServiceId$AndroidPhotosUploader_release", "setServiceId$AndroidPhotosUploader_release", "startTime", "totalBytesForProgressReport", "totalRemainingParts", "uploadCancelled", "uploadCancelled$annotations", "getUploadCancelled$AndroidPhotosUploader_release", "setUploadCancelled$AndroidPhotosUploader_release", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "uploadRequest$annotations", "getUploadRequest$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/UploadRequest;", "setUploadRequest$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/UploadRequest;)V", "canExecute", "cleanUpUploadRequestData", "", "includeParts", "cleanUponCancel", "cleanUponComplete", "cleanUponPaused", "clearDisposables", "completeMultipartUpload", "onSuccess", "Lkotlin/Function1;", "Lcom/amazon/photos/uploader/UploadResponse;", "Lkotlin/ParameterName;", "name", "response", "onError", "error", "destroy", "fetchNodeInfo", "getSumOfBytes", "init", "ur", "onProgressUpdate", "progress", "maxProgress", "partId", "retrieveAndCommitMultipart", "startNextSegment", "file", "Ljava/io/File;", "contentUri", "Landroid/net/Uri;", "totalParts", "segmentProgressListener", "uploadAllParts", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MultipartUploadCoordinator implements PartProgressListener {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    public static final double PROGRESS_ADJUSTMENT_FACTOR = 0.9d;
    public static final int SEGMENT_SIZE = 5;
    @NotNull
    public static final String TAG = "MultipartUploadCoor";
    private long completedBytes;
    private final List<Disposable> disposables;
    private boolean isDestroyed;
    private final UploadLogger logger;
    private final Metrics metrics;
    private final MultipartTransactionRunner multipartTransactionRunner;
    private final MultipartUploadCompleter multipartUploadCompleter;
    private final MultipartUploadInitiator multipartUploadInitiator;
    private final MultipartUploadNodeFetcher multipartUploadNodeFetcher;
    private final MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao;
    private final MultipartUploadVerifier multipartUploadVerifier;
    @NotNull
    public String nodeId;
    private final PartInfoDao partInfoDao;
    private final PartUploader partUploader;
    private final UploadProgressListener progressListener;
    private final ConcurrentHashMap<Long, Long> segmentProgressHashMap;
    @NotNull
    private final Stack<List<PartInfo>> segmentStack;
    @NotNull
    public String serviceId;
    private long startTime;
    private long totalBytesForProgressReport;
    private List<PartInfo> totalRemainingParts;
    private boolean uploadCancelled;
    @NotNull
    public UploadRequest uploadRequest;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MultipartUploadCoordinator.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadCoordinator$Companion;", "", "()V", "PROGRESS_ADJUSTMENT_FACTOR", "", "SEGMENT_SIZE", "", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public MultipartUploadCoordinator(@NotNull UploadLogger logger, @NotNull Metrics metrics, @NotNull PartInfoDao partInfoDao, @NotNull MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao, @NotNull MultipartUploadInitiator multipartUploadInitiator, @NotNull PartUploader partUploader, @NotNull MultipartUploadCompleter multipartUploadCompleter, @NotNull MultipartUploadVerifier multipartUploadVerifier, @NotNull MultipartUploadNodeFetcher multipartUploadNodeFetcher, @NotNull UploadProgressListener progressListener, @NotNull MultipartTransactionRunner multipartTransactionRunner) {
        List<PartInfo> emptyList;
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(partInfoDao, "partInfoDao");
        Intrinsics.checkParameterIsNotNull(multipartUploadRequestMetadataDao, "multipartUploadRequestMetadataDao");
        Intrinsics.checkParameterIsNotNull(multipartUploadInitiator, "multipartUploadInitiator");
        Intrinsics.checkParameterIsNotNull(partUploader, "partUploader");
        Intrinsics.checkParameterIsNotNull(multipartUploadCompleter, "multipartUploadCompleter");
        Intrinsics.checkParameterIsNotNull(multipartUploadVerifier, "multipartUploadVerifier");
        Intrinsics.checkParameterIsNotNull(multipartUploadNodeFetcher, "multipartUploadNodeFetcher");
        Intrinsics.checkParameterIsNotNull(progressListener, "progressListener");
        Intrinsics.checkParameterIsNotNull(multipartTransactionRunner, "multipartTransactionRunner");
        this.logger = logger;
        this.metrics = metrics;
        this.partInfoDao = partInfoDao;
        this.multipartUploadRequestMetadataDao = multipartUploadRequestMetadataDao;
        this.multipartUploadInitiator = multipartUploadInitiator;
        this.partUploader = partUploader;
        this.multipartUploadCompleter = multipartUploadCompleter;
        this.multipartUploadVerifier = multipartUploadVerifier;
        this.multipartUploadNodeFetcher = multipartUploadNodeFetcher;
        this.progressListener = progressListener;
        this.multipartTransactionRunner = multipartTransactionRunner;
        this.disposables = new ArrayList();
        this.segmentStack = new Stack<>();
        this.segmentProgressHashMap = new ConcurrentHashMap<>();
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        this.totalRemainingParts = emptyList;
    }

    private final synchronized boolean canExecute() {
        boolean z;
        if (!this.isDestroyed) {
            if (!this.uploadCancelled) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    private final void cleanUpUploadRequestData(boolean z) {
        if (z) {
            this.multipartTransactionRunner.runInTransaction$AndroidPhotosUploader_release(new Runnable() { // from class: com.amazon.photos.uploader.cds.multipart.MultipartUploadCoordinator$cleanUpUploadRequestData$1
                @Override // java.lang.Runnable
                public final void run() {
                    MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao;
                    PartInfoDao partInfoDao;
                    multipartUploadRequestMetadataDao = MultipartUploadCoordinator.this.multipartUploadRequestMetadataDao;
                    multipartUploadRequestMetadataDao.deleteRequest(MultipartUploadCoordinator.this.getUploadRequest$AndroidPhotosUploader_release().getId());
                    partInfoDao = MultipartUploadCoordinator.this.partInfoDao;
                    partInfoDao.deletePartsByRequestId(MultipartUploadCoordinator.this.getUploadRequest$AndroidPhotosUploader_release().getId());
                }
            });
        } else {
            MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao = this.multipartUploadRequestMetadataDao;
            UploadRequest uploadRequest = this.uploadRequest;
            if (uploadRequest == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
            }
            multipartUploadRequestMetadataDao.deleteRequest(uploadRequest.getId());
        }
        clearDisposables();
    }

    private final void clearDisposables() {
        for (Disposable disposable : this.disposables) {
            RxUtilsKt.disposeQuietly(disposable);
        }
        this.disposables.clear();
    }

    private final long getSumOfBytes() {
        Collection<Long> values = this.segmentProgressHashMap.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "segmentProgressHashMap.values");
        long j = 0;
        for (Long it2 : values) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            j += it2.longValue();
        }
        return j;
    }

    @VisibleForTesting
    public static /* synthetic */ void isDestroyed$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void nodeId$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void segmentStack$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void serviceId$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startNextSegment(File file, Uri uri, long j, PartProgressListener partProgressListener, Function1<? super UploadResponse, Unit> function1, Function1<? super UploadResponse, Unit> function12) {
        List<PartInfo> currentSegment = this.segmentStack.pop();
        boolean z = this.segmentStack.size() == 0;
        this.completedBytes += getSumOfBytes();
        this.segmentProgressHashMap.clear();
        Intrinsics.checkExpressionValueIsNotNull(currentSegment, "currentSegment");
        UploadRequest uploadRequest = this.uploadRequest;
        if (uploadRequest == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
        }
        this.partUploader.startMultiPartUpload$AndroidPhotosUploader_release(new PartUploaderParcel(currentSegment, uploadRequest.getId(), z, j, this.startTime, this.disposables), file, uri, partProgressListener, new MultipartUploadCoordinator$startNextSegment$1(this, function1, file, uri, j, partProgressListener, function12), new MultipartUploadCoordinator$startNextSegment$2(function12));
    }

    @VisibleForTesting
    public static /* synthetic */ void uploadCancelled$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void uploadRequest$annotations() {
    }

    public final synchronized void cleanUponCancel() {
        if (!canExecute()) {
            return;
        }
        cleanUpUploadRequestData(true);
        this.uploadCancelled = true;
    }

    public final void cleanUponComplete() {
        if (!canExecute()) {
            return;
        }
        cleanUpUploadRequestData(true);
    }

    public final synchronized void cleanUponPaused() {
        if (!canExecute()) {
            return;
        }
        clearDisposables();
        this.uploadCancelled = true;
    }

    public final void completeMultipartUpload(@NotNull Function1<? super UploadResponse, Unit> onSuccess, @NotNull Function1<? super UploadResponse, Unit> onError) {
        Intrinsics.checkParameterIsNotNull(onSuccess, "onSuccess");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        if (!canExecute()) {
            return;
        }
        UploadLogger uploadLogger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Time taken for completing all parts : ");
        outline107.append(System.currentTimeMillis() - this.startTime);
        uploadLogger.i$AndroidPhotosUploader_release(TAG, outline107.toString());
        this.logger.i$AndroidPhotosUploader_release(TAG, "Making Complete Multipart call");
        this.startTime = System.currentTimeMillis();
        MultipartUploadCompleter multipartUploadCompleter = this.multipartUploadCompleter;
        String str = this.nodeId;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID);
        }
        String str2 = this.serviceId;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("serviceId");
        }
        UploadRequest uploadRequest = this.uploadRequest;
        if (uploadRequest == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
        }
        Disposable checkAndCompleteMultiPartUpload = multipartUploadCompleter.checkAndCompleteMultiPartUpload(str, str2, uploadRequest, new MultipartUploadCoordinator$completeMultipartUpload$1(onSuccess), new MultipartUploadCoordinator$completeMultipartUpload$2(onError));
        if (checkAndCompleteMultiPartUpload == null) {
            return;
        }
        this.disposables.add(checkAndCompleteMultiPartUpload);
    }

    public final synchronized void destroy() {
        cleanUpUploadRequestData(true);
        this.isDestroyed = true;
    }

    public final void fetchNodeInfo(@NotNull Function1<? super UploadResponse, Unit> onSuccess, @NotNull Function1<? super UploadResponse, Unit> onError) {
        Intrinsics.checkParameterIsNotNull(onSuccess, "onSuccess");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        if (!canExecute()) {
            return;
        }
        UploadLogger uploadLogger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Time taken for retrieving multipart call : ");
        outline107.append(System.currentTimeMillis() - this.startTime);
        uploadLogger.i$AndroidPhotosUploader_release(TAG, outline107.toString());
        this.startTime = System.currentTimeMillis();
        this.logger.i$AndroidPhotosUploader_release(TAG, "Fetch node info to pass on to caller");
        MultipartUploadNodeFetcher multipartUploadNodeFetcher = this.multipartUploadNodeFetcher;
        String str = this.nodeId;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID);
        }
        multipartUploadNodeFetcher.fetchNodeMetadata(str, new MultipartUploadCoordinator$fetchNodeInfo$1(this, onSuccess), new MultipartUploadCoordinator$fetchNodeInfo$2(onError));
    }

    @NotNull
    public final String getNodeId$AndroidPhotosUploader_release() {
        String str = this.nodeId;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID);
        }
        return str;
    }

    @NotNull
    public final Stack<List<PartInfo>> getSegmentStack$AndroidPhotosUploader_release() {
        return this.segmentStack;
    }

    @NotNull
    public final String getServiceId$AndroidPhotosUploader_release() {
        String str = this.serviceId;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("serviceId");
        }
        return str;
    }

    public final boolean getUploadCancelled$AndroidPhotosUploader_release() {
        return this.uploadCancelled;
    }

    @NotNull
    public final UploadRequest getUploadRequest$AndroidPhotosUploader_release() {
        UploadRequest uploadRequest = this.uploadRequest;
        if (uploadRequest == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
        }
        return uploadRequest;
    }

    public final void init(@NotNull UploadRequest ur, @NotNull Function1<? super String, Unit> onSuccess, @NotNull Function1<? super UploadResponse, Unit> onError) {
        Intrinsics.checkParameterIsNotNull(ur, "ur");
        Intrinsics.checkParameterIsNotNull(onSuccess, "onSuccess");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        if (!canExecute()) {
            return;
        }
        this.uploadRequest = ur;
        this.startTime = System.currentTimeMillis();
        Metrics metrics = this.metrics;
        MultipartUploadCoordinator$init$1 multipartUploadCoordinator$init$1 = MultipartUploadCoordinator$init$1.INSTANCE;
        long j = this.startTime;
        UploadRequest uploadRequest = this.uploadRequest;
        if (uploadRequest == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
        }
        metrics.recordSimpleDuration(TAG, multipartUploadCoordinator$init$1, j - uploadRequest.getCreationTimeMillis());
        UploadLogger uploadLogger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Initiating Multipart call for request = ");
        UploadRequest uploadRequest2 = this.uploadRequest;
        if (uploadRequest2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
        }
        outline107.append(uploadRequest2.getId());
        outline107.append(Chars.SPACE);
        outline107.append("and path = ");
        UploadLogger uploadLogger2 = this.logger;
        UploadRequest uploadRequest3 = this.uploadRequest;
        if (uploadRequest3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
        }
        outline107.append(uploadLogger2.obfuscate$AndroidPhotosUploader_release(uploadRequest3.getFilePath()));
        uploadLogger.i$AndroidPhotosUploader_release(TAG, outline107.toString());
        MultipartUploadInitiator multipartUploadInitiator = this.multipartUploadInitiator;
        UploadRequest uploadRequest4 = this.uploadRequest;
        if (uploadRequest4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
        }
        Disposable initiateMultiPartUpload$AndroidPhotosUploader_release = multipartUploadInitiator.initiateMultiPartUpload$AndroidPhotosUploader_release(uploadRequest4, new MultipartUploadCoordinator$init$2(this, onSuccess), new MultipartUploadCoordinator$init$3(onError));
        if (initiateMultiPartUpload$AndroidPhotosUploader_release == null) {
            return;
        }
        this.disposables.add(initiateMultiPartUpload$AndroidPhotosUploader_release);
    }

    public final boolean isDestroyed$AndroidPhotosUploader_release() {
        return this.isDestroyed;
    }

    @Override // com.amazon.photos.uploader.cds.multipart.PartProgressListener
    public void onProgressUpdate(long j, long j2, long j3) {
        if (!canExecute()) {
            return;
        }
        this.segmentProgressHashMap.put(Long.valueOf(j3), Long.valueOf(j));
        this.progressListener.onProgressUpdate((long) ((this.completedBytes + getSumOfBytes()) * 0.9d), this.totalBytesForProgressReport);
    }

    public final void retrieveAndCommitMultipart(@NotNull Function1<? super UploadResponse, Unit> onSuccess, @NotNull Function1<? super UploadResponse, Unit> onError) {
        Intrinsics.checkParameterIsNotNull(onSuccess, "onSuccess");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        if (!canExecute()) {
            return;
        }
        UploadLogger uploadLogger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Time taken for completing multipart call : ");
        outline107.append(System.currentTimeMillis() - this.startTime);
        uploadLogger.i$AndroidPhotosUploader_release(TAG, outline107.toString());
        this.logger.i$AndroidPhotosUploader_release(TAG, "Making Retrieve Multipart call");
        this.startTime = System.currentTimeMillis();
        MultipartUploadVerifier multipartUploadVerifier = this.multipartUploadVerifier;
        UploadRequest uploadRequest = this.uploadRequest;
        if (uploadRequest == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
        }
        String str = this.serviceId;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("serviceId");
        }
        String str2 = this.nodeId;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID);
        }
        multipartUploadVerifier.initiateRetrieveMultipart$AndroidPhotosUploader_release(uploadRequest, str, str2, new MultipartUploadCoordinator$retrieveAndCommitMultipart$1(this, onSuccess), new MultipartUploadCoordinator$retrieveAndCommitMultipart$2(onError));
    }

    public final void setDestroyed$AndroidPhotosUploader_release(boolean z) {
        this.isDestroyed = z;
    }

    public final void setNodeId$AndroidPhotosUploader_release(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.nodeId = str;
    }

    public final void setServiceId$AndroidPhotosUploader_release(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.serviceId = str;
    }

    public final void setUploadCancelled$AndroidPhotosUploader_release(boolean z) {
        this.uploadCancelled = z;
    }

    public final void setUploadRequest$AndroidPhotosUploader_release(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "<set-?>");
        this.uploadRequest = uploadRequest;
    }

    public final void uploadAllParts(@NotNull Function1<? super UploadResponse, Unit> onSuccess, @NotNull Function1<? super UploadResponse, Unit> onError) {
        Intrinsics.checkParameterIsNotNull(onSuccess, "onSuccess");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        if (!canExecute()) {
            return;
        }
        UploadLogger uploadLogger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Starting Parts upload for multipart request = ");
        UploadRequest uploadRequest = this.uploadRequest;
        if (uploadRequest == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
        }
        outline107.append(uploadRequest.getId());
        outline107.append(Chars.SPACE);
        outline107.append("and path = ");
        UploadLogger uploadLogger2 = this.logger;
        UploadRequest uploadRequest2 = this.uploadRequest;
        if (uploadRequest2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
        }
        outline107.append(uploadLogger2.obfuscate$AndroidPhotosUploader_release(uploadRequest2.getFilePath()));
        uploadLogger.i$AndroidPhotosUploader_release(TAG, outline107.toString());
        PartInfoDao partInfoDao = this.partInfoDao;
        UploadRequest uploadRequest3 = this.uploadRequest;
        if (uploadRequest3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
        }
        long allCountByRequestId = partInfoDao.getAllCountByRequestId(uploadRequest3.getId());
        UploadLogger uploadLogger3 = this.logger;
        StringBuilder outline111 = GeneratedOutlineSupport1.outline111("Total parts : ", allCountByRequestId, " in request = ");
        UploadRequest uploadRequest4 = this.uploadRequest;
        if (uploadRequest4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
        }
        outline111.append(uploadRequest4.getId());
        outline111.append(Chars.SPACE);
        outline111.append("and path = ");
        UploadLogger uploadLogger4 = this.logger;
        UploadRequest uploadRequest5 = this.uploadRequest;
        if (uploadRequest5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
        }
        outline111.append(uploadLogger4.obfuscate$AndroidPhotosUploader_release(uploadRequest5.getFilePath()));
        uploadLogger3.i$AndroidPhotosUploader_release(TAG, outline111.toString());
        if (allCountByRequestId == 0) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Parts missing for request ");
            UploadRequest uploadRequest6 = this.uploadRequest;
            if (uploadRequest6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
            }
            outline1072.append(uploadRequest6.getId());
            onError.mo12165invoke(new UploadResponse.Failure(UploadResponse.UNKNOWN_UPLOAD_ERROR, new LocalValidationException(outline1072.toString()), UploadErrorCategory.LOCAL_VALIDATION, null, false, 24, null));
            this.metrics.recordSimpleEvent(TAG, MultipartUploadCoordinator$uploadAllParts$1.INSTANCE, new MetricRecordingType[0]);
        }
        PartInfoDao partInfoDao2 = this.partInfoDao;
        UploadRequest uploadRequest7 = this.uploadRequest;
        if (uploadRequest7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
        }
        this.totalRemainingParts = partInfoDao2.getAllPendingRequestId(uploadRequest7.getId());
        UploadLogger uploadLogger5 = this.logger;
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Total parts ");
        outline1073.append(this.totalRemainingParts.size());
        outline1073.append(" remaining in request =");
        outline1073.append(Chars.SPACE);
        UploadRequest uploadRequest8 = this.uploadRequest;
        if (uploadRequest8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
        }
        outline1073.append(uploadRequest8.getId());
        outline1073.append(" and path = ");
        UploadLogger uploadLogger6 = this.logger;
        UploadRequest uploadRequest9 = this.uploadRequest;
        if (uploadRequest9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
        }
        outline1073.append(uploadLogger6.obfuscate$AndroidPhotosUploader_release(uploadRequest9.getFilePath()));
        uploadLogger5.d$AndroidPhotosUploader_release(TAG, outline1073.toString());
        UploadLogger uploadLogger7 = this.logger;
        StringBuilder outline108 = GeneratedOutlineSupport1.outline108("Validating all nodes for the upload request =", Chars.SPACE);
        UploadRequest uploadRequest10 = this.uploadRequest;
        if (uploadRequest10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
        }
        outline108.append(uploadRequest10.getId());
        outline108.append(" and path = ");
        UploadLogger uploadLogger8 = this.logger;
        UploadRequest uploadRequest11 = this.uploadRequest;
        if (uploadRequest11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
        }
        outline108.append(uploadLogger8.obfuscate$AndroidPhotosUploader_release(uploadRequest11.getFilePath()));
        uploadLogger7.d$AndroidPhotosUploader_release(TAG, outline108.toString());
        if (this.totalRemainingParts.isEmpty()) {
            PartInfoDao partInfoDao3 = this.partInfoDao;
            UploadRequest uploadRequest12 = this.uploadRequest;
            if (uploadRequest12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
            }
            if (partInfoDao3.getAllCountByStateRequestId(uploadRequest12.getId(), PartUploadState.SUCCEEDED) == allCountByRequestId) {
                onSuccess.mo12165invoke(new UploadResponse.Success());
                return;
            }
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("No enqueued parts found for request ");
            UploadRequest uploadRequest13 = this.uploadRequest;
            if (uploadRequest13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
            }
            outline1074.append(uploadRequest13.getId());
            outline1074.append(Chars.SPACE);
            outline1074.append("and path = ");
            UploadLogger uploadLogger9 = this.logger;
            UploadRequest uploadRequest14 = this.uploadRequest;
            if (uploadRequest14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
            }
            outline1074.append(uploadLogger9.obfuscate$AndroidPhotosUploader_release(uploadRequest14.getFilePath()));
            onError.mo12165invoke(new UploadResponse.Failure(UploadResponse.UNKNOWN_UPLOAD_ERROR, new LocalValidationException(outline1074.toString()), UploadErrorCategory.LOCAL_VALIDATION, null, false, 24, null));
            this.metrics.recordSimpleEvent(TAG, MultipartUploadCoordinator$uploadAllParts$2.INSTANCE, new MetricRecordingType[0]);
        } else if (!this.partUploader.ensureValidNodeIds$AndroidPhotosUploader_release(this.totalRemainingParts)) {
            PartUploader partUploader = this.partUploader;
            UploadRequest uploadRequest15 = this.uploadRequest;
            if (uploadRequest15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
            }
            partUploader.clearParts(uploadRequest15.getId());
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("Mismatch in node ids for upload request ");
            UploadRequest uploadRequest16 = this.uploadRequest;
            if (uploadRequest16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
            }
            outline1075.append(uploadRequest16.getId());
            onError.mo12165invoke(new UploadResponse.Failure(UploadResponse.UNKNOWN_UPLOAD_ERROR, new LocalValidationException(outline1075.toString()), UploadErrorCategory.LOCAL_VALIDATION, null, false, 24, null));
        } else {
            UploadLogger uploadLogger10 = this.logger;
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("Start uploading all parts. Time taken for Initiate : ");
            outline1076.append(System.currentTimeMillis() - this.startTime);
            outline1076.append(" for request ");
            UploadRequest uploadRequest17 = this.uploadRequest;
            if (uploadRequest17 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
            }
            outline1076.append(uploadRequest17.getId());
            outline1076.append(Chars.SPACE);
            outline1076.append("and path = ");
            UploadLogger uploadLogger11 = this.logger;
            UploadRequest uploadRequest18 = this.uploadRequest;
            if (uploadRequest18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
            }
            outline1076.append(uploadLogger11.obfuscate$AndroidPhotosUploader_release(uploadRequest18.getFilePath()));
            uploadLogger10.i$AndroidPhotosUploader_release(TAG, outline1076.toString());
            this.startTime = System.currentTimeMillis();
            List<List<PartInfo>> segmentedList = Lists.partition(this.totalRemainingParts, 5);
            Intrinsics.checkExpressionValueIsNotNull(segmentedList, "segmentedList");
            for (List<PartInfo> list : segmentedList) {
                this.segmentStack.push(list);
            }
            CollectionsKt___CollectionsJvmKt.reverse(this.segmentStack);
            PartInfoDao partInfoDao4 = this.partInfoDao;
            UploadRequest uploadRequest19 = this.uploadRequest;
            if (uploadRequest19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
            }
            this.completedBytes = partInfoDao4.getCompletedPartsBytesCount(uploadRequest19.getId());
            PartUploader partUploader2 = this.partUploader;
            UploadRequest uploadRequest20 = this.uploadRequest;
            if (uploadRequest20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
            }
            File file$AndroidPhotosUploader_release = partUploader2.getFile$AndroidPhotosUploader_release(uploadRequest20);
            this.totalBytesForProgressReport = file$AndroidPhotosUploader_release.length();
            UploadRequest uploadRequest21 = this.uploadRequest;
            if (uploadRequest21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
            }
            startNextSegment(file$AndroidPhotosUploader_release, uploadRequest21.getContentUri(), allCountByRequestId, this, onSuccess, onError);
            UploadLogger uploadLogger12 = this.logger;
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("Started first segment for request ");
            UploadRequest uploadRequest22 = this.uploadRequest;
            if (uploadRequest22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uploadRequest");
            }
            outline1077.append(uploadRequest22.getId());
            uploadLogger12.i$AndroidPhotosUploader_release(TAG, outline1077.toString());
        }
    }
}
