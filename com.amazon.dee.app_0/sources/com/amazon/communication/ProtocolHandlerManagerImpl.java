package com.amazon.communication;

import com.amazon.dp.logger.DPLogger;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes12.dex */
public class ProtocolHandlerManagerImpl implements ProtocolHandlerManager {
    private static final DPLogger log = new DPLogger("TComm.ProtocolHandlerManagerImpl");
    protected final Map<String, ProtocolHandlerFactory> mProtocolHandlerFactories = new ConcurrentHashMap();

    @Override // com.amazon.communication.ProtocolHandlerManager
    public void addProtocolHandlerFactory(ProtocolHandlerFactory protocolHandlerFactory) {
        if (protocolHandlerFactory != null) {
            log.verbose("addProtocolHandlerFactory", "beginning execution", "factory.getProtocolName", protocolHandlerFactory.getProtocolName());
            if (this.mProtocolHandlerFactories.put(protocolHandlerFactory.getProtocolName(), protocolHandlerFactory) == null) {
                return;
            }
            log.warn("addProtocolHandlerFactory", "overriding ProtocolHandlerFactory; was that intentional?", "factory.getProtocolName", protocolHandlerFactory.getProtocolName());
            return;
        }
        throw new IllegalArgumentException("factory cannot be null.");
    }

    @Override // com.amazon.communication.ProtocolHandlerManager
    public Set<String> getAllProtocolHandlerNames() {
        return this.mProtocolHandlerFactories.keySet();
    }

    @Override // com.amazon.communication.ProtocolHandlerManager
    public ProtocolHandlerFactory getProtocolHandlerFactory(String str) {
        if (str != null && str.trim().length() != 0) {
            return this.mProtocolHandlerFactories.get(str);
        }
        throw new IllegalArgumentException("protocolName cannot be null or empty.");
    }
}
