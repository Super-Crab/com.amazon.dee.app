package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class AddTagsToStreamRequest extends AmazonWebServiceRequest implements Serializable {
    private String streamName;
    private Map<String, String> tags = new HashMap();

    public AddTagsToStreamRequest addTagsEntry(String str, String str2) {
        if (this.tags == null) {
            this.tags = new HashMap();
        }
        if (!this.tags.containsKey(str)) {
            this.tags.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public AddTagsToStreamRequest clearTagsEntries() {
        this.tags = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AddTagsToStreamRequest)) {
            return false;
        }
        AddTagsToStreamRequest addTagsToStreamRequest = (AddTagsToStreamRequest) obj;
        if ((addTagsToStreamRequest.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (addTagsToStreamRequest.getStreamName() != null && !addTagsToStreamRequest.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((addTagsToStreamRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return addTagsToStreamRequest.getTags() == null || addTagsToStreamRequest.getTags().equals(getTags());
    }

    public String getStreamName() {
        return this.streamName;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getStreamName() == null ? 0 : getStreamName().hashCode()) + 31) * 31;
        if (getTags() != null) {
            i = getTags().hashCode();
        }
        return hashCode + i;
    }

    public void setStreamName(String str) {
        this.streamName = str;
    }

    public void setTags(Map<String, String> map) {
        this.tags = map;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStreamName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("StreamName: ");
            outline1072.append(getStreamName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTags() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Tags: ");
            outline1073.append(getTags());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AddTagsToStreamRequest withStreamName(String str) {
        this.streamName = str;
        return this;
    }

    public AddTagsToStreamRequest withTags(Map<String, String> map) {
        this.tags = map;
        return this;
    }
}
