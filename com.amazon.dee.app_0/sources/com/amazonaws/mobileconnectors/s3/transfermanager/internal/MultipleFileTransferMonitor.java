package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes13.dex */
public class MultipleFileTransferMonitor implements TransferMonitor {
    private final Future<?> future = new Future<Object>() { // from class: com.amazonaws.mobileconnectors.s3.transfermanager.internal.MultipleFileTransferMonitor.1
        @Override // java.util.concurrent.Future
        public boolean cancel(boolean z) {
            return true;
        }

        @Override // java.util.concurrent.Future
        public Object get() throws InterruptedException, ExecutionException {
            Object obj = null;
            for (AbstractTransfer abstractTransfer : MultipleFileTransferMonitor.this.subTransfers) {
                obj = abstractTransfer.getMonitor().getFuture().get();
            }
            return obj;
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return MultipleFileTransferMonitor.this.transfer.getState() == Transfer.TransferState.Canceled;
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return MultipleFileTransferMonitor.this.isDone();
        }

        @Override // java.util.concurrent.Future
        public Object get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            Object obj = null;
            for (AbstractTransfer abstractTransfer : MultipleFileTransferMonitor.this.subTransfers) {
                obj = abstractTransfer.getMonitor().getFuture().get(j, timeUnit);
            }
            return obj;
        }
    };
    private final Collection<? extends AbstractTransfer> subTransfers;
    private final AbstractTransfer transfer;

    public MultipleFileTransferMonitor(AbstractTransfer abstractTransfer, Collection<? extends AbstractTransfer> collection) {
        this.subTransfers = collection;
        this.transfer = abstractTransfer;
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.internal.TransferMonitor
    public Future<?> getFuture() {
        return this.future;
    }

    @Override // com.amazonaws.mobileconnectors.s3.transfermanager.internal.TransferMonitor
    public synchronized boolean isDone() {
        for (AbstractTransfer abstractTransfer : this.subTransfers) {
            if (!abstractTransfer.isDone()) {
                return false;
            }
        }
        return true;
    }
}
