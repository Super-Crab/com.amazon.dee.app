package com.amazon.alexa.accessory.repositories.state;

import com.amazon.alexa.accessory.protocol.StateOuterClass;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
/* loaded from: classes6.dex */
public interface StateRepository {
    Flowable<StateOuterClass.State> query(StateFeature stateFeature);

    Completable set(StateOuterClass.State state);
}
