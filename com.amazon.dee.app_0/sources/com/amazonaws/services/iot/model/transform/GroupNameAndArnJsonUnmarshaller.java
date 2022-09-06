package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.accessory.notificationpublisher.utils.GroupNotificationHelper;
import com.amazonaws.services.iot.model.GroupNameAndArn;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class GroupNameAndArnJsonUnmarshaller implements Unmarshaller<GroupNameAndArn, JsonUnmarshallerContext> {
    private static GroupNameAndArnJsonUnmarshaller instance;

    GroupNameAndArnJsonUnmarshaller() {
    }

    public static GroupNameAndArnJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GroupNameAndArnJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public GroupNameAndArn unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        GroupNameAndArn groupNameAndArn = new GroupNameAndArn();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals(GroupNotificationHelper.PARSER_GROUP_NAME_KEY)) {
                groupNameAndArn.setGroupName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("groupArn")) {
                groupNameAndArn.setGroupArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return groupNameAndArn;
    }
}
