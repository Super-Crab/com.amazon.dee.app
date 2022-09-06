package com.amazon.alexa.handsfree.settings.quicksettings;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.handsfree.notification.services.EnableHandsFreeService;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.handsfree.settings.quicksettings.-$$Lambda$AlexaQuickSettingService$IjE4WM2e4jOcgMbZgu8HbtM2uRg  reason: invalid class name */
/* loaded from: classes8.dex */
public final /* synthetic */ class $$Lambda$AlexaQuickSettingService$IjE4WM2e4jOcgMbZgu8HbtM2uRg implements MessageFilter {
    public static final /* synthetic */ $$Lambda$AlexaQuickSettingService$IjE4WM2e4jOcgMbZgu8HbtM2uRg INSTANCE = new $$Lambda$AlexaQuickSettingService$IjE4WM2e4jOcgMbZgu8HbtM2uRg();

    private /* synthetic */ $$Lambda$AlexaQuickSettingService$IjE4WM2e4jOcgMbZgu8HbtM2uRg() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = EnableHandsFreeService.ENABLE_HANDS_FREE_EVENT.equals(message.getEventType());
        return equals;
    }
}
