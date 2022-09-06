package com.amazon.alexa.tasks;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.tasks.-$$Lambda$BackgroundTaskManager$9lHySmRKQgl45Pg1r80OwC0DqTo  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$BackgroundTaskManager$9lHySmRKQgl45Pg1r80OwC0DqTo implements MessageFilter {
    public static final /* synthetic */ $$Lambda$BackgroundTaskManager$9lHySmRKQgl45Pg1r80OwC0DqTo INSTANCE = new $$Lambda$BackgroundTaskManager$9lHySmRKQgl45Pg1r80OwC0DqTo();

    private /* synthetic */ $$Lambda$BackgroundTaskManager$9lHySmRKQgl45Pg1r80OwC0DqTo() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return message.getEventType().equals("ui:loading:started");
    }
}
