package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class CustomerCredentials implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.CustomerCredentials");
    private String accessToken;
    private AuthToken authToken;

    public boolean equals(Object obj) {
        if (!(obj instanceof CustomerCredentials)) {
            return false;
        }
        CustomerCredentials customerCredentials = (CustomerCredentials) obj;
        return Helper.equals(this.accessToken, customerCredentials.accessToken) && Helper.equals(this.authToken, customerCredentials.authToken);
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public AuthToken getAuthToken() {
        return this.authToken;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.accessToken, this.authToken);
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }
}
