package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$rtpc5P86N0t3kXiVK-hzRkgguqM  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$ConnectivityInteractor$rtpc5P86N0t3kXiVKhzRkgguqM implements Consumer {
    public static final /* synthetic */ $$Lambda$ConnectivityInteractor$rtpc5P86N0t3kXiVKhzRkgguqM INSTANCE = new $$Lambda$ConnectivityInteractor$rtpc5P86N0t3kXiVKhzRkgguqM();

    private /* synthetic */ $$Lambda$ConnectivityInteractor$rtpc5P86N0t3kXiVKhzRkgguqM() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("ConnectivityInteractor: failed to query expired standby accessories", (Throwable) obj);
    }
}
