package com.amazon.whisperjoin.softap.observables.rx.transforms;

import rx.Observable;
import rx.Single;
/* loaded from: classes13.dex */
public interface ServiceCall {
    <T> Observable.Transformer<T, T> observable();

    <T> Single.Transformer<T, T> single();
}
