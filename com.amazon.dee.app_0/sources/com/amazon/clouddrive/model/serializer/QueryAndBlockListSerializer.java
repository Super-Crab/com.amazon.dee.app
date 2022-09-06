package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.QueryAndBlock;
import java.util.List;
/* loaded from: classes11.dex */
public class QueryAndBlockListSerializer extends ListSerializer<QueryAndBlock> {
    public static final JsonSerializer<List<QueryAndBlock>> INSTANCE = new QueryAndBlockListSerializer();

    private QueryAndBlockListSerializer() {
        super(QueryAndBlockSerializer.INSTANCE);
    }
}
