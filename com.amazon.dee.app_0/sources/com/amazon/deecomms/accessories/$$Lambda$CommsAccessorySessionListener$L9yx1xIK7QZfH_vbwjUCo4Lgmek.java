package com.amazon.deecomms.accessories;

import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.accessories.-$$Lambda$CommsAccessorySessionListener$L9yx1xIK7QZfH_vbwjUCo4Lgmek  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsAccessorySessionListener$L9yx1xIK7QZfH_vbwjUCo4Lgmek implements Consumer {
    public static final /* synthetic */ $$Lambda$CommsAccessorySessionListener$L9yx1xIK7QZfH_vbwjUCo4Lgmek INSTANCE = new $$Lambda$CommsAccessorySessionListener$L9yx1xIK7QZfH_vbwjUCo4Lgmek();

    private /* synthetic */ $$Lambda$CommsAccessorySessionListener$L9yx1xIK7QZfH_vbwjUCo4Lgmek() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        CommsAccessorySessionListener.LOG.e("CRITICAL error", (Throwable) obj);
    }
}
