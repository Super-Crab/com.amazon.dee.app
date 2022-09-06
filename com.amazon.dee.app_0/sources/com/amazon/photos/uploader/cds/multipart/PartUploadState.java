package com.amazon.photos.uploader.cds.multipart;

import com.amazon.alexa.presence.bleconn.service.PresenceBleService;
import kotlin.Metadata;
/* compiled from: PartUploadState.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/PartUploadState;", "", "(Ljava/lang/String;I)V", "ENQUEUED", "FAILED", PresenceBleService.ServiceState.RUNNING, "SUCCEEDED", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public enum PartUploadState {
    ENQUEUED,
    FAILED,
    RUNNING,
    SUCCEEDED
}
