package com.here.sdk.core.engine;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class SDKVersion {
    @NonNull
    public String productVariant;
    public int versionBuild;
    public int versionGeneration;
    public int versionMajor;
    public int versionMinor;
    @NonNull
    public String versionName;
    public int versionPatch;
    @NonNull
    public String versionTag;

    public SDKVersion(@NonNull String str, @NonNull String str2, int i, int i2, int i3, int i4, int i5, @NonNull String str3) {
        this.productVariant = str;
        this.versionName = str2;
        this.versionGeneration = i;
        this.versionMajor = i2;
        this.versionMinor = i3;
        this.versionPatch = i4;
        this.versionBuild = i5;
        this.versionTag = str3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SDKVersion)) {
            return false;
        }
        SDKVersion sDKVersion = (SDKVersion) obj;
        return Objects.equals(this.productVariant, sDKVersion.productVariant) && Objects.equals(this.versionName, sDKVersion.versionName) && this.versionGeneration == sDKVersion.versionGeneration && this.versionMajor == sDKVersion.versionMajor && this.versionMinor == sDKVersion.versionMinor && this.versionPatch == sDKVersion.versionPatch && this.versionBuild == sDKVersion.versionBuild && Objects.equals(this.versionTag, sDKVersion.versionTag);
    }

    public int hashCode() {
        String str = this.productVariant;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        String str2 = this.versionName;
        int hashCode2 = (((((((((((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.versionGeneration) * 31) + this.versionMajor) * 31) + this.versionMinor) * 31) + this.versionPatch) * 31) + this.versionBuild) * 31;
        String str3 = this.versionTag;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }
}
