package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.extended.model.Reaction;
import java.util.List;
/* loaded from: classes11.dex */
public class CreateReactionResponse extends Reaction {

    /* loaded from: classes11.dex */
    public static class Builder extends Reaction.Builder {
        @Override // com.amazon.clouddrive.extended.model.Reaction.Builder
        /* renamed from: build */
        public CreateReactionResponse mo2999build() {
            return new CreateReactionResponse(this.groupId, this.reactionId, this.kind, this.body, this.topic, this.author, this.version, this.modifiedDate, this.createdDate, this.authorProfile, this.topicInfo, this.coverPhotos, this.topicOwner, this.topicContentAggregations);
        }
    }

    public CreateReactionResponse(String str, String str2, String str3, String str4, String str5, String str6, Long l, String str7, String str8, Profile profile, TopicInfo topicInfo, List<GroupCoverPhoto> list, Profile profile2, ContentAggregations contentAggregations) {
        super(str, str2, str3, str4, str5, str6, l, str7, str8, profile, topicInfo, list, profile2, contentAggregations);
    }
}
