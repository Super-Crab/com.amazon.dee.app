package com.here.sdk.routing;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class PostAction {
    @NonNull
    public PostActionType action;
    public int durationInSeconds;

    public PostAction(@NonNull PostActionType postActionType, int i) {
        this.action = postActionType;
        this.durationInSeconds = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PostAction)) {
            return false;
        }
        PostAction postAction = (PostAction) obj;
        return Objects.equals(this.action, postAction.action) && this.durationInSeconds == postAction.durationInSeconds;
    }

    public int hashCode() {
        PostActionType postActionType = this.action;
        return (((postActionType != null ? postActionType.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31) + this.durationInSeconds;
    }
}
