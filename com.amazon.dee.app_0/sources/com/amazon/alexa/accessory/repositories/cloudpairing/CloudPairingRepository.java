package com.amazon.alexa.accessory.repositories.cloudpairing;

import com.amazon.alexa.accessory.protocol.Cloudpairing;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface CloudPairingRepository {
    Single<Cloudpairing.CloudPairingAttributes> getCloudPairingAttributes();

    Single<Cloudpairing.CloudPairingStatus> getCloudPairingStatus(Cloudpairing.Seed seed);

    Completable removeCloudPairingKeys(Cloudpairing.Seed seed);

    Completable replaceCloudPairingKeys(Cloudpairing.Seed seed, Cloudpairing.CloudPairingKeys cloudPairingKeys);

    Completable setCloudPairingKeys(Cloudpairing.CloudPairingKeys cloudPairingKeys);
}
