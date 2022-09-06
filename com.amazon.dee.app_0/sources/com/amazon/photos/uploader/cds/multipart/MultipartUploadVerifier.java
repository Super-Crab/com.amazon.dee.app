package com.amazon.photos.uploader.cds.multipart;

import androidx.annotation.VisibleForTesting;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.clouddrive.cdasdk.cdus.CDUSError;
import com.amazon.clouddrive.cdasdk.cdus.CDUSException;
import com.amazon.clouddrive.cdasdk.cdus.MultipartUploadStatus;
import com.amazon.clouddrive.cdasdk.cdus.RetrieveMultipartRequest;
import com.amazon.clouddrive.cdasdk.cdus.RetrieveMultipartResponse;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadResponse;
import com.amazon.photos.uploader.log.UploadLogger;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt__MathJVMKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: MultipartUploadVerifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0000\u0018\u0000  2\u00020\u0001:\u0001 B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJk\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102!\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\f0\u00132!\u0010\u0018\u001a\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\f0\u0013H\u0000¢\u0006\u0002\b\u001aJ%\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001eH\u0001¢\u0006\u0002\b\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadVerifier;", "", "cdClient", "Lcom/amazon/clouddrive/cdasdk/CDClient;", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "partInfoDao", "Lcom/amazon/photos/uploader/cds/multipart/PartInfoDao;", "(Lcom/amazon/clouddrive/cdasdk/CDClient;Lcom/amazon/photos/uploader/log/UploadLogger;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/uploader/cds/multipart/PartInfoDao;)V", "initiateRetrieveMultipart", "", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "serviceUploadId", "", AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, "onSuccess", "Lkotlin/Function1;", "Lcom/amazon/photos/uploader/UploadResponse;", "Lkotlin/ParameterName;", "name", "response", "onError", "error", "initiateRetrieveMultipart$AndroidPhotosUploader_release", "waitAndMakeRetrieveCall", "Lcom/amazon/clouddrive/cdasdk/cdus/RetrieveMultipartResponse;", "tier", "", "waitAndMakeRetrieveCall$AndroidPhotosUploader_release", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MultipartUploadVerifier {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final int MAX_RETRIES = 11;
    private static final long ONE_SECOND = 1000;
    private static final String TAG = "MultipartUploadVerifier";
    private final CDClient cdClient;
    private final UploadLogger logger;
    private final Metrics metrics;
    private final PartInfoDao partInfoDao;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MultipartUploadVerifier.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadVerifier$Companion;", "", "()V", "MAX_RETRIES", "", "ONE_SECOND", "", "TAG", "", "calculateMaxBackOffMillis", "tier", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
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

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public MultipartUploadVerifier(@NotNull CDClient cdClient, @NotNull UploadLogger logger, @NotNull Metrics metrics, @NotNull PartInfoDao partInfoDao) {
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(partInfoDao, "partInfoDao");
        this.cdClient = cdClient;
        this.logger = logger;
        this.metrics = metrics;
        this.partInfoDao = partInfoDao;
    }

    /* JADX WARN: Type inference failed for: r11v4, types: [T, com.amazon.clouddrive.cdasdk.cdus.RetrieveMultipartResponse] */
    public final void initiateRetrieveMultipart$AndroidPhotosUploader_release(@NotNull UploadRequest uploadRequest, @NotNull String serviceUploadId, @NotNull String nodeId, @NotNull Function1<? super UploadResponse, Unit> onSuccess, @NotNull Function1<? super UploadResponse, Unit> onError) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(serviceUploadId, "serviceUploadId");
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        Intrinsics.checkParameterIsNotNull(onSuccess, "onSuccess");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        try {
            AtomicInteger atomicInteger = new AtomicInteger(0);
            do {
                int incrementAndGet = atomicInteger.incrementAndGet();
                UploadLogger uploadLogger = this.logger;
                uploadLogger.i$AndroidPhotosUploader_release(TAG, "RetrieveMultipartUpload call number : " + incrementAndGet);
                objectRef.element = waitAndMakeRetrieveCall$AndroidPhotosUploader_release(serviceUploadId, nodeId, incrementAndGet);
                String status = ((RetrieveMultipartResponse) objectRef.element).getStatus();
                if (status != null) {
                    switch (status.hashCode()) {
                        case -1990365125:
                            if (status.equals(MultipartUploadStatus.UPLOAD_FAILED)) {
                                RetrieveMultipartResponse.UploadErrorDetails uploadErrorDetails = ((RetrieveMultipartResponse) objectRef.element).getUploadErrorDetails();
                                Intrinsics.checkExpressionValueIsNotNull(uploadErrorDetails, "retrieveMultipartResponse.uploadErrorDetails");
                                String errorCode = uploadErrorDetails.getErrorCode();
                                RetrieveMultipartResponse.UploadErrorDetails uploadErrorDetails2 = ((RetrieveMultipartResponse) objectRef.element).getUploadErrorDetails();
                                Intrinsics.checkExpressionValueIsNotNull(uploadErrorDetails2, "retrieveMultipartResponse.uploadErrorDetails");
                                String errorMessage = uploadErrorDetails2.getErrorMessage();
                                Intrinsics.checkExpressionValueIsNotNull(errorMessage, "errorMessage");
                                LocalValidationException localValidationException = new LocalValidationException(errorMessage, errorCode);
                                Intrinsics.checkExpressionValueIsNotNull(errorCode, "errorCode");
                                onError.mo12165invoke(new UploadResponse.Failure(errorCode, localValidationException, UploadErrorCategory.MULTIPART_NOT_SUCCEEDED, null, false, 24, null));
                                this.metrics.recordSimpleEvent(TAG, MultipartUploadVerifier$initiateRetrieveMultipart$1.INSTANCE, new MetricRecordingType[0]);
                                return;
                            }
                            break;
                        case -1794422073:
                            if (status.equals(MultipartUploadStatus.UPLOAD_EXPIRED)) {
                                this.partInfoDao.deleteByRequestId(uploadRequest.getId());
                                onError.mo12165invoke(new UploadResponse.Failure(UploadResponse.UNKNOWN_UPLOAD_ERROR, new LocalValidationException("Upload expired after " + atomicInteger.get() + " retries"), UploadErrorCategory.MULTIPART_NOT_SUCCEEDED, null, false, 24, null));
                                this.metrics.recordSimpleEvent(TAG, MultipartUploadVerifier$initiateRetrieveMultipart$2.INSTANCE, new MetricRecordingType[0]);
                                return;
                            }
                            break;
                        case -1769293111:
                            if (status.equals(MultipartUploadStatus.UPLOAD_IN_PROGRESS)) {
                                this.metrics.recordCustomMetric(TAG, new ClientMetric().addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.multipart.MultipartUploadVerifier$initiateRetrieveMultipart$clientMetric$1
                                    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                                    public final String getEventName() {
                                        return ((RetrieveMultipartResponse) Ref.ObjectRef.this.element).getStatus();
                                    }
                                }, ((RetrieveMultipartResponse) objectRef.element).getMissingParts().size()).withTagName(TAG), new MetricRecordingType[0]);
                                PartInfoDao partInfoDao = this.partInfoDao;
                                long id = uploadRequest.getId();
                                List<Long> missingParts = ((RetrieveMultipartResponse) objectRef.element).getMissingParts();
                                Intrinsics.checkExpressionValueIsNotNull(missingParts, "retrieveMultipartResponse.missingParts");
                                partInfoDao.deleteBulkParts(id, missingParts);
                                onError.mo12165invoke(new UploadResponse.Failure(UploadResponse.UNKNOWN_UPLOAD_ERROR, new LocalValidationException("Upload still in progress"), UploadErrorCategory.MULTIPART_NOT_SUCCEEDED, null, false, 24, null));
                                return;
                            }
                            break;
                        case -1462173309:
                            if (status.equals("UPLOAD_SUCCEEDED")) {
                                onSuccess.mo12165invoke(new UploadResponse.Success());
                                return;
                            }
                            break;
                    }
                }
                if (atomicInteger.get() < 11) {
                }
                this.partInfoDao.deleteByRequestId(uploadRequest.getId());
                onError.mo12165invoke(new UploadResponse.Failure(UploadResponse.UNKNOWN_UPLOAD_ERROR, new Throwable("Did not get Success status after 11 retries"), UploadErrorCategory.MULTIPART_NOT_SUCCEEDED, null, false, 24, null));
            } while (Intrinsics.areEqual(((RetrieveMultipartResponse) objectRef.element).getStatus(), MultipartUploadStatus.UPLOAD_COMPLETING));
            this.partInfoDao.deleteByRequestId(uploadRequest.getId());
            onError.mo12165invoke(new UploadResponse.Failure(UploadResponse.UNKNOWN_UPLOAD_ERROR, new Throwable("Did not get Success status after 11 retries"), UploadErrorCategory.MULTIPART_NOT_SUCCEEDED, null, false, 24, null));
        } catch (CDUSException e) {
            this.logger.e$AndroidPhotosUploader_release(TAG, "CDUSException received while calling retrieveMultipartUpload", e);
            CDUSError cdusError = e.getCdusError();
            Intrinsics.checkExpressionValueIsNotNull(cdusError, "ex.cdusError");
            String errorCode2 = cdusError.getErrorCode();
            if (errorCode2 == null) {
                errorCode2 = UploadResponse.UNKNOWN_UPLOAD_ERROR;
            }
            onError.mo12165invoke(new UploadResponse.Failure(errorCode2, e, UploadErrorCategory.MULTIPART_NOT_SUCCEEDED, null, false, 24, null));
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            onError.mo12165invoke(new UploadResponse.Failure(UploadResponse.UNKNOWN_UPLOAD_ERROR, e2, UploadErrorCategory.UNKNOWN_ERROR, null, false, 24, null));
        } catch (Exception e3) {
            this.logger.e$AndroidPhotosUploader_release(TAG, "Exception received while calling retrieveMultipartUpload", e3);
            onError.mo12165invoke(new UploadResponse.Failure(UploadResponse.UNKNOWN_UPLOAD_ERROR, e3, UploadErrorCategory.MULTIPART_NOT_SUCCEEDED, null, false, 24, null));
        }
    }

    @VisibleForTesting
    @NotNull
    public final RetrieveMultipartResponse waitAndMakeRetrieveCall$AndroidPhotosUploader_release(@NotNull String serviceUploadId, @NotNull String nodeId, int i) {
        Intrinsics.checkParameterIsNotNull(serviceUploadId, "serviceUploadId");
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        Thread.sleep(Companion.calculateMaxBackOffMillis(i));
        RetrieveMultipartResponse blockingGet = this.cdClient.getCDUSCalls().retrieveMultipartUpload(new RetrieveMultipartRequest(serviceUploadId), nodeId).blockingGet();
        Intrinsics.checkExpressionValueIsNotNull(blockingGet, "cdClient.cdusCalls.retri…st, nodeId).blockingGet()");
        return blockingGet;
    }
}
