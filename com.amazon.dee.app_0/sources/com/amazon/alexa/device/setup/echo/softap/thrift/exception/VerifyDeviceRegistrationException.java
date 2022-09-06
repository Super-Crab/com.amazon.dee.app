package com.amazon.alexa.device.setup.echo.softap.thrift.exception;
/* loaded from: classes6.dex */
public class VerifyDeviceRegistrationException extends ThriftRequestException {
    public VerifyDeviceRegistrationException(String str) {
        super("Failed to verify device registration: ", str);
    }
}
