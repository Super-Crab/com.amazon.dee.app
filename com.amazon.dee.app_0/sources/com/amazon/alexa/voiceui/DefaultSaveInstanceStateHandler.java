package com.amazon.alexa.voiceui;

import android.os.Bundle;
/* loaded from: classes11.dex */
public class DefaultSaveInstanceStateHandler implements SaveInstanceStateHandler {
    private final RouterDelegate routerDelegate;

    public DefaultSaveInstanceStateHandler(RouterDelegate routerDelegate) {
        this.routerDelegate = routerDelegate;
    }

    @Override // com.amazon.alexa.voiceui.SaveInstanceStateHandler
    public void onSaveInstanceState(Bundle bundle) {
        this.routerDelegate.saveInstanceState(bundle);
    }
}
