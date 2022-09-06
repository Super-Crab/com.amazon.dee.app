package com.amazon.alexa.location.provider.interactor.sync;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.provider.interactor.sync.-$$Lambda$DemoRulesSyncInteractor$8Q2wdZnEc2n7Fi6ZwJi6ghPMcAA  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$DemoRulesSyncInteractor$8Q2wdZnEc2n7Fi6ZwJi6ghPMcAA implements MessageFilter {
    public static final /* synthetic */ $$Lambda$DemoRulesSyncInteractor$8Q2wdZnEc2n7Fi6ZwJi6ghPMcAA INSTANCE = new $$Lambda$DemoRulesSyncInteractor$8Q2wdZnEc2n7Fi6ZwJi6ghPMcAA();

    private /* synthetic */ $$Lambda$DemoRulesSyncInteractor$8Q2wdZnEc2n7Fi6ZwJi6ghPMcAA() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return DemoRulesSyncInteractor.lambda$getMessageFilter$0(message);
    }
}
