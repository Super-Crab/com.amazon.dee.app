package com.amazon.alexa.accessory.internal.session;

import com.amazon.alexa.accessory.internal.connection.QueueTransportDispatcher;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.session.-$$Lambda$BaseAccessorySession$P4JOxs6shw2pUp1TWb6soSa22UM  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$BaseAccessorySession$P4JOxs6shw2pUp1TWb6soSa22UM implements QueueTransportDispatcher.DrainablePredicate {
    public static final /* synthetic */ $$Lambda$BaseAccessorySession$P4JOxs6shw2pUp1TWb6soSa22UM INSTANCE = new $$Lambda$BaseAccessorySession$P4JOxs6shw2pUp1TWb6soSa22UM();

    private /* synthetic */ $$Lambda$BaseAccessorySession$P4JOxs6shw2pUp1TWb6soSa22UM() {
    }

    @Override // com.amazon.alexa.accessory.internal.connection.QueueTransportDispatcher.DrainablePredicate
    public final boolean canDrain(Object obj) {
        return BaseAccessorySession.lambda$new$0((Boolean) obj);
    }
}
