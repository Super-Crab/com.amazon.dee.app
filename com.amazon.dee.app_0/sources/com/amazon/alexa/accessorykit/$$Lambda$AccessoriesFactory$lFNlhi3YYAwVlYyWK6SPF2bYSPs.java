package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.-$$Lambda$AccessoriesFactory$lFNlhi3YYAwVlYyWK6SPF2bYSPs  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoriesFactory$lFNlhi3YYAwVlYyWK6SPF2bYSPs implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessoriesFactory$lFNlhi3YYAwVlYyWK6SPF2bYSPs INSTANCE = new $$Lambda$AccessoriesFactory$lFNlhi3YYAwVlYyWK6SPF2bYSPs();

    private /* synthetic */ $$Lambda$AccessoriesFactory$lFNlhi3YYAwVlYyWK6SPF2bYSPs() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Error observing users to initialize DeviceEngagementMetrics.", (Throwable) obj);
    }
}
