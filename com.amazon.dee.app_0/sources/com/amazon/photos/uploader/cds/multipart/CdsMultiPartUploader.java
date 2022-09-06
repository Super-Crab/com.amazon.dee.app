package com.amazon.photos.uploader.cds.multipart;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.cds.CDSError;
import com.amazon.clouddrive.cdasdk.cds.CDSException;
import com.amazon.clouddrive.cdasdk.cdus.CDUSException;
import com.amazon.photos.uploader.UploadCompleter;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.UploadProgressListener;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadResponse;
import com.amazon.photos.uploader.Uploader;
import com.amazon.photos.uploader.UploaderState;
import com.amazon.photos.uploader.cds.CdsMetrics;
import com.amazon.photos.uploader.cds.error.CdsUploadErrorResolver;
import com.amazon.photos.uploader.cds.error.UploadException;
import com.amazon.photos.uploader.internal.DestroyableDatabaseWrapper;
import com.amazon.photos.uploader.log.UploadLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: CdsMultiPartUploader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ê\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 b2\u00020\u0001:\u0001bBm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b¢\u0006\u0002\u0010\u001cJ\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0016J\r\u0010-\u001a\u00020*H\u0000¢\u0006\u0002\b.J\u0015\u0010/\u001a\u00020*2\u0006\u00100\u001a\u00020\u001fH\u0001¢\u0006\u0002\b1J\u0015\u00102\u001a\u00020*2\u0006\u00100\u001a\u00020\u001fH\u0001¢\u0006\u0002\b3J\b\u00104\u001a\u00020*H\u0016J5\u00105\u001a\u00020*2\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u0002092\u0006\u0010+\u001a\u00020,2\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0001¢\u0006\u0002\b>J\u0015\u0010?\u001a\u00020 2\u0006\u0010@\u001a\u00020AH\u0001¢\u0006\u0002\bBJ!\u0010C\u001a\u000e\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020E0D2\u0006\u0010F\u001a\u00020GH\u0001¢\u0006\u0002\bHJ5\u0010I\u001a\u00020*2\u0006\u0010J\u001a\u00020G2\u0006\u0010K\u001a\u0002072\u0006\u00108\u001a\u0002092\u0006\u0010+\u001a\u00020,2\u0006\u0010<\u001a\u00020=H\u0001¢\u0006\u0002\bLJE\u0010M\u001a\u00020*2\u0006\u0010J\u001a\u00020N2\u0006\u0010K\u001a\u0002072\u0006\u0010O\u001a\u00020=2\u0006\u0010P\u001a\u00020E2\u0006\u00108\u001a\u0002092\u0006\u0010+\u001a\u00020,2\u0006\u0010<\u001a\u00020=H\u0001¢\u0006\u0002\bQJ\r\u0010R\u001a\u00020=H\u0001¢\u0006\u0002\bSJ(\u0010T\u001a\u00020*2\u0006\u0010U\u001a\u00020=2\u0006\u0010V\u001a\u00020=2\u0006\u0010:\u001a\u00020;2\u0006\u0010W\u001a\u00020EH\u0002J \u0010X\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u00108\u001a\u0002092\u0006\u0010@\u001a\u00020AH\u0016J-\u0010Y\u001a\u00020*2\u0006\u0010:\u001a\u00020Z2\u0006\u00108\u001a\u0002092\u0006\u0010+\u001a\u00020,2\u0006\u0010<\u001a\u00020=H\u0001¢\u0006\u0002\b[JE\u0010\\\u001a\u00020*2\u0006\u00108\u001a\u0002092\u0006\u0010+\u001a\u00020,2\u0006\u0010K\u001a\u0002072\u0006\u0010O\u001a\u00020=2\u0006\u0010J\u001a\u00020;2\u0006\u0010P\u001a\u00020E2\u0006\u0010<\u001a\u00020=H\u0001¢\u0006\u0002\b]J-\u0010\\\u001a\u00020*2\u0006\u0010^\u001a\u0002072\u0006\u00108\u001a\u0002092\u0006\u0010+\u001a\u00020,2\u0006\u0010<\u001a\u00020=H\u0001¢\u0006\u0002\b]J\u0010\u0010_\u001a\u00020*2\u0006\u0010`\u001a\u00020aH\u0016R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R0\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 0\u001e8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006c"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/CdsMultiPartUploader;", "Lcom/amazon/photos/uploader/Uploader;", "uploadFrameworkContext", "Lcom/amazon/photos/uploader/UploadFrameworkContext;", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "partInfoDao", "Lcom/amazon/photos/uploader/cds/multipart/PartInfoDao;", "multipartUploadRequestMetadataDao", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadRequestMetadataDao;", "multipartDbWrapper", "Lcom/amazon/photos/uploader/internal/DestroyableDatabaseWrapper;", "multipartUploadInitiator", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadInitiator;", "multipartUploader", "Lcom/amazon/photos/uploader/cds/multipart/PartUploader;", "errorResolver", "Lcom/amazon/photos/uploader/cds/error/CdsUploadErrorResolver;", "multipartUploadCompleter", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadCompleter;", "multipartUploadVerifier", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadVerifier;", "multipartUploadNodeFetcher", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadNodeFetcher;", "multipartTransactionRunner", "Lcom/amazon/photos/uploader/cds/multipart/MultipartTransactionRunner;", "(Lcom/amazon/photos/uploader/UploadFrameworkContext;Lcom/amazon/photos/uploader/log/UploadLogger;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/uploader/cds/multipart/PartInfoDao;Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadRequestMetadataDao;Lcom/amazon/photos/uploader/internal/DestroyableDatabaseWrapper;Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadInitiator;Lcom/amazon/photos/uploader/cds/multipart/PartUploader;Lcom/amazon/photos/uploader/cds/error/CdsUploadErrorResolver;Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadCompleter;Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadVerifier;Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadNodeFetcher;Lcom/amazon/photos/uploader/cds/multipart/MultipartTransactionRunner;)V", "multipartUploadCoordinators", "Ljava/util/concurrent/ConcurrentMap;", "", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadCoordinator;", "multipartUploadCoordinators$annotations", "()V", "getMultipartUploadCoordinators$AndroidPhotosUploader_release", "()Ljava/util/concurrent/ConcurrentMap;", "setMultipartUploadCoordinators$AndroidPhotosUploader_release", "(Ljava/util/concurrent/ConcurrentMap;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "cancelUpload", "", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "cleanup", "cleanup$AndroidPhotosUploader_release", "cleanupCoordinatorOnComplete", "requestId", "cleanupCoordinatorOnComplete$AndroidPhotosUploader_release", "cleanupCoordinatorOnError", "cleanupCoordinatorOnError$AndroidPhotosUploader_release", "clearCompleted", "completeAfterResolution", "resolvedResponse", "Lcom/amazon/photos/uploader/UploadResponse;", "completer", "Lcom/amazon/photos/uploader/UploadCompleter;", "throwable", "", "errorMessage", "", "completeAfterResolution$AndroidPhotosUploader_release", "createCoordinatorForRequest", "progressListener", "Lcom/amazon/photos/uploader/UploadProgressListener;", "createCoordinatorForRequest$AndroidPhotosUploader_release", "extractCDSError", "Lkotlin/Pair;", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "ex", "Lcom/amazon/clouddrive/cdasdk/cds/CDSException;", "extractCDSError$AndroidPhotosUploader_release", "extractCDSErrorAndComplete", "originalThrowable", "originalResponse", "extractCDSErrorAndComplete$AndroidPhotosUploader_release", "extractLocalValidationAndComplete", "Lcom/amazon/photos/uploader/cds/multipart/LocalValidationException;", "originalErrorCode", "originalErrorCategory", "extractLocalValidationAndComplete$AndroidPhotosUploader_release", "getUploaderState", "getUploaderState$AndroidPhotosUploader_release", "recordErrorMetric", "metricEventName", "errorCode", "errorCategory", "startUpload", "tryResolveCDUSErrorAndComplete", "Lcom/amazon/clouddrive/cdasdk/cdus/CDUSException;", "tryResolveCDUSErrorAndComplete$AndroidPhotosUploader_release", "tryResolveErrorAndComplete", "tryResolveErrorAndComplete$AndroidPhotosUploader_release", "response", "updateUploaderState", "uploaderState", "Lcom/amazon/photos/uploader/UploaderState;", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CdsMultiPartUploader implements Uploader {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "CdsMultiPartUploader";
    private static final String UPLOADER_STATE_KEY = "UPLOADER_STATE_KEY";
    private static final String UPLOADER_STATE_PREFERENCES = "UPLOADER_STATE_PREFERENCES";
    private final CdsUploadErrorResolver errorResolver;
    private final UploadLogger logger;
    private final Metrics metrics;
    private final DestroyableDatabaseWrapper multipartDbWrapper;
    private final MultipartTransactionRunner multipartTransactionRunner;
    private final MultipartUploadCompleter multipartUploadCompleter;
    @NotNull
    private ConcurrentMap<Long, MultipartUploadCoordinator> multipartUploadCoordinators;
    private final MultipartUploadInitiator multipartUploadInitiator;
    private final MultipartUploadNodeFetcher multipartUploadNodeFetcher;
    private final MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao;
    private final MultipartUploadVerifier multipartUploadVerifier;
    private final PartUploader multipartUploader;
    private final PartInfoDao partInfoDao;
    private SharedPreferences sharedPreferences;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CdsMultiPartUploader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/CdsMultiPartUploader$Companion;", "", "()V", "TAG", "", CdsMultiPartUploader.UPLOADER_STATE_KEY, CdsMultiPartUploader.UPLOADER_STATE_PREFERENCES, "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CdsMultiPartUploader(@NotNull UploadFrameworkContext uploadFrameworkContext, @NotNull UploadLogger logger, @NotNull Metrics metrics, @NotNull PartInfoDao partInfoDao, @NotNull MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao, @NotNull DestroyableDatabaseWrapper multipartDbWrapper, @NotNull MultipartUploadInitiator multipartUploadInitiator, @NotNull PartUploader multipartUploader, @NotNull CdsUploadErrorResolver errorResolver, @NotNull MultipartUploadCompleter multipartUploadCompleter, @NotNull MultipartUploadVerifier multipartUploadVerifier, @NotNull MultipartUploadNodeFetcher multipartUploadNodeFetcher, @NotNull MultipartTransactionRunner multipartTransactionRunner) {
        Intrinsics.checkParameterIsNotNull(uploadFrameworkContext, "uploadFrameworkContext");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(partInfoDao, "partInfoDao");
        Intrinsics.checkParameterIsNotNull(multipartUploadRequestMetadataDao, "multipartUploadRequestMetadataDao");
        Intrinsics.checkParameterIsNotNull(multipartDbWrapper, "multipartDbWrapper");
        Intrinsics.checkParameterIsNotNull(multipartUploadInitiator, "multipartUploadInitiator");
        Intrinsics.checkParameterIsNotNull(multipartUploader, "multipartUploader");
        Intrinsics.checkParameterIsNotNull(errorResolver, "errorResolver");
        Intrinsics.checkParameterIsNotNull(multipartUploadCompleter, "multipartUploadCompleter");
        Intrinsics.checkParameterIsNotNull(multipartUploadVerifier, "multipartUploadVerifier");
        Intrinsics.checkParameterIsNotNull(multipartUploadNodeFetcher, "multipartUploadNodeFetcher");
        Intrinsics.checkParameterIsNotNull(multipartTransactionRunner, "multipartTransactionRunner");
        this.logger = logger;
        this.metrics = metrics;
        this.partInfoDao = partInfoDao;
        this.multipartUploadRequestMetadataDao = multipartUploadRequestMetadataDao;
        this.multipartDbWrapper = multipartDbWrapper;
        this.multipartUploadInitiator = multipartUploadInitiator;
        this.multipartUploader = multipartUploader;
        this.errorResolver = errorResolver;
        this.multipartUploadCompleter = multipartUploadCompleter;
        this.multipartUploadVerifier = multipartUploadVerifier;
        this.multipartUploadNodeFetcher = multipartUploadNodeFetcher;
        this.multipartTransactionRunner = multipartTransactionRunner;
        this.multipartUploadCoordinators = new ConcurrentHashMap();
        Context applicationContext = uploadFrameworkContext.getApplicationContext();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UPLOADER_STATE_PREFERENCES_");
        outline107.append(uploadFrameworkContext.getHashedDirectedId());
        outline107.append(')');
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences(outline107.toString(), 0);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "uploadFrameworkContext.a…    Context.MODE_PRIVATE)");
        this.sharedPreferences = sharedPreferences;
    }

    @VisibleForTesting
    public static /* synthetic */ void multipartUploadCoordinators$annotations() {
    }

    private final void recordErrorMetric(final String str, final String str2, final Throwable th, final UploadErrorCategory uploadErrorCategory) {
        this.metrics.recordCustomMetric(TAG, new ClientMetric().addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.multipart.CdsMultiPartUploader$recordErrorMetric$clientMetric$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return str;
            }
        }, 1).addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.multipart.CdsMultiPartUploader$recordErrorMetric$clientMetric$2
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return str + JsonReaderKt.COLON + str2;
            }
        }, 1).addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.multipart.CdsMultiPartUploader$recordErrorMetric$clientMetric$3
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return str + JsonReaderKt.COLON + th.getClass().getSimpleName();
            }
        }, 1).addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.multipart.CdsMultiPartUploader$recordErrorMetric$clientMetric$4
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return str + "_CATEGORY_" + uploadErrorCategory.name();
            }
        }, 1).withTagName(TAG), new MetricRecordingType[0]);
    }

    @Override // com.amazon.photos.uploader.Uploader
    public void cancelUpload(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        UploadLogger uploadLogger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cancelUpload ");
        outline107.append(uploadRequest.getId());
        outline107.append(Chars.SPACE);
        outline107.append(this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()));
        uploadLogger.d$AndroidPhotosUploader_release(TAG, outline107.toString());
        if (Intrinsics.areEqual(getUploaderState$AndroidPhotosUploader_release(), UploaderState.CANCELLED.name())) {
            MultipartUploadCoordinator multipartUploadCoordinator = this.multipartUploadCoordinators.get(Long.valueOf(uploadRequest.getId()));
            if (multipartUploadCoordinator != null) {
                multipartUploadCoordinator.cleanUponCancel();
            }
            this.multipartUploadCoordinators.remove(Long.valueOf(uploadRequest.getId()));
            return;
        }
        ConcurrentMap<Long, MultipartUploadCoordinator> concurrentMap = this.multipartUploadCoordinators;
        ArrayList arrayList = new ArrayList(concurrentMap.size());
        for (Map.Entry<Long, MultipartUploadCoordinator> entry : concurrentMap.entrySet()) {
            entry.getValue().cleanUponPaused();
            arrayList.add(Unit.INSTANCE);
        }
        this.multipartUploadCoordinators.remove(Long.valueOf(uploadRequest.getId()));
    }

    public final void cleanup$AndroidPhotosUploader_release() {
        this.logger.i$AndroidPhotosUploader_release(TAG, "cleanup Start");
        ConcurrentMap<Long, MultipartUploadCoordinator> concurrentMap = this.multipartUploadCoordinators;
        ArrayList arrayList = new ArrayList(concurrentMap.size());
        for (Map.Entry<Long, MultipartUploadCoordinator> entry : concurrentMap.entrySet()) {
            entry.getValue().destroy();
            arrayList.add(Unit.INSTANCE);
        }
        this.multipartUploadCoordinators.clear();
        this.multipartDbWrapper.destroy();
        this.logger.i$AndroidPhotosUploader_release(TAG, "cleanup End");
    }

    @VisibleForTesting
    public final void cleanupCoordinatorOnComplete$AndroidPhotosUploader_release(long j) {
        MultipartUploadCoordinator multipartUploadCoordinator = this.multipartUploadCoordinators.get(Long.valueOf(j));
        if (multipartUploadCoordinator != null) {
            multipartUploadCoordinator.cleanUponComplete();
        }
        this.multipartUploadCoordinators.remove(Long.valueOf(j));
    }

    @VisibleForTesting
    public final void cleanupCoordinatorOnError$AndroidPhotosUploader_release(long j) {
        this.multipartUploadCoordinators.remove(Long.valueOf(j));
    }

    @Override // com.amazon.photos.uploader.Uploader
    public void clearCompleted() {
        this.multipartTransactionRunner.runInTransaction$AndroidPhotosUploader_release(new Runnable() { // from class: com.amazon.photos.uploader.cds.multipart.CdsMultiPartUploader$clearCompleted$1
            @Override // java.lang.Runnable
            public final void run() {
                MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao;
                PartInfoDao partInfoDao;
                MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao2;
                PartInfoDao partInfoDao2;
                multipartUploadRequestMetadataDao = CdsMultiPartUploader.this.multipartUploadRequestMetadataDao;
                for (MultipartUploadRequestMetadata multipartUploadRequestMetadata : multipartUploadRequestMetadataDao.getAll()) {
                    Long totalNumberOfParts = multipartUploadRequestMetadata.getTotalNumberOfParts();
                    if (totalNumberOfParts != null) {
                        partInfoDao = CdsMultiPartUploader.this.partInfoDao;
                        if (totalNumberOfParts.longValue() == partInfoDao.getAllCountByStateRequestId(multipartUploadRequestMetadata.getUploadRequestId(), PartUploadState.SUCCEEDED)) {
                            multipartUploadRequestMetadataDao2 = CdsMultiPartUploader.this.multipartUploadRequestMetadataDao;
                            multipartUploadRequestMetadataDao2.deleteRequest(multipartUploadRequestMetadata.getUploadRequestId());
                            partInfoDao2 = CdsMultiPartUploader.this.partInfoDao;
                            partInfoDao2.deletePartsByRequestId(multipartUploadRequestMetadata.getUploadRequestId());
                        }
                    }
                }
            }
        });
    }

    @VisibleForTesting
    public final void completeAfterResolution$AndroidPhotosUploader_release(@NotNull UploadResponse resolvedResponse, @NotNull UploadCompleter completer, @NotNull UploadRequest uploadRequest, @NotNull Throwable throwable, @NotNull String errorMessage) {
        Intrinsics.checkParameterIsNotNull(resolvedResponse, "resolvedResponse");
        Intrinsics.checkParameterIsNotNull(completer, "completer");
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(throwable, "throwable");
        Intrinsics.checkParameterIsNotNull(errorMessage, "errorMessage");
        this.logger.w$AndroidPhotosUploader_release(TAG, errorMessage);
        if (resolvedResponse instanceof UploadResponse.Success) {
            UploadLogger uploadLogger = this.logger;
            uploadLogger.i$AndroidPhotosUploader_release(TAG, "Error Resolved. Returning Success " + resolvedResponse + " for " + uploadRequest.getId() + " and path = " + this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()));
            cleanupCoordinatorOnComplete$AndroidPhotosUploader_release(uploadRequest.getId());
        } else if (resolvedResponse instanceof UploadResponse.Failure) {
            UploadLogger uploadLogger2 = this.logger;
            UploadResponse.Failure failure = (UploadResponse.Failure) resolvedResponse;
            uploadLogger2.e$AndroidPhotosUploader_release(TAG, "Got Failure response from error resolver. Returning Failure = " + resolvedResponse + " for " + uploadRequest.getId() + " and path =" + Chars.SPACE + this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()), failure.getEx());
            cleanupCoordinatorOnError$AndroidPhotosUploader_release(uploadRequest.getId());
            recordErrorMetric(CdsMetrics.UPLOAD_MULTIPART_CDUS_EXCEPTION, failure.getErrorCode(), throwable, failure.getErrorCategory());
        } else if (resolvedResponse instanceof UploadResponse.NoRetryFailure) {
            UploadLogger uploadLogger3 = this.logger;
            UploadResponse.NoRetryFailure noRetryFailure = (UploadResponse.NoRetryFailure) resolvedResponse;
            uploadLogger3.e$AndroidPhotosUploader_release(TAG, "Got No Retry Failure response from error resolver. Returning No Retry Failure = " + resolvedResponse + " for " + uploadRequest.getId() + " and path =" + Chars.SPACE + this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()), noRetryFailure.getEx());
            cleanupCoordinatorOnError$AndroidPhotosUploader_release(uploadRequest.getId());
            recordErrorMetric(CdsMetrics.UPLOAD_MULTIPART_CDUS_EXCEPTION, noRetryFailure.getErrorCode(), throwable, noRetryFailure.getErrorCategory());
        }
        completer.setResponse(resolvedResponse);
    }

    @VisibleForTesting
    @NotNull
    public final MultipartUploadCoordinator createCoordinatorForRequest$AndroidPhotosUploader_release(@NotNull UploadProgressListener progressListener) {
        Intrinsics.checkParameterIsNotNull(progressListener, "progressListener");
        return new MultipartUploadCoordinator(this.logger, this.metrics, this.partInfoDao, this.multipartUploadRequestMetadataDao, this.multipartUploadInitiator, this.multipartUploader, this.multipartUploadCompleter, this.multipartUploadVerifier, this.multipartUploadNodeFetcher, progressListener, this.multipartTransactionRunner);
    }

    @VisibleForTesting
    @NotNull
    public final Pair<String, UploadErrorCategory> extractCDSError$AndroidPhotosUploader_release(@NotNull CDSException ex) {
        UploadErrorCategory uploadErrorCategory;
        String str;
        Intrinsics.checkParameterIsNotNull(ex, "ex");
        CDSError cdsError = ex.getCdsError();
        Intrinsics.checkExpressionValueIsNotNull(cdsError, "ex.cdsError");
        if (cdsError.getCode() != null) {
            CDSError cdsError2 = ex.getCdsError();
            Intrinsics.checkExpressionValueIsNotNull(cdsError2, "ex.cdsError");
            str = cdsError2.getCode();
            Intrinsics.checkExpressionValueIsNotNull(str, "ex.cdsError.code");
            uploadErrorCategory = UploadErrorCategory.OTHER_KNOWN_ERROR;
        } else {
            uploadErrorCategory = UploadErrorCategory.UNKNOWN_ERROR;
            str = UploadResponse.UNKNOWN_UPLOAD_ERROR;
        }
        return new Pair<>(str, uploadErrorCategory);
    }

    @VisibleForTesting
    public final void extractCDSErrorAndComplete$AndroidPhotosUploader_release(@NotNull CDSException originalThrowable, @NotNull UploadResponse originalResponse, @NotNull UploadCompleter completer, @NotNull UploadRequest uploadRequest, @NotNull String errorMessage) {
        Intrinsics.checkParameterIsNotNull(originalThrowable, "originalThrowable");
        Intrinsics.checkParameterIsNotNull(originalResponse, "originalResponse");
        Intrinsics.checkParameterIsNotNull(completer, "completer");
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(errorMessage, "errorMessage");
        boolean z = originalResponse instanceof UploadResponse.Failure;
        if (z || (originalResponse instanceof UploadResponse.NoRetryFailure)) {
            Pair<String, UploadErrorCategory> extractCDSError$AndroidPhotosUploader_release = extractCDSError$AndroidPhotosUploader_release(originalThrowable);
            String component1 = extractCDSError$AndroidPhotosUploader_release.component1();
            UploadErrorCategory component2 = extractCDSError$AndroidPhotosUploader_release.component2();
            this.logger.w$AndroidPhotosUploader_release(TAG, errorMessage);
            UploadLogger uploadLogger = this.logger;
            uploadLogger.e$AndroidPhotosUploader_release(TAG, "Got CDS exception error [" + component1 + ", " + component2 + "] for " + uploadRequest.getId() + " and path = " + this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()), originalThrowable);
            recordErrorMetric(CdsMetrics.UPLOAD_MULTIPART_CDS_EXCEPTION, component1, originalThrowable, component2);
            cleanupCoordinatorOnError$AndroidPhotosUploader_release(uploadRequest.getId());
            if (z) {
                UploadResponse.Failure failure = (UploadResponse.Failure) originalResponse;
                completer.setResponse(new UploadResponse.Failure(component1, originalThrowable, component2, failure.getRevisedRequest(), failure.getResetAttempts()));
                return;
            }
            completer.setResponse(new UploadResponse.NoRetryFailure(component1, originalThrowable, component2));
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    @VisibleForTesting
    public final void extractLocalValidationAndComplete$AndroidPhotosUploader_release(@NotNull LocalValidationException originalThrowable, @NotNull UploadResponse originalResponse, @NotNull String originalErrorCode, @NotNull UploadErrorCategory originalErrorCategory, @NotNull UploadCompleter completer, @NotNull UploadRequest uploadRequest, @NotNull String errorMessage) {
        Intrinsics.checkParameterIsNotNull(originalThrowable, "originalThrowable");
        Intrinsics.checkParameterIsNotNull(originalResponse, "originalResponse");
        Intrinsics.checkParameterIsNotNull(originalErrorCode, "originalErrorCode");
        Intrinsics.checkParameterIsNotNull(originalErrorCategory, "originalErrorCategory");
        Intrinsics.checkParameterIsNotNull(completer, "completer");
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(errorMessage, "errorMessage");
        boolean z = originalResponse instanceof UploadResponse.Failure;
        if (z || (originalResponse instanceof UploadResponse.NoRetryFailure)) {
            if (originalThrowable.getErrorCodeString() != null) {
                completeAfterResolution$AndroidPhotosUploader_release(this.errorResolver.resolve(uploadRequest, new UploadException(originalThrowable.getMessage(), originalThrowable.getErrorCodeString(), 200, null, 8, null)), completer, uploadRequest, originalThrowable, errorMessage);
                return;
            }
            this.logger.w$AndroidPhotosUploader_release(TAG, errorMessage);
            UploadLogger uploadLogger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Got Local Validation Exception for ");
            outline107.append(uploadRequest.getId());
            outline107.append(" and path = ");
            outline107.append(this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()));
            uploadLogger.e$AndroidPhotosUploader_release(TAG, outline107.toString(), originalThrowable);
            cleanupCoordinatorOnError$AndroidPhotosUploader_release(uploadRequest.getId());
            recordErrorMetric(CdsMetrics.UPLOAD_MULTIPART_CDUS_EXCEPTION, originalErrorCode, originalThrowable, originalErrorCategory);
            if (z) {
                UploadResponse.Failure failure = (UploadResponse.Failure) originalResponse;
                completer.setResponse(new UploadResponse.Failure(originalErrorCode, originalThrowable, originalErrorCategory, failure.getRevisedRequest(), failure.getResetAttempts()));
                return;
            }
            completer.setResponse(new UploadResponse.NoRetryFailure(originalErrorCode, originalThrowable, originalErrorCategory));
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    @NotNull
    public final ConcurrentMap<Long, MultipartUploadCoordinator> getMultipartUploadCoordinators$AndroidPhotosUploader_release() {
        return this.multipartUploadCoordinators;
    }

    @VisibleForTesting
    @NotNull
    public final String getUploaderState$AndroidPhotosUploader_release() {
        String string = this.sharedPreferences.getString(UPLOADER_STATE_KEY, UploaderState.RESUME.name());
        return string == null ? UploaderState.RESUME.name() : string;
    }

    public final void setMultipartUploadCoordinators$AndroidPhotosUploader_release(@NotNull ConcurrentMap<Long, MultipartUploadCoordinator> concurrentMap) {
        Intrinsics.checkParameterIsNotNull(concurrentMap, "<set-?>");
        this.multipartUploadCoordinators = concurrentMap;
    }

    @Override // com.amazon.photos.uploader.Uploader
    public void startUpload(@NotNull UploadRequest uploadRequest, @NotNull UploadCompleter completer, @NotNull UploadProgressListener progressListener) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(completer, "completer");
        Intrinsics.checkParameterIsNotNull(progressListener, "progressListener");
        if (this.multipartUploadCoordinators.containsKey(Long.valueOf(uploadRequest.getId()))) {
            UploadLogger uploadLogger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Upload Request ");
            outline107.append(uploadRequest.getId());
            outline107.append(" and path =");
            outline107.append(Chars.SPACE);
            outline107.append(this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()));
            outline107.append(" is currently running.Skipping this call");
            uploadLogger.w$AndroidPhotosUploader_release(TAG, outline107.toString());
            this.metrics.recordSimpleEvent(TAG, CdsMultiPartUploader$startUpload$1.INSTANCE, new MetricRecordingType[0]);
            return;
        }
        MultipartUploadCoordinator createCoordinatorForRequest$AndroidPhotosUploader_release = createCoordinatorForRequest$AndroidPhotosUploader_release(progressListener);
        this.multipartUploadCoordinators.put(Long.valueOf(uploadRequest.getId()), createCoordinatorForRequest$AndroidPhotosUploader_release);
        UploadLogger uploadLogger2 = this.logger;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Initiating multipart upload for request: ");
        outline1072.append(uploadRequest.getId());
        outline1072.append(" and ");
        outline1072.append("path = ");
        outline1072.append(this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()));
        uploadLogger2.i$AndroidPhotosUploader_release(TAG, outline1072.toString());
        createCoordinatorForRequest$AndroidPhotosUploader_release.init(uploadRequest, new CdsMultiPartUploader$startUpload$2(this, uploadRequest, createCoordinatorForRequest$AndroidPhotosUploader_release, completer), new CdsMultiPartUploader$startUpload$3(this, completer, uploadRequest));
    }

    @VisibleForTesting
    public final void tryResolveCDUSErrorAndComplete$AndroidPhotosUploader_release(@NotNull CDUSException throwable, @NotNull UploadCompleter completer, @NotNull UploadRequest uploadRequest, @NotNull String errorMessage) {
        Intrinsics.checkParameterIsNotNull(throwable, "throwable");
        Intrinsics.checkParameterIsNotNull(completer, "completer");
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(errorMessage, "errorMessage");
        completeAfterResolution$AndroidPhotosUploader_release(this.errorResolver.resolve(uploadRequest, throwable), completer, uploadRequest, throwable, errorMessage);
    }

    @VisibleForTesting
    public final void tryResolveErrorAndComplete$AndroidPhotosUploader_release(@NotNull UploadResponse response, @NotNull UploadCompleter completer, @NotNull UploadRequest uploadRequest, @NotNull String errorMessage) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        Intrinsics.checkParameterIsNotNull(completer, "completer");
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(errorMessage, "errorMessage");
        if (response instanceof UploadResponse.Success) {
            this.metrics.recordSimpleEvent(TAG, CdsMultiPartUploader$tryResolveErrorAndComplete$1.INSTANCE, new MetricRecordingType[0]);
            UploadLogger uploadLogger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received success response for error flow for request ");
            outline107.append(uploadRequest.getId());
            outline107.append("and path = ");
            outline107.append(this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()));
            uploadLogger.w$AndroidPhotosUploader_release(TAG, outline107.toString());
            this.logger.w$AndroidPhotosUploader_release(TAG, errorMessage);
            cleanupCoordinatorOnComplete$AndroidPhotosUploader_release(uploadRequest.getId());
            completer.setResponse(response);
        } else if (response instanceof UploadResponse.Failure) {
            UploadResponse.Failure failure = (UploadResponse.Failure) response;
            tryResolveErrorAndComplete$AndroidPhotosUploader_release(completer, uploadRequest, response, failure.getErrorCode(), failure.getEx(), failure.getErrorCategory(), errorMessage);
        } else if (response instanceof UploadResponse.NoRetryFailure) {
            UploadResponse.NoRetryFailure noRetryFailure = (UploadResponse.NoRetryFailure) response;
            tryResolveErrorAndComplete$AndroidPhotosUploader_release(completer, uploadRequest, response, noRetryFailure.getErrorCode(), noRetryFailure.getEx(), noRetryFailure.getErrorCategory(), errorMessage);
        } else if (response instanceof UploadResponse.AcceptableFailure) {
            UploadLogger uploadLogger2 = this.logger;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Received acceptable failure request ");
            outline1072.append(uploadRequest.getId());
            outline1072.append("and path = ");
            outline1072.append(this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()));
            uploadLogger2.w$AndroidPhotosUploader_release(TAG, outline1072.toString());
            this.logger.w$AndroidPhotosUploader_release(TAG, errorMessage);
            cleanupCoordinatorOnError$AndroidPhotosUploader_release(uploadRequest.getId());
            UploadResponse.AcceptableFailure acceptableFailure = (UploadResponse.AcceptableFailure) response;
            recordErrorMetric(CdsMetrics.UPLOAD_MULTIPART_ACCEPTABLE_ERROR, acceptableFailure.getErrorCode(), acceptableFailure.getEx(), acceptableFailure.getErrorCategory());
            completer.setResponse(response);
        } else {
            UploadLogger uploadLogger3 = this.logger;
            uploadLogger3.w$AndroidPhotosUploader_release(TAG, "Received unknown response " + response + " type for request " + uploadRequest.getId() + Chars.SPACE + "and path = " + this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()));
            this.logger.w$AndroidPhotosUploader_release(TAG, errorMessage);
            this.metrics.recordSimpleEvent(TAG, CdsMultiPartUploader$tryResolveErrorAndComplete$2.INSTANCE, new MetricRecordingType[0]);
            completer.setResponse(response);
        }
    }

    @Override // com.amazon.photos.uploader.Uploader
    public void updateUploaderState(@NotNull UploaderState uploaderState) {
        Intrinsics.checkParameterIsNotNull(uploaderState, "uploaderState");
        if (!Intrinsics.areEqual(uploaderState.name(), getUploaderState$AndroidPhotosUploader_release())) {
            this.sharedPreferences.edit().putString(UPLOADER_STATE_KEY, uploaderState.name()).apply();
        }
    }

    @VisibleForTesting
    public final void tryResolveErrorAndComplete$AndroidPhotosUploader_release(@NotNull UploadCompleter completer, @NotNull UploadRequest uploadRequest, @NotNull UploadResponse originalResponse, @NotNull String originalErrorCode, @NotNull Throwable originalThrowable, @NotNull UploadErrorCategory originalErrorCategory, @NotNull String errorMessage) {
        Intrinsics.checkParameterIsNotNull(completer, "completer");
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(originalResponse, "originalResponse");
        Intrinsics.checkParameterIsNotNull(originalErrorCode, "originalErrorCode");
        Intrinsics.checkParameterIsNotNull(originalThrowable, "originalThrowable");
        Intrinsics.checkParameterIsNotNull(originalErrorCategory, "originalErrorCategory");
        Intrinsics.checkParameterIsNotNull(errorMessage, "errorMessage");
        if ((originalResponse instanceof UploadResponse.Failure) || (originalResponse instanceof UploadResponse.NoRetryFailure)) {
            if (originalThrowable instanceof CDUSException) {
                tryResolveCDUSErrorAndComplete$AndroidPhotosUploader_release((CDUSException) originalThrowable, completer, uploadRequest, errorMessage);
                return;
            } else if (originalThrowable instanceof CDSException) {
                extractCDSErrorAndComplete$AndroidPhotosUploader_release((CDSException) originalThrowable, originalResponse, completer, uploadRequest, errorMessage);
                return;
            } else if (originalThrowable instanceof LocalValidationException) {
                extractLocalValidationAndComplete$AndroidPhotosUploader_release((LocalValidationException) originalThrowable, originalResponse, originalErrorCode, originalErrorCategory, completer, uploadRequest, errorMessage);
                return;
            } else if (originalThrowable instanceof PartUploadException) {
                recordErrorMetric(CdsMetrics.UPLOAD_MULTIPART_PART_EXCEPTION, originalErrorCode, originalThrowable, originalErrorCategory);
                cleanupCoordinatorOnError$AndroidPhotosUploader_release(uploadRequest.getId());
                completer.setResponse(originalResponse);
                return;
            } else {
                recordErrorMetric(CdsMetrics.UPLOAD_MULTIPART_UNKNOWN_EXCEPTION, originalErrorCode, originalThrowable, originalErrorCategory);
                cleanupCoordinatorOnError$AndroidPhotosUploader_release(uploadRequest.getId());
                completer.setResponse(originalResponse);
                return;
            }
        }
        throw new IllegalStateException("Check failed.".toString());
    }
}
