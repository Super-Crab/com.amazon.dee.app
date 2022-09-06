package com.amazon.alexa.handsfree.uservoicerecognition.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.metrics.VoiceTrainingMetricMetadata;
import com.amazon.alexa.handsfree.settings.contract.SettingsSetupFlowContract;
import com.amazon.alexa.handsfree.ui.ManagedActivity;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.finalstep.EnrollmentCompleteFragment;
import com.amazon.alexa.mosaic.components.ThemeUtil;
/* loaded from: classes8.dex */
public class LockScreenSettingActivity extends ManagedActivity implements EnrollmentCompleteFragment.EnrollmentCompleteCallback {
    private static final String TAG = "LockScreenSettingActivity";
    private EnrollmentTypeResolver mEnrollmentTypeResolver;
    private MetricsBuilderProvider mMetricsBuilderProvider;

    public LockScreenSettingActivity() {
    }

    @VisibleForTesting
    void emitPageLoadMetric() {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        newBuilder.withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_FINISH, VoiceTrainingMetricMetadata.SubPageType.NONE, VoiceTrainingMetricMetadata.EventType.PAGE_LOAD, this.mEnrollmentTypeResolver.getEnrollmentType());
        newBuilder.emit(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.handsfree.ui.ManagedActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        InitializerProvider.getInitializer().initialize(this);
        setRequestedOrientation(1);
        ThemeUtil.setTheme(this);
        setContentView(R.layout.mosaic_activity_enrollment);
        this.mMetricsBuilderProvider = MetricsBuilderProvider.getInstance(this);
        this.mEnrollmentTypeResolver = ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(getApplicationContext(), FalcoProtocolComponent.class)).enrollmentTypeResolverLazy().mo358get();
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_enter, R.animator.exit_fade_out).replace(R.id.container, new EnrollmentCompleteFragment(getSharedPreferences(SettingsSetupFlowContract.PREFERENCE_FILE_NAME, 0).getBoolean(SettingsSetupFlowContract.AIS_FLOW_ONGOING, false))).commitAllowingStateLoss();
        emitPageLoadMetric();
        resetAisFlowFlag();
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.finalstep.EnrollmentCompleteFragment.EnrollmentCompleteCallback
    public void onLockScreenSettingFinished() {
        finishStep(ManagedActivity.StepResult.CONTINUE);
    }

    @VisibleForTesting
    void resetAisFlowFlag() {
        getSharedPreferences(SettingsSetupFlowContract.PREFERENCE_FILE_NAME, 0).edit().putBoolean(SettingsSetupFlowContract.AIS_FLOW_ONGOING, false).apply();
    }

    @VisibleForTesting
    LockScreenSettingActivity(@NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull EnrollmentTypeResolver enrollmentTypeResolver) {
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mEnrollmentTypeResolver = enrollmentTypeResolver;
    }
}
