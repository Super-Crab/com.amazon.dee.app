package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class InternalCreateFfsSessionTokenOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.InternalCreateFfsSessionTokenOutput");
    private String token;

    public boolean equals(Object obj) {
        if (!(obj instanceof InternalCreateFfsSessionTokenOutput)) {
            return false;
        }
        return Helper.equals(this.token, ((InternalCreateFfsSessionTokenOutput) obj).token);
    }

    public String getToken() {
        return this.token;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.token);
    }

    public void setToken(String str) {
        this.token = str;
    }
}
