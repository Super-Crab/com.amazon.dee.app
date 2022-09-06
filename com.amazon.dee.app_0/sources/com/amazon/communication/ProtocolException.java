package com.amazon.communication;

import amazon.communication.CommunicationBaseException;
/* loaded from: classes12.dex */
public class ProtocolException extends CommunicationBaseException {
    private static final long serialVersionUID = 1;

    public ProtocolException(Exception exc) {
        super(exc);
    }

    public ProtocolException(String str, Exception exc) {
        super(str, exc);
    }

    public ProtocolException(String str) {
        super(str);
    }
}
