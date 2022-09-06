package com.amazon.alexa.presence.bleconn.service;

import android.content.Context;
import com.amazon.alexa.presence.bleconn.service.handlers.IdentityRequestHandler;
/* loaded from: classes9.dex */
class BleConnServiceComponent {
    private static final String TAG = "com.amazon.alexa.presence.bleconn.service.BleConnServiceComponent";
    private final IdentityRequestHandler identityRequestHandler;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BleConnServiceComponent(Context context, BleConnIdentityComponent bleConnIdentityComponent) {
        this.identityRequestHandler = new IdentityRequestHandler(bleConnIdentityComponent.getIdentityController());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IdentityRequestHandler getIdentityRequestHandler() {
        return this.identityRequestHandler;
    }
}
