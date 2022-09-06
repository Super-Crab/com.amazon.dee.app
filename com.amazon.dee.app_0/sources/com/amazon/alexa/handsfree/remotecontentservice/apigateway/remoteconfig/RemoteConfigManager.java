package com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.utils.ApplicationInformationProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.HandsFreeStateProvider;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.RemoteConfigProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes8.dex */
public class RemoteConfigManager {
    private static final String TAG = "RemoteConfigManager";
    private final Lazy<ApplicationInformationProvider> mAppInfoProviderLazy;
    private StatusConfig mDisabledState;
    private HandsFreeSetupProperties mHandsFreeSetupProperties;
    private final HandsFreeStateProvider mHandsFreeStateProvider;
    private final RemoteConfigDeserializer mRemoteConfigDeserializer;
    private final RemoteConfigProvider mRemoteConfigProvider;

    @Inject
    public RemoteConfigManager(RemoteConfigProvider remoteConfigProvider, RemoteConfigDeserializer remoteConfigDeserializer, HandsFreeStateProvider handsFreeStateProvider, Lazy<ApplicationInformationProvider> lazy) {
        this.mRemoteConfigProvider = remoteConfigProvider;
        this.mRemoteConfigDeserializer = remoteConfigDeserializer;
        this.mHandsFreeStateProvider = handsFreeStateProvider;
        this.mAppInfoProviderLazy = lazy;
        initialize();
    }

    @Nullable
    private RemoteConfig getRemoteConfigObject() {
        return this.mRemoteConfigDeserializer.deserialize(this.mRemoteConfigProvider.getRemoteConfig());
    }

