package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListenerCallbackExecutor;
import com.amazonaws.event.ProgressListenerChain;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.mobileconnectors.s3.transfermanager.PersistableUpload;
import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferManager;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferManagerConfiguration;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferProgress;
import com.amazonaws.mobileconnectors.s3.transfermanager.model.UploadResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3EncryptionClient;
import com.amazonaws.services.s3.model.AbortMultipartUploadRequest;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.CompleteMultipartUploadResult;
import com.amazonaws.services.s3.model.EncryptedInitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.EncryptedPutObjectRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.ListPartsRequest;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.PartListing;
import com.amazonaws.services.s3.model.PartSummary;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.StorageClass;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
/* loaded from: classes13.dex */
public class UploadCallable implements Callable<UploadResult> {
    private static final Log log = LogFactory.getLog(UploadCallable.class);
    private final TransferManagerConfiguration configuration;
    private final ProgressListenerChain listener;
    private String multipartUploadId;
    private PersistableUpload persistableUpload;
    private final PutObjectRequest putObjectRequest;
    private final AmazonS3 s3;
    private final ExecutorService threadPool;
    private final TransferProgress transferProgress;
    private final UploadImpl upload;
    private final List<Future<PartETag>> futures = new ArrayList();
    private final List<PartETag> eTagsToSkip = new ArrayList();

    public UploadCallable(TransferManager transferManager, ExecutorService executorService, UploadImpl uploadImpl, PutObjectRequest putObjectRequest, ProgressListenerChain progressListenerChain, String str, TransferProgress transferProgress) {
        this.s3 = transferManager.getAmazonS3Client();
        this.configuration = transferManager.getConfiguration();
        this.threadPool = executorService;
        this.putObjectRequest = putObjectRequest;
        this.listener = progressListenerChain;
        this.upload = uploadImpl;
        this.multipartUploadId = str;
        this.transferProgress = transferProgress;
    }

    private void captureUploadStateIfPossible() {
        if (this.putObjectRequest.getSSECustomerKey() == null) {
            this.persistableUpload = new PersistableUpload(this.putObjectRequest.getBucketName(), this.putObjectRequest.getKey(), this.putObjectRequest.getFile().getAbsolutePath(), this.multipartUploadId, this.configuration.getMinimumUploadPartSize(), this.configuration.getMultipartUploadThreshold());
            notifyPersistableTransferAvailability();
        }
    }

    private void fireProgressEvent(int i) {
        ProgressEvent progressEvent = new ProgressEvent(0L);
        progressEvent.setEventCode(i);
        ProgressListenerCallbackExecutor.progressChanged(this.listener, progressEvent);
    }

    private long getOptimalPartSize(boolean z) {
        long calculateOptimalPartSize = TransferManagerUtils.calculateOptimalPartSize(this.putObjectRequest, this.configuration);
        if (z) {
            long j = calculateOptimalPartSize % 32;
            if (j > 0) {
                calculateOptimalPartSize = (calculateOptimalPartSize - j) + 32;
            }
        }
        Log log2 = log;
        log2.debug("Calculated optimal part size: " + calculateOptimalPartSize);
        return calculateOptimalPartSize;
    }

    private Map<Integer, PartSummary> identifyExistingPartsForResume(String str) {
        HashMap hashMap = new HashMap();
        if (str == null) {
            return hashMap;
        }
        int i = 0;
        while (true) {
            PartListing listParts = this.s3.listParts(new ListPartsRequest(this.putObjectRequest.getBucketName(), this.putObjectRequest.getKey(), str).withPartNumberMarker(Integer.valueOf(i)));
            for (PartSummary partSummary : listParts.getParts()) {
                hashMap.put(Integer.valueOf(partSummary.getPartNumber()), partSummary);
            }
            if (!listParts.isTruncated()) {
                return hashMap;
            }
            i = listParts.getNextPartNumberMarker().intValue();
        }
    }

