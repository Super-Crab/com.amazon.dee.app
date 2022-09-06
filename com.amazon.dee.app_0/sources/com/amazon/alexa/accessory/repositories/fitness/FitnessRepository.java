package com.amazon.alexa.accessory.repositories.fitness;

import com.amazon.alexa.accessory.protocol.Fitness;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
/* loaded from: classes6.dex */
public interface FitnessRepository {
    Single<FitnessDataSource> getFitnessData();

    Single<FitnessDataSource> getFitnessData(byte[] bArr);

    Observable<FitnessDataAvailableNotification> observeFitnessDataAvailableNotifications();

    Observable<FitnessSessionUpdate> observeFitnessSessionUpdates();

    Observable<Fitness.LiveFitnessData> observeLiveFitnessDataNotifications();

    Observable<Fitness.StopLiveFitnessData> observeStopLiveFitnessDataNotifications();

    Completable setFitnessSession(FitnessSession fitnessSession);

    Completable startLiveFitnessData(List<Integer> list);

    Completable stopLiveFitnessData(List<Integer> list);
}
