package com.amazon.alexa.api;

import android.os.Bundle;
import com.amazon.alexa.utils.validation.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class Versions {
    public static final AlexaApiVersion V1_0_0 = AlexaApiVersion.create("1.0.0");
    public static final AlexaApiVersion V1_1_0 = AlexaApiVersion.create("1.1.0");
    public static final AlexaApiVersion V2_0_0 = AlexaApiVersion.create("2.0.0");
    public static final AlexaApiVersion V2_1_0 = AlexaApiVersion.create("2.1.0");
    public static final AlexaApiVersion V2_2_0 = AlexaApiVersion.create("2.2.0");
    public static final AlexaApiVersion V2_2_1 = AlexaApiVersion.create("2.2.1");
    public static final AlexaApiVersion V2_3_0 = AlexaApiVersion.create("2.3.0");
    public static final AlexaApiVersion V2_3_1 = AlexaApiVersion.create("2.3.1");
    public static final AlexaApiVersion V2_4_0 = AlexaApiVersion.create("2.4.0");
    public static final AlexaApiVersion CURRENT_API_VERSION = V2_4_0;

    private Versions() {
    }

    public static AlexaApiVersion getVersion(Bundle bundle) {
        Preconditions.notNull(bundle, "bundle is null");
        return Bundles.getClient(bundle).getVersion();
    }

    public static boolean isPayloadSupportedByVersion(Bundle bundle, AlexaApiVersion alexaApiVersion) {
        return getVersion(bundle).isAtLeast(alexaApiVersion);
    }
}
