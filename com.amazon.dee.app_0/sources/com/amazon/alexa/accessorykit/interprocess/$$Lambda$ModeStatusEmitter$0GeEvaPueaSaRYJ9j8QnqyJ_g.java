package com.amazon.alexa.accessorykit.interprocess;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$ModeStatusEmitter$0GeEvaPueaSa-RY-J9j8QnqyJ_g  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$ModeStatusEmitter$0GeEvaPueaSaRYJ9j8QnqyJ_g implements MessageFilter {
    public static final /* synthetic */ $$Lambda$ModeStatusEmitter$0GeEvaPueaSaRYJ9j8QnqyJ_g INSTANCE = new $$Lambda$ModeStatusEmitter$0GeEvaPueaSaRYJ9j8QnqyJ_g();

    private /* synthetic */ $$Lambda$ModeStatusEmitter$0GeEvaPueaSaRYJ9j8QnqyJ_g() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return ModeStatusEmitter.lambda$observeModeServiceChanges$0(message);
    }
}
