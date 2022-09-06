package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.accessory.notificationpublisher.utils.GroupNotificationHelper;
import com.amazonaws.services.iot.model.GroupNameAndArn;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class GroupNameAndArnJsonMarshaller {
    private static GroupNameAndArnJsonMarshaller instance;

    GroupNameAndArnJsonMarshaller() {
    }

    public static GroupNameAndArnJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new GroupNameAndArnJsonMarshaller();
        }
        return instance;
    }

    public void marshall(GroupNameAndArn groupNameAndArn, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (groupNameAndArn.getGroupName() != null) {
            String groupName = groupNameAndArn.getGroupName();
            awsJsonWriter.name(GroupNotificationHelper.PARSER_GROUP_NAME_KEY);
            awsJsonWriter.value(groupName);
        }
        if (groupNameAndArn.getGroupArn() != null) {
            String groupArn = groupNameAndArn.getGroupArn();
            awsJsonWriter.name("groupArn");
            awsJsonWriter.value(groupArn);
        }
        awsJsonWriter.endObject();
    }
}
