package com.amazon.alexa.accessory.avsclient.locale;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Action;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$UnmatchedLocaleNotifier$i6kJ6VUX9wKkITZp7Hi29W3ylEw  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$UnmatchedLocaleNotifier$i6kJ6VUX9wKkITZp7Hi29W3ylEw implements Action {
    public static final /* synthetic */ $$Lambda$UnmatchedLocaleNotifier$i6kJ6VUX9wKkITZp7Hi29W3ylEw INSTANCE = new $$Lambda$UnmatchedLocaleNotifier$i6kJ6VUX9wKkITZp7Hi29W3ylEw();

    private /* synthetic */ $$Lambda$UnmatchedLocaleNotifier$i6kJ6VUX9wKkITZp7Hi29W3ylEw() {
    }

    @Override // io.reactivex.rxjava3.functions.Action
    public final void run() {
        Logger.d("%s: List of locales for which we've displayed a notification has been cleared because there is no user logged in.", UnmatchedLocaleNotifier.TAG);
    }
}
