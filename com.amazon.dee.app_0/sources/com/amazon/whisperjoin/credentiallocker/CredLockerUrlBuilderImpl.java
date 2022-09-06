package com.amazon.whisperjoin.credentiallocker;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
/* loaded from: classes13.dex */
class CredLockerUrlBuilderImpl implements CredLockerUrlBuilder {
    private static final String V1_CRED_LOCKER_PATH = "/credentiallocker/v1";
    private final CredLockerEndpointResolver endpointResolver;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CredLockerUrlBuilderImpl(CredLockerEndpointResolver credLockerEndpointResolver) {
        this.endpointResolver = credLockerEndpointResolver;
    }

    @Override // com.amazon.whisperjoin.credentiallocker.CredLockerUrlBuilder
    public URL getWifiConfigurationsUrl() throws IOException {
        return new URL(this.endpointResolver.getEndpoint(), String.format(Locale.ENGLISH, "%s/wifiConfigurations?utf8Supported=true", V1_CRED_LOCKER_PATH));
    }

    @Override // com.amazon.whisperjoin.credentiallocker.CredLockerUrlBuilder
    public URL getWifiConfigurationsUrl(String str, String str2) throws IOException {
        return new URL(this.endpointResolver.getEndpoint(), String.format(Locale.ENGLISH, "%s/wifiConfigurations?deviceId=%s;deviceType=%s;utf8Supported=true", V1_CRED_LOCKER_PATH, str, str2));
    }
}
