package com.here.sdk.core.engine;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class SDKOptions {
    @NonNull
    public String accessKeyId;
    @NonNull
    public String accessKeySecret;
    @NonNull
    @Deprecated
    public AuthenticationPreferences authenticationPreferences;
    @NonNull
    public String cachePath;
    public long cacheSizeInBytes;
    @NonNull
    public String persistentMapStoragePath;

    public SDKOptions(@NonNull String str) {
        SDKOptions make = make(str);
        this.accessKeyId = make.accessKeyId;
        this.accessKeySecret = make.accessKeySecret;
        this.cachePath = make.cachePath;
        this.cacheSizeInBytes = make.cacheSizeInBytes;
        this.persistentMapStoragePath = make.persistentMapStoragePath;
        this.authenticationPreferences = make.authenticationPreferences;
    }

    public SDKOptions(@NonNull String str, @NonNull String str2) {
        SDKOptions make = make(str, str2);
        this.accessKeyId = make.accessKeyId;
        this.accessKeySecret = make.accessKeySecret;
        this.cachePath = make.cachePath;
        this.cacheSizeInBytes = make.cacheSizeInBytes;
        this.persistentMapStoragePath = make.persistentMapStoragePath;
        this.authenticationPreferences = make.authenticationPreferences;
    }

    public SDKOptions(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        SDKOptions make = make(str, str2, str3);
        this.accessKeyId = make.accessKeyId;
        this.accessKeySecret = make.accessKeySecret;
        this.cachePath = make.cachePath;
        this.cacheSizeInBytes = make.cacheSizeInBytes;
        this.persistentMapStoragePath = make.persistentMapStoragePath;
        this.authenticationPreferences = make.authenticationPreferences;
    }

    public SDKOptions(@NonNull String str, @NonNull String str2, @NonNull String str3, long j) {
        SDKOptions make = make(str, str2, str3, j);
        this.accessKeyId = make.accessKeyId;
        this.accessKeySecret = make.accessKeySecret;
        this.cachePath = make.cachePath;
        this.cacheSizeInBytes = make.cacheSizeInBytes;
        this.persistentMapStoragePath = make.persistentMapStoragePath;
        this.authenticationPreferences = make.authenticationPreferences;
    }

    public SDKOptions(@NonNull String str, @NonNull String str2, @NonNull String str3, long j, @NonNull String str4) {
        SDKOptions make = make(str, str2, str3, j, str4);
        this.accessKeyId = make.accessKeyId;
        this.accessKeySecret = make.accessKeySecret;
        this.cachePath = make.cachePath;
        this.cacheSizeInBytes = make.cacheSizeInBytes;
        this.persistentMapStoragePath = make.persistentMapStoragePath;
        this.authenticationPreferences = make.authenticationPreferences;
    }

    @Deprecated
    public SDKOptions(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull AuthenticationPreferences authenticationPreferences) {
        SDKOptions make = make(str, str2, str3, authenticationPreferences);
        this.accessKeyId = make.accessKeyId;
        this.accessKeySecret = make.accessKeySecret;
        this.cachePath = make.cachePath;
        this.cacheSizeInBytes = make.cacheSizeInBytes;
        this.persistentMapStoragePath = make.persistentMapStoragePath;
        this.authenticationPreferences = make.authenticationPreferences;
    }

    private static native SDKOptions make(@NonNull String str);

    private static native SDKOptions make(@NonNull String str, @NonNull String str2);

    private static native SDKOptions make(@NonNull String str, @NonNull String str2, @NonNull String str3);

    private static native SDKOptions make(@NonNull String str, @NonNull String str2, @NonNull String str3, long j);

    private static native SDKOptions make(@NonNull String str, @NonNull String str2, @NonNull String str3, long j, @NonNull String str4);

    private static native SDKOptions make(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull AuthenticationPreferences authenticationPreferences);

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SDKOptions)) {
            return false;
        }
        SDKOptions sDKOptions = (SDKOptions) obj;
        return Objects.equals(this.accessKeyId, sDKOptions.accessKeyId) && Objects.equals(this.accessKeySecret, sDKOptions.accessKeySecret) && Objects.equals(this.cachePath, sDKOptions.cachePath) && this.cacheSizeInBytes == sDKOptions.cacheSizeInBytes && Objects.equals(this.persistentMapStoragePath, sDKOptions.persistentMapStoragePath) && Objects.equals(this.authenticationPreferences, sDKOptions.authenticationPreferences);
    }

    public int hashCode() {
        String str = this.accessKeyId;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        String str2 = this.accessKeySecret;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.cachePath;
        int hashCode3 = str3 != null ? str3.hashCode() : 0;
        long j = this.cacheSizeInBytes;
        int i2 = (((hashCode2 + hashCode3) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
        String str4 = this.persistentMapStoragePath;
        int hashCode4 = (i2 + (str4 != null ? str4.hashCode() : 0)) * 31;
        AuthenticationPreferences authenticationPreferences = this.authenticationPreferences;
        if (authenticationPreferences != null) {
            i = authenticationPreferences.hashCode();
        }
        return hashCode4 + i;
    }
}
