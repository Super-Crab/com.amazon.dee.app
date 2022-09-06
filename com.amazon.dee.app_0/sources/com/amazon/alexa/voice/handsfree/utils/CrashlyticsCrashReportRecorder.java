package com.amazon.alexa.voice.handsfree.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public class CrashlyticsCrashReportRecorder implements CrashReportRecorder {
    private static final String TAG = "CrashlyticsCrashReportRecorder";
    private static final String VOICE_APP = "Voice app";
    private static final String VOICE_APP_VERSION = "Voice app version";
    private final AMPDInformationProvider mAMPDInformationProvider;
    private final ComponentRegistry mComponentRegistry;
    private final Provider<CrashMetadata> mCrashMetadata;
    private final MetricsBuilderProvider mMetricsBuilderProvider;

    public CrashlyticsCrashReportRecorder(Context context) {
        this(ComponentRegistry.getInstance(), MetricsBuilderProvider.getInstance(context), ComponentRegistry.getInstance().getLazy(CrashMetadata.class), AMPDInformationProvider.getInstance(context));
    }

    @Nullable
    @VisibleForTesting
    CrashReporter getCrashReporter() {
        return (CrashReporter) this.mComponentRegistry.get(CrashReporter.class).orNull();
    }

    @Override // com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder
    public void reportNonFatalCrash(@NonNull Context context, @NonNull String str, @NonNull Throwable th) {
        this.mCrashMetadata.mo10268get().put(VOICE_APP, this.mAMPDInformationProvider.getVoiceAppPackageNameString());
        this.mCrashMetadata.mo10268get().put(VOICE_APP_VERSION, this.mAMPDInformationProvider.getVoiceAppVersionString());
        CrashReporter crashReporter = getCrashReporter();
        if (crashReporter == null) {
            Log.e(TAG, "CrashReporter is not available. Will not report the crash.");
        } else {
            crashReporter.reportNonFatal(th);
        }
        this.mMetricsBuilderProvider.newBuilder().withNonFatalErrorEventMetric(TAG, str).emit(context);
    }

    @VisibleForTesting
    CrashlyticsCrashReportRecorder(@NonNull ComponentRegistry componentRegistry, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull Provider<CrashMetadata> provider, @NonNull AMPDInformationProvider aMPDInformationProvider) {
        this.mComponentRegistry = componentRegistry;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mCrashMetadata = provider;
        this.mAMPDInformationProvider = aMPDInformationProvider;
    }
}
