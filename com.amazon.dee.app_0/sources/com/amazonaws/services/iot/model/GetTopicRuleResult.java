package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetTopicRuleResult implements Serializable {
    private TopicRule rule;
    private String ruleArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetTopicRuleResult)) {
            return false;
        }
        GetTopicRuleResult getTopicRuleResult = (GetTopicRuleResult) obj;
        if ((getTopicRuleResult.getRuleArn() == null) ^ (getRuleArn() == null)) {
            return false;
        }
        if (getTopicRuleResult.getRuleArn() != null && !getTopicRuleResult.getRuleArn().equals(getRuleArn())) {
            return false;
        }
        if ((getTopicRuleResult.getRule() == null) ^ (getRule() == null)) {
            return false;
        }
        return getTopicRuleResult.getRule() == null || getTopicRuleResult.getRule().equals(getRule());
    }

    public TopicRule getRule() {
        return this.rule;
    }

    public String getRuleArn() {
        return this.ruleArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getRuleArn() == null ? 0 : getRuleArn().hashCode()) + 31) * 31;
        if (getRule() != null) {
            i = getRule().hashCode();
        }
        return hashCode + i;
    }

    public void setRule(TopicRule topicRule) {
        this.rule = topicRule;
    }

    public void setRuleArn(String str) {
        this.ruleArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRuleArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("ruleArn: ");
            outline1072.append(getRuleArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRule() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("rule: ");
            outline1073.append(getRule());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetTopicRuleResult withRule(TopicRule topicRule) {
        this.rule = topicRule;
        return this;
    }

    public GetTopicRuleResult withRuleArn(String str) {
        this.ruleArn = str;
        return this;
    }
}
