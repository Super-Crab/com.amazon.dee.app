package com.amazon.communication;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
import android.content.Context;
import android.os.IBinder;
import java.util.concurrent.CountDownLatch;
/* loaded from: classes12.dex */
public interface ITCommService {
    IBinder getBinder();

    void initialize(Context context, CountDownLatch countDownLatch);

    void routeMessage(EndpointIdentity endpointIdentity, Message message, int i);

    void routeMessageFragment(ParcelableEndpointIdentity parcelableEndpointIdentity, int i, MessageEnvelope messageEnvelope, boolean z, int i2);

    void shutdown();
}
