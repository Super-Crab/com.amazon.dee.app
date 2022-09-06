package com.amazon.alexa.accessory.repositories.cloudpairing;

import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.internal.repositories.ResultCallable;
import com.amazon.alexa.accessory.internal.repositories.SingleResult;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Cloudpairing;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingProducer;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public class MemoryCloudPairingRepository extends BaseProducer<CloudPairingProducer.ActionHandler> implements CloudPairingRepository, CloudPairingProducer {
    @Override // com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingRepository
    public Single<Cloudpairing.CloudPairingAttributes> getCloudPairingAttributes() {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.cloudpairing.-$$Lambda$MemoryCloudPairingRepository$BfozD3PxlMNpXIacLDtTDz8LtpE
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryCloudPairingRepository.this.lambda$getCloudPairingAttributes$1$MemoryCloudPairingRepository(result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingRepository
    public Single<Cloudpairing.CloudPairingStatus> getCloudPairingStatus(final Cloudpairing.Seed seed) {
        Preconditions.notNull(seed, "seed");
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.cloudpairing.-$$Lambda$MemoryCloudPairingRepository$VyCHlL3TJFErVnGel0f4h3J_5eM
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryCloudPairingRepository.this.lambda$getCloudPairingStatus$0$MemoryCloudPairingRepository(seed, result);
            }
        });
    }

    public /* synthetic */ void lambda$getCloudPairingAttributes$1$MemoryCloudPairingRepository(Producer.Result result) {
        getHandler().handleGetCloudPairingAttributes(result);
    }

    public /* synthetic */ void lambda$getCloudPairingStatus$0$MemoryCloudPairingRepository(Cloudpairing.Seed seed, Producer.Result result) {
        getHandler().handleGetCloudPairingStatus(seed, result);
    }

    public /* synthetic */ void lambda$removeCloudPairingKeys$4$MemoryCloudPairingRepository(Cloudpairing.Seed seed, Producer.Result result) {
        getHandler().handleRemoveCloudPairingKeys(seed, result);
    }

    public /* synthetic */ void lambda$replaceCloudPairingKeys$3$MemoryCloudPairingRepository(Cloudpairing.Seed seed, Cloudpairing.CloudPairingKeys cloudPairingKeys, Producer.Result result) {
        getHandler().handleReplaceCloudPairingKeys(seed, cloudPairingKeys, result);
    }

    public /* synthetic */ void lambda$setCloudPairingKeys$2$MemoryCloudPairingRepository(Cloudpairing.CloudPairingKeys cloudPairingKeys, Producer.Result result) {
        getHandler().handleSetCloudPairingKeys(cloudPairingKeys, result);
    }

    @Override // com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingRepository
    public Completable removeCloudPairingKeys(final Cloudpairing.Seed seed) {
        Preconditions.notNull(seed, "seed");
        return CompletableResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.cloudpairing.-$$Lambda$MemoryCloudPairingRepository$eyRC-j6fY23MCl1_3hdZJ4JVN5g
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryCloudPairingRepository.this.lambda$removeCloudPairingKeys$4$MemoryCloudPairingRepository(seed, result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingRepository
    public Completable replaceCloudPairingKeys(final Cloudpairing.Seed seed, final Cloudpairing.CloudPairingKeys cloudPairingKeys) {
        Preconditions.notNull(seed, "currentSeed");
        Preconditions.notNull(cloudPairingKeys, QuickTimeAtomTypes.ATOM_KEYS);
        return CompletableResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.cloudpairing.-$$Lambda$MemoryCloudPairingRepository$uDb7XOQ1njWIUcx_5UhKnl9eIEI
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryCloudPairingRepository.this.lambda$replaceCloudPairingKeys$3$MemoryCloudPairingRepository(seed, cloudPairingKeys, result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingRepository
    public Completable setCloudPairingKeys(final Cloudpairing.CloudPairingKeys cloudPairingKeys) {
        Preconditions.notNull(cloudPairingKeys, QuickTimeAtomTypes.ATOM_KEYS);
        return CompletableResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.cloudpairing.-$$Lambda$MemoryCloudPairingRepository$_RRCQo0VS6iq_Gkz2Vk7IuypnYE
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryCloudPairingRepository.this.lambda$setCloudPairingKeys$2$MemoryCloudPairingRepository(cloudPairingKeys, result);
            }
        });
    }
}
