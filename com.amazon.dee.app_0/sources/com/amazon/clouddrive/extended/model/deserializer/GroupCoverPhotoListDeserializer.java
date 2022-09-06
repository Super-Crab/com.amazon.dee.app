package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GroupCoverPhoto;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
/* loaded from: classes11.dex */
public class GroupCoverPhotoListDeserializer extends ListDeserializer<GroupCoverPhoto> {
    public static final ListDeserializer<GroupCoverPhoto> INSTANCE = new GroupCoverPhotoListDeserializer();

    private GroupCoverPhotoListDeserializer() {
        super(GroupCoverPhotoDeserializer.INSTANCE);
    }
}
