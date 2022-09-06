package com.amazon.alexa.photos;

import androidx.annotation.NonNull;
import com.amazon.alexa.photos.events.PhotosUploaderEvent;
import com.amazon.alexa.photos.events.PhotosUploaderEventType;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MessagePublisher.java */
/* loaded from: classes9.dex */
public class PhotosUploaderEventMessage {
    @NonNull
    final PhotosUploaderEvent event;
    @NonNull
    final PhotosUploaderEventType eventType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PhotosUploaderEventMessage(@NonNull PhotosUploaderEventType photosUploaderEventType, @NonNull PhotosUploaderEvent photosUploaderEvent) {
        this.eventType = photosUploaderEventType;
        this.event = photosUploaderEvent;
    }
}
