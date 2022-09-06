package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public class GroupEventResponse implements CloudDriveResponse {
    private String addedBy;
    private Profile addedByProfile;
    private String caption;
    private ContentAggregations contentAggregations;
    private List<NodeExtended> coverNodes;
    private String dateAdded;
    private String eventId;
    private String groupId;
    private EventParent parent;
    private ReactionSummary reactionSummary;
    private String reactionTopic;
    private String subtitle;

    protected boolean canEqual(Object obj) {
        return obj instanceof GroupEventResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GroupEventResponse)) {
            return false;
        }
        GroupEventResponse groupEventResponse = (GroupEventResponse) obj;
        if (!groupEventResponse.canEqual(this)) {
            return false;
        }
        String eventId = getEventId();
        String eventId2 = groupEventResponse.getEventId();
        if (eventId != null ? !eventId.equals(eventId2) : eventId2 != null) {
            return false;
        }
        String groupId = getGroupId();
        String groupId2 = groupEventResponse.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        String addedBy = getAddedBy();
        String addedBy2 = groupEventResponse.getAddedBy();
        if (addedBy != null ? !addedBy.equals(addedBy2) : addedBy2 != null) {
            return false;
        }
        Profile addedByProfile = getAddedByProfile();
        Profile addedByProfile2 = groupEventResponse.getAddedByProfile();
        if (addedByProfile != null ? !addedByProfile.equals(addedByProfile2) : addedByProfile2 != null) {
            return false;
        }
        String dateAdded = getDateAdded();
        String dateAdded2 = groupEventResponse.getDateAdded();
        if (dateAdded != null ? !dateAdded.equals(dateAdded2) : dateAdded2 != null) {
            return false;
        }
        EventParent parent = getParent();
        EventParent parent2 = groupEventResponse.getParent();
        if (parent != null ? !parent.equals(parent2) : parent2 != null) {
            return false;
        }
        String reactionTopic = getReactionTopic();
        String reactionTopic2 = groupEventResponse.getReactionTopic();
        if (reactionTopic != null ? !reactionTopic.equals(reactionTopic2) : reactionTopic2 != null) {
            return false;
        }
        ReactionSummary reactionSummary = getReactionSummary();
        ReactionSummary reactionSummary2 = groupEventResponse.getReactionSummary();
        if (reactionSummary != null ? !reactionSummary.equals(reactionSummary2) : reactionSummary2 != null) {
            return false;
        }
        String caption = getCaption();
        String caption2 = groupEventResponse.getCaption();
        if (caption != null ? !caption.equals(caption2) : caption2 != null) {
            return false;
        }
        String subtitle = getSubtitle();
        String subtitle2 = groupEventResponse.getSubtitle();
        if (subtitle != null ? !subtitle.equals(subtitle2) : subtitle2 != null) {
            return false;
        }
        List<NodeExtended> coverNodes = getCoverNodes();
        List<NodeExtended> coverNodes2 = groupEventResponse.getCoverNodes();
        if (coverNodes != null ? !coverNodes.equals(coverNodes2) : coverNodes2 != null) {
            return false;
        }
        ContentAggregations contentAggregations = getContentAggregations();
        ContentAggregations contentAggregations2 = groupEventResponse.getContentAggregations();
        return contentAggregations != null ? contentAggregations.equals(contentAggregations2) : contentAggregations2 == null;
    }

    public String getAddedBy() {
        return this.addedBy;
    }

    public Profile getAddedByProfile() {
        return this.addedByProfile;
    }

    public String getCaption() {
        return this.caption;
    }

    public ContentAggregations getContentAggregations() {
        return this.contentAggregations;
    }

    public List<NodeExtended> getCoverNodes() {
        return this.coverNodes;
    }

    public String getDateAdded() {
        return this.dateAdded;
    }

    public String getEventId() {
        return this.eventId;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public EventParent getParent() {
        return this.parent;
    }

    public ReactionSummary getReactionSummary() {
        return this.reactionSummary;
    }

    public String getReactionTopic() {
        return this.reactionTopic;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public int hashCode() {
        String eventId = getEventId();
        int i = 43;
        int hashCode = eventId == null ? 43 : eventId.hashCode();
        String groupId = getGroupId();
        int hashCode2 = ((hashCode + 59) * 59) + (groupId == null ? 43 : groupId.hashCode());
        String addedBy = getAddedBy();
        int hashCode3 = (hashCode2 * 59) + (addedBy == null ? 43 : addedBy.hashCode());
        Profile addedByProfile = getAddedByProfile();
        int hashCode4 = (hashCode3 * 59) + (addedByProfile == null ? 43 : addedByProfile.hashCode());
        String dateAdded = getDateAdded();
        int hashCode5 = (hashCode4 * 59) + (dateAdded == null ? 43 : dateAdded.hashCode());
        EventParent parent = getParent();
        int hashCode6 = (hashCode5 * 59) + (parent == null ? 43 : parent.hashCode());
        String reactionTopic = getReactionTopic();
        int hashCode7 = (hashCode6 * 59) + (reactionTopic == null ? 43 : reactionTopic.hashCode());
        ReactionSummary reactionSummary = getReactionSummary();
        int hashCode8 = (hashCode7 * 59) + (reactionSummary == null ? 43 : reactionSummary.hashCode());
        String caption = getCaption();
        int hashCode9 = (hashCode8 * 59) + (caption == null ? 43 : caption.hashCode());
        String subtitle = getSubtitle();
        int hashCode10 = (hashCode9 * 59) + (subtitle == null ? 43 : subtitle.hashCode());
        List<NodeExtended> coverNodes = getCoverNodes();
        int hashCode11 = (hashCode10 * 59) + (coverNodes == null ? 43 : coverNodes.hashCode());
        ContentAggregations contentAggregations = getContentAggregations();
        int i2 = hashCode11 * 59;
        if (contentAggregations != null) {
            i = contentAggregations.hashCode();
        }
        return i2 + i;
    }

    public void setAddedBy(String str) {
        this.addedBy = str;
    }

    public void setAddedByProfile(Profile profile) {
        this.addedByProfile = profile;
    }

    public void setCaption(String str) {
        this.caption = str;
    }

    public void setContentAggregations(ContentAggregations contentAggregations) {
        this.contentAggregations = contentAggregations;
    }

    public void setCoverNodes(List<NodeExtended> list) {
        this.coverNodes = list;
    }

    public void setDateAdded(String str) {
        this.dateAdded = str;
    }

    public void setEventId(String str) {
        this.eventId = str;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public void setParent(EventParent eventParent) {
        this.parent = eventParent;
    }

    public void setReactionSummary(ReactionSummary reactionSummary) {
        this.reactionSummary = reactionSummary;
    }

    public void setReactionTopic(String str) {
        this.reactionTopic = str;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupEventResponse(eventId=");
        outline107.append(getEventId());
        outline107.append(", groupId=");
        outline107.append(getGroupId());
        outline107.append(", addedBy=");
        outline107.append(getAddedBy());
        outline107.append(", addedByProfile=");
        outline107.append(getAddedByProfile());
        outline107.append(", dateAdded=");
        outline107.append(getDateAdded());
        outline107.append(", parent=");
        outline107.append(getParent());
        outline107.append(", reactionTopic=");
        outline107.append(getReactionTopic());
        outline107.append(", reactionSummary=");
        outline107.append(getReactionSummary());
        outline107.append(", caption=");
        outline107.append(getCaption());
        outline107.append(", subtitle=");
        outline107.append(getSubtitle());
        outline107.append(", coverNodes=");
        outline107.append(getCoverNodes());
        outline107.append(", contentAggregations=");
        outline107.append(getContentAggregations());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse instanceof GroupEventResponse) {
            return cloudDriveResponse.hashCode() - hashCode();
        }
        return -1;
    }
}
