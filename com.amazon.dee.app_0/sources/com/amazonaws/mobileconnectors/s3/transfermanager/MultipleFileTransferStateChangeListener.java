package com.amazonaws.mobileconnectors.s3.transfermanager;

import com.amazonaws.AmazonClientException;
import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.MultipleFileTransfer;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.TransferStateChangeListener;
import java.util.concurrent.CountDownLatch;
/* compiled from: MultipleFileTransferChangeStateListener.java */
/* loaded from: classes13.dex */
final class MultipleFileTransferStateChangeListener implements TransferStateChangeListener {
    private final CountDownLatch latch;
    private final MultipleFileTransfer<?> multipleFileTransfer;

    public MultipleFileTransferStateChangeListener(CountDownLatch countDownLatch, MultipleFileTransfer<?> multipleFileTransfer) {
        this.latch = countDownLatch;
        this.multipleFileTransfer = multipleFileTransfer;
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.internal.TransferStateChangeListener
    public void transferStateChanged(Transfer transfer, Transfer.TransferState transferState) {
        try {
            this.latch.await();
            synchronized (this.multipleFileTransfer) {
                if (this.multipleFileTransfer.getState() != transferState && !this.multipleFileTransfer.isDone()) {
                    if (transferState == Transfer.TransferState.InProgress) {
                        this.multipleFileTransfer.setState(transferState);
                    } else if (this.multipleFileTransfer.getMonitor().isDone()) {
                        this.multipleFileTransfer.collateFinalState();
                    } else {
                        this.multipleFileTransfer.setState(Transfer.TransferState.InProgress);
                    }
                }
            }
        } catch (InterruptedException unused) {
            throw new AmazonClientException("Couldn't wait for all downloads to be queued");
        }
    }
}
