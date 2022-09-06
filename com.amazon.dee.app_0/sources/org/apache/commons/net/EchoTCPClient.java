package org.apache.commons.net;

import java.io.InputStream;
/* loaded from: classes4.dex */
public final class EchoTCPClient extends DiscardTCPClient {
    public static final int DEFAULT_PORT = 7;

    public EchoTCPClient() {
        setDefaultPort(7);
    }

    public InputStream getInputStream() {
        return this._input_;
    }
}
