package com.amazon.communication;

import amazon.communication.CommunicationBaseException;
import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
import java.io.IOException;
/* loaded from: classes12.dex */
public interface ProtocolHandler {
    public static final String ERROR_MESSAGE_TYPE = "ERR";
    public static final String MESSAGE_MESSAGE_TYPE = "MSG";
    public static final String REQUEST_MESSAGE_TYPE = "RQS";
    public static final String RESPONSE_MESSAGE_TYPE = "RSP";

    void decodeMessage(EndpointIdentity endpointIdentity, ByteBufferBackedMessage byteBufferBackedMessage) throws CommunicationBaseException;

    void encodeMessage(Message message, String str, int i) throws ProtocolException, IOException;

    Encoding getEncodingType();
}
