package com.amazon.alexa.fitness.store;

import com.amazon.alexa.fitness.metrics.MetricsEnabled;
import com.amazon.alexa.fitness.network.NetworkNotifiable;
import kotlin.Metadata;
/* compiled from: DataStore.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H&¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/fitness/store/DataStore;", "Lcom/amazon/alexa/fitness/metrics/MetricsEnabled;", "Lcom/amazon/alexa/fitness/network/NetworkNotifiable;", "reset", "", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface DataStore extends MetricsEnabled, NetworkNotifiable {
    void reset();
}
