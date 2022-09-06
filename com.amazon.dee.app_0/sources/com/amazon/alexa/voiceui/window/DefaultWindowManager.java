package com.amazon.alexa.voiceui.window;

import android.view.Window;
/* loaded from: classes11.dex */
public class DefaultWindowManager implements WindowManager {
    private final Window window;

    public DefaultWindowManager(Window window) {
        this.window = window;
    }

    @Override // com.amazon.alexa.voiceui.window.WindowManager
    public void setTouchEventPassThrough(boolean z) {
        WindowUtils.setTouchEventPassThrough(this.window, z);
    }
}
