package com.amazon.alexa.accessory.avsclient;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.-$$Lambda$AlexaScoSessionCache$ap1Gfw8h95B0V1n75a2Ax71TP1M  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AlexaScoSessionCache$ap1Gfw8h95B0V1n75a2Ax71TP1M implements Consumer {
    public static final /* synthetic */ $$Lambda$AlexaScoSessionCache$ap1Gfw8h95B0V1n75a2Ax71TP1M INSTANCE = new $$Lambda$AlexaScoSessionCache$ap1Gfw8h95B0V1n75a2Ax71TP1M();

    private /* synthetic */ $$Lambda$AlexaScoSessionCache$ap1Gfw8h95B0V1n75a2Ax71TP1M() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        AccessorySession accessorySession = (AccessorySession) obj;
        Logger.d("AlexaScoSessionCache: cached all SCO preferences");
    }
}
