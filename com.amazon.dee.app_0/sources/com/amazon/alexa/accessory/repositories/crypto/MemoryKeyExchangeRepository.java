package com.amazon.alexa.accessory.repositories.crypto;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.ReplaySubject;
/* loaded from: classes6.dex */
public class MemoryKeyExchangeRepository implements KeyExchangeRepository, KeyExchangeProvider {
    private final ReplaySubject<Boolean> isAwaitingDerivedKeysSubject = ReplaySubject.createWithSize(1);

    @Override // com.amazon.alexa.accessory.repositories.crypto.KeyExchangeProvider
    public void provideIsAwaitingDerivedKeys(boolean z) {
        Preconditions.mainThread();
        this.isAwaitingDerivedKeysSubject.onNext(Boolean.valueOf(z));
    }

    @Override // com.amazon.alexa.accessory.repositories.crypto.KeyExchangeRepository
    public Observable<Boolean> queryIsAwaitingDerivedKeys() {
        return this.isAwaitingDerivedKeysSubject;
    }
}
