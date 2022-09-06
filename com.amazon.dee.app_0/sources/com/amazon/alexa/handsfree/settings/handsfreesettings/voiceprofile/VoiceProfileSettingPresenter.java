package com.amazon.alexa.handsfree.settings.handsfreesettings.voiceprofile;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.HandsFreeSettingsState;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.handsfree.HandsFreeSettingContract;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.voiceprofile.VoiceProfileSettingContract;
import com.amazon.alexa.handsfree.settings.metrics.ClickMetricMetadata;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class VoiceProfileSettingPresenter implements VoiceProfileSettingContract.Listener {
    @VisibleForTesting
    static final String ALEXA_PACKAGE_NAME = "com.amazon.dee.app";
    @VisibleForTesting
    static final String ALEXA_WEB_APP_ACTION_NAME = "com.amazon.intent.action.VKICK";
    @VisibleForTesting
    static final String ALEXA_WEB_APP_CLASS_NAME = "com.amazon.dee.webapp.activity.AlexaWebAppActivity";
    @VisibleForTesting
    static final String ALEXA_WEB_APP_INITIAL_PATH = "\\#";
    private static final String TAG = "VoiceProfileStngPrsntr";
    private final Context mContext;
    private Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private MetricsBuilderProvider mMetricsBuilderProvider;
    private RoutingService mRoutingService;
    private VoiceProfileSettingModel mVoiceProfileSettingModel;
    private final VoiceProfileSettingContract.View mVoiceProfileSettingView;
    private HandsFreeSettingContract.WakeWordManager mWakeWordManager;

    public VoiceProfileSettingPresenter(@NonNull VoiceProfileSettingContract.View view, @NonNull Context context, @NonNull HandsFreeSettingContract.WakeWordManager wakeWordManager) {
        this.mVoiceProfileSettingView = view;
        this.mContext = context;
        this.mVoiceProfileSettingModel = new VoiceProfileSettingModel(this, context);
        this.mMetricsBuilderProvider = MetricsBuilderProvider.getInstance(context);
        this.mWakeWordManager = wakeWordManager;
        this.mEnrollmentTypeResolverLazy = ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy();
        this.mRoutingService = (RoutingService) GeneratedOutlineSupport1.outline20(RoutingService.class);
    }

    public boolean isVoiceProfileSetup() {
        return this.mVoiceProfileSettingModel.isUVREnrolled();
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.voiceprofile.VoiceProfileSettingContract.Listener
    public void onProfileDeletedSuccessfully() {
        this.mVoiceProfileSettingView.showDeleteSuccess();
        if (this.mVoiceProfileSettingModel.isUVRMandatory()) {
            if (this.mWakeWordManager.isWakeWordActive()) {
                this.mWakeWordManager.toggleWakeWordDetection();
            }
            this.mVoiceProfileSettingView.setMandatoryVoiceProfileNotEnrolled();
            return;
        }
        this.mVoiceProfileSettingView.setOptionalVoiceProfileNotEnrolled();
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.voiceprofile.VoiceProfileSettingContract.Listener
    public void onProfileDeletedWithError() {
        this.mVoiceProfileSettingView.showDeleteFail();
    }

    public void refreshDisplay() {
        if (this.mVoiceProfileSettingModel.isUVREnrolled()) {
            this.mVoiceProfileSettingView.setVoiceProfileEnrolled();
        } else if (this.mVoiceProfileSettingModel.isUVRMandatory()) {
            this.mVoiceProfileSettingView.setMandatoryVoiceProfileNotEnrolled();
        } else {
            this.mVoiceProfileSettingView.setOptionalVoiceProfileNotEnrolled();
        }
    }

    public void setRemotelyDisabled() {
        this.mVoiceProfileSettingView.setRemotelyDisabled();
    }

    public void setupVoiceProfilePreferences(@NonNull PreferenceCallback preferenceCallback) {
        this.mVoiceProfileSettingView.setupLockScreenCallback(preferenceCallback);
        this.mVoiceProfileSettingView.setupCreateVoiceProfilePreference(new PreferenceCallback() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.voiceprofile.VoiceProfileSettingPresenter.1
            @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback
            public void onPreferenceChanged(boolean z) {
                VoiceProfileSettingPresenter.this.mMetricsBuilderProvider.newBuilder().withClickMetric(VoiceProfileSettingPresenter.TAG, ClickMetricMetadata.Component.SETTINGS_MENU, ClickMetricMetadata.PageType.VOICE_PROFILE, ClickMetricMetadata.Action.START_VOICE_TRAINING).emit(VoiceProfileSettingPresenter.this.mContext);
            }
        });
        this.mVoiceProfileSettingView.setupDeleteVoiceProfilePreference(new PreferenceCallback() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.voiceprofile.VoiceProfileSettingPresenter.2
            @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback
            public void onPreferenceChanged(boolean z) {
                VoiceProfileSettingPresenter.this.mMetricsBuilderProvider.newBuilder().withClickMetric(VoiceProfileSettingPresenter.TAG, ClickMetricMetadata.Component.SETTINGS_MENU, ClickMetricMetadata.PageType.VOICE_PROFILE, ClickMetricMetadata.Action.DELETE).emit(VoiceProfileSettingPresenter.this.mContext);
                if (z) {
                    VoiceProfileSettingPresenter.this.mMetricsBuilderProvider.newBuilder().withClickMetric(VoiceProfileSettingPresenter.TAG, ClickMetricMetadata.Component.SETTINGS_MENU, ClickMetricMetadata.PageType.VOICE_PROFILE, ClickMetricMetadata.Action.DELETE_CONFIRM).emit(VoiceProfileSettingPresenter.this.mContext);
                    EnrollmentTypeResolver enrollmentTypeResolver = (EnrollmentTypeResolver) VoiceProfileSettingPresenter.this.mEnrollmentTypeResolverLazy.mo358get();
                    if (enrollmentTypeResolver == null || enrollmentTypeResolver.getSpeakerVerificationEnrollmentType() != EnrollmentType._1PSV || enrollmentTypeResolver.getEnrollmentType() == EnrollmentType._1PSV_DECOUPLED) {
                        VoiceProfileSettingPresenter.this.mVoiceProfileSettingModel.deleteVoiceModel();
                        return;
                    }
                    VoiceProfileSettingPresenter.this.mRoutingService.navigate(VoiceProfileSettingPresenter.this.mRoutingService.route("v2/settings/your-voice").with("ishandsfree", "true").create());
                    ((Activity) VoiceProfileSettingPresenter.this.mContext).finish();
                    return;
                }
                VoiceProfileSettingPresenter.this.mMetricsBuilderProvider.newBuilder().withClickMetric(VoiceProfileSettingPresenter.TAG, ClickMetricMetadata.Component.SETTINGS_MENU, ClickMetricMetadata.PageType.VOICE_PROFILE, ClickMetricMetadata.Action.DELETE_CANCEL).emit(VoiceProfileSettingPresenter.this.mContext);
            }
        });
        refreshDisplay();
    }

    public void updateState(@NonNull HandsFreeSettingsState handsFreeSettingsState) {
        if (handsFreeSettingsState.isRemotelyDisabled()) {
            this.mVoiceProfileSettingView.setRemotelyDisabled();
        } else if (handsFreeSettingsState == HandsFreeSettingsState.INACTIVE) {
            this.mVoiceProfileSettingView.setUIRemoveVoiceProfileSettingsAndSetInactive();
        } else if (this.mVoiceProfileSettingModel.isUVREnrolled()) {
            this.mVoiceProfileSettingView.setUIAddVoiceProfileSettingsAndSetActive();
        } else {
            this.mVoiceProfileSettingView.updateViews(handsFreeSettingsState == HandsFreeSettingsState.ACTIVE);
        }
    }

    @VisibleForTesting
    VoiceProfileSettingPresenter(@NonNull VoiceProfileSettingContract.View view, @NonNull VoiceProfileSettingModel voiceProfileSettingModel, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull Context context, @NonNull HandsFreeSettingContract.WakeWordManager wakeWordManager, @NonNull Lazy<EnrollmentTypeResolver> lazy, @NonNull RoutingService routingService) {
        this.mVoiceProfileSettingView = view;
        this.mVoiceProfileSettingModel = voiceProfileSettingModel;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mContext = context;
        this.mWakeWordManager = wakeWordManager;
        this.mEnrollmentTypeResolverLazy = lazy;
        this.mRoutingService = routingService;
    }
}
