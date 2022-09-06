package com.amazon.alexa.dnssd.subscribers;

import android.util.Log;
import io.reactivex.rxjava3.functions.Action;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.dnssd.subscribers.-$$Lambda$StartDiscoverySubscriber$cbBhDI5p7xPBIyHMnd0hdbH8GQQ  reason: invalid class name */
/* loaded from: classes7.dex */
public final /* synthetic */ class $$Lambda$StartDiscoverySubscriber$cbBhDI5p7xPBIyHMnd0hdbH8GQQ implements Action {
    public static final /* synthetic */ $$Lambda$StartDiscoverySubscriber$cbBhDI5p7xPBIyHMnd0hdbH8GQQ INSTANCE = new $$Lambda$StartDiscoverySubscriber$cbBhDI5p7xPBIyHMnd0hdbH8GQQ();

    private /* synthetic */ $$Lambda$StartDiscoverySubscriber$cbBhDI5p7xPBIyHMnd0hdbH8GQQ() {
    }

    @Override // io.reactivex.rxjava3.functions.Action
    public final void run() {
        Log.i(StartDiscoverySubscriber.TAG, "Discovery ended");
    }
}
