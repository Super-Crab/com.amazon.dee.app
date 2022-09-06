package com.amazon.photos.uploader.cds.error;

import com.amazon.alexa.presence.utils.MetricsUtil;
import com.amazon.clouddrive.cdasdk.cdus.UploadErrorCode;
import com.amazon.photos.uploader.ResultMetadata;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadResponse;
import com.amazon.photos.uploader.cds.CdsUploader;
import com.amazon.photos.uploader.cds.CdsUtil;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadRequestMetadataDao;
import java.io.InterruptedIOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MultiPartUploadErrorResolver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J \u0010\u000f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/photos/uploader/cds/error/MultiPartUploadErrorResolver;", "", "multipartUploadRequestMetadataDao", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadRequestMetadataDao;", "cdsUtil", "Lcom/amazon/photos/uploader/cds/CdsUtil;", "(Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadRequestMetadataDao;Lcom/amazon/photos/uploader/cds/CdsUtil;)V", "getUploadResponse", "Lcom/amazon/photos/uploader/UploadResponse;", "request", "Lcom/amazon/photos/uploader/UploadRequest;", "uploadErrorCode", "Lcom/amazon/clouddrive/cdasdk/cdus/UploadErrorCode;", "ex", "", MetricsUtil.MetricsId.RESOLVE, "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MultiPartUploadErrorResolver {
    private final CdsUtil cdsUtil;
    private final MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[UploadErrorCode.values().length];

        static {
            $EnumSwitchMapping$0[UploadErrorCode.MultipartUploadAlreadyAborted.ordinal()] = 1;
            $EnumSwitchMapping$0[UploadErrorCode.MultipartUploadAlreadyCompleted.ordinal()] = 2;
            $EnumSwitchMapping$0[UploadErrorCode.MultipartUploadCompletionInProgress.ordinal()] = 3;
        }
    }

    public MultiPartUploadErrorResolver(@NotNull MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao, @NotNull CdsUtil cdsUtil) {
        Intrinsics.checkParameterIsNotNull(multipartUploadRequestMetadataDao, "multipartUploadRequestMetadataDao");
        Intrinsics.checkParameterIsNotNull(cdsUtil, "cdsUtil");
        this.multipartUploadRequestMetadataDao = multipartUploadRequestMetadataDao;
        this.cdsUtil = cdsUtil;
    }

    private final UploadResponse getUploadResponse(UploadRequest uploadRequest, UploadErrorCode uploadErrorCode, Throwable th) {
        try {
            String nodeIdForUploadRequest = this.multipartUploadRequestMetadataDao.getNodeIdForUploadRequest(uploadRequest.getId());
            if (nodeIdForUploadRequest != null) {
                if (CdsUtil.hasChangedFromPendingToAvailable$AndroidPhotosUploader_release$default(this.cdsUtil, nodeIdForUploadRequest, 0L, 2, null)) {
                    ResultMetadata resultMetadata = new ResultMetadata();
                    resultMetadata.putString(CdsUploader.RESULT_NODE_ID_KEY, nodeIdForUploadRequest);
                    return new UploadResponse.Success(resultMetadata);
                }
                return new UploadResponse.Failure(uploadErrorCode.name(), th, UploadErrorCategory.UPLOAD_NOT_IN_PROGRESS, null, false, 24, null);
            }
            return new UploadResponse.NoRetryFailure(uploadErrorCode.name(), th, UploadErrorCategory.UPLOAD_NOT_IN_PROGRESS);
        } catch (InterruptedIOException e) {
            Thread.currentThread().interrupt();
            return new UploadResponse.Failure(uploadErrorCode.name(), e, UploadErrorCategory.UPLOAD_NOT_IN_PROGRESS, null, false, 24, null);
        } catch (Exception e2) {
            return new UploadResponse.Failure(uploadErrorCode.name(), e2, UploadErrorCategory.UPLOAD_NOT_IN_PROGRESS, null, false, 24, null);
        }
    }

    @Nullable
    public final UploadResponse resolve(@NotNull UploadErrorCode uploadErrorCode, @NotNull UploadRequest request, @NotNull Throwable ex) {
        Intrinsics.checkParameterIsNotNull(uploadErrorCode, "uploadErrorCode");
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(ex, "ex");
        int i = WhenMappings.$EnumSwitchMapping$0[uploadErrorCode.ordinal()];
        if (i != 1) {
            if (i != 2 && i != 3) {
                return null;
            }
            return getUploadResponse(request, uploadErrorCode, ex);
        }
        return new UploadResponse.NoRetryFailure(uploadErrorCode.name(), ex, UploadErrorCategory.UPLOAD_NOT_IN_PROGRESS);
    }
}
