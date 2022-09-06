package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListTopicRulesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListTopicRulesResultJsonUnmarshaller implements Unmarshaller<ListTopicRulesResult, JsonUnmarshallerContext> {
    private static ListTopicRulesResultJsonUnmarshaller instance;

    public static ListTopicRulesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListTopicRulesResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListTopicRulesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListTopicRulesResult listTopicRulesResult = new ListTopicRulesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("rules")) {
                listTopicRulesResult.setRules(new ListUnmarshaller(TopicRuleListItemJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                listTopicRulesResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listTopicRulesResult;
    }
}
