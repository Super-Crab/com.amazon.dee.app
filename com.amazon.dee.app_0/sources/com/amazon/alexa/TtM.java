package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.core.messages.Message;
import java.util.Collection;
/* compiled from: SendMessageCallback.java */
/* loaded from: classes.dex */
public interface TtM {

    /* compiled from: SendMessageCallback.java */
    /* loaded from: classes.dex */
    public enum zZm {
        ERROR,
        NETWORK,
        AUTHORIZATION,
        TIMEOUT,
        CANCELLED,
        TEARDOWN
    }

    void onFailure(RrI rrI, @Nullable Integer num, @Nullable Exception exc);

    void onMessageReceived(RrI rrI, Message message);

    void onRequestDropped(RrI rrI, zZm zzm);

    void onRequestFinished(RrI rrI);

    void onRequestQueued(RrI rrI);

    void onRequestStarted(RrI rrI);

    void onSuccess(RrI rrI, Collection<Message> collection);
}
