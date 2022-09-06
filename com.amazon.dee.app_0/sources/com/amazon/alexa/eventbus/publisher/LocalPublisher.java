package com.amazon.alexa.eventbus.publisher;

import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Publisher;
import com.amazon.alexa.eventbus.core.Dispatcher;
/* loaded from: classes7.dex */
public class LocalPublisher implements Publisher {
    @NonNull
    private final Dispatcher dispatcher;

    public LocalPublisher(@NonNull Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override // com.amazon.alexa.eventbus.api.Publisher
    public void publish(@NonNull Message message) {
        this.dispatcher.dispatch(message);
    }
}
