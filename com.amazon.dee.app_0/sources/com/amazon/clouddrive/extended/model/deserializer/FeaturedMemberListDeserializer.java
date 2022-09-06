package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.FeaturedMember;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
/* loaded from: classes11.dex */
public class FeaturedMemberListDeserializer extends ListDeserializer<FeaturedMember> {
    public static final ListDeserializer<FeaturedMember> INSTANCE = new FeaturedMemberListDeserializer();

    private FeaturedMemberListDeserializer() {
        super(FeaturedMemberDeserializer.INSTANCE);
    }
}
