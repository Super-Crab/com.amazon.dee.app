package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryPresenceNotifier$093xzUQWDS4uFZ5aBGAoPtIV25I  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryPresenceNotifier$093xzUQWDS4uFZ5aBGAoPtIV25I implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessoryPresenceNotifier$093xzUQWDS4uFZ5aBGAoPtIV25I INSTANCE = new $$Lambda$AccessoryPresenceNotifier$093xzUQWDS4uFZ5aBGAoPtIV25I();

    private /* synthetic */ $$Lambda$AccessoryPresenceNotifier$093xzUQWDS4uFZ5aBGAoPtIV25I() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%sError in Session listener", (Throwable) obj, AccessoryPresenceNotifier.TAG);
    }
}
