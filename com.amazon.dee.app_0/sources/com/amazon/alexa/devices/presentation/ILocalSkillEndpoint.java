package com.amazon.alexa.devices.presentation;

import com.amazon.alexa.devices.AlexaException;
/* loaded from: classes6.dex */
public interface ILocalSkillEndpoint {
    void sendAskResponse(String str, String str2, ICallStatusCallback iCallStatusCallback) throws AlexaException;
}
