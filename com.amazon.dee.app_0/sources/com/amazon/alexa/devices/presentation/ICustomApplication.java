package com.amazon.alexa.devices.presentation;

import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.SkillEndpointContext;
/* loaded from: classes6.dex */
public interface ICustomApplication {
    void addOrUpdateSkillEndpointContext(String str, SkillEndpointContext skillEndpointContext, ICallStatusCallback iCallStatusCallback) throws AlexaException;

    void removeSkillEndpointContext(String str, ICallStatusCallback iCallStatusCallback) throws AlexaException;

    void sendAskUserEvent(String str, String str2, ICallStatusCallback iCallStatusCallback) throws AlexaException;
}
