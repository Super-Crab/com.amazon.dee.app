package com.amazon.alexa.accessory.internal;

import com.amazon.alexa.accessory.internal.ActionQueue;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.streams.control.ControlMessage;
import com.amazon.alexa.accessory.streams.control.ControlStream;
/* loaded from: classes.dex */
public final class ErrorCodeAction implements ActionQueue.Action {
    private final MappedResponseAction<Common.ErrorCode> action;

    public ErrorCodeAction(ControlMessage controlMessage, ControlStream controlStream, Producer.Result<Common.ErrorCode> result) {
        Preconditions.notNull(controlMessage, "controlMessage");
        Preconditions.notNull(controlStream, "controlStream");
        Preconditions.notNull(result, "result");
        this.action = new MappedResponseAction<>(controlMessage, controlStream, result, $$Lambda$mSUrIml8_VK9J0SRYncmq0ApxE.INSTANCE);
    }

    @Override // com.amazon.alexa.accessory.internal.ActionQueue.Action
    public void abort() {
        this.action.abort();
    }

    @Override // com.amazon.alexa.accessory.internal.ActionQueue.Action
    public void run(ActionQueue.Action.Callback callback) {
        Preconditions.notNull(callback, "callback");
        this.action.run(callback);
    }
}
