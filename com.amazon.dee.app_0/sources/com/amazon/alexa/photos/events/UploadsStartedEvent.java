package com.amazon.alexa.photos.events;

import java.util.Objects;
/* loaded from: classes9.dex */
public class UploadsStartedEvent extends PhotosUploaderEvent {
    private final int selectedCount;

    public UploadsStartedEvent(int i) {
        this.selectedCount = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && UploadsStartedEvent.class == obj.getClass() && this.selectedCount == ((UploadsStartedEvent) obj).selectedCount;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.selectedCount));
    }
}
