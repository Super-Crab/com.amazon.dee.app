package com.amazon.alexa.location.provider.interactor.sync.contextualevents;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.provider.interactor.sync.contextualevents.-$$Lambda$ContextualEventRulesSyncInteractor$f308UqDgnkVyPLl9RJI02OvFVlo  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ContextualEventRulesSyncInteractor$f308UqDgnkVyPLl9RJI02OvFVlo implements MessageFilter {
    public static final /* synthetic */ $$Lambda$ContextualEventRulesSyncInteractor$f308UqDgnkVyPLl9RJI02OvFVlo INSTANCE = new $$Lambda$ContextualEventRulesSyncInteractor$f308UqDgnkVyPLl9RJI02OvFVlo();

    private /* synthetic */ $$Lambda$ContextualEventRulesSyncInteractor$f308UqDgnkVyPLl9RJI02OvFVlo() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return ContextualEventRulesSyncInteractor.lambda$getMessageFilter$0(message);
    }
}
