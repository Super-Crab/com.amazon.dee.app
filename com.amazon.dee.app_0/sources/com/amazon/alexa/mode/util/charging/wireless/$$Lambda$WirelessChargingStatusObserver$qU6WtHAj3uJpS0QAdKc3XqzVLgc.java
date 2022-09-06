package com.amazon.alexa.mode.util.charging.wireless;

import android.util.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.mode.util.charging.wireless.-$$Lambda$WirelessChargingStatusObserver$qU6WtHAj3uJpS0QAdKc3XqzVLgc  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$WirelessChargingStatusObserver$qU6WtHAj3uJpS0QAdKc3XqzVLgc implements Consumer {
    public static final /* synthetic */ $$Lambda$WirelessChargingStatusObserver$qU6WtHAj3uJpS0QAdKc3XqzVLgc INSTANCE = new $$Lambda$WirelessChargingStatusObserver$qU6WtHAj3uJpS0QAdKc3XqzVLgc();

    private /* synthetic */ $$Lambda$WirelessChargingStatusObserver$qU6WtHAj3uJpS0QAdKc3XqzVLgc() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.e("AccessoryObserver", "Unexpected error: " + ((Throwable) obj));
    }
}
