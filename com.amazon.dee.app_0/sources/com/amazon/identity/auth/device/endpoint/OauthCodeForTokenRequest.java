package com.amazon.identity.auth.device.endpoint;

import android.content.Context;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;
/* loaded from: classes12.dex */
class OauthCodeForTokenRequest extends AbstractOauthTokenRequest<OauthCodeForTokenResponse> {
    protected static final String AUTHORIZATION_CODE_GRANT = "authorization_code";
    protected static final String AUTHORIZATION_CODE_PARAM = "code";
    private static final String LOG_TAG = "com.amazon.identity.auth.device.endpoint.OauthCodeForTokenRequest";
    protected static final String REDIRECT_URI_PARAM = "redirect_uri";
    private final String mCode;
    private final String mCodeVerifier;
    private final String mDirectedId;
    private final String mRedirectUri;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OauthCodeForTokenRequest(String str, String str2, String str3, String str4, AppInfo appInfo, Context context) throws AuthError {
        super(context, appInfo);
        this.mCode = str;
        this.mRedirectUri = str3;
        this.mDirectedId = str4;
        this.mCodeVerifier = str2;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractOauthTokenRequest
    protected List<BasicNameValuePair> getExtraOauthTokenRequestParameters() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("code", this.mCode));
        arrayList.add(new BasicNameValuePair(REDIRECT_URI_PARAM, this.mRedirectUri));
        arrayList.add(new BasicNameValuePair(AccountConstants.KEY_CODE_VERIFIER, this.mCodeVerifier));
        return arrayList;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractOauthTokenRequest
    public String getGrantType() {
        return "authorization_code";
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected void logRequest() {
        String str = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Executing OAuth Code for Token Exchange. redirectUri=");
        outline107.append(this.mRedirectUri);
        outline107.append(" appId=");
        outline107.append(getAppFamilyId());
        String sb = outline107.toString();
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("code=");
        outline1072.append(this.mCode);
        MAPLog.pii(str, sb, outline1072.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    /* renamed from: generateResponse */
    public OauthCodeForTokenResponse mo4066generateResponse(HttpResponse httpResponse) {
        return new OauthCodeForTokenResponse(httpResponse, getAppFamilyId(), this.mDirectedId);
    }
}
