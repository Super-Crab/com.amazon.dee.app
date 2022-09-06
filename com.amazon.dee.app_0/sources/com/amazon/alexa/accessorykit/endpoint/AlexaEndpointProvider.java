package com.amazon.alexa.accessorykit.endpoint;

import com.amazon.alexa.accessory.internal.EndpointProvider;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.interprocess.environment.AccessoryEnvironmentService;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public class AlexaEndpointProvider implements EndpointProvider {
    private static final String DEVICE_ACCOUNT_PATH = "/api/devices-v2/";
    private static final String DEVICE_ACCOUNT_PATH_V1 = "/api/v1/devices/";
    private static final String LOCATION_ENABLEMENT_KEY = "/settings/locationEnablement?domain=FindMy";
    private static final String REPORT_LOCATIONS_API_PATH = "/v1/locations/report";
    private final AccessoryEnvironmentService accessoryEnvironmentService;

    public AlexaEndpointProvider(AccessoryEnvironmentService accessoryEnvironmentService) {
        Preconditions.notNull(accessoryEnvironmentService, "accessoryEnvironmentService");
        this.accessoryEnvironmentService = accessoryEnvironmentService;
    }

    @Override // com.amazon.alexa.accessory.internal.EndpointProvider
    public String getAlexaApiEndpoint() {
        String apiGatewayEndpoint = this.accessoryEnvironmentService.getApiGatewayEndpoint();
        if (apiGatewayEndpoint != null) {
            return apiGatewayEndpoint;
        }
        throw new RuntimeException("AlexaApiEndpoint: Not a valid endpoint");
    }

    @Override // com.amazon.alexa.accessory.internal.EndpointProvider
    public String getDeviceAccountApiEndpoint(String str, String str2) {
        Preconditions.notEmpty(str, "deviceType");
        Preconditions.notEmpty(str2, "deviceSerialNumber");
        String webEndpoint = this.accessoryEnvironmentService.getWebEndpoint();
        if (webEndpoint != null) {
            return GeneratedOutlineSupport1.outline77(webEndpoint, DEVICE_ACCOUNT_PATH, str, "/", str2);
        }
        throw new RuntimeException("DeviceAPI endpoint: Not a valid endpoint");
    }

    public String getLocationEnablementApiEndpoint(String str) {
        String webEndpoint = this.accessoryEnvironmentService.getWebEndpoint();
        if (webEndpoint != null) {
            return GeneratedOutlineSupport1.outline76(webEndpoint, DEVICE_ACCOUNT_PATH_V1, str, LOCATION_ENABLEMENT_KEY);
        }
        throw new RuntimeException("LocationAPI endpoint: Not a valid endpoint");
    }

    public String getReportLocationsEndpoint() {
        String apiGatewayEndpoint = this.accessoryEnvironmentService.getApiGatewayEndpoint();
        if (apiGatewayEndpoint != null) {
            return GeneratedOutlineSupport1.outline72(apiGatewayEndpoint, REPORT_LOCATIONS_API_PATH);
        }
        throw new RuntimeException("LocationAPI endpoint: Not a valid endpoint");
    }
}
