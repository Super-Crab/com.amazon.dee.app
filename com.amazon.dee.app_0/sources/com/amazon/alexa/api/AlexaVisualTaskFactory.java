package com.amazon.alexa.api;

import android.os.IBinder;
import com.amazon.alexa.KvZ;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class AlexaVisualTaskFactory {
    public final KvZ messageReceiverAuthority;

    @Inject
    public AlexaVisualTaskFactory(KvZ kvZ) {
        this.messageReceiverAuthority = kvZ;
    }

    public AlexaVisualTask createAlexaVisualTask(IBinder iBinder, ExtendedClient extendedClient) {
        return new VisualTaskSender(extendedClient, iBinder, this.messageReceiverAuthority.zZm(extendedClient));
    }
}
