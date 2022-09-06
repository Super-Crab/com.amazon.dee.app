package com.amazon.communication.socket;

import com.amazon.communication.WorkExecutor;
/* loaded from: classes12.dex */
public class NotifyStateChangeCallable extends ProtocolSocketSingletonCallable {
    public NotifyStateChangeCallable(ProtocolSocketBase protocolSocketBase, WorkExecutor workExecutor) {
        super(protocolSocketBase, workExecutor);
    }

    @Override // com.amazon.communication.socket.ProtocolSocketSingletonCallable
    public void actualCall() throws Exception {
        this.mSocket.notifyStateChanged();
    }

    @Override // com.amazon.communication.socket.ProtocolSocketSingletonCallable
    public boolean preCall() {
        return true;
    }
}
