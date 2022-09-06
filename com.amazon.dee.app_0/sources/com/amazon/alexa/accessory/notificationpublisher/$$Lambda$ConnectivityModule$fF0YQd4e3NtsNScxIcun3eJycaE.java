package com.amazon.alexa.accessory.notificationpublisher;

import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$ConnectivityModule$fF0YQd4e3NtsNScxIcun3eJycaE  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$ConnectivityModule$fF0YQd4e3NtsNScxIcun3eJycaE implements Consumer {
    public static final /* synthetic */ $$Lambda$ConnectivityModule$fF0YQd4e3NtsNScxIcun3eJycaE INSTANCE = new $$Lambda$ConnectivityModule$fF0YQd4e3NtsNScxIcun3eJycaE();

    private /* synthetic */ $$Lambda$ConnectivityModule$fF0YQd4e3NtsNScxIcun3eJycaE() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.e(ConnectivityModule.TAG, "Critical AccessoryClient error in observeSessionConnected", (Throwable) obj);
    }
}
