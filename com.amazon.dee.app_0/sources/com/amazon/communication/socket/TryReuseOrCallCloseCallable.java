package com.amazon.communication.socket;

import com.amazon.communication.ThreadName;
import com.amazon.communication.WorkExecutor;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.FailFast;
import com.dp.utils.ThreadGuard;
/* loaded from: classes12.dex */
public class TryReuseOrCallCloseCallable extends ProtocolSocketSingletonCallable {
    private final int mDelayCloseTimeInMillis;
    private boolean mIsSocketReused;
    private static final DPLogger log = new DPLogger("TComm.TryReuseOrCallCloseCallable");
    private static final CloseDetail CLEAN_CLOSE = new CloseDetail(1000, "Clean close after no reuse");

    public TryReuseOrCallCloseCallable(ProtocolSocketBase protocolSocketBase, WorkExecutor workExecutor, int i) {
        super(protocolSocketBase, workExecutor);
        this.mDelayCloseTimeInMillis = i;
        this.mIsSocketReused = false;
    }

    @Override // com.amazon.communication.socket.ProtocolSocketSingletonCallable
    public void actualCall() throws Exception {
        boolean z;
        ThreadGuard.ensureThreadPrefix(ThreadName.HANDLER);
        synchronized (this) {
            z = this.mIsSocketReused;
            this.mIsSocketReused = false;
        }
        if (!z) {
            try {
                if (!this.mSocket.isLargeMessageTransactionInProgress()) {
                    log.verbose("actualCall", "no reuse, close the socket", new Object[0]);
                    FailFast.expectEquals(0, this.mSocket.getRefCount(), "Reference count should be 0 if no reuse");
                    this.mSocket.close(CLEAN_CLOSE);
                }
            } catch (Exception e) {
                log.error("actualCall", "log exception", e);
                return;
            }
        }
        log.verbose("actualCall", "there is either recent reuse or a large message transaction still in process, don't close the socket", "isSocketReused", Boolean.valueOf(z), "mSocket.largeMessageTransactionInProgress()", Boolean.valueOf(this.mSocket.isLargeMessageTransactionInProgress()));
        if (this.mSocket.getRefCount() == 0) {
            log.verbose("actualCall", "reschedule the task as refCount == 0", new Object[0]);
            enqueueAfter(this.mDelayCloseTimeInMillis);
        }
    }

    @Override // com.amazon.communication.socket.ProtocolSocketSingletonCallable
    public synchronized boolean enqueueAfter(long j) {
        boolean enqueueAfter;
        enqueueAfter = super.enqueueAfter(j);
        if (!enqueueAfter) {
            log.verbose("enqueueAfter", "scheduled socket close task.", new Object[0]);
        } else {
            log.verbose("enqueueAfter", "a socket close task is already scheduled. nothing to do.", new Object[0]);
        }
        return enqueueAfter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void tryReuseSocket() {
        if (this.mIsQueued) {
            this.mIsSocketReused = true;
            log.verbose("tryReuseSocket", "this is a reuse of this socket", new Object[0]);
        }
    }
}
