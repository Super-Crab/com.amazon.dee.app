package com.amazon.alexa.featureservice.implementation;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.featureservice.implementation.-$$Lambda$AlexaMobileAndroidFeatureServiceImpl$vZmahlcKQcqPK4EZSYBNrl04gRg  reason: invalid class name */
/* loaded from: classes7.dex */
public final /* synthetic */ class $$Lambda$AlexaMobileAndroidFeatureServiceImpl$vZmahlcKQcqPK4EZSYBNrl04gRg implements MessageFilter {
    public static final /* synthetic */ $$Lambda$AlexaMobileAndroidFeatureServiceImpl$vZmahlcKQcqPK4EZSYBNrl04gRg INSTANCE = new $$Lambda$AlexaMobileAndroidFeatureServiceImpl$vZmahlcKQcqPK4EZSYBNrl04gRg();

    private /* synthetic */ $$Lambda$AlexaMobileAndroidFeatureServiceImpl$vZmahlcKQcqPK4EZSYBNrl04gRg() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return IdentityEvent.IDENTITY_SIGN_OUT_SUCCESS.equals(message.getEventType());
    }
}
