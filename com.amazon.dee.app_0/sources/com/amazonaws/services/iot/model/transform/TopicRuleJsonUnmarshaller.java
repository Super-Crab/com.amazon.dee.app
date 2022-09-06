package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.services.iot.model.TopicRule;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class TopicRuleJsonUnmarshaller implements Unmarshaller<TopicRule, JsonUnmarshallerContext> {
    private static TopicRuleJsonUnmarshaller instance;

    TopicRuleJsonUnmarshaller() {
    }

    public static TopicRuleJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TopicRuleJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TopicRule unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TopicRule topicRule = new TopicRule();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("ruleName")) {
                topicRule.setRuleName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("sql")) {
                topicRule.setSql(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("description")) {
                topicRule.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("createdAt")) {
                topicRule.setCreatedAt(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ACTIONS)) {
                topicRule.setActions(new ListUnmarshaller(ActionJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ruleDisabled")) {
                topicRule.setRuleDisabled(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("awsIotSqlVersion")) {
                topicRule.setAwsIotSqlVersion(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("errorAction")) {
                topicRule.setErrorAction(ActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return topicRule;
    }
}
