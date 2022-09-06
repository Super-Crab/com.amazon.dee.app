package com.amazon.deecomms.commscore.integration;

import com.amazon.alexa.enrollment.ui.terms.EnrollmentTermsViewFragment;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.identity.CommsCoreIdentity;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.metrics.CounterMetric;
import com.amazon.commscore.api.metrics.TimerMetric;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.amazon.deecomms.api.CommsDynamicFeature;
import com.amazon.deecomms.core.FeatureFlagManager;
import com.here.sdk.search.PlaceCategory;
import java.util.HashMap;
/* loaded from: classes12.dex */
public class CommsCoreLog {
    private static final String METRIC_PREFIX = "CommCoreLogTest";
    private static final String SUBCOMPONENT = "CommsCoreLog";
    private AlexaCommsCoreRemoteConfigurationService configurationService;
    private FeatureFlagManager featureFlagManager;
    private AlexaCommsCoreIdentityService identityService;
    private AlexaCommsCoreMetricsService metricsService;

    public CommsCoreLog(AlexaCommsCoreMetricsService alexaCommsCoreMetricsService, AlexaCommsCoreIdentityService alexaCommsCoreIdentityService, AlexaCommsCoreRemoteConfigurationService alexaCommsCoreRemoteConfigurationService, FeatureFlagManager featureFlagManager) {
        this.metricsService = alexaCommsCoreMetricsService;
        this.identityService = alexaCommsCoreIdentityService;
        this.configurationService = alexaCommsCoreRemoteConfigurationService;
        this.featureFlagManager = featureFlagManager;
    }

    private void buildLog(StringBuilder sb, String str, String str2) {
        sb.append(str + ":" + str2);
        sb.append("\n");
    }

    public void logAllData() {
        this.featureFlagManager.setFeature(CommsDynamicFeature.COMMS_CORE_METRICS_SERVICE, false);
        logMetrics();
        logIdentity();
        logRemoteConfig();
    }

    public void logIdentity() {
        StringBuilder sb = new StringBuilder();
        CommsCoreIdentity identity = this.identityService.getIdentity();
        buildLog(sb, "DirectedId", identity.getDirectedId());
        buildLog(sb, "CommsId", identity.getCommsId());
        buildLog(sb, "Cor", identity.getCountryOfResidence());
        buildLog(sb, "FirstName", identity.getName().getFirstName());
        buildLog(sb, "PCE ID", identity.getPceId());
        buildLog(sb, "PersonId V2", identity.getPersonIdV2());
        buildLog(sb, "MP Country Code", identity.getMarketplaceInfo().getMarketPlaceCountryCode());
        buildLog(sb, "PFM Code", identity.getMarketplaceInfo().getPfmCode());
        sb.toString();
    }

    public void logMetrics() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("statusCode", PlaceCategory.EAT_AND_DRINK);
        hashMap.put("messageType", "text");
        TimerMetric createTimer = this.metricsService.createTimer("CommCoreLogTest:timer", SUBCOMPONENT, hashMap);
        CounterMetric createCounter = this.metricsService.createCounter("CommCoreLogTest:counter", SUBCOMPONENT, hashMap);
        createCounter.incrementCounter();
        this.metricsService.recordCounter(createCounter);
        this.metricsService.recordOccurrence("CommCoreLogTest:occurrence", SUBCOMPONENT, true, hashMap);
        this.metricsService.recordWarningEvent("CommCoreLogTest:warning", "Test error: log test", SUBCOMPONENT, new Exception("Test exception"));
        this.metricsService.recordTimer(createTimer);
    }

    public void logRemoteConfig() {
        String remoteConfigValue = this.configurationService.getRemoteConfiguration("ACMS.Endpoints.PFM.US", EnrollmentTermsViewFragment.DEFAULT_ENDPOINT).toString();
        String str = "ACMS.Endpoints.PFM.US: " + remoteConfigValue;
        String str2 = "Invalid.Key: " + this.configurationService.getRemoteConfiguration("Invalid.Key", "DefaultValue").toString();
    }
}
