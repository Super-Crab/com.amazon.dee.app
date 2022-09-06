package com.amazon.communication;

import java.util.Set;
/* loaded from: classes12.dex */
public interface ProtocolHandlerManager {
    void addProtocolHandlerFactory(ProtocolHandlerFactory protocolHandlerFactory);

    Set<String> getAllProtocolHandlerNames();

    ProtocolHandlerFactory getProtocolHandlerFactory(String str);
}
