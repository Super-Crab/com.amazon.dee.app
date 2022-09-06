package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class TestAuthorizationResult implements Serializable {
    private List<AuthResult> authResults;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TestAuthorizationResult)) {
            return false;
        }
        TestAuthorizationResult testAuthorizationResult = (TestAuthorizationResult) obj;
        if ((testAuthorizationResult.getAuthResults() == null) ^ (getAuthResults() == null)) {
            return false;
        }
        return testAuthorizationResult.getAuthResults() == null || testAuthorizationResult.getAuthResults().equals(getAuthResults());
    }

    public List<AuthResult> getAuthResults() {
        return this.authResults;
    }

    public int hashCode() {
        return 31 + (getAuthResults() == null ? 0 : getAuthResults().hashCode());
    }

    public void setAuthResults(Collection<AuthResult> collection) {
        if (collection == null) {
            this.authResults = null;
        } else {
            this.authResults = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getAuthResults() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("authResults: ");
            outline1072.append(getAuthResults());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public TestAuthorizationResult withAuthResults(AuthResult... authResultArr) {
        if (getAuthResults() == null) {
            this.authResults = new ArrayList(authResultArr.length);
        }
        for (AuthResult authResult : authResultArr) {
            this.authResults.add(authResult);
        }
        return this;
    }

    public TestAuthorizationResult withAuthResults(Collection<AuthResult> collection) {
        setAuthResults(collection);
        return this;
    }
}
