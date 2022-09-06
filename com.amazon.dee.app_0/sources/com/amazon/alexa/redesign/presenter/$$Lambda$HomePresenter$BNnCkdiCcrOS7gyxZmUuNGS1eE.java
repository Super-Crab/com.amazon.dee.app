package com.amazon.alexa.redesign.presenter;

import android.util.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.redesign.presenter.-$$Lambda$HomePresenter$BNnCkdiCcrOS-7gyxZmUuNGS1eE  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$HomePresenter$BNnCkdiCcrOS7gyxZmUuNGS1eE implements Consumer {
    public static final /* synthetic */ $$Lambda$HomePresenter$BNnCkdiCcrOS7gyxZmUuNGS1eE INSTANCE = new $$Lambda$HomePresenter$BNnCkdiCcrOS7gyxZmUuNGS1eE();

    private /* synthetic */ $$Lambda$HomePresenter$BNnCkdiCcrOS7gyxZmUuNGS1eE() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Throwable th = (Throwable) obj;
        Log.e(HomePresenter.TAG, "User logging out, error occurred when clearing home cards from cache.");
    }
}
