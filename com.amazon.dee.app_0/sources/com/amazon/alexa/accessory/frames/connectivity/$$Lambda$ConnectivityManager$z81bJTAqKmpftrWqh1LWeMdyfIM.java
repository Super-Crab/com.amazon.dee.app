package com.amazon.alexa.accessory.frames.connectivity;

import com.amazon.alexa.accessory.frames.utils.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.frames.connectivity.-$$Lambda$ConnectivityManager$z81bJTAqKmpftrWqh1LWeMdyfIM  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$ConnectivityManager$z81bJTAqKmpftrWqh1LWeMdyfIM implements Consumer {
    public static final /* synthetic */ $$Lambda$ConnectivityManager$z81bJTAqKmpftrWqh1LWeMdyfIM INSTANCE = new $$Lambda$ConnectivityManager$z81bJTAqKmpftrWqh1LWeMdyfIM();

    private /* synthetic */ $$Lambda$ConnectivityManager$z81bJTAqKmpftrWqh1LWeMdyfIM() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.d(ConnectivityManager.TAG, "Critical AccessoryClient error in observeSessionConnected", (Throwable) obj);
    }
}
