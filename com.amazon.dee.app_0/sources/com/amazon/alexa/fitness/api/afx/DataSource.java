package com.amazon.alexa.fitness.api.afx;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AFITSTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÆ\u0003J-\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/DataSource;", "", "type", "", "identifier", "deviceMetadata", "Lcom/amazon/alexa/fitness/api/afx/DeviceMetadata;", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/alexa/fitness/api/afx/DeviceMetadata;)V", "getDeviceMetadata", "()Lcom/amazon/alexa/fitness/api/afx/DeviceMetadata;", "getIdentifier", "()Ljava/lang/String;", "getType", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DataSource {
    @Nullable
    private final DeviceMetadata deviceMetadata;
    @Nullable
    private final String identifier;
    @Nullable
    private final String type;

    public DataSource(@Nullable String str, @Nullable String str2, @Nullable DeviceMetadata deviceMetadata) {
        this.type = str;
        this.identifier = str2;
        this.deviceMetadata = deviceMetadata;
    }

    public static /* synthetic */ DataSource copy$default(DataSource dataSource, String str, String str2, DeviceMetadata deviceMetadata, int i, Object obj) {
        if ((i & 1) != 0) {
            str = dataSource.type;
        }
        if ((i & 2) != 0) {
            str2 = dataSource.identifier;
        }
        if ((i & 4) != 0) {
            deviceMetadata = dataSource.deviceMetadata;
        }
        return dataSource.copy(str, str2, deviceMetadata);
    }

    @Nullable
    public final String component1() {
        return this.type;
    }

    @Nullable
    public final String component2() {
        return this.identifier;
    }

    @Nullable
    public final DeviceMetadata component3() {
        return this.deviceMetadata;
    }

    @NotNull
    public final DataSource copy(@Nullable String str, @Nullable String str2, @Nullable DeviceMetadata deviceMetadata) {
        return new DataSource(str, str2, deviceMetadata);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof DataSource)) {
                return false;
            }
            DataSource dataSource = (DataSource) obj;
            return Intrinsics.areEqual(this.type, dataSource.type) && Intrinsics.areEqual(this.identifier, dataSource.identifier) && Intrinsics.areEqual(this.deviceMetadata, dataSource.deviceMetadata);
        }
        return true;
    }

    @Nullable
    public final DeviceMetadata getDeviceMetadata() {
        return this.deviceMetadata;
    }

    @Nullable
    public final String getIdentifier() {
        return this.identifier;
    }

    @Nullable
    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        String str = this.type;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.identifier;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        DeviceMetadata deviceMetadata = this.deviceMetadata;
        if (deviceMetadata != null) {
            i = deviceMetadata.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DataSource(type=");
        outline107.append(this.type);
        outline107.append(", identifier=");
        outline107.append(this.identifier);
        outline107.append(", deviceMetadata=");
        outline107.append(this.deviceMetadata);
        outline107.append(")");
        return outline107.toString();
    }
}
