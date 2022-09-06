package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.api.fitnessSdk.ActivityType;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SampleDataModels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001d"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/EchoBudSampleData;", "Ljava/io/Serializable;", "collectionTimestamp", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/alexa/fitness/api/fitnessSdk/ActivityType;", Metrics.STEPS, "", "cadence", "(JLcom/amazon/alexa/fitness/api/fitnessSdk/ActivityType;II)V", "getActivity", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/ActivityType;", "getCadence", "()I", "getCollectionTimestamp", "()J", "getSteps", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class EchoBudSampleData implements Serializable {
    @NotNull
    private final ActivityType activity;
    private final int cadence;
    private final long collectionTimestamp;
    private final int steps;

    public EchoBudSampleData(long j, @NotNull ActivityType activity, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        this.collectionTimestamp = j;
        this.activity = activity;
        this.steps = i;
        this.cadence = i2;
    }

    public static /* synthetic */ EchoBudSampleData copy$default(EchoBudSampleData echoBudSampleData, long j, ActivityType activityType, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            j = echoBudSampleData.collectionTimestamp;
        }
        long j2 = j;
        if ((i3 & 2) != 0) {
            activityType = echoBudSampleData.activity;
        }
        ActivityType activityType2 = activityType;
        if ((i3 & 4) != 0) {
            i = echoBudSampleData.steps;
        }
        int i4 = i;
        if ((i3 & 8) != 0) {
            i2 = echoBudSampleData.cadence;
        }
        return echoBudSampleData.copy(j2, activityType2, i4, i2);
    }

    public final long component1() {
        return this.collectionTimestamp;
    }

    @NotNull
    public final ActivityType component2() {
        return this.activity;
    }

    public final int component3() {
        return this.steps;
    }

    public final int component4() {
        return this.cadence;
    }

    @NotNull
    public final EchoBudSampleData copy(long j, @NotNull ActivityType activity, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        return new EchoBudSampleData(j, activity, i, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof EchoBudSampleData)) {
                return false;
            }
            EchoBudSampleData echoBudSampleData = (EchoBudSampleData) obj;
            return this.collectionTimestamp == echoBudSampleData.collectionTimestamp && Intrinsics.areEqual(this.activity, echoBudSampleData.activity) && this.steps == echoBudSampleData.steps && this.cadence == echoBudSampleData.cadence;
        }
        return true;
    }

    @NotNull
    public final ActivityType getActivity() {
        return this.activity;
    }

    public final int getCadence() {
        return this.cadence;
    }

    public final long getCollectionTimestamp() {
        return this.collectionTimestamp;
    }

    public final int getSteps() {
        return this.steps;
    }

    public int hashCode() {
        long j = this.collectionTimestamp;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        ActivityType activityType = this.activity;
        return ((((i + (activityType != null ? activityType.hashCode() : 0)) * 31) + this.steps) * 31) + this.cadence;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EchoBudSampleData(collectionTimestamp=");
        outline107.append(this.collectionTimestamp);
        outline107.append(", activity=");
        outline107.append(this.activity);
        outline107.append(", steps=");
        outline107.append(this.steps);
        outline107.append(", cadence=");
        return GeneratedOutlineSupport1.outline86(outline107, this.cadence, ")");
    }
}
