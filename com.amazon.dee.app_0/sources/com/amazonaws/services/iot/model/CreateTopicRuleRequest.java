package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateTopicRuleRequest extends AmazonWebServiceRequest implements Serializable {
    private String ruleName;
    private String tags;
    private TopicRulePayload topicRulePayload;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateTopicRuleRequest)) {
            return false;
        }
        CreateTopicRuleRequest createTopicRuleRequest = (CreateTopicRuleRequest) obj;
        if ((createTopicRuleRequest.getRuleName() == null) ^ (getRuleName() == null)) {
            return false;
        }
        if (createTopicRuleRequest.getRuleName() != null && !createTopicRuleRequest.getRuleName().equals(getRuleName())) {
            return false;
        }
        if ((createTopicRuleRequest.getTopicRulePayload() == null) ^ (getTopicRulePayload() == null)) {
            return false;
        }
        if (createTopicRuleRequest.getTopicRulePayload() != null && !createTopicRuleRequest.getTopicRulePayload().equals(getTopicRulePayload())) {
            return false;
        }
        if ((createTopicRuleRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return createTopicRuleRequest.getTags() == null || createTopicRuleRequest.getTags().equals(getTags());
    }

    public String getRuleName() {
        return this.ruleName;
    }

    public String getTags() {
        return this.tags;
    }

    public TopicRulePayload getTopicRulePayload() {
        return this.topicRulePayload;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getRuleName() == null ? 0 : getRuleName().hashCode()) + 31) * 31) + (getTopicRulePayload() == null ? 0 : getTopicRulePayload().hashCode())) * 31;
        if (getTags() != null) {
            i = getTags().hashCode();
        }
        return hashCode + i;
    }

    public void setRuleName(String str) {
        this.ruleName = str;
    }

    public void setTags(String str) {
        this.tags = str;
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
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getTags() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("tags: ");
            outline1074.append(getTags());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateTopicRuleRequest withRuleName(String str) {
        this.ruleName = str;
        return this;
    }

    public CreateTopicRuleRequest withTags(String str) {
        this.tags = str;
        return this;
    }

    public CreateTopicRuleRequest withTopicRulePayload(TopicRulePayload topicRulePayload) {
        this.topicRulePayload = topicRulePayload;
        return this;
    }
}
