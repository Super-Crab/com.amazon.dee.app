package com.amazon.alexa.devices.environment;

import com.amazon.alexa.devices.AlexaException;
/* loaded from: classes6.dex */
public interface IAlexaEnvironmentComponent {
    boolean isAccountRegistered() throws AlexaException;

    void launchAccountRegistration() throws AlexaException;
}
