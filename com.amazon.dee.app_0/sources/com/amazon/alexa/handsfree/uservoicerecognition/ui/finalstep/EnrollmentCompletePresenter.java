package com.amazon.alexa.handsfree.uservoicerecognition.ui.finalstep;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.notification.notifiers.EnableHandsFreeNotifier;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManagerProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback.SettingsCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentStatus;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentStatusManager;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.metrics.ClickMetricMetadata;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.LockScreenSettingState;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.LockScreenSettingStateProvider;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback;
import com.amazon.alexa.handsfree.settings.voxsettings.ShowOnLockscreenResultReceiver;
import com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsEnqueuer;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.EnrollmentResumeStateStore;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes8.dex */
public class EnrollmentCompletePresenter {
    static final String DISABLE_SHOW_ON_LOCK_SCREEN = "disableShowOnLockscreen";
    private static final String TAG = "EnrollCompletePresenter";
    private final Context mContext;
    private EnrollmentResumeStateStore mEnrollmentResumeStateStore;
    private final EnrollmentStatusManager mEnrollmentStatusProvider;
    private final EnrollmentTypeResolver mEnrollmentTypeResolver;
    private final FinalStepMetricsRecorder mFinalStepMetricsRecorder;
    private final HandsFreeUserIdentity mHandsFreeUser;
    private final LockScreenSettingStateProvider mLockScreenSettingStateProvider;
    private final LockScreenSettingView mLockScreenSettingView;
    private final MetricsBuilderProvider mMetricsBuilderProvider;
    private final EnableHandsFreeNotifier.PreferenceManager mNotificationPreferenceManager;
    private final SettingsCallback mSettingsCallback;
    private final UVREnroller mUVREnroller;
    private final UVRVendorSettings mUVRVendorSettings;
    private final VoxSettingsEnqueuer mVoxSettingsEnqueuer;
    private final WakeWordSettingsManager mWakeWordSettingsManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EnrollmentCompletePresenter(@NonNull Context context, @NonNull LockScreenSettingView lockScreenSettingView) {
        this(context, lockScreenSettingView, new LockScreenSettingStateProvider(context), MetricsBuilderProvider.getInstance(context), UVRModule.INSTANCE.getSettingsCallback(), new VoxSettingsEnqueuer(), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy().mo358get(), EnrollmentStatusManager.getInstance(context), UVRModule.INSTANCE.getUVRContract().getVendorSettings(), UVRModule.INSTANCE.getUVRContract().getEnroller(), WakeWordSettingsManagerProvider.getInstance().get(), new FinalStepMetricsRecorder(context), new EnrollmentResumeStateStore(context), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).handsFreeUserIdentityProvider().getHandsFreeUserIdentity(), new EnableHandsFreeNotifier.PreferenceManager());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void emitMetrics(boolean z) {
        this.mMetricsBuilderProvider.newBuilder().withClickMetric(TAG, ClickMetricMetadata.Component.UVR, ClickMetricMetadata.PageType.RESPOND_ON_LOCK_SCREEN, z ? ClickMetricMetadata.Action.ENABLE : ClickMetricMetadata.Action.DISABLE, this.mEnrollmentTypeResolver.getEnrollmentType()).emit(this.mContext);
    }

