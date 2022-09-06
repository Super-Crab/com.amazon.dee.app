package com.amazon.communication.socket.ssl;

import com.amazon.communication.socket.SocketAcquisitionFailedException;
/* loaded from: classes12.dex */
public class NoSecureRouteToEndpointException extends SocketAcquisitionFailedException {
    public NoSecureRouteToEndpointException(String str) {
        super(str);
    }

    public NoSecureRouteToEndpointException(Exception exc) {
        super(exc);
    }

    public NoSecureRouteToEndpointException(String str, Exception exc) {
        super(str, exc);
    }
}
