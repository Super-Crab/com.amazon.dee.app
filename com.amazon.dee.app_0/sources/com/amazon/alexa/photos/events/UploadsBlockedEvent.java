package com.amazon.alexa.photos.events;

import androidx.annotation.NonNull;
import java.util.Objects;
/* loaded from: classes9.dex */
public class UploadsBlockedEvent extends PhotosUploaderEvent {
    private final String reason;

    public UploadsBlockedEvent(@NonNull String str) {
        this.reason = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && UploadsBlockedEvent.class == obj.getClass()) {
            return this.reason.equals(((UploadsBlockedEvent) obj).reason);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.reason);
    }
}
