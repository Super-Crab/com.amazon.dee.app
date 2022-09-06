package com.amazon.alexa.accessory.repositories.inputevents;

import com.amazon.alexa.accessory.protocol.Input;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
/* loaded from: classes6.dex */
public interface InputRepository {
    Flowable<Input.InputBehaviorConfigurationSet> queryConfiguration(int i);

    Completable resetConfiguration(int i);

    Completable setConfiguration(int i, Input.InputBehaviorConfiguration inputBehaviorConfiguration);
}
