package com.amazon.photos.uploader.cds.error;

import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.amazon.alexa.presence.utils.MetricsUtil;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.cds.CDSErrorCode;
import com.amazon.clouddrive.cdasdk.cdus.CDUSError;
import com.amazon.clouddrive.cdasdk.cdus.CDUSErrorDetails;
import com.amazon.clouddrive.cdasdk.cdus.CDUSException;
import com.amazon.clouddrive.cdasdk.cdus.UploadErrorCode;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadResponse;
import com.amazon.photos.uploader.cds.observer.CdsUploadNotifier;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CdsUploadErrorResolver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000  2\u00020\u0001:\u0001 B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0003J\u0018\u0010\u0013\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u0014H\u0002J(\u0010\u0015\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u0014H\u0002J\u0018\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u001aH\u0007J\u0018\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000eH\u0007J8\u0010\u001b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0010H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/amazon/photos/uploader/cds/error/CdsUploadErrorResolver;", "", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "conflictResolver", "Lcom/amazon/photos/uploader/cds/error/CdsConflictResolver;", "multiPartUploadErrorResolver", "Lcom/amazon/photos/uploader/cds/error/MultiPartUploadErrorResolver;", "cdsUploadNotifier", "Lcom/amazon/photos/uploader/cds/observer/CdsUploadNotifier;", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/uploader/cds/error/CdsConflictResolver;Lcom/amazon/photos/uploader/cds/error/MultiPartUploadErrorResolver;Lcom/amazon/photos/uploader/cds/observer/CdsUploadNotifier;)V", "getUploadResponse", "Lcom/amazon/photos/uploader/UploadResponse;", "ex", "Lcom/amazon/photos/uploader/cds/error/UploadException;", "errorCode", "", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "getUploadResponseForCdsErrors", "", "getUploadResponseForUploadErrors", "uploadErrorCode", "Lcom/amazon/clouddrive/cdasdk/cdus/UploadErrorCode;", MetricsUtil.MetricsId.RESOLVE, "request", "Lcom/amazon/clouddrive/cdasdk/cdus/CDUSException;", "uploadResponseFailure", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "revisedRequest", "metricEventName", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CdsUploadErrorResolver {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String ERROR = "Error";
    private static final int HTTP_CODE_TOO_MANY_REQUESTS = 429;
    @NotNull
    private static final List<UploadErrorCategory> NO_RETRY_ERROR_CATEGORIES;
    private static final String TAG = "CdsUploadErrorResolver";
    private final CdsUploadNotifier cdsUploadNotifier;
    private final CdsConflictResolver conflictResolver;
    private final Metrics metrics;
    private final MultiPartUploadErrorResolver multiPartUploadErrorResolver;

    /* compiled from: CdsUploadErrorResolver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0011J\u0015\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/photos/uploader/cds/error/CdsUploadErrorResolver$Companion;", "", "()V", "ERROR", "", "HTTP_CODE_TOO_MANY_REQUESTS", "", "NO_RETRY_ERROR_CATEGORIES", "", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "NO_RETRY_ERROR_CATEGORIES$annotations", "getNO_RETRY_ERROR_CATEGORIES$AndroidPhotosUploader_release", "()Ljava/util/List;", "TAG", "is500Error", "", "code", "is500Error$AndroidPhotosUploader_release", "tooManyRequests", "tooManyRequests$AndroidPhotosUploader_release", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @VisibleForTesting
        public static /* synthetic */ void NO_RETRY_ERROR_CATEGORIES$annotations() {
        }

        @NotNull
        public final List<UploadErrorCategory> getNO_RETRY_ERROR_CATEGORIES$AndroidPhotosUploader_release() {
            return CdsUploadErrorResolver.NO_RETRY_ERROR_CATEGORIES;
        }

        public final boolean is500Error$AndroidPhotosUploader_release(int i) {
            return i / 100 == 5;
        }

        public final boolean tooManyRequests$AndroidPhotosUploader_release(int i) {
            return i == 429;
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
            $EnumSwitchMapping$0[UploadErrorCode.Md5MismatchError.ordinal()] = 1;
            $EnumSwitchMapping$0[UploadErrorCode.InvalidContentMd5Error.ordinal()] = 2;
            $EnumSwitchMapping$0[UploadErrorCode.ContentSignatureMismatch.ordinal()] = 3;
            $EnumSwitchMapping$0[UploadErrorCode.InvalidContentSignatureType.ordinal()] = 4;
            $EnumSwitchMapping$0[UploadErrorCode.InsufficientStorage.ordinal()] = 5;
            $EnumSwitchMapping$0[UploadErrorCode.NoActiveSubscriptionFound.ordinal()] = 6;
            $EnumSwitchMapping$0[UploadErrorCode.ForbiddenAccess.ordinal()] = 7;
            $EnumSwitchMapping$0[UploadErrorCode.AuthenticationError.ordinal()] = 8;
            $EnumSwitchMapping$0[UploadErrorCode.UnauthourizedAccess.ordinal()] = 9;
            $EnumSwitchMapping$0[UploadErrorCode.InvalidParent.ordinal()] = 10;
            $EnumSwitchMapping$0[UploadErrorCode.ParentNodeIdNotFound.ordinal()] = 11;
            $EnumSwitchMapping$0[UploadErrorCode.ParentNodeInTrash.ordinal()] = 12;
            $EnumSwitchMapping$0[UploadErrorCode.DateTimeNotInUTC.ordinal()] = 13;
            $EnumSwitchMapping$0[UploadErrorCode.MissingContentName.ordinal()] = 14;
            $EnumSwitchMapping$0[UploadErrorCode.MissingFileSize.ordinal()] = 15;
            $EnumSwitchMapping$0[UploadErrorCode.MissingFileContent.ordinal()] = 16;
            $EnumSwitchMapping$0[UploadErrorCode.MissingFileMd5.ordinal()] = 17;
            $EnumSwitchMapping$0[UploadErrorCode.SocketException.ordinal()] = 18;
            $EnumSwitchMapping$0[UploadErrorCode.InvalidDateTimeFormat.ordinal()] = 19;
            $EnumSwitchMapping$0[UploadErrorCode.FileSizeTooLarge.ordinal()] = 20;
            $EnumSwitchMapping$0[UploadErrorCode.FileSizeTooSmall.ordinal()] = 21;
            $EnumSwitchMapping$0[UploadErrorCode.InvalidFileSize.ordinal()] = 22;
            $EnumSwitchMapping$0[UploadErrorCode.FileSizeLargerThanExpected.ordinal()] = 23;
            $EnumSwitchMapping$0[UploadErrorCode.FileSizeSmallerThanExpected.ordinal()] = 24;
            $EnumSwitchMapping$0[UploadErrorCode.PartSizeLargerThanExpected.ordinal()] = 25;
            $EnumSwitchMapping$0[UploadErrorCode.PartSizeSmallerThanExpected.ordinal()] = 26;
            $EnumSwitchMapping$0[UploadErrorCode.NameAlreadyExists.ordinal()] = 27;
            $EnumSwitchMapping$0[UploadErrorCode.DuplicatesConflictError.ordinal()] = 28;
            $EnumSwitchMapping$0[UploadErrorCode.FamilyConflict.ordinal()] = 29;
            $EnumSwitchMapping$0[UploadErrorCode.ConcurrentContentEdit.ordinal()] = 30;
            $EnumSwitchMapping$0[UploadErrorCode.ContentSignatureDuplicate.ordinal()] = 31;
            $EnumSwitchMapping$0[UploadErrorCode.NodeNotFound.ordinal()] = 32;
            $EnumSwitchMapping$0[UploadErrorCode.PreconditionFailed.ordinal()] = 33;
            $EnumSwitchMapping$0[UploadErrorCode.UnclassifiedClientError.ordinal()] = 34;
            $EnumSwitchMapping$0[UploadErrorCode.TooManyRequests.ordinal()] = 35;
            $EnumSwitchMapping$0[UploadErrorCode.InvalidRequest.ordinal()] = 36;
            $EnumSwitchMapping$0[UploadErrorCode.MissingNodeId.ordinal()] = 37;
            $EnumSwitchMapping$0[UploadErrorCode.InvalidNodeKind.ordinal()] = 38;
        }
    }

    static {
        List<UploadErrorCategory> listOf;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new UploadErrorCategory[]{UploadErrorCategory.CONFLICT_ERROR, UploadErrorCategory.QUOTA_ERROR_UNKNOWN, UploadErrorCategory.INVALID_PARAMETER, UploadErrorCategory.SPECIFIC_PARENT_NOT_FOUND});
        NO_RETRY_ERROR_CATEGORIES = listOf;
    }

    public CdsUploadErrorResolver(@NotNull Metrics metrics, @NotNull CdsConflictResolver conflictResolver, @NotNull MultiPartUploadErrorResolver multiPartUploadErrorResolver, @NotNull CdsUploadNotifier cdsUploadNotifier) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(conflictResolver, "conflictResolver");
        Intrinsics.checkParameterIsNotNull(multiPartUploadErrorResolver, "multiPartUploadErrorResolver");
        Intrinsics.checkParameterIsNotNull(cdsUploadNotifier, "cdsUploadNotifier");
        this.metrics = metrics;
        this.conflictResolver = conflictResolver;
        this.multiPartUploadErrorResolver = multiPartUploadErrorResolver;
        this.cdsUploadNotifier = cdsUploadNotifier;
    }

    @WorkerThread
    private final UploadResponse getUploadResponse(UploadException uploadException, String str, UploadRequest uploadRequest) {
        if (Companion.is500Error$AndroidPhotosUploader_release(uploadException.getHttpCode())) {
            return uploadResponseFailure$default(this, str, uploadException, UploadErrorCategory.SERVER_ERROR, null, null, 24, null);
        }
        if (Companion.tooManyRequests$AndroidPhotosUploader_release(uploadException.getHttpCode())) {
            return uploadResponseFailure$default(this, str, uploadException, UploadErrorCategory.TOO_MANY_REQUESTS, null, null, 24, null);
        }
        UploadErrorCode uploadErrorCode = CdsErrorCodesKt.toUploadErrorCode(str);
        if (uploadErrorCode == null) {
            return getUploadResponseForCdsErrors(str, uploadException);
        }
        return getUploadResponseForUploadErrors(str, uploadRequest, uploadErrorCode, uploadException);
    }

    private final UploadResponse getUploadResponseForCdsErrors(String str, Throwable th) {
        if (Intrinsics.areEqual(str, CDSErrorCode.APP_ID_DOES_NOT_HAVE_ACCESS.name())) {
            return uploadResponseFailure$default(this, str, th, UploadErrorCategory.QUOTA_ERROR_UNKNOWN, null, GeneratedOutlineSupport1.outline72("UNEXPECTED_SERVICE_ERROR:", str), 8, null);
        }
        if (Intrinsics.areEqual(str, CdsErrorCodes.internalServerError)) {
            return uploadResponseFailure$default(this, str, th, UploadErrorCategory.OTHER_KNOWN_ERROR, null, GeneratedOutlineSupport1.outline72("UNEXPECTED_SERVICE_ERROR:", str), 8, null);
        }
        if (Intrinsics.areEqual(str, CdsErrorCodes.nodeNotAvailableForUpdate)) {
            return uploadResponseFailure$default(this, str, th, UploadErrorCategory.INVALID_PARAMETER, null, null, 24, null);
        }
        return uploadResponseFailure(str, th, UploadErrorCategory.UNKNOWN_ERROR, null, GeneratedOutlineSupport1.outline72("UNEXPECTED_SERVICE_ERROR:", str));
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00d1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.amazon.photos.uploader.UploadResponse getUploadResponseForUploadErrors(java.lang.String r40, com.amazon.photos.uploader.UploadRequest r41, com.amazon.clouddrive.cdasdk.cdus.UploadErrorCode r42, java.lang.Throwable r43) {
        /*
            Method dump skipped, instructions count: 460
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.uploader.cds.error.CdsUploadErrorResolver.getUploadResponseForUploadErrors(java.lang.String, com.amazon.photos.uploader.UploadRequest, com.amazon.clouddrive.cdasdk.cdus.UploadErrorCode, java.lang.Throwable):com.amazon.photos.uploader.UploadResponse");
    }

    private final UploadResponse uploadResponseFailure(String str, Throwable th, UploadErrorCategory uploadErrorCategory, UploadRequest uploadRequest, final String str2) {
        if (str2 != null) {
            this.metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.cds.error.CdsUploadErrorResolver$uploadResponseFailure$$inlined$let$lambda$1
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                @NotNull
                public final String getEventName() {
                    return str2;
                }
            }, new MetricRecordingType[0]);
        }
        if (NO_RETRY_ERROR_CATEGORIES.contains(uploadErrorCategory)) {
            return new UploadResponse.NoRetryFailure(str, th, uploadErrorCategory);
        }
        return new UploadResponse.Failure(str, th, uploadErrorCategory, uploadRequest, false, 16, null);
    }

    static /* synthetic */ UploadResponse uploadResponseFailure$default(CdsUploadErrorResolver cdsUploadErrorResolver, String str, Throwable th, UploadErrorCategory uploadErrorCategory, UploadRequest uploadRequest, String str2, int i, Object obj) {
        return cdsUploadErrorResolver.uploadResponseFailure(str, th, uploadErrorCategory, (i & 8) != 0 ? null : uploadRequest, (i & 16) != 0 ? null : str2);
    }

    @WorkerThread
    @NotNull
    public final UploadResponse resolve(@NotNull UploadRequest request, @NotNull CDUSException ex) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(ex, "ex");
        CDUSError cdusError = ex.getCdusError();
        Intrinsics.checkExpressionValueIsNotNull(cdusError, "ex.cdusError");
        String errorCode = cdusError.getErrorCode();
        if (errorCode == null) {
            errorCode = UploadResponse.UNKNOWN_UPLOAD_ERROR;
        }
        String str = errorCode;
        CDUSError cdusError2 = ex.getCdusError();
        Intrinsics.checkExpressionValueIsNotNull(cdusError2, "ex.cdusError");
        CDUSErrorDetails errorDetails = cdusError2.getErrorDetails();
        List<String> conflictNodeIds = errorDetails != null ? errorDetails.getConflictNodeIds() : null;
        CDUSError cdusError3 = ex.getCdusError();
        Intrinsics.checkExpressionValueIsNotNull(cdusError3, "ex.cdusError");
        UploadException uploadException = new UploadException(cdusError3.getMessage(), str, ex.getCode(), null, 8, null);
        if (!(conflictNodeIds == null || conflictNodeIds.isEmpty())) {
            uploadException.setConflictNodeId(conflictNodeIds.get(0));
        }
        return resolve(request, uploadException);
    }

    @WorkerThread
    @NotNull
    public final UploadResponse resolve(@NotNull UploadRequest request, @NotNull final UploadException ex) {
        UploadResponse uploadResponse;
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(ex, "ex");
        boolean z = false;
        this.metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.cds.error.CdsUploadErrorResolver$resolve$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error:");
                outline107.append(UploadException.this.getErrorCode());
                return outline107.toString();
            }
        }, new MetricRecordingType[0]);
        String conflictNodeId = ex.getConflictNodeId();
        if (conflictNodeId == null || conflictNodeId.length() == 0) {
            z = true;
        }
        if (!z) {
            uploadResponse = this.conflictResolver.resolveConflict(request, conflictNodeId, ex);
        } else {
            uploadResponse = getUploadResponse(ex, ex.getErrorCode(), request);
        }
        if (uploadResponse instanceof UploadResponse.Failure) {
            UploadResponse.Failure failure = (UploadResponse.Failure) uploadResponse;
            this.cdsUploadNotifier.onUploadFailed$AndroidPhotosUploader_release(request, failure.getEx(), failure.getErrorCategory());
        } else if (uploadResponse instanceof UploadResponse.NoRetryFailure) {
            UploadResponse.NoRetryFailure noRetryFailure = (UploadResponse.NoRetryFailure) uploadResponse;
            this.cdsUploadNotifier.onUploadFailed$AndroidPhotosUploader_release(request, noRetryFailure.getEx(), noRetryFailure.getErrorCategory());
        }
        return uploadResponse;
    }
}
