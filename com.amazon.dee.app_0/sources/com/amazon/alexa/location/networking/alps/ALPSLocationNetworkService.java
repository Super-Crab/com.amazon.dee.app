package com.amazon.alexa.location.networking.alps;

import io.reactivex.rxjava3.core.Completable;
/* loaded from: classes9.dex */
public interface ALPSLocationNetworkService {
    Completable reportLocation(double d, double d2, double d3);

    Completable reportLocation(double d, double d2, double d3, String str);
}
