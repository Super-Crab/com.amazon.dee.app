package com.amazon.alexa.photos.events;

import java.util.Objects;
/* loaded from: classes9.dex */
public class UploadsCompleteEvent extends PhotosUploaderEvent {
    private final int failureCount;
    private final int uploadedCount;

    public UploadsCompleteEvent(int i, int i2) {
        this.uploadedCount = i;
        this.failureCount = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || UploadsCompleteEvent.class != obj.getClass()) {
            return false;
        }
        UploadsCompleteEvent uploadsCompleteEvent = (UploadsCompleteEvent) obj;
        return this.uploadedCount == uploadsCompleteEvent.uploadedCount && this.failureCount == uploadsCompleteEvent.failureCount;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.uploadedCount), Integer.valueOf(this.failureCount));
    }
}
