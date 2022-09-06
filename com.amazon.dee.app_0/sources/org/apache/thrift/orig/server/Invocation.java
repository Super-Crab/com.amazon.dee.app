package org.apache.thrift.orig.server;

import org.apache.thrift.orig.server.AbstractNonblockingServer;
/* loaded from: classes4.dex */
class Invocation implements Runnable {
    private final AbstractNonblockingServer.FrameBuffer frameBuffer;

    public Invocation(AbstractNonblockingServer.FrameBuffer frameBuffer) {
        this.frameBuffer = frameBuffer;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.frameBuffer.invoke();
    }
}
