package com.amazon.alexa.accessory.avsclient.sco;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.sco.-$$Lambda$AlexaClientScoPrioritizer$0DXQhJ5n360217JzJ_GGQAx9Jc4  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AlexaClientScoPrioritizer$0DXQhJ5n360217JzJ_GGQAx9Jc4 implements Consumer {
    public static final /* synthetic */ $$Lambda$AlexaClientScoPrioritizer$0DXQhJ5n360217JzJ_GGQAx9Jc4 INSTANCE = new $$Lambda$AlexaClientScoPrioritizer$0DXQhJ5n360217JzJ_GGQAx9Jc4();

    private /* synthetic */ $$Lambda$AlexaClientScoPrioritizer$0DXQhJ5n360217JzJ_GGQAx9Jc4() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s Critical: determineCurrentStatus failed", (Throwable) obj, AlexaClientScoPrioritizer.TAG);
    }
}
