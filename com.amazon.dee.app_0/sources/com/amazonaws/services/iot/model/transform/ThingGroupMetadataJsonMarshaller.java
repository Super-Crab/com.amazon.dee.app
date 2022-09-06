package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GroupNameAndArn;
import com.amazonaws.services.iot.model.ThingGroupMetadata;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
import java.util.List;
/* loaded from: classes13.dex */
class ThingGroupMetadataJsonMarshaller {
    private static ThingGroupMetadataJsonMarshaller instance;

    ThingGroupMetadataJsonMarshaller() {
    }

    public static ThingGroupMetadataJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ThingGroupMetadataJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ThingGroupMetadata thingGroupMetadata, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (thingGroupMetadata.getParentGroupName() != null) {
            String parentGroupName = thingGroupMetadata.getParentGroupName();
            awsJsonWriter.name("parentGroupName");
            awsJsonWriter.value(parentGroupName);
        }
        if (thingGroupMetadata.getRootToParentThingGroups() != null) {
            List<GroupNameAndArn> rootToParentThingGroups = thingGroupMetadata.getRootToParentThingGroups();
            awsJsonWriter.name("rootToParentThingGroups");
            awsJsonWriter.beginArray();
            for (GroupNameAndArn groupNameAndArn : rootToParentThingGroups) {
                if (groupNameAndArn != null) {
                    GroupNameAndArnJsonMarshaller.getInstance().marshall(groupNameAndArn, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        if (thingGroupMetadata.getCreationDate() != null) {
            Date creationDate = thingGroupMetadata.getCreationDate();
            awsJsonWriter.name("creationDate");
            awsJsonWriter.value(creationDate);
        }
        awsJsonWriter.endObject();
    }
}
