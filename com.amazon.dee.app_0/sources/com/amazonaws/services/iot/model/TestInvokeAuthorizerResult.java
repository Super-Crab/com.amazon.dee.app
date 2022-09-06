package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class TestInvokeAuthorizerResult implements Serializable {
    private Integer disconnectAfterInSeconds;
    private Boolean isAuthenticated;
    private List<String> policyDocuments;
    private String principalId;
    private Integer refreshAfterInSeconds;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TestInvokeAuthorizerResult)) {
            return false;
        }
        TestInvokeAuthorizerResult testInvokeAuthorizerResult = (TestInvokeAuthorizerResult) obj;
        if ((testInvokeAuthorizerResult.getIsAuthenticated() == null) ^ (getIsAuthenticated() == null)) {
            return false;
        }
        if (testInvokeAuthorizerResult.getIsAuthenticated() != null && !testInvokeAuthorizerResult.getIsAuthenticated().equals(getIsAuthenticated())) {
            return false;
        }
        if ((testInvokeAuthorizerResult.getPrincipalId() == null) ^ (getPrincipalId() == null)) {
            return false;
        }
        if (testInvokeAuthorizerResult.getPrincipalId() != null && !testInvokeAuthorizerResult.getPrincipalId().equals(getPrincipalId())) {
            return false;
        }
        if ((testInvokeAuthorizerResult.getPolicyDocuments() == null) ^ (getPolicyDocuments() == null)) {
            return false;
        }
        if (testInvokeAuthorizerResult.getPolicyDocuments() != null && !testInvokeAuthorizerResult.getPolicyDocuments().equals(getPolicyDocuments())) {
            return false;
        }
        if ((testInvokeAuthorizerResult.getRefreshAfterInSeconds() == null) ^ (getRefreshAfterInSeconds() == null)) {
            return false;
        }
        if (testInvokeAuthorizerResult.getRefreshAfterInSeconds() != null && !testInvokeAuthorizerResult.getRefreshAfterInSeconds().equals(getRefreshAfterInSeconds())) {
            return false;
        }
        if ((testInvokeAuthorizerResult.getDisconnectAfterInSeconds() == null) ^ (getDisconnectAfterInSeconds() == null)) {
            return false;
        }
        return testInvokeAuthorizerResult.getDisconnectAfterInSeconds() == null || testInvokeAuthorizerResult.getDisconnectAfterInSeconds().equals(getDisconnectAfterInSeconds());
    }

    public Integer getDisconnectAfterInSeconds() {
        return this.disconnectAfterInSeconds;
    }

    public Boolean getIsAuthenticated() {
        return this.isAuthenticated;
    }

    public List<String> getPolicyDocuments() {
        return this.policyDocuments;
    }

    public String getPrincipalId() {
        return this.principalId;
    }

    public Integer getRefreshAfterInSeconds() {
        return this.refreshAfterInSeconds;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getIsAuthenticated() == null ? 0 : getIsAuthenticated().hashCode()) + 31) * 31) + (getPrincipalId() == null ? 0 : getPrincipalId().hashCode())) * 31) + (getPolicyDocuments() == null ? 0 : getPolicyDocuments().hashCode())) * 31) + (getRefreshAfterInSeconds() == null ? 0 : getRefreshAfterInSeconds().hashCode())) * 31;
        if (getDisconnectAfterInSeconds() != null) {
            i = getDisconnectAfterInSeconds().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isIsAuthenticated() {
        return this.isAuthenticated;
    }

    public void setDisconnectAfterInSeconds(Integer num) {
        this.disconnectAfterInSeconds = num;
    }

    public void setIsAuthenticated(Boolean bool) {
        this.isAuthenticated = bool;
    }

    public void setPolicyDocuments(Collection<String> collection) {
        if (collection == null) {
            this.policyDocuments = null;
        } else {
            this.policyDocuments = new ArrayList(collection);
        }
    }

    public void setPrincipalId(String str) {
        this.principalId = str;
    }

    public void setRefreshAfterInSeconds(Integer num) {
        this.refreshAfterInSeconds = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getIsAuthenticated() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("isAuthenticated: ");
            outline1072.append(getIsAuthenticated());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getPrincipalId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("principalId: ");
            outline1073.append(getPrincipalId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getPolicyDocuments() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("policyDocuments: ");
            outline1074.append(getPolicyDocuments());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getRefreshAfterInSeconds() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("refreshAfterInSeconds: ");
            outline1075.append(getRefreshAfterInSeconds());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getDisconnectAfterInSeconds() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("disconnectAfterInSeconds: ");
            outline1076.append(getDisconnectAfterInSeconds());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public TestInvokeAuthorizerResult withDisconnectAfterInSeconds(Integer num) {
        this.disconnectAfterInSeconds = num;
        return this;
    }

    public TestInvokeAuthorizerResult withIsAuthenticated(Boolean bool) {
        this.isAuthenticated = bool;
        return this;
    }

    public TestInvokeAuthorizerResult withPolicyDocuments(String... strArr) {
        if (getPolicyDocuments() == null) {
            this.policyDocuments = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.policyDocuments.add(str);
        }
        return this;
    }

    public TestInvokeAuthorizerResult withPrincipalId(String str) {
        this.principalId = str;
        return this;
    }

    public TestInvokeAuthorizerResult withRefreshAfterInSeconds(Integer num) {
        this.refreshAfterInSeconds = num;
        return this;
    }

    public TestInvokeAuthorizerResult withPolicyDocuments(Collection<String> collection) {
        setPolicyDocuments(collection);
        return this;
    }
}
