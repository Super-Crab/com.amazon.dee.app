package com.amazon.alexa.mobilytics.integration.ama;

import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.identity.MobilyticsEndpointPicker;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import java.util.Map;
/* loaded from: classes9.dex */
public class DefaultMobilyticsEndpointPicker implements MobilyticsEndpointPicker {
    private static final String DEFAULT_REGION = "us-east-1";
    private EnvironmentService environmentService;

    public DefaultMobilyticsEndpointPicker() {
        this.environmentService = null;
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        if (componentRegistry != null) {
            this.environmentService = (EnvironmentService) componentRegistry.getLazy(EnvironmentService.class).mo10268get();
        }
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsEndpointPicker
    public <T> T getEndpoint(@NonNull Map<String, T> map) {
        EnvironmentService environmentService = this.environmentService;
        if (environmentService != null) {
            return (T) environmentService.getMobilyticsEndpoint(map);
        }
        return map.get("us-east-1");
    }
}
