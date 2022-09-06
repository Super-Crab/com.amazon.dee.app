package com.amazon.alexa.drive.navigation.location;

import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Optional;
/* loaded from: classes7.dex */
public class AlexaLocationEndpointFactory {
    private static final String GET_CUSTOMER_ADDRESSES_API_PATH = "/v1/alexa-address/persons/%s/addresses";
    private static final String NA_PROD_ALEXA_ENDPOINT = "https://api.amazonalexa.com";
    private static final String REPORT_LOCATIONS_API_PATH = "/v1/locations/report";
    private static final String TAG = "AlexaLocationEndpointFactory";

    private String getEndPoint() {
        Optional optional = ComponentRegistry.getInstance().get(EnvironmentService.class);
        return optional.isPresent() ? ((EnvironmentService) optional.get()).getApiGatewayEndpoint() : "https://api.amazonalexa.com";
    }

    public String getCustomerAddressEndPoint(String str) {
        return getEndPoint() + String.format(GET_CUSTOMER_ADDRESSES_API_PATH, str);
    }

    public String getReportLocationsEndpoint() {
        return GeneratedOutlineSupport1.outline91(new StringBuilder(), getEndPoint(), REPORT_LOCATIONS_API_PATH);
    }
}
