package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class TopicRuleListItem implements Serializable {
    private Date createdAt;
    private String ruleArn;
    private Boolean ruleDisabled;
    private String ruleName;
    private String topicPattern;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TopicRuleListItem)) {
            return false;
        }
        TopicRuleListItem topicRuleListItem = (TopicRuleListItem) obj;
        if ((topicRuleListItem.getRuleArn() == null) ^ (getRuleArn() == null)) {
            return false;
        }
        if (topicRuleListItem.getRuleArn() != null && !topicRuleListItem.getRuleArn().equals(getRuleArn())) {
            return false;
        }
        if ((topicRuleListItem.getRuleName() == null) ^ (getRuleName() == null)) {
            return false;
        }
        if (topicRuleListItem.getRuleName() != null && !topicRuleListItem.getRuleName().equals(getRuleName())) {
            return false;
        }
        if ((topicRuleListItem.getTopicPattern() == null) ^ (getTopicPattern() == null)) {
            return false;
        }
        if (topicRuleListItem.getTopicPattern() != null && !topicRuleListItem.getTopicPattern().equals(getTopicPattern())) {
            return false;
        }
        if ((topicRuleListItem.getCreatedAt() == null) ^ (getCreatedAt() == null)) {
            return false;
        }
        if (topicRuleListItem.getCreatedAt() != null && !topicRuleListItem.getCreatedAt().equals(getCreatedAt())) {
            return false;
        }
        if ((topicRuleListItem.getRuleDisabled() == null) ^ (getRuleDisabled() == null)) {
            return false;
        }
        return topicRuleListItem.getRuleDisabled() == null || topicRuleListItem.getRuleDisabled().equals(getRuleDisabled());
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public String getRuleArn() {
        return this.ruleArn;
    }

    public Boolean getRuleDisabled() {
        return this.ruleDisabled;
    }

    public String getRuleName() {
        return this.ruleName;
    }

    public String getTopicPattern() {
        return this.topicPattern;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getRuleArn() == null ? 0 : getRuleArn().hashCode()) + 31) * 31) + (getRuleName() == null ? 0 : getRuleName().hashCode())) * 31) + (getTopicPattern() == null ? 0 : getTopicPattern().hashCode())) * 31) + (getCreatedAt() == null ? 0 : getCreatedAt().hashCode())) * 31;
        if (getRuleDisabled() != null) {
            i = getRuleDisabled().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isRuleDisabled() {
        return this.ruleDisabled;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    public void setRuleArn(String str) {
        this.ruleArn = str;
    }

    public void setRuleDisabled(Boolean bool) {
        this.ruleDisabled = bool;
    }

    public void setRuleName(String str) {
        this.ruleName = str;
    }

    public void setTopicPattern(String str) {
        this.topicPattern = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRuleArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("ruleArn: ");
            outline1072.append(getRuleArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRuleName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("ruleName: ");
            outline1073.append(getRuleName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getTopicPattern() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("topicPattern: ");
            outline1074.append(getTopicPattern());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getCreatedAt() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("createdAt: ");
            outline1075.append(getCreatedAt());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getRuleDisabled() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("ruleDisabled: ");
            outline1076.append(getRuleDisabled());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public TopicRuleListItem withCreatedAt(Date date) {
        this.createdAt = date;
        return this;
    }

    public TopicRuleListItem withRuleArn(String str) {
        this.ruleArn = str;
        return this;
    }

    public TopicRuleListItem withRuleDisabled(Boolean bool) {
        this.ruleDisabled = bool;
        return this;
    }

    public TopicRuleListItem withRuleName(String str) {
        this.ruleName = str;
        return this;
    }

    public TopicRuleListItem withTopicPattern(String str) {
        this.topicPattern = str;
        return this;
    }
}
