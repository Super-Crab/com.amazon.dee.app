package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class TopicRulePayload implements Serializable {
    private List<Action> actions;
    private String awsIotSqlVersion;
    private String description;
    private Action errorAction;
    private Boolean ruleDisabled;
    private String sql;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TopicRulePayload)) {
            return false;
        }
        TopicRulePayload topicRulePayload = (TopicRulePayload) obj;
        if ((topicRulePayload.getSql() == null) ^ (getSql() == null)) {
            return false;
        }
        if (topicRulePayload.getSql() != null && !topicRulePayload.getSql().equals(getSql())) {
            return false;
        }
        if ((topicRulePayload.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (topicRulePayload.getDescription() != null && !topicRulePayload.getDescription().equals(getDescription())) {
            return false;
        }
        if ((topicRulePayload.getActions() == null) ^ (getActions() == null)) {
            return false;
        }
        if (topicRulePayload.getActions() != null && !topicRulePayload.getActions().equals(getActions())) {
            return false;
        }
        if ((topicRulePayload.getRuleDisabled() == null) ^ (getRuleDisabled() == null)) {
            return false;
        }
        if (topicRulePayload.getRuleDisabled() != null && !topicRulePayload.getRuleDisabled().equals(getRuleDisabled())) {
            return false;
        }
        if ((topicRulePayload.getAwsIotSqlVersion() == null) ^ (getAwsIotSqlVersion() == null)) {
            return false;
        }
        if (topicRulePayload.getAwsIotSqlVersion() != null && !topicRulePayload.getAwsIotSqlVersion().equals(getAwsIotSqlVersion())) {
            return false;
        }
        if ((topicRulePayload.getErrorAction() == null) ^ (getErrorAction() == null)) {
            return false;
        }
        return topicRulePayload.getErrorAction() == null || topicRulePayload.getErrorAction().equals(getErrorAction());
    }

    public List<Action> getActions() {
        return this.actions;
    }

    public String getAwsIotSqlVersion() {
        return this.awsIotSqlVersion;
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

    public String getSql() {
        return this.sql;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getSql() == null ? 0 : getSql().hashCode()) + 31) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getActions() == null ? 0 : getActions().hashCode())) * 31) + (getRuleDisabled() == null ? 0 : getRuleDisabled().hashCode())) * 31) + (getAwsIotSqlVersion() == null ? 0 : getAwsIotSqlVersion().hashCode())) * 31;
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

    public void setDescription(String str) {
        this.description = str;
    }

    public void setErrorAction(Action action) {
        this.errorAction = action;
    }

    public void setRuleDisabled(Boolean bool) {
        this.ruleDisabled = bool;
    }

    public void setSql(String str) {
        this.sql = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getSql() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("sql: ");
            outline1072.append(getSql());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getDescription() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("description: ");
            outline1073.append(getDescription());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getActions() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("actions: ");
            outline1074.append(getActions());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getRuleDisabled() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("ruleDisabled: ");
            outline1075.append(getRuleDisabled());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getAwsIotSqlVersion() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("awsIotSqlVersion: ");
            outline1076.append(getAwsIotSqlVersion());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getErrorAction() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("errorAction: ");
            outline1077.append(getErrorAction());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public TopicRulePayload withActions(Action... actionArr) {
        if (getActions() == null) {
            this.actions = new ArrayList(actionArr.length);
        }
        for (Action action : actionArr) {
            this.actions.add(action);
        }
        return this;
    }

    public TopicRulePayload withAwsIotSqlVersion(String str) {
        this.awsIotSqlVersion = str;
        return this;
    }

    public TopicRulePayload withDescription(String str) {
        this.description = str;
        return this;
    }

    public TopicRulePayload withErrorAction(Action action) {
        this.errorAction = action;
        return this;
    }

    public TopicRulePayload withRuleDisabled(Boolean bool) {
        this.ruleDisabled = bool;
        return this;
    }

    public TopicRulePayload withSql(String str) {
        this.sql = str;
        return this;
    }

    public TopicRulePayload withActions(Collection<Action> collection) {
        setActions(collection);
        return this;
    }
}
