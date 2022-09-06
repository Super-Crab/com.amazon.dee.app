package com.amazon.identity.auth.device.endpoint;

import android.content.Context;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;
/* loaded from: classes12.dex */
public class LogoutRequest extends AbstractJsonPandaRequest<LogoutResponse> {
    private static final String BEARER = "bearer";
    private static final String LOGOUT_ENDPOINT = "/auth/relyingPartyLogout";
    private static final String LOG_TAG = "com.amazon.identity.auth.device.endpoint.LogoutRequest";
    private static final String TOKEN = "token";
    private static final String TOKEN_TYPE = "token_type";
    private String authzToken;

    public LogoutRequest(Context context, AppInfo appInfo, String str) {
        super(context, appInfo);
        this.authzToken = str;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected String getEndPoint() {
        return LOGOUT_ENDPOINT;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected List<BasicNameValuePair> getExtraParameters() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("token_type", "bearer"));
        arrayList.add(new BasicNameValuePair("token", this.authzToken));
        return arrayList;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected void logRequest() {
        String str = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("accessToken=");
        outline107.append(this.authzToken);
        MAPLog.pii(str, "Executing logout request", outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    /* renamed from: generateResponse */
    public LogoutResponse mo4066generateResponse(HttpResponse httpResponse) {
        return new LogoutResponse(httpResponse);
    }
}
