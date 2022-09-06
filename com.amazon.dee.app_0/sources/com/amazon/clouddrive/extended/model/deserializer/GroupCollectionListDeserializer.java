package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GroupCollection;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
/* loaded from: classes11.dex */
public class GroupCollectionListDeserializer extends ListDeserializer<GroupCollection> {
    public static final ListDeserializer<GroupCollection> INSTANCE = new GroupCollectionListDeserializer();

    public GroupCollectionListDeserializer() {
        super(GroupCollectionDeserializer.INSTANCE);
    }
}
