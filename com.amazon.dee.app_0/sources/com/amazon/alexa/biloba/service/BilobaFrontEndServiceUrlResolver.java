package com.amazon.alexa.biloba.service;

import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class BilobaFrontEndServiceUrlResolver {
    private static final String ALPHA_ENDPOINT = "us-west-2.beta.frontend.care.alexa.amazon.dev";
    private static final String ALPHA_KEY = "alpha";
    private static final String BETA_ENDPOINT = "us-west-2.beta.frontend.care.alexa.amazon.dev";
    private static final String BETA_KEY = "beta";
    private static final String DEFAULT_ENDPOINT = "us-west-2.beta.frontend.care.alexa.amazon.dev";
    private static final String GAMMA_ENDPOINT = "us-west-2.beta.frontend.care.alexa.amazon.dev";
    private static final String GAMMA_KEY = "gamma";
    private static final String PROD_ENDPOINT = "us-west-2.beta.frontend.care.alexa.amazon.dev";
    private static final String PROD_KEY = "prod";
    private static final String TAG = "AlexaPhotosUrlResolver";
    private static final Map<String, String> urlConfiguration = new HashMap();
    private LazyComponent<EnvironmentService> environmentService;

    static {
        urlConfiguration.put("alpha", "us-west-2.beta.frontend.care.alexa.amazon.dev");
        urlConfiguration.put("beta", "us-west-2.beta.frontend.care.alexa.amazon.dev");
        urlConfiguration.put("gamma", "us-west-2.beta.frontend.care.alexa.amazon.dev");
        urlConfiguration.put("prod", "us-west-2.beta.frontend.care.alexa.amazon.dev");
    }

    BilobaFrontEndServiceUrlResolver(LazyComponent<EnvironmentService> lazyComponent) {
        this.environmentService = lazyComponent;
    }

    public String resolve() {
        if (this.environmentService.mo10268get() == null) {
            return "us-west-2.beta.frontend.care.alexa.amazon.dev";
        }
        String str = urlConfiguration.get(this.environmentService.mo10268get().getBuildStage());
        return str == null ? "us-west-2.beta.frontend.care.alexa.amazon.dev" : str;
    }

    public BilobaFrontEndServiceUrlResolver() {
        this.environmentService = ComponentRegistry.getInstance().getLazy(EnvironmentService.class);
    }
}
