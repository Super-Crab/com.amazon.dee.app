package com.amazon.alexa.accessory.repositories.cbl;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.internal.repositories.ResultCallable;
import com.amazon.alexa.accessory.internal.repositories.SingleResult;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.protocol.Cbl;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.cbl.CblProducer;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
/* loaded from: classes6.dex */
public final class MemoryCblRepository extends BaseProducer<CblProducer.ActionHandler> implements CblProducer, CblProvider, CblRepository {
    private final BehaviorSubject<Cbl.CblLoginState> cblLoginStateSubject = BehaviorSubject.create();
    private final Object lock = new Object();
    private boolean hasRequestedInitialLoginState = false;

    @SuppressLint({"CheckResult"})
    private void retrieveInitialLoginState() {
        synchronized (this.lock) {
            if (this.hasRequestedInitialLoginState) {
                return;
            }
            this.hasRequestedInitialLoginState = true;
            SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.cbl.-$$Lambda$MemoryCblRepository$d-_sl_leSuG04d8twBIWRUna2ds
                @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
                public final void call(Producer.Result result) {
                    MemoryCblRepository.this.lambda$retrieveInitialLoginState$1$MemoryCblRepository(result);
                }
            }).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.repositories.cbl.-$$Lambda$Z-KFvybXvnIxiJu-5YnyfoQohBw
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    MemoryCblRepository.this.provideCblLoginState((Cbl.CblLoginState) obj);
                }
            }, new Consumer() { // from class: com.amazon.alexa.accessory.repositories.cbl.-$$Lambda$ix6gm-HtUNehsF3LT6a0gVudXXE
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    MemoryCblRepository.this.provideCblLoginStateError((Throwable) obj);
                }
            });
        }
    }

    public /* synthetic */ void lambda$queryCblLoginState$0$MemoryCblRepository(Disposable disposable) throws Throwable {
        retrieveInitialLoginState();
    }

    public /* synthetic */ void lambda$requestCblInformation$2$MemoryCblRepository(Producer.Result result) {
        getHandler().handleGetCblInformation(result);
    }

    public /* synthetic */ void lambda$retrieveInitialLoginState$1$MemoryCblRepository(Producer.Result result) {
        getHandler().handleGetCblLoginState();
    }

    @Override // com.amazon.alexa.accessory.repositories.cbl.CblProvider
    public void provideCblLoginState(Cbl.CblLoginState cblLoginState) {
        synchronized (this.lock) {
            if (!this.cblLoginStateSubject.hasComplete() && !this.cblLoginStateSubject.hasThrowable()) {
                this.cblLoginStateSubject.onNext(cblLoginState);
            }
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.cbl.CblProvider
    public void provideCblLoginStateError(Throwable th) {
        synchronized (this.lock) {
            if (!this.cblLoginStateSubject.hasComplete() && !this.cblLoginStateSubject.hasThrowable()) {
                this.cblLoginStateSubject.onError(th);
            }
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.cbl.CblRepository
    public Observable<Cbl.CblLoginState> queryCblLoginState() {
        return this.cblLoginStateSubject.doOnSubscribe(new Consumer() { // from class: com.amazon.alexa.accessory.repositories.cbl.-$$Lambda$MemoryCblRepository$_3dhVQT2WfadLC526WNBKHh98tw
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MemoryCblRepository.this.lambda$queryCblLoginState$0$MemoryCblRepository((Disposable) obj);
            }
        });
    }

    public void release() {
        synchronized (this.lock) {
            ObservableUtils.release(this.cblLoginStateSubject);
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.cbl.CblRepository
    public Single<Cbl.CblInformation> requestCblInformation() {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.cbl.-$$Lambda$MemoryCblRepository$WtNZoE9T4CAkellIkQ4U1-Vq4f8
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryCblRepository.this.lambda$requestCblInformation$2$MemoryCblRepository(result);
            }
        });
    }
}
