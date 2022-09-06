package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public abstract class AuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.AuthenticatedInput");
    private String accessToken;

    public boolean equals(Object obj) {
        if (!(obj instanceof AuthenticatedInput)) {
            return false;
        }
        return Helper.equals(this.accessToken, ((AuthenticatedInput) obj).accessToken);
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.accessToken);
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }
}
