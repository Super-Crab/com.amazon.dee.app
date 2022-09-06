package com.here.sdk.core;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.facebook.imageutils.JfifUtil;
/* loaded from: classes3.dex */
public final class Point2D {
    public double x;
    public double y;

    public Point2D() {
        this.x = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.y = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    public Point2D(double d, double d2) {
        this.x = d;
        this.y = d2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Point2D)) {
            return false;
        }
        Point2D point2D = (Point2D) obj;
        return Double.compare(this.x, point2D.x) == 0 && Double.compare(this.y, point2D.y) == 0;
    }

    public int hashCode() {
        return ((((int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32))) + JfifUtil.MARKER_EOI) * 31) + ((int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32)));
    }
}
