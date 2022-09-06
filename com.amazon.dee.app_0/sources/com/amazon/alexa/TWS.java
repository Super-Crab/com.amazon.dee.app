package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: $AutoValue_Heading.java */
/* loaded from: classes.dex */
public abstract class TWS extends wze {
    public final double zZm;

    public TWS(double d) {
        this.zZm = d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof wze) && Double.doubleToLongBits(this.zZm) == Double.doubleToLongBits(((TWS) obj).zZm);
    }

    public int hashCode() {
        return ((int) ((Double.doubleToLongBits(this.zZm) >>> 32) ^ Double.doubleToLongBits(this.zZm))) ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline84(C0179Pya.zZm("Heading{directionInDegrees="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
