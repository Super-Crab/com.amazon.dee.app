package com.amazon.photos.uploader;

import com.amazon.devicesetupservice.scap.v1.BleConnectionPriority;
import kotlin.Metadata;
/* compiled from: Priority.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/amazon/photos/uploader/Priority;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", BleConnectionPriority.LOW, "MEDIUM", BleConnectionPriority.HIGH, "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public enum Priority {
    LOW(100),
    MEDIUM(200),
    HIGH(300);
    
    private final int value;

    Priority(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }
}
