package com.amazon.alexa.device.setup.echo.softap.thrift.exception;
/* loaded from: classes6.dex */
public class ConnectToAPException extends ThriftRequestException {
    public ConnectToAPException(String str) {
        super("Failed to Connect to AP: ", str);
    }
}
