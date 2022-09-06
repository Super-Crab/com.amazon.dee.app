package com.amazon.photos.uploader.cds.multipart;

import kotlin.Metadata;
/* compiled from: PartProgressListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/PartProgressListener;", "", "onProgressUpdate", "", "progress", "", "maxProgress", "partId", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface PartProgressListener {
    void onProgressUpdate(long j, long j2, long j3);
}
