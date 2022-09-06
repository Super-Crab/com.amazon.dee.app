package com.amazon.alexa.wakeword.speakerverification.encryption;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder;
import com.amazon.alexa.wakeword.speakerverification.errors.EncryptionFailure;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
/* loaded from: classes11.dex */
public class EncryptionMetricsReporter implements EncryptionMetricsListener {
    static final String DECRYPT = "DECRYPT";
    static final String ENCRYPT = "ENCRYPT";
    static final String SEPARATOR = ".";
    static final String SV_ENCRYPTION = "SV_ENCRYPTION";
    static final String TAG = "EncryptionMetricsReporter";
    private final Context mContext;
    private final Lazy<CrashReportRecorder> mCrashReportRecorderLazy;
    private final Lazy<MetricsBuilderProvider> mMetricsBuilderProviderLazy;

    public EncryptionMetricsReporter(@NonNull Context context) {
        this(context, ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).metricsBuilderProviderLazy(), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).crashReportRecorderLazy());
    }

    @VisibleForTesting
    String createFailureMetricName(@NonNull String str, @NonNull String str2) {
        return GeneratedOutlineSupport1.outline76("SV_ENCRYPTION.", str, ".", str2);
    }

    @VisibleForTesting
    String createMetricName(@NonNull String str) {
        return GeneratedOutlineSupport1.outline72("SV_ENCRYPTION.", str);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.encryption.EncryptionMetricsListener
    public void onEncryptionFailure(@NonNull EncryptionFailure encryptionFailure) {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricFailure(TAG, createMetricName(ENCRYPT)).withPerformanceMetric(TAG, createFailureMetricName(ENCRYPT, encryptionFailure.name())).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.encryption.EncryptionMetricsListener
    public void onEncryptionSuccess() {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricSuccess(TAG, createMetricName(ENCRYPT)).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.encryption.EncryptionMetricsListener
    public void onNonFatalError(@NonNull Exception exc, @NonNull String str) {
        this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(this.mContext, str, exc);
    }

    public EncryptionMetricsReporter(@NonNull Context context, @NonNull Lazy<MetricsBuilderProvider> lazy, @NonNull Lazy<CrashReportRecorder> lazy2) {
        this.mContext = context;
        this.mMetricsBuilderProviderLazy = lazy;
        this.mCrashReportRecorderLazy = lazy2;
    }
}
