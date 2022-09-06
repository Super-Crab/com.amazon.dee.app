package com.amazon.alexa.redesign.presenter;

import android.util.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.redesign.presenter.-$$Lambda$HomePresenter$0q92cUxVXlA1_ifLa3RNzC03wBU  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$HomePresenter$0q92cUxVXlA1_ifLa3RNzC03wBU implements Consumer {
    public static final /* synthetic */ $$Lambda$HomePresenter$0q92cUxVXlA1_ifLa3RNzC03wBU INSTANCE = new $$Lambda$HomePresenter$0q92cUxVXlA1_ifLa3RNzC03wBU();

    private /* synthetic */ $$Lambda$HomePresenter$0q92cUxVXlA1_ifLa3RNzC03wBU() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Throwable th = (Throwable) obj;
        Log.e(HomePresenter.TAG, "Cache Error!");
    }
}
