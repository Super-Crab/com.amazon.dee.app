package com.amazon.alexa.drive.smart.device.data;

import java.net.MalformedURLException;
import java.net.URL;
/* loaded from: classes7.dex */
public final class SmartDeviceEndpoint {
    public static final String ALEXA_AMAZON_ENDPOINT = "https://alexa.amazon.com";
    private static final String GET_GUARD_MODE_API = "/api/smarthome/security/alexagui/v1/guard/mode";
    private static final String HAS_GUARD_API = "/api/smarthome/security/v1/guard/existing";
    private static final String NEXUS_V_1_GRAPHQL = "/nexus/v1/graphql";

    private SmartDeviceEndpoint() {
    }

    public static URL getGuardModeEndpoint() throws MalformedURLException {
        return new URL("https://alexa.amazon.com/api/smarthome/security/alexagui/v1/guard/mode");
    }

    public static URL getHasGuardEndpoint() throws MalformedURLException {
        return new URL("https://alexa.amazon.com/api/smarthome/security/v1/guard/existing");
    }

    public static URL getLockApi() throws MalformedURLException {
        return new URL("https://alexa.amazon.com/nexus/v1/graphql");
    }
}
