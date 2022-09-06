package com.amazon.alexa.accessory.repositories.state;

import com.amazon.alexa.accessory.protocol.StateOuterClass;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface PhoneStatePlugin {
    Single<StateOuterClass.State> getState();

    Flowable<StateOuterClass.State> queryState();

    Completable setState(StateOuterClass.State state);
}
