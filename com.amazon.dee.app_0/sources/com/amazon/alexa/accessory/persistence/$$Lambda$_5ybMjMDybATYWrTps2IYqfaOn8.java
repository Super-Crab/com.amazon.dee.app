package com.amazon.alexa.accessory.persistence;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableConverter;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.persistence.-$$Lambda$_5ybMjMDybATYWrTps2IYqfaOn8  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$_5ybMjMDybATYWrTps2IYqfaOn8 implements ObservableConverter {
    public static final /* synthetic */ $$Lambda$_5ybMjMDybATYWrTps2IYqfaOn8 INSTANCE = new $$Lambda$_5ybMjMDybATYWrTps2IYqfaOn8();

    private /* synthetic */ $$Lambda$_5ybMjMDybATYWrTps2IYqfaOn8() {
    }

    @Override // io.reactivex.rxjava3.core.ObservableConverter
    public final Object apply(Observable observable) {
        return new ObservableQuery(observable);
    }
}
