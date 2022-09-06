package com.amazon.identity.auth.device.endpoint;

import android.content.Context;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.endpoint.PandaResponse;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.message.BasicNameValuePair;
/* loaded from: classes12.dex */
public abstract class AbstractOauthTokenRequest<T extends PandaResponse> extends AbstractPandaRequest<T> {
    private static final String APP_CLIENT_ID_PARAM = "client_id";
    protected static final String GRANT_TYPE_PARAM = "grant_type";
    protected static final String OAUTH_END_POINT = "/auth/o2/token";
    private final String appFamilyId;
    private final String clientId;

    public AbstractOauthTokenRequest(Context context, AppInfo appInfo) throws AuthError {
        super(context, appInfo);
        if (appInfo != null) {
            this.appFamilyId = appInfo.getAppFamilyId();
            this.clientId = appInfo.getClientId();
            return;
        }
        throw new AuthError("Appinfo can not be null to make an OAuthTokenRequest", AuthError.ERROR_TYPE.ERROR_UNKNOWN);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getAppFamilyId() {
        return this.appFamilyId;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected String getEndPoint() {
        return OAUTH_END_POINT;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected List<Header> getExtraHeaders() {
        return new ArrayList();
    }

    protected abstract List<BasicNameValuePair> getExtraOauthTokenRequestParameters();

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected List<BasicNameValuePair> getExtraParameters() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair(GRANT_TYPE_PARAM, getGrantType()));
        arrayList.add(new BasicNameValuePair("client_id", this.clientId));
        List<BasicNameValuePair> extraOauthTokenRequestParameters = getExtraOauthTokenRequestParameters();
        if (extraOauthTokenRequestParameters != null) {
            arrayList.addAll(extraOauthTokenRequestParameters);
        }
        return arrayList;
    }

    protected abstract String getGrantType();
}
