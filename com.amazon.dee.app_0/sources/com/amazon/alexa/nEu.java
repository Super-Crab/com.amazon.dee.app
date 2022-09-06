package com.amazon.alexa;

import android.os.ConditionVariable;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import java.util.concurrent.Callable;
/* compiled from: BlockingMessageLifecycleCallable.java */
/* loaded from: classes.dex */
public class nEu implements Callable, MessageProcessingCallbacks {
    public final Qoi BIo;
    public volatile boolean Qle;
    public volatile boolean jiA;
    public final DialogRequestIdentifier zQM;
    public final AlexaClientEventBus zZm;
    public final ConditionVariable zyO = new ConditionVariable();

    public nEu(AlexaClientEventBus alexaClientEventBus, Qoi qoi, @Nullable DialogRequestIdentifier dialogRequestIdentifier) {
        this.zZm = alexaClientEventBus;
        this.BIo = qoi;
        this.zQM = dialogRequestIdentifier;
    }

    @Override // java.util.concurrent.Callable
    public Object call() throws Exception {
        Boolean.valueOf(true);
        this.zyO.block();
        return Boolean.valueOf(this.Qle);
    }

    @Override // com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks
    public synchronized void onError() {
        if (!this.jiA) {
            this.zZm.zyO(TTH.zZm("Error handling a directive, invalidating dialog"));
            zZm();
        }
    }

    @Override // com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks
    public synchronized void onFinished() {
        if (!this.jiA) {
            this.Qle = true;
        }
        this.jiA = true;
        this.zyO.open();
    }

    @Override // com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks
    public synchronized void onStopped() {
        zZm();
    }

    public final void zZm() {
        if (!this.jiA) {
            this.Qle = false;
            DialogRequestIdentifier dialogRequestIdentifier = this.zQM;
            if (dialogRequestIdentifier != null) {
                this.BIo.zZm(dialogRequestIdentifier);
            }
        }
        this.jiA = true;
        this.zyO.open();
    }
}
