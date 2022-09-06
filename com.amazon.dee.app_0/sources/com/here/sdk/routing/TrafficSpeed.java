package com.here.sdk.routing;

import androidx.annotation.Nullable;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class TrafficSpeed {
    @Nullable
    public Double baseSpeedInMetersPerSecond;
    @Nullable
    public Double jamFactor;
    public int offset;
    @Nullable
    public Double trafficSpeedInMetersPerSecond;

    public TrafficSpeed(int i, @Nullable Double d, @Nullable Double d2, @Nullable Double d3) {
        this.offset = i;
        this.baseSpeedInMetersPerSecond = d;
        this.trafficSpeedInMetersPerSecond = d2;
        this.jamFactor = d3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TrafficSpeed)) {
            return false;
        }
        TrafficSpeed trafficSpeed = (TrafficSpeed) obj;
        return this.offset == trafficSpeed.offset && Objects.equals(this.baseSpeedInMetersPerSecond, trafficSpeed.baseSpeedInMetersPerSecond) && Objects.equals(this.trafficSpeedInMetersPerSecond, trafficSpeed.trafficSpeedInMetersPerSecond) && Objects.equals(this.jamFactor, trafficSpeed.jamFactor);
    }

    public int hashCode() {
        int i = (this.offset + JfifUtil.MARKER_EOI) * 31;
        Double d = this.baseSpeedInMetersPerSecond;
        int i2 = 0;
        int hashCode = (i + (d != null ? d.hashCode() : 0)) * 31;
        Double d2 = this.trafficSpeedInMetersPerSecond;
        int hashCode2 = (hashCode + (d2 != null ? d2.hashCode() : 0)) * 31;
        Double d3 = this.jamFactor;
        if (d3 != null) {
            i2 = d3.hashCode();
        }
        return hashCode2 + i2;
    }
}
