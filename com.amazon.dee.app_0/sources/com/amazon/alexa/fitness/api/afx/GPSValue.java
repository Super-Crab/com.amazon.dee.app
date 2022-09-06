package com.amazon.alexa.fitness.api.afx;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AFITSTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0007J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0007J&\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\t\u0010\u0007¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/GPSValue;", "", "value", "", "accuracy", "(Ljava/lang/Double;Ljava/lang/Double;)V", "getAccuracy", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getValue", "component1", "component2", "copy", "(Ljava/lang/Double;Ljava/lang/Double;)Lcom/amazon/alexa/fitness/api/afx/GPSValue;", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class GPSValue {
    @Nullable
    private final Double accuracy;
    @Nullable
    private final Double value;

    public GPSValue(@Nullable Double d, @Nullable Double d2) {
        this.value = d;
        this.accuracy = d2;
    }

    public static /* synthetic */ GPSValue copy$default(GPSValue gPSValue, Double d, Double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            d = gPSValue.value;
        }
        if ((i & 2) != 0) {
            d2 = gPSValue.accuracy;
        }
        return gPSValue.copy(d, d2);
    }

    @Nullable
    public final Double component1() {
        return this.value;
    }

    @Nullable
    public final Double component2() {
        return this.accuracy;
    }

    @NotNull
    public final GPSValue copy(@Nullable Double d, @Nullable Double d2) {
        return new GPSValue(d, d2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof GPSValue)) {
                return false;
            }
            GPSValue gPSValue = (GPSValue) obj;
            return Intrinsics.areEqual((Object) this.value, (Object) gPSValue.value) && Intrinsics.areEqual((Object) this.accuracy, (Object) gPSValue.accuracy);
        }
        return true;
    }

    @Nullable
    public final Double getAccuracy() {
        return this.accuracy;
    }

    @Nullable
    public final Double getValue() {
        return this.value;
    }

    public int hashCode() {
        Double d = this.value;
        int i = 0;
        int hashCode = (d != null ? d.hashCode() : 0) * 31;
        Double d2 = this.accuracy;
        if (d2 != null) {
            i = d2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GPSValue(value=");
        outline107.append(this.value);
        outline107.append(", accuracy=");
        outline107.append(this.accuracy);
        outline107.append(")");
        return outline107.toString();
    }
}
