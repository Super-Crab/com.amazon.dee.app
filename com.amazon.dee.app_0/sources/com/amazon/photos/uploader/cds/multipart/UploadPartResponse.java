package com.amazon.photos.uploader.cds.multipart;

import com.amazon.photos.uploader.UploadErrorCategory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UploadPartResponse.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000 \u00032\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/UploadPartResponse;", "", "()V", "Companion", "Failure", "NoRetryFailure", "Lcom/amazon/photos/uploader/cds/multipart/UploadPartResponse$Failure;", "Lcom/amazon/photos/uploader/cds/multipart/UploadPartResponse$NoRetryFailure;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class UploadPartResponse {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String UNKNOWN_PART_UPLOAD_ERROR = "UNKNOWN_PART_UPLOAD_ERROR";

    /* compiled from: UploadPartResponse.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/UploadPartResponse$Companion;", "", "()V", UploadPartResponse.UNKNOWN_PART_UPLOAD_ERROR, "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: UploadPartResponse.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\tHÆ\u0003J3\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/UploadPartResponse$Failure;", "Lcom/amazon/photos/uploader/cds/multipart/UploadPartResponse;", "errorCode", "", "ex", "", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "revisedRequest", "Lcom/amazon/photos/uploader/cds/multipart/PartInfo;", "(Ljava/lang/String;Ljava/lang/Throwable;Lcom/amazon/photos/uploader/UploadErrorCategory;Lcom/amazon/photos/uploader/cds/multipart/PartInfo;)V", "getErrorCategory", "()Lcom/amazon/photos/uploader/UploadErrorCategory;", "getErrorCode", "()Ljava/lang/String;", "getEx", "()Ljava/lang/Throwable;", "getRevisedRequest", "()Lcom/amazon/photos/uploader/cds/multipart/PartInfo;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Failure extends UploadPartResponse {
        @NotNull
        private final UploadErrorCategory errorCategory;
        @NotNull
        private final String errorCode;
        @NotNull
        private final Throwable ex;
        @Nullable
        private final PartInfo revisedRequest;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Failure(@NotNull String errorCode, @NotNull Throwable ex, @NotNull UploadErrorCategory errorCategory, @Nullable PartInfo partInfo) {
            super(null);
            Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
            Intrinsics.checkParameterIsNotNull(ex, "ex");
            Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
            this.errorCode = errorCode;
            this.ex = ex;
            this.errorCategory = errorCategory;
            this.revisedRequest = partInfo;
        }

        public static /* synthetic */ Failure copy$default(Failure failure, String str, Throwable th, UploadErrorCategory uploadErrorCategory, PartInfo partInfo, int i, Object obj) {
            if ((i & 1) != 0) {
                str = failure.errorCode;
            }
            if ((i & 2) != 0) {
                th = failure.ex;
            }
            if ((i & 4) != 0) {
                uploadErrorCategory = failure.errorCategory;
            }
            if ((i & 8) != 0) {
                partInfo = failure.revisedRequest;
            }
            return failure.copy(str, th, uploadErrorCategory, partInfo);
        }

        @NotNull
        public final String component1() {
            return this.errorCode;
        }

        @NotNull
        public final Throwable component2() {
            return this.ex;
        }

        @NotNull
        public final UploadErrorCategory component3() {
            return this.errorCategory;
        }

        @Nullable
        public final PartInfo component4() {
            return this.revisedRequest;
        }

        @NotNull
        public final Failure copy(@NotNull String errorCode, @NotNull Throwable ex, @NotNull UploadErrorCategory errorCategory, @Nullable PartInfo partInfo) {
            Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
            Intrinsics.checkParameterIsNotNull(ex, "ex");
            Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
            return new Failure(errorCode, ex, errorCategory, partInfo);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof Failure)) {
                    return false;
                }
                Failure failure = (Failure) obj;
                return Intrinsics.areEqual(this.errorCode, failure.errorCode) && Intrinsics.areEqual(this.ex, failure.ex) && Intrinsics.areEqual(this.errorCategory, failure.errorCategory) && Intrinsics.areEqual(this.revisedRequest, failure.revisedRequest);
            }
            return true;
        }

        @NotNull
        public final UploadErrorCategory getErrorCategory() {
            return this.errorCategory;
        }

        @NotNull
        public final String getErrorCode() {
            return this.errorCode;
        }

        @NotNull
        public final Throwable getEx() {
            return this.ex;
        }

        @Nullable
        public final PartInfo getRevisedRequest() {
            return this.revisedRequest;
        }

        public int hashCode() {
            String str = this.errorCode;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            Throwable th = this.ex;
            int hashCode2 = (hashCode + (th != null ? th.hashCode() : 0)) * 31;
            UploadErrorCategory uploadErrorCategory = this.errorCategory;
            int hashCode3 = (hashCode2 + (uploadErrorCategory != null ? uploadErrorCategory.hashCode() : 0)) * 31;
            PartInfo partInfo = this.revisedRequest;
            if (partInfo != null) {
                i = partInfo.hashCode();
            }
            return hashCode3 + i;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failure(errorCode=");
            outline107.append(this.errorCode);
            outline107.append(", ex=");
            outline107.append(this.ex);
            outline107.append(", errorCategory=");
            outline107.append(this.errorCategory);
            outline107.append(", revisedRequest=");
            outline107.append(this.revisedRequest);
            outline107.append(")");
            return outline107.toString();
        }

        public /* synthetic */ Failure(String str, Throwable th, UploadErrorCategory uploadErrorCategory, PartInfo partInfo, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, th, uploadErrorCategory, (i & 8) != 0 ? null : partInfo);
        }
    }

    /* compiled from: UploadPartResponse.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/UploadPartResponse$NoRetryFailure;", "Lcom/amazon/photos/uploader/cds/multipart/UploadPartResponse;", "errorCode", "", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "ex", "", "(Ljava/lang/String;Lcom/amazon/photos/uploader/UploadErrorCategory;Ljava/lang/Throwable;)V", "getErrorCategory", "()Lcom/amazon/photos/uploader/UploadErrorCategory;", "getErrorCode", "()Ljava/lang/String;", "getEx", "()Ljava/lang/Throwable;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class NoRetryFailure extends UploadPartResponse {
        @NotNull
        private final UploadErrorCategory errorCategory;
        @NotNull
        private final String errorCode;
        @NotNull
        private final Throwable ex;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NoRetryFailure(@NotNull String errorCode, @NotNull UploadErrorCategory errorCategory, @NotNull Throwable ex) {
            super(null);
            Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
            Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
            Intrinsics.checkParameterIsNotNull(ex, "ex");
            this.errorCode = errorCode;
            this.errorCategory = errorCategory;
            this.ex = ex;
        }

        public static /* synthetic */ NoRetryFailure copy$default(NoRetryFailure noRetryFailure, String str, UploadErrorCategory uploadErrorCategory, Throwable th, int i, Object obj) {
            if ((i & 1) != 0) {
                str = noRetryFailure.errorCode;
            }
            if ((i & 2) != 0) {
                uploadErrorCategory = noRetryFailure.errorCategory;
            }
            if ((i & 4) != 0) {
                th = noRetryFailure.ex;
            }
            return noRetryFailure.copy(str, uploadErrorCategory, th);
        }

        @NotNull
        public final String component1() {
            return this.errorCode;
        }

        @NotNull
        public final UploadErrorCategory component2() {
            return this.errorCategory;
        }

        @NotNull
        public final Throwable component3() {
            return this.ex;
        }

        @NotNull
        public final NoRetryFailure copy(@NotNull String errorCode, @NotNull UploadErrorCategory errorCategory, @NotNull Throwable ex) {
            Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
            Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
            Intrinsics.checkParameterIsNotNull(ex, "ex");
            return new NoRetryFailure(errorCode, errorCategory, ex);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof NoRetryFailure)) {
                    return false;
                }
                NoRetryFailure noRetryFailure = (NoRetryFailure) obj;
                return Intrinsics.areEqual(this.errorCode, noRetryFailure.errorCode) && Intrinsics.areEqual(this.errorCategory, noRetryFailure.errorCategory) && Intrinsics.areEqual(this.ex, noRetryFailure.ex);
            }
            return true;
        }

        @NotNull
        public final UploadErrorCategory getErrorCategory() {
            return this.errorCategory;
        }

        @NotNull
        public final String getErrorCode() {
            return this.errorCode;
        }

        @NotNull
        public final Throwable getEx() {
            return this.ex;
        }

        public int hashCode() {
            String str = this.errorCode;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            UploadErrorCategory uploadErrorCategory = this.errorCategory;
            int hashCode2 = (hashCode + (uploadErrorCategory != null ? uploadErrorCategory.hashCode() : 0)) * 31;
            Throwable th = this.ex;
            if (th != null) {
                i = th.hashCode();
            }
            return hashCode2 + i;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NoRetryFailure(errorCode=");
            outline107.append(this.errorCode);
            outline107.append(", errorCategory=");
            outline107.append(this.errorCategory);
            outline107.append(", ex=");
            outline107.append(this.ex);
            outline107.append(")");
            return outline107.toString();
        }
    }

    private UploadPartResponse() {
    }

    public /* synthetic */ UploadPartResponse(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
