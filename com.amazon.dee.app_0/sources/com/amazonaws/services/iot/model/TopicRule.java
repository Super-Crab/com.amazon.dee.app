package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
/* loaded from: classes13.dex */
public class TopicRule implements Serializable {
    private List<Action> actions;
    private String awsIotSqlVersion;
    private Date createdAt;
    private String description;
    private Action errorAction;
    private Boolean ruleDisabled;
    private String ruleName;
    private String sql;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TopicRule)) {
            return false;
        }
        TopicRule topicRule = (TopicRule) obj;
        if ((topicRule.getRuleName() == null) ^ (getRuleName() == null)) {
            return false;
        }
        if (topicRule.getRuleName() != null && !topicRule.getRuleName().equals(getRuleName())) {
            return false;
        }
        if ((topicRule.getSql() == null) ^ (getSql() == null)) {
            return false;
        }
        if (topicRule.getSql() != null && !topicRule.getSql().equals(getSql())) {
            return false;
        }
        if ((topicRule.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (topicRule.getDescription() != null && !topicRule.getDescription().equals(getDescription())) {
            return false;
        }
        if ((topicRule.getCreatedAt() == null) ^ (getCreatedAt() == null)) {
            return false;
        }
        if (topicRule.getCreatedAt() != null && !topicRule.getCreatedAt().equals(getCreatedAt())) {
            return false;
        }
        if ((topicRule.getActions() == null) ^ (getActions() == null)) {
            return false;
        }
        if (topicRule.getActions() != null && !topicRule.getActions().equals(getActions())) {
            return false;
        }
        if ((topicRule.getRuleDisabled() == null) ^ (getRuleDisabled() == null)) {
            return false;
        }
        if (topicRule.getRuleDisabled() != null && !topicRule.getRuleDisabled().equals(getRuleDisabled())) {
            return false;
        }
        if ((topicRule.getAwsIotSqlVersion() == null) ^ (getAwsIotSqlVersion() == null)) {
            return false;
        }
        if (topicRule.getAwsIotSqlVersion() != null && !topicRule.getAwsIotSqlVersion().equals(getAwsIotSqlVersion())) {
            return false;
        }
        if ((topicRule.getErrorAction() == null) ^ (getErrorAction() == null)) {
            return false;
        }
        return topicRule.getErrorAction() == null || topicRule.getErrorAction().equals(getErrorAction());
    }

    public List<Action> getActions() {
        return this.actions;
    }

    public String getAwsIotSqlVersion() {
        return this.awsIotSqlVersion;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public String getDescription() {
        return this.description;
    }

    public Action getErrorAction() {
        return this.errorAction;
    }

    public Boolean getRuleDisabled() {
        return this.ruleDisabled;
    }

    public String getRuleName() {
        return this.ruleName;
    }

    public String getSql() {
        return this.sql;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((getRuleName() == null ? 0 : getRuleName().hashCode()) + 31) * 31) + (getSql() == null ? 0 : getSql().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getCreatedAt() == null ? 0 : getCreatedAt().hashCode())) * 31) + (getActions() == null ? 0 : getActions().hashCode())) * 31) + (getRuleDisabled() == null ? 0 : getRuleDisabled().hashCode())) * 31) + (getAwsIotSqlVersion() == null ? 0 : getAwsIotSqlVersion().hashCode())) * 31;
        if (getErrorAction() != null) {
            i = getErrorAction().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isRuleDisabled() {
        return this.ruleDisabled;
    }

    public void setActions(Collection<Action> collection) {
        if (collection == null) {
            this.actions = null;
        } else {
            this.actions = new ArrayList(collection);
        }
    }

    public void setAwsIotSqlVersion(String str) {
        this.awsIotSqlVersion = str;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setErrorAction(Action action) {
        this.errorAction = action;
    }

    public void setRuleDisabled(Boolean bool) {
        this.ruleDisabled = bool;
    }

    public void setRuleName(String str) {
        this.ruleName = str;
    }

    public void setSql(String str) {
        this.sql = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRuleName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("ruleName: ");
            outline1072.append(getRuleName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getSql() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("sql: ");
            outline1073.append(getSql());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getDescription() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("description: ");
            outline1074.append(getDescription());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getCreatedAt() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("createdAt: ");
            outline1075.append(getCreatedAt());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getActions() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("actions: ");
            outline1076.append(getActions());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getRuleDisabled() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("ruleDisabled: ");
            outline1077.append(getRuleDisabled());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getAwsIotSqlVersion() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("awsIotSqlVersion: ");
            outline1078.append(getAwsIotSqlVersion());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getErrorAction() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("errorAction: ");
            outline1079.append(getErrorAction());
            outline107.append(outline1079.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public TopicRule withActions(Action... actionArr) {
        if (getActions() == null) {
            this.actions = new ArrayList(actionArr.length);
        }
        for (Action action : actionArr) {
            this.actions.add(action);
        }
        return this;
    }

    public TopicRule withAwsIotSqlVersion(String str) {
        this.awsIotSqlVersion = str;
        return this;
    }

    public TopicRule withCreatedAt(Date date) {
        this.createdAt = date;
        return this;
    }

    public TopicRule withDescription(String str) {
        this.description = str;
        return this;
    }

    public TopicRule withErrorAction(Action action) {
        this.errorAction = action;
        return this;
    }

    public TopicRule withRuleDisabled(Boolean bool) {
        this.ruleDisabled = bool;
        return this;
    }

    public TopicRule withRuleName(String str) {
        this.ruleName = str;
        return this;
    }

    public TopicRule withSql(String str) {
        this.sql = str;
        return this;
    }

    public TopicRule withActions(Collection<Action> collection) {
        setActions(collection);
        return this;
    }
}
