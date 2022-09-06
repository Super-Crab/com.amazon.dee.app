package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import com.amazonaws.AmazonClientException;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListenerCallbackExecutor;
import com.amazonaws.event.ProgressListenerChain;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.mobileconnectors.s3.transfermanager.PauseResult;
import com.amazonaws.mobileconnectors.s3.transfermanager.PauseStatus;
import com.amazonaws.mobileconnectors.s3.transfermanager.PersistableUpload;
import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferManager;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferManagerConfiguration;
import com.amazonaws.mobileconnectors.s3.transfermanager.model.UploadResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.CompleteMultipartUploadResult;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class UploadMonitor implements Callable<UploadResult>, TransferMonitor {
    private static final Log log = LogFactory.getLog(UploadMonitor.class);
    private final TransferManagerConfiguration configuration;
    private final UploadCallable multipartUploadCallable;
    private Future<UploadResult> nextFuture;
    private final ProgressListenerCallbackExecutor progressListenerChainCallbackExecutor;
    private final PutObjectRequest putObjectRequest;
    private final AmazonS3 s3;
    private final ExecutorService threadPool;
    private ScheduledExecutorService timedThreadPool;
    private final UploadImpl transfer;
    private String uploadId;
    private final List<Future<PartETag>> futures = new ArrayList();
    private boolean isUploadDone = false;
    private int pollInterval = 5000;

    public UploadMonitor(TransferManager transferManager, UploadImpl uploadImpl, ExecutorService executorService, UploadCallable uploadCallable, PutObjectRequest putObjectRequest, ProgressListenerChain progressListenerChain) {
        this.s3 = transferManager.getAmazonS3Client();
        this.configuration = transferManager.getConfiguration();
        this.multipartUploadCallable = uploadCallable;
        this.threadPool = executorService;
        this.putObjectRequest = putObjectRequest;
        this.progressListenerChainCallbackExecutor = ProgressListenerCallbackExecutor.wrapListener(progressListenerChain);
        this.transfer = uploadImpl;
        setNextFuture(executorService.submit(this));
    }

    private void cancelFutures() {
        this.nextFuture.cancel(true);
        for (Future<PartETag> future : this.futures) {
            future.cancel(true);
        }
        this.multipartUploadCallable.getFutures().clear();
        this.futures.clear();
    }

    private List<PartETag> collectPartETags() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.multipartUploadCallable.getETags());
        for (Future<PartETag> future : this.futures) {
            try {
                arrayList.add(future.get());
            } catch (Exception e) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to upload part: ");
                outline107.append(e.getCause().getMessage());
                throw new AmazonClientException(outline107.toString(), e.getCause());
            }
        }
        return arrayList;
    }

    private UploadResult completeMultipartUpload() {
        CompleteMultipartUploadResult completeMultipartUpload = this.s3.completeMultipartUpload(new CompleteMultipartUploadRequest(this.putObjectRequest.getBucketName(), this.putObjectRequest.getKey(), this.uploadId, collectPartETags()));
        uploadComplete();
        UploadResult uploadResult = new UploadResult();
        uploadResult.setBucketName(completeMultipartUpload.getBucketName());
        uploadResult.setKey(completeMultipartUpload.getKey());
        uploadResult.setETag(completeMultipartUpload.getETag());
        uploadResult.setVersionId(completeMultipartUpload.getVersionId());
        return uploadResult;
    }

    private void fireProgressEvent(int i) {
        if (this.progressListenerChainCallbackExecutor == null) {
            return;
        }
        ProgressEvent progressEvent = new ProgressEvent(0L);
        progressEvent.setEventCode(i);
        this.progressListenerChainCallbackExecutor.progressChanged(progressEvent);
    }

    private synchronized void markAllDone() {
        this.isUploadDone = true;
    }

    private UploadResult poll() throws InterruptedException {
        for (Future<PartETag> future : this.futures) {
            if (!future.isDone()) {
                reschedule();
                return null;
            }
        }
        for (Future<PartETag> future2 : this.futures) {
            if (future2.isCancelled()) {
                throw new CancellationException();
            }
        }
        return completeMultipartUpload();
    }

    private void reschedule() {
        setNextFuture(this.timedThreadPool.schedule(new Callable<UploadResult>() { // from class: com.amazonaws.mobileconnectors.s3.transfermanager.internal.UploadMonitor.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public UploadResult mo6701call() throws Exception {
                UploadMonitor uploadMonitor = UploadMonitor.this;
                uploadMonitor.setNextFuture(uploadMonitor.threadPool.submit(UploadMonitor.this));
                return null;
            }
        }, this.pollInterval, TimeUnit.MILLISECONDS));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setNextFuture(Future<UploadResult> future) {
        this.nextFuture = future;
    }

    private UploadResult upload() throws Exception, InterruptedException {
        UploadResult mo6699call = this.multipartUploadCallable.mo6699call();
        if (mo6699call != null) {
            uploadComplete();
        } else {
            this.uploadId = this.multipartUploadCallable.getMultipartUploadId();
            this.futures.addAll(this.multipartUploadCallable.getFutures());
            reschedule();
        }
        return mo6699call;
    }

    private void uploadComplete() {
        markAllDone();
        this.transfer.setState(Transfer.TransferState.Completed);
        if (this.multipartUploadCallable.isMultipartUpload()) {
            fireProgressEvent(4);
        }
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.internal.TransferMonitor
    public synchronized Future<UploadResult> getFuture() {
        return this.nextFuture;
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.internal.TransferMonitor
    public synchronized boolean isDone() {
        return this.isUploadDone;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PauseResult<PersistableUpload> pause(boolean z) {
        PersistableUpload persistableUpload = this.multipartUploadCallable.getPersistableUpload();
        if (persistableUpload == null) {
            PauseStatus determinePauseStatus = TransferManagerUtils.determinePauseStatus(this.transfer.getState(), z);
            if (z) {
                cancelFutures();
                this.multipartUploadCallable.performAbortMultipartUpload();
            }
            return new PauseResult<>(determinePauseStatus);
        }
        cancelFutures();
        return new PauseResult<>(PauseStatus.SUCCESS, persistableUpload);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void performAbort() {
        cancelFutures();
        this.multipartUploadCallable.performAbortMultipartUpload();
    }

    public void setTimedThreadPool(ScheduledExecutorService scheduledExecutorService) {
        this.timedThreadPool = scheduledExecutorService;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    /* renamed from: call */
    public UploadResult mo6700call() throws Exception {
        try {
            if (this.uploadId == null) {
                return upload();
            }
            return poll();
        } catch (CancellationException unused) {
            this.transfer.setState(Transfer.TransferState.Canceled);
            fireProgressEvent(16);
            throw new AmazonClientException("Upload canceled");
        } catch (Exception e) {
            this.transfer.setState(Transfer.TransferState.Failed);
            fireProgressEvent(8);
            throw e;
        }
    }
}
