package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.feature.consumer.api.FeatureFlagConsumer;
import com.amazon.alexa.feature.consumer.api.FeatureFlagListener;
import com.amazon.alexa.feature.consumer.api.FeatureQuery;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;
/* compiled from: FeatureFlagConfigurationAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class gSO {
    public static final Set<String> BIo = new HashSet();
    public static final String zZm = "gSO";
    public final ExecutorService JTe;
    public final TimeProvider Qle;
    public final Provider<AlexaClientEventBus> jiA;
    public final FeatureFlagConsumer zQM;
    public final FeatureQuery zyO;

    static {
        for (Feature feature : Feature.values()) {
            BIo.add(feature.name());
        }
    }

    @Inject
    public gSO(FeatureFlagConsumer featureFlagConsumer, FeatureQuery featureQuery, TimeProvider timeProvider, Provider<AlexaClientEventBus> provider, @Named("shared_scheduler") ScheduledExecutorService scheduledExecutorService) {
        this.zQM = featureFlagConsumer;
        this.zyO = featureQuery;
        this.JTe = scheduledExecutorService;
        this.jiA = provider;
        this.Qle = timeProvider;
    }

    public boolean zZm(Feature feature) {
        return this.zyO.isActive(feature.name());
    }

    public void zZm(Feature feature, FeatureFlagListener featureFlagListener) {
        this.zQM.enqueueFeatureFlagListener(feature.name(), featureFlagListener);
    }

    public void zZm() {
        ManagedExecutorFactory.shutdown("feature-flag-executor");
        this.zQM.teardown();
    }
}
