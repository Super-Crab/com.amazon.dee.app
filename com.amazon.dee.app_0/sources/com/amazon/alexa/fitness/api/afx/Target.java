package com.amazon.alexa.fitness.api.afx;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AFITSTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J2\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/Target;", "", "type", "", "value", "", "unit", "(Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;)V", "getType", "()Ljava/lang/String;", "getUnit", "getValue", "()Ljava/lang/Double;", "Ljava/lang/Double;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;)Lcom/amazon/alexa/fitness/api/afx/Target;", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Target {
    @Nullable
    private final String type;
    @Nullable
    private final String unit;
    @Nullable
    private final Double value;

    public Target(@Nullable String str, @Nullable Double d, @Nullable String str2) {
        this.type = str;
        this.value = d;
        this.unit = str2;
    }

    public static /* synthetic */ Target copy$default(Target target, String str, Double d, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = target.type;
        }
        if ((i & 2) != 0) {
            d = target.value;
        }
        if ((i & 4) != 0) {
            str2 = target.unit;
        }
        return target.copy(str, d, str2);
    }

    @Nullable
    public final String component1() {
        return this.type;
    }

    @Nullable
    public final Double component2() {
        return this.value;
    }

    @Nullable
    public final String component3() {
        return this.unit;
    }

    @NotNull
    public final Target copy(@Nullable String str, @Nullable Double d, @Nullable String str2) {
        return new Target(str, d, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Target)) {
                return false;
            }
            Target target = (Target) obj;
            return Intrinsics.areEqual(this.type, target.type) && Intrinsics.areEqual((Object) this.value, (Object) target.value) && Intrinsics.areEqual(this.unit, target.unit);
        }
        return true;
    }

    @Nullable
    public final String getType() {
        return this.type;
    }

    @Nullable
    public final String getUnit() {
        return this.unit;
    }

    @Nullable
    public final Double getValue() {
        return this.value;
    }

    public int hashCode() {
        String str = this.type;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Double d = this.value;
        int hashCode2 = (hashCode + (d != null ? d.hashCode() : 0)) * 31;
        String str2 = this.unit;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Target(type=");
        outline107.append(this.type);
        outline107.append(", value=");
        outline107.append(this.value);
        outline107.append(", unit=");
        return GeneratedOutlineSupport1.outline91(outline107, this.unit, ")");
    }
}
