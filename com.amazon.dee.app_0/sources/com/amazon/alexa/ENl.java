package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.feature.consumer.DefaultFeatureFlagConsumer;
import com.amazon.alexa.feature.consumer.api.FeatureFlagConsumer;
import com.amazon.alexa.feature.consumer.api.FeatureQuery;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
/* compiled from: FeatureFlagModule.java */
@Module
/* loaded from: classes.dex */
public class ENl {
    @Provides
    @Singleton
    public FeatureFlagConsumer zZm(Context context) {
        return new DefaultFeatureFlagConsumer(context);
    }

    @Provides
    @Singleton
    public FeatureQuery zZm(FeatureFlagConsumer featureFlagConsumer) {
        return featureFlagConsumer.getFeatureQuery();
    }
}
