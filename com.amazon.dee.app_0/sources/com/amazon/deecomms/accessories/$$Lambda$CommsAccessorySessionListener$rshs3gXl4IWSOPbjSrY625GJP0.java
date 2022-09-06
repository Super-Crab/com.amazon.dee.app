package com.amazon.deecomms.accessories;

import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.accessories.-$$Lambda$CommsAccessorySessionListener$rshs3gXl4IWS-OPbjSrY625GJP0  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsAccessorySessionListener$rshs3gXl4IWSOPbjSrY625GJP0 implements Consumer {
    public static final /* synthetic */ $$Lambda$CommsAccessorySessionListener$rshs3gXl4IWSOPbjSrY625GJP0 INSTANCE = new $$Lambda$CommsAccessorySessionListener$rshs3gXl4IWSOPbjSrY625GJP0();

    private /* synthetic */ $$Lambda$CommsAccessorySessionListener$rshs3gXl4IWSOPbjSrY625GJP0() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        CommsAccessorySessionListener.LOG.e("CRITICAL error", (Throwable) obj);
    }
}
