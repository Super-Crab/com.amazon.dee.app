package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ReplaceTopicRuleRequest extends AmazonWebServiceRequest implements Serializable {
    private String ruleName;
    private TopicRulePayload topicRulePayload;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ReplaceTopicRuleRequest)) {
            return false;
        }
        ReplaceTopicRuleRequest replaceTopicRuleRequest = (ReplaceTopicRuleRequest) obj;
        if ((replaceTopicRuleRequest.getRuleName() == null) ^ (getRuleName() == null)) {
            return false;
        }
        if (replaceTopicRuleRequest.getRuleName() != null && !replaceTopicRuleRequest.getRuleName().equals(getRuleName())) {
            return false;
        }
        if ((replaceTopicRuleRequest.getTopicRulePayload() == null) ^ (getTopicRulePayload() == null)) {
            return false;
        }
        return replaceTopicRuleRequest.getTopicRulePayload() == null || replaceTopicRuleRequest.getTopicRulePayload().equals(getTopicRulePayload());
    }

    public String getRuleName() {
        return this.ruleName;
    }

    public TopicRulePayload getTopicRulePayload() {
        return this.topicRulePayload;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getRuleName() == null ? 0 : getRuleName().hashCode()) + 31) * 31;
        if (getTopicRulePayload() != null) {
            i = getTopicRulePayload().hashCode();
        }
        return hashCode + i;
    }

    public void setRuleName(String str) {
        this.ruleName = str;
    }

    public void setTopicRulePayload(TopicRulePayload topicRulePayload) {
        this.topicRulePayload = topicRulePayload;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRuleName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("ruleName: ");
            outline1072.append(getRuleName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTopicRulePayload() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("topicRulePayload: ");
            outline1073.append(getTopicRulePayload());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ReplaceTopicRuleRequest withRuleName(String str) {
        this.ruleName = str;
        return this;
    }

    public ReplaceTopicRuleRequest withTopicRulePayload(TopicRulePayload topicRulePayload) {
        this.topicRulePayload = topicRulePayload;
        return this;
    }
}
