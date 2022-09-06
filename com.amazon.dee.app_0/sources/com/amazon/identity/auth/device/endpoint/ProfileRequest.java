package com.amazon.identity.auth.device.endpoint;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
/* loaded from: classes12.dex */
public class ProfileRequest extends AbstractPandaGetRequest<ProfileResponse> {
    protected static final String API_PREFIX_DEVO_SANDBOX = "api-sandbox.integ";
    protected static final String API_PREFIX_PRE_PROD_SANDBOX = "api.sandbox";
    protected static final String API_PREFIX_PROD_SANDBOX = "api.sandbox";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String LOG_TAG = "com.amazon.identity.auth.device.endpoint.ProfileRequest";
    private static final String PROFILE_ENDPOINT = "/user/profile";
    private String authzToken;
    private boolean sandboxMode;

    public ProfileRequest(Bundle bundle, String str, Context context, AppInfo appInfo) {
        super(context, appInfo);
        this.authzToken = str;
        if (bundle != null) {
            this.sandboxMode = bundle.getBoolean(AuthzConstants.BUNDLE_KEY.SANDBOX.val, false);
        }
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected String getEndPoint() {
        return PROFILE_ENDPOINT;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected List<Header> getExtraHeaders() {
        ArrayList arrayList = new ArrayList();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(BEARER_PREFIX);
        outline107.append(this.authzToken);
        arrayList.add(new BasicHeader("Authorization", outline107.toString()));
        return arrayList;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected List<BasicNameValuePair> getExtraParameters() {
        return new ArrayList();
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected boolean isSandboxEnabled() {
        return this.sandboxMode;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected void logRequest() {
        String str = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("accessToken=");
        outline107.append(this.authzToken);
        MAPLog.pii(str, "Executing profile request", outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    /* renamed from: generateResponse  reason: collision with other method in class */
    public ProfileResponse mo4066generateResponse(HttpResponse httpResponse) {
        return new ProfileResponse(httpResponse);
    }
}
