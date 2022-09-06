package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.UploadPartRequest;
import java.util.concurrent.Callable;
/* loaded from: classes13.dex */
public class UploadPartCallable implements Callable<PartETag> {
    private final UploadPartRequest request;
    private final AmazonS3 s3;

    public UploadPartCallable(AmazonS3 amazonS3, UploadPartRequest uploadPartRequest) {
        this.s3 = amazonS3;
        this.request = uploadPartRequest;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    /* renamed from: call */
    public PartETag mo6702call() throws Exception {
        return this.s3.uploadPart(this.request).getPartETag();
    }
}
