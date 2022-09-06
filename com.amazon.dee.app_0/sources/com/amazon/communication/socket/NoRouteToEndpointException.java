package com.amazon.communication.socket;
/* loaded from: classes12.dex */
public class NoRouteToEndpointException extends SocketAcquisitionFailedException {
    private static final long serialVersionUID = -5774882286016123508L;

    public NoRouteToEndpointException(String str) {
        super(str);
    }

    public NoRouteToEndpointException(Exception exc) {
        super(exc);
    }

    public NoRouteToEndpointException(String str, Exception exc) {
        super(str, exc);
    }
}
