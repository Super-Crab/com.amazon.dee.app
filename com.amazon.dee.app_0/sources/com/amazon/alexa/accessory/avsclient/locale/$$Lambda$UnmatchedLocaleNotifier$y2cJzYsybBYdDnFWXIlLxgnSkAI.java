package com.amazon.alexa.accessory.avsclient.locale;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$UnmatchedLocaleNotifier$y2cJzYsybBYdDnFWXIlLxgnSkAI  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$UnmatchedLocaleNotifier$y2cJzYsybBYdDnFWXIlLxgnSkAI implements Function {
    public static final /* synthetic */ $$Lambda$UnmatchedLocaleNotifier$y2cJzYsybBYdDnFWXIlLxgnSkAI INSTANCE = new $$Lambda$UnmatchedLocaleNotifier$y2cJzYsybBYdDnFWXIlLxgnSkAI();

    private /* synthetic */ $$Lambda$UnmatchedLocaleNotifier$y2cJzYsybBYdDnFWXIlLxgnSkAI() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return Logger.e("%s: Error querying device information. Assuming the device is blocklisted for displaying the locale notification.", (Throwable) obj, UnmatchedLocaleNotifier.TAG);
    }
}
