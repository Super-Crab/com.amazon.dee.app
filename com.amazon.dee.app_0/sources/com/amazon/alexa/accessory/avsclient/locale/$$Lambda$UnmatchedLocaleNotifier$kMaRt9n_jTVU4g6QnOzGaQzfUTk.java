package com.amazon.alexa.accessory.avsclient.locale;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$UnmatchedLocaleNotifier$kMaRt9n_jTVU4g6QnOzGaQzfUTk  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$UnmatchedLocaleNotifier$kMaRt9n_jTVU4g6QnOzGaQzfUTk implements Consumer {
    public static final /* synthetic */ $$Lambda$UnmatchedLocaleNotifier$kMaRt9n_jTVU4g6QnOzGaQzfUTk INSTANCE = new $$Lambda$UnmatchedLocaleNotifier$kMaRt9n_jTVU4g6QnOzGaQzfUTk();

    private /* synthetic */ $$Lambda$UnmatchedLocaleNotifier$kMaRt9n_jTVU4g6QnOzGaQzfUTk() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Boolean bool = (Boolean) obj;
        Logger.d("%s: Finished conditionally displaying locale notification", UnmatchedLocaleNotifier.TAG);
    }
}
