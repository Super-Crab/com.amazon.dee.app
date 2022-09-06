package com.amazon.alexa.fitness.service;

import android.content.Context;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.data.registry.NativeFeatureRegistry;
import com.amazon.alexa.fitness.api.afx.FeatureService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FeatureServiceImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\b\u0010\f\u001a\u00020\bH\u0016J\b\u0010\r\u001a\u00020\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/fitness/service/FeatureServiceImpl;", "Lcom/amazon/alexa/fitness/api/afx/FeatureService;", "componentRegistry", "Lcom/amazon/alexa/protocols/service/api/ComponentRegistry;", "featureService", "Lcom/amazon/alexa/featureservice/api/FeatureServiceV2;", "(Lcom/amazon/alexa/protocols/service/api/ComponentRegistry;Lcom/amazon/alexa/featureservice/api/FeatureServiceV2;)V", "isFitnessEnabled", "", "isFitnessInitEnabled", "isHybridDistanceEnabled", "isLocationTrackingEnabled", "isMapViewEnabled", "isWorkoutHistoryTabEnabled", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FeatureServiceImpl implements FeatureService {
    private final FeatureServiceV2 featureService;

    @Inject
    public FeatureServiceImpl(@NotNull ComponentRegistry componentRegistry, @NotNull FeatureServiceV2 featureService) {
        Intrinsics.checkParameterIsNotNull(componentRegistry, "componentRegistry");
        Intrinsics.checkParameterIsNotNull(featureService, "featureService");
        this.featureService = featureService;
        componentRegistry.bindConcreteFactory(FeatureService.class, new ComponentRegistry.ConcreteComponentFactory<FeatureService>() { // from class: com.amazon.alexa.fitness.service.FeatureServiceImpl.1
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            @NotNull
            /* renamed from: create */
            public final FeatureService create2(Context context) {
                return FeatureServiceImpl.this;
            }
        });
    }

    @Override // com.amazon.alexa.fitness.api.afx.FeatureService
    public boolean isFitnessEnabled() {
        return this.featureService.hasAccess(NativeFeatureRegistry.TEMPO_REMOTE_SPEECHLET, false);
    }

    @Override // com.amazon.alexa.fitness.api.afx.FeatureService
    public boolean isFitnessInitEnabled() {
        return this.featureService.hasAccess(NativeFeatureRegistry.ALEXA_FITNESS_INITIALIZATION, false);
    }

    @Override // com.amazon.alexa.fitness.api.afx.FeatureService
    public boolean isHybridDistanceEnabled() {
        return this.featureService.hasAccess(NativeFeatureRegistry.ALEXA_FITNESS_HYBRID_DISTANCE_DATASOURCE2, false);
    }

    @Override // com.amazon.alexa.fitness.api.afx.FeatureService
    public boolean isLocationTrackingEnabled() {
        return this.featureService.hasAccess(NativeFeatureRegistry.ALEXA_FITNESS_LOCATION_TRACKING, false);
    }

    @Override // com.amazon.alexa.fitness.api.afx.FeatureService
    public boolean isMapViewEnabled() {
        return this.featureService.hasAccess(NativeFeatureRegistry.ALEXA_FITNESS_MAPS, false);
    }

    @Override // com.amazon.alexa.fitness.api.afx.FeatureService
    public boolean isWorkoutHistoryTabEnabled() {
        return this.featureService.hasAccess("ALEXA_FITNESS_WORKOUT_HISTORY_ANDROID", false);
    }
}
