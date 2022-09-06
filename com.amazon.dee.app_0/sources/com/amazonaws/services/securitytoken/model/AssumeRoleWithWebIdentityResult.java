package com.amazonaws.services.securitytoken.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class AssumeRoleWithWebIdentityResult implements Serializable {
    private AssumedRoleUser assumedRoleUser;
    private String audience;
    private Credentials credentials;
    private Integer packedPolicySize;
    private String provider;
    private String subjectFromWebIdentityToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AssumeRoleWithWebIdentityResult)) {
            return false;
        }
        AssumeRoleWithWebIdentityResult assumeRoleWithWebIdentityResult = (AssumeRoleWithWebIdentityResult) obj;
        if ((assumeRoleWithWebIdentityResult.getCredentials() == null) ^ (getCredentials() == null)) {
            return false;
        }
        if (assumeRoleWithWebIdentityResult.getCredentials() != null && !assumeRoleWithWebIdentityResult.getCredentials().equals(getCredentials())) {
            return false;
        }
        if ((assumeRoleWithWebIdentityResult.getSubjectFromWebIdentityToken() == null) ^ (getSubjectFromWebIdentityToken() == null)) {
            return false;
        }
        if (assumeRoleWithWebIdentityResult.getSubjectFromWebIdentityToken() != null && !assumeRoleWithWebIdentityResult.getSubjectFromWebIdentityToken().equals(getSubjectFromWebIdentityToken())) {
            return false;
        }
        if ((assumeRoleWithWebIdentityResult.getAssumedRoleUser() == null) ^ (getAssumedRoleUser() == null)) {
            return false;
        }
        if (assumeRoleWithWebIdentityResult.getAssumedRoleUser() != null && !assumeRoleWithWebIdentityResult.getAssumedRoleUser().equals(getAssumedRoleUser())) {
            return false;
        }
        if ((assumeRoleWithWebIdentityResult.getPackedPolicySize() == null) ^ (getPackedPolicySize() == null)) {
            return false;
        }
        if (assumeRoleWithWebIdentityResult.getPackedPolicySize() != null && !assumeRoleWithWebIdentityResult.getPackedPolicySize().equals(getPackedPolicySize())) {
            return false;
        }
        if ((assumeRoleWithWebIdentityResult.getProvider() == null) ^ (getProvider() == null)) {
            return false;
        }
        if (assumeRoleWithWebIdentityResult.getProvider() != null && !assumeRoleWithWebIdentityResult.getProvider().equals(getProvider())) {
            return false;
        }
        if ((assumeRoleWithWebIdentityResult.getAudience() == null) ^ (getAudience() == null)) {
            return false;
        }
        return assumeRoleWithWebIdentityResult.getAudience() == null || assumeRoleWithWebIdentityResult.getAudience().equals(getAudience());
    }

    public AssumedRoleUser getAssumedRoleUser() {
        return this.assumedRoleUser;
    }

    public String getAudience() {
        return this.audience;
    }

    public Credentials getCredentials() {
        return this.credentials;
    }

    public Integer getPackedPolicySize() {
        return this.packedPolicySize;
    }

    public String getProvider() {
        return this.provider;
    }

    public String getSubjectFromWebIdentityToken() {
        return this.subjectFromWebIdentityToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getCredentials() == null ? 0 : getCredentials().hashCode()) + 31) * 31) + (getSubjectFromWebIdentityToken() == null ? 0 : getSubjectFromWebIdentityToken().hashCode())) * 31) + (getAssumedRoleUser() == null ? 0 : getAssumedRoleUser().hashCode())) * 31) + (getPackedPolicySize() == null ? 0 : getPackedPolicySize().hashCode())) * 31) + (getProvider() == null ? 0 : getProvider().hashCode())) * 31;
        if (getAudience() != null) {
            i = getAudience().hashCode();
        }
        return hashCode + i;
    }

    public void setAssumedRoleUser(AssumedRoleUser assumedRoleUser) {
        this.assumedRoleUser = assumedRoleUser;
    }

    public void setAudience(String str) {
        this.audience = str;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public void setPackedPolicySize(Integer num) {
        this.packedPolicySize = num;
    }

    public void setProvider(String str) {
        this.provider = str;
    }

    public void setSubjectFromWebIdentityToken(String str) {
        this.subjectFromWebIdentityToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCredentials() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Credentials: ");
            outline1072.append(getCredentials());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getSubjectFromWebIdentityToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("SubjectFromWebIdentityToken: ");
            outline1073.append(getSubjectFromWebIdentityToken());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getAssumedRoleUser() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("AssumedRoleUser: ");
            outline1074.append(getAssumedRoleUser());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getPackedPolicySize() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("PackedPolicySize: ");
            outline1075.append(getPackedPolicySize());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getProvider() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("Provider: ");
            outline1076.append(getProvider());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getAudience() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("Audience: ");
            outline1077.append(getAudience());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AssumeRoleWithWebIdentityResult withAssumedRoleUser(AssumedRoleUser assumedRoleUser) {
        this.assumedRoleUser = assumedRoleUser;
        return this;
    }

    public AssumeRoleWithWebIdentityResult withAudience(String str) {
        this.audience = str;
        return this;
    }

    public AssumeRoleWithWebIdentityResult withCredentials(Credentials credentials) {
        this.credentials = credentials;
        return this;
    }

    public AssumeRoleWithWebIdentityResult withPackedPolicySize(Integer num) {
        this.packedPolicySize = num;
        return this;
    }

    public AssumeRoleWithWebIdentityResult withProvider(String str) {
        this.provider = str;
        return this;
    }

    public AssumeRoleWithWebIdentityResult withSubjectFromWebIdentityToken(String str) {
        this.subjectFromWebIdentityToken = str;
        return this;
    }
}
