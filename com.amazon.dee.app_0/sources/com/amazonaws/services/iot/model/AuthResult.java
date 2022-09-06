package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class AuthResult implements Serializable {
    private Allowed allowed;
    private String authDecision;
    private AuthInfo authInfo;
    private Denied denied;
    private List<String> missingContextValues;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuthResult)) {
            return false;
        }
        AuthResult authResult = (AuthResult) obj;
        if ((authResult.getAuthInfo() == null) ^ (getAuthInfo() == null)) {
            return false;
        }
        if (authResult.getAuthInfo() != null && !authResult.getAuthInfo().equals(getAuthInfo())) {
            return false;
        }
        if ((authResult.getAllowed() == null) ^ (getAllowed() == null)) {
            return false;
        }
        if (authResult.getAllowed() != null && !authResult.getAllowed().equals(getAllowed())) {
            return false;
        }
        if ((authResult.getDenied() == null) ^ (getDenied() == null)) {
            return false;
        }
        if (authResult.getDenied() != null && !authResult.getDenied().equals(getDenied())) {
            return false;
        }
        if ((authResult.getAuthDecision() == null) ^ (getAuthDecision() == null)) {
            return false;
        }
        if (authResult.getAuthDecision() != null && !authResult.getAuthDecision().equals(getAuthDecision())) {
            return false;
        }
        if ((authResult.getMissingContextValues() == null) ^ (getMissingContextValues() == null)) {
            return false;
        }
        return authResult.getMissingContextValues() == null || authResult.getMissingContextValues().equals(getMissingContextValues());
    }

    public Allowed getAllowed() {
        return this.allowed;
    }

    public String getAuthDecision() {
        return this.authDecision;
    }

    public AuthInfo getAuthInfo() {
        return this.authInfo;
    }

    public Denied getDenied() {
        return this.denied;
    }

    public List<String> getMissingContextValues() {
        return this.missingContextValues;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getAuthInfo() == null ? 0 : getAuthInfo().hashCode()) + 31) * 31) + (getAllowed() == null ? 0 : getAllowed().hashCode())) * 31) + (getDenied() == null ? 0 : getDenied().hashCode())) * 31) + (getAuthDecision() == null ? 0 : getAuthDecision().hashCode())) * 31;
        if (getMissingContextValues() != null) {
            i = getMissingContextValues().hashCode();
        }
        return hashCode + i;
    }

    public void setAllowed(Allowed allowed) {
        this.allowed = allowed;
    }

    public void setAuthDecision(String str) {
        this.authDecision = str;
    }

    public void setAuthInfo(AuthInfo authInfo) {
        this.authInfo = authInfo;
    }

    public void setDenied(Denied denied) {
        this.denied = denied;
    }

    public void setMissingContextValues(Collection<String> collection) {
        if (collection == null) {
            this.missingContextValues = null;
        } else {
            this.missingContextValues = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getAuthInfo() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("authInfo: ");
            outline1072.append(getAuthInfo());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getAllowed() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("allowed: ");
            outline1073.append(getAllowed());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getDenied() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("denied: ");
            outline1074.append(getDenied());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getAuthDecision() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("authDecision: ");
            outline1075.append(getAuthDecision());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getMissingContextValues() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("missingContextValues: ");
            outline1076.append(getMissingContextValues());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AuthResult withAllowed(Allowed allowed) {
        this.allowed = allowed;
        return this;
    }

    public AuthResult withAuthDecision(String str) {
        this.authDecision = str;
        return this;
    }

    public AuthResult withAuthInfo(AuthInfo authInfo) {
        this.authInfo = authInfo;
        return this;
    }

    public AuthResult withDenied(Denied denied) {
        this.denied = denied;
        return this;
    }

    public AuthResult withMissingContextValues(String... strArr) {
        if (getMissingContextValues() == null) {
            this.missingContextValues = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.missingContextValues.add(str);
        }
        return this;
    }

    public void setAuthDecision(AuthDecision authDecision) {
        this.authDecision = authDecision.toString();
    }

    public AuthResult withAuthDecision(AuthDecision authDecision) {
        this.authDecision = authDecision.toString();
        return this;
    }

    public AuthResult withMissingContextValues(Collection<String> collection) {
        setMissingContextValues(collection);
        return this;
    }
}
