package com.amazon.alexa.biloba.view;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.biloba.view.-$$Lambda$RootViewControllerFactory$YW-ysHdD5TyFrpSHYgmcVU1anvA  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$RootViewControllerFactory$YWysHdD5TyFrpSHYgmcVU1anvA implements MessageFilter {
    public static final /* synthetic */ $$Lambda$RootViewControllerFactory$YWysHdD5TyFrpSHYgmcVU1anvA INSTANCE = new $$Lambda$RootViewControllerFactory$YWysHdD5TyFrpSHYgmcVU1anvA();

    private /* synthetic */ $$Lambda$RootViewControllerFactory$YWysHdD5TyFrpSHYgmcVU1anvA() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return IdentityEvent.IDENTITY_SIGN_OUT_SUCCESS.equals(message.getEventType());
    }
}
