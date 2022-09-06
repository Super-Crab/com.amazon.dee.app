package com.amazon.deecomms.common.auth;

import android.text.TextUtils;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.IHttpClient;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class MAPAuthorizationProvider implements IHttpClient.AuthHeaderProvider {
    private static final String AUTH_HEADER_KEY = "Authorization";
    private static final String AUTH_SCHEME = "map";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, EPMSAuthorizationProvider.class.getSimpleName());
    private String authenticateWithCommsId;
    private IdentityService identityService;

    public MAPAuthorizationProvider(String str, String str2) {
        this.authenticateWithCommsId = str;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.AuthHeaderProvider
    public Map<String, String> getAuthHeaders(String str, boolean z) {
        HashMap hashMap = new HashMap();
        String accessToken = this.identityService.getAccessToken(MAPAuthorizationProvider.class.getName());
        if (accessToken != null && !TextUtils.isEmpty(accessToken)) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("MAP Token received = ");
            outline1.append(LOG.sensitive(accessToken));
            commsLogger.d(outline1.toString());
            hashMap.put("Authorization", "map " + accessToken);
            return hashMap;
        }
        LOG.e("Cannot fetch mapToken from IdentityService. Auth Token is NULL");
        return hashMap;
    }

    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }
}
