package com.amazon.alexa.accessory.avsclient.locale;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$UnmatchedLocaleNotifier$16LdZpdaJQkTGYm41NdKDvVEctA  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$UnmatchedLocaleNotifier$16LdZpdaJQkTGYm41NdKDvVEctA implements Consumer {
    public static final /* synthetic */ $$Lambda$UnmatchedLocaleNotifier$16LdZpdaJQkTGYm41NdKDvVEctA INSTANCE = new $$Lambda$UnmatchedLocaleNotifier$16LdZpdaJQkTGYm41NdKDvVEctA();

    private /* synthetic */ $$Lambda$UnmatchedLocaleNotifier$16LdZpdaJQkTGYm41NdKDvVEctA() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s: Failed to perform logic to conditionally display the locale notification: ", (Throwable) obj, UnmatchedLocaleNotifier.TAG);
    }
}
