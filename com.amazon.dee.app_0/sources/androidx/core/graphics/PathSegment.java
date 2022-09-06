package androidx.core.graphics;

import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes.dex */
public final class PathSegment {
    private final PointF mEnd;
    private final float mEndFraction;
    private final PointF mStart;
    private final float mStartFraction;

    public PathSegment(@NonNull PointF pointF, float f, @NonNull PointF pointF2, float f2) {
        this.mStart = (PointF) Preconditions.checkNotNull(pointF, "start == null");
        this.mStartFraction = f;
        this.mEnd = (PointF) Preconditions.checkNotNull(pointF2, "end == null");
        this.mEndFraction = f2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PathSegment)) {
            return false;
        }
        PathSegment pathSegment = (PathSegment) obj;
        return Float.compare(this.mStartFraction, pathSegment.mStartFraction) == 0 && Float.compare(this.mEndFraction, pathSegment.mEndFraction) == 0 && this.mStart.equals(pathSegment.mStart) && this.mEnd.equals(pathSegment.mEnd);
    }

    @NonNull
    public PointF getEnd() {
        return this.mEnd;
    }

    public float getEndFraction() {
        return this.mEndFraction;
    }

    @NonNull
    public PointF getStart() {
        return this.mStart;
    }

    public float getStartFraction() {
        return this.mStartFraction;
    }

    public int hashCode() {
        int hashCode = this.mStart.hashCode() * 31;
        float f = this.mStartFraction;
        int i = 0;
        int hashCode2 = (this.mEnd.hashCode() + ((hashCode + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31)) * 31;
        float f2 = this.mEndFraction;
        if (f2 != 0.0f) {
            i = Float.floatToIntBits(f2);
        }
        return hashCode2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PathSegment{start=");
        outline107.append(this.mStart);
        outline107.append(", startFraction=");
        outline107.append(this.mStartFraction);
        outline107.append(", end=");
        outline107.append(this.mEnd);
        outline107.append(", endFraction=");
        outline107.append(this.mEndFraction);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
