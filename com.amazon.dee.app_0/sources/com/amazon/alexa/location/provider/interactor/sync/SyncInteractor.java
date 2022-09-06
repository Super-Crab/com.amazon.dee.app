package com.amazon.alexa.location.provider.interactor.sync;

import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.eventbus.api.MessageHandler;
/* loaded from: classes9.dex */
public interface SyncInteractor {
    MessageFilter getMessageFilter();

    MessageHandler getMessageHandler();

    String getName();
}
