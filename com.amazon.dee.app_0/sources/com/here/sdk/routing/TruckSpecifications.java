package com.here.sdk.routing;

import androidx.annotation.Nullable;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class TruckSpecifications {
    @Nullable
    public Integer axleCount;
    @Nullable
    public Integer grossWeightInKilograms;
    @Nullable
    public Integer heightInCentimeters;
    @Nullable
    public Integer lengthInCentimeters;
    @Nullable
    public Integer weightPerAxleInKilograms;
    @Nullable
    public Integer widthInCentimeters;

    public TruckSpecifications() {
        this.grossWeightInKilograms = null;
        this.weightPerAxleInKilograms = null;
        this.heightInCentimeters = null;
        this.widthInCentimeters = null;
        this.lengthInCentimeters = null;
        this.axleCount = null;
    }

    public TruckSpecifications(@Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable Integer num4, @Nullable Integer num5, @Nullable Integer num6) {
        this.grossWeightInKilograms = num;
        this.weightPerAxleInKilograms = num2;
        this.heightInCentimeters = num3;
        this.widthInCentimeters = num4;
        this.lengthInCentimeters = num5;
        this.axleCount = num6;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TruckSpecifications)) {
            return false;
        }
        TruckSpecifications truckSpecifications = (TruckSpecifications) obj;
        return Objects.equals(this.grossWeightInKilograms, truckSpecifications.grossWeightInKilograms) && Objects.equals(this.weightPerAxleInKilograms, truckSpecifications.weightPerAxleInKilograms) && Objects.equals(this.heightInCentimeters, truckSpecifications.heightInCentimeters) && Objects.equals(this.widthInCentimeters, truckSpecifications.widthInCentimeters) && Objects.equals(this.lengthInCentimeters, truckSpecifications.lengthInCentimeters) && Objects.equals(this.axleCount, truckSpecifications.axleCount);
    }

    public int hashCode() {
        Integer num = this.grossWeightInKilograms;
        int i = 0;
        int hashCode = ((num != null ? num.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        Integer num2 = this.weightPerAxleInKilograms;
        int hashCode2 = (hashCode + (num2 != null ? num2.hashCode() : 0)) * 31;
        Integer num3 = this.heightInCentimeters;
        int hashCode3 = (hashCode2 + (num3 != null ? num3.hashCode() : 0)) * 31;
        Integer num4 = this.widthInCentimeters;
        int hashCode4 = (hashCode3 + (num4 != null ? num4.hashCode() : 0)) * 31;
        Integer num5 = this.lengthInCentimeters;
        int hashCode5 = (hashCode4 + (num5 != null ? num5.hashCode() : 0)) * 31;
        Integer num6 = this.axleCount;
        if (num6 != null) {
            i = num6.hashCode();
        }
        return hashCode5 + i;
    }
}
