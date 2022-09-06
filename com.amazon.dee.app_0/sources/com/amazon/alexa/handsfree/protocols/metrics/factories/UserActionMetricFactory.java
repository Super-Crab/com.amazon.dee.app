package com.amazon.alexa.handsfree.protocols.metrics.factories;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
/* loaded from: classes8.dex */
public interface UserActionMetricFactory {
    @NonNull
    Metric buildClickMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4);

    @NonNull
    Metric buildClickMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5);

    @NonNull
    Metric buildDismissMetric(@NonNull String str, @NonNull String str2);

    @NonNull
    Metric buildEnrollmentDetectionEventMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, long j);

    @NonNull
    Metric buildFtueDlsLanguageSelectorLocaleSelected(@NonNull String str, @NonNull String str2);

    Metric buildFtueLanguageSelectorLocaleSelected(@NonNull String str, @NonNull String str2);

    @NonNull
    Metric buildPageViewMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4);

    @NonNull
    Metric buildPageViewMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5);

    @NonNull
    Metric buildPermissionResultMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, @NonNull String str6);

    @NonNull
    Metric buildRouteInvoked(@NonNull String str, @NonNull String str2);

    @NonNull
    Metric buildRouteInvokedException(@NonNull String str, @NonNull String str2);

    @NonNull
    Metric buildSVClassificationMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, @NonNull String str6, @NonNull String str7);

    @NonNull
    Metric buildSpeakerVerificationModelDownloadMetric(@NonNull String str, @NonNull String str2, @NonNull String str3);

    @NonNull
    Metric buildSpeakerVerificationProfileGenerationMetric(@NonNull String str, @NonNull String str2, @NonNull String str3);

    @NonNull
    Metric buildSupportedLocaleChangeMetric(@NonNull String str, @NonNull String str2);

    @NonNull
    Metric buildSupportedLocaleInUseAtStartUpMetric(@NonNull String str, @NonNull String str2);

    @NonNull
    Metric buildUnsupportedLocaleInUseMetric(@NonNull String str, @NonNull String str2);

    @NonNull
    Metric buildUtteranceMetric(@NonNull String str, @NonNull String str2);

    @NonNull
    Metric buildWakeWordInvocationMetric(@NonNull String str, @NonNull String str2, @NonNull String str3);
}
