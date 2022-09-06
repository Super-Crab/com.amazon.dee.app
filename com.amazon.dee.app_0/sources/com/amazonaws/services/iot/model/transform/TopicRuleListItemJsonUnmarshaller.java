package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TopicRuleListItem;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class TopicRuleListItemJsonUnmarshaller implements Unmarshaller<TopicRuleListItem, JsonUnmarshallerContext> {
    private static TopicRuleListItemJsonUnmarshaller instance;

    TopicRuleListItemJsonUnmarshaller() {
    }

    public static TopicRuleListItemJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TopicRuleListItemJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TopicRuleListItem unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TopicRuleListItem topicRuleListItem = new TopicRuleListItem();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("ruleArn")) {
                topicRuleListItem.setRuleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ruleName")) {
                topicRuleListItem.setRuleName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("topicPattern")) {
                topicRuleListItem.setTopicPattern(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("createdAt")) {
                topicRuleListItem.setCreatedAt(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ruleDisabled")) {
                topicRuleListItem.setRuleDisabled(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return topicRuleListItem;
    }
}
