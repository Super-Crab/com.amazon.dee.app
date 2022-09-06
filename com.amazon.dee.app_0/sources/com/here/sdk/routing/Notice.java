package com.here.sdk.routing;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class Notice {
    @NonNull
    public NoticeCode code;

    public Notice(@NonNull NoticeCode noticeCode) {
        this.code = noticeCode;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Notice) {
            return Objects.equals(this.code, ((Notice) obj).code);
        }
        return false;
    }

    public int hashCode() {
        NoticeCode noticeCode = this.code;
        return (noticeCode != null ? noticeCode.hashCode() : 0) + JfifUtil.MARKER_EOI;
    }
}
