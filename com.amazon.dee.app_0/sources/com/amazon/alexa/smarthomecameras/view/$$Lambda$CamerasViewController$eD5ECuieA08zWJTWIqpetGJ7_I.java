package com.amazon.alexa.smarthomecameras.view;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.smarthomecameras.view.-$$Lambda$CamerasViewController$eD5ECuieA08zWJTWIqpe-tGJ7_I  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$CamerasViewController$eD5ECuieA08zWJTWIqpetGJ7_I implements MessageFilter {
    public static final /* synthetic */ $$Lambda$CamerasViewController$eD5ECuieA08zWJTWIqpetGJ7_I INSTANCE = new $$Lambda$CamerasViewController$eD5ECuieA08zWJTWIqpetGJ7_I();

    private /* synthetic */ $$Lambda$CamerasViewController$eD5ECuieA08zWJTWIqpetGJ7_I() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return CamerasViewController.lambda$setupLifecycleEventBus$0(message);
    }
}
