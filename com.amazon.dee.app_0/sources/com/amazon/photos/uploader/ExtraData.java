package com.amazon.photos.uploader;

import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CrashReporter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/amazon/photos/uploader/ExtraData;", "", SierraContentProviderContract.MD5_VALUE, "", "visualDigest", "filePath", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFilePath", "()Ljava/lang/String;", "setFilePath", "(Ljava/lang/String;)V", "getMd5", "setMd5", "getVisualDigest", "setVisualDigest", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ExtraData {
    @Nullable
    private String filePath;
    @Nullable
    private String md5;
    @Nullable
    private String visualDigest;

    public ExtraData() {
        this(null, null, null, 7, null);
    }

    public ExtraData(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.md5 = str;
        this.visualDigest = str2;
        this.filePath = str3;
    }

    public static /* synthetic */ ExtraData copy$default(ExtraData extraData, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = extraData.md5;
        }
        if ((i & 2) != 0) {
            str2 = extraData.visualDigest;
        }
        if ((i & 4) != 0) {
            str3 = extraData.filePath;
        }
        return extraData.copy(str, str2, str3);
    }

    @Nullable
    public final String component1() {
        return this.md5;
    }

    @Nullable
    public final String component2() {
        return this.visualDigest;
    }

    @Nullable
    public final String component3() {
        return this.filePath;
    }

    @NotNull
    public final ExtraData copy(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        return new ExtraData(str, str2, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof ExtraData)) {
                return false;
            }
            ExtraData extraData = (ExtraData) obj;
            return Intrinsics.areEqual(this.md5, extraData.md5) && Intrinsics.areEqual(this.visualDigest, extraData.visualDigest) && Intrinsics.areEqual(this.filePath, extraData.filePath);
        }
        return true;
    }

    @JsonProperty("file_path")
    @Nullable
    public final String getFilePath() {
        return this.filePath;
    }

    @JsonProperty(SierraContentProviderContract.MD5_VALUE)
    @Nullable
    public final String getMd5() {
        return this.md5;
    }

    @JsonProperty("visual_digest")
    @Nullable
    public final String getVisualDigest() {
        return this.visualDigest;
    }

    public int hashCode() {
        String str = this.md5;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.visualDigest;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.filePath;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    public final void setFilePath(@Nullable String str) {
        this.filePath = str;
    }

    public final void setMd5(@Nullable String str) {
        this.md5 = str;
    }

    public final void setVisualDigest(@Nullable String str) {
        this.visualDigest = str;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ExtraData(md5=");
        outline107.append(this.md5);
        outline107.append(", visualDigest=");
        outline107.append(this.visualDigest);
        outline107.append(", filePath=");
        return GeneratedOutlineSupport1.outline91(outline107, this.filePath, ")");
    }

    public /* synthetic */ ExtraData(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
    }
}