    private String initiateMultipartUpload(PutObjectRequest putObjectRequest, boolean z) {
        InitiateMultipartUploadRequest withObjectMetadata;
        if (z && (putObjectRequest instanceof EncryptedPutObjectRequest)) {
            withObjectMetadata = new EncryptedInitiateMultipartUploadRequest(putObjectRequest.getBucketName(), putObjectRequest.getKey()).withCannedACL(putObjectRequest.getCannedAcl()).withObjectMetadata(putObjectRequest.getMetadata());
            ((EncryptedInitiateMultipartUploadRequest) withObjectMetadata).setMaterialsDescription(((EncryptedPutObjectRequest) putObjectRequest).getMaterialsDescription());
        } else {
            withObjectMetadata = new InitiateMultipartUploadRequest(putObjectRequest.getBucketName(), putObjectRequest.getKey()).withCannedACL(putObjectRequest.getCannedAcl()).withObjectMetadata(putObjectRequest.getMetadata());
        }
        TransferManager.appendMultipartUserAgent(withObjectMetadata);
        if (putObjectRequest.getStorageClass() != null) {
            withObjectMetadata.setStorageClass(StorageClass.fromValue(putObjectRequest.getStorageClass()));
        }
        if (putObjectRequest.getRedirectLocation() != null) {
            withObjectMetadata.setRedirectLocation(putObjectRequest.getRedirectLocation());
        }
        if (putObjectRequest.getSSECustomerKey() != null) {
            withObjectMetadata.setSSECustomerKey(putObjectRequest.getSSECustomerKey());
        }
        String uploadId = this.s3.initiateMultipartUpload(withObjectMetadata).getUploadId();
        Log log2 = log;
        log2.debug("Initiated new multipart upload: " + uploadId);
        return uploadId;
    }

    private void notifyPersistableTransferAvailability() {
        S3ProgressPublisher.publishTransferPersistable(this.listener, this.persistableUpload);
    }

    private UploadResult uploadInOneChunk() {
        PutObjectResult putObject = this.s3.putObject(this.putObjectRequest);
        UploadResult uploadResult = new UploadResult();
        uploadResult.setBucketName(this.putObjectRequest.getBucketName());
        uploadResult.setKey(this.putObjectRequest.getKey());
        uploadResult.setETag(putObject.getETag());
        uploadResult.setVersionId(putObject.getVersionId());
        return uploadResult;
    }

    private UploadResult uploadInParts() throws Exception {
        boolean z = this.s3 instanceof AmazonS3EncryptionClient;
        long optimalPartSize = getOptimalPartSize(z);
        if (this.multipartUploadId == null) {
            this.multipartUploadId = initiateMultipartUpload(this.putObjectRequest, z);
        }
        try {
            try {
                UploadPartRequestFactory uploadPartRequestFactory = new UploadPartRequestFactory(this.putObjectRequest, this.multipartUploadId, optimalPartSize);
                if (TransferManagerUtils.isUploadParallelizable(this.putObjectRequest, z)) {
                    captureUploadStateIfPossible();
                    uploadPartsInParallel(uploadPartRequestFactory, this.multipartUploadId);
                    return null;
                }
                UploadResult uploadPartsInSeries = uploadPartsInSeries(uploadPartRequestFactory);
                if (this.putObjectRequest.getInputStream() != null) {
                    try {
                        this.putObjectRequest.getInputStream().close();
                    } catch (Exception e) {
                        Log log2 = log;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to cleanly close input stream: ");
                        outline107.append(e.getMessage());
                        log2.warn(outline107.toString(), e);
                    }
                }
                return uploadPartsInSeries;
            } catch (Exception e2) {
                fireProgressEvent(8);
                performAbortMultipartUpload();
                throw e2;
            }
        } finally {
            if (this.putObjectRequest.getInputStream() != null) {
                try {
                    this.putObjectRequest.getInputStream().close();
                } catch (Exception e3) {
                    Log log3 = log;
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unable to cleanly close input stream: ");
                    outline1072.append(e3.getMessage());
                    log3.warn(outline1072.toString(), e3);
                }
            }
        }
    }

