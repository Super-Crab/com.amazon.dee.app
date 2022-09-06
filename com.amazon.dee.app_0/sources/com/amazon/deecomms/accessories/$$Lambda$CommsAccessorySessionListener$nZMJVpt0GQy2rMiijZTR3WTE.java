package com.amazon.deecomms.accessories;

import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.accessories.-$$Lambda$CommsAccessorySessionListener$-nZM--JVpt0GQy2rMiijZTR3WTE  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsAccessorySessionListener$nZMJVpt0GQy2rMiijZTR3WTE implements Consumer {
    public static final /* synthetic */ $$Lambda$CommsAccessorySessionListener$nZMJVpt0GQy2rMiijZTR3WTE INSTANCE = new $$Lambda$CommsAccessorySessionListener$nZMJVpt0GQy2rMiijZTR3WTE();

    private /* synthetic */ $$Lambda$CommsAccessorySessionListener$nZMJVpt0GQy2rMiijZTR3WTE() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        CommsAccessorySessionListener.LOG.e("CRITICAL error", (Throwable) obj);
    }
}
