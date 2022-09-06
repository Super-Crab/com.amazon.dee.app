package com.amazon.photos.uploader.cds;

import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.clouddrive.cdasdk.cdus.UploadContentRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UploadDataPair.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/amazon/photos/uploader/cds/UploadDataPair;", "", SierraContentProviderContract.MD5_VALUE, "", "uploadContentRequest", "Lcom/amazon/clouddrive/cdasdk/cdus/UploadContentRequest;", "(Ljava/lang/String;Lcom/amazon/clouddrive/cdasdk/cdus/UploadContentRequest;)V", "getMd5", "()Ljava/lang/String;", "getUploadContentRequest", "()Lcom/amazon/clouddrive/cdasdk/cdus/UploadContentRequest;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadDataPair {
    @NotNull
    private final String md5;
    @NotNull
    private final UploadContentRequest uploadContentRequest;

    public UploadDataPair(@NotNull String md5, @NotNull UploadContentRequest uploadContentRequest) {
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(uploadContentRequest, "uploadContentRequest");
        this.md5 = md5;
        this.uploadContentRequest = uploadContentRequest;
    }

    public static /* synthetic */ UploadDataPair copy$default(UploadDataPair uploadDataPair, String str, UploadContentRequest uploadContentRequest, int i, Object obj) {
        if ((i & 1) != 0) {
            str = uploadDataPair.md5;
        }
        if ((i & 2) != 0) {
            uploadContentRequest = uploadDataPair.uploadContentRequest;
        }
        return uploadDataPair.copy(str, uploadContentRequest);
    }

    @NotNull
    public final String component1() {
        return this.md5;
    }

    @NotNull
    public final UploadContentRequest component2() {
        return this.uploadContentRequest;
    }

    @NotNull
    public final UploadDataPair copy(@NotNull String md5, @NotNull UploadContentRequest uploadContentRequest) {
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(uploadContentRequest, "uploadContentRequest");
        return new UploadDataPair(md5, uploadContentRequest);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof UploadDataPair)) {
                return false;
            }
            UploadDataPair uploadDataPair = (UploadDataPair) obj;
            return Intrinsics.areEqual(this.md5, uploadDataPair.md5) && Intrinsics.areEqual(this.uploadContentRequest, uploadDataPair.uploadContentRequest);
        }
        return true;
    }

    @NotNull
    public final String getMd5() {
        return this.md5;
    }

    @NotNull
    public final UploadContentRequest getUploadContentRequest() {
        return this.uploadContentRequest;
    }

    public int hashCode() {
        String str = this.md5;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        UploadContentRequest uploadContentRequest = this.uploadContentRequest;
        if (uploadContentRequest != null) {
            i = uploadContentRequest.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UploadDataPair(md5=");
        outline107.append(this.md5);
        outline107.append(", uploadContentRequest=");
        outline107.append(this.uploadContentRequest);
        outline107.append(")");
        return outline107.toString();
    }
}
