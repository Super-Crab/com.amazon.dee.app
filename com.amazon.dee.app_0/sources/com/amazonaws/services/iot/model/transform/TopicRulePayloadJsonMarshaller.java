package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.services.iot.model.Action;
import com.amazonaws.services.iot.model.TopicRulePayload;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;
/* loaded from: classes13.dex */
class TopicRulePayloadJsonMarshaller {
    private static TopicRulePayloadJsonMarshaller instance;

    TopicRulePayloadJsonMarshaller() {
    }

    public static TopicRulePayloadJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new TopicRulePayloadJsonMarshaller();
        }
        return instance;
    }

    public void marshall(TopicRulePayload topicRulePayload, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (topicRulePayload.getSql() != null) {
            String sql = topicRulePayload.getSql();
            awsJsonWriter.name("sql");
            awsJsonWriter.value(sql);
        }
        if (topicRulePayload.getDescription() != null) {
            String description = topicRulePayload.getDescription();
            awsJsonWriter.name("description");
            awsJsonWriter.value(description);
        }
        if (topicRulePayload.getActions() != null) {
            List<Action> actions = topicRulePayload.getActions();
            awsJsonWriter.name(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ACTIONS);
            awsJsonWriter.beginArray();
            for (Action action : actions) {
                if (action != null) {
                    ActionJsonMarshaller.getInstance().marshall(action, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        if (topicRulePayload.getRuleDisabled() != null) {
            Boolean ruleDisabled = topicRulePayload.getRuleDisabled();
            awsJsonWriter.name("ruleDisabled");
            awsJsonWriter.value(ruleDisabled.booleanValue());
        }
        if (topicRulePayload.getAwsIotSqlVersion() != null) {
            String awsIotSqlVersion = topicRulePayload.getAwsIotSqlVersion();
            awsJsonWriter.name("awsIotSqlVersion");
            awsJsonWriter.value(awsIotSqlVersion);
        }
        if (topicRulePayload.getErrorAction() != null) {
            Action errorAction = topicRulePayload.getErrorAction();
            awsJsonWriter.name("errorAction");
            ActionJsonMarshaller.getInstance().marshall(errorAction, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }
}
