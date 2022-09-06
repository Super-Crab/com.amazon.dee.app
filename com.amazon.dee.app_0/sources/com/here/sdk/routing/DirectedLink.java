package com.here.sdk.routing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
final class DirectedLink {
    @Nullable
    String segmentid;

    DirectedLink(@NonNull String str) {
        this.segmentid = make(str).segmentid;
    }

    private static native DirectedLink make(@NonNull String str);

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DirectedLink) {
            return Objects.equals(this.segmentid, ((DirectedLink) obj).segmentid);
        }
        return false;
    }

    public int hashCode() {
        String str = this.segmentid;
        return (str != null ? str.hashCode() : 0) + JfifUtil.MARKER_EOI;
    }
}
