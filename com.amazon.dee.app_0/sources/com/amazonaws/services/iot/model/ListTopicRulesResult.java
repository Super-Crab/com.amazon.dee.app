package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListTopicRulesResult implements Serializable {
    private String nextToken;
    private List<TopicRuleListItem> rules;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTopicRulesResult)) {
            return false;
        }
        ListTopicRulesResult listTopicRulesResult = (ListTopicRulesResult) obj;
        if ((listTopicRulesResult.getRules() == null) ^ (getRules() == null)) {
            return false;
        }
        if (listTopicRulesResult.getRules() != null && !listTopicRulesResult.getRules().equals(getRules())) {
            return false;
        }
        if ((listTopicRulesResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listTopicRulesResult.getNextToken() == null || listTopicRulesResult.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<TopicRuleListItem> getRules() {
        return this.rules;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getRules() == null ? 0 : getRules().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setRules(Collection<TopicRuleListItem> collection) {
        if (collection == null) {
            this.rules = null;
        } else {
            this.rules = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRules() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("rules: ");
            outline1072.append(getRules());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListTopicRulesResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListTopicRulesResult withRules(TopicRuleListItem... topicRuleListItemArr) {
        if (getRules() == null) {
            this.rules = new ArrayList(topicRuleListItemArr.length);
        }
        for (TopicRuleListItem topicRuleListItem : topicRuleListItemArr) {
            this.rules.add(topicRuleListItem);
        }
        return this;
    }

    public ListTopicRulesResult withRules(Collection<TopicRuleListItem> collection) {
        setRules(collection);
        return this;
    }
}
