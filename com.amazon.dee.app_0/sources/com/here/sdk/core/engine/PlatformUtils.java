package com.here.sdk.core.engine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.imageutils.JfifUtil;
import com.here.NativeBase;
import java.util.Objects;
/* loaded from: classes3.dex */
class PlatformUtils extends NativeBase {

    /* loaded from: classes3.dex */
    static final class PlatformInformation {
        @Nullable
        String assetsPath;
        @NonNull
        String cachePath;
        @NonNull
        String platformName;
        @NonNull
        String platformVersion;
        @NonNull
        String privateStoragePath;

        PlatformInformation(@Nullable String str, @NonNull String str2) {
            this.platformName = "Unknown";
            this.platformVersion = "Unknown";
            this.privateStoragePath = "Unknown";
            this.assetsPath = str;
            this.cachePath = str2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public PlatformInformation(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, @NonNull String str5) {
            this.platformName = str;
            this.platformVersion = str2;
            this.privateStoragePath = str3;
            this.assetsPath = str4;
            this.cachePath = str5;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PlatformInformation)) {
                return false;
            }
            PlatformInformation platformInformation = (PlatformInformation) obj;
            return Objects.equals(this.platformName, platformInformation.platformName) && Objects.equals(this.platformVersion, platformInformation.platformVersion) && Objects.equals(this.privateStoragePath, platformInformation.privateStoragePath) && Objects.equals(this.assetsPath, platformInformation.assetsPath) && Objects.equals(this.cachePath, platformInformation.cachePath);
        }

        public int hashCode() {
            String str = this.platformName;
            int i = 0;
            int hashCode = ((str != null ? str.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
            String str2 = this.platformVersion;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.privateStoragePath;
            int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.assetsPath;
            int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
            String str5 = this.cachePath;
            if (str5 != null) {
                i = str5.hashCode();
            }
            return hashCode4 + i;
        }
    }

    protected PlatformUtils(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.core.engine.PlatformUtils.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                PlatformUtils.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @NonNull
    static native AssetsLoader getAssetsLoader();

    @NonNull
    static native PlatformInformation getPlatformInformation();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setAssetsLoader(@NonNull AssetsLoader assetsLoader);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setLocaleFactory(@NonNull LocaleFactory localeFactory);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setPlatformInformation(@NonNull PlatformInformation platformInformation);
}
