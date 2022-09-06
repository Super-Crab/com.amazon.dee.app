package com.amazon.alexa.accessory.internal;

import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes.dex */
public final class UnavailableUserSupplier implements UserSupplier {
    @Override // com.amazon.alexa.accessory.UserSupplier
    public Observable<User> queryUser() {
        return Observable.error(new UnsupportedOperationException("UnavailableUserSupplier does not support this operation."));
    }
}
