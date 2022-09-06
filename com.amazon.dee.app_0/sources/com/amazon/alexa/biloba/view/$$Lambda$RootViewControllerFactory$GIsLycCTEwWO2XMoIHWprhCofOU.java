package com.amazon.alexa.biloba.view;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.lifecycle.api.LifecycleEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.biloba.view.-$$Lambda$RootViewControllerFactory$GIsLycCTEwWO2XMoIHWprhCofOU  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$RootViewControllerFactory$GIsLycCTEwWO2XMoIHWprhCofOU implements MessageFilter {
    public static final /* synthetic */ $$Lambda$RootViewControllerFactory$GIsLycCTEwWO2XMoIHWprhCofOU INSTANCE = new $$Lambda$RootViewControllerFactory$GIsLycCTEwWO2XMoIHWprhCofOU();

    private /* synthetic */ $$Lambda$RootViewControllerFactory$GIsLycCTEwWO2XMoIHWprhCofOU() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = LifecycleEvent.APPLICATION_DID_BACKGROUND.name.equals(message.getEventType());
        return equals;
    }
}
