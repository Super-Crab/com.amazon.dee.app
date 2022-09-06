package com.amazon.photos.uploader.cds.error;

import com.amazon.alexa.presence.utils.MetricsUtil;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.cdus.CDUSError;
import com.amazon.clouddrive.cdasdk.cdus.CDUSException;
import com.amazon.clouddrive.cdasdk.cdus.UploadErrorCode;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.cds.multipart.PartInfo;
import com.amazon.photos.uploader.cds.multipart.PartUploadException;
import com.amazon.photos.uploader.cds.multipart.UploadPartResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CdsUploadPartErrorResolver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0016\u0010\r\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bJ8\u0010\u000e\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/photos/uploader/cds/error/CdsUploadPartErrorResolver;", "", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "getUploadPartResponse", "Lcom/amazon/photos/uploader/cds/multipart/UploadPartResponse;", "ex", "Lcom/amazon/clouddrive/cdasdk/cdus/CDUSException;", "errorCode", "", "partInfo", "Lcom/amazon/photos/uploader/cds/multipart/PartInfo;", MetricsUtil.MetricsId.RESOLVE, "uploadPartResponseFailure", "", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "revisedRequest", "metricEventName", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CdsUploadPartErrorResolver {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "CdsUploadPartErrorResolver";
    private final Metrics metrics;

    /* compiled from: CdsUploadPartErrorResolver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/cds/error/CdsUploadPartErrorResolver$Companion;", "", "()V", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[UploadErrorCode.values().length];

        static {
            $EnumSwitchMapping$0[UploadErrorCode.MultipartUploadNotFound.ordinal()] = 1;
            $EnumSwitchMapping$0[UploadErrorCode.MissingUploadId.ordinal()] = 2;
            $EnumSwitchMapping$0[UploadErrorCode.MissingPartMd5.ordinal()] = 3;
            $EnumSwitchMapping$0[UploadErrorCode.InvalidPartNumber.ordinal()] = 4;
            $EnumSwitchMapping$0[UploadErrorCode.InvalidPartSize.ordinal()] = 5;
            $EnumSwitchMapping$0[UploadErrorCode.PartSizeSmallerThanExpected.ordinal()] = 6;
            $EnumSwitchMapping$0[UploadErrorCode.PartSizeLargerThanExpected.ordinal()] = 7;
            $EnumSwitchMapping$0[UploadErrorCode.Md5MismatchError.ordinal()] = 8;
            $EnumSwitchMapping$0[UploadErrorCode.AuthenticationError.ordinal()] = 9;
        }
    }

    public CdsUploadPartErrorResolver(@NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.metrics = metrics;
    }

    private final UploadPartResponse getUploadPartResponse(CDUSException cDUSException, String str, PartInfo partInfo) {
        PartInfo copy;
        if (CdsUploadErrorResolver.Companion.is500Error$AndroidPhotosUploader_release(cDUSException.getCode())) {
            return uploadPartResponseFailure$default(this, str, cDUSException, UploadErrorCategory.SERVER_ERROR, null, null, 24, null);
        }
        if (CdsUploadErrorResolver.Companion.tooManyRequests$AndroidPhotosUploader_release(cDUSException.getCode())) {
            return uploadPartResponseFailure$default(this, str, cDUSException, UploadErrorCategory.TOO_MANY_REQUESTS, null, null, 24, null);
        }
        UploadErrorCode uploadErrorCode = CdsErrorCodesKt.toUploadErrorCode(str);
        if (uploadErrorCode != null) {
            switch (WhenMappings.$EnumSwitchMapping$0[uploadErrorCode.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    return uploadPartResponseFailure$default(this, str, cDUSException, UploadErrorCategory.INVALID_PARAMETER, null, null, 24, null);
                case 8:
                    CDUSError cdusError = cDUSException.getCdusError();
                    Intrinsics.checkExpressionValueIsNotNull(cdusError, "ex.cdusError");
                    PartUploadException partUploadException = new PartUploadException(cdusError.getMessage(), UploadErrorCode.Md5MismatchError.name());
                    UploadErrorCategory uploadErrorCategory = UploadErrorCategory.DIGEST_ERROR;
                    copy = partInfo.copy((r35 & 1) != 0 ? partInfo.partId : 0L, (r35 & 2) != 0 ? partInfo.uploadRequestId : 0L, (r35 & 4) != 0 ? partInfo.partUploadState : null, (r35 & 8) != 0 ? partInfo.partEnqueueTimestamp : 0L, (r35 & 16) != 0 ? partInfo.partUploadStartTimestamp : 0L, (r35 & 32) != 0 ? partInfo.partUploadCompleteTimestamp : 0L, (r35 & 64) != 0 ? partInfo.partMd5 : null, (r35 & 128) != 0 ? partInfo.partSize : 0L, (r35 & 256) != 0 ? partInfo.partOffset : 0L, (r35 & 512) != 0 ? partInfo.serviceUploadId : null, (r35 & 1024) != 0 ? partInfo.nodeId : null);
                    return uploadPartResponseFailure$default(this, str, partUploadException, uploadErrorCategory, copy, null, 16, null);
                case 9:
                    return uploadPartResponseFailure$default(this, str, cDUSException, UploadErrorCategory.AUTH_ERROR, null, null, 24, null);
            }
        }
        return uploadPartResponseFailure(str, cDUSException, UploadErrorCategory.UNKNOWN_ERROR, null, GeneratedOutlineSupport1.outline72("UNEXPECTED_SERVICE_ERROR:", str));
    }

    private final UploadPartResponse uploadPartResponseFailure(String str, Throwable th, UploadErrorCategory uploadErrorCategory, PartInfo partInfo, final String str2) {
        if (str2 != null) {
            this.metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.cds.error.CdsUploadPartErrorResolver$uploadPartResponseFailure$$inlined$let$lambda$1
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                @NotNull
                public final String getEventName() {
                    return str2;
                }
            }, new MetricRecordingType[0]);
        }
        return new UploadPartResponse.Failure(str, th, uploadErrorCategory, partInfo);
    }

    static /* synthetic */ UploadPartResponse uploadPartResponseFailure$default(CdsUploadPartErrorResolver cdsUploadPartErrorResolver, String str, Throwable th, UploadErrorCategory uploadErrorCategory, PartInfo partInfo, String str2, int i, Object obj) {
        return cdsUploadPartErrorResolver.uploadPartResponseFailure(str, th, uploadErrorCategory, (i & 8) != 0 ? null : partInfo, (i & 16) != 0 ? null : str2);
    }

    @NotNull
    public final UploadPartResponse resolve(@NotNull PartInfo partInfo, @NotNull CDUSException ex) {
        Intrinsics.checkParameterIsNotNull(partInfo, "partInfo");
        Intrinsics.checkParameterIsNotNull(ex, "ex");
        CDUSError cdusError = ex.getCdusError();
        Intrinsics.checkExpressionValueIsNotNull(cdusError, "ex.cdusError");
        final String errorCode = cdusError.getErrorCode();
        if (errorCode == null) {
            errorCode = UploadPartResponse.UNKNOWN_PART_UPLOAD_ERROR;
        }
        this.metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.cds.error.CdsUploadPartErrorResolver$resolve$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error:");
                outline107.append(errorCode);
                return outline107.toString();
            }
        }, new MetricRecordingType[0]);
        return getUploadPartResponse(ex, errorCode, partInfo);
    }
}
