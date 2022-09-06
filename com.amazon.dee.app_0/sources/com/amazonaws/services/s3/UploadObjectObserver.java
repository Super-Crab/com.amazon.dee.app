package com.amazonaws.services.s3;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.services.s3.internal.PartCreationEvent;
import com.amazonaws.services.s3.internal.S3DirectSpi;
import com.amazonaws.services.s3.model.AbortMultipartUploadRequest;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.CompleteMultipartUploadResult;
import com.amazonaws.services.s3.model.EncryptedInitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.UploadObjectRequest;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.services.s3.model.UploadPartResult;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
/* loaded from: classes13.dex */
public class UploadObjectObserver {
    private ExecutorService es;
    private final List<Future<UploadPartResult>> futures = new ArrayList();
    private UploadObjectRequest req;
    private AmazonS3 s3;
    private S3DirectSpi s3direct;
    private String uploadId;

    protected <X extends AmazonWebServiceRequest> X appendUserAgent(X x, String str) {
        x.getRequestClientOptions().appendUserAgent(str);
        return x;
    }

    protected AmazonS3 getAmazonS3() {
        return this.s3;
    }

    protected ExecutorService getExecutorService() {
        return this.es;
    }

    public List<Future<UploadPartResult>> getFutures() {
        return this.futures;
    }

    protected UploadObjectRequest getRequest() {
        return this.req;
    }

    protected S3DirectSpi getS3DirectSpi() {
        return this.s3direct;
    }

    protected String getUploadId() {
        return this.uploadId;
    }

    public UploadObjectObserver init(UploadObjectRequest uploadObjectRequest, S3DirectSpi s3DirectSpi, AmazonS3 amazonS3, ExecutorService executorService) {
        this.req = uploadObjectRequest;
        this.s3direct = s3DirectSpi;
        this.s3 = amazonS3;
        this.es = executorService;
        return this;
    }

    protected InitiateMultipartUploadRequest newInitiateMultipartUploadRequest(UploadObjectRequest uploadObjectRequest) {
        return (InitiateMultipartUploadRequest) new EncryptedInitiateMultipartUploadRequest(uploadObjectRequest.getBucketName(), uploadObjectRequest.getKey(), uploadObjectRequest.getMetadata()).withMaterialsDescription(uploadObjectRequest.getMaterialsDescription()).withRedirectLocation(uploadObjectRequest.getRedirectLocation()).withSSEAwsKeyManagementParams(uploadObjectRequest.getSSEAwsKeyManagementParams()).withSSECustomerKey(uploadObjectRequest.getSSECustomerKey()).withStorageClass(uploadObjectRequest.getStorageClass()).withAccessControlList(uploadObjectRequest.getAccessControlList()).withCannedACL(uploadObjectRequest.getCannedAcl()).mo6730withGeneralProgressListener(uploadObjectRequest.getGeneralProgressListener()).withRequestMetricCollector(uploadObjectRequest.getRequestMetricCollector());
    }

    protected UploadPartRequest newUploadPartRequest(PartCreationEvent partCreationEvent, File file) {
        return new UploadPartRequest().withBucketName(this.req.getBucketName()).withFile(file).withKey(this.req.getKey()).withPartNumber(partCreationEvent.getPartNumber()).withPartSize(file.length()).withLastPart(partCreationEvent.isLastPart()).withUploadId(this.uploadId).withObjectMetadata(this.req.getUploadPartMetadata());
    }

    public void onAbort() {
        for (Future<UploadPartResult> future : getFutures()) {
            future.cancel(true);
        }
        if (this.uploadId != null) {
            try {
                this.s3.abortMultipartUpload(new AbortMultipartUploadRequest(this.req.getBucketName(), this.req.getKey(), this.uploadId));
            } catch (Exception e) {
                Log log = LogFactory.getLog(UploadObjectObserver.class);
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to abort multi-part upload: ");
                outline107.append(this.uploadId);
                log.debug(outline107.toString(), e);
            }
        }
    }

    public CompleteMultipartUploadResult onCompletion(List<PartETag> list) {
        return this.s3.completeMultipartUpload(new CompleteMultipartUploadRequest(this.req.getBucketName(), this.req.getKey(), this.uploadId, list));
    }

    public void onPartCreate(PartCreationEvent partCreationEvent) {
        final File part = partCreationEvent.getPart();
        final UploadPartRequest newUploadPartRequest = newUploadPartRequest(partCreationEvent, part);
        final OnFileDelete fileDeleteObserver = partCreationEvent.getFileDeleteObserver();
        appendUserAgent(newUploadPartRequest, AmazonS3EncryptionClient.USER_AGENT);
        this.futures.add(this.es.submit(new Callable<UploadPartResult>() { // from class: com.amazonaws.services.s3.UploadObjectObserver.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public UploadPartResult mo6706call() {
                try {
                    UploadPartResult uploadPart = UploadObjectObserver.this.uploadPart(newUploadPartRequest);
                    if (!part.delete()) {
                        Log log = LogFactory.getLog(AnonymousClass1.class);
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Ignoring failure to delete file ");
                        outline107.append(part);
                        outline107.append(" which has already been uploaded");
                        log.debug(outline107.toString());
                    } else {
                        OnFileDelete onFileDelete = fileDeleteObserver;
                        if (onFileDelete != null) {
                            onFileDelete.onFileDelete(null);
                        }
                    }
                    return uploadPart;
                } catch (Throwable th) {
                    if (part.delete()) {
                        OnFileDelete onFileDelete2 = fileDeleteObserver;
                        if (onFileDelete2 != null) {
                            onFileDelete2.onFileDelete(null);
                        }
                    } else {
                        Log log2 = LogFactory.getLog(AnonymousClass1.class);
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Ignoring failure to delete file ");
                        outline1072.append(part);
                        outline1072.append(" which has already been uploaded");
                        log2.debug(outline1072.toString());
                    }
                    throw th;
                }
            }
        }));
    }

    public String onUploadInitiation(UploadObjectRequest uploadObjectRequest) {
        this.uploadId = this.s3.initiateMultipartUpload(newInitiateMultipartUploadRequest(uploadObjectRequest)).getUploadId();
        return this.uploadId;
    }

    protected UploadPartResult uploadPart(UploadPartRequest uploadPartRequest) {
        return this.s3direct.uploadPart(uploadPartRequest);
    }
}
