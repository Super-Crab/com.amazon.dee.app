package com.amazon.alexa.voice.ui.onedesign.local;

import com.amazon.alexa.voice.ui.superbowl.ControllerFactory;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public final class LocalControllerFactory implements ControllerFactory<LocalController> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.voice.ui.superbowl.ControllerFactory
    /* renamed from: create */
    public LocalController mo2780create(JSONObject jSONObject) throws JSONException {
        return LocalController.create(LocalCardFactory.fromJson(jSONObject));
    }
}
