package com.amazon.alexa.photos.events;

import androidx.annotation.Nullable;
import java.util.Objects;
/* loaded from: classes9.dex */
public class UploadFailureEvent extends PhotosUploaderEvent {
    private final int errorCode;
    private final String errorType;
    private final String message;

    public UploadFailureEvent(@Nullable String str, @Nullable String str2, int i) {
        this.message = str;
        this.errorType = str2;
        this.errorCode = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || UploadFailureEvent.class != obj.getClass()) {
            return false;
        }
        UploadFailureEvent uploadFailureEvent = (UploadFailureEvent) obj;
        return this.errorCode == uploadFailureEvent.errorCode && Objects.equals(this.message, uploadFailureEvent.message) && Objects.equals(this.errorType, uploadFailureEvent.errorType);
    }

    public int hashCode() {
        return Objects.hash(this.message, this.errorType, Integer.valueOf(this.errorCode));
    }
}
