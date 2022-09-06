package com.amazon.deecomms.alexa.unsent.event;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.incallcommands.models.InCallCommandModel;
/* loaded from: classes12.dex */
public interface QueuedAlexaEvents {
    void add(@NonNull InCallCommandModel inCallCommandModel);

    void clear();

    int getSize();

    void send();
}
