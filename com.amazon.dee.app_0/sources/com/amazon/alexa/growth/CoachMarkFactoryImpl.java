package com.amazon.alexa.growth;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.growth.coachmark.CoachMark;
import com.amazon.alexa.growth.metrics.BIMetricsRecorder;
import com.amazon.alexa.growth.metrics.OEMetricsRecorder;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.latencyinfra.LatencyInfra;
import com.dee.app.http.CoralService;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public class CoachMarkFactoryImpl implements CoachMarkFactory {
    @VisibleForTesting
    final BIMetricsRecorder biMetricsRecorder;
    private final Context context;
    @VisibleForTesting
    final Provider<CoralService> coralServiceProvider;
    @VisibleForTesting
    final Provider<FeatureServiceV2> featureServiceProvider;
    @VisibleForTesting
    final OEMetricsRecorder oeMetricsRecorder;
    @VisibleForTesting
    final Provider<TaskManager> taskManagerProvider;

    public CoachMarkFactoryImpl(@NonNull final ComponentGetter componentGetter, @NonNull Context context) {
        this.context = context;
        this.oeMetricsRecorder = new OEMetricsRecorder(new Provider() { // from class: com.amazon.alexa.growth.-$$Lambda$CoachMarkFactoryImpl$b1NSuFIFdPYx7yBvDeQPJ3NmxYY
            @Override // javax.inject.Provider
            /* renamed from: get */
            public final Object mo10268get() {
                return CoachMarkFactoryImpl.lambda$new$0(ComponentGetter.this);
            }
        });
        this.biMetricsRecorder = new BIMetricsRecorder(new Provider() { // from class: com.amazon.alexa.growth.-$$Lambda$CoachMarkFactoryImpl$8bYKsvPDhRA1vKm1gbK4gdVnuYg
            @Override // javax.inject.Provider
            /* renamed from: get */
            public final Object mo10268get() {
                return CoachMarkFactoryImpl.lambda$new$1(ComponentGetter.this);
            }
        });
        this.taskManagerProvider = new Provider() { // from class: com.amazon.alexa.growth.-$$Lambda$CoachMarkFactoryImpl$vpdJOkgyqVmp1CE9yvujV3LzByY
            @Override // javax.inject.Provider
            /* renamed from: get */
            public final Object mo10268get() {
                return CoachMarkFactoryImpl.lambda$new$2(ComponentGetter.this);
            }
        };
        this.featureServiceProvider = new Provider() { // from class: com.amazon.alexa.growth.-$$Lambda$CoachMarkFactoryImpl$OtQuyXAOb0hqQGs8tk0bn1dj4t8
            @Override // javax.inject.Provider
            /* renamed from: get */
            public final Object mo10268get() {
                return CoachMarkFactoryImpl.lambda$new$3(ComponentGetter.this);
            }
        };
        this.coralServiceProvider = new Provider() { // from class: com.amazon.alexa.growth.-$$Lambda$CoachMarkFactoryImpl$qFjMLTZKQUfhyYqBgHJZyjYGFpw
            @Override // javax.inject.Provider
            /* renamed from: get */
            public final Object mo10268get() {
                return CoachMarkFactoryImpl.lambda$new$4(ComponentGetter.this);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ LatencyInfra lambda$new$0(ComponentGetter componentGetter) {
        return (LatencyInfra) componentGetter.get(LatencyInfra.class).mo10268get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Mobilytics lambda$new$1(ComponentGetter componentGetter) {
        return (Mobilytics) componentGetter.get(Mobilytics.class).mo10268get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ TaskManager lambda$new$2(ComponentGetter componentGetter) {
        return (TaskManager) componentGetter.get(TaskManager.class).mo10268get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ FeatureServiceV2 lambda$new$3(ComponentGetter componentGetter) {
        return (FeatureServiceV2) componentGetter.get(FeatureServiceV2.class).mo10268get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CoralService lambda$new$4(ComponentGetter componentGetter) {
        return (CoralService) componentGetter.get(CoralService.class).mo10268get();
    }

    @Override // com.amazon.alexa.growth.CoachMarkFactory
    public CoachMark getCoachMark(@NonNull View view) {
        return getCoachMark(view, null, null, CoachMark.ORIENTATION.VERTICAL);
    }

    @Override // com.amazon.alexa.growth.CoachMarkFactory
    public CoachMark getCoachMark(@NonNull View view, @Nullable String str) {
        return getCoachMark(view, str, null, CoachMark.ORIENTATION.VERTICAL);
    }

    @Override // com.amazon.alexa.growth.CoachMarkFactory
    public CoachMark getCoachMark(@NonNull View view, @Nullable String str, @NonNull CoachMark.ORIENTATION orientation) {
        return getCoachMark(view, str, null, orientation);
    }

    @Override // com.amazon.alexa.growth.CoachMarkFactory
    public CoachMark getCoachMark(@NonNull View view, @Nullable String str, @Nullable ViewGroup viewGroup) {
        return getCoachMark(view, str, viewGroup, CoachMark.ORIENTATION.VERTICAL);
    }

    @Override // com.amazon.alexa.growth.CoachMarkFactory
    public CoachMark getCoachMark(@NonNull View view, @Nullable String str, @Nullable ViewGroup viewGroup, @NonNull CoachMark.ORIENTATION orientation) {
        return new CoachMark(view, str, viewGroup, orientation, this.oeMetricsRecorder, this.biMetricsRecorder, this.taskManagerProvider.mo10268get(), this.featureServiceProvider.mo10268get(), this.coralServiceProvider.mo10268get());
    }
}
