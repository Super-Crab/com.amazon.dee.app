package com.here.sdk.core;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class Rectangle2D {
    @NonNull
    public Point2D origin;
    @NonNull
    public Size2D size;

    public Rectangle2D(@NonNull Point2D point2D, @NonNull Size2D size2D) {
        this.origin = point2D;
        this.size = size2D;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Rectangle2D)) {
            return false;
        }
        Rectangle2D rectangle2D = (Rectangle2D) obj;
        return Objects.equals(this.origin, rectangle2D.origin) && Objects.equals(this.size, rectangle2D.size);
    }

    public int hashCode() {
        Point2D point2D = this.origin;
        int i = 0;
        int hashCode = ((point2D != null ? point2D.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        Size2D size2D = this.size;
        if (size2D != null) {
            i = size2D.hashCode();
        }
        return hashCode + i;
    }
}
