package org.bouncycastle.tls;
/* loaded from: classes5.dex */
public interface TlsHeartbeat {
    byte[] generatePayload();

    int getIdleMillis();

    int getTimeoutMillis();
}