    private void setupShowOnLockScreen(boolean z, boolean z2, @Nullable final PreferenceCallback preferenceCallback) {
        this.mLockScreenSettingView.setupListener(new PreferenceCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.finalstep.EnrollmentCompletePresenter.1
            @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback
            public void onPreferenceChanged(boolean z3) {
                EnrollmentCompletePresenter.this.emitMetrics(z3);
                EnrollmentCompletePresenter.this.mVoxSettingsEnqueuer.updateShowOnLockscreenPref(EnrollmentCompletePresenter.this.mContext, z3);
                PreferenceCallback preferenceCallback2 = preferenceCallback;
                if (preferenceCallback2 != null) {
                    preferenceCallback2.onPreferenceChanged(z3);
                }
            }
        });
        this.mLockScreenSettingView.setEnabled(z);
        if (!z2) {
            disableShowOnLockScreen();
        }
        enableUVR();
    }

    private void updateUvrEnrollmentStatusProvider() {
        this.mEnrollmentStatusProvider.setEnrollmentStatus(this.mEnrollmentTypeResolver.getSpeakerVerificationEnrollmentType() == EnrollmentType._1PSV ? EnrollmentStatus.SETUP_IN_1PSV : EnrollmentStatus.SETUP_IN_3PSV);
    }

    @VisibleForTesting
    void cancelEnrollment() {
        this.mUVREnroller.cancelUserVoiceEnrollment(new ResponseCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.finalstep.EnrollmentCompletePresenter.4
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cancelUserVoiceEnrollment onError: ");
                outline107.append(callbackErrorMetadata.getErrorMessage());
                Log.d(EnrollmentCompletePresenter.TAG, outline107.toString());
                EnrollmentCompletePresenter.this.mFinalStepMetricsRecorder.logUserVoiceEnrollmentError(callbackErrorMetadata, "UVREnrollerCancelEnrollment");
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onSuccess() {
                Log.d(EnrollmentCompletePresenter.TAG, "cancelUserVoiceEnrollment onSuccess");
            }
        });
    }

    public void disableShowOnLockScreen() {
        this.mVoxSettingsEnqueuer.getShowOnLockscreen(this.mContext, new ShowOnLockscreenResultReceiver(new PreferenceCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.finalstep.EnrollmentCompletePresenter.2
            @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback
            public void onPreferenceChanged(boolean z) {
                EnrollmentCompletePresenter.this.mLockScreenSettingStateProvider.setLockScreenSettingState(z ? LockScreenSettingState.ACTIVE : LockScreenSettingState.INACTIVE);
            }
        }));
        this.mLockScreenSettingView.setRemotelyDisabled();
        this.mMetricsBuilderProvider.newBuilder().withPerformanceMetric(TAG, DISABLE_SHOW_ON_LOCK_SCREEN).emit(this.mContext);
    }

    @VisibleForTesting
    void enableUVR() {
        this.mUVRVendorSettings.enableUVR(UserInfo.DEFAULT_USER, new ResponseCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.finalstep.EnrollmentCompletePresenter.3
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("enableUVR onError with reason: ");
                outline107.append(callbackErrorMetadata.getErrorMessage());
                Log.d(EnrollmentCompletePresenter.TAG, outline107.toString());
                EnrollmentCompletePresenter.this.cancelEnrollment();
                EnrollmentCompletePresenter.this.mFinalStepMetricsRecorder.logUserVoiceEnrollmentError(callbackErrorMetadata, "UVREnrollerEnableUVR");
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onSuccess() {
                Log.d(EnrollmentCompletePresenter.TAG, "enableUVR onSuccess");
                EnrollmentCompletePresenter.this.mFinalStepMetricsRecorder.logSetupComplete();
                EnrollmentCompletePresenter.this.mEnrollmentResumeStateStore.setEnrollmentComplete();
                EnrollmentCompletePresenter.this.mWakeWordSettingsManager.setHandsFreeState(true, new ResponseCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.finalstep.EnrollmentCompletePresenter.3.1
                    @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
                    public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("setHandsFreeState onError: ");
                        outline107.append(callbackErrorMetadata.getErrorMessage());
                        Log.e(EnrollmentCompletePresenter.TAG, outline107.toString());
                    }

                    @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
                    public void onSuccess() {
                        Log.d(EnrollmentCompletePresenter.TAG, "setHandsFreeState onSuccess");
                        EnrollmentCompletePresenter.this.mNotificationPreferenceManager.disableEnableHandsFreeNotification(EnrollmentCompletePresenter.this.mContext);
                    }
                }, EnrollmentCompletePresenter.TAG);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initialize() {
        if (!isBlockSensitiveRequest()) {
            setupShowOnLockScreen(this.mSettingsCallback.isLockScreenEnabled());
        }
        updateUvrEnrollmentStatusProvider();
        enableUVR();
    }

    public boolean isBlockSensitiveRequest() {
        HandsFreeUserIdentity handsFreeUserIdentity = this.mHandsFreeUser;
        return handsFreeUserIdentity != null && handsFreeUserIdentity.hasComponent(HandsFreeComponent.HANDS_FREE_BLOCK_SENSITIVE_REQUEST) && AMPDInformationProvider.getInstance(this.mContext).isBSR();
    }

    public void updateShowOnLockScreenValue() {
        final LockScreenSettingView lockScreenSettingView = this.mLockScreenSettingView;
        lockScreenSettingView.getClass();
        ShowOnLockscreenResultReceiver showOnLockscreenResultReceiver = new ShowOnLockscreenResultReceiver(new PreferenceCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.finalstep.-$$Lambda$7RGtkULxTtpOlQDLTQDusxsWEyU
            @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback
            public final void onPreferenceChanged(boolean z) {
                LockScreenSettingView.this.setActive(z);
            }
        });
        Log.d(TAG, "call vox getShowOnLockScreenValue");
        this.mVoxSettingsEnqueuer.getShowOnLockscreen(this.mContext, showOnLockscreenResultReceiver);
    }

    private void setupShowOnLockScreen(boolean z) {
        setupShowOnLockScreen(true, z, null);
    }

    @VisibleForTesting
    EnrollmentCompletePresenter(@NonNull Context context, @NonNull LockScreenSettingView lockScreenSettingView, @NonNull LockScreenSettingStateProvider lockScreenSettingStateProvider, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull SettingsCallback settingsCallback, @NonNull VoxSettingsEnqueuer voxSettingsEnqueuer, @NonNull EnrollmentTypeResolver enrollmentTypeResolver, @NonNull EnrollmentStatusManager enrollmentStatusManager, @NonNull UVRVendorSettings uVRVendorSettings, @NonNull UVREnroller uVREnroller, @NonNull WakeWordSettingsManager wakeWordSettingsManager, @NonNull FinalStepMetricsRecorder finalStepMetricsRecorder, @NonNull EnrollmentResumeStateStore enrollmentResumeStateStore, @NonNull HandsFreeUserIdentity handsFreeUserIdentity, @NonNull EnableHandsFreeNotifier.PreferenceManager preferenceManager) {
        this.mContext = context;
        this.mLockScreenSettingView = lockScreenSettingView;
        this.mLockScreenSettingStateProvider = lockScreenSettingStateProvider;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mSettingsCallback = settingsCallback;
        this.mVoxSettingsEnqueuer = voxSettingsEnqueuer;
        this.mEnrollmentTypeResolver = enrollmentTypeResolver;
        this.mEnrollmentStatusProvider = enrollmentStatusManager;
        this.mUVRVendorSettings = uVRVendorSettings;
        this.mWakeWordSettingsManager = wakeWordSettingsManager;
        this.mFinalStepMetricsRecorder = finalStepMetricsRecorder;
        this.mUVREnroller = uVREnroller;
        this.mEnrollmentResumeStateStore = enrollmentResumeStateStore;
        this.mHandsFreeUser = handsFreeUserIdentity;
        this.mNotificationPreferenceManager = preferenceManager;
    }
}
