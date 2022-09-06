package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.Invitee;
import com.amazon.clouddrive.model.serializer.ListSerializer;
/* loaded from: classes11.dex */
public class InviteeListSerializer extends ListSerializer<Invitee> {
    public static final ListSerializer<Invitee> INSTANCE = new InviteeListSerializer();

    private InviteeListSerializer() {
        super(InviteeSerializer.INSTANCE);
    }
}
