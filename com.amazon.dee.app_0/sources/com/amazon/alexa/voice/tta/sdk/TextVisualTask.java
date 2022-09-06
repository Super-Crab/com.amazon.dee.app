package com.amazon.alexa.voice.tta.sdk;

import com.amazon.alexa.api.AlexaVisualTask;
import com.amazon.alexa.voice.tta.Constants;
/* loaded from: classes11.dex */
public class TextVisualTask implements AlexaVisualTask {
    private static final String TAG = "TextVisualTask";

    @Override // com.amazon.alexa.api.AlexaVisualTask
    public String getTaskComponentName() {
        return Constants.Namespaces.TEXT;
    }

    @Override // com.amazon.alexa.api.AlexaVisualTask
    public void onStop() {
    }
}
