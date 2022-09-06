package com.amazon.communication;
/* loaded from: classes12.dex */
public abstract class CommunicationEngineBase implements CommunicationEngine {
    protected static final String TAG = "TComm.CommunicationEngineBase";
    protected ProtocolHandlerManager mProtocolHandlerManager;

    public CommunicationEngineBase(ProtocolHandlerManager protocolHandlerManager) {
        this.mProtocolHandlerManager = protocolHandlerManager;
    }
}
