package com.amazon.alexa.photos.events;

import java.util.Objects;
/* loaded from: classes9.dex */
public class UploadsProcessingCompleteEvent extends PhotosUploaderEvent {
    private final int processedCount;
    private final int totalQueuedCount;

    public UploadsProcessingCompleteEvent(int i, int i2) {
        this.processedCount = i;
        this.totalQueuedCount = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || UploadsProcessingCompleteEvent.class != obj.getClass()) {
            return false;
        }
        UploadsProcessingCompleteEvent uploadsProcessingCompleteEvent = (UploadsProcessingCompleteEvent) obj;
        return this.processedCount == uploadsProcessingCompleteEvent.processedCount && this.totalQueuedCount == uploadsProcessingCompleteEvent.totalQueuedCount;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.processedCount), Integer.valueOf(this.totalQueuedCount));
    }
}
