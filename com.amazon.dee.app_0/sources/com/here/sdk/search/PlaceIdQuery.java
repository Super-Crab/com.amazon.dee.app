package com.here.sdk.search;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class PlaceIdQuery {
    @NonNull
    public final String id;

    public PlaceIdQuery(@NonNull String str) {
        this.id = make(str).id;
    }

    private static native PlaceIdQuery make(@NonNull String str);

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PlaceIdQuery) {
            return Objects.equals(this.id, ((PlaceIdQuery) obj).id);
        }
        return false;
    }

    public int hashCode() {
        String str = this.id;
        return (str != null ? str.hashCode() : 0) + JfifUtil.MARKER_EOI;
    }
}
