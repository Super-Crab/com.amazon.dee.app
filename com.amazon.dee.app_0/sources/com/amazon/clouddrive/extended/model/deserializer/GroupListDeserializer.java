package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.Group;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
/* loaded from: classes11.dex */
public class GroupListDeserializer extends ListDeserializer<Group> {
    public static final ListDeserializer<Group> INSTANCE = new GroupListDeserializer();

    private GroupListDeserializer() {
        super(GroupDeserializer.INSTANCE);
    }
}
