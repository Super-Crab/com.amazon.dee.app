package com.amazon.deecomms.accessories;

import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.accessories.-$$Lambda$CommsAccessorySessionListener$zbRVnoCwTkULUI-bx_7g-tBSmdQ  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsAccessorySessionListener$zbRVnoCwTkULUIbx_7gtBSmdQ implements Consumer {
    public static final /* synthetic */ $$Lambda$CommsAccessorySessionListener$zbRVnoCwTkULUIbx_7gtBSmdQ INSTANCE = new $$Lambda$CommsAccessorySessionListener$zbRVnoCwTkULUIbx_7gtBSmdQ();

    private /* synthetic */ $$Lambda$CommsAccessorySessionListener$zbRVnoCwTkULUIbx_7gtBSmdQ() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        CommsAccessorySessionListener.LOG.e("Error retrieving Device Master id", ((Throwable) obj).getMessage());
    }
}
