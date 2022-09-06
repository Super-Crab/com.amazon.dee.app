package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class RemoveTagsFromStreamRequest extends AmazonWebServiceRequest implements Serializable {
    private String streamName;
    private List<String> tagKeys = new ArrayList();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RemoveTagsFromStreamRequest)) {
            return false;
        }
        RemoveTagsFromStreamRequest removeTagsFromStreamRequest = (RemoveTagsFromStreamRequest) obj;
        if ((removeTagsFromStreamRequest.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (removeTagsFromStreamRequest.getStreamName() != null && !removeTagsFromStreamRequest.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((removeTagsFromStreamRequest.getTagKeys() == null) ^ (getTagKeys() == null)) {
            return false;
        }
        return removeTagsFromStreamRequest.getTagKeys() == null || removeTagsFromStreamRequest.getTagKeys().equals(getTagKeys());
    }

    public String getStreamName() {
        return this.streamName;
    }

    public List<String> getTagKeys() {
        return this.tagKeys;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getStreamName() == null ? 0 : getStreamName().hashCode()) + 31) * 31;
        if (getTagKeys() != null) {
            i = getTagKeys().hashCode();
        }
        return hashCode + i;
    }

    public void setStreamName(String str) {
        this.streamName = str;
    }

    public void setTagKeys(Collection<String> collection) {
        if (collection == null) {
            this.tagKeys = null;
        } else {
            this.tagKeys = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStreamName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("StreamName: ");
            outline1072.append(getStreamName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTagKeys() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("TagKeys: ");
            outline1073.append(getTagKeys());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public RemoveTagsFromStreamRequest withStreamName(String str) {
        this.streamName = str;
        return this;
    }

    public RemoveTagsFromStreamRequest withTagKeys(String... strArr) {
        if (getTagKeys() == null) {
            this.tagKeys = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.tagKeys.add(str);
        }
        return this;
    }

    public RemoveTagsFromStreamRequest withTagKeys(Collection<String> collection) {
        setTagKeys(collection);
        return this;
    }
}
