package org.apache.thrift.orig.transport;

import java.util.Map;
import javax.security.auth.callback.CallbackHandler;
import javax.security.sasl.Sasl;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import org.apache.thrift.orig.transport.TSaslTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes4.dex */
public class TSaslClientTransport extends TSaslTransport {
    private static final Logger LOGGER = LoggerFactory.getLogger(TSaslClientTransport.class);
    private final String mechanism;

    public TSaslClientTransport(SaslClient saslClient, TTransport tTransport) {
        super(saslClient, tTransport);
        this.mechanism = saslClient.getMechanismName();
    }

    @Override // org.apache.thrift.orig.transport.TSaslTransport, org.apache.thrift.orig.transport.TTransport
    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    @Override // org.apache.thrift.orig.transport.TSaslTransport, org.apache.thrift.orig.transport.TTransport
    public /* bridge */ /* synthetic */ void flush() throws TTransportException {
        super.flush();
    }

    @Override // org.apache.thrift.orig.transport.TSaslTransport
    protected TSaslTransport.SaslRole getRole() {
        return TSaslTransport.SaslRole.CLIENT;
    }

    @Override // org.apache.thrift.orig.transport.TSaslTransport
    public /* bridge */ /* synthetic */ SaslClient getSaslClient() {
        return super.getSaslClient();
    }

    @Override // org.apache.thrift.orig.transport.TSaslTransport
    public /* bridge */ /* synthetic */ SaslServer getSaslServer() {
        return super.getSaslServer();
    }

    @Override // org.apache.thrift.orig.transport.TSaslTransport
    public /* bridge */ /* synthetic */ TTransport getUnderlyingTransport() {
        return super.getUnderlyingTransport();
    }

    @Override // org.apache.thrift.orig.transport.TSaslTransport
    protected void handleSaslStartMessage() throws TTransportException, SaslException {
        SaslClient saslClient = getSaslClient();
        byte[] bArr = new byte[0];
        if (saslClient.hasInitialResponse()) {
            bArr = saslClient.evaluateChallenge(bArr);
        }
        LOGGER.debug("Sending mechanism name {} and initial response of length {}", this.mechanism, Integer.valueOf(bArr.length));
        sendSaslMessage(TSaslTransport.NegotiationStatus.START, this.mechanism.getBytes());
        sendSaslMessage(saslClient.isComplete() ? TSaslTransport.NegotiationStatus.COMPLETE : TSaslTransport.NegotiationStatus.OK, bArr);
        this.underlyingTransport.flush();
    }

    @Override // org.apache.thrift.orig.transport.TSaslTransport, org.apache.thrift.orig.transport.TTransport
    public /* bridge */ /* synthetic */ boolean isOpen() {
        return super.isOpen();
    }

    @Override // org.apache.thrift.orig.transport.TSaslTransport, org.apache.thrift.orig.transport.TTransport
    public /* bridge */ /* synthetic */ void open() throws TTransportException {
        super.open();
    }

    @Override // org.apache.thrift.orig.transport.TSaslTransport, org.apache.thrift.orig.transport.TTransport
    public /* bridge */ /* synthetic */ int read(byte[] bArr, int i, int i2) throws TTransportException {
        return super.read(bArr, i, i2);
    }

    @Override // org.apache.thrift.orig.transport.TSaslTransport, org.apache.thrift.orig.transport.TTransport
    public /* bridge */ /* synthetic */ void write(byte[] bArr, int i, int i2) throws TTransportException {
        super.write(bArr, i, i2);
    }

    public TSaslClientTransport(String str, String str2, String str3, String str4, Map<String, String> map, CallbackHandler callbackHandler, TTransport tTransport) throws SaslException {
        super(Sasl.createSaslClient(new String[]{str}, str2, str3, str4, map, callbackHandler), tTransport);
        this.mechanism = str;
    }
}
