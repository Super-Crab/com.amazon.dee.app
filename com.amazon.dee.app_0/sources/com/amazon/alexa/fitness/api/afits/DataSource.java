package com.amazon.alexa.fitness.api.afits;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AfitsTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001e"}, d2 = {"Lcom/amazon/alexa/fitness/api/afits/DataSource;", "Ljava/io/Serializable;", "id", "", "type", "deviceMetadata", "Lcom/amazon/alexa/fitness/api/afits/DeviceMetadata;", "softwareMetadata", "Lcom/amazon/alexa/fitness/api/afits/SoftwareMetadata;", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/alexa/fitness/api/afits/DeviceMetadata;Lcom/amazon/alexa/fitness/api/afits/SoftwareMetadata;)V", "getDeviceMetadata", "()Lcom/amazon/alexa/fitness/api/afits/DeviceMetadata;", "getId", "()Ljava/lang/String;", "getSoftwareMetadata", "()Lcom/amazon/alexa/fitness/api/afits/SoftwareMetadata;", "getType", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DataSource implements Serializable {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final DeviceMetadata deviceMetadata;
    @NotNull
    private final String id;
    @NotNull
    private final SoftwareMetadata softwareMetadata;
    @NotNull
    private final String type;

    /* compiled from: AfitsTypes.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/api/afits/DataSource$Companion;", "", "()V", "default", "Lcom/amazon/alexa/fitness/api/afits/DataSource;", "getDefault", "()Lcom/amazon/alexa/fitness/api/afits/DataSource;", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final DataSource getDefault() {
            return new DataSource("", "", new DeviceMetadata("", "", "", ""), new SoftwareMetadata("", "", ""));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public DataSource(@NotNull String id, @NotNull String type, @NotNull DeviceMetadata deviceMetadata, @NotNull SoftwareMetadata softwareMetadata) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(deviceMetadata, "deviceMetadata");
        Intrinsics.checkParameterIsNotNull(softwareMetadata, "softwareMetadata");
        this.id = id;
        this.type = type;
        this.deviceMetadata = deviceMetadata;
        this.softwareMetadata = softwareMetadata;
    }

    public static /* synthetic */ DataSource copy$default(DataSource dataSource, String str, String str2, DeviceMetadata deviceMetadata, SoftwareMetadata softwareMetadata, int i, Object obj) {
        if ((i & 1) != 0) {
            str = dataSource.id;
        }
        if ((i & 2) != 0) {
            str2 = dataSource.type;
        }
        if ((i & 4) != 0) {
            deviceMetadata = dataSource.deviceMetadata;
        }
        if ((i & 8) != 0) {
            softwareMetadata = dataSource.softwareMetadata;
        }
        return dataSource.copy(str, str2, deviceMetadata, softwareMetadata);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.type;
    }

    @NotNull
    public final DeviceMetadata component3() {
        return this.deviceMetadata;
    }

    @NotNull
    public final SoftwareMetadata component4() {
        return this.softwareMetadata;
    }

    @NotNull
    public final DataSource copy(@NotNull String id, @NotNull String type, @NotNull DeviceMetadata deviceMetadata, @NotNull SoftwareMetadata softwareMetadata) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(deviceMetadata, "deviceMetadata");
        Intrinsics.checkParameterIsNotNull(softwareMetadata, "softwareMetadata");
        return new DataSource(id, type, deviceMetadata, softwareMetadata);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof DataSource)) {
                return false;
            }
            DataSource dataSource = (DataSource) obj;
            return Intrinsics.areEqual(this.id, dataSource.id) && Intrinsics.areEqual(this.type, dataSource.type) && Intrinsics.areEqual(this.deviceMetadata, dataSource.deviceMetadata) && Intrinsics.areEqual(this.softwareMetadata, dataSource.softwareMetadata);
        }
        return true;
    }

    @NotNull
    public final DeviceMetadata getDeviceMetadata() {
        return this.deviceMetadata;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final SoftwareMetadata getSoftwareMetadata() {
        return this.softwareMetadata;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.type;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        DeviceMetadata deviceMetadata = this.deviceMetadata;
        int hashCode3 = (hashCode2 + (deviceMetadata != null ? deviceMetadata.hashCode() : 0)) * 31;
        SoftwareMetadata softwareMetadata = this.softwareMetadata;
        if (softwareMetadata != null) {
            i = softwareMetadata.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DataSource(id=");
        outline107.append(this.id);
        outline107.append(", type=");
        outline107.append(this.type);
        outline107.append(", deviceMetadata=");
        outline107.append(this.deviceMetadata);
        outline107.append(", softwareMetadata=");
        outline107.append(this.softwareMetadata);
        outline107.append(")");
        return outline107.toString();
    }
}
