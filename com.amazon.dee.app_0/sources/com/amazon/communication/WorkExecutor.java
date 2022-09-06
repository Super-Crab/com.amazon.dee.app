package com.amazon.communication;

import com.amazon.communication.socket.ProtocolSocket;
import java.util.concurrent.Callable;
/* loaded from: classes12.dex */
public interface WorkExecutor {
    void doBackgroundWork(Callable<Void> callable);

    void doBackgroundWorkAfter(Callable<Void> callable, long j);

    void enqueueWork(ProtocolSocket protocolSocket, Callable<Void> callable);

    void enqueueWorkAfter(ProtocolSocket protocolSocket, Callable<Void> callable, long j);

    void shutdown();
}
