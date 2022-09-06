package org.apache.thrift.orig.transport;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import javax.security.auth.callback.CallbackHandler;
import javax.security.sasl.Sasl;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import org.apache.thrift.orig.transport.TSaslTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes4.dex */
public class TSaslServerTransport extends TSaslTransport {
    private static final Logger LOGGER = LoggerFactory.getLogger(TSaslServerTransport.class);
    private Map<String, TSaslServerDefinition> serverDefinitionMap;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class TSaslServerDefinition {
        public CallbackHandler cbh;
        public String mechanism;
        public Map<String, String> props;
        public String protocol;
        public String serverName;

        public TSaslServerDefinition(String str, String str2, String str3, Map<String, String> map, CallbackHandler callbackHandler) {
            this.mechanism = str;
            this.protocol = str2;
            this.serverName = str3;
            this.props = map;
            this.cbh = callbackHandler;
        }
    }

    public void addServerDefinition(String str, String str2, String str3, Map<String, String> map, CallbackHandler callbackHandler) {
        this.serverDefinitionMap.put(str, new TSaslServerDefinition(str, str2, str3, map, callbackHandler));
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
        return TSaslTransport.SaslRole.SERVER;
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
        TSaslTransport.SaslResponse receiveSaslMessage = receiveSaslMessage();
        LOGGER.debug("Received start message with status {}", receiveSaslMessage.status);
        if (receiveSaslMessage.status != TSaslTransport.NegotiationStatus.START) {
            TSaslTransport.NegotiationStatus negotiationStatus = TSaslTransport.NegotiationStatus.ERROR;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expecting START status, received ");
            outline107.append(receiveSaslMessage.status);
            sendAndThrowMessage(negotiationStatus, outline107.toString());
        }
        String str = new String(receiveSaslMessage.payload);
        TSaslServerDefinition tSaslServerDefinition = this.serverDefinitionMap.get(str);
        LOGGER.debug("Received mechanism name '{}'", str);
        if (tSaslServerDefinition == null) {
            sendAndThrowMessage(TSaslTransport.NegotiationStatus.BAD, GeneratedOutlineSupport1.outline72("Unsupported mechanism type ", str));
        }
        setSaslServer(Sasl.createSaslServer(tSaslServerDefinition.mechanism, tSaslServerDefinition.protocol, tSaslServerDefinition.serverName, tSaslServerDefinition.props, tSaslServerDefinition.cbh));
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

    /* loaded from: classes4.dex */
    public static class Factory extends TTransportFactory {
        private static Map<TTransport, WeakReference<TSaslServerTransport>> transportMap = Collections.synchronizedMap(new WeakHashMap());
        private Map<String, TSaslServerDefinition> serverDefinitionMap = new HashMap();

        public Factory() {
        }

        public void addServerDefinition(String str, String str2, String str3, Map<String, String> map, CallbackHandler callbackHandler) {
            this.serverDefinitionMap.put(str, new TSaslServerDefinition(str, str2, str3, map, callbackHandler));
        }

        @Override // org.apache.thrift.orig.transport.TTransportFactory
        public TTransport getTransport(TTransport tTransport) {
            WeakReference<TSaslServerTransport> weakReference = transportMap.get(tTransport);
            if (weakReference == null || weakReference.get() == null) {
                TSaslServerTransport.LOGGER.debug("transport map does not contain key", tTransport);
                weakReference = new WeakReference<>(new TSaslServerTransport(this.serverDefinitionMap, tTransport));
                try {
                    weakReference.get().open();
                    transportMap.put(tTransport, weakReference);
                } catch (TTransportException e) {
                    TSaslServerTransport.LOGGER.debug("failed to open server transport", (Throwable) e);
                    throw new RuntimeException(e);
                }
            } else {
                TSaslServerTransport.LOGGER.debug("transport map does contain key {}", tTransport);
            }
            return weakReference.get();
        }

        public Factory(String str, String str2, String str3, Map<String, String> map, CallbackHandler callbackHandler) {
            addServerDefinition(str, str2, str3, map, callbackHandler);
        }
    }

    public TSaslServerTransport(TTransport tTransport) {
        super(tTransport);
        this.serverDefinitionMap = new HashMap();
    }

    public TSaslServerTransport(String str, String str2, String str3, Map<String, String> map, CallbackHandler callbackHandler, TTransport tTransport) {
        super(tTransport);
        this.serverDefinitionMap = new HashMap();
        addServerDefinition(str, str2, str3, map, callbackHandler);
    }

    private TSaslServerTransport(Map<String, TSaslServerDefinition> map, TTransport tTransport) {
        super(tTransport);
        this.serverDefinitionMap = new HashMap();
        this.serverDefinitionMap.putAll(map);
    }
}
