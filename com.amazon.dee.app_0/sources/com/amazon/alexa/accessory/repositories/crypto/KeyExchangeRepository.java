package com.amazon.alexa.accessory.repositories.crypto;

import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes6.dex */
public interface KeyExchangeRepository {
    Observable<Boolean> queryIsAwaitingDerivedKeys();
}
