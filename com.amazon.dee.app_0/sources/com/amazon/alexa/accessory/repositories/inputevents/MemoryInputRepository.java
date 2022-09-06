package com.amazon.alexa.accessory.repositories.inputevents;

import android.util.SparseArray;
import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.internal.repositories.ResultCallable;
import com.amazon.alexa.accessory.internal.repositories.SingleResult;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Input;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.inputevents.InputProducer;
import com.amazon.alexa.accessory.repositories.inputevents.MemoryInputRepository;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.processors.FlowableProcessor;
import io.reactivex.rxjava3.processors.PublishProcessor;
import java.util.Arrays;
import java.util.List;
import org.reactivestreams.Publisher;
/* loaded from: classes6.dex */
public final class MemoryInputRepository extends BaseProducer<InputProducer.ActionHandler> implements InputRepository, InputProvider, InputProducer {
    private final SparseArray<Input.InputBehaviorConfigurationSet> configurations = new SparseArray<>();
    private final FlowableProcessor<DeviceInputConfiguration> configurationPublisher = PublishProcessor.create().toSerialized();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static final class DeviceInputConfiguration {
        private final Input.InputBehaviorConfigurationSet configuration;
        private final int deviceId;

        public Input.InputBehaviorConfigurationSet getConfiguration() {
            return this.configuration;
        }

        public int getDeviceId() {
            return this.deviceId;
        }

        private DeviceInputConfiguration(Input.InputBehaviorConfigurationSet inputBehaviorConfigurationSet, int i) {
            Preconditions.notNull(inputBehaviorConfigurationSet, PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY);
            this.configuration = inputBehaviorConfigurationSet;
            this.deviceId = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: cacheConfiguration */
    public void lambda$getCachedOrRequest$5$MemoryInputRepository(int i, Input.InputBehaviorConfigurationSet inputBehaviorConfigurationSet) {
        Preconditions.notNull(inputBehaviorConfigurationSet, PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY);
        synchronized (this.configurations) {
            this.configurations.put(i, inputBehaviorConfigurationSet);
        }
    }

    private List<Publisher<Input.InputBehaviorConfigurationSet>> createQueryPublishers(final int i) {
        return Arrays.asList(getCachedOrRequest(i), this.configurationPublisher.filter(new Predicate() { // from class: com.amazon.alexa.accessory.repositories.inputevents.-$$Lambda$MemoryInputRepository$_8WE4ST7AtAIUAeKBwrVfiBtDTI
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return MemoryInputRepository.lambda$createQueryPublishers$3(i, (MemoryInputRepository.DeviceInputConfiguration) obj);
            }
        }).map($$Lambda$zdnt8UJmqQNaYBR1J4JXRWBWz8.INSTANCE));
    }

    private Maybe<Input.InputBehaviorConfigurationSet> getCached(final int i) {
        return Maybe.defer(new Supplier() { // from class: com.amazon.alexa.accessory.repositories.inputevents.-$$Lambda$MemoryInputRepository$sAVyHjE2lCWYwdM0I_y3it4RlmQ
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return MemoryInputRepository.this.lambda$getCached$6$MemoryInputRepository(i);
            }
        });
    }

