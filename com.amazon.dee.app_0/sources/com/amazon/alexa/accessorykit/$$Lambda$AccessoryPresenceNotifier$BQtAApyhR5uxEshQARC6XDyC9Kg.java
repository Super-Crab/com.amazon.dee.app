package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryPresenceNotifier$BQtAApyhR5uxEshQARC6XDyC9Kg  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryPresenceNotifier$BQtAApyhR5uxEshQARC6XDyC9Kg implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessoryPresenceNotifier$BQtAApyhR5uxEshQARC6XDyC9Kg INSTANCE = new $$Lambda$AccessoryPresenceNotifier$BQtAApyhR5uxEshQARC6XDyC9Kg();

    private /* synthetic */ $$Lambda$AccessoryPresenceNotifier$BQtAApyhR5uxEshQARC6XDyC9Kg() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Throwable th = (Throwable) obj;
        Logger.e("AccessoryPresenceNotifier:Failed to cache sessions");
    }
}
