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
public class OneTimeCodeRequest extends AbstractJsonPandaRequest<OneTimeCodeResponse> {
    private static final String ACCESS_TOKEN_PARAMETER = "accessToken";
    private static final String LOG_TAG = "com.amazon.identity.auth.device.endpoint.OneTimeCodeRequest";
    private static final String ONE_TIME_CODE_ENDPOINT = "/auth/create/oneTimeCode";
    private static final String WORKFLOW_CLIENT_ID_PARAMETER = "workflowClientId";
    private String accessToken;
    private String workflowClientId;

    public OneTimeCodeRequest(Context context, String str, String str2, AppInfo appInfo) {
        super(context, appInfo);
        this.workflowClientId = str;
        this.accessToken = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || OneTimeCodeRequest.class != obj.getClass()) {
            return false;
        }
        OneTimeCodeRequest oneTimeCodeRequest = (OneTimeCodeRequest) obj;
        if ((this.accessToken == null && oneTimeCodeRequest.accessToken != null) || !this.accessToken.equals(oneTimeCodeRequest.accessToken)) {
            return false;
        }
        return (this.workflowClientId != null || oneTimeCodeRequest.workflowClientId == null) && this.workflowClientId.equals(oneTimeCodeRequest.workflowClientId);
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    public String getEndPoint() {
        return ONE_TIME_CODE_ENDPOINT;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected List<BasicNameValuePair> getExtraParameters() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair(WORKFLOW_CLIENT_ID_PARAMETER, this.workflowClientId));
        arrayList.add(new BasicNameValuePair(ACCESS_TOKEN_PARAMETER, this.accessToken));
        return arrayList;
    }

    public int hashCode() {
        String str = this.accessToken;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.workflowClientId;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected void logRequest() {
        String str = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Executing create one time code request. workflowClientId=");
        outline107.append(this.workflowClientId);
        String sb = outline107.toString();
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("accessToken=");
        outline1072.append(this.accessToken);
        MAPLog.pii(str, sb, outline1072.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    /* renamed from: generateResponse */
    public OneTimeCodeResponse mo4066generateResponse(HttpResponse httpResponse) {
        return new OneTimeCodeResponse(httpResponse);
    }
}
