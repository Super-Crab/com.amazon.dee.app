package com.amazon.alexa.accessory.transport;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.smarthomecameras.constants.NetworkConstants;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import java.util.Set;
/* loaded from: classes6.dex */
public class MemoryTransportFeaturesRepository implements TransportFeaturesRepository, TransportFeaturesProvider {
    private final ReplaySubject<Set<TransportFeature>> transportFeatures = ReplaySubject.createWithSize(1);

    @Override // com.amazon.alexa.accessory.transport.TransportFeaturesProvider
    public void provideTransportFeatures(Set<TransportFeature> set) {
        Preconditions.notNull(set, NetworkConstants.FEATURES_KEY);
        if (this.transportFeatures.hasValue()) {
            Logger.e("Received another set of transport features, should never happen. Ignoring.");
        } else {
            this.transportFeatures.onNext(set);
        }
    }

    @Override // com.amazon.alexa.accessory.transport.TransportFeaturesRepository
    public Single<Set<TransportFeature>> queryTransportFeatures() {
        return this.transportFeatures.firstOrError();
    }
}
