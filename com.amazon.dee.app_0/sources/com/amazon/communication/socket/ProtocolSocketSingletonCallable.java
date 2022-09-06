package com.amazon.communication.socket;

import com.amazon.communication.PowerManagerWrapper;
import com.amazon.communication.WorkExecutor;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.dp.logger.DPLogger;
import java.util.concurrent.Callable;
/* loaded from: classes12.dex */
public abstract class ProtocolSocketSingletonCallable implements Callable<Void> {
    private static final DPLogger log = new DPLogger("TComm.ProtocolSocketSingletonCallable");
    protected boolean mIsQueued;
    private final PowerManagerWrapper mPowerManager;
    protected final ProtocolSocketBase mSocket;
    private final WorkExecutor mWorkExecutor;

    public ProtocolSocketSingletonCallable(ProtocolSocketBase protocolSocketBase, WorkExecutor workExecutor) {
        this.mSocket = protocolSocketBase;
        this.mWorkExecutor = workExecutor;
        this.mPowerManager = null;
    }

    public abstract void actualCall() throws Exception;

    public synchronized boolean enqueue() {
        boolean z;
        log.debug("enqueue", "enqueuing", "mPowerManager", this.mPowerManager);
        z = this.mIsQueued;
        if (!this.mIsQueued) {
            this.mIsQueued = true;
            this.mWorkExecutor.enqueueWork(this.mSocket, this);
        }
        return z;
    }

    public synchronized boolean enqueueAfter(long j) {
        boolean z;
        log.debug("enqueueAfter", "enqueuing", "mPowerManager", this.mPowerManager);
        z = this.mIsQueued;
        if (!this.mIsQueued) {
            this.mIsQueued = true;
            this.mWorkExecutor.enqueueWorkAfter(this.mSocket, this, j);
        }
        return z;
    }

    public boolean preCall() {
        ProtocolSocket.ProtocolSocketState socketState = this.mSocket.socketState();
        if (socketState == ProtocolSocket.ProtocolSocketState.DISCONNECTED || socketState == ProtocolSocket.ProtocolSocketState.DISCONNECTING) {
            log.debug("preCall", "Nothing to do, socket was closed already", "mSocket", this.mSocket, "socketState", socketState);
            return false;
        }
        return true;
    }

    @Override // java.util.concurrent.Callable
    public Void call() throws Exception {
        synchronized (this) {
            this.mIsQueued = false;
        }
        if (preCall()) {
            actualCall();
            return null;
        }
        return null;
    }

    public ProtocolSocketSingletonCallable(ProtocolSocketBase protocolSocketBase, WorkExecutor workExecutor, PowerManagerWrapper powerManagerWrapper) {
        this.mSocket = protocolSocketBase;
        this.mWorkExecutor = workExecutor;
        this.mPowerManager = powerManagerWrapper;
    }
}