    private Flowable<Input.InputBehaviorConfigurationSet> getCachedOrRequest(final int i) {
        return getCached(i).switchIfEmpty(SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.inputevents.-$$Lambda$MemoryInputRepository$iVnMCKARyedh3BPlDPz-KhY0idY
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryInputRepository.this.lambda$getCachedOrRequest$4$MemoryInputRepository(i, result);
            }
        }).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.accessory.repositories.inputevents.-$$Lambda$MemoryInputRepository$0dhmiqU7v86-WponzTA6aqhq1mY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MemoryInputRepository.this.lambda$getCachedOrRequest$5$MemoryInputRepository(i, (Input.InputBehaviorConfigurationSet) obj);
            }
        }).toMaybe()).toFlowable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$createQueryPublishers$3(int i, DeviceInputConfiguration deviceInputConfiguration) throws Throwable {
        return deviceInputConfiguration.getDeviceId() == i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$removeConfigurationFromSet$7(Input.InputBehaviorConfiguration inputBehaviorConfiguration, Input.InputBehaviorConfiguration inputBehaviorConfiguration2) throws Throwable {
        return (inputBehaviorConfiguration2.getAction() == inputBehaviorConfiguration.getAction() && inputBehaviorConfiguration2.getSource() == inputBehaviorConfiguration.getSource()) ? false : true;
    }

    private static List<Input.InputBehaviorConfiguration> removeConfigurationFromSet(final Input.InputBehaviorConfiguration inputBehaviorConfiguration, Input.InputBehaviorConfigurationSet inputBehaviorConfigurationSet) {
        return (List) Observable.fromIterable(inputBehaviorConfigurationSet.getConfigurationsList()).filter(new Predicate() { // from class: com.amazon.alexa.accessory.repositories.inputevents.-$$Lambda$MemoryInputRepository$dV63nSG3pTp6LaNfheojl8QHbfo
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return MemoryInputRepository.lambda$removeConfigurationFromSet$7(Input.InputBehaviorConfiguration.this, (Input.InputBehaviorConfiguration) obj);
            }
        }).toList().blockingGet();
    }

    private static Input.InputBehaviorConfigurationSet replaceConfigurationInSet(Input.InputBehaviorConfigurationSet inputBehaviorConfigurationSet, Input.InputBehaviorConfiguration inputBehaviorConfiguration) {
        List<Input.InputBehaviorConfiguration> removeConfigurationFromSet = removeConfigurationFromSet(inputBehaviorConfiguration, inputBehaviorConfigurationSet);
        removeConfigurationFromSet.add(inputBehaviorConfiguration);
        return Input.InputBehaviorConfigurationSet.newBuilder().addAllConfigurations(removeConfigurationFromSet).mo10084build();
    }

    public /* synthetic */ MaybeSource lambda$getCached$6$MemoryInputRepository(int i) throws Throwable {
        Maybe empty;
        synchronized (this.configurations) {
            Input.InputBehaviorConfigurationSet inputBehaviorConfigurationSet = this.configurations.get(i);
            empty = inputBehaviorConfigurationSet == null ? Maybe.empty() : Maybe.just(inputBehaviorConfigurationSet);
        }
        return empty;
    }

    public /* synthetic */ void lambda$getCachedOrRequest$4$MemoryInputRepository(int i, Producer.Result result) {
        getHandler().handleGetInputConfiguration(i, result);
    }

    public /* synthetic */ void lambda$resetConfiguration$1$MemoryInputRepository(int i, Producer.Result result) {
        getHandler().handleResetInputConfiguration(i, result);
    }

    public /* synthetic */ void lambda$setConfiguration$0$MemoryInputRepository(int i, Input.InputBehaviorConfiguration inputBehaviorConfiguration, Producer.Result result) {
        getHandler().handleSetInputConfiguration(i, inputBehaviorConfiguration, result);
    }

    @Override // com.amazon.alexa.accessory.repositories.inputevents.InputProvider
    public void provideConfiguration(int i, Input.InputBehaviorConfiguration inputBehaviorConfiguration) {
        Preconditions.notNull(inputBehaviorConfiguration, PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY);
        synchronized (this.configurations) {
            Input.InputBehaviorConfigurationSet inputBehaviorConfigurationSet = this.configurations.get(i);
            if (inputBehaviorConfigurationSet == null) {
                lambda$resetConfiguration$2$MemoryInputRepository(i, Input.InputBehaviorConfigurationSet.newBuilder().addConfigurations(inputBehaviorConfiguration).mo10084build());
            } else {
                lambda$resetConfiguration$2$MemoryInputRepository(i, replaceConfigurationInSet(inputBehaviorConfigurationSet, inputBehaviorConfiguration));
            }
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.inputevents.InputRepository
    public Flowable<Input.InputBehaviorConfigurationSet> queryConfiguration(int i) {
        return Flowable.concatEager(createQueryPublishers(i)).onErrorResumeNext(ObservableUtils.errorIfNotReleased()).distinctUntilChanged();
    }

    public void release() {
        synchronized (this.configurations) {
            if (!this.configurationPublisher.hasComplete() && !this.configurationPublisher.hasThrowable()) {
                this.configurationPublisher.onError(new ObservableUtils.StreamReleasedException());
            }
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.inputevents.InputRepository
    public Completable resetConfiguration(final int i) {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.inputevents.-$$Lambda$MemoryInputRepository$4wygSFNqJl51fey_TuvFfaOuvVM
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryInputRepository.this.lambda$resetConfiguration$1$MemoryInputRepository(i, result);
            }
        }).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.accessory.repositories.inputevents.-$$Lambda$MemoryInputRepository$JDLTXT3YDQpDX659994nCIJuvQQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MemoryInputRepository.this.lambda$resetConfiguration$2$MemoryInputRepository(i, (Input.InputBehaviorConfigurationSet) obj);
            }
        }).ignoreElement();
    }

    @Override // com.amazon.alexa.accessory.repositories.inputevents.InputRepository
    public Completable setConfiguration(final int i, final Input.InputBehaviorConfiguration inputBehaviorConfiguration) {
        Preconditions.notNull(inputBehaviorConfiguration, PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY);
        return CompletableResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.inputevents.-$$Lambda$MemoryInputRepository$odhFG_nzba2hMaewpDFnO4lbQtc
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryInputRepository.this.lambda$setConfiguration$0$MemoryInputRepository(i, inputBehaviorConfiguration, result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.inputevents.InputProvider
    /* renamed from: provideConfiguration */
    public void lambda$resetConfiguration$2$MemoryInputRepository(int i, Input.InputBehaviorConfigurationSet inputBehaviorConfigurationSet) {
        Preconditions.notNull(inputBehaviorConfigurationSet, PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY);
        synchronized (this.configurations) {
            lambda$getCachedOrRequest$5$MemoryInputRepository(i, inputBehaviorConfigurationSet);
            this.configurationPublisher.onNext(new DeviceInputConfiguration(inputBehaviorConfigurationSet, i));
        }
    }
}
