package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class RegisterThingResult implements Serializable {
    private String certificatePem;
    private Map<String, String> resourceArns;

    public RegisterThingResult addresourceArnsEntry(String str, String str2) {
        if (this.resourceArns == null) {
            this.resourceArns = new HashMap();
        }
        if (!this.resourceArns.containsKey(str)) {
            this.resourceArns.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public RegisterThingResult clearresourceArnsEntries() {
        this.resourceArns = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RegisterThingResult)) {
            return false;
        }
        RegisterThingResult registerThingResult = (RegisterThingResult) obj;
        if ((registerThingResult.getCertificatePem() == null) ^ (getCertificatePem() == null)) {
            return false;
        }
        if (registerThingResult.getCertificatePem() != null && !registerThingResult.getCertificatePem().equals(getCertificatePem())) {
            return false;
        }
        if ((registerThingResult.getResourceArns() == null) ^ (getResourceArns() == null)) {
            return false;
        }
        return registerThingResult.getResourceArns() == null || registerThingResult.getResourceArns().equals(getResourceArns());
    }

    public String getCertificatePem() {
        return this.certificatePem;
    }

    public Map<String, String> getResourceArns() {
        return this.resourceArns;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getCertificatePem() == null ? 0 : getCertificatePem().hashCode()) + 31) * 31;
        if (getResourceArns() != null) {
            i = getResourceArns().hashCode();
        }
        return hashCode + i;
    }

    public void setCertificatePem(String str) {
        this.certificatePem = str;
    }

    public void setResourceArns(Map<String, String> map) {
        this.resourceArns = map;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCertificatePem() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("certificatePem: ");
            outline1072.append(getCertificatePem());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getResourceArns() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("resourceArns: ");
            outline1073.append(getResourceArns());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public RegisterThingResult withCertificatePem(String str) {
        this.certificatePem = str;
        return this;
    }

    public RegisterThingResult withResourceArns(Map<String, String> map) {
        this.resourceArns = map;
        return this;
    }
}
