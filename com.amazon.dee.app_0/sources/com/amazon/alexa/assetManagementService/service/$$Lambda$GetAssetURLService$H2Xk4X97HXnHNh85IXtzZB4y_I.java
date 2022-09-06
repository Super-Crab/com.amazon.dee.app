package com.amazon.alexa.assetManagementService.service;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.assetManagementService.service.-$$Lambda$GetAssetURLService$H-2Xk4X97HXnHNh85IXtzZB4y_I  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$GetAssetURLService$H2Xk4X97HXnHNh85IXtzZB4y_I implements MessageFilter {
    public static final /* synthetic */ $$Lambda$GetAssetURLService$H2Xk4X97HXnHNh85IXtzZB4y_I INSTANCE = new $$Lambda$GetAssetURLService$H2Xk4X97HXnHNh85IXtzZB4y_I();

    private /* synthetic */ $$Lambda$GetAssetURLService$H2Xk4X97HXnHNh85IXtzZB4y_I() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return IdentityEvent.IDENTITY_SIGN_OUT_SUCCESS.equals(message.getEventType());
    }
}
