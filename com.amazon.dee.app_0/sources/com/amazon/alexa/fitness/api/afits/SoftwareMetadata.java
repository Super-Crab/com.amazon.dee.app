package com.amazon.alexa.fitness.api.afits;

import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AfitsTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/fitness/api/afits/SoftwareMetadata;", "Ljava/io/Serializable;", MetricsConfiguration.PLATFORM, "", "platformVersion", "version", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getPlatform", "()Ljava/lang/String;", "getPlatformVersion", "getVersion", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SoftwareMetadata implements Serializable {
    @Nullable
    private final String platform;
    @Nullable
    private final String platformVersion;
    @Nullable
    private final String version;

    public SoftwareMetadata(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.platform = str;
        this.platformVersion = str2;
        this.version = str3;
    }

    public static /* synthetic */ SoftwareMetadata copy$default(SoftwareMetadata softwareMetadata, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = softwareMetadata.platform;
        }
        if ((i & 2) != 0) {
            str2 = softwareMetadata.platformVersion;
        }
        if ((i & 4) != 0) {
            str3 = softwareMetadata.version;
        }
        return softwareMetadata.copy(str, str2, str3);
    }

    @Nullable
    public final String component1() {
        return this.platform;
    }

    @Nullable
    public final String component2() {
        return this.platformVersion;
    }

    @Nullable
    public final String component3() {
        return this.version;
    }

    @NotNull
    public final SoftwareMetadata copy(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        return new SoftwareMetadata(str, str2, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof SoftwareMetadata)) {
                return false;
            }
            SoftwareMetadata softwareMetadata = (SoftwareMetadata) obj;
            return Intrinsics.areEqual(this.platform, softwareMetadata.platform) && Intrinsics.areEqual(this.platformVersion, softwareMetadata.platformVersion) && Intrinsics.areEqual(this.version, softwareMetadata.version);
        }
        return true;
    }

    @Nullable
    public final String getPlatform() {
        return this.platform;
    }

    @Nullable
    public final String getPlatformVersion() {
        return this.platformVersion;
    }

    @Nullable
    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        String str = this.platform;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.platformVersion;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.version;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SoftwareMetadata(platform=");
        outline107.append(this.platform);
        outline107.append(", platformVersion=");
        outline107.append(this.platformVersion);
        outline107.append(", version=");
        return GeneratedOutlineSupport1.outline91(outline107, this.version, ")");
    }
}
