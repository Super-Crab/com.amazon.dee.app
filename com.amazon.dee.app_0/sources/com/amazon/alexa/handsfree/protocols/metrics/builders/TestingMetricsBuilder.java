package com.amazon.alexa.handsfree.protocols.metrics.builders;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsRecordMode;
import com.amazon.alexa.handsfree.protocols.metrics.PermissionResult;
import com.amazon.alexa.handsfree.protocols.metrics.factories.HandsFreeSetupMetricData;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
/* loaded from: classes8.dex */
public class TestingMetricsBuilder implements MetricsBuilder {
    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public void emit(@NonNull Context context) {
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public void emit(@NonNull MetricsRecordMode metricsRecordMode, @NonNull Context context) {
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>, S extends Enum<S>> MetricsBuilder withClickMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull S s) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>, S extends Enum<S>> MetricsBuilder withClickMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull S s, @NonNull EnrollmentType enrollmentType) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withDspApkUpdateMetric(@NonNull String str) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withEnrollmentDetectionEventMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, long j) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withFeatureGateDialedDownMetric(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withFirstStartupMetric(@NonNull String str) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withFtueDlsLanguageSelectorLocaleSelected(@NonNull String str, @NonNull String str2) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withFtueLanguageSelectorLocaleSelected(@NonNull String str, @NonNull String str2) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withHandsFreeSetupMetric(@NonNull String str, @NonNull HandsFreeSetupMetricData handsFreeSetupMetricData) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withHandsFreeSetupMetric(@NonNull String str, @NonNull EnrollmentType enrollmentType, @NonNull HandsFreeSetupMetricData handsFreeSetupMetricData) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withLatencyMetric(long j, long j2, @NonNull String str) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withLatencyMetric(@NonNull String str, @NonNull String str2, long j) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withNonFatalErrorEventMetric(@NonNull String str, @NonNull String str2) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>, S extends Enum<S>> MetricsBuilder withNotificationClickMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull S s, @NonNull String str2, @NonNull String str3) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <P extends Enum<P>> MetricsBuilder withNotificationDismissMetric(@NonNull String str, @NonNull P p, @NonNull String str2, @NonNull String str3) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>> MetricsBuilder withNotificationEventMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull String str2, @NonNull String str3) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>, S extends Enum<S>> MetricsBuilder withPageViewMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull S s) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>, S extends Enum<S>> MetricsBuilder withPageViewMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull S s, @NonNull EnrollmentType enrollmentType) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withPercentileMetricFailure(@NonNull String str, @NonNull String str2) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withPercentileMetricSuccess(@NonNull String str, @NonNull String str2) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withPerformanceMetric(@NonNull String str, @NonNull String str2) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withPerformanceMetricFailure(@NonNull String str, @NonNull String str2, int i) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public <P extends Enum<P>> MetricsBuilder withPermissionResultMetric(@NonNull String str, @NonNull String str2, @NonNull P p, @NonNull String str3, @NonNull PermissionResult permissionResult, @NonNull EnrollmentType enrollmentType) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withRouteInvoked(@NonNull String str, @NonNull String str2) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withRouteInvokedException(@NonNull String str, @NonNull String str2) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withSVClassificationMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, @NonNull String str6, @NonNull String str7) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withSdkUpdateMetric(@NonNull String str) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withSpeakerVerificationModelDownloadMetric(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withSpeakerVerificationProfileGenerationMetric(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withSupportedLocaleChangeMetric(@NonNull String str, @NonNull String str2) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withSupportedLocaleInUseAtStartUpMetric(@NonNull String str, @NonNull String str2) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withUnsupportedLocaleInUseMetric(@NonNull String str, @NonNull String str2) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>> MetricsBuilder withUtteranceMetric(@NonNull String str, @NonNull C c) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <T extends Enum<T>> MetricsBuilder withVendorEventMetric(@NonNull String str, @NonNull T t) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>> MetricsBuilder withVoiceMetadataMetric(@NonNull String str, @NonNull C c, @NonNull P p, double d) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>> MetricsBuilder withVoiceMetadataMetric(@NonNull String str, @NonNull C c, @NonNull P p, int i) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>> MetricsBuilder withVoiceMetadataMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull String str2) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>> MetricsBuilder withVoiceMetadataMetric(@NonNull String str, @NonNull C c, @NonNull P p, boolean z) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public <P extends Enum<P>, S extends Enum<S>, E extends Enum<E>, N extends Enum<N>> MetricsBuilder withVoiceTrainingMetric(@NonNull String str, @NonNull P p, @NonNull S s, @NonNull E e, @NonNull N n) {
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withWakeWordInvocationMetric(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        return this;
    }
}
