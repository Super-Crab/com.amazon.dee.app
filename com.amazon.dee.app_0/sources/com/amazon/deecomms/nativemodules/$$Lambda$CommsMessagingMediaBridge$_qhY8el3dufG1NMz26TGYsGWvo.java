package com.amazon.deecomms.nativemodules;

import rx.functions.Action1;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.nativemodules.-$$Lambda$CommsMessagingMediaBridge$_qhY8el3dufG1NMz-26TGYsGWvo  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsMessagingMediaBridge$_qhY8el3dufG1NMz26TGYsGWvo implements Action1 {
    public static final /* synthetic */ $$Lambda$CommsMessagingMediaBridge$_qhY8el3dufG1NMz26TGYsGWvo INSTANCE = new $$Lambda$CommsMessagingMediaBridge$_qhY8el3dufG1NMz26TGYsGWvo();

    private /* synthetic */ $$Lambda$CommsMessagingMediaBridge$_qhY8el3dufG1NMz26TGYsGWvo() {
    }

    @Override // rx.functions.Action1
    public final void call(Object obj) {
        Throwable th = (Throwable) obj;
        CommsMessagingMediaBridge.LOG.e("[Sharing] Unexpected error occurred while initializing file transfer setup!");
    }
}
