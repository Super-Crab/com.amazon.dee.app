package com.amazon.communication.directorservice;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class GetEndpointResponse {
    private static final String ENDPOINT_JSON_KEY = "endpointUrl";
    private static final String REQUEST_ID_JSON_KEY = "requestId";
    private final String mEndpointUrl;
    private final String mRequestId;

    public GetEndpointResponse(String str, String str2) {
        this.mRequestId = str;
        this.mEndpointUrl = str2;
    }

    public static GetEndpointResponse fromJson(JSONObject jSONObject) throws JSONException {
        return new GetEndpointResponse(jSONObject.getString("requestId"), jSONObject.getString(ENDPOINT_JSON_KEY));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetEndpointResponse.class != obj.getClass()) {
            return false;
        }
        GetEndpointResponse getEndpointResponse = (GetEndpointResponse) obj;
        String str = this.mEndpointUrl;
        if (str == null) {
            if (getEndpointResponse.mEndpointUrl != null) {
                return false;
            }
        } else if (!str.equals(getEndpointResponse.mEndpointUrl)) {
            return false;
        }
        String str2 = this.mRequestId;
        if (str2 == null) {
            if (getEndpointResponse.mRequestId != null) {
                return false;
            }
        } else if (!str2.equals(getEndpointResponse.mRequestId)) {
            return false;
        }
        return true;
    }

    public String getEndpointUrl() {
        return this.mEndpointUrl;
    }

    public String getRequestId() {
        return this.mRequestId;
    }

    public int hashCode() {
        String str = this.mEndpointUrl;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.mRequestId;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return String.format("requestId = %s, endpointUrl = %s", this.mRequestId, this.mEndpointUrl);
    }
}
