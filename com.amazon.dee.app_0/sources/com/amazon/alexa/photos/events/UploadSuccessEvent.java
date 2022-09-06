package com.amazon.alexa.photos.events;

import androidx.annotation.Nullable;
import java.util.Objects;
/* loaded from: classes9.dex */
public class UploadSuccessEvent extends PhotosUploaderEvent {
    private final String nodeId;

    public UploadSuccessEvent(@Nullable String str) {
        this.nodeId = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && UploadSuccessEvent.class == obj.getClass()) {
            return Objects.equals(this.nodeId, ((UploadSuccessEvent) obj).nodeId);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.nodeId);
    }
}