    private void sortAppVersionsByDspDesc(@NonNull List<AppVersions> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Collections.sort(list, new Comparator<AppVersions>() { // from class: com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.RemoteConfigManager.1
            @Override // java.util.Comparator
            public int compare(AppVersions appVersions, AppVersions appVersions2) {
                boolean verifyAppVersionsHasDsp = RemoteConfigManager.this.verifyAppVersionsHasDsp(appVersions);
                boolean verifyAppVersionsHasDsp2 = RemoteConfigManager.this.verifyAppVersionsHasDsp(appVersions2);
                if (verifyAppVersionsHasDsp || verifyAppVersionsHasDsp2) {
                    if (!verifyAppVersionsHasDsp) {
                        return 1;
                    }
                    if (verifyAppVersionsHasDsp2) {
                        return appVersions2.getLocalAppVersions().getDspAppVersion().intValue() - appVersions.getLocalAppVersions().getDspAppVersion().intValue();
                    }
                    return -1;
                }
                return 0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean verifyAppVersionsHasDsp(@NonNull AppVersions appVersions) {
        return (appVersions == null || appVersions.getLocalAppVersions() == null || appVersions.getLocalAppVersions().getDspAppVersion() == null) ? false : true;
    }

    private boolean verifyVersions(@Nullable List<LocalAppVersions> list) {
        Integer dspAppBuildCode = this.mAppInfoProviderLazy.mo358get().getDspAppBuildCode();
        Integer falcoLibBuildCode = this.mAppInfoProviderLazy.mo358get().getFalcoLibBuildCode();
        String str = TAG;
        Log.d(str, "Dsp app version " + dspAppBuildCode);
        String str2 = TAG;
        Log.d(str2, "Falco version " + falcoLibBuildCode);
        if (!verifyMinimumSupportedLocalAppVersions(dspAppBuildCode, falcoLibBuildCode)) {
            return false;
        }
        if (list != null && !list.isEmpty()) {
            for (LocalAppVersions localAppVersions : list) {
                if (localAppVersions != null) {
                    Integer dspAppVersion = localAppVersions.getDspAppVersion();
                    Integer falcoVersion = localAppVersions.getFalcoVersion();
                    if (Objects.equals(dspAppBuildCode, dspAppVersion) && Objects.equals(falcoLibBuildCode, falcoVersion)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Nullable
    public Integer getConfidenceLevel() {
        HandsFreeSetupProperties handsFreeSetupProperties = this.mHandsFreeSetupProperties;
        if (handsFreeSetupProperties != null) {
            return handsFreeSetupProperties.getConfidenceLevel();
        }
        return null;
    }

    @Nullable
    public HandsFreeSetupProperties getHandsFreeSetupProperties() {
        return this.mHandsFreeSetupProperties;
    }

    @Nullable
    public Integer getMaxAllowedNotificationsBeforeTerms() {
        HandsFreeSetupProperties handsFreeSetupProperties = this.mHandsFreeSetupProperties;
        if (handsFreeSetupProperties != null) {
            return handsFreeSetupProperties.getNotificationsConfig().getMaxAllowedNotificationsBeforeTerms();
        }
        return null;
    }

    @Nullable
    public Integer getMaxDaysAfterFirstUtterance(@NonNull String str) {
        UtteranceLimitsConfig utteranceLimitsConfig = getUtteranceLimitsConfig(str);
        if (utteranceLimitsConfig == null) {
            return null;
        }
        return utteranceLimitsConfig.getMaxDaysAfterFirstUtterance();
    }

    @Nullable
    public Integer getMinHoursBeforeNextUtterance(@NonNull String str) {
        UtteranceLimitsConfig utteranceLimitsConfig = getUtteranceLimitsConfig(str);
        if (utteranceLimitsConfig == null) {
            return null;
        }
        return utteranceLimitsConfig.getMinHoursBeforeNextUtterance();
    }

    @Nullable
    public Integer getMinimumAlexaAppVersionForDsp() {
        HandsFreeSetupProperties handsFreeSetupProperties = this.mHandsFreeSetupProperties;
        if (handsFreeSetupProperties == null) {
            return null;
        }
        List<AppVersions> minimumSupportedAlexaAppVersionForDSP = handsFreeSetupProperties.getMinimumSupportedAlexaAppVersionForDSP();
        Integer dspAppBuildCode = this.mAppInfoProviderLazy.mo358get().getDspAppBuildCode();
        if (minimumSupportedAlexaAppVersionForDSP != null && !minimumSupportedAlexaAppVersionForDSP.isEmpty() && dspAppBuildCode != null) {
            for (AppVersions appVersions : minimumSupportedAlexaAppVersionForDSP) {
                if (verifyAppVersionsHasDsp(appVersions)) {
                    if (dspAppBuildCode.intValue() >= appVersions.getLocalAppVersions().getDspAppVersion().intValue() && appVersions.getVoxAppVersion() != null) {
                        return appVersions.getVoxAppVersion();
                    }
                }
            }
        }
        return null;
    }

    @Nullable
    public List<Long> getNotificationDisplayIntervals(@NonNull String str) {
        List<NotificationsFrequencyConfig> notificationsFrequencyConfigList;
        HandsFreeSetupProperties handsFreeSetupProperties = this.mHandsFreeSetupProperties;
        if (handsFreeSetupProperties != null && handsFreeSetupProperties.getNotificationsConfig() != null && (notificationsFrequencyConfigList = this.mHandsFreeSetupProperties.getNotificationsConfig().getNotificationsFrequencyConfigList()) != null && !notificationsFrequencyConfigList.isEmpty()) {
            for (NotificationsFrequencyConfig notificationsFrequencyConfig : notificationsFrequencyConfigList) {
                if (notificationsFrequencyConfig != null && str.equals(notificationsFrequencyConfig.getType())) {
                    return notificationsFrequencyConfig.getIntervals();
                }
            }
        }
        return null;
    }

    @Nullable
    @VisibleForTesting
    UtteranceLimitsConfig getUtteranceLimitsConfig(@NonNull String str) {
        List<NotificationsUtteranceConfig> notificationsUtteranceConfigList;
        HandsFreeSetupProperties handsFreeSetupProperties = this.mHandsFreeSetupProperties;
        if (handsFreeSetupProperties != null && handsFreeSetupProperties.getNotificationsConfig() != null && (notificationsUtteranceConfigList = this.mHandsFreeSetupProperties.getNotificationsConfig().getNotificationsUtteranceConfigList()) != null && !notificationsUtteranceConfigList.isEmpty()) {
            for (NotificationsUtteranceConfig notificationsUtteranceConfig : notificationsUtteranceConfigList) {
                if (notificationsUtteranceConfig != null && str.equals(notificationsUtteranceConfig.getType()) && notificationsUtteranceConfig.getLimits() != null) {
                    return notificationsUtteranceConfig.getLimits();
                }
            }
        }
        return null;
    }

    @Nullable
    public Integer getUtteranceNotificationMaxCount(@NonNull String str) {
        UtteranceLimitsConfig utteranceLimitsConfig = getUtteranceLimitsConfig(str);
        if (utteranceLimitsConfig == null) {
            return null;
        }
        return utteranceLimitsConfig.getMaxCount();
    }

    public void initialize() {
        Log.d(TAG, "Initializing remote config");
        RemoteConfig remoteConfigObject = getRemoteConfigObject();
        if (remoteConfigObject != null) {
            this.mHandsFreeSetupProperties = remoteConfigObject.getHandsFreeSetupProperties();
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Remote config version ");
            outline107.append(remoteConfigObject.getVersion());
            Log.d(str, outline107.toString());
        }
        HandsFreeSetupProperties handsFreeSetupProperties = this.mHandsFreeSetupProperties;
        if (handsFreeSetupProperties != null) {
            this.mDisabledState = handsFreeSetupProperties.getDisabledState();
            sortAppVersionsByDspDesc(this.mHandsFreeSetupProperties.getMinimumSupportedAlexaAppVersionForDSP());
        }
        String str2 = TAG;
        Log.d(str2, "Remote config " + remoteConfigObject);
    }

    public boolean isAlexaAppVersionSupported() {
        Integer minimumSupportedAlexaAppVersion;
        Integer voxAppBuildCode = this.mAppInfoProviderLazy.mo358get().getVoxAppBuildCode();
        if (voxAppBuildCode == null) {
            Log.e(TAG, "Unable to retrieve an Alexa App version code (app not installed). Not supported.");
            return false;
        }
        String str = TAG;
        Log.d(str, "Vox app version " + voxAppBuildCode);
        Integer minimumAlexaAppVersionForDsp = getMinimumAlexaAppVersionForDsp();
        if (minimumAlexaAppVersionForDsp != null) {
            String str2 = TAG;
            Log.d(str2, "Minimum supported Alexa app version for dsp " + minimumAlexaAppVersionForDsp);
            return minimumAlexaAppVersionForDsp.intValue() <= voxAppBuildCode.intValue();
        }
        HandsFreeSetupProperties handsFreeSetupProperties = this.mHandsFreeSetupProperties;
        if (handsFreeSetupProperties == null || handsFreeSetupProperties.getMinimumSupportedAlexaAppVersion() == null || (minimumSupportedAlexaAppVersion = this.mHandsFreeSetupProperties.getMinimumSupportedAlexaAppVersion()) == null || minimumSupportedAlexaAppVersion.intValue() <= voxAppBuildCode.intValue()) {
            return true;
        }
        Log.e(TAG, "verifyMinimumSupportedVersions: Alexa App version not supported");
        return false;
    }

    public boolean isDeciderActive() {
        StatusConfig statusConfig = this.mDisabledState;
        return statusConfig == null || verifyVersions(statusConfig.getDeciderVersions());
    }

    public boolean isHandsFreeActive() {
        Integer voxAppBuildCode = this.mAppInfoProviderLazy.mo358get().getVoxAppBuildCode();
        String str = TAG;
        Log.d(str, "Vox app version " + voxAppBuildCode);
        StatusConfig statusConfig = this.mDisabledState;
        if (statusConfig == null) {
            Log.d(TAG, "The disabledState from remote Falco config file was not set. Hands-free remains active.");
            return true;
        }
        List<AppVersions> handsFreeVersions = statusConfig.getHandsFreeVersions();
        if (handsFreeVersions != null && !handsFreeVersions.isEmpty()) {
            for (AppVersions appVersions : handsFreeVersions) {
                boolean verifyVersions = verifyVersions(Collections.singletonList(appVersions.getLocalAppVersions()));
                boolean equals = Objects.equals(voxAppBuildCode, appVersions.getVoxAppVersion());
                if (!verifyVersions && equals) {
                    Log.d(TAG, "Hands-free was remotely disabled.");
                    return false;
                }
            }
            Log.d(TAG, "Hands-free is active.");
            return true;
        }
        Log.d(TAG, "No disabled app versions were set in remote Falco config file. Hands-free remains active.");
        return true;
    }

    public boolean isLockScreenActive() {
        StatusConfig statusConfig = this.mDisabledState;
        return statusConfig == null || verifyVersions(statusConfig.getLockScreenVersions());
    }

    public boolean isMetricsActive() {
        StatusConfig statusConfig = this.mDisabledState;
        return statusConfig == null || verifyVersions(statusConfig.getMetricsVersions());
    }

    public boolean isNotificationsActive() {
        StatusConfig statusConfig = this.mDisabledState;
        return statusConfig == null || verifyVersions(statusConfig.getNotificationsVersions());
    }

    public boolean isSettingsActive() {
        StatusConfig statusConfig = this.mDisabledState;
        return statusConfig == null || verifyVersions(statusConfig.getSettingsVersions());
    }

    public boolean turnOnHandsFree() {
        return !this.mHandsFreeStateProvider.getPreviousHandsFreeState().booleanValue() && isHandsFreeActive() && this.mRemoteConfigProvider.getRemoteConfig() != null;
    }

    @VisibleForTesting
    boolean verifyMinimumSupportedLocalAppVersions(@Nullable Integer num, @Nullable Integer num2) {
        LocalAppVersions minimumSupportedLocalAppVersion;
        HandsFreeSetupProperties handsFreeSetupProperties = this.mHandsFreeSetupProperties;
        if (handsFreeSetupProperties == null || handsFreeSetupProperties.getMinimumSupportedLocalAppVersion() == null || (minimumSupportedLocalAppVersion = this.mHandsFreeSetupProperties.getMinimumSupportedLocalAppVersion()) == null) {
            return true;
        }
        Integer dspAppVersion = minimumSupportedLocalAppVersion.getDspAppVersion();
        Integer falcoVersion = minimumSupportedLocalAppVersion.getFalcoVersion();
        if (dspAppVersion != null && num != null && dspAppVersion.intValue() > num.intValue()) {
            Log.e(TAG, "verifyMinimumSupportedVersions: DSP App version not supported");
            return false;
        }
        if (falcoVersion != null && num2 != null && falcoVersion.intValue() > num2.intValue()) {
            Log.e(TAG, "verifyMinimumSupportedVersions: Falco version not supported");
            return false;
        }
        return true;
    }
}
