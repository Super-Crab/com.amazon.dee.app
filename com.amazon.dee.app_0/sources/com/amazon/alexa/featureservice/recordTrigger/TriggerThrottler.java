package com.amazon.alexa.featureservice.recordTrigger;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes7.dex */
public class TriggerThrottler {
    @VisibleForTesting
    ConcurrentMap<String, RequestTreatment> incomingRequests;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TriggerThrottler() {
        this(new ConcurrentHashMap());
    }

    public synchronized RequestTreatment add(@NonNull RequestTreatment requestTreatment) {
        return this.incomingRequests.putIfAbsent(requestTreatment.getFeatureName(), requestTreatment);
    }

    public synchronized boolean isEmpty() {
        return this.incomingRequests.isEmpty();
    }

    @NonNull
    public synchronized List<RequestTreatment> removeAll() {
        ArrayList arrayList;
        arrayList = new ArrayList(this.incomingRequests.values());
        this.incomingRequests.clear();
        return arrayList;
    }

    @VisibleForTesting
    TriggerThrottler(@NonNull ConcurrentMap<String, RequestTreatment> concurrentMap) {
        this.incomingRequests = concurrentMap;
    }
}
