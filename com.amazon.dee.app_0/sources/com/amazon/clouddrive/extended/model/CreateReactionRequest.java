package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes11.dex */
public class CreateReactionRequest implements CloudDriveRequest {
    private final String body;
    private final String groupId;
    private final String kind;
    private final String reactionId;
    private final String topic;

    public CreateReactionRequest(String str, String str2, String str3, String str4, String str5) {
        this.kind = str2;
        this.body = str3;
        this.topic = str4;
        this.groupId = str;
        this.reactionId = str5;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CreateReactionRequest)) {
            return false;
        }
        CreateReactionRequest createReactionRequest = (CreateReactionRequest) obj;
        if (getKind() == null ? createReactionRequest.getKind() == null : getKind().equals(createReactionRequest.getKind())) {
            if (getBody() == null ? createReactionRequest.getBody() == null : getBody().equals(createReactionRequest.getBody())) {
                if (getTopic() == null ? createReactionRequest.getTopic() == null : getTopic().equals(createReactionRequest.getTopic())) {
                    if (getGroupId() == null ? createReactionRequest.getGroupId() == null : getGroupId().equals(createReactionRequest.getGroupId())) {
                        if (getReactionId() != null) {
                            if (getReactionId().equals(createReactionRequest.getReactionId())) {
                                return true;
                            }
                        } else if (createReactionRequest.getReactionId() == null) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public String getBody() {
        return this.body;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getKind() {
        return this.kind;
    }

    public String getReactionId() {
        return this.reactionId;
    }

    public String getTopic() {
        return this.topic;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((getKind() != null ? getKind().hashCode() : 0) * 31) + (getBody() != null ? getBody().hashCode() : 0)) * 31) + (getTopic() != null ? getTopic().hashCode() : 0)) * 31) + (getGroupId() != null ? getGroupId().hashCode() : 0)) * 31;
        if (getReactionId() != null) {
            i = getReactionId().hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CreateReactionRequest{kind='");
        GeneratedOutlineSupport1.outline176(outline107, this.kind, Chars.QUOTE, ", body='");
        GeneratedOutlineSupport1.outline176(outline107, this.body, Chars.QUOTE, ", topic='");
        GeneratedOutlineSupport1.outline176(outline107, this.topic, Chars.QUOTE, ", groupId='");
        GeneratedOutlineSupport1.outline176(outline107, this.groupId, Chars.QUOTE, ", reactionId='");
        return GeneratedOutlineSupport1.outline90(outline107, this.reactionId, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this || !(cloudDriveRequest instanceof CreateReactionRequest)) {
            return 0;
        }
        CreateReactionRequest createReactionRequest = (CreateReactionRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getKind(), createReactionRequest.getKind());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getBody(), createReactionRequest.getBody());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getTopic(), createReactionRequest.getTopic());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getGroupId(), createReactionRequest.getGroupId());
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(getReactionId(), createReactionRequest.getReactionId());
        if (compare5 == 0) {
            return 0;
        }
        return compare5;
    }
}
