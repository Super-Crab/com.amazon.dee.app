package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListTopicRulesRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private Boolean ruleDisabled;
    private String topic;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTopicRulesRequest)) {
            return false;
        }
        ListTopicRulesRequest listTopicRulesRequest = (ListTopicRulesRequest) obj;
        if ((listTopicRulesRequest.getTopic() == null) ^ (getTopic() == null)) {
            return false;
        }
        if (listTopicRulesRequest.getTopic() != null && !listTopicRulesRequest.getTopic().equals(getTopic())) {
            return false;
        }
        if ((listTopicRulesRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listTopicRulesRequest.getMaxResults() != null && !listTopicRulesRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listTopicRulesRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listTopicRulesRequest.getNextToken() != null && !listTopicRulesRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listTopicRulesRequest.getRuleDisabled() == null) ^ (getRuleDisabled() == null)) {
            return false;
        }
        return listTopicRulesRequest.getRuleDisabled() == null || listTopicRulesRequest.getRuleDisabled().equals(getRuleDisabled());
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public Boolean getRuleDisabled() {
        return this.ruleDisabled;
    }

    public String getTopic() {
        return this.topic;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getTopic() == null ? 0 : getTopic().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31;
        if (getRuleDisabled() != null) {
            i = getRuleDisabled().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isRuleDisabled() {
        return this.ruleDisabled;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setRuleDisabled(Boolean bool) {
        this.ruleDisabled = bool;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTopic() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("topic: ");
            outline1072.append(getTopic());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getMaxResults() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("maxResults: ");
            outline1073.append(getMaxResults());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1074.append(getNextToken());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getRuleDisabled() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("ruleDisabled: ");
            outline1075.append(getRuleDisabled());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListTopicRulesRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListTopicRulesRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListTopicRulesRequest withRuleDisabled(Boolean bool) {
        this.ruleDisabled = bool;
        return this;
    }

    public ListTopicRulesRequest withTopic(String str) {
        this.topic = str;
        return this;
    }
}