    private void uploadPartsInParallel(UploadPartRequestFactory uploadPartRequestFactory, String str) {
        Map<Integer, PartSummary> identifyExistingPartsForResume = identifyExistingPartsForResume(str);
        while (uploadPartRequestFactory.hasMoreRequests()) {
            if (!this.threadPool.isShutdown()) {
                UploadPartRequest nextUploadPartRequest = uploadPartRequestFactory.getNextUploadPartRequest();
                if (identifyExistingPartsForResume.containsKey(Integer.valueOf(nextUploadPartRequest.getPartNumber()))) {
                    PartSummary partSummary = identifyExistingPartsForResume.get(Integer.valueOf(nextUploadPartRequest.getPartNumber()));
                    this.eTagsToSkip.add(new PartETag(nextUploadPartRequest.getPartNumber(), partSummary.getETag()));
                    this.transferProgress.updateProgress(partSummary.getSize());
                } else {
                    this.futures.add(this.threadPool.submit(new UploadPartCallable(this.s3, nextUploadPartRequest)));
                }
            } else {
                throw new CancellationException("TransferManager has been shutdown");
            }
        }
    }

    private UploadResult uploadPartsInSeries(UploadPartRequestFactory uploadPartRequestFactory) {
        ArrayList arrayList = new ArrayList();
        while (uploadPartRequestFactory.hasMoreRequests()) {
            if (!this.threadPool.isShutdown()) {
                UploadPartRequest nextUploadPartRequest = uploadPartRequestFactory.getNextUploadPartRequest();
                InputStream inputStream = nextUploadPartRequest.getInputStream();
                if (inputStream != null && inputStream.markSupported()) {
                    if (nextUploadPartRequest.getPartSize() >= 2147483647L) {
                        inputStream.mark(Integer.MAX_VALUE);
                    } else {
                        inputStream.mark((int) nextUploadPartRequest.getPartSize());
                    }
                }
                arrayList.add(this.s3.uploadPart(nextUploadPartRequest).getPartETag());
            } else {
                throw new CancellationException("TransferManager has been shutdown");
            }
        }
        CompleteMultipartUploadResult completeMultipartUpload = this.s3.completeMultipartUpload(new CompleteMultipartUploadRequest(this.putObjectRequest.getBucketName(), this.putObjectRequest.getKey(), this.multipartUploadId, arrayList));
        UploadResult uploadResult = new UploadResult();
        uploadResult.setBucketName(completeMultipartUpload.getBucketName());
        uploadResult.setKey(completeMultipartUpload.getKey());
        uploadResult.setETag(completeMultipartUpload.getETag());
        uploadResult.setVersionId(completeMultipartUpload.getVersionId());
        return uploadResult;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<PartETag> getETags() {
        return this.eTagsToSkip;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Future<PartETag>> getFutures() {
        return this.futures;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getMultipartUploadId() {
        return this.multipartUploadId;
    }

    public PersistableUpload getPersistableUpload() {
        return this.persistableUpload;
    }

    public boolean isMultipartUpload() {
        return TransferManagerUtils.shouldUseMultipartUpload(this.putObjectRequest, this.configuration);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void performAbortMultipartUpload() {
        try {
            if (this.multipartUploadId == null) {
                return;
            }
            this.s3.abortMultipartUpload(new AbortMultipartUploadRequest(this.putObjectRequest.getBucketName(), this.putObjectRequest.getKey(), this.multipartUploadId));
        } catch (Exception e) {
            Log log2 = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to abort multipart upload, you may need to manually remove uploaded parts: ");
            outline107.append(e.getMessage());
            log2.info(outline107.toString(), e);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    /* renamed from: call */
    public UploadResult mo6699call() throws Exception {
        this.upload.setState(Transfer.TransferState.InProgress);
        if (isMultipartUpload()) {
            fireProgressEvent(2);
            return uploadInParts();
        }
        return uploadInOneChunk();
    }
}
