package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.services.iot.model.TopicRulePayload;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class TopicRulePayloadJsonUnmarshaller implements Unmarshaller<TopicRulePayload, JsonUnmarshallerContext> {
    private static TopicRulePayloadJsonUnmarshaller instance;

    TopicRulePayloadJsonUnmarshaller() {
    }

    public static TopicRulePayloadJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TopicRulePayloadJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TopicRulePayload unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TopicRulePayload topicRulePayload = new TopicRulePayload();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("sql")) {
                topicRulePayload.setSql(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("description")) {
                topicRulePayload.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ACTIONS)) {
                topicRulePayload.setActions(new ListUnmarshaller(ActionJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ruleDisabled")) {
                topicRulePayload.setRuleDisabled(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("awsIotSqlVersion")) {
                topicRulePayload.setAwsIotSqlVersion(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("errorAction")) {
                topicRulePayload.setErrorAction(ActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return topicRulePayload;
    }
}
