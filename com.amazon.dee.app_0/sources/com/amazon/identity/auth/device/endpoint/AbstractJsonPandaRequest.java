package com.amazon.identity.auth.device.endpoint;

import android.content.Context;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.endpoint.PandaResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public abstract class AbstractJsonPandaRequest<T extends PandaResponse> extends AbstractPandaRequest<T> {
    private static final String APPLICATION_JSON_CONTENT_TYPE = "application/json";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";

    public AbstractJsonPandaRequest(Context context, AppInfo appInfo) {
        super(context, appInfo);
    }

    private String getJsonString() throws AuthError {
        JSONObject jSONObject = new JSONObject();
        try {
            for (NameValuePair nameValuePair : this.postParameters) {
                jSONObject.put(nameValuePair.getName(), nameValuePair.getValue());
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            throw new AuthError("Received JSONException while building request", e, AuthError.ERROR_TYPE.ERROR_BAD_PARAM);
        }
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected List<Header> getExtraHeaders() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicHeader("Content-Type", "application/json"));
        return arrayList;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected void setEntity() throws UnsupportedEncodingException, AuthError {
        this.httpRequest.setEntity(new StringEntity(getJsonString(), "UTF-8"));
    }
}
