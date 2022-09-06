package com.amazon.communication.socket;
/* loaded from: classes12.dex */
public class EndpointNotAuthenticatedException extends SocketAcquisitionFailedException {
    public EndpointNotAuthenticatedException(String str) {
        super(str);
    }

    public EndpointNotAuthenticatedException(Exception exc) {
        super(exc);
    }

    public EndpointNotAuthenticatedException(String str, Exception exc) {
        super(str, exc);
    }
}
