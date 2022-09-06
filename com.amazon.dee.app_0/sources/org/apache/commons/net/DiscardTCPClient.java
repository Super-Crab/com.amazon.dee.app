package org.apache.commons.net;

import java.io.OutputStream;
/* loaded from: classes4.dex */
public class DiscardTCPClient extends SocketClient {
    public static final int DEFAULT_PORT = 9;

    public DiscardTCPClient() {
        setDefaultPort(9);
    }

    public OutputStream getOutputStream() {
        return this._output_;
    }
}
