package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.event.ProgressListenerChain;
import com.amazonaws.mobileconnectors.s3.transfermanager.PauseResult;
import com.amazonaws.mobileconnectors.s3.transfermanager.PauseStatus;
import com.amazonaws.mobileconnectors.s3.transfermanager.PersistableUpload;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferProgress;
import com.amazonaws.mobileconnectors.s3.transfermanager.Upload;
import com.amazonaws.mobileconnectors.s3.transfermanager.exception.PauseException;
import com.amazonaws.mobileconnectors.s3.transfermanager.model.UploadResult;
import java.util.concurrent.ExecutionException;
/* loaded from: classes13.dex */
public class UploadImpl extends AbstractTransfer implements Upload {
    public UploadImpl(String str, TransferProgress transferProgress, ProgressListenerChain progressListenerChain, TransferStateChangeListener transferStateChangeListener) {
        super(str, transferProgress, progressListenerChain, transferStateChangeListener);
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.Upload
    public void abort() {
        ((UploadMonitor) this.monitor).performAbort();
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.Upload
    public PersistableUpload pause() throws PauseException {
        PauseResult<PersistableUpload> pause = pause(true);
        if (pause.getPauseStatus() == PauseStatus.SUCCESS) {
            return pause.getInfoToResume();
        }
        throw new PauseException(pause.getPauseStatus());
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.Upload
    public PauseResult<PersistableUpload> tryPause(boolean z) {
        return pause(z);
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.Upload
    public UploadResult waitForUploadResult() throws AmazonClientException, AmazonServiceException, InterruptedException {
        UploadResult uploadResult = null;
        while (true) {
            try {
                if (this.monitor.isDone() && uploadResult != null) {
                    return uploadResult;
                }
                uploadResult = (UploadResult) this.monitor.getFuture().get();
            } catch (ExecutionException e) {
                rethrowExecutionException(e);
                return null;
            }
        }
    }

    private PauseResult<PersistableUpload> pause(boolean z) throws AmazonClientException {
        return ((UploadMonitor) this.monitor).pause(z);
    }
}
