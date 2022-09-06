package com.amazon.alexa.accessory.internal;

import com.amazon.alexa.accessory.internal.ActionQueue;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.-$$Lambda$ActionQueue$JZ4jDtIw9xxMOP_xw8okhAuj5Bo  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$ActionQueue$JZ4jDtIw9xxMOP_xw8okhAuj5Bo implements ActionQueue.Action.Callback {
    private final /* synthetic */ ActionQueue f$0;

    public /* synthetic */ $$Lambda$ActionQueue$JZ4jDtIw9xxMOP_xw8okhAuj5Bo(ActionQueue actionQueue) {
        this.f$0 = actionQueue;
    }

    @Override // com.amazon.alexa.accessory.internal.ActionQueue.Action.Callback
    public final void onFinished() {
        ActionQueue.lambda$JZ4jDtIw9xxMOP_xw8okhAuj5Bo(this.f$0);
    }
}
