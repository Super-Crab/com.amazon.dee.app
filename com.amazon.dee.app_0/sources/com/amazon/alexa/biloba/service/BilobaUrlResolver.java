package com.amazon.alexa.biloba.service;

import com.amazon.alexa.protocols.environment.EnvironmentService;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class BilobaUrlResolver {
    private static final String ALPHA_KEY = "alpha";
    private static final String BETA_KEY = "beta";
    private static final String COMMS_SETUP_ALPHA_URL = "https://aiwa-mge-pdx-pd.integ.amazon.com/groups/care/commsPrimer";
    private static final String COMMS_SETUP_BETA_URL = "https://aiwa-mge-pdx-d.integ.amazon.com/groups/care/commsPrimer";
    private static final String COMMS_SETUP_GAMMA_URL = "https://projectdee-ui-gamma.amazon.com/groups/care/commsPrimer";
    private static final String COMMS_SETUP_PROD_URL = "https://alexa.amazon.com/groups/care/commsPrimer";
    private static final String GAMMA_KEY = "gamma";
    private static final String PROD_KEY = "prod";
    private static final String TAG = "BilobaUrlResolver";
    private static final Map<String, String> commsSetupUrls = new HashMap();
    private EnvironmentService environmentService;

    static {
        commsSetupUrls.put("alpha", COMMS_SETUP_ALPHA_URL);
        commsSetupUrls.put("beta", COMMS_SETUP_BETA_URL);
        commsSetupUrls.put("gamma", COMMS_SETUP_GAMMA_URL);
        commsSetupUrls.put("prod", COMMS_SETUP_PROD_URL);
    }

    public BilobaUrlResolver(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    public String getCommsSetupUrl() {
        String buildStage = this.environmentService.getBuildStage();
        return (buildStage == null || !commsSetupUrls.containsKey(buildStage)) ? COMMS_SETUP_PROD_URL : commsSetupUrls.get(buildStage);
    }
}
