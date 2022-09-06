package com.amazon.alexa.fitness.sdk;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SampleDataModels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001e"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/Sensor;", "Ljava/io/Serializable;", "id", "", "manufacturer", "sampleType", "Lcom/amazon/alexa/fitness/sdk/SampleType;", "osVersion", "softwareVersion", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/alexa/fitness/sdk/SampleType;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getManufacturer", "getOsVersion", "getSampleType", "()Lcom/amazon/alexa/fitness/sdk/SampleType;", "getSoftwareVersion", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Sensor implements Serializable {
    @NotNull
    private final String id;
    @NotNull
    private final String manufacturer;
    @NotNull
    private final String osVersion;
    @NotNull
    private final SampleType sampleType;
    @NotNull
    private final String softwareVersion;

    public Sensor(@NotNull String id, @NotNull String manufacturer, @NotNull SampleType sampleType, @NotNull String osVersion, @NotNull String softwareVersion) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(manufacturer, "manufacturer");
        Intrinsics.checkParameterIsNotNull(sampleType, "sampleType");
        Intrinsics.checkParameterIsNotNull(osVersion, "osVersion");
        Intrinsics.checkParameterIsNotNull(softwareVersion, "softwareVersion");
        this.id = id;
        this.manufacturer = manufacturer;
        this.sampleType = sampleType;
        this.osVersion = osVersion;
        this.softwareVersion = softwareVersion;
    }

    public static /* synthetic */ Sensor copy$default(Sensor sensor, String str, String str2, SampleType sampleType, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sensor.id;
        }
        if ((i & 2) != 0) {
            str2 = sensor.manufacturer;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            sampleType = sensor.sampleType;
        }
        SampleType sampleType2 = sampleType;
        if ((i & 8) != 0) {
            str3 = sensor.osVersion;
        }
        String str6 = str3;
        if ((i & 16) != 0) {
            str4 = sensor.softwareVersion;
        }
        return sensor.copy(str, str5, sampleType2, str6, str4);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.manufacturer;
    }

    @NotNull
    public final SampleType component3() {
        return this.sampleType;
    }

    @NotNull
    public final String component4() {
        return this.osVersion;
    }

    @NotNull
    public final String component5() {
        return this.softwareVersion;
    }

    @NotNull
    public final Sensor copy(@NotNull String id, @NotNull String manufacturer, @NotNull SampleType sampleType, @NotNull String osVersion, @NotNull String softwareVersion) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(manufacturer, "manufacturer");
        Intrinsics.checkParameterIsNotNull(sampleType, "sampleType");
        Intrinsics.checkParameterIsNotNull(osVersion, "osVersion");
        Intrinsics.checkParameterIsNotNull(softwareVersion, "softwareVersion");
        return new Sensor(id, manufacturer, sampleType, osVersion, softwareVersion);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Sensor)) {
                return false;
            }
            Sensor sensor = (Sensor) obj;
            return Intrinsics.areEqual(this.id, sensor.id) && Intrinsics.areEqual(this.manufacturer, sensor.manufacturer) && Intrinsics.areEqual(this.sampleType, sensor.sampleType) && Intrinsics.areEqual(this.osVersion, sensor.osVersion) && Intrinsics.areEqual(this.softwareVersion, sensor.softwareVersion);
        }
        return true;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final String getManufacturer() {
        return this.manufacturer;
    }

    @NotNull
    public final String getOsVersion() {
        return this.osVersion;
    }

    @NotNull
    public final SampleType getSampleType() {
        return this.sampleType;
    }

    @NotNull
    public final String getSoftwareVersion() {
        return this.softwareVersion;
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.manufacturer;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        SampleType sampleType = this.sampleType;
        int hashCode3 = (hashCode2 + (sampleType != null ? sampleType.hashCode() : 0)) * 31;
        String str3 = this.osVersion;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.softwareVersion;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return hashCode4 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Sensor(id=");
        outline107.append(this.id);
        outline107.append(", manufacturer=");
        outline107.append(this.manufacturer);
        outline107.append(", sampleType=");
        outline107.append(this.sampleType);
        outline107.append(", osVersion=");
        outline107.append(this.osVersion);
        outline107.append(", softwareVersion=");
        return GeneratedOutlineSupport1.outline91(outline107, this.softwareVersion, ")");
    }
}
