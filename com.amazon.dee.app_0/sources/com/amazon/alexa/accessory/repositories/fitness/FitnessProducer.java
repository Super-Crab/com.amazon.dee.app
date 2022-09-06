package com.amazon.alexa.accessory.repositories.fitness;

import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.repositories.Producer;
import java.util.List;
/* loaded from: classes6.dex */
public interface FitnessProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleGetFitnessData(byte[] bArr, Producer.Result<FitnessDataSource> result);

        void handleSetFitnessSession(FitnessSession fitnessSession, Producer.Result<CompletableResult.Value> result);

        void handleStartLiveFitnessData(List<Integer> list, Producer.Result<CompletableResult.Value> result);

        void handleStopLiveFitnessData(List<Integer> list, Producer.Result<CompletableResult.Value> result);
    }
}
