package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.gson.JsonParseException;
/* compiled from: LauncherPayloadTypeMapper.java */
/* renamed from: com.amazon.alexa.bPW  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0190bPW implements GwO {
    @Override // com.amazon.alexa.GwO
    public Class<? extends Payload> zZm(Name name) throws JsonParseException {
        if (AvsApiConstants.Alexa.Launcher.Directives.LaunchTarget.zZm.equals(name)) {
            return mLq.class;
        }
        if (AvsApiConstants.Alexa.Launcher.Directives.DisambiguateAndLaunchTarget.zZm.equals(name)) {
            return gUg.class;
        }
        StringBuilder zZm = C0179Pya.zZm("Unknown name: ");
        zZm.append(name.getValue());
        throw new JsonParseException(zZm.toString());
    }
}
