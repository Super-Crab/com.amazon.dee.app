package com.amazon.alexa.voice.tta.features;

import android.content.Context;
import com.amazon.alexa.feature.consumer.DefaultFeatureFlagConsumer;
import com.amazon.alexa.feature.consumer.api.FeatureFlagConsumer;
import com.amazon.alexa.feature.consumer.api.FeatureQuery;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FeaturesModule.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\bH\u0007¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/voice/tta/features/FeaturesModule;", "", "()V", "providesFeatureChecker", "Lcom/amazon/alexa/voice/tta/features/FeatureChecker;", "featureQuery", "Lcom/amazon/alexa/feature/consumer/api/FeatureQuery;", "providesFeatureFlagConsumer", "Lcom/amazon/alexa/feature/consumer/api/FeatureFlagConsumer;", "context", "Landroid/content/Context;", "providesFeatureQuery", "featureFlagConsumer", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
@Module
/* loaded from: classes11.dex */
public final class FeaturesModule {
    @Provides
    @Singleton
    @NotNull
    public final FeatureChecker providesFeatureChecker(@NotNull FeatureQuery featureQuery) {
        Intrinsics.checkParameterIsNotNull(featureQuery, "featureQuery");
        return new DefaultFeatureChecker(featureQuery);
    }

    @Provides
    @Singleton
    @NotNull
    public final FeatureFlagConsumer providesFeatureFlagConsumer(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return new DefaultFeatureFlagConsumer(context);
    }

    @Provides
    @Singleton
    @NotNull
    public final FeatureQuery providesFeatureQuery(@NotNull FeatureFlagConsumer featureFlagConsumer) {
        Intrinsics.checkParameterIsNotNull(featureFlagConsumer, "featureFlagConsumer");
        FeatureQuery featureQuery = featureFlagConsumer.getFeatureQuery();
        Intrinsics.checkExpressionValueIsNotNull(featureQuery, "featureFlagConsumer.featureQuery");
        return featureQuery;
    }
}
