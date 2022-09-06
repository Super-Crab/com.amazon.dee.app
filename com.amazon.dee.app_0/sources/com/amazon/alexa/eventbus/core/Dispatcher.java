package com.amazon.alexa.eventbus.core;

import androidx.annotation.NonNull;
import com.amazon.alexa.component.api.ServiceLifecycle;
import com.amazon.alexa.eventbus.api.Message;
/* loaded from: classes7.dex */
public interface Dispatcher extends ServiceLifecycle {
    void dispatch(@NonNull Message message);
}
