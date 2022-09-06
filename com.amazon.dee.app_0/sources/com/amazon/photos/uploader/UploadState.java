package com.amazon.photos.uploader;

import com.amazon.alexa.presence.bleconn.service.PresenceBleService;
import kotlin.Metadata;
/* compiled from: UploadState.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/amazon/photos/uploader/UploadState;", "", "(Ljava/lang/String;I)V", "BLOCKED", "CANCELLED", "ENQUEUED", "FAILED", PresenceBleService.ServiceState.RUNNING, "SUCCEEDED", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public enum UploadState {
    BLOCKED,
    CANCELLED,
    ENQUEUED,
    FAILED,
    RUNNING,
    SUCCEEDED
}
