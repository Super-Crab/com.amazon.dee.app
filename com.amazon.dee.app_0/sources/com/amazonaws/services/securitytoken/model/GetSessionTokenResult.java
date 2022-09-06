package com.amazonaws.services.securitytoken.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetSessionTokenResult implements Serializable {
    private Credentials credentials;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetSessionTokenResult)) {
            return false;
        }
        GetSessionTokenResult getSessionTokenResult = (GetSessionTokenResult) obj;
        if ((getSessionTokenResult.getCredentials() == null) ^ (getCredentials() == null)) {
            return false;
        }
        return getSessionTokenResult.getCredentials() == null || getSessionTokenResult.getCredentials().equals(getCredentials());
    }

    public Credentials getCredentials() {
        return this.credentials;
    }

    public int hashCode() {
        return 31 + (getCredentials() == null ? 0 : getCredentials().hashCode());
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCredentials() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Credentials: ");
            outline1072.append(getCredentials());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetSessionTokenResult withCredentials(Credentials credentials) {
        this.credentials = credentials;
        return this;
    }
}
