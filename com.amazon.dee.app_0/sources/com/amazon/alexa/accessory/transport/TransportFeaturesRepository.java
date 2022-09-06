package com.amazon.alexa.accessory.transport;

import io.reactivex.rxjava3.core.Single;
import java.util.Set;
/* loaded from: classes6.dex */
public interface TransportFeaturesRepository {
    Single<Set<TransportFeature>> queryTransportFeatures();
}
