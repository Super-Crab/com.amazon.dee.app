package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GroupPermission;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
/* loaded from: classes11.dex */
public class GroupPermissionListDeserializer extends ListDeserializer<GroupPermission> {
    public static final ListDeserializer<GroupPermission> INSTANCE = new GroupPermissionListDeserializer();

    private GroupPermissionListDeserializer() {
        super(GroupPermissionDeserializer.INSTANCE);
    }
}
