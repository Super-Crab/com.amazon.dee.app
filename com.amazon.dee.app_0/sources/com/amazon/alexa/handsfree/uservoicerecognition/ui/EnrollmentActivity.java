package com.amazon.alexa.handsfree.uservoicerecognition.ui;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import com.amazon.alexa.handsfree.notification.metrics.NotificationMetricReporter;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.metrics.VoiceTrainingMetricMetadata;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.ui.ManagedActivity;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.finalstep.EnrollmentCompleteFragment;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.GettingReadyFragment;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.privacy.EnrollmentPrivacyTermsActivity;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentFragment;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment;
import com.amazon.alexa.mosaic.components.ThemeUtil;
/* loaded from: classes8.dex */
public class EnrollmentActivity extends ManagedActivity implements StartEnrollmentFragment.StartEnrollmentFragmentCallback, EnrollmentPrimerFragment.EnrollmentPrimerFragmentCallback, StepsFragment.StepsFragmentCallback, EnrollmentCompleteFragment.EnrollmentCompleteCallback {
    public static final String EXTRA_SUPPRESS_UVR_ACTIVATION = "SUPPRESS_UVR_ACTIVATION";
    private static final String GETTING_READY_FRAGMENT_NAME = "GettingReadyFragment";
    private static final String TAG = EnrollmentActivity.class.getSimpleName();
    private static int sBackgroundColor;
    private VoiceTrainingMetricMetadata.PageType mCurrentPage;
    private EnrollmentType mEnrollmentType;
    private EnrollmentTypeResolver mEnrollmentTypeResolver;
    private Initializer mInitializer;
    private MetricsBuilderProvider mMetricsBuilderProvider;
    private NotificationManager mNotificationManager;
    private boolean mSuppressUVRActivation;
    private UVRConnector mUVRConnector;

