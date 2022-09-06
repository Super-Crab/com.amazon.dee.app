package com.amazon.alexa.fitness.api.afx;

import com.amazon.alexa.fitness.api.util.DateTime;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AFITSTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u0010\u001e\u001a\u00020\fHÆ\u0003JK\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0006HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006&"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/AFITSWorkoutSummary;", "", "startDateTime", "Lcom/amazon/alexa/fitness/api/util/DateTime;", "endDateTime", "activityType", "", "dataSource", "Lcom/amazon/alexa/fitness/api/afx/DataSource;", "softwareMetadata", "Lcom/amazon/alexa/fitness/api/afx/SoftwareMetadata;", "values", "Lcom/amazon/alexa/fitness/api/afx/AFITSActivitySummary;", "(Lcom/amazon/alexa/fitness/api/util/DateTime;Lcom/amazon/alexa/fitness/api/util/DateTime;Ljava/lang/String;Lcom/amazon/alexa/fitness/api/afx/DataSource;Lcom/amazon/alexa/fitness/api/afx/SoftwareMetadata;Lcom/amazon/alexa/fitness/api/afx/AFITSActivitySummary;)V", "getActivityType", "()Ljava/lang/String;", "getDataSource", "()Lcom/amazon/alexa/fitness/api/afx/DataSource;", "getEndDateTime", "()Lcom/amazon/alexa/fitness/api/util/DateTime;", "getSoftwareMetadata", "()Lcom/amazon/alexa/fitness/api/afx/SoftwareMetadata;", "getStartDateTime", "getValues", "()Lcom/amazon/alexa/fitness/api/afx/AFITSActivitySummary;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class AFITSWorkoutSummary {
    @Nullable
    private final String activityType;
    @Nullable
    private final DataSource dataSource;
    @NotNull
    private final DateTime endDateTime;
    @Nullable
    private final SoftwareMetadata softwareMetadata;
    @NotNull
    private final DateTime startDateTime;
    @NotNull
    private final AFITSActivitySummary values;

    public AFITSWorkoutSummary(@NotNull DateTime startDateTime, @NotNull DateTime endDateTime, @Nullable String str, @Nullable DataSource dataSource, @Nullable SoftwareMetadata softwareMetadata, @NotNull AFITSActivitySummary values) {
        Intrinsics.checkParameterIsNotNull(startDateTime, "startDateTime");
        Intrinsics.checkParameterIsNotNull(endDateTime, "endDateTime");
        Intrinsics.checkParameterIsNotNull(values, "values");
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.activityType = str;
        this.dataSource = dataSource;
        this.softwareMetadata = softwareMetadata;
        this.values = values;
    }

    public static /* synthetic */ AFITSWorkoutSummary copy$default(AFITSWorkoutSummary aFITSWorkoutSummary, DateTime dateTime, DateTime dateTime2, String str, DataSource dataSource, SoftwareMetadata softwareMetadata, AFITSActivitySummary aFITSActivitySummary, int i, Object obj) {
        if ((i & 1) != 0) {
            dateTime = aFITSWorkoutSummary.startDateTime;
        }
        if ((i & 2) != 0) {
            dateTime2 = aFITSWorkoutSummary.endDateTime;
        }
        DateTime dateTime3 = dateTime2;
        if ((i & 4) != 0) {
            str = aFITSWorkoutSummary.activityType;
        }
        String str2 = str;
        if ((i & 8) != 0) {
            dataSource = aFITSWorkoutSummary.dataSource;
        }
        DataSource dataSource2 = dataSource;
        if ((i & 16) != 0) {
            softwareMetadata = aFITSWorkoutSummary.softwareMetadata;
        }
        SoftwareMetadata softwareMetadata2 = softwareMetadata;
        if ((i & 32) != 0) {
            aFITSActivitySummary = aFITSWorkoutSummary.values;
        }
        return aFITSWorkoutSummary.copy(dateTime, dateTime3, str2, dataSource2, softwareMetadata2, aFITSActivitySummary);
    }

    @NotNull
    public final DateTime component1() {
        return this.startDateTime;
    }

    @NotNull
    public final DateTime component2() {
        return this.endDateTime;
    }

    @Nullable
    public final String component3() {
        return this.activityType;
    }

    @Nullable
    public final DataSource component4() {
        return this.dataSource;
    }

    @Nullable
    public final SoftwareMetadata component5() {
        return this.softwareMetadata;
    }

    @NotNull
    public final AFITSActivitySummary component6() {
        return this.values;
    }

    @NotNull
    public final AFITSWorkoutSummary copy(@NotNull DateTime startDateTime, @NotNull DateTime endDateTime, @Nullable String str, @Nullable DataSource dataSource, @Nullable SoftwareMetadata softwareMetadata, @NotNull AFITSActivitySummary values) {
        Intrinsics.checkParameterIsNotNull(startDateTime, "startDateTime");
        Intrinsics.checkParameterIsNotNull(endDateTime, "endDateTime");
        Intrinsics.checkParameterIsNotNull(values, "values");
        return new AFITSWorkoutSummary(startDateTime, endDateTime, str, dataSource, softwareMetadata, values);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof AFITSWorkoutSummary)) {
                return false;
            }
            AFITSWorkoutSummary aFITSWorkoutSummary = (AFITSWorkoutSummary) obj;
            return Intrinsics.areEqual(this.startDateTime, aFITSWorkoutSummary.startDateTime) && Intrinsics.areEqual(this.endDateTime, aFITSWorkoutSummary.endDateTime) && Intrinsics.areEqual(this.activityType, aFITSWorkoutSummary.activityType) && Intrinsics.areEqual(this.dataSource, aFITSWorkoutSummary.dataSource) && Intrinsics.areEqual(this.softwareMetadata, aFITSWorkoutSummary.softwareMetadata) && Intrinsics.areEqual(this.values, aFITSWorkoutSummary.values);
        }
        return true;
    }

    @Nullable
    public final String getActivityType() {
        return this.activityType;
    }

    @Nullable
    public final DataSource getDataSource() {
        return this.dataSource;
    }

    @NotNull
    public final DateTime getEndDateTime() {
        return this.endDateTime;
    }

    @Nullable
    public final SoftwareMetadata getSoftwareMetadata() {
        return this.softwareMetadata;
    }

    @NotNull
    public final DateTime getStartDateTime() {
        return this.startDateTime;
    }

    @NotNull
    public final AFITSActivitySummary getValues() {
        return this.values;
    }

    public int hashCode() {
        DateTime dateTime = this.startDateTime;
        int i = 0;
        int hashCode = (dateTime != null ? dateTime.hashCode() : 0) * 31;
        DateTime dateTime2 = this.endDateTime;
        int hashCode2 = (hashCode + (dateTime2 != null ? dateTime2.hashCode() : 0)) * 31;
        String str = this.activityType;
        int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        DataSource dataSource = this.dataSource;
        int hashCode4 = (hashCode3 + (dataSource != null ? dataSource.hashCode() : 0)) * 31;
        SoftwareMetadata softwareMetadata = this.softwareMetadata;
        int hashCode5 = (hashCode4 + (softwareMetadata != null ? softwareMetadata.hashCode() : 0)) * 31;
        AFITSActivitySummary aFITSActivitySummary = this.values;
        if (aFITSActivitySummary != null) {
            i = aFITSActivitySummary.hashCode();
        }
        return hashCode5 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AFITSWorkoutSummary(startDateTime=");
        outline107.append(this.startDateTime);
        outline107.append(", endDateTime=");
        outline107.append(this.endDateTime);
        outline107.append(", activityType=");
        outline107.append(this.activityType);
        outline107.append(", dataSource=");
        outline107.append(this.dataSource);
        outline107.append(", softwareMetadata=");
        outline107.append(this.softwareMetadata);
        outline107.append(", values=");
        outline107.append(this.values);
        outline107.append(")");
        return outline107.toString();
    }
}
