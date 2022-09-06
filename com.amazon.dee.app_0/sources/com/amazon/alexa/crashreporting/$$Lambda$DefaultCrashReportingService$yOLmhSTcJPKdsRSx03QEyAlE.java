package com.amazon.alexa.crashreporting;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.crashreporting.-$$Lambda$DefaultCrashReportingService$yOL-mh--STcJPKdsRSx03QEyAlE  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$DefaultCrashReportingService$yOLmhSTcJPKdsRSx03QEyAlE implements MessageFilter {
    public static final /* synthetic */ $$Lambda$DefaultCrashReportingService$yOLmhSTcJPKdsRSx03QEyAlE INSTANCE = new $$Lambda$DefaultCrashReportingService$yOLmhSTcJPKdsRSx03QEyAlE();

    private /* synthetic */ $$Lambda$DefaultCrashReportingService$yOLmhSTcJPKdsRSx03QEyAlE() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = "featureServiceV2:internal:featuresUpdated".equals(message.getEventType());
        return equals;
    }
}
