package com.here.sdk.gestures;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import com.here.sdk.core.Point2D;
import java.util.Objects;
/* loaded from: classes3.dex */
final class TouchPoint {
    @NonNull
    Point2D coordinates;
    long id;
    @NonNull
    TouchPhase phase;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TouchPoint(long j, @NonNull Point2D point2D, @NonNull TouchPhase touchPhase) {
        this.id = j;
        this.coordinates = point2D;
        this.phase = touchPhase;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TouchPoint)) {
            return false;
        }
        TouchPoint touchPoint = (TouchPoint) obj;
        return this.id == touchPoint.id && Objects.equals(this.coordinates, touchPoint.coordinates) && Objects.equals(this.phase, touchPoint.phase);
    }

    public int hashCode() {
        long j = this.id;
        int i = (((int) (j ^ (j >>> 32))) + JfifUtil.MARKER_EOI) * 31;
        Point2D point2D = this.coordinates;
        int i2 = 0;
        int hashCode = (i + (point2D != null ? point2D.hashCode() : 0)) * 31;
        TouchPhase touchPhase = this.phase;
        if (touchPhase != null) {
            i2 = touchPhase.hashCode();
        }
        return hashCode + i2;
    }
}
