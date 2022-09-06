package org.bouncycastle.tls;

import java.io.IOException;
import org.bouncycastle.tls.crypto.TlsCrypto;
/* loaded from: classes5.dex */
public interface TlsPeer {
    boolean allowLegacyResumption();

    void cancel() throws IOException;

    int[] getCipherSuites();

    /* renamed from: getCrypto */
    TlsCrypto mo12857getCrypto();

    int getHandshakeTimeoutMillis();

    TlsHeartbeat getHeartbeat();

    short getHeartbeatPolicy();

    TlsKeyExchangeFactory getKeyExchangeFactory() throws IOException;

    ProtocolVersion[] getProtocolVersions();

    int getRenegotiationPolicy();

    void notifyAlertRaised(short s, short s2, String str, Throwable th);

    void notifyAlertReceived(short s, short s2);

    void notifyCloseHandle(TlsCloseable tlsCloseable);

    void notifyHandshakeBeginning() throws IOException;

    void notifyHandshakeComplete() throws IOException;

    void notifySecureRenegotiation(boolean z) throws IOException;

    boolean requiresExtendedMasterSecret();

    boolean shouldCheckSigAlgOfPeerCerts();

    boolean shouldUseExtendedMasterSecret();

    boolean shouldUseExtendedPadding();

    boolean shouldUseGMTUnixTime();
}
