package com.amazon.alexa.devices;
/* loaded from: classes6.dex */
public class AlexaNotInstalledException extends AlexaException {
    public AlexaNotInstalledException() {
        super("Unable to connect to the Proteus API Gateway: Runtime not installed");
    }
}
