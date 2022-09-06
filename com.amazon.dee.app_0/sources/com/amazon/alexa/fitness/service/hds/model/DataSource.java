package com.amazon.alexa.fitness.service.hds.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HdsDataModel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u0013\u001a\u00020\u0005J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\tHÆ\u0003J5\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006 "}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/DataSource;", "", "type", "Lcom/amazon/alexa/fitness/service/hds/model/DataSourceType;", "identifier", "", "deviceMetadata", "Lcom/amazon/alexa/fitness/service/hds/model/DeviceMetadata;", "softwareMetadata", "Lcom/amazon/alexa/fitness/service/hds/model/SoftwareMetadata;", "(Lcom/amazon/alexa/fitness/service/hds/model/DataSourceType;Ljava/lang/String;Lcom/amazon/alexa/fitness/service/hds/model/DeviceMetadata;Lcom/amazon/alexa/fitness/service/hds/model/SoftwareMetadata;)V", "getDeviceMetadata", "()Lcom/amazon/alexa/fitness/service/hds/model/DeviceMetadata;", "getIdentifier", "()Ljava/lang/String;", "getSoftwareMetadata", "()Lcom/amazon/alexa/fitness/service/hds/model/SoftwareMetadata;", "getType", "()Lcom/amazon/alexa/fitness/service/hds/model/DataSourceType;", "buildDataSourceInput", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DataSource {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final String outputFieldSelection;
    @Nullable
    private final DeviceMetadata deviceMetadata;
    @NotNull
    private final String identifier;
    @Nullable
    private final SoftwareMetadata softwareMetadata;
    @NotNull
    private final DataSourceType type;

    /* compiled from: HdsDataModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/DataSource$Companion;", "", "()V", "outputFieldSelection", "", "getOutputFieldSelection", "()Ljava/lang/String;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final String getOutputFieldSelection() {
            return DataSource.outputFieldSelection;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        String trimMargin$default;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("\n                |dataSource {\n                    |type\n                    |identifier\n                    |");
        outline107.append(DeviceMetadata.Companion.getOutputFieldSelection());
        outline107.append(SoftwareMetadata.Companion.getOutputFieldSelection());
        outline107.append("\n                |}");
        trimMargin$default = StringsKt__IndentKt.trimMargin$default(outline107.toString(), null, 1, null);
        outputFieldSelection = trimMargin$default;
    }

    public DataSource(@NotNull DataSourceType type, @NotNull String identifier, @Nullable DeviceMetadata deviceMetadata, @Nullable SoftwareMetadata softwareMetadata) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.type = type;
        this.identifier = identifier;
        this.deviceMetadata = deviceMetadata;
        this.softwareMetadata = softwareMetadata;
    }

    public static /* synthetic */ DataSource copy$default(DataSource dataSource, DataSourceType dataSourceType, String str, DeviceMetadata deviceMetadata, SoftwareMetadata softwareMetadata, int i, Object obj) {
        if ((i & 1) != 0) {
            dataSourceType = dataSource.type;
        }
        if ((i & 2) != 0) {
            str = dataSource.identifier;
        }
        if ((i & 4) != 0) {
            deviceMetadata = dataSource.deviceMetadata;
        }
        if ((i & 8) != 0) {
            softwareMetadata = dataSource.softwareMetadata;
        }
        return dataSource.copy(dataSourceType, str, deviceMetadata, softwareMetadata);
    }

    @NotNull
    public final String buildDataSourceInput() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("type:");
        outline1072.append(this.type.getValue());
        outline1072.append(JsonReaderKt.COMMA);
        outline107.append(outline1072.toString());
        outline107.append("identifier:\"" + this.identifier + "\",");
        DeviceMetadata deviceMetadata = this.deviceMetadata;
        if (deviceMetadata != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("deviceMetadata:");
            outline1073.append(deviceMetadata.buildDeviceMetadataInput());
            outline1073.append(JsonReaderKt.COMMA);
            outline107.append(outline1073.toString());
        }
        SoftwareMetadata softwareMetadata = this.softwareMetadata;
        if (softwareMetadata != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("softwareMetadata:");
            outline1074.append(softwareMetadata.buildSoftwareMetadataInput());
            outline1074.append(JsonReaderKt.COMMA);
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        String sb = outline107.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "gqlBuilder.toString()");
        return sb;
    }

    @NotNull
    public final DataSourceType component1() {
        return this.type;
    }

    @NotNull
    public final String component2() {
        return this.identifier;
    }

    @Nullable
    public final DeviceMetadata component3() {
        return this.deviceMetadata;
    }

    @Nullable
    public final SoftwareMetadata component4() {
        return this.softwareMetadata;
    }

    @NotNull
    public final DataSource copy(@NotNull DataSourceType type, @NotNull String identifier, @Nullable DeviceMetadata deviceMetadata, @Nullable SoftwareMetadata softwareMetadata) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        return new DataSource(type, identifier, deviceMetadata, softwareMetadata);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof DataSource)) {
                return false;
            }
            DataSource dataSource = (DataSource) obj;
            return Intrinsics.areEqual(this.type, dataSource.type) && Intrinsics.areEqual(this.identifier, dataSource.identifier) && Intrinsics.areEqual(this.deviceMetadata, dataSource.deviceMetadata) && Intrinsics.areEqual(this.softwareMetadata, dataSource.softwareMetadata);
        }
        return true;
    }

    @Nullable
    public final DeviceMetadata getDeviceMetadata() {
        return this.deviceMetadata;
    }

    @NotNull
    public final String getIdentifier() {
        return this.identifier;
    }

    @Nullable
    public final SoftwareMetadata getSoftwareMetadata() {
        return this.softwareMetadata;
    }

    @NotNull
    public final DataSourceType getType() {
        return this.type;
    }

    public int hashCode() {
        DataSourceType dataSourceType = this.type;
        int i = 0;
        int hashCode = (dataSourceType != null ? dataSourceType.hashCode() : 0) * 31;
        String str = this.identifier;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DataSource(type=");
        outline107.append(this.type);
        outline107.append(", identifier=");
        outline107.append(this.identifier);
        outline107.append(", deviceMetadata=");
        outline107.append(this.deviceMetadata);
        outline107.append(", softwareMetadata=");
        outline107.append(this.softwareMetadata);
        outline107.append(")");
        return outline107.toString();
    }
}
