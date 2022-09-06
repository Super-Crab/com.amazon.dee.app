package org.bouncycastle.tls;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
/* loaded from: classes5.dex */
public interface TlsClient extends TlsPeer {
    TlsAuthentication getAuthentication() throws IOException;

    Hashtable getClientExtensions() throws IOException;

    Vector getClientSupplementalData() throws IOException;

    TlsDHGroupVerifier getDHGroupVerifier() throws IOException;

    Vector getEarlyKeyShareGroups();

    TlsPSKIdentity getPSKIdentity() throws IOException;

    TlsSRPConfigVerifier getSRPConfigVerifier() throws IOException;

    TlsSRPIdentity getSRPIdentity() throws IOException;

    TlsSession getSessionToResume();

    void init(TlsClientContext tlsClientContext);

    boolean isFallback();

    void notifyNewSessionTicket(NewSessionTicket newSessionTicket) throws IOException;

    void notifySelectedCipherSuite(int i);

    void notifyServerVersion(ProtocolVersion protocolVersion) throws IOException;

    void notifySessionID(byte[] bArr);

    void processServerExtensions(Hashtable hashtable) throws IOException;

    void processServerSupplementalData(Vector vector) throws IOException;
}
