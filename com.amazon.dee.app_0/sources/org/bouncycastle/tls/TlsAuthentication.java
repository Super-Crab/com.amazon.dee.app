package org.bouncycastle.tls;

import java.io.IOException;
/* loaded from: classes5.dex */
public interface TlsAuthentication {
    TlsCredentials getClientCredentials(CertificateRequest certificateRequest) throws IOException;

    void notifyServerCertificate(TlsServerCertificate tlsServerCertificate) throws IOException;
}
