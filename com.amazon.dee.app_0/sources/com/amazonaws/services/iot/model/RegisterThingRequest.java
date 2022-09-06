package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class RegisterThingRequest extends AmazonWebServiceRequest implements Serializable {
    private Map<String, String> parameters;
    private String templateBody;

    public RegisterThingRequest addparametersEntry(String str, String str2) {
        if (this.parameters == null) {
            this.parameters = new HashMap();
        }
        if (!this.parameters.containsKey(str)) {
            this.parameters.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public RegisterThingRequest clearparametersEntries() {
        this.parameters = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RegisterThingRequest)) {
            return false;
        }
        RegisterThingRequest registerThingRequest = (RegisterThingRequest) obj;
        if ((registerThingRequest.getTemplateBody() == null) ^ (getTemplateBody() == null)) {
            return false;
        }
        if (registerThingRequest.getTemplateBody() != null && !registerThingRequest.getTemplateBody().equals(getTemplateBody())) {
            return false;
        }
        if ((registerThingRequest.getParameters() == null) ^ (getParameters() == null)) {
            return false;
        }
        return registerThingRequest.getParameters() == null || registerThingRequest.getParameters().equals(getParameters());
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public String getTemplateBody() {
        return this.templateBody;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getTemplateBody() == null ? 0 : getTemplateBody().hashCode()) + 31) * 31;
        if (getParameters() != null) {
            i = getParameters().hashCode();
        }
        return hashCode + i;
    }

    public void setParameters(Map<String, String> map) {
        this.parameters = map;
    }

    public void setTemplateBody(String str) {
        this.templateBody = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTemplateBody() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("templateBody: ");
            outline1072.append(getTemplateBody());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getParameters() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("parameters: ");
            outline1073.append(getParameters());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public RegisterThingRequest withParameters(Map<String, String> map) {
        this.parameters = map;
        return this;
    }

    public RegisterThingRequest withTemplateBody(String str) {
        this.templateBody = str;
        return this;
    }
}
