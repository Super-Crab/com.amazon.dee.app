package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.event.ProgressListenerChain;
import com.amazonaws.mobileconnectors.s3.transfermanager.Download;
import com.amazonaws.mobileconnectors.s3.transfermanager.MultipleFileDownload;
import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferProgress;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
/* loaded from: classes13.dex */
public class MultipleFileDownloadImpl extends MultipleFileTransfer<Download> implements MultipleFileDownload {
    private final String bucketName;
    private final String keyPrefix;

    public MultipleFileDownloadImpl(String str, TransferProgress transferProgress, ProgressListenerChain progressListenerChain, String str2, String str3, Collection<? extends Download> collection) {
        super(str, transferProgress, progressListenerChain, collection);
        this.keyPrefix = str2;
        this.bucketName = str3;
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.MultipleFileDownload
    public void abort() throws IOException {
        Iterator it2 = this.subTransfers.iterator();
        while (it2.hasNext()) {
            ((DownloadImpl) it2.next()).abortWithoutNotifyingStateChangeListener();
        }
        Iterator it3 = this.subTransfers.iterator();
        while (it3.hasNext()) {
            ((DownloadImpl) it3.next()).notifyStateChangeListeners(Transfer.TransferState.Canceled);
        }
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.MultipleFileDownload
    public String getBucketName() {
        return this.bucketName;
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.MultipleFileDownload
    public String getKeyPrefix() {
        return this.keyPrefix;
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.internal.AbstractTransfer, com.amazonaws.mobileconnectors.s3.transfermanager.Transfer
    public void waitForCompletion() throws AmazonClientException, AmazonServiceException, InterruptedException {
        if (this.subTransfers.isEmpty()) {
            return;
        }
        super.waitForCompletion();
    }
}
