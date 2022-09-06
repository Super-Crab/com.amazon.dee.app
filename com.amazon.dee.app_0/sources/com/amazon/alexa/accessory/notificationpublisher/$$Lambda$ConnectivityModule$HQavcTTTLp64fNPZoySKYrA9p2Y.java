package com.amazon.alexa.accessory.notificationpublisher;

import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$ConnectivityModule$HQavcTTTLp64fNPZoySKYrA9p2Y  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$ConnectivityModule$HQavcTTTLp64fNPZoySKYrA9p2Y implements Consumer {
    public static final /* synthetic */ $$Lambda$ConnectivityModule$HQavcTTTLp64fNPZoySKYrA9p2Y INSTANCE = new $$Lambda$ConnectivityModule$HQavcTTTLp64fNPZoySKYrA9p2Y();

    private /* synthetic */ $$Lambda$ConnectivityModule$HQavcTTTLp64fNPZoySKYrA9p2Y() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.e(ConnectivityModule.TAG, "Critical AccessoryClient error in observeSessionReleased", (Throwable) obj);
    }
}
