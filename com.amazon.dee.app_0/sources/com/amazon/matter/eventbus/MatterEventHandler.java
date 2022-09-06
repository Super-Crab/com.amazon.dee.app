package com.amazon.matter.eventbus;

import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.Message;
/* loaded from: classes9.dex */
interface MatterEventHandler {
    void handleMatterRequest(@NonNull Message message);
}
