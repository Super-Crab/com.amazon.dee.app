package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.TtM;
import com.amazon.alexa.client.core.messages.Message;
import java.util.Collection;
/* compiled from: MessageCallbackAdapter.java */
/* loaded from: classes.dex */
public class UcG implements TtM {
    public static final UcG INSTANCE = new UcG();

    public static UcG getInstance() {
        return INSTANCE;
    }

    @Override // com.amazon.alexa.TtM
    public void onFailure(RrI rrI, @Nullable Integer num, @Nullable Exception exc) {
    }

    @Override // com.amazon.alexa.TtM
    public void onMessageReceived(RrI rrI, Message message) {
    }

    @Override // com.amazon.alexa.TtM
    public void onRequestDropped(RrI rrI, TtM.zZm zzm) {
    }

    @Override // com.amazon.alexa.TtM
    public void onRequestFinished(RrI rrI) {
    }

    @Override // com.amazon.alexa.TtM
    public void onRequestQueued(RrI rrI) {
    }

    @Override // com.amazon.alexa.TtM
    public void onRequestStarted(RrI rrI) {
    }

    @Override // com.amazon.alexa.TtM
    public void onSuccess(RrI rrI, Collection<Message> collection) {
    }
}
