package com.here.sdk.routing;

import com.facebook.imageutils.JfifUtil;
/* loaded from: classes3.dex */
public final class EVDetails {
    public double consumptionInKilowattHour;

    public EVDetails(double d) {
        this.consumptionInKilowattHour = d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof EVDetails) && Double.compare(this.consumptionInKilowattHour, ((EVDetails) obj).consumptionInKilowattHour) == 0;
    }

    public int hashCode() {
        return ((int) (Double.doubleToLongBits(this.consumptionInKilowattHour) ^ (Double.doubleToLongBits(this.consumptionInKilowattHour) >>> 32))) + JfifUtil.MARKER_EOI;
    }
}
