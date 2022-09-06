package org.apache.thrift.orig.server;

import org.apache.thrift.orig.server.TServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes4.dex */
public class TSimpleServer extends TServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TSimpleServer.class.getName());
    private boolean stopped_;

    public TSimpleServer(TServer.AbstractServerArgs abstractServerArgs) {
        super(abstractServerArgs);
        this.stopped_ = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00ad A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0013 A[SYNTHETIC] */
    @Override // org.apache.thrift.orig.server.TServer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void serve() {
        /*
            Method dump skipped, instructions count: 191
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.thrift.orig.server.TSimpleServer.serve():void");
    }

    @Override // org.apache.thrift.orig.server.TServer
    public void stop() {
        this.stopped_ = true;
        this.serverTransport_.interrupt();
    }
}
