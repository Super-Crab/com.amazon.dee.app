package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetTopicRuleRequest extends AmazonWebServiceRequest implements Serializable {
    private String ruleName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetTopicRuleRequest)) {
            return false;
        }
        GetTopicRuleRequest getTopicRuleRequest = (GetTopicRuleRequest) obj;
        if ((getTopicRuleRequest.getRuleName() == null) ^ (getRuleName() == null)) {
            return false;
        }
        return getTopicRuleRequest.getRuleName() == null || getTopicRuleRequest.getRuleName().equals(getRuleName());
    }

    public String getRuleName() {
        return this.ruleName;
    }

    public int hashCode() {
        return 31 + (getRuleName() == null ? 0 : getRuleName().hashCode());
    }

    public void setRuleName(String str) {
        this.ruleName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRuleName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("ruleName: ");
            outline1072.append(getRuleName());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetTopicRuleRequest withRuleName(String str) {
        this.ruleName = str;
        return this;
    }
}