    /* renamed from: com.amazon.alexa.handsfree.uservoicerecognition.ui.EnrollmentActivity$2  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$handsfree$protocols$uservoicerecognition$metrics$VoiceTrainingMetricMetadata$PageType = new int[VoiceTrainingMetricMetadata.PageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$handsfree$protocols$uservoicerecognition$metrics$VoiceTrainingMetricMetadata$PageType[VoiceTrainingMetricMetadata.PageType.TRAINING_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$protocols$uservoicerecognition$metrics$VoiceTrainingMetricMetadata$PageType[VoiceTrainingMetricMetadata.PageType.TRAINING_PRIMER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public EnrollmentActivity() {
    }

    private void emitNotificationClickMetric() {
        new NotificationMetricReporter(this).reportNotificationClickMetric(getIntent());
    }

    public static int getBackgroundColor() {
        return sBackgroundColor;
    }

    @VisibleForTesting
    VoiceTrainingMetricMetadata.PageType chooseStartEnrollmentPageType() {
        boolean z = true;
        boolean z2 = this.mEnrollmentTypeResolver.getSpeakerVerificationEnrollmentType() == EnrollmentType._1PSV;
        if (this.mEnrollmentTypeResolver.getEnrollmentType() != EnrollmentType._1PSV_DECOUPLED) {
            z = false;
        }
        if (z2 && !z) {
            return VoiceTrainingMetricMetadata.PageType.TRAINING_PRIMER;
        }
        return VoiceTrainingMetricMetadata.PageType.TRAINING_START;
    }

    public void emitMetric(@NonNull VoiceTrainingMetricMetadata.PageType pageType, @NonNull VoiceTrainingMetricMetadata.SubPageType subPageType, @NonNull VoiceTrainingMetricMetadata.EventType eventType, @NonNull EnrollmentType enrollmentType) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        newBuilder.withVoiceTrainingMetric(TAG, pageType, subPageType, eventType, enrollmentType);
        newBuilder.emit(this);
    }

    @VisibleForTesting
    void emitPageLeaveMetric() {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        newBuilder.withVoiceTrainingMetric(TAG, this.mCurrentPage, VoiceTrainingMetricMetadata.SubPageType.NONE, VoiceTrainingMetricMetadata.EventType.LEAVE, getEnrollmentType());
        newBuilder.emit(this);
    }

    @VisibleForTesting
    void emitPageLoadMetric() {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        newBuilder.withVoiceTrainingMetric(TAG, this.mCurrentPage, VoiceTrainingMetricMetadata.SubPageType.NONE, VoiceTrainingMetricMetadata.EventType.PAGE_LOAD, getEnrollmentTypeForCurrentPage());
        newBuilder.emit(this);
    }

    @VisibleForTesting
    EnrollmentCompleteFragment getEnrollmentCompleteFragment() {
        return new EnrollmentCompleteFragment(this.mSuppressUVRActivation);
    }

    @VisibleForTesting
    EnrollmentType getEnrollmentType() {
        if (this.mEnrollmentType == null) {
            this.mEnrollmentType = this.mEnrollmentTypeResolver.getEnrollmentType();
        }
        return this.mEnrollmentType;
    }

    @VisibleForTesting
    EnrollmentType getEnrollmentTypeForCurrentPage() {
        if (this.mCurrentPage == VoiceTrainingMetricMetadata.PageType.TRAINING_PRIMER) {
            return this.mEnrollmentTypeResolver.getSpeakerVerificationEnrollmentType();
        }
        return getEnrollmentType();
    }

    @VisibleForTesting
    public NotificationManager getNotificationManager(@NonNull Context context) {
        return (NotificationManager) context.getSystemService("notification");
    }

    @VisibleForTesting
    Fragment getStartEnrollmentFragment(@NonNull VoiceTrainingMetricMetadata.PageType pageType) {
        int ordinal = pageType.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                Log.e(TAG, "Unexpected page type, defaulting to StartEnrollmentFragment");
                return new StartEnrollmentFragment();
            }
            return new EnrollmentPrimerFragment();
        }
        return new StartEnrollmentFragment();
    }

    @VisibleForTesting
    void loadEnrollmentCompleteFragment() {
        runOnUiThread(new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.EnrollmentActivity.1
            @Override // java.lang.Runnable
            public void run() {
                EnrollmentActivity.this.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_enter, R.animator.exit_fade_out).replace(R.id.container, EnrollmentActivity.this.getEnrollmentCompleteFragment()).commitAllowingStateLoss();
            }
        });
        this.mCurrentPage = VoiceTrainingMetricMetadata.PageType.TRAINING_FINISH;
        emitPageLoadMetric();
        this.mNotificationManager.cancel(1001);
    }

    public void loadGettingReadyFragment() {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_enter, R.animator.exit_fade_out).replace(R.id.container, new GettingReadyFragment()).addToBackStack(GETTING_READY_FRAGMENT_NAME).commit();
        this.mCurrentPage = VoiceTrainingMetricMetadata.PageType.TRAINING_GETTING_READY;
        emitPageLoadMetric();
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.StepsFragmentCallback
    public void loadStartEnrollmentFragment() {
        this.mCurrentPage = chooseStartEnrollmentPageType();
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_enter, R.animator.exit_fade_out).replace(R.id.container, getStartEnrollmentFragment(this.mCurrentPage)).commit();
        emitPageLoadMetric();
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentFragment.StartEnrollmentFragmentCallback
    public void loadStepsFragment() {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_enter, R.animator.exit_fade_out).replace(R.id.container, new StepsFragment()).commit();
        this.mCurrentPage = VoiceTrainingMetricMetadata.PageType.TRAINING_MAIN;
        emitPageLoadMetric();
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment.EnrollmentPrimerFragmentCallback
    public void loadStepsFragmentForEnrollmentPrimer() {
        if (shouldShowGettingReadyFragment()) {
            loadGettingReadyFragment();
        } else {
            loadStepsFragment();
        }
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment.EnrollmentPrimerFragmentCallback
    public void loadVoicePrivacyScreenForEnrollmentPrimer() {
        Log.i(TAG, "loadVoicePrivacyScreenForEnrollmentPrimer");
        startActivity(new Intent(this, EnrollmentPrivacyTermsActivity.class));
        finishStep(ManagedActivity.StepResult.EXIT);
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentFragment.StartEnrollmentFragmentCallback
    public void loadVoicePrivacyScreenForEnrollmentStart() {
        loadVoicePrivacyScreenForEnrollmentPrimer();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        newBuilder.withVoiceTrainingMetric(TAG, this.mCurrentPage, VoiceTrainingMetricMetadata.SubPageType.NONE, VoiceTrainingMetricMetadata.EventType.BACK_BUTTON, getEnrollmentType());
        newBuilder.emit(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.handsfree.ui.ManagedActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        this.mInitializer = InitializerProvider.getInitializer();
        this.mInitializer.initialize(this);
        super.onCreate(bundle);
        setRequestedOrientation(1);
        ThemeUtil.setTheme(this);
        setContentView(R.layout.mosaic_activity_enrollment);
        sBackgroundColor = ThemeUtil.getColorFromAttribute(this, R.attr.mosaicBackground);
        this.mUVRConnector = UVRModule.INSTANCE.getUVRContract().getUVRConnector();
        this.mUVRConnector.startConnection(this, false);
        this.mNotificationManager = getNotificationManager(this);
        this.mMetricsBuilderProvider = MetricsBuilderProvider.getInstance(this);
        this.mEnrollmentTypeResolver = ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(getApplicationContext(), FalcoProtocolComponent.class)).enrollmentTypeResolverLazy().mo358get();
        this.mSuppressUVRActivation = getIntent().getBooleanExtra(EXTRA_SUPPRESS_UVR_ACTIVATION, false);
        this.mCurrentPage = chooseStartEnrollmentPageType();
        getSupportFragmentManager().beginTransaction().add(R.id.container, getStartEnrollmentFragment(this.mCurrentPage)).commit();
        emitPageLoadMetric();
        emitNotificationClickMetric();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.handsfree.ui.ManagedActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.mUVRConnector.endConnection(this);
        super.onDestroy();
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.StepsFragmentCallback
    public void onFinishEnrollment() {
        if (this.mSuppressUVRActivation) {
            finishStep(ManagedActivity.StepResult.CONTINUE);
        } else {
            loadEnrollmentCompleteFragment();
        }
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.finalstep.EnrollmentCompleteFragment.EnrollmentCompleteCallback
    public void onLockScreenSettingFinished() {
        finishStep(ManagedActivity.StepResult.CONTINUE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(@NonNull Intent intent) {
        super.onNewIntent(intent);
        if (!UVRModule.INSTANCE.getUVRContract().getVendorSettings().isUVREnrolled(UserInfo.DEFAULT_USER)) {
            loadStartEnrollmentFragment();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        emitPageLeaveMetric();
        super.onPause();
    }

    boolean shouldShowGettingReadyFragment() {
        return false;
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentFragment.StartEnrollmentFragmentCallback
    public void skipEnrollment() {
        finishStep(ManagedActivity.StepResult.EXIT);
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment.EnrollmentPrimerFragmentCallback
    public void skipEnrollmentForEnrollmentPrimer() {
        Log.i(TAG, "skipEnrollmentForEnrollmentPrimer");
        emitPageLeaveMetric();
        finishStep(ManagedActivity.StepResult.EXIT);
    }

    @VisibleForTesting
    public EnrollmentActivity(@NonNull Initializer initializer, @NonNull UVRConnector uVRConnector, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull VoiceTrainingMetricMetadata.PageType pageType, @NonNull NotificationManager notificationManager, @NonNull EnrollmentTypeResolver enrollmentTypeResolver, boolean z) {
        this.mInitializer = initializer;
        this.mUVRConnector = uVRConnector;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mCurrentPage = pageType;
        this.mNotificationManager = notificationManager;
        this.mEnrollmentTypeResolver = enrollmentTypeResolver;
        this.mSuppressUVRActivation = z;
    }
}
