package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: $AutoValue_Speed.java */
/* loaded from: classes.dex */
public abstract class ejS extends rfd {
    public final double zZm;

    public ejS(double d) {
        this.zZm = d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof rfd) && Double.doubleToLongBits(this.zZm) == Double.doubleToLongBits(((ejS) obj).zZm);
    }

    public int hashCode() {
        return ((int) ((Double.doubleToLongBits(this.zZm) >>> 32) ^ Double.doubleToLongBits(this.zZm))) ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline84(C0179Pya.zZm("Speed{speedInMetersPerSecond="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
