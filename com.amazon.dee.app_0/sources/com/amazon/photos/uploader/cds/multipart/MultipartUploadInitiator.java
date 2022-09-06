package com.amazon.photos.uploader.cds.multipart;

import androidx.annotation.VisibleForTesting;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.clouddrive.cdasdk.CloudDriveException;
import com.amazon.clouddrive.cdasdk.cdus.CDUSCalls;
import com.amazon.clouddrive.cdasdk.cdus.CDUSError;
import com.amazon.clouddrive.cdasdk.cdus.CDUSException;
import com.amazon.clouddrive.cdasdk.cdus.InitiateMultipartRequest;
import com.amazon.clouddrive.cdasdk.cdus.InitiateMultipartResponse;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadResponse;
import com.amazon.photos.uploader.cds.CdsMetrics;
import com.amazon.photos.uploader.cds.ParentNodeFetcher;
import com.amazon.photos.uploader.log.UploadLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MultipartUploadInitiator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 82\u00020\u0001:\u00018B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0015\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u0014H\u0001¢\u0006\u0002\b\u0018J\u001c\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001a2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0015\u0010\u001f\u001a\u00020 2\u0006\u0010\u0013\u001a\u00020\u0014H\u0001¢\u0006\u0002\b!J(\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020\u001cH\u0002J3\u0010)\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020%2!\u0010*\u001a\u001d\u0012\u0013\u0012\u00110#¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00120+H\u0002J\u0018\u0010/\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J,\u00100\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001b2\b\b\u0002\u0010'\u001a\u00020\u001b2\b\b\u0002\u0010(\u001a\u00020\u001cH\u0002J]\u00101\u001a\u0004\u0018\u0001022\u0006\u0010\u0013\u001a\u00020\u00142!\u00103\u001a\u001d\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(4\u0012\u0004\u0012\u00020\u00120+2!\u0010*\u001a\u001d\u0012\u0013\u0012\u00110#¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00120+H\u0000¢\u0006\u0002\b5J\u0015\u00106\u001a\u00020#2\u0006\u0010\u001d\u001a\u00020%H\u0001¢\u0006\u0002\b7R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadInitiator;", "", "cdClient", "Lcom/amazon/clouddrive/cdasdk/CDClient;", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "parentNodeFetcher", "Lcom/amazon/photos/uploader/cds/ParentNodeFetcher;", "multipartUploadRequestMetadataDao", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadRequestMetadataDao;", "partInfoDao", "Lcom/amazon/photos/uploader/cds/multipart/PartInfoDao;", "initializeMultiPartRequestBuilder", "Lcom/amazon/photos/uploader/cds/multipart/InitializeMultiPartRequestBuilder;", "(Lcom/amazon/clouddrive/cdasdk/CDClient;Lcom/amazon/photos/uploader/log/UploadLogger;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/uploader/cds/ParentNodeFetcher;Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadRequestMetadataDao;Lcom/amazon/photos/uploader/cds/multipart/PartInfoDao;Lcom/amazon/photos/uploader/cds/multipart/InitializeMultiPartRequestBuilder;)V", "createPartsForRequest", "", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "initiateMultipartResponse", "Lcom/amazon/clouddrive/cdasdk/cdus/InitiateMultipartResponse;", "createResponseFromMetadata", "createResponseFromMetadata$AndroidPhotosUploader_release", "extractErrorDetails", "Lkotlin/Pair;", "", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "ex", "Lcom/amazon/clouddrive/cdasdk/cdus/CDUSException;", "getFile", "Ljava/io/File;", "getFile$AndroidPhotosUploader_release", "handleAcceptableException", "Lcom/amazon/photos/uploader/UploadResponse;", "throwable", "", "metricEventName", "errorCode", "errorCategory", "handleException", "onError", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "error", "handleInitiateMultiPartSuccess", "handleUploadRequestException", "initiateMultiPartUpload", "Lio/reactivex/rxjava3/disposables/Disposable;", "onSuccess", "response", "initiateMultiPartUpload$AndroidPhotosUploader_release", "wrapExceptionToUploadResponse", "wrapExceptionToUploadResponse$AndroidPhotosUploader_release", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MultipartUploadInitiator {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String FILE_NOT_FOUND = "FileNotFound";
    private static final String TAG = "MultipartUploadInitiator";
    private final CDClient cdClient;
    private final InitializeMultiPartRequestBuilder initializeMultiPartRequestBuilder;
    private final UploadLogger logger;
    private final Metrics metrics;
    private final MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao;
    private final ParentNodeFetcher parentNodeFetcher;
    private final PartInfoDao partInfoDao;

    /* compiled from: MultipartUploadInitiator.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadInitiator$Companion;", "", "()V", "FILE_NOT_FOUND", "", "TAG", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public MultipartUploadInitiator(@NotNull CDClient cdClient, @NotNull UploadLogger logger, @NotNull Metrics metrics, @NotNull ParentNodeFetcher parentNodeFetcher, @NotNull MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao, @NotNull PartInfoDao partInfoDao, @NotNull InitializeMultiPartRequestBuilder initializeMultiPartRequestBuilder) {
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(parentNodeFetcher, "parentNodeFetcher");
        Intrinsics.checkParameterIsNotNull(multipartUploadRequestMetadataDao, "multipartUploadRequestMetadataDao");
        Intrinsics.checkParameterIsNotNull(partInfoDao, "partInfoDao");
        Intrinsics.checkParameterIsNotNull(initializeMultiPartRequestBuilder, "initializeMultiPartRequestBuilder");
        this.cdClient = cdClient;
        this.logger = logger;
        this.metrics = metrics;
        this.parentNodeFetcher = parentNodeFetcher;
        this.multipartUploadRequestMetadataDao = multipartUploadRequestMetadataDao;
        this.partInfoDao = partInfoDao;
        this.initializeMultiPartRequestBuilder = initializeMultiPartRequestBuilder;
    }

    private final void createPartsForRequest(UploadRequest uploadRequest, InitiateMultipartResponse initiateMultipartResponse) {
        Long totalNumberOfParts = initiateMultipartResponse.getTotalNumberOfParts();
        Intrinsics.checkExpressionValueIsNotNull(totalNumberOfParts, "initiateMultipartResponse.totalNumberOfParts");
        long longValue = totalNumberOfParts.longValue();
        if (1 <= longValue) {
            long j = 1;
            while (true) {
                long id = uploadRequest.getId();
                PartUploadState partUploadState = PartUploadState.ENQUEUED;
                String uploadId = initiateMultipartResponse.getUploadId();
                String nodeId = initiateMultipartResponse.getNodeId();
                Long partSize = initiateMultipartResponse.getPartSize();
                Intrinsics.checkExpressionValueIsNotNull(partSize, "initiateMultipartResponse.partSize");
                this.partInfoDao.insert(new PartInfo(j, id, partUploadState, 0L, 0L, 0L, null, partSize.longValue(), j - 1, uploadId, nodeId, 120, null));
                if (j == longValue) {
                    return;
                }
                j++;
            }
        }
    }

    private final Pair<String, UploadErrorCategory> extractErrorDetails(CDUSException cDUSException) {
        CDUSError cdusError = cDUSException.getCdusError();
        Intrinsics.checkExpressionValueIsNotNull(cdusError, "ex.cdusError");
        if (cdusError.getErrorCode() != null) {
            CDUSError cdusError2 = cDUSException.getCdusError();
            Intrinsics.checkExpressionValueIsNotNull(cdusError2, "ex.cdusError");
            return new Pair<>(cdusError2.getErrorCode(), UploadErrorCategory.OTHER_KNOWN_ERROR);
        }
        return new Pair<>(UploadResponse.UNKNOWN_UPLOAD_ERROR, UploadErrorCategory.UNKNOWN_ERROR);
    }

    private final UploadResponse handleAcceptableException(Throwable th, final String str, String str2, UploadErrorCategory uploadErrorCategory) {
        this.logger.d$AndroidPhotosUploader_release(TAG, "Got acceptable error while creating upload content request", th);
        this.metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.cds.multipart.MultipartUploadInitiator$handleAcceptableException$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return str;
            }
        }, new MetricRecordingType[0]);
        return new UploadResponse.AcceptableFailure(str2, th, uploadErrorCategory);
    }

    private final void handleException(Throwable th, Function1<? super UploadResponse, Unit> function1) {
        if (th instanceof CDUSException) {
            Pair<String, UploadErrorCategory> extractErrorDetails = extractErrorDetails((CDUSException) th);
            function1.mo12165invoke(handleUploadRequestException(th, CdsMetrics.CDS_EXCEPTION, extractErrorDetails.component1(), extractErrorDetails.component2()));
        } else if (th instanceof CloudDriveException) {
            function1.mo12165invoke(handleUploadRequestException$default(this, th, CdsMetrics.CLOUD_DRIVE_EXCEPTION, null, null, 12, null));
        } else if (th instanceof InterruptedIOException) {
            Thread.currentThread().interrupt();
            function1.mo12165invoke(handleUploadRequestException$default(this, th, CdsMetrics.INTERRUPTED_IO_EXCEPTION, null, null, 12, null));
        } else if (th instanceof FileNotFoundException) {
            function1.mo12165invoke(handleAcceptableException(th, CdsMetrics.FILE_NOT_FOUND_EXCEPTION, FILE_NOT_FOUND, UploadErrorCategory.FILE_NOT_PRESENT));
        } else if (!(th instanceof IOException)) {
            throw th;
        } else {
            function1.mo12165invoke(handleUploadRequestException$default(this, th, CdsMetrics.IO_EXCEPTION, null, null, 12, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleInitiateMultiPartSuccess(UploadRequest uploadRequest, InitiateMultipartResponse initiateMultipartResponse) {
        long id = uploadRequest.getId();
        String nodeId = initiateMultipartResponse.getNodeId();
        Intrinsics.checkExpressionValueIsNotNull(nodeId, "initiateMultipartResponse.nodeId");
        this.multipartUploadRequestMetadataDao.insert(new MultipartUploadRequestMetadata(id, nodeId, initiateMultipartResponse.getUploadId(), initiateMultipartResponse.getPartSize(), initiateMultipartResponse.getTotalNumberOfParts(), initiateMultipartResponse.getMultipartUploadStartTime(), initiateMultipartResponse.getMultipartUploadExpirationTime()));
        UploadLogger uploadLogger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Inserted metadata for node ");
        outline107.append(uploadRequest.getId());
        outline107.append(Chars.SPACE);
        outline107.append("and path = ");
        outline107.append(this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()));
        uploadLogger.d$AndroidPhotosUploader_release(TAG, outline107.toString());
        createPartsForRequest(uploadRequest, initiateMultipartResponse);
        UploadLogger uploadLogger2 = this.logger;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Inserted ");
        outline1072.append(initiateMultipartResponse.getTotalNumberOfParts());
        outline1072.append(" parts for request ");
        outline1072.append(uploadRequest.getId());
        outline1072.append(" and path = ");
        outline1072.append(this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()));
        uploadLogger2.d$AndroidPhotosUploader_release(TAG, outline1072.toString());
    }

    private final UploadResponse handleUploadRequestException(Throwable th, final String str, String str2, UploadErrorCategory uploadErrorCategory) {
        this.logger.e$AndroidPhotosUploader_release(TAG, "Got exception while creating upload content request", th);
        this.metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.cds.multipart.MultipartUploadInitiator$handleUploadRequestException$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return str;
            }
        }, new MetricRecordingType[0]);
        return new UploadResponse.Failure(str2, th, uploadErrorCategory, null, false, 24, null);
    }

    static /* synthetic */ UploadResponse handleUploadRequestException$default(MultipartUploadInitiator multipartUploadInitiator, Throwable th, String str, String str2, UploadErrorCategory uploadErrorCategory, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = UploadResponse.UNKNOWN_UPLOAD_ERROR;
        }
        if ((i & 8) != 0) {
            uploadErrorCategory = UploadErrorCategory.UNKNOWN_ERROR;
        }
        return multipartUploadInitiator.handleUploadRequestException(th, str, str2, uploadErrorCategory);
    }

    @VisibleForTesting
    @NotNull
    public final InitiateMultipartResponse createResponseFromMetadata$AndroidPhotosUploader_release(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        MultipartUploadRequestMetadata multipartMetadataByRequestId = this.multipartUploadRequestMetadataDao.getMultipartMetadataByRequestId(uploadRequest.getId());
        InitiateMultipartResponse initiateMultipartResponse = new InitiateMultipartResponse();
        String str = null;
        initiateMultipartResponse.setNodeId(multipartMetadataByRequestId != null ? multipartMetadataByRequestId.getNodeId() : null);
        if (multipartMetadataByRequestId != null) {
            str = multipartMetadataByRequestId.getUploadId();
        }
        initiateMultipartResponse.setUploadId(str);
        return initiateMultipartResponse;
    }

    @VisibleForTesting
    @NotNull
    public final File getFile$AndroidPhotosUploader_release(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        return new File(uploadRequest.getFilePath());
    }

    @Nullable
    public final Disposable initiateMultiPartUpload$AndroidPhotosUploader_release(@NotNull final UploadRequest uploadRequest, @NotNull final Function1<? super InitiateMultipartResponse, Unit> onSuccess, @NotNull final Function1<? super UploadResponse, Unit> onError) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(onSuccess, "onSuccess");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        try {
            String nodeIdForUploadRequest = this.multipartUploadRequestMetadataDao.getNodeIdForUploadRequest(uploadRequest.getId());
            if (nodeIdForUploadRequest != null) {
                UploadLogger uploadLogger = this.logger;
                uploadLogger.d$AndroidPhotosUploader_release(TAG, "Node found " + this.logger.obfuscate$AndroidPhotosUploader_release(nodeIdForUploadRequest));
                onSuccess.mo12165invoke(createResponseFromMetadata$AndroidPhotosUploader_release(uploadRequest));
                return null;
            }
            UploadLogger uploadLogger2 = this.logger;
            uploadLogger2.d$AndroidPhotosUploader_release(TAG, "Node not found initiating request: " + uploadRequest.getId() + Chars.SPACE + "and path = " + this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()));
            if (uploadRequest.getMd5() != null) {
                InitiateMultipartRequest request = this.initializeMultiPartRequestBuilder.getRequest(getFile$AndroidPhotosUploader_release(uploadRequest), uploadRequest, this.parentNodeFetcher);
                Unit unit = Unit.INSTANCE;
                CDUSCalls cDUSCalls = this.cdClient.getCDUSCalls();
                if (request == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("initializeMultipartRequest");
                }
                return cDUSCalls.initiateMultipartUpload(request, uploadRequest.getMd5()).subscribeOn(Schedulers.io()).subscribe(new Consumer<InitiateMultipartResponse>() { // from class: com.amazon.photos.uploader.cds.multipart.MultipartUploadInitiator$initiateMultiPartUpload$1
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(InitiateMultipartResponse it2) {
                        MultipartUploadInitiator multipartUploadInitiator = MultipartUploadInitiator.this;
                        UploadRequest uploadRequest2 = uploadRequest;
                        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                        multipartUploadInitiator.handleInitiateMultiPartSuccess(uploadRequest2, it2);
                        onSuccess.mo12165invoke(it2);
                    }
                }, new Consumer<Throwable>() { // from class: com.amazon.photos.uploader.cds.multipart.MultipartUploadInitiator$initiateMultiPartUpload$2
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Throwable it2) {
                        Function1 function1 = onError;
                        MultipartUploadInitiator multipartUploadInitiator = MultipartUploadInitiator.this;
                        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                        function1.mo12165invoke(multipartUploadInitiator.wrapExceptionToUploadResponse$AndroidPhotosUploader_release(it2));
                    }
                });
            }
            throw new IllegalArgumentException("Required value was null.".toString());
        } catch (Throwable th) {
            handleException(th, onError);
            return null;
        }
    }

    @VisibleForTesting
    @NotNull
    public final UploadResponse wrapExceptionToUploadResponse$AndroidPhotosUploader_release(@NotNull Throwable ex) {
        Intrinsics.checkParameterIsNotNull(ex, "ex");
        if (ex instanceof FileNotFoundException) {
            return handleAcceptableException(ex, CdsMetrics.FILE_NOT_FOUND_EXCEPTION, FILE_NOT_FOUND, UploadErrorCategory.FILE_NOT_PRESENT);
        }
        if (ex instanceof InterruptedIOException) {
            Thread.currentThread().interrupt();
            return handleUploadRequestException$default(this, ex, CdsMetrics.INTERRUPTED_IO_EXCEPTION, null, null, 12, null);
        } else if (ex instanceof CDUSException) {
            Pair<String, UploadErrorCategory> extractErrorDetails = extractErrorDetails((CDUSException) ex);
            return handleUploadRequestException(ex, CdsMetrics.CDS_EXCEPTION, extractErrorDetails.component1(), extractErrorDetails.component2());
        } else if (ex instanceof CloudDriveException) {
            return handleUploadRequestException$default(this, ex, CdsMetrics.CLOUD_DRIVE_EXCEPTION, null, null, 12, null);
        } else {
            if (!(ex instanceof IOException)) {
                throw ex;
            }
            return handleUploadRequestException$default(this, ex, CdsMetrics.IO_EXCEPTION, null, null, 12, null);
        }
    }
}
