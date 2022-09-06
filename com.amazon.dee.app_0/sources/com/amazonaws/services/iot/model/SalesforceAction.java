package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class SalesforceAction implements Serializable {
    private String token;
    private String url;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SalesforceAction)) {
            return false;
        }
        SalesforceAction salesforceAction = (SalesforceAction) obj;
        if ((salesforceAction.getToken() == null) ^ (getToken() == null)) {
            return false;
        }
        if (salesforceAction.getToken() != null && !salesforceAction.getToken().equals(getToken())) {
            return false;
        }
        if ((salesforceAction.getUrl() == null) ^ (getUrl() == null)) {
            return false;
        }
        return salesforceAction.getUrl() == null || salesforceAction.getUrl().equals(getUrl());
    }

    public String getToken() {
        return this.token;
    }

    public String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getToken() == null ? 0 : getToken().hashCode()) + 31) * 31;
        if (getUrl() != null) {
            i = getUrl().hashCode();
        }
        return hashCode + i;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getToken() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("token: ");
            outline1072.append(getToken());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getUrl() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("url: ");
            outline1073.append(getUrl());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public SalesforceAction withToken(String str) {
        this.token = str;
        return this;
    }

    public SalesforceAction withUrl(String str) {
        this.url = str;
        return this;
    }
}
