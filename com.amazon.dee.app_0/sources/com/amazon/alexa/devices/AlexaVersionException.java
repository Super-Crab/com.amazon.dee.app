package com.amazon.alexa.devices;
/* loaded from: classes6.dex */
public class AlexaVersionException extends AlexaException {
    public AlexaVersionException(Version version, Version version2) {
        super(String.format("Detected version mismatch: Client is %s - Server is %s", version, version2));
    }
}
