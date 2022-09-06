package com.amazon.communication.socket;

import amazon.communication.CommunicationBaseException;
/* loaded from: classes12.dex */
public class SocketAcquisitionFailedException extends CommunicationBaseException {
    public SocketAcquisitionFailedException(Exception exc) {
        super(exc);
    }

    public SocketAcquisitionFailedException(String str, Exception exc) {
        super(str, exc);
    }

    public SocketAcquisitionFailedException(String str) {
        super(str);
    }
}
