package com.amazon.alexa.accessory.repositories.fitness;

import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.internal.repositories.ResultCallable;
import com.amazon.alexa.accessory.internal.repositories.SingleResult;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.protocol.Fitness;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.fitness.FitnessProducer;
import com.amazon.alexa.accessory.repositories.fitness.FitnessProvider;
import com.amazon.alexa.accessory.repositories.fitness.FitnessSessionUpdate;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.List;
/* loaded from: classes6.dex */
public final class MemoryFitnessRepository extends BaseProducer<FitnessProducer.ActionHandler> implements FitnessProvider, FitnessProducer, FitnessRepository {
    private PublishSubject<FitnessDataAvailableNotification> fitnessDataAvailableNotificationObservableEmitter = PublishSubject.create();
    private PublishSubject<FitnessSessionUpdate> fitnessSessionUpdateObservableEmitter = PublishSubject.create();
    private PublishSubject<Fitness.LiveFitnessData> liveFitnessDataNotificationObservervableEmitter = PublishSubject.create();
    private PublishSubject<Fitness.StopLiveFitnessData> stopLiveFitnessDataNotificationObservervableEmitter = PublishSubject.create();

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessRepository
    public Single<FitnessDataSource> getFitnessData() {
        return getFitnessData(null);
    }

    public /* synthetic */ void lambda$getFitnessData$0$MemoryFitnessRepository(byte[] bArr, Producer.Result result) {
        getHandler().handleGetFitnessData(bArr, result);
    }

    public /* synthetic */ void lambda$setFitnessSession$1$MemoryFitnessRepository(FitnessSession fitnessSession, Producer.Result result) {
        getHandler().handleSetFitnessSession(fitnessSession, result);
    }

    public /* synthetic */ void lambda$setFitnessSession$2$MemoryFitnessRepository(FitnessSession fitnessSession) throws Throwable {
        this.fitnessSessionUpdateObservableEmitter.onNext(new FitnessSessionUpdate(FitnessSessionUpdate.Origin.APPLICATION, fitnessSession, new FitnessSessionUpdate.OnUpdateProcessedListener() { // from class: com.amazon.alexa.accessory.repositories.fitness.MemoryFitnessRepository.1
            @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessSessionUpdate.OnUpdateProcessedListener
            public void onFailure(Throwable th) {
            }

            @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessSessionUpdate.OnUpdateProcessedListener
            public void onSuccess() {
            }
        }));
        Logger.d("Emitted a FitnessSessionUpdate");
    }

    public /* synthetic */ void lambda$startLiveFitnessData$3$MemoryFitnessRepository(List list, Producer.Result result) {
        getHandler().handleStartLiveFitnessData(list, result);
    }

    public /* synthetic */ void lambda$stopLiveFitnessData$4$MemoryFitnessRepository(List list, Producer.Result result) {
        getHandler().handleStopLiveFitnessData(list, result);
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessRepository
    public Observable<FitnessDataAvailableNotification> observeFitnessDataAvailableNotifications() {
        return this.fitnessDataAvailableNotificationObservableEmitter;
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessRepository
    public Observable<FitnessSessionUpdate> observeFitnessSessionUpdates() {
        return this.fitnessSessionUpdateObservableEmitter;
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessRepository
    public Observable<Fitness.LiveFitnessData> observeLiveFitnessDataNotifications() {
        return this.liveFitnessDataNotificationObservervableEmitter;
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessRepository
    public Observable<Fitness.StopLiveFitnessData> observeStopLiveFitnessDataNotifications() {
        return this.stopLiveFitnessDataNotificationObservervableEmitter;
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessProvider
    public void onFitnessDataAvailable(FitnessProvider.OnSuccessListener onSuccessListener) {
        this.fitnessDataAvailableNotificationObservableEmitter.onNext(FitnessDataAvailableNotification.INSTANCE);
        Logger.d("Emitted a FitnessDataAvailableNotification");
        onSuccessListener.onSuccess();
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessProvider
    public void onLiveFitnessData(Fitness.LiveFitnessData liveFitnessData, FitnessProvider.OnSuccessListener onSuccessListener) {
        this.liveFitnessDataNotificationObservervableEmitter.onNext(liveFitnessData);
        Logger.d("Emitted a LiveFitnessDataAvailableNotification");
        onSuccessListener.onSuccess();
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessProvider
    public void onStopLiveFitnessData(Fitness.StopLiveFitnessData stopLiveFitnessData, FitnessProvider.OnSuccessListener onSuccessListener) {
        this.stopLiveFitnessDataNotificationObservervableEmitter.onNext(stopLiveFitnessData);
        Logger.d("Emitted a StopLiveFitnessDataNotification");
        onSuccessListener.onSuccess();
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessProvider
    public void onSyncFitnessSession(FitnessSession fitnessSession, final FitnessProvider.OnSuccessListener onSuccessListener, final FitnessProvider.OnFailureListener onFailureListener) {
        this.fitnessSessionUpdateObservableEmitter.onNext(new FitnessSessionUpdate(FitnessSessionUpdate.Origin.ACCESSORY, fitnessSession, new FitnessSessionUpdate.OnUpdateProcessedListener() { // from class: com.amazon.alexa.accessory.repositories.fitness.MemoryFitnessRepository.2
            @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessSessionUpdate.OnUpdateProcessedListener
            public void onFailure(Throwable th) {
                Logger.e("Failed to process a FitnessSession update");
                onFailureListener.onFailure();
            }

            @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessSessionUpdate.OnUpdateProcessedListener
            public void onSuccess() {
                onSuccessListener.onSuccess();
            }
        }));
        Logger.d("Emitted a FitnessSessionUpdate");
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessRepository
    public Completable setFitnessSession(final FitnessSession fitnessSession) {
        return CompletableResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.fitness.-$$Lambda$MemoryFitnessRepository$bYu3CnhXNd_X_wCDhr7U4HK-lv4
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryFitnessRepository.this.lambda$setFitnessSession$1$MemoryFitnessRepository(fitnessSession, result);
            }
        }).doOnComplete(new Action() { // from class: com.amazon.alexa.accessory.repositories.fitness.-$$Lambda$MemoryFitnessRepository$edgitOqVSnrm8jwyW2LsBSw2BRI
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                MemoryFitnessRepository.this.lambda$setFitnessSession$2$MemoryFitnessRepository(fitnessSession);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessRepository
    public Completable startLiveFitnessData(final List<Integer> list) {
        return CompletableResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.fitness.-$$Lambda$MemoryFitnessRepository$AiEqcpKCJdJQq_QeerfcYSOWR5M
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryFitnessRepository.this.lambda$startLiveFitnessData$3$MemoryFitnessRepository(list, result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessRepository
    public Completable stopLiveFitnessData(final List<Integer> list) {
        return CompletableResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.fitness.-$$Lambda$MemoryFitnessRepository$jXZlAjVoK1ft-OYg_cslzaawIDs
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryFitnessRepository.this.lambda$stopLiveFitnessData$4$MemoryFitnessRepository(list, result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessRepository
    public Single<FitnessDataSource> getFitnessData(final byte[] bArr) {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.fitness.-$$Lambda$MemoryFitnessRepository$2Q72Gu7AezgIK1bCX-kWgR5jwLE
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryFitnessRepository.this.lambda$getFitnessData$0$MemoryFitnessRepository(bArr, result);
            }
        });
    }
}
