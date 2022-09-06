package com.amazon.whisperjoin.credentiallocker;

import java.io.IOException;
import java.net.URL;
/* loaded from: classes13.dex */
interface CredLockerUrlBuilder {
    URL getWifiConfigurationsUrl() throws IOException;

    URL getWifiConfigurationsUrl(String str, String str2) throws IOException;
}
