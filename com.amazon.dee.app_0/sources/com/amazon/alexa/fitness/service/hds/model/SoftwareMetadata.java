package com.amazon.alexa.fitness.service.hds.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HdsDataModel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0017"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/SoftwareMetadata;", "", "version", "", MetricsConfiguration.PLATFORM, "platformVersion", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getPlatform", "()Ljava/lang/String;", "getPlatformVersion", "getVersion", "buildSoftwareMetadataInput", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SoftwareMetadata {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final String outputFieldSelection = "softwareMetadata {\nversion\nplatform\nplatformVersion\n}";
    @Nullable
    private final String platform;
    @Nullable
    private final String platformVersion;
    @Nullable
    private final String version;

    /* compiled from: HdsDataModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/SoftwareMetadata$Companion;", "", "()V", "outputFieldSelection", "", "getOutputFieldSelection", "()Ljava/lang/String;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final String getOutputFieldSelection() {
            return SoftwareMetadata.outputFieldSelection;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SoftwareMetadata(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.version = str;
        this.platform = str2;
        this.platformVersion = str3;
    }

    public static /* synthetic */ SoftwareMetadata copy$default(SoftwareMetadata softwareMetadata, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = softwareMetadata.version;
        }
        if ((i & 2) != 0) {
            str2 = softwareMetadata.platform;
        }
        if ((i & 4) != 0) {
            str3 = softwareMetadata.platformVersion;
        }
        return softwareMetadata.copy(str, str2, str3);
    }

    @NotNull
    public final String buildSoftwareMetadataInput() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (this.version != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("version:\"");
            outline1072.append(this.version);
            outline1072.append("\",");
            outline107.append(outline1072.toString());
        }
        if (this.platform != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("platform:\"");
            outline1073.append(this.platform);
            outline1073.append("\",");
            outline107.append(outline1073.toString());
        }
        if (this.platformVersion != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("platformVersion:\"");
            outline1074.append(this.platformVersion);
            outline1074.append("\",");
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        String sb = outline107.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "gqlBuilder.toString()");
        return sb;
    }

    @Nullable
    public final String component1() {
        return this.version;
    }

    @Nullable
    public final String component2() {
        return this.platform;
    }

    @Nullable
    public final String component3() {
        return this.platformVersion;
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
            return Intrinsics.areEqual(this.version, softwareMetadata.version) && Intrinsics.areEqual(this.platform, softwareMetadata.platform) && Intrinsics.areEqual(this.platformVersion, softwareMetadata.platformVersion);
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
        String str = this.version;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.platform;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.platformVersion;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SoftwareMetadata(version=");
        outline107.append(this.version);
        outline107.append(", platform=");
        outline107.append(this.platform);
        outline107.append(", platformVersion=");
        return GeneratedOutlineSupport1.outline91(outline107, this.platformVersion, ")");
    }
}
