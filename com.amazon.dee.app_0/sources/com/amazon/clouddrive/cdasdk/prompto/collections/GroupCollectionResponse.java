package com.amazon.clouddrive.cdasdk.prompto.collections;

import com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceResponse;
import com.amazon.clouddrive.cdasdk.prompto.contentAggregations.ContentAggregationsResponse;
import com.amazon.clouddrive.cdasdk.prompto.nodes.GroupNode;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class GroupCollectionResponse extends PromptoServiceResponse {
    @JsonProperty("caption")
    private String caption;
    @JsonProperty("collectionId")
    private String collectionId;
    @JsonProperty("contentAggregations")
    private ContentAggregationsResponse contentAggregations;
    @JsonProperty("dateAdded")
    private String dateAdded;
    @JsonProperty("eventIds")
    private List<String> eventIds;
    @JsonProperty("groupId")
    private String groupId;
    @JsonProperty("groupNodes")
    private List<GroupNode> groupNodes;
    @JsonProperty(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME)
    private String kind;
    @JsonProperty("name")
    private String name;
    @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY)
    private String ownerId;
    @JsonProperty("reactionTopic")
    private String reactionTopic;
    @JsonProperty("status")
    private String status;

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof GroupCollectionResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GroupCollectionResponse)) {
            return false;
        }
        GroupCollectionResponse groupCollectionResponse = (GroupCollectionResponse) obj;
        if (!groupCollectionResponse.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String collectionId = getCollectionId();
        String collectionId2 = groupCollectionResponse.getCollectionId();
        if (collectionId != null ? !collectionId.equals(collectionId2) : collectionId2 != null) {
            return false;
        }
        String groupId = getGroupId();
        String groupId2 = groupCollectionResponse.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        String ownerId = getOwnerId();
        String ownerId2 = groupCollectionResponse.getOwnerId();
        if (ownerId != null ? !ownerId.equals(ownerId2) : ownerId2 != null) {
            return false;
        }
        String kind = getKind();
        String kind2 = groupCollectionResponse.getKind();
        if (kind != null ? !kind.equals(kind2) : kind2 != null) {
            return false;
        }
        String caption = getCaption();
        String caption2 = groupCollectionResponse.getCaption();
        if (caption != null ? !caption.equals(caption2) : caption2 != null) {
            return false;
        }
        String name = getName();
        String name2 = groupCollectionResponse.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        String dateAdded = getDateAdded();
        String dateAdded2 = groupCollectionResponse.getDateAdded();
        if (dateAdded != null ? !dateAdded.equals(dateAdded2) : dateAdded2 != null) {
            return false;
        }
        ContentAggregationsResponse contentAggregations = getContentAggregations();
        ContentAggregationsResponse contentAggregations2 = groupCollectionResponse.getContentAggregations();
        if (contentAggregations != null ? !contentAggregations.equals(contentAggregations2) : contentAggregations2 != null) {
            return false;
        }
        List<GroupNode> groupNodes = getGroupNodes();
        List<GroupNode> groupNodes2 = groupCollectionResponse.getGroupNodes();
        if (groupNodes != null ? !groupNodes.equals(groupNodes2) : groupNodes2 != null) {
            return false;
        }
        List<String> eventIds = getEventIds();
        List<String> eventIds2 = groupCollectionResponse.getEventIds();
        if (eventIds != null ? !eventIds.equals(eventIds2) : eventIds2 != null) {
            return false;
        }
        String status = getStatus();
        String status2 = groupCollectionResponse.getStatus();
        if (status != null ? !status.equals(status2) : status2 != null) {
            return false;
        }
        String reactionTopic = getReactionTopic();
        String reactionTopic2 = groupCollectionResponse.getReactionTopic();
        return reactionTopic != null ? reactionTopic.equals(reactionTopic2) : reactionTopic2 == null;
    }

    public String getCaption() {
        return this.caption;
    }

    public String getCollectionId() {
        return this.collectionId;
    }

    public ContentAggregationsResponse getContentAggregations() {
        return this.contentAggregations;
    }

    public String getDateAdded() {
        return this.dateAdded;
    }

    public List<String> getEventIds() {
        return this.eventIds;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public List<GroupNode> getGroupNodes() {
        return this.groupNodes;
    }

    public String getKind() {
        return this.kind;
    }

    public String getName() {
        return this.name;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public String getReactionTopic() {
        return this.reactionTopic;
    }

    public String getStatus() {
        return this.status;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceResponse
    public int hashCode() {
        int hashCode = super.hashCode();
        String collectionId = getCollectionId();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (collectionId == null ? 43 : collectionId.hashCode());
        String groupId = getGroupId();
        int hashCode3 = (hashCode2 * 59) + (groupId == null ? 43 : groupId.hashCode());
        String ownerId = getOwnerId();
        int hashCode4 = (hashCode3 * 59) + (ownerId == null ? 43 : ownerId.hashCode());
        String kind = getKind();
        int hashCode5 = (hashCode4 * 59) + (kind == null ? 43 : kind.hashCode());
        String caption = getCaption();
        int hashCode6 = (hashCode5 * 59) + (caption == null ? 43 : caption.hashCode());
        String name = getName();
        int hashCode7 = (hashCode6 * 59) + (name == null ? 43 : name.hashCode());
        String dateAdded = getDateAdded();
        int hashCode8 = (hashCode7 * 59) + (dateAdded == null ? 43 : dateAdded.hashCode());
        ContentAggregationsResponse contentAggregations = getContentAggregations();
        int hashCode9 = (hashCode8 * 59) + (contentAggregations == null ? 43 : contentAggregations.hashCode());
        List<GroupNode> groupNodes = getGroupNodes();
        int hashCode10 = (hashCode9 * 59) + (groupNodes == null ? 43 : groupNodes.hashCode());
        List<String> eventIds = getEventIds();
        int hashCode11 = (hashCode10 * 59) + (eventIds == null ? 43 : eventIds.hashCode());
        String status = getStatus();
        int hashCode12 = (hashCode11 * 59) + (status == null ? 43 : status.hashCode());
        String reactionTopic = getReactionTopic();
        int i2 = hashCode12 * 59;
        if (reactionTopic != null) {
            i = reactionTopic.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("caption")
    public void setCaption(String str) {
        this.caption = str;
    }

    @JsonProperty("collectionId")
    public void setCollectionId(String str) {
        this.collectionId = str;
    }

    @JsonProperty("contentAggregations")
    public void setContentAggregations(ContentAggregationsResponse contentAggregationsResponse) {
        this.contentAggregations = contentAggregationsResponse;
    }

    @JsonProperty("dateAdded")
    public void setDateAdded(String str) {
        this.dateAdded = str;
    }

    @JsonProperty("eventIds")
    public void setEventIds(List<String> list) {
        this.eventIds = list;
    }

    @JsonProperty("groupId")
    public void setGroupId(String str) {
        this.groupId = str;
    }

    @JsonProperty("groupNodes")
    public void setGroupNodes(List<GroupNode> list) {
        this.groupNodes = list;
    }

    @JsonProperty(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME)
    public void setKind(String str) {
        this.kind = str;
    }

    @JsonProperty("name")
    public void setName(String str) {
        this.name = str;
    }

    @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY)
    public void setOwnerId(String str) {
        this.ownerId = str;
    }

    @JsonProperty("reactionTopic")
    public void setReactionTopic(String str) {
        this.reactionTopic = str;
    }

    @JsonProperty("status")
    public void setStatus(String str) {
        this.status = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceResponse
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupCollectionResponse(collectionId=");
        outline107.append(getCollectionId());
        outline107.append(", groupId=");
        outline107.append(getGroupId());
        outline107.append(", ownerId=");
        outline107.append(getOwnerId());
        outline107.append(", kind=");
        outline107.append(getKind());
        outline107.append(", caption=");
        outline107.append(getCaption());
        outline107.append(", name=");
        outline107.append(getName());
        outline107.append(", dateAdded=");
        outline107.append(getDateAdded());
        outline107.append(", contentAggregations=");
        outline107.append(getContentAggregations());
        outline107.append(", groupNodes=");
        outline107.append(getGroupNodes());
        outline107.append(", eventIds=");
        outline107.append(getEventIds());
        outline107.append(", status=");
        outline107.append(getStatus());
        outline107.append(", reactionTopic=");
        outline107.append(getReactionTopic());
        outline107.append(")");
        return outline107.toString();
    }
}
