package com.amazon.alexa.hho.stickynotes;

import javax.annotation.Nonnull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public class MediaDownloadRequest {
    final String mediaId;
    final StickyNoteMediaType mediaType;
    final boolean prefetch;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaDownloadRequest(@Nonnull StickyNoteMediaType stickyNoteMediaType, @Nonnull String str, boolean z) {
        this.mediaId = str;
        this.mediaType = stickyNoteMediaType;
        this.prefetch = z;
    }
}
