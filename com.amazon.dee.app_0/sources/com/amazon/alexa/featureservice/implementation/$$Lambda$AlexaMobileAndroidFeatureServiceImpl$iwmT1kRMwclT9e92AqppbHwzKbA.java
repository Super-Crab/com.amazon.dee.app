package com.amazon.alexa.featureservice.implementation;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.featureservice.implementation.-$$Lambda$AlexaMobileAndroidFeatureServiceImpl$iwmT1kRMwclT9e92AqppbHwzKbA  reason: invalid class name */
/* loaded from: classes7.dex */
public final /* synthetic */ class $$Lambda$AlexaMobileAndroidFeatureServiceImpl$iwmT1kRMwclT9e92AqppbHwzKbA implements MessageFilter {
    public static final /* synthetic */ $$Lambda$AlexaMobileAndroidFeatureServiceImpl$iwmT1kRMwclT9e92AqppbHwzKbA INSTANCE = new $$Lambda$AlexaMobileAndroidFeatureServiceImpl$iwmT1kRMwclT9e92AqppbHwzKbA();

    private /* synthetic */ $$Lambda$AlexaMobileAndroidFeatureServiceImpl$iwmT1kRMwclT9e92AqppbHwzKbA() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return "app:authenticated".equals(message.getEventType());
    }
}
