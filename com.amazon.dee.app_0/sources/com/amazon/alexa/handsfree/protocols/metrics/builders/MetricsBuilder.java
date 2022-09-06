package com.amazon.alexa.handsfree.protocols.metrics.builders;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsRecordMode;
import com.amazon.alexa.handsfree.protocols.metrics.PermissionResult;
import com.amazon.alexa.handsfree.protocols.metrics.factories.HandsFreeSetupMetricData;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
/* loaded from: classes8.dex */
public interface MetricsBuilder {
    void emit(@NonNull Context context);

    void emit(@NonNull MetricsRecordMode metricsRecordMode, @NonNull Context context);

    @NonNull
    <C extends Enum<C>, P extends Enum<P>, S extends Enum<S>> MetricsBuilder withClickMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull S s);

    @NonNull
    <C extends Enum<C>, P extends Enum<P>, S extends Enum<S>> MetricsBuilder withClickMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull S s, @NonNull EnrollmentType enrollmentType);

    MetricsBuilder withDspApkUpdateMetric(@NonNull String str);

    MetricsBuilder withEnrollmentDetectionEventMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, long j);

    MetricsBuilder withFeatureGateDialedDownMetric(@NonNull String str, @NonNull String str2, @NonNull String str3);

    MetricsBuilder withFirstStartupMetric(@NonNull String str);

    MetricsBuilder withFtueDlsLanguageSelectorLocaleSelected(@NonNull String str, @NonNull String str2);

    MetricsBuilder withFtueLanguageSelectorLocaleSelected(@NonNull String str, @NonNull String str2);

    @NonNull
    MetricsBuilder withHandsFreeSetupMetric(@NonNull String str, @NonNull HandsFreeSetupMetricData handsFreeSetupMetricData);

    @NonNull
    MetricsBuilder withHandsFreeSetupMetric(@NonNull String str, @NonNull EnrollmentType enrollmentType, @NonNull HandsFreeSetupMetricData handsFreeSetupMetricData);

    @NonNull
    MetricsBuilder withLatencyMetric(long j, long j2, @NonNull String str);

    @NonNull
    MetricsBuilder withLatencyMetric(@NonNull String str, @NonNull String str2, long j);

    MetricsBuilder withNonFatalErrorEventMetric(@NonNull String str, @NonNull String str2);

    @NonNull
    <C extends Enum<C>, P extends Enum<P>, S extends Enum<S>> MetricsBuilder withNotificationClickMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull S s, @NonNull String str2, @NonNull String str3);

    @NonNull
    <P extends Enum<P>> MetricsBuilder withNotificationDismissMetric(@NonNull String str, @NonNull P p, @NonNull String str2, @NonNull String str3);

    @NonNull
    <C extends Enum<C>, P extends Enum<P>> MetricsBuilder withNotificationEventMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull String str2, @NonNull String str3);

    @NonNull
    <C extends Enum<C>, P extends Enum<P>, S extends Enum<S>> MetricsBuilder withPageViewMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull S s);

    @NonNull
    <C extends Enum<C>, P extends Enum<P>, S extends Enum<S>> MetricsBuilder withPageViewMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull S s, @NonNull EnrollmentType enrollmentType);

    @NonNull
    MetricsBuilder withPercentileMetricFailure(@NonNull String str, @NonNull String str2);

    @NonNull
    MetricsBuilder withPercentileMetricSuccess(@NonNull String str, @NonNull String str2);

    @NonNull
    MetricsBuilder withPerformanceMetric(@NonNull String str, @NonNull String str2);

    @NonNull
    MetricsBuilder withPerformanceMetricFailure(@NonNull String str, @NonNull String str2, int i);

    <P extends Enum<P>> MetricsBuilder withPermissionResultMetric(@NonNull String str, @NonNull String str2, @NonNull P p, @NonNull String str3, @NonNull PermissionResult permissionResult, @NonNull EnrollmentType enrollmentType);

    MetricsBuilder withRouteInvoked(@NonNull String str, @NonNull String str2);

    MetricsBuilder withRouteInvokedException(@NonNull String str, @NonNull String str2);

    @NonNull
    MetricsBuilder withSVClassificationMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, @NonNull String str6, @NonNull String str7);

    MetricsBuilder withSdkUpdateMetric(@NonNull String str);

    MetricsBuilder withSpeakerVerificationModelDownloadMetric(@NonNull String str, @NonNull String str2, @NonNull String str3);

    MetricsBuilder withSpeakerVerificationProfileGenerationMetric(@NonNull String str, @NonNull String str2, @NonNull String str3);

    MetricsBuilder withSupportedLocaleChangeMetric(@NonNull String str, @NonNull String str2);

    MetricsBuilder withSupportedLocaleInUseAtStartUpMetric(@NonNull String str, @NonNull String str2);

    MetricsBuilder withUnsupportedLocaleInUseMetric(@NonNull String str, @NonNull String str2);

    @NonNull
    <C extends Enum<C>> MetricsBuilder withUtteranceMetric(@NonNull String str, @NonNull C c);

    @NonNull
    <T extends Enum<T>> MetricsBuilder withVendorEventMetric(@NonNull String str, @NonNull T t);

    @NonNull
    <C extends Enum<C>, P extends Enum<P>> MetricsBuilder withVoiceMetadataMetric(@NonNull String str, @NonNull C c, @NonNull P p, double d);

    @NonNull
    <C extends Enum<C>, P extends Enum<P>> MetricsBuilder withVoiceMetadataMetric(@NonNull String str, @NonNull C c, @NonNull P p, int i);

    @NonNull
    <C extends Enum<C>, P extends Enum<P>> MetricsBuilder withVoiceMetadataMetric(@NonNull String str, @NonNull C c, @NonNull P p, String str2);

    @NonNull
    <C extends Enum<C>, P extends Enum<P>> MetricsBuilder withVoiceMetadataMetric(@NonNull String str, @NonNull C c, @NonNull P p, boolean z);

    <P extends Enum<P>, S extends Enum<S>, E extends Enum<E>, N extends Enum<N>> MetricsBuilder withVoiceTrainingMetric(@NonNull String str, @NonNull P p, @NonNull S s, @NonNull E e, @NonNull N n);

    @NonNull
    MetricsBuilder withWakeWordInvocationMetric(@NonNull String str, @NonNull String str2, @NonNull String str3);
}
