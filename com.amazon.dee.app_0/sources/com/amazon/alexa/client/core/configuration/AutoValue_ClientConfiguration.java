package com.amazon.alexa.client.core.configuration;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes6.dex */
final class AutoValue_ClientConfiguration extends ClientConfiguration {
    private final Boolean ECAV2;
    private final Set<Locale> actualSupportedLocales;
    private final String alexaProfileOverride;
    private final Uri avsEndpoint;
    private final String awsMobileAnalyticsApplicationId;
    private final String awsMobileAnalyticsIdentityPoolId;
    private final String backupWakeWordModelAssetFileName;
    private final Uri capabilitiesEndpoint;
    private final String datamartNamespace;
    private final Set<Feature> enabledFeatures;
    private final Set<ExperimentalLocale> experimentalLocales;
    private final Long firstTurnNetworkTotalWriteTimeout;
    private final Boolean hardcodedCapabilitiesDisabled;
    private final Boolean locationPermissionsAllowed;
    private final Long maxUtteranceDuration;
    private final String metricsDeviceType;
    private final String metricsServiceName;
    private final String mobilyticsKinesisCognitoIdentityPoolId;
    private final String mobilyticsKinesisRegion;
    private final String mobilyticsKinesisStreamName;
    private final Boolean multiWwEnabled;
    private final Long networkTotalWriteTimeout;
    private final Long networkWriteBytesTimeout;
    private final Long playbackResumingTimeout;
    private final Boolean removeNotificationOnTeardown;
    private final Stage stage;
    private final Set<List<Locale>> supportedLocaleCombinations;
    private final Set<Locale> supportedLocales;
    private final Boolean supportsPfmChanged;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static final class Builder extends ClientConfiguration.Builder {
        private Boolean ECAV2;
        private Set<Locale> actualSupportedLocales;
        private String alexaProfileOverride;
        private Uri avsEndpoint;
        private String awsMobileAnalyticsApplicationId;
        private String awsMobileAnalyticsIdentityPoolId;
        private String backupWakeWordModelAssetFileName;
        private Uri capabilitiesEndpoint;
        private String datamartNamespace;
        private Set<Feature> enabledFeatures;
        private Set<ExperimentalLocale> experimentalLocales;
        private Long firstTurnNetworkTotalWriteTimeout;
        private Boolean hardcodedCapabilitiesDisabled;
        private Boolean locationPermissionsAllowed;
        private Long maxUtteranceDuration;
        private String metricsDeviceType;
        private String metricsServiceName;
        private String mobilyticsKinesisCognitoIdentityPoolId;
        private String mobilyticsKinesisRegion;
        private String mobilyticsKinesisStreamName;
        private Boolean multiWwEnabled;
        private Long networkTotalWriteTimeout;
        private Long networkWriteBytesTimeout;
        private Long playbackResumingTimeout;
        private Boolean removeNotificationOnTeardown;
        private Stage stage;
        private Set<List<Locale>> supportedLocaleCombinations;
        private Set<Locale> supportedLocales;
        private Boolean supportsPfmChanged;

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        ClientConfiguration autoBuild() {
            return new AutoValue_ClientConfiguration(this.enabledFeatures, this.stage, this.avsEndpoint, this.capabilitiesEndpoint, this.supportedLocales, this.supportedLocaleCombinations, this.actualSupportedLocales, this.experimentalLocales, this.metricsDeviceType, this.ECAV2, this.networkTotalWriteTimeout, this.firstTurnNetworkTotalWriteTimeout, this.networkWriteBytesTimeout, this.maxUtteranceDuration, this.awsMobileAnalyticsApplicationId, this.awsMobileAnalyticsIdentityPoolId, this.mobilyticsKinesisStreamName, this.mobilyticsKinesisCognitoIdentityPoolId, this.mobilyticsKinesisRegion, this.playbackResumingTimeout, this.removeNotificationOnTeardown, this.metricsServiceName, this.backupWakeWordModelAssetFileName, this.alexaProfileOverride, this.locationPermissionsAllowed, this.multiWwEnabled, this.hardcodedCapabilitiesDisabled, this.datamartNamespace, this.supportsPfmChanged);
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        @Nullable
        Set<Locale> getActualSupportedLocales() {
            return this.actualSupportedLocales;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        @Nullable
        Set<Feature> getEnabledFeatures() {
            return this.enabledFeatures;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        @Nullable
        Set<ExperimentalLocale> getExperimentalLocales() {
            return this.experimentalLocales;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        @Nullable
        Set<List<Locale>> getSupportedLocaleCombinations() {
            return this.supportedLocaleCombinations;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        @Nullable
        Set<Locale> getSupportedLocales() {
            return this.supportedLocales;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setActualSupportedLocales(@Nullable Set<Locale> set) {
            this.actualSupportedLocales = set;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setAlexaProfileOverride(@Nullable String str) {
            this.alexaProfileOverride = str;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setAvsEndpoint(@Nullable Uri uri) {
            this.avsEndpoint = uri;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setAwsMobileAnalyticsApplicationId(@Nullable String str) {
            this.awsMobileAnalyticsApplicationId = str;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setAwsMobileAnalyticsIdentityPoolId(@Nullable String str) {
            this.awsMobileAnalyticsIdentityPoolId = str;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setBackupWakeWordModelAssetFileName(@Nullable String str) {
            this.backupWakeWordModelAssetFileName = str;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setCapabilitiesEndpoint(@Nullable Uri uri) {
            this.capabilitiesEndpoint = uri;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setDatamartNamespace(@Nullable String str) {
            this.datamartNamespace = str;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setECAV2(@Nullable Boolean bool) {
            this.ECAV2 = bool;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setEnabledFeatures(@Nullable Set<Feature> set) {
            this.enabledFeatures = set;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setExperimentalLocales(@Nullable Set<ExperimentalLocale> set) {
            this.experimentalLocales = set;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setFirstTurnNetworkTotalWriteTimeout(@Nullable Long l) {
            this.firstTurnNetworkTotalWriteTimeout = l;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setHardcodedCapabilitiesDisabled(@Nullable Boolean bool) {
            this.hardcodedCapabilitiesDisabled = bool;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setLocationPermissionsAllowed(@Nullable Boolean bool) {
            this.locationPermissionsAllowed = bool;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setMaxUtteranceDuration(@Nullable Long l) {
            this.maxUtteranceDuration = l;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setMetricsDeviceType(@Nullable String str) {
            this.metricsDeviceType = str;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setMetricsServiceName(@Nullable String str) {
            this.metricsServiceName = str;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setMobilyticsKinesisCognitoIdentityPoolId(@Nullable String str) {
            this.mobilyticsKinesisCognitoIdentityPoolId = str;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setMobilyticsKinesisRegion(@Nullable String str) {
            this.mobilyticsKinesisRegion = str;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setMobilyticsKinesisStreamName(@Nullable String str) {
            this.mobilyticsKinesisStreamName = str;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setMultiWwEnabled(@Nullable Boolean bool) {
            this.multiWwEnabled = bool;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setNetworkTotalWriteTimeout(@Nullable Long l) {
            this.networkTotalWriteTimeout = l;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setNetworkWriteBytesTimeout(@Nullable Long l) {
            this.networkWriteBytesTimeout = l;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setPlaybackResumingTimeout(@Nullable Long l) {
            this.playbackResumingTimeout = l;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setRemoveNotificationOnTeardown(@Nullable Boolean bool) {
            this.removeNotificationOnTeardown = bool;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setStage(@Nullable Stage stage) {
            this.stage = stage;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setSupportedLocaleCombinations(@Nullable Set<List<Locale>> set) {
            this.supportedLocaleCombinations = set;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setSupportedLocales(@Nullable Set<Locale> set) {
            this.supportedLocales = set;
            return this;
        }

        @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration.Builder
        public ClientConfiguration.Builder setSupportsPfmChanged(@Nullable Boolean bool) {
            this.supportsPfmChanged = bool;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClientConfiguration)) {
            return false;
        }
        ClientConfiguration clientConfiguration = (ClientConfiguration) obj;
        Set<Feature> set = this.enabledFeatures;
        if (set != null ? set.equals(clientConfiguration.getEnabledFeatures()) : clientConfiguration.getEnabledFeatures() == null) {
            Stage stage = this.stage;
            if (stage != null ? stage.equals(clientConfiguration.getStage()) : clientConfiguration.getStage() == null) {
                Uri uri = this.avsEndpoint;
                if (uri != null ? uri.equals(clientConfiguration.getAvsEndpoint()) : clientConfiguration.getAvsEndpoint() == null) {
                    Uri uri2 = this.capabilitiesEndpoint;
                    if (uri2 != null ? uri2.equals(clientConfiguration.getCapabilitiesEndpoint()) : clientConfiguration.getCapabilitiesEndpoint() == null) {
                        Set<Locale> set2 = this.supportedLocales;
                        if (set2 != null ? set2.equals(clientConfiguration.getSupportedLocales()) : clientConfiguration.getSupportedLocales() == null) {
                            Set<List<Locale>> set3 = this.supportedLocaleCombinations;
                            if (set3 != null ? set3.equals(clientConfiguration.getSupportedLocaleCombinations()) : clientConfiguration.getSupportedLocaleCombinations() == null) {
                                Set<Locale> set4 = this.actualSupportedLocales;
                                if (set4 != null ? set4.equals(clientConfiguration.getActualSupportedLocales()) : clientConfiguration.getActualSupportedLocales() == null) {
                                    Set<ExperimentalLocale> set5 = this.experimentalLocales;
                                    if (set5 != null ? set5.equals(clientConfiguration.getExperimentalLocales()) : clientConfiguration.getExperimentalLocales() == null) {
                                        String str = this.metricsDeviceType;
                                        if (str != null ? str.equals(clientConfiguration.getMetricsDeviceType()) : clientConfiguration.getMetricsDeviceType() == null) {
                                            Boolean bool = this.ECAV2;
                                            if (bool != null ? bool.equals(clientConfiguration.getECAV2()) : clientConfiguration.getECAV2() == null) {
                                                Long l = this.networkTotalWriteTimeout;
                                                if (l != null ? l.equals(clientConfiguration.getNetworkTotalWriteTimeout()) : clientConfiguration.getNetworkTotalWriteTimeout() == null) {
                                                    Long l2 = this.firstTurnNetworkTotalWriteTimeout;
                                                    if (l2 != null ? l2.equals(clientConfiguration.getFirstTurnNetworkTotalWriteTimeout()) : clientConfiguration.getFirstTurnNetworkTotalWriteTimeout() == null) {
                                                        Long l3 = this.networkWriteBytesTimeout;
                                                        if (l3 != null ? l3.equals(clientConfiguration.getNetworkWriteBytesTimeout()) : clientConfiguration.getNetworkWriteBytesTimeout() == null) {
                                                            Long l4 = this.maxUtteranceDuration;
                                                            if (l4 != null ? l4.equals(clientConfiguration.getMaxUtteranceDuration()) : clientConfiguration.getMaxUtteranceDuration() == null) {
                                                                String str2 = this.awsMobileAnalyticsApplicationId;
                                                                if (str2 != null ? str2.equals(clientConfiguration.getAwsMobileAnalyticsApplicationId()) : clientConfiguration.getAwsMobileAnalyticsApplicationId() == null) {
                                                                    String str3 = this.awsMobileAnalyticsIdentityPoolId;
                                                                    if (str3 != null ? str3.equals(clientConfiguration.getAwsMobileAnalyticsIdentityPoolId()) : clientConfiguration.getAwsMobileAnalyticsIdentityPoolId() == null) {
                                                                        String str4 = this.mobilyticsKinesisStreamName;
                                                                        if (str4 != null ? str4.equals(clientConfiguration.getMobilyticsKinesisStreamName()) : clientConfiguration.getMobilyticsKinesisStreamName() == null) {
                                                                            String str5 = this.mobilyticsKinesisCognitoIdentityPoolId;
                                                                            if (str5 != null ? str5.equals(clientConfiguration.getMobilyticsKinesisCognitoIdentityPoolId()) : clientConfiguration.getMobilyticsKinesisCognitoIdentityPoolId() == null) {
                                                                                String str6 = this.mobilyticsKinesisRegion;
                                                                                if (str6 != null ? str6.equals(clientConfiguration.getMobilyticsKinesisRegion()) : clientConfiguration.getMobilyticsKinesisRegion() == null) {
                                                                                    Long l5 = this.playbackResumingTimeout;
                                                                                    if (l5 != null ? l5.equals(clientConfiguration.getPlaybackResumingTimeout()) : clientConfiguration.getPlaybackResumingTimeout() == null) {
                                                                                        Boolean bool2 = this.removeNotificationOnTeardown;
                                                                                        if (bool2 != null ? bool2.equals(clientConfiguration.getRemoveNotificationOnTeardown()) : clientConfiguration.getRemoveNotificationOnTeardown() == null) {
                                                                                            String str7 = this.metricsServiceName;
                                                                                            if (str7 != null ? str7.equals(clientConfiguration.getMetricsServiceName()) : clientConfiguration.getMetricsServiceName() == null) {
                                                                                                String str8 = this.backupWakeWordModelAssetFileName;
                                                                                                if (str8 != null ? str8.equals(clientConfiguration.getBackupWakeWordModelAssetFileName()) : clientConfiguration.getBackupWakeWordModelAssetFileName() == null) {
                                                                                                    String str9 = this.alexaProfileOverride;
                                                                                                    if (str9 != null ? str9.equals(clientConfiguration.getAlexaProfileOverride()) : clientConfiguration.getAlexaProfileOverride() == null) {
                                                                                                        Boolean bool3 = this.locationPermissionsAllowed;
                                                                                                        if (bool3 != null ? bool3.equals(clientConfiguration.getLocationPermissionsAllowed()) : clientConfiguration.getLocationPermissionsAllowed() == null) {
                                                                                                            Boolean bool4 = this.multiWwEnabled;
                                                                                                            if (bool4 != null ? bool4.equals(clientConfiguration.getMultiWwEnabled()) : clientConfiguration.getMultiWwEnabled() == null) {
                                                                                                                Boolean bool5 = this.hardcodedCapabilitiesDisabled;
                                                                                                                if (bool5 != null ? bool5.equals(clientConfiguration.getHardcodedCapabilitiesDisabled()) : clientConfiguration.getHardcodedCapabilitiesDisabled() == null) {
                                                                                                                    String str10 = this.datamartNamespace;
                                                                                                                    if (str10 != null ? str10.equals(clientConfiguration.getDatamartNamespace()) : clientConfiguration.getDatamartNamespace() == null) {
                                                                                                                        Boolean bool6 = this.supportsPfmChanged;
                                                                                                                        if (bool6 == null) {
                                                                                                                            if (clientConfiguration.getSupportsPfmChanged() == null) {
                                                                                                                                return true;
                                                                                                                            }
                                                                                                                        } else if (bool6.equals(clientConfiguration.getSupportsPfmChanged())) {
                                                                                                                            return true;
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Set<Locale> getActualSupportedLocales() {
        return this.actualSupportedLocales;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public String getAlexaProfileOverride() {
        return this.alexaProfileOverride;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Uri getAvsEndpoint() {
        return this.avsEndpoint;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public String getAwsMobileAnalyticsApplicationId() {
        return this.awsMobileAnalyticsApplicationId;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public String getAwsMobileAnalyticsIdentityPoolId() {
        return this.awsMobileAnalyticsIdentityPoolId;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public String getBackupWakeWordModelAssetFileName() {
        return this.backupWakeWordModelAssetFileName;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Uri getCapabilitiesEndpoint() {
        return this.capabilitiesEndpoint;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public String getDatamartNamespace() {
        return this.datamartNamespace;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Boolean getECAV2() {
        return this.ECAV2;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Set<Feature> getEnabledFeatures() {
        return this.enabledFeatures;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Set<ExperimentalLocale> getExperimentalLocales() {
        return this.experimentalLocales;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Long getFirstTurnNetworkTotalWriteTimeout() {
        return this.firstTurnNetworkTotalWriteTimeout;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Boolean getHardcodedCapabilitiesDisabled() {
        return this.hardcodedCapabilitiesDisabled;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Boolean getLocationPermissionsAllowed() {
        return this.locationPermissionsAllowed;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Long getMaxUtteranceDuration() {
        return this.maxUtteranceDuration;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public String getMetricsDeviceType() {
        return this.metricsDeviceType;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public String getMetricsServiceName() {
        return this.metricsServiceName;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public String getMobilyticsKinesisCognitoIdentityPoolId() {
        return this.mobilyticsKinesisCognitoIdentityPoolId;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public String getMobilyticsKinesisRegion() {
        return this.mobilyticsKinesisRegion;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public String getMobilyticsKinesisStreamName() {
        return this.mobilyticsKinesisStreamName;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Boolean getMultiWwEnabled() {
        return this.multiWwEnabled;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Long getNetworkTotalWriteTimeout() {
        return this.networkTotalWriteTimeout;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Long getNetworkWriteBytesTimeout() {
        return this.networkWriteBytesTimeout;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Long getPlaybackResumingTimeout() {
        return this.playbackResumingTimeout;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Boolean getRemoveNotificationOnTeardown() {
        return this.removeNotificationOnTeardown;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Stage getStage() {
        return this.stage;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Set<List<Locale>> getSupportedLocaleCombinations() {
        return this.supportedLocaleCombinations;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Set<Locale> getSupportedLocales() {
        return this.supportedLocales;
    }

    @Override // com.amazon.alexa.client.core.configuration.ClientConfiguration
    @Nullable
    public Boolean getSupportsPfmChanged() {
        return this.supportsPfmChanged;
    }

    public int hashCode() {
        Set<Feature> set = this.enabledFeatures;
        int i = 0;
        int hashCode = ((set == null ? 0 : set.hashCode()) ^ 1000003) * 1000003;
        Stage stage = this.stage;
        int hashCode2 = (hashCode ^ (stage == null ? 0 : stage.hashCode())) * 1000003;
        Uri uri = this.avsEndpoint;
        int hashCode3 = (hashCode2 ^ (uri == null ? 0 : uri.hashCode())) * 1000003;
        Uri uri2 = this.capabilitiesEndpoint;
        int hashCode4 = (hashCode3 ^ (uri2 == null ? 0 : uri2.hashCode())) * 1000003;
        Set<Locale> set2 = this.supportedLocales;
        int hashCode5 = (hashCode4 ^ (set2 == null ? 0 : set2.hashCode())) * 1000003;
        Set<List<Locale>> set3 = this.supportedLocaleCombinations;
        int hashCode6 = (hashCode5 ^ (set3 == null ? 0 : set3.hashCode())) * 1000003;
        Set<Locale> set4 = this.actualSupportedLocales;
        int hashCode7 = (hashCode6 ^ (set4 == null ? 0 : set4.hashCode())) * 1000003;
        Set<ExperimentalLocale> set5 = this.experimentalLocales;
        int hashCode8 = (hashCode7 ^ (set5 == null ? 0 : set5.hashCode())) * 1000003;
        String str = this.metricsDeviceType;
        int hashCode9 = (hashCode8 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        Boolean bool = this.ECAV2;
        int hashCode10 = (hashCode9 ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        Long l = this.networkTotalWriteTimeout;
        int hashCode11 = (hashCode10 ^ (l == null ? 0 : l.hashCode())) * 1000003;
        Long l2 = this.firstTurnNetworkTotalWriteTimeout;
        int hashCode12 = (hashCode11 ^ (l2 == null ? 0 : l2.hashCode())) * 1000003;
        Long l3 = this.networkWriteBytesTimeout;
        int hashCode13 = (hashCode12 ^ (l3 == null ? 0 : l3.hashCode())) * 1000003;
        Long l4 = this.maxUtteranceDuration;
        int hashCode14 = (hashCode13 ^ (l4 == null ? 0 : l4.hashCode())) * 1000003;
        String str2 = this.awsMobileAnalyticsApplicationId;
        int hashCode15 = (hashCode14 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.awsMobileAnalyticsIdentityPoolId;
        int hashCode16 = (hashCode15 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        String str4 = this.mobilyticsKinesisStreamName;
        int hashCode17 = (hashCode16 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        String str5 = this.mobilyticsKinesisCognitoIdentityPoolId;
        int hashCode18 = (hashCode17 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        String str6 = this.mobilyticsKinesisRegion;
        int hashCode19 = (hashCode18 ^ (str6 == null ? 0 : str6.hashCode())) * 1000003;
        Long l5 = this.playbackResumingTimeout;
        int hashCode20 = (hashCode19 ^ (l5 == null ? 0 : l5.hashCode())) * 1000003;
        Boolean bool2 = this.removeNotificationOnTeardown;
        int hashCode21 = (hashCode20 ^ (bool2 == null ? 0 : bool2.hashCode())) * 1000003;
        String str7 = this.metricsServiceName;
        int hashCode22 = (hashCode21 ^ (str7 == null ? 0 : str7.hashCode())) * 1000003;
        String str8 = this.backupWakeWordModelAssetFileName;
        int hashCode23 = (hashCode22 ^ (str8 == null ? 0 : str8.hashCode())) * 1000003;
        String str9 = this.alexaProfileOverride;
        int hashCode24 = (hashCode23 ^ (str9 == null ? 0 : str9.hashCode())) * 1000003;
        Boolean bool3 = this.locationPermissionsAllowed;
        int hashCode25 = (hashCode24 ^ (bool3 == null ? 0 : bool3.hashCode())) * 1000003;
        Boolean bool4 = this.multiWwEnabled;
        int hashCode26 = (hashCode25 ^ (bool4 == null ? 0 : bool4.hashCode())) * 1000003;
        Boolean bool5 = this.hardcodedCapabilitiesDisabled;
        int hashCode27 = (hashCode26 ^ (bool5 == null ? 0 : bool5.hashCode())) * 1000003;
        String str10 = this.datamartNamespace;
        int hashCode28 = (hashCode27 ^ (str10 == null ? 0 : str10.hashCode())) * 1000003;
        Boolean bool6 = this.supportsPfmChanged;
        if (bool6 != null) {
            i = bool6.hashCode();
        }
        return hashCode28 ^ i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ClientConfiguration{enabledFeatures=");
        outline107.append(this.enabledFeatures);
        outline107.append(", stage=");
        outline107.append(this.stage);
        outline107.append(", avsEndpoint=");
        outline107.append(this.avsEndpoint);
        outline107.append(", capabilitiesEndpoint=");
        outline107.append(this.capabilitiesEndpoint);
        outline107.append(", supportedLocales=");
        outline107.append(this.supportedLocales);
        outline107.append(", supportedLocaleCombinations=");
        outline107.append(this.supportedLocaleCombinations);
        outline107.append(", actualSupportedLocales=");
        outline107.append(this.actualSupportedLocales);
        outline107.append(", experimentalLocales=");
        outline107.append(this.experimentalLocales);
        outline107.append(", metricsDeviceType=");
        outline107.append(this.metricsDeviceType);
        outline107.append(", ECAV2=");
        outline107.append(this.ECAV2);
        outline107.append(", networkTotalWriteTimeout=");
        outline107.append(this.networkTotalWriteTimeout);
        outline107.append(", firstTurnNetworkTotalWriteTimeout=");
        outline107.append(this.firstTurnNetworkTotalWriteTimeout);
        outline107.append(", networkWriteBytesTimeout=");
        outline107.append(this.networkWriteBytesTimeout);
        outline107.append(", maxUtteranceDuration=");
        outline107.append(this.maxUtteranceDuration);
        outline107.append(", awsMobileAnalyticsApplicationId=");
        outline107.append(this.awsMobileAnalyticsApplicationId);
        outline107.append(", awsMobileAnalyticsIdentityPoolId=");
        outline107.append(this.awsMobileAnalyticsIdentityPoolId);
        outline107.append(", mobilyticsKinesisStreamName=");
        outline107.append(this.mobilyticsKinesisStreamName);
        outline107.append(", mobilyticsKinesisCognitoIdentityPoolId=");
        outline107.append(this.mobilyticsKinesisCognitoIdentityPoolId);
        outline107.append(", mobilyticsKinesisRegion=");
        outline107.append(this.mobilyticsKinesisRegion);
        outline107.append(", playbackResumingTimeout=");
        outline107.append(this.playbackResumingTimeout);
        outline107.append(", removeNotificationOnTeardown=");
        outline107.append(this.removeNotificationOnTeardown);
        outline107.append(", metricsServiceName=");
        outline107.append(this.metricsServiceName);
        outline107.append(", backupWakeWordModelAssetFileName=");
        outline107.append(this.backupWakeWordModelAssetFileName);
        outline107.append(", alexaProfileOverride=");
        outline107.append(this.alexaProfileOverride);
        outline107.append(", locationPermissionsAllowed=");
        outline107.append(this.locationPermissionsAllowed);
        outline107.append(", multiWwEnabled=");
        outline107.append(this.multiWwEnabled);
        outline107.append(", hardcodedCapabilitiesDisabled=");
        outline107.append(this.hardcodedCapabilitiesDisabled);
        outline107.append(", datamartNamespace=");
        outline107.append(this.datamartNamespace);
        outline107.append(", supportsPfmChanged=");
        outline107.append(this.supportsPfmChanged);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    private AutoValue_ClientConfiguration(@Nullable Set<Feature> set, @Nullable Stage stage, @Nullable Uri uri, @Nullable Uri uri2, @Nullable Set<Locale> set2, @Nullable Set<List<Locale>> set3, @Nullable Set<Locale> set4, @Nullable Set<ExperimentalLocale> set5, @Nullable String str, @Nullable Boolean bool, @Nullable Long l, @Nullable Long l2, @Nullable Long l3, @Nullable Long l4, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable Long l5, @Nullable Boolean bool2, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable Boolean bool3, @Nullable Boolean bool4, @Nullable Boolean bool5, @Nullable String str10, @Nullable Boolean bool6) {
        this.enabledFeatures = set;
        this.stage = stage;
        this.avsEndpoint = uri;
        this.capabilitiesEndpoint = uri2;
        this.supportedLocales = set2;
        this.supportedLocaleCombinations = set3;
        this.actualSupportedLocales = set4;
        this.experimentalLocales = set5;
        this.metricsDeviceType = str;
        this.ECAV2 = bool;
        this.networkTotalWriteTimeout = l;
        this.firstTurnNetworkTotalWriteTimeout = l2;
        this.networkWriteBytesTimeout = l3;
        this.maxUtteranceDuration = l4;
        this.awsMobileAnalyticsApplicationId = str2;
        this.awsMobileAnalyticsIdentityPoolId = str3;
        this.mobilyticsKinesisStreamName = str4;
        this.mobilyticsKinesisCognitoIdentityPoolId = str5;
        this.mobilyticsKinesisRegion = str6;
        this.playbackResumingTimeout = l5;
        this.removeNotificationOnTeardown = bool2;
        this.metricsServiceName = str7;
        this.backupWakeWordModelAssetFileName = str8;
        this.alexaProfileOverride = str9;
        this.locationPermissionsAllowed = bool3;
        this.multiWwEnabled = bool4;
        this.hardcodedCapabilitiesDisabled = bool5;
        this.datamartNamespace = str10;
        this.supportsPfmChanged = bool6;
    }
}
