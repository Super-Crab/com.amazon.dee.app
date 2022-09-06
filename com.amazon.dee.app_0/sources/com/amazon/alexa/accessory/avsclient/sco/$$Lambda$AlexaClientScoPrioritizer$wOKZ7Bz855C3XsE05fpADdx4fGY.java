package com.amazon.alexa.accessory.avsclient.sco;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.sco.-$$Lambda$AlexaClientScoPrioritizer$wOKZ7Bz855C3XsE05fpADdx4fGY  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AlexaClientScoPrioritizer$wOKZ7Bz855C3XsE05fpADdx4fGY implements Consumer {
    public static final /* synthetic */ $$Lambda$AlexaClientScoPrioritizer$wOKZ7Bz855C3XsE05fpADdx4fGY INSTANCE = new $$Lambda$AlexaClientScoPrioritizer$wOKZ7Bz855C3XsE05fpADdx4fGY();

    private /* synthetic */ $$Lambda$AlexaClientScoPrioritizer$wOKZ7Bz855C3XsE05fpADdx4fGY() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s Critical: observeSessions failed", (Throwable) obj, AlexaClientScoPrioritizer.TAG);
    }
}
