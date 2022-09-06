package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes11.dex */
public class BulkGetReactionSummariesRequest implements CloudDriveRequest {
    public final String groupId;
    public final List<String> topics;

    public BulkGetReactionSummariesRequest(String str, List<String> list) {
        this.groupId = str;
        this.topics = list;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof BulkGetReactionSummariesRequest) && compareTo((CloudDriveRequest) ((BulkGetReactionSummariesRequest) obj)) == 0;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public List<String> getTopics() {
        return this.topics;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getGroupId() != null ? getGroupId().hashCode() : 0) * 31;
        if (getTopics() != null) {
            i = getTopics().hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BulkGetReactionSummariesRequest{groupId='");
        GeneratedOutlineSupport1.outline176(outline107, this.groupId, Chars.QUOTE, ", topics='");
        return GeneratedOutlineSupport1.outline94(outline107, this.topics, JsonReaderKt.END_OBJ);
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this || !(cloudDriveRequest instanceof BulkGetReactionSummariesRequest)) {
            return 0;
        }
        BulkGetReactionSummariesRequest bulkGetReactionSummariesRequest = (BulkGetReactionSummariesRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getGroupId(), bulkGetReactionSummariesRequest.getGroupId());
        if (compare != 0) {
            return compare;
        }
        int compareCollections = ObjectComparator.compareCollections(getTopics(), bulkGetReactionSummariesRequest.getTopics());
        if (compareCollections == 0) {
            return 0;
        }
        return compareCollections;
    }
}
