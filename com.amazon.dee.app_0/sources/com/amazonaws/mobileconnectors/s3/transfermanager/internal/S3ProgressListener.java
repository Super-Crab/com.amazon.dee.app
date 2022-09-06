package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import com.amazonaws.event.ProgressListener;
import com.amazonaws.mobileconnectors.s3.transfermanager.PersistableTransfer;
/* loaded from: classes13.dex */
public interface S3ProgressListener extends ProgressListener {
    void onPersistableTransfer(PersistableTransfer persistableTransfer);
}
