package com.amazon.identity.auth.device.endpoint;

import android.content.Context;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.token.RefreshAtzToken;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;
/* loaded from: classes12.dex */
class OauthTokenRequest extends AbstractOauthTokenRequest<OauthTokenResponse> {
    private static final String LOG_TAG = "com.amazon.identity.auth.device.endpoint.OauthTokenRequest";
    protected static final String REFRESH_TOKEN = "refresh_token";
    protected static final String REFRESH_TOKEN_GRANT = "refresh_token";
    private final RefreshAtzToken mRefreshToken;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OauthTokenRequest(Context context, RefreshAtzToken refreshAtzToken, AppInfo appInfo) throws AuthError {
        super(context, appInfo);
        this.mRefreshToken = refreshAtzToken;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractOauthTokenRequest
    protected List<BasicNameValuePair> getExtraOauthTokenRequestParameters() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair(AbstractJSONTokenResponse.REFRESH_TOKEN, this.mRefreshToken.toString()));
        return arrayList;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractOauthTokenRequest
    public String getGrantType() {
        return AbstractJSONTokenResponse.REFRESH_TOKEN;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected void logRequest() {
        String str = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Executing OAuth access token exchange. appId=");
        outline107.append(getAppFamilyId());
        String sb = outline107.toString();
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("refreshAtzToken=");
        outline1072.append(this.mRefreshToken.toString());
        MAPLog.pii(str, sb, outline1072.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    /* renamed from: generateResponse */
    public OauthTokenResponse mo4066generateResponse(HttpResponse httpResponse) {
        return new OauthTokenResponse(httpResponse, getAppFamilyId(), null);
    }
}
