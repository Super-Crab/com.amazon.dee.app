package com.amazon.alexa.accessory;

import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes.dex */
public interface UserSupplier {
    Observable<User> queryUser();
}
