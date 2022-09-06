package com.amazon.commsnetworking.auth;

import androidx.annotation.NonNull;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commsnetworking.Constants;
import com.amazon.commsnetworking.api.INetworkRequest;
import com.amazon.commsnetworking.util.TextUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
/* loaded from: classes12.dex */
public class MSSAuthenticationProvider implements AuthenticationProvider {
    static final String AUTH_HEADER_KEY = "Authorization";
    static final String AUTH_SCHEME = "map";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MSSAuthenticationProvider.class);
    private static final String METRIC_PREFIX = "comms.net.auth.mss";
    private IdentityService identityService;
    private AlexaCommsCoreMetricsService metricsService;
    private String source;

    public MSSAuthenticationProvider(@NonNull String str, @NonNull IdentityService identityService, @NonNull AlexaCommsCoreMetricsService alexaCommsCoreMetricsService) {
        this.source = str;
        this.identityService = identityService;
        this.metricsService = alexaCommsCoreMetricsService;
    }

    @Override // com.amazon.commsnetworking.auth.AuthenticationProvider
    public void authenticateRequest(@NonNull INetworkRequest iNetworkRequest) {
        String accessToken = this.identityService.getAccessToken(MSSAuthenticationProvider.class.getSimpleName());
        if (!TextUtils.isEmpty(accessToken)) {
            this.metricsService.recordOccurrence("comms.net.auth.mss.success", this.source, true, new HashMap());
            iNetworkRequest.withHeader("Authorization", "map " + accessToken);
            return;
        }
        CommsLogger commsLogger = LOG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[");
        outline107.append(this.source);
        outline107.append("] mapToken is empty");
        commsLogger.e(outline107.toString());
        HashMap hashMap = new HashMap();
        hashMap.put("errorSource", "mapToken is empty");
        this.metricsService.recordOccurrence("comms.net.auth.mss.fail", this.source, true, hashMap);
    }
}
