package com.amazon.alexa.accessory.repositories.transport;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface TransportRepository {
    Completable requestUpgrade();

    Single<Boolean> shouldUpgrade();
}
