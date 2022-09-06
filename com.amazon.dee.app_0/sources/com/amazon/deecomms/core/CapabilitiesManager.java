package com.amazon.deecomms.core;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsDynamicFeature;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.Utils;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import javax.inject.Inject;
import rx.Observable;
/* loaded from: classes12.dex */
public class CapabilitiesManager {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CapabilitiesManager.class);
    private final Context context;
    private final FeatureFlagManager ffManager;

    @Inject
    public CapabilitiesManager(@NonNull FeatureFlagManager featureFlagManager, @NonNull Context context) {
        this.ffManager = featureFlagManager;
        this.context = context;
    }

    private boolean isFeatureAvailabileInArcusBasedOnPFM(@NonNull String str) {
        Set<String> configValueSet;
        String preferredMarketplace = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getPreferredMarketplace(false);
        if (preferredMarketplace == null || (configValueSet = CommsDaggerWrapper.getComponent().getArcusConfig().getConfigValueSet(str)) == null || configValueSet.isEmpty()) {
            return false;
        }
        return configValueSet.contains(preferredMarketplace);
    }

    public boolean disableNMinuteIdentityCache() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.WARIO, false);
    }

    public ArrayList<String> getAllFeatures() {
        return this.ffManager.getAllFeatures();
    }

    public boolean getCoboValue() {
        if (this.ffManager.isCommsAllFeatureEnabled()) {
            return true;
        }
        return isFeatureAvailabileInArcusBasedOnPFM("Access.CallerIdSettingAccess");
    }

    public boolean getCookiesFromCore() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.YOSHI, false);
    }

    public boolean getCyborgValue() {
        if (this.ffManager.isCommsAllFeatureEnabled()) {
            return true;
        }
        return isFeatureAvailabileInArcusBasedOnPFM("Access.CoboAccess");
    }

    @Deprecated
    public Observable<Boolean> getDiagnosticsObservable() {
        return Observable.just(false);
    }

    public boolean getDiagnosticsValue() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.DIAGNOSTICS);
    }

    public boolean hasSharingDecouplingAccess() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.SHARING_DECOUPLING, false);
    }

    public boolean isAllWebRTCMetricsEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.HUDSON);
    }

    public boolean isAndroid10CallingEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.AVALANCHE, true);
    }

    public boolean isAnnouncementAccessoriesEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.ANNOUNCEMENT_24024, true);
    }

    public boolean isAnnouncementPushNotificationEnabled() {
        return isCommsAllFeaturesEnabled() || this.ffManager.isFeatureEnabled(CommsDynamicFeature.CATALINA);
    }

    public boolean isAssistSipCallingEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.ASSIST_SIP, false);
    }

    public boolean isAuthTokenErrorDialogEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.TACHYON_CALLING_FEATURE_AUTH_TOKEN_ERROR_DIALOG, true);
    }

    public boolean isAutoProvisioningEnabled() {
        return isCommsAllFeaturesEnabled() || this.ffManager.isFeatureEnabled(CommsDynamicFeature.IVYSAUR);
    }

    public boolean isBatchContactsDownloadEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.RATICATE, true);
    }

    public boolean isBlockByNameEnabled() {
        return isCommsAllFeaturesEnabled() || this.ffManager.isFeatureEnabled(CommsDynamicFeature.HURRICANE);
    }

    public boolean isBlockByNumberEnabled() {
        return isCommsAllFeaturesEnabled() || this.ffManager.isFeatureEnabled(CommsDynamicFeature.TYPHOON);
    }

    public boolean isCallCaptioningEnabled() {
        return isCommsAllFeaturesEnabled() || this.ffManager.isFeatureEnabled(CommsDynamicFeature.MIAOYU);
    }

    public boolean isCallReconnectv3Enabled() {
        if (this.ffManager.isCommsAllFeatureEnabled()) {
            return true;
        }
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.CALL_RECONNECT_V3_LIWAN);
    }

    public boolean isChildInvitationEnabled() {
        return isCommsAllFeaturesEnabled() || this.ffManager.isFeatureEnabled(CommsDynamicFeature.DIGLETT);
    }

    public boolean isCommsAllFeaturesEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.COMMS_ALL_FEATURES);
    }

    public boolean isCommsEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.COMMS_AVAILABILITY);
    }

    public boolean isContactsManagementEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.BEARCAT);
    }

    public boolean isContentSharingEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.COMO_23931, false);
    }

    public boolean isDriveModeCommsNativeEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.DRIVE_MODE_ANDROID_COMMS_NATIVE, false);
    }

    public boolean isEducationalTextEnabled() {
        return isCommsAllFeaturesEnabled() || this.ffManager.isFeatureEnabled(CommsDynamicFeature.BULBASAUR);
    }

    public boolean isEnhancedNotificationEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.ENHANCED_NOTIFICATION);
    }

    public boolean isEnhancedVideoProcessingEnabled() {
        return false;
    }

    public boolean isFireOSCommsGatingEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.HORNET);
    }

    public boolean isLightyearEnabled() {
        return isCommsAllFeaturesEnabled() || this.ffManager.isFeatureEnabled(CommsDynamicFeature.CABLE);
    }

    public boolean isLinkSharingEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.SHARE_LINK_ANDROID, false) || this.ffManager.isFeatureEnabled(CommsDynamicFeature.SHARE_LINK, false);
    }

    public boolean isMPUCallOrientationEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.DESPACITO, false);
    }

    public boolean isMPUDynamicPipEnabled() {
        return isCommsAllFeaturesEnabled() || this.ffManager.isFeatureEnabled(CommsDynamicFeature.MPU_DYNAMIC_PIP);
    }

    public boolean isMessagingControllerFeaturesEnabled() {
        return this.ffManager.isFeatureEnabled(new ArrayList(Arrays.asList(CommsDynamicFeature.PERSHING)));
    }

    public boolean isMosaicThemingEnabled() {
        return true;
    }

    public boolean isNativeCallingEnabled() {
        boolean z = isCommsAllFeaturesEnabled() || this.ffManager.isFeatureEnabled(CommsDynamicFeature.EKANS);
        GeneratedOutlineSupport.outline5("Native Calling feature enabled: ", z, LOG);
        return z;
    }

    public boolean isPCAProfilesEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.PCA_PROFILES, false);
    }

    public boolean isPublishingMetricsToInsightsEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.ALEXA_COMMS_ENABLE_INSIGHTS_ON_AMA, false);
    }

    public boolean isRNProfileOobeEnabled() {
        return !Utils.isFireOS() && this.ffManager.isFeatureEnabled(CommsDynamicFeature.ALEXA_PROFILE_OOBE_DECOUPLING_ANDROID, false);
    }

    public boolean isReactionsEnabled() {
        return isCommsAllFeaturesEnabled() || this.ffManager.isFeatureEnabled(CommsDynamicFeature.REACTIONS);
    }

    public boolean isRealTimeTextEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.LINDAIYU, true);
    }

    public boolean isRecommendationsEnabled() {
        return isCommsAllFeaturesEnabled() || this.ffManager.isFeatureEnabled(CommsDynamicFeature.PIDGEOTTO);
    }

    public boolean isRemovingGUIInCallPromptsEnabled() {
        return isCommsAllFeaturesEnabled() || this.ffManager.isFeatureEnabled(CommsDynamicFeature.CROMWELL);
    }

    public boolean isSepiaMessagingImprovementsEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.SEPIA_MESSAGING);
    }

    public boolean isShareSheetEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.SHARE_SHEET, false);
    }

    public boolean isSharingEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.VENUSAUR, false) || this.ffManager.isFeatureEnabled(CommsDynamicFeature.RIDLEY, false);
    }

    public boolean isStoryTimeSupported() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.STORYTIME, true);
    }

    public boolean isSunflowersEnabled() {
        return isCommsAllFeaturesEnabled() || this.ffManager.isFeatureEnabled(CommsDynamicFeature.PIKACHU);
    }

    public boolean isTelemetryEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.TELEMETRY);
    }

    public boolean isTestersAccount() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.COMMS_ALL_FEATURES);
    }

    public boolean isThemedUIEnabled() {
        return true;
    }

    public boolean isVoxPersonalizationEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.THOR);
    }

    public boolean isWorldsEnabled() {
        return isCommsAllFeaturesEnabled() || this.ffManager.isFeatureEnabled(CommsDynamicFeature.SEPIA);
    }

    public void observeSharingFeature(FeatureServiceV2.FeatureUpdateListener featureUpdateListener) {
        this.ffManager.observeFeature(CommsDynamicFeature.VENUSAUR, featureUpdateListener);
        this.ffManager.observeFeature(CommsDynamicFeature.RIDLEY, featureUpdateListener);
    }

    public boolean shouldSkipOOBEIntro() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.ROGUE);
    }

    public boolean showPSTNForNonCOBORegions() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.SHERMAN);
    }

    public boolean tapToCallEnabled() {
        return this.ffManager.isFeatureEnabled(CommsDynamicFeature.COMO26513_TTC_ANDROID, false);
    }
}
