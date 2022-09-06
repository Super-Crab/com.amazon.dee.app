package com.amazon.photos.uploader;

import amazon.speech.simclient.settings.Settings;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UploadResponse.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00042\u00020\u0001:\u0006\u0003\u0004\u0005\u0006\u0007\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0005\t\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lcom/amazon/photos/uploader/UploadResponse;", "", "()V", "AcceptableFailure", "Companion", "Failure", "NoRetryFailure", "NoWorkRequired", "Success", "Lcom/amazon/photos/uploader/UploadResponse$Success;", "Lcom/amazon/photos/uploader/UploadResponse$Failure;", "Lcom/amazon/photos/uploader/UploadResponse$NoRetryFailure;", "Lcom/amazon/photos/uploader/UploadResponse$AcceptableFailure;", "Lcom/amazon/photos/uploader/UploadResponse$NoWorkRequired;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class UploadResponse {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String UNKNOWN_UPLOAD_ERROR = "UNKNOWN_UPLOAD_ERROR";

    /* compiled from: UploadResponse.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/amazon/photos/uploader/UploadResponse$AcceptableFailure;", "Lcom/amazon/photos/uploader/UploadResponse;", "errorCode", "", "ex", "", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "(Ljava/lang/String;Ljava/lang/Throwable;Lcom/amazon/photos/uploader/UploadErrorCategory;)V", "getErrorCategory", "()Lcom/amazon/photos/uploader/UploadErrorCategory;", "getErrorCode", "()Ljava/lang/String;", "getEx", "()Ljava/lang/Throwable;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class AcceptableFailure extends UploadResponse {
        @NotNull
        private final UploadErrorCategory errorCategory;
        @NotNull
        private final String errorCode;
        @NotNull
        private final Throwable ex;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AcceptableFailure(@NotNull String errorCode, @NotNull Throwable ex, @NotNull UploadErrorCategory errorCategory) {
            super(null);
            Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
            Intrinsics.checkParameterIsNotNull(ex, "ex");
            Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
            this.errorCode = errorCode;
            this.ex = ex;
            this.errorCategory = errorCategory;
        }

        public static /* synthetic */ AcceptableFailure copy$default(AcceptableFailure acceptableFailure, String str, Throwable th, UploadErrorCategory uploadErrorCategory, int i, Object obj) {
            if ((i & 1) != 0) {
                str = acceptableFailure.errorCode;
            }
            if ((i & 2) != 0) {
                th = acceptableFailure.ex;
            }
            if ((i & 4) != 0) {
                uploadErrorCategory = acceptableFailure.errorCategory;
            }
            return acceptableFailure.copy(str, th, uploadErrorCategory);
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

        @NotNull
        public final AcceptableFailure copy(@NotNull String errorCode, @NotNull Throwable ex, @NotNull UploadErrorCategory errorCategory) {
            Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
            Intrinsics.checkParameterIsNotNull(ex, "ex");
            Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
            return new AcceptableFailure(errorCode, ex, errorCategory);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof AcceptableFailure)) {
                    return false;
                }
                AcceptableFailure acceptableFailure = (AcceptableFailure) obj;
                return Intrinsics.areEqual(this.errorCode, acceptableFailure.errorCode) && Intrinsics.areEqual(this.ex, acceptableFailure.ex) && Intrinsics.areEqual(this.errorCategory, acceptableFailure.errorCategory);
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
            Throwable th = this.ex;
            int hashCode2 = (hashCode + (th != null ? th.hashCode() : 0)) * 31;
            UploadErrorCategory uploadErrorCategory = this.errorCategory;
            if (uploadErrorCategory != null) {
                i = uploadErrorCategory.hashCode();
            }
            return hashCode2 + i;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AcceptableFailure(errorCode=");
            outline107.append(this.errorCode);
            outline107.append(", ex=");
            outline107.append(this.ex);
            outline107.append(", errorCategory=");
            outline107.append(this.errorCategory);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* compiled from: UploadResponse.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/UploadResponse$Companion;", "", "()V", UploadResponse.UNKNOWN_UPLOAD_ERROR, "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: UploadResponse.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010\u001b\u001a\u00020\u000bHÆ\u0003J=\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u000b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006#"}, d2 = {"Lcom/amazon/photos/uploader/UploadResponse$Failure;", "Lcom/amazon/photos/uploader/UploadResponse;", "errorCode", "", "ex", "", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "revisedRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "resetAttempts", "", "(Ljava/lang/String;Ljava/lang/Throwable;Lcom/amazon/photos/uploader/UploadErrorCategory;Lcom/amazon/photos/uploader/UploadRequest;Z)V", "getErrorCategory", "()Lcom/amazon/photos/uploader/UploadErrorCategory;", "getErrorCode", "()Ljava/lang/String;", "getEx", "()Ljava/lang/Throwable;", "getResetAttempts", "()Z", "getRevisedRequest", "()Lcom/amazon/photos/uploader/UploadRequest;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "", "hashCode", "", "toString", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Failure extends UploadResponse {
        @NotNull
        private final UploadErrorCategory errorCategory;
        @NotNull
        private final String errorCode;
        @NotNull
        private final Throwable ex;
        private final boolean resetAttempts;
        @Nullable
        private final UploadRequest revisedRequest;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Failure(@NotNull String errorCode, @NotNull Throwable ex, @NotNull UploadErrorCategory errorCategory, @Nullable UploadRequest uploadRequest, boolean z) {
            super(null);
            Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
            Intrinsics.checkParameterIsNotNull(ex, "ex");
            Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
            this.errorCode = errorCode;
            this.ex = ex;
            this.errorCategory = errorCategory;
            this.revisedRequest = uploadRequest;
            this.resetAttempts = z;
        }

        public static /* synthetic */ Failure copy$default(Failure failure, String str, Throwable th, UploadErrorCategory uploadErrorCategory, UploadRequest uploadRequest, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = failure.errorCode;
            }
            if ((i & 2) != 0) {
                th = failure.ex;
            }
            Throwable th2 = th;
            if ((i & 4) != 0) {
                uploadErrorCategory = failure.errorCategory;
            }
            UploadErrorCategory uploadErrorCategory2 = uploadErrorCategory;
            if ((i & 8) != 0) {
                uploadRequest = failure.revisedRequest;
            }
            UploadRequest uploadRequest2 = uploadRequest;
            if ((i & 16) != 0) {
                z = failure.resetAttempts;
            }
            return failure.copy(str, th2, uploadErrorCategory2, uploadRequest2, z);
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
        public final UploadRequest component4() {
            return this.revisedRequest;
        }

        public final boolean component5() {
            return this.resetAttempts;
        }

        @NotNull
        public final Failure copy(@NotNull String errorCode, @NotNull Throwable ex, @NotNull UploadErrorCategory errorCategory, @Nullable UploadRequest uploadRequest, boolean z) {
            Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
            Intrinsics.checkParameterIsNotNull(ex, "ex");
            Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
            return new Failure(errorCode, ex, errorCategory, uploadRequest, z);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof Failure)) {
                    return false;
                }
                Failure failure = (Failure) obj;
                return Intrinsics.areEqual(this.errorCode, failure.errorCode) && Intrinsics.areEqual(this.ex, failure.ex) && Intrinsics.areEqual(this.errorCategory, failure.errorCategory) && Intrinsics.areEqual(this.revisedRequest, failure.revisedRequest) && this.resetAttempts == failure.resetAttempts;
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

        public final boolean getResetAttempts() {
            return this.resetAttempts;
        }

        @Nullable
        public final UploadRequest getRevisedRequest() {
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
            UploadRequest uploadRequest = this.revisedRequest;
            if (uploadRequest != null) {
                i = uploadRequest.hashCode();
            }
            int i2 = (hashCode3 + i) * 31;
            boolean z = this.resetAttempts;
            if (z) {
                z = true;
            }
            int i3 = z ? 1 : 0;
            int i4 = z ? 1 : 0;
            return i2 + i3;
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
            outline107.append(", resetAttempts=");
            return GeneratedOutlineSupport1.outline97(outline107, this.resetAttempts, ")");
        }

        public /* synthetic */ Failure(String str, Throwable th, UploadErrorCategory uploadErrorCategory, UploadRequest uploadRequest, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, th, uploadErrorCategory, (i & 8) != 0 ? null : uploadRequest, (i & 16) != 0 ? false : z);
        }
    }

    /* compiled from: UploadResponse.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/amazon/photos/uploader/UploadResponse$NoRetryFailure;", "Lcom/amazon/photos/uploader/UploadResponse;", "errorCode", "", "ex", "", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "(Ljava/lang/String;Ljava/lang/Throwable;Lcom/amazon/photos/uploader/UploadErrorCategory;)V", "getErrorCategory", "()Lcom/amazon/photos/uploader/UploadErrorCategory;", "getErrorCode", "()Ljava/lang/String;", "getEx", "()Ljava/lang/Throwable;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class NoRetryFailure extends UploadResponse {
        @NotNull
        private final UploadErrorCategory errorCategory;
        @NotNull
        private final String errorCode;
        @NotNull
        private final Throwable ex;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NoRetryFailure(@NotNull String errorCode, @NotNull Throwable ex, @NotNull UploadErrorCategory errorCategory) {
            super(null);
            Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
            Intrinsics.checkParameterIsNotNull(ex, "ex");
            Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
            this.errorCode = errorCode;
            this.ex = ex;
            this.errorCategory = errorCategory;
        }

        public static /* synthetic */ NoRetryFailure copy$default(NoRetryFailure noRetryFailure, String str, Throwable th, UploadErrorCategory uploadErrorCategory, int i, Object obj) {
            if ((i & 1) != 0) {
                str = noRetryFailure.errorCode;
            }
            if ((i & 2) != 0) {
                th = noRetryFailure.ex;
            }
            if ((i & 4) != 0) {
                uploadErrorCategory = noRetryFailure.errorCategory;
            }
            return noRetryFailure.copy(str, th, uploadErrorCategory);
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

        @NotNull
        public final NoRetryFailure copy(@NotNull String errorCode, @NotNull Throwable ex, @NotNull UploadErrorCategory errorCategory) {
            Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
            Intrinsics.checkParameterIsNotNull(ex, "ex");
            Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
            return new NoRetryFailure(errorCode, ex, errorCategory);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof NoRetryFailure)) {
                    return false;
                }
                NoRetryFailure noRetryFailure = (NoRetryFailure) obj;
                return Intrinsics.areEqual(this.errorCode, noRetryFailure.errorCode) && Intrinsics.areEqual(this.ex, noRetryFailure.ex) && Intrinsics.areEqual(this.errorCategory, noRetryFailure.errorCategory);
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
            Throwable th = this.ex;
            int hashCode2 = (hashCode + (th != null ? th.hashCode() : 0)) * 31;
            UploadErrorCategory uploadErrorCategory = this.errorCategory;
            if (uploadErrorCategory != null) {
                i = uploadErrorCategory.hashCode();
            }
            return hashCode2 + i;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NoRetryFailure(errorCode=");
            outline107.append(this.errorCode);
            outline107.append(", ex=");
            outline107.append(this.ex);
            outline107.append(", errorCategory=");
            outline107.append(this.errorCategory);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* compiled from: UploadResponse.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/amazon/photos/uploader/UploadResponse$NoWorkRequired;", "Lcom/amazon/photos/uploader/UploadResponse;", Settings.ListeningSettings.EXTRA_REASON, "Lcom/amazon/photos/uploader/AbandonReason;", "(Lcom/amazon/photos/uploader/AbandonReason;)V", "getReason", "()Lcom/amazon/photos/uploader/AbandonReason;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class NoWorkRequired extends UploadResponse {
        @NotNull
        private final AbandonReason reason;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NoWorkRequired(@NotNull AbandonReason reason) {
            super(null);
            Intrinsics.checkParameterIsNotNull(reason, "reason");
            this.reason = reason;
        }

        public static /* synthetic */ NoWorkRequired copy$default(NoWorkRequired noWorkRequired, AbandonReason abandonReason, int i, Object obj) {
            if ((i & 1) != 0) {
                abandonReason = noWorkRequired.reason;
            }
            return noWorkRequired.copy(abandonReason);
        }

        @NotNull
        public final AbandonReason component1() {
            return this.reason;
        }

        @NotNull
        public final NoWorkRequired copy(@NotNull AbandonReason reason) {
            Intrinsics.checkParameterIsNotNull(reason, "reason");
            return new NoWorkRequired(reason);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                return (obj instanceof NoWorkRequired) && Intrinsics.areEqual(this.reason, ((NoWorkRequired) obj).reason);
            }
            return true;
        }

        @NotNull
        public final AbandonReason getReason() {
            return this.reason;
        }

        public int hashCode() {
            AbandonReason abandonReason = this.reason;
            if (abandonReason != null) {
                return abandonReason.hashCode();
            }
            return 0;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NoWorkRequired(reason=");
            outline107.append(this.reason);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* compiled from: UploadResponse.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0004HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/amazon/photos/uploader/UploadResponse$Success;", "Lcom/amazon/photos/uploader/UploadResponse;", "()V", "resultMetadata", "Lcom/amazon/photos/uploader/ResultMetadata;", "(Lcom/amazon/photos/uploader/ResultMetadata;)V", "getResultMetadata", "()Lcom/amazon/photos/uploader/ResultMetadata;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Success extends UploadResponse {
        @NotNull
        private final ResultMetadata resultMetadata;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Success(@NotNull ResultMetadata resultMetadata) {
            super(null);
            Intrinsics.checkParameterIsNotNull(resultMetadata, "resultMetadata");
            this.resultMetadata = resultMetadata;
        }

        public static /* synthetic */ Success copy$default(Success success, ResultMetadata resultMetadata, int i, Object obj) {
            if ((i & 1) != 0) {
                resultMetadata = success.resultMetadata;
            }
            return success.copy(resultMetadata);
        }

        @NotNull
        public final ResultMetadata component1() {
            return this.resultMetadata;
        }

        @NotNull
        public final Success copy(@NotNull ResultMetadata resultMetadata) {
            Intrinsics.checkParameterIsNotNull(resultMetadata, "resultMetadata");
            return new Success(resultMetadata);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                return (obj instanceof Success) && Intrinsics.areEqual(this.resultMetadata, ((Success) obj).resultMetadata);
            }
            return true;
        }

        @NotNull
        public final ResultMetadata getResultMetadata() {
            return this.resultMetadata;
        }

        public int hashCode() {
            ResultMetadata resultMetadata = this.resultMetadata;
            if (resultMetadata != null) {
                return resultMetadata.hashCode();
            }
            return 0;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Success(resultMetadata=");
            outline107.append(this.resultMetadata);
            outline107.append(")");
            return outline107.toString();
        }

        public Success() {
            this(new ResultMetadata());
        }
    }

    private UploadResponse() {
    }

    public /* synthetic */ UploadResponse(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
