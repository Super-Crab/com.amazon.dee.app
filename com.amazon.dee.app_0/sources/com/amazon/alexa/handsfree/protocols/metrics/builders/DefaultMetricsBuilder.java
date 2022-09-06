package com.amazon.alexa.handsfree.protocols.metrics.builders;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsConfiguration;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsEmitter;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsRecordMode;
import com.amazon.alexa.handsfree.protocols.metrics.PermissionResult;
import com.amazon.alexa.handsfree.protocols.metrics.factories.HandsFreeSetupMetricData;
import com.amazon.alexa.handsfree.protocols.metrics.factories.MetricFactoryProvider;
import com.amazon.alexa.handsfree.protocols.metrics.factories.VoiceMetadataMetricFactory;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class DefaultMetricsBuilder implements MetricsBuilder {
    private static final String PARAMETER_KEY_FORMAT = "%s_%s";
    private boolean hasNotificationMetric;
    private final MetricFactoryProvider mMetricFactoryProvider;
    private final List<Metric> mMetrics = new ArrayList();
    private final MetricsEmitter mMetricsEmitter;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultMetricsBuilder(@NonNull MetricsConfiguration metricsConfiguration) {
        this.mMetricFactoryProvider = metricsConfiguration.getMetricFactoryProvider();
        this.mMetricsEmitter = metricsConfiguration.getMetricsEmitter();
    }

    private <C extends Enum<C>, P extends Enum<P>> String buildParameterKey(@NonNull C c, @NonNull P p) {
        return String.format(PARAMETER_KEY_FORMAT, p.name(), c.name());
    }

    @NonNull
    private <C extends Enum<C>, P extends Enum<P>> Metric getVoiceMetadataMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull VoiceMetadataMetricFactory.ParameterType parameterType, @NonNull String str2) {
        return this.mMetricFactoryProvider.getVoiceMetadataMetricFactory().buildVoiceMetadataMetric(str, buildParameterKey(c, p), parameterType, str2);
    }

    @VisibleForTesting
    void addNotificationMetric(@NonNull String str, @NonNull String str2) {
        if (!this.hasNotificationMetric) {
            this.hasNotificationMetric = true;
            this.mMetrics.add(this.mMetricFactoryProvider.getNotificationMetricFactory().buildNotificationMetadataMetric(str, str2));
        }
    }

    @VisibleForTesting
    Metric[] build() {
        return (Metric[]) this.mMetrics.toArray(new Metric[0]);
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public void emit(@NonNull Context context) {
        this.mMetricsEmitter.recordMetrics(context, this, build());
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>, S extends Enum<S>> MetricsBuilder withClickMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull S s) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildClickMetric(str, c.name(), p.name(), s.name()));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withDspApkUpdateMetric(@NonNull String str) {
        this.mMetrics.add(this.mMetricFactoryProvider.getOperationalMetricFactory().buildDspApkUpdateMetric(str));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withEnrollmentDetectionEventMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, long j) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildEnrollmentDetectionEventMetric(str, str2, str3, str4, j));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withFeatureGateDialedDownMetric(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        this.mMetrics.add(this.mMetricFactoryProvider.getOperationalMetricFactory().buildFeatureGateDownMetric(str, str2, str3));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withFirstStartupMetric(@NonNull String str) {
        this.mMetrics.add(this.mMetricFactoryProvider.getOperationalMetricFactory().buildFirstStartupMetric(str));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withFtueDlsLanguageSelectorLocaleSelected(@NonNull String str, @NonNull String str2) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildFtueDlsLanguageSelectorLocaleSelected(str, str2));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withFtueLanguageSelectorLocaleSelected(@NonNull String str, @NonNull String str2) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildFtueLanguageSelectorLocaleSelected(str, str2));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withHandsFreeSetupMetric(@NonNull String str, @NonNull HandsFreeSetupMetricData handsFreeSetupMetricData) {
        this.mMetrics.add(this.mMetricFactoryProvider.getHandsFreeSetupMetricsFactory().buildHandsFreeSetupMetric(str, handsFreeSetupMetricData));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withLatencyMetric(@NonNull String str, @NonNull String str2, long j) {
        this.mMetrics.add(this.mMetricFactoryProvider.getPerformanceMetricFactory().buildLatencyMetric(str, str2, j));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withNonFatalErrorEventMetric(@NonNull String str, @NonNull String str2) {
        this.mMetrics.add(this.mMetricFactoryProvider.getErrorEventMetricFactory().buildNonFatalErrorMetric(str, str2));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>, S extends Enum<S>> MetricsBuilder withNotificationClickMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull S s, @NonNull String str2, @NonNull String str3) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildClickMetric(str, c.name(), p.name(), s.name()));
        addNotificationMetric(str2, str3);
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <P extends Enum<P>> MetricsBuilder withNotificationDismissMetric(@NonNull String str, @NonNull P p, @NonNull String str2, @NonNull String str3) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildDismissMetric(str, p.name()));
        addNotificationMetric(str2, str3);
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>> MetricsBuilder withNotificationEventMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull String str2, @NonNull String str3) {
        this.mMetrics.add(this.mMetricFactoryProvider.getNotificationMetricFactory().buildNotificationMetric(str, c.name(), p.name()));
        addNotificationMetric(str2, str3);
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>, S extends Enum<S>> MetricsBuilder withPageViewMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull S s) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildPageViewMetric(str, c.name(), p.name(), s.name()));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withPercentileMetricFailure(@NonNull String str, @NonNull String str2) {
        this.mMetrics.add(this.mMetricFactoryProvider.getPerformanceMetricFactory().buildPercentileMetricFailure(str, str2));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withPercentileMetricSuccess(@NonNull String str, @NonNull String str2) {
        this.mMetrics.add(this.mMetricFactoryProvider.getPerformanceMetricFactory().buildPercentileMetricSuccess(str, str2));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withPerformanceMetric(@NonNull String str, @NonNull String str2) {
        this.mMetrics.add(this.mMetricFactoryProvider.getPerformanceMetricFactory().buildPerformanceMetric(str, str2));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withPerformanceMetricFailure(@NonNull String str, @NonNull String str2, int i) {
        this.mMetrics.add(this.mMetricFactoryProvider.getPerformanceMetricFactory().buildPerformanceMetricFailure(str, str2, i));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public <P extends Enum<P>> MetricsBuilder withPermissionResultMetric(@NonNull String str, @NonNull String str2, @NonNull P p, @NonNull String str3, @NonNull PermissionResult permissionResult, @NonNull EnrollmentType enrollmentType) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildPermissionResultMetric(str, str2, p.name(), str3, permissionResult.name(), enrollmentType.name()));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withRouteInvoked(@NonNull String str, @NonNull String str2) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildRouteInvoked(str, str2));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withRouteInvokedException(@NonNull String str, @NonNull String str2) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildRouteInvokedException(str, str2));
        return null;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withSVClassificationMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, @NonNull String str6, @NonNull String str7) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildSVClassificationMetric(str, str2, str3, str4, str5, str6, str7));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withSdkUpdateMetric(@NonNull String str) {
        this.mMetrics.add(this.mMetricFactoryProvider.getOperationalMetricFactory().buildSdkUpdateMetric(str));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withSpeakerVerificationModelDownloadMetric(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildSpeakerVerificationModelDownloadMetric(str, str2, str3));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withSpeakerVerificationProfileGenerationMetric(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildSpeakerVerificationProfileGenerationMetric(str, str2, str3));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withSupportedLocaleChangeMetric(@NonNull String str, @NonNull String str2) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildSupportedLocaleChangeMetric(str, str2));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withSupportedLocaleInUseAtStartUpMetric(@NonNull String str, @NonNull String str2) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildSupportedLocaleInUseAtStartUpMetric(str, str2));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public MetricsBuilder withUnsupportedLocaleInUseMetric(@NonNull String str, @NonNull String str2) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildUnsupportedLocaleInUseMetric(str, str2));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>> MetricsBuilder withUtteranceMetric(@NonNull String str, @NonNull C c) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildUtteranceMetric(str, c.name()));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <T extends Enum<T>> MetricsBuilder withVendorEventMetric(@NonNull String str, @NonNull T t) {
        this.mMetrics.add(this.mMetricFactoryProvider.getVendorEventMetricFactory().newVendorEventMetric(str, t.name()));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>> MetricsBuilder withVoiceMetadataMetric(@NonNull String str, @NonNull C c, @NonNull P p, int i) {
        this.mMetrics.add(getVoiceMetadataMetric(str, c, p, VoiceMetadataMetricFactory.ParameterType.INT, String.valueOf(i)));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public <P extends Enum<P>, S extends Enum<S>, E extends Enum<E>, N extends Enum<N>> MetricsBuilder withVoiceTrainingMetric(@NonNull String str, @NonNull P p, @NonNull S s, @NonNull E e, @NonNull N n) {
        this.mMetrics.add(this.mMetricFactoryProvider.getVoiceTrainingMetricsFactory().buildVoiceTrainingMetric(str, p.name(), s.name(), e.name(), n.name()));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withWakeWordInvocationMetric(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildWakeWordInvocationMetric(str, str2, str3));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    public void emit(@NonNull MetricsRecordMode metricsRecordMode, @NonNull Context context) {
        this.mMetricsEmitter.recordMetrics(metricsRecordMode, context, this, build());
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>, S extends Enum<S>> MetricsBuilder withClickMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull S s, @NonNull EnrollmentType enrollmentType) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildClickMetric(str, c.name(), p.name(), s.name(), enrollmentType.name()));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withLatencyMetric(long j, long j2, @NonNull String str) {
        this.mMetrics.add(this.mMetricFactoryProvider.getOperationalMetricFactory().buildLatencyMetric(j, j2, str));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>> MetricsBuilder withVoiceMetadataMetric(@NonNull String str, @NonNull C c, @NonNull P p, double d) {
        this.mMetrics.add(getVoiceMetadataMetric(str, c, p, VoiceMetadataMetricFactory.ParameterType.DOUBLE, String.valueOf(d)));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public MetricsBuilder withHandsFreeSetupMetric(@NonNull String str, @NonNull EnrollmentType enrollmentType, @NonNull HandsFreeSetupMetricData handsFreeSetupMetricData) {
        this.mMetrics.add(this.mMetricFactoryProvider.getHandsFreeSetupMetricsFactory().buildHandsFreeSetupMetric(str, enrollmentType.name(), handsFreeSetupMetricData));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>, S extends Enum<S>> MetricsBuilder withPageViewMetric(@NonNull String str, @NonNull C c, @NonNull P p, @NonNull S s, @NonNull EnrollmentType enrollmentType) {
        this.mMetrics.add(this.mMetricFactoryProvider.getUserActionMetricFactory().buildPageViewMetric(str, c.name(), p.name(), s.name(), enrollmentType.name()));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>> MetricsBuilder withVoiceMetadataMetric(@NonNull String str, @NonNull C c, @NonNull P p, boolean z) {
        this.mMetrics.add(getVoiceMetadataMetric(str, c, p, VoiceMetadataMetricFactory.ParameterType.BOOLEAN, String.valueOf(z)));
        return this;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder
    @NonNull
    public <C extends Enum<C>, P extends Enum<P>> MetricsBuilder withVoiceMetadataMetric(@NonNull String str, @NonNull C c, @NonNull P p, String str2) {
        this.mMetrics.add(getVoiceMetadataMetric(str, c, p, VoiceMetadataMetricFactory.ParameterType.STRING, String.valueOf(str2)));
        return this;
    }
}
