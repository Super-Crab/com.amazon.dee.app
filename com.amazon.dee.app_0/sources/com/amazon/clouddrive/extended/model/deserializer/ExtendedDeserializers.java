package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.NodeExtended;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import java.util.List;
/* loaded from: classes11.dex */
public class ExtendedDeserializers {
    public static final JsonDeserializer<List<NodeExtended>> LIST_NODE_EXTENDED_DESERIALIZER = new ListDeserializer(NodeExtendedDeserializer.INSTANCE);

    private ExtendedDeserializers() {
        throw new UnsupportedOperationException("Do not instantiate!");
    }
}
