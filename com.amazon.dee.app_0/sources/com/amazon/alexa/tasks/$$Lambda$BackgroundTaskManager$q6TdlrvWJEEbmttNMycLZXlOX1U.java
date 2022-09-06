package com.amazon.alexa.tasks;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.tasks.-$$Lambda$BackgroundTaskManager$q6TdlrvWJEEbmttNMycLZXlOX1U  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$BackgroundTaskManager$q6TdlrvWJEEbmttNMycLZXlOX1U implements MessageFilter {
    public static final /* synthetic */ $$Lambda$BackgroundTaskManager$q6TdlrvWJEEbmttNMycLZXlOX1U INSTANCE = new $$Lambda$BackgroundTaskManager$q6TdlrvWJEEbmttNMycLZXlOX1U();

    private /* synthetic */ $$Lambda$BackgroundTaskManager$q6TdlrvWJEEbmttNMycLZXlOX1U() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return message.getEventType().equals("ui:loading:completed");
    }
}
