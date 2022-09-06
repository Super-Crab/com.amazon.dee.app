package com.amazon.alexa.accessory.internal;

import com.amazon.alexa.accessory.internal.ActionQueue;
import com.amazon.alexa.accessory.internal.MappedResponseAction;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.streams.control.ControlMessage;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import java.io.IOException;
/* loaded from: classes.dex */
public final class SuccessResponseAction<T> implements ActionQueue.Action {
    private final MappedResponseAction<T> action;

    /* loaded from: classes.dex */
    public interface Mapper<T> {
        T map(Accessories.Response response) throws IOException;
    }

    public SuccessResponseAction(ControlMessage controlMessage, ControlStream controlStream, Producer.Result<T> result, final Accessories.Response.PayloadCase payloadCase, final Mapper<T> mapper) {
        Preconditions.notNull(controlMessage, "controlMessage");
        Preconditions.notNull(controlStream, "controlStream");
        Preconditions.notNull(result, "result");
        Preconditions.notNull(payloadCase, "expectedPayloadCase");
        Preconditions.notNull(mapper, "mapper");
        this.action = new MappedResponseAction<>(controlMessage, controlStream, result, new MappedResponseAction.Mapper() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$SuccessResponseAction$hFm0DesWOdrbwtkXeLjVXv3YlPU
            @Override // com.amazon.alexa.accessory.internal.MappedResponseAction.Mapper
            public final Object map(Accessories.Response response) {
                return SuccessResponseAction.lambda$new$0(Accessories.Response.PayloadCase.this, mapper, response);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$new$0(Accessories.Response.PayloadCase payloadCase, Mapper mapper, Accessories.Response response) throws IOException {
        if (response.getErrorCode() == Common.ErrorCode.SUCCESS && response.getPayloadCase() == payloadCase) {
            return mapper.map(response);
        }
        throw new IOException("Unexpected response code or payload in response " + response);
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
