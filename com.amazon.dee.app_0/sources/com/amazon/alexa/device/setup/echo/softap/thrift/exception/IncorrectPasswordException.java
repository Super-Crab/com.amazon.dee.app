package com.amazon.alexa.device.setup.echo.softap.thrift.exception;
/* loaded from: classes6.dex */
public class IncorrectPasswordException extends ThriftRequestException {
    public IncorrectPasswordException(String str) {
        super("Failed to Connect to AP: ", str);
    }
}
