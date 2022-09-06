package com.amazon.alexa.fitness.api.afits;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AfitsTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f¢\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\tHÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010 \u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fHÆ\u0003JY\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014¨\u0006("}, d2 = {"Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "", "id", "", "startTime", "endTime", "dataSource", "Lcom/amazon/alexa/fitness/api/afits/DataSource;", "activitySummary", "Lcom/amazon/alexa/fitness/api/afits/ActivitySummary;", "version", "route", "", "Lcom/amazon/alexa/fitness/api/afits/LocationSample;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/alexa/fitness/api/afits/DataSource;Lcom/amazon/alexa/fitness/api/afits/ActivitySummary;Ljava/lang/String;Ljava/util/List;)V", "getActivitySummary", "()Lcom/amazon/alexa/fitness/api/afits/ActivitySummary;", "getDataSource", "()Lcom/amazon/alexa/fitness/api/afits/DataSource;", "getEndTime", "()Ljava/lang/String;", "getId", "getRoute", "()Ljava/util/List;", "getStartTime", "getVersion", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessSession {
    @NotNull
    private final ActivitySummary activitySummary;
    @NotNull
    private final DataSource dataSource;
    @NotNull
    private final String endTime;
    @NotNull
    private final String id;
    @Nullable
    private final List<LocationSample> route;
    @NotNull
    private final String startTime;
    @Nullable
    private final String version;

    public FitnessSession(@NotNull String id, @NotNull String startTime, @NotNull String endTime, @NotNull DataSource dataSource, @NotNull ActivitySummary activitySummary, @Nullable String str, @Nullable List<LocationSample> list) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(startTime, "startTime");
        Intrinsics.checkParameterIsNotNull(endTime, "endTime");
        Intrinsics.checkParameterIsNotNull(dataSource, "dataSource");
        Intrinsics.checkParameterIsNotNull(activitySummary, "activitySummary");
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dataSource = dataSource;
        this.activitySummary = activitySummary;
        this.version = str;
        this.route = list;
    }

    public static /* synthetic */ FitnessSession copy$default(FitnessSession fitnessSession, String str, String str2, String str3, DataSource dataSource, ActivitySummary activitySummary, String str4, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = fitnessSession.id;
        }
        if ((i & 2) != 0) {
            str2 = fitnessSession.startTime;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = fitnessSession.endTime;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            dataSource = fitnessSession.dataSource;
        }
        DataSource dataSource2 = dataSource;
        if ((i & 16) != 0) {
            activitySummary = fitnessSession.activitySummary;
        }
        ActivitySummary activitySummary2 = activitySummary;
        if ((i & 32) != 0) {
            str4 = fitnessSession.version;
        }
        String str7 = str4;
        List<LocationSample> list2 = list;
        if ((i & 64) != 0) {
            list2 = fitnessSession.route;
        }
        return fitnessSession.copy(str, str5, str6, dataSource2, activitySummary2, str7, list2);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.startTime;
    }

    @NotNull
    public final String component3() {
        return this.endTime;
    }

    @NotNull
    public final DataSource component4() {
        return this.dataSource;
    }

    @NotNull
    public final ActivitySummary component5() {
        return this.activitySummary;
    }

    @Nullable
    public final String component6() {
        return this.version;
    }

    @Nullable
    public final List<LocationSample> component7() {
        return this.route;
    }

    @NotNull
    public final FitnessSession copy(@NotNull String id, @NotNull String startTime, @NotNull String endTime, @NotNull DataSource dataSource, @NotNull ActivitySummary activitySummary, @Nullable String str, @Nullable List<LocationSample> list) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(startTime, "startTime");
        Intrinsics.checkParameterIsNotNull(endTime, "endTime");
        Intrinsics.checkParameterIsNotNull(dataSource, "dataSource");
        Intrinsics.checkParameterIsNotNull(activitySummary, "activitySummary");
        return new FitnessSession(id, startTime, endTime, dataSource, activitySummary, str, list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof FitnessSession)) {
                return false;
            }
            FitnessSession fitnessSession = (FitnessSession) obj;
            return Intrinsics.areEqual(this.id, fitnessSession.id) && Intrinsics.areEqual(this.startTime, fitnessSession.startTime) && Intrinsics.areEqual(this.endTime, fitnessSession.endTime) && Intrinsics.areEqual(this.dataSource, fitnessSession.dataSource) && Intrinsics.areEqual(this.activitySummary, fitnessSession.activitySummary) && Intrinsics.areEqual(this.version, fitnessSession.version) && Intrinsics.areEqual(this.route, fitnessSession.route);
        }
        return true;
    }

    @NotNull
    public final ActivitySummary getActivitySummary() {
        return this.activitySummary;
    }

    @NotNull
    public final DataSource getDataSource() {
        return this.dataSource;
    }

    @NotNull
    public final String getEndTime() {
        return this.endTime;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @Nullable
    public final List<LocationSample> getRoute() {
        return this.route;
    }

    @NotNull
    public final String getStartTime() {
        return this.startTime;
    }

    @Nullable
    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.startTime;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.endTime;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        DataSource dataSource = this.dataSource;
        int hashCode4 = (hashCode3 + (dataSource != null ? dataSource.hashCode() : 0)) * 31;
        ActivitySummary activitySummary = this.activitySummary;
        int hashCode5 = (hashCode4 + (activitySummary != null ? activitySummary.hashCode() : 0)) * 31;
        String str4 = this.version;
        int hashCode6 = (hashCode5 + (str4 != null ? str4.hashCode() : 0)) * 31;
        List<LocationSample> list = this.route;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode6 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FitnessSession(id=");
        outline107.append(this.id);
        outline107.append(", startTime=");
        outline107.append(this.startTime);
        outline107.append(", endTime=");
        outline107.append(this.endTime);
        outline107.append(", dataSource=");
        outline107.append(this.dataSource);
        outline107.append(", activitySummary=");
        outline107.append(this.activitySummary);
        outline107.append(", version=");
        outline107.append(this.version);
        outline107.append(", route=");
        return GeneratedOutlineSupport1.outline95(outline107, this.route, ")");
    }
}
