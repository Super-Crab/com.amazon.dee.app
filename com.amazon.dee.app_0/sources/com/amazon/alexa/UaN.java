package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import java.util.concurrent.FutureTask;
/* compiled from: DispatchMessageTask.java */
/* loaded from: classes.dex */
public class UaN extends FutureTask<Boolean> {
    public final Message BIo;
    public final MessageProcessingCallbacks zQM;
    public final AlexaClientEventBus zZm;

    public UaN(AlexaClientEventBus alexaClientEventBus, nEu neu, Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        super(neu);
        this.zZm = alexaClientEventBus;
        this.BIo = message;
        this.zQM = messageProcessingCallbacks;
        this.zZm.zyO(new liQ(message, messageProcessingCallbacks));
    }

    @Override // java.util.concurrent.FutureTask, java.util.concurrent.Future
    public boolean cancel(boolean z) {
        boolean cancel = super.cancel(z);
        this.zZm.zyO(new NBU(this.BIo, this.zQM));
        return cancel;
    }

    @Override // java.util.concurrent.FutureTask, java.util.concurrent.RunnableFuture, java.lang.Runnable
    public void run() {
        if (!isCancelled()) {
            this.zZm.zyO(new Cuq(this.BIo, this.zQM));
        }
        super.run();
    }
}
