package com.amazon.alexa.devices;
/* loaded from: classes6.dex */
public class ComponentMissingException extends AlexaException {
    static final String COMPONENT_MISSING_EXCEPTION_STRING = "Component %s is not within the classpath.";

    public ComponentMissingException(String str) {
        super(String.format(COMPONENT_MISSING_EXCEPTION_STRING, str));
    }
}
