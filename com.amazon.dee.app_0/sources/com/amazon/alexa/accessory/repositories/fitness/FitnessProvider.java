package com.amazon.alexa.accessory.repositories.fitness;

import com.amazon.alexa.accessory.protocol.Fitness;
/* loaded from: classes6.dex */
public interface FitnessProvider {

    /* loaded from: classes6.dex */
    public interface OnFailureListener {
        void onFailure();
    }

    /* loaded from: classes6.dex */
    public interface OnSuccessListener {
        void onSuccess();
    }

    void onFitnessDataAvailable(OnSuccessListener onSuccessListener);

    void onLiveFitnessData(Fitness.LiveFitnessData liveFitnessData, OnSuccessListener onSuccessListener);

    void onStopLiveFitnessData(Fitness.StopLiveFitnessData stopLiveFitnessData, OnSuccessListener onSuccessListener);

    void onSyncFitnessSession(FitnessSession fitnessSession, OnSuccessListener onSuccessListener, OnFailureListener onFailureListener);
}
