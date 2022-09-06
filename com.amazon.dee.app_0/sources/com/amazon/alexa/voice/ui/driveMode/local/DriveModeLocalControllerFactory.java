package com.amazon.alexa.voice.ui.driveMode.local;

import com.amazon.alexa.voice.ui.onedesign.local.LocalCardFactory;
import com.amazon.alexa.voice.ui.superbowl.ControllerFactory;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public final class DriveModeLocalControllerFactory implements ControllerFactory<DriveModeLocalController> {
    private int cardTheme;

    public DriveModeLocalControllerFactory(int i) {
        this.cardTheme = i;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.voice.ui.superbowl.ControllerFactory
    /* renamed from: create */
    public DriveModeLocalController mo2780create(JSONObject jSONObject) throws JSONException {
        return DriveModeLocalController.create(LocalCardFactory.fromJson(jSONObject), this.cardTheme);
    }
}
