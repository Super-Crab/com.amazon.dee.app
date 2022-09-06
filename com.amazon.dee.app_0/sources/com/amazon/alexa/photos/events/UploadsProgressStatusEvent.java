package com.amazon.alexa.photos.events;

import java.util.Objects;
/* loaded from: classes9.dex */
public class UploadsProgressStatusEvent extends PhotosUploaderEvent {
    private final int processedCount;
    private final int totalQueuedCount;

    public UploadsProgressStatusEvent(int i, int i2) {
        this.processedCount = i;
        this.totalQueuedCount = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || UploadsProgressStatusEvent.class != obj.getClass()) {
            return false;
        }
        UploadsProgressStatusEvent uploadsProgressStatusEvent = (UploadsProgressStatusEvent) obj;
        return this.processedCount == uploadsProgressStatusEvent.processedCount && this.totalQueuedCount == uploadsProgressStatusEvent.totalQueuedCount;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.processedCount), Integer.valueOf(this.totalQueuedCount));
    }
}
