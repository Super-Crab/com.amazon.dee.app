package com.amazon.communication.socket;

import amazon.communication.CommunicationBaseException;
/* loaded from: classes12.dex */
public class IllegalSocketStateException extends CommunicationBaseException {
    public IllegalSocketStateException(Exception exc) {
        super(exc);
    }

    public IllegalSocketStateException(String str, Exception exc) {
        super(str, exc);
    }

    public IllegalSocketStateException(String str) {
        super(str);
    }
}
