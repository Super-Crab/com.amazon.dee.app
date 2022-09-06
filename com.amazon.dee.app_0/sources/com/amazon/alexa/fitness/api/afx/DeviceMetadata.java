package com.amazon.alexa.fitness.api.afx;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AFITSTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003JE\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/DeviceMetadata;", "", "model", "", "manufacturer", "softwareVersion", "firmwareVersion", "hardwareVersion", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFirmwareVersion", "()Ljava/lang/String;", "getHardwareVersion", "getManufacturer", "getModel", "getSoftwareVersion", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DeviceMetadata {
    @Nullable
    private final String firmwareVersion;
    @Nullable
    private final String hardwareVersion;
    @Nullable
    private final String manufacturer;
    @Nullable
    private final String model;
    @Nullable
    private final String softwareVersion;

    public DeviceMetadata(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        this.model = str;
        this.manufacturer = str2;
        this.softwareVersion = str3;
        this.firmwareVersion = str4;
        this.hardwareVersion = str5;
    }

    public static /* synthetic */ DeviceMetadata copy$default(DeviceMetadata deviceMetadata, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = deviceMetadata.model;
        }
        if ((i & 2) != 0) {
            str2 = deviceMetadata.manufacturer;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = deviceMetadata.softwareVersion;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = deviceMetadata.firmwareVersion;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = deviceMetadata.hardwareVersion;
        }
        return deviceMetadata.copy(str, str6, str7, str8, str5);
    }

    @Nullable
    public final String component1() {
        return this.model;
    }

    @Nullable
    public final String component2() {
        return this.manufacturer;
    }

    @Nullable
    public final String component3() {
        return this.softwareVersion;
    }

    @Nullable
    public final String component4() {
        return this.firmwareVersion;
    }

    @Nullable
    public final String component5() {
        return this.hardwareVersion;
    }

    @NotNull
    public final DeviceMetadata copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        return new DeviceMetadata(str, str2, str3, str4, str5);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof DeviceMetadata)) {
                return false;
            }
            DeviceMetadata deviceMetadata = (DeviceMetadata) obj;
            return Intrinsics.areEqual(this.model, deviceMetadata.model) && Intrinsics.areEqual(this.manufacturer, deviceMetadata.manufacturer) && Intrinsics.areEqual(this.softwareVersion, deviceMetadata.softwareVersion) && Intrinsics.areEqual(this.firmwareVersion, deviceMetadata.firmwareVersion) && Intrinsics.areEqual(this.hardwareVersion, deviceMetadata.hardwareVersion);
        }
        return true;
    }

    @Nullable
    public final String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    @Nullable
    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    @Nullable
    public final String getManufacturer() {
        return this.manufacturer;
    }

    @Nullable
    public final String getModel() {
        return this.model;
    }

    @Nullable
    public final String getSoftwareVersion() {
        return this.softwareVersion;
    }

    public int hashCode() {
        String str = this.model;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.manufacturer;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.softwareVersion;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.firmwareVersion;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.hardwareVersion;
        if (str5 != null) {
            i = str5.hashCode();
        }
        return hashCode4 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceMetadata(model=");
        outline107.append(this.model);
        outline107.append(", manufacturer=");
        outline107.append(this.manufacturer);
        outline107.append(", softwareVersion=");
        outline107.append(this.softwareVersion);
        outline107.append(", firmwareVersion=");
        outline107.append(this.firmwareVersion);
        outline107.append(", hardwareVersion=");
        return GeneratedOutlineSupport1.outline91(outline107, this.hardwareVersion, ")");
    }
}
