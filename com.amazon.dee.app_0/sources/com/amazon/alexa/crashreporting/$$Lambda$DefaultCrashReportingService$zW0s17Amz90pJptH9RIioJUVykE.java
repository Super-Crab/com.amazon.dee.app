package com.amazon.alexa.crashreporting;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.crashreporting.-$$Lambda$DefaultCrashReportingService$zW0s17Amz90pJptH9RIioJUVykE  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$DefaultCrashReportingService$zW0s17Amz90pJptH9RIioJUVykE implements MessageFilter {
    public static final /* synthetic */ $$Lambda$DefaultCrashReportingService$zW0s17Amz90pJptH9RIioJUVykE INSTANCE = new $$Lambda$DefaultCrashReportingService$zW0s17Amz90pJptH9RIioJUVykE();

    private /* synthetic */ $$Lambda$DefaultCrashReportingService$zW0s17Amz90pJptH9RIioJUVykE() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = IdentityEvent.IDENTITY_CHANGED.equals(message.getEventType());
        return equals;
    }
}
