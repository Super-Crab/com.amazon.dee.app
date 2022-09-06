package com.amazon.alexa.featureservice.service;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.featureservice.service.-$$Lambda$DefaultFeatureServiceV2$mECLwJya63FvvZsV9k3NAws-kcU  reason: invalid class name */
/* loaded from: classes7.dex */
public final /* synthetic */ class $$Lambda$DefaultFeatureServiceV2$mECLwJya63FvvZsV9k3NAwskcU implements MessageFilter {
    public static final /* synthetic */ $$Lambda$DefaultFeatureServiceV2$mECLwJya63FvvZsV9k3NAwskcU INSTANCE = new $$Lambda$DefaultFeatureServiceV2$mECLwJya63FvvZsV9k3NAwskcU();

    private /* synthetic */ $$Lambda$DefaultFeatureServiceV2$mECLwJya63FvvZsV9k3NAwskcU() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return "app:authenticated".equals(message.getEventType());
    }
}
