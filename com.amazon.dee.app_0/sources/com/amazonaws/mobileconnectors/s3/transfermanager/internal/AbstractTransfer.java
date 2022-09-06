package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.event.ProgressListenerCallbackExecutor;
import com.amazonaws.event.ProgressListenerChain;
import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferProgress;
import com.amazonaws.services.s3.model.LegacyS3ProgressListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
/* loaded from: classes13.dex */
public abstract class AbstractTransfer implements Transfer {
    private final String description;
    protected TransferMonitor monitor;
    protected final ProgressListenerChain progressListenerChain;
    protected volatile Transfer.TransferState state;
    protected final Collection<TransferStateChangeListener> stateChangeListeners;
    private final TransferProgress transferProgress;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractTransfer(String str, TransferProgress transferProgress, ProgressListenerChain progressListenerChain) {
        this(str, transferProgress, progressListenerChain, null);
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.Transfer
    public synchronized void addProgressListener(ProgressListener progressListener) {
        this.progressListenerChain.addProgressListener(progressListener);
    }

    public synchronized void addStateChangeListener(TransferStateChangeListener transferStateChangeListener) {
        if (transferStateChangeListener != null) {
            this.stateChangeListeners.add(transferStateChangeListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fireProgressEvent(int i) {
        ProgressListenerCallbackExecutor.progressChanged(this.progressListenerChain, new ProgressEvent(i, 0L));
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.Transfer
    public String getDescription() {
        return this.description;
    }

    public TransferMonitor getMonitor() {
        return this.monitor;
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.Transfer
    public TransferProgress getProgress() {
        return this.transferProgress;
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.Transfer
    public synchronized Transfer.TransferState getState() {
        return this.state;
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.Transfer
    public synchronized boolean isDone() {
        boolean z;
        if (this.state != Transfer.TransferState.Failed && this.state != Transfer.TransferState.Completed) {
            if (this.state != Transfer.TransferState.Canceled) {
                z = false;
            }
        }
        z = true;
        return z;
    }

    public void notifyStateChangeListeners(Transfer.TransferState transferState) {
        for (TransferStateChangeListener transferStateChangeListener : this.stateChangeListeners) {
            transferStateChangeListener.transferStateChanged(this, transferState);
        }
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.Transfer
    public synchronized void removeProgressListener(ProgressListener progressListener) {
        this.progressListenerChain.removeProgressListener(progressListener);
    }

    public synchronized void removeStateChangeListener(TransferStateChangeListener transferStateChangeListener) {
        if (transferStateChangeListener != null) {
            this.stateChangeListeners.remove(transferStateChangeListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void rethrowExecutionException(ExecutionException executionException) {
        throw unwrapExecutionException(executionException);
    }

    public void setMonitor(TransferMonitor transferMonitor) {
        this.monitor = transferMonitor;
    }

    public void setState(Transfer.TransferState transferState) {
        synchronized (this) {
            this.state = transferState;
        }
        for (TransferStateChangeListener transferStateChangeListener : this.stateChangeListeners) {
            transferStateChangeListener.transferStateChanged(this, transferState);
        }
    }

    protected AmazonClientException unwrapExecutionException(ExecutionException executionException) {
        Throwable cause = executionException.getCause();
        if (cause instanceof AmazonClientException) {
            return (AmazonClientException) cause;
        }
        return new AmazonClientException(GeneratedOutlineSupport1.outline98(cause, GeneratedOutlineSupport1.outline107("Unable to complete transfer: ")), cause);
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.Transfer
    public void waitForCompletion() throws AmazonClientException, AmazonServiceException, InterruptedException {
        Object obj = null;
        while (true) {
            try {
                if (this.monitor.isDone() && obj != null) {
                    return;
                }
                obj = this.monitor.getFuture().get();
            } catch (ExecutionException e) {
                rethrowExecutionException(e);
                return;
            }
        }
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.Transfer
    public AmazonClientException waitForException() throws InterruptedException {
        while (!this.monitor.isDone()) {
            try {
                this.monitor.getFuture().get();
            } catch (ExecutionException e) {
                return unwrapExecutionException(e);
            }
        }
        this.monitor.getFuture().get();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractTransfer(String str, TransferProgress transferProgress, ProgressListenerChain progressListenerChain, TransferStateChangeListener transferStateChangeListener) {
        this.state = Transfer.TransferState.Waiting;
        this.stateChangeListeners = new LinkedList();
        this.description = str;
        this.progressListenerChain = progressListenerChain;
        this.transferProgress = transferProgress;
        addStateChangeListener(transferStateChangeListener);
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.Transfer
    @Deprecated
    public synchronized void addProgressListener(com.amazonaws.services.s3.model.ProgressListener progressListener) {
        this.progressListenerChain.addProgressListener(new LegacyS3ProgressListener(progressListener));
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.Transfer
    @Deprecated
    public synchronized void removeProgressListener(com.amazonaws.services.s3.model.ProgressListener progressListener) {
        this.progressListenerChain.removeProgressListener(new LegacyS3ProgressListener(progressListener));
    }
}
