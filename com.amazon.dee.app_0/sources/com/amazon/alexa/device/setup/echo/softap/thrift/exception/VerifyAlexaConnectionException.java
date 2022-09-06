package com.amazon.alexa.device.setup.echo.softap.thrift.exception;
/* loaded from: classes6.dex */
public class VerifyAlexaConnectionException extends ThriftRequestException {
    public VerifyAlexaConnectionException(String str) {
        super("Failed to verify Alexa Connection: ", str);
    }
}
