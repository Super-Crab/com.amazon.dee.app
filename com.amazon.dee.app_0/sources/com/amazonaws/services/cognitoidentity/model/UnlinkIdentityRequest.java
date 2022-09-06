package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public class UnlinkIdentityRequest extends AmazonWebServiceRequest implements Serializable {
    private String identityId;
    private Map<String, String> logins;
    private List<String> loginsToRemove;

    public UnlinkIdentityRequest addLoginsEntry(String str, String str2) {
        if (this.logins == null) {
            this.logins = new HashMap();
        }
        if (!this.logins.containsKey(str)) {
            this.logins.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public UnlinkIdentityRequest clearLoginsEntries() {
        this.logins = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UnlinkIdentityRequest)) {
            return false;
        }
        UnlinkIdentityRequest unlinkIdentityRequest = (UnlinkIdentityRequest) obj;
        if ((unlinkIdentityRequest.getIdentityId() == null) ^ (getIdentityId() == null)) {
            return false;
        }
        if (unlinkIdentityRequest.getIdentityId() != null && !unlinkIdentityRequest.getIdentityId().equals(getIdentityId())) {
            return false;
        }
        if ((unlinkIdentityRequest.getLogins() == null) ^ (getLogins() == null)) {
            return false;
        }
        if (unlinkIdentityRequest.getLogins() != null && !unlinkIdentityRequest.getLogins().equals(getLogins())) {
            return false;
        }
        if ((unlinkIdentityRequest.getLoginsToRemove() == null) ^ (getLoginsToRemove() == null)) {
            return false;
        }
        return unlinkIdentityRequest.getLoginsToRemove() == null || unlinkIdentityRequest.getLoginsToRemove().equals(getLoginsToRemove());
    }

    public String getIdentityId() {
        return this.identityId;
    }

    public Map<String, String> getLogins() {
        return this.logins;
    }

    public List<String> getLoginsToRemove() {
        return this.loginsToRemove;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getIdentityId() == null ? 0 : getIdentityId().hashCode()) + 31) * 31) + (getLogins() == null ? 0 : getLogins().hashCode())) * 31;
        if (getLoginsToRemove() != null) {
            i = getLoginsToRemove().hashCode();
        }
        return hashCode + i;
    }

    public void setIdentityId(String str) {
        this.identityId = str;
    }

    public void setLogins(Map<String, String> map) {
        this.logins = map;
    }

    public void setLoginsToRemove(Collection<String> collection) {
        if (collection == null) {
            this.loginsToRemove = null;
        } else {
            this.loginsToRemove = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getIdentityId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("IdentityId: ");
            outline1072.append(getIdentityId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getLogins() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Logins: ");
            outline1073.append(getLogins());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getLoginsToRemove() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("LoginsToRemove: ");
            outline1074.append(getLoginsToRemove());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UnlinkIdentityRequest withIdentityId(String str) {
        this.identityId = str;
        return this;
    }

    public UnlinkIdentityRequest withLogins(Map<String, String> map) {
        this.logins = map;
        return this;
    }

    public UnlinkIdentityRequest withLoginsToRemove(String... strArr) {
        if (getLoginsToRemove() == null) {
            this.loginsToRemove = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.loginsToRemove.add(str);
        }
        return this;
    }

    public UnlinkIdentityRequest withLoginsToRemove(Collection<String> collection) {
        setLoginsToRemove(collection);
        return this;
    }
}
