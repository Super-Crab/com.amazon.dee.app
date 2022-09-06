package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.SearchGroupNodesResponse;
import com.amazon.clouddrive.extended.model.deserializer.SearchKeyResponseDeserializer;
import com.amazon.clouddrive.model.PaginatedCloudDriveResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
/* loaded from: classes11.dex */
public class SearchGroupNodesResponseDeserializer extends AbstractDeserializer<PaginatedCloudDriveResponse, SearchGroupNodesResponse> {
    public static final JsonDeserializer<SearchGroupNodesResponse> INSTANCE = new SearchGroupNodesResponseDeserializer();

    /* loaded from: classes11.dex */
    static class SearchGroupNodesResponseFieldDeserializer extends SearchKeyResponseDeserializer.SearchKeyResponseFieldDeserializer {
    }

    private SearchGroupNodesResponseDeserializer() {
        super(new SearchGroupNodesResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public SearchGroupNodesResponse mo3156createValue() {
        return new SearchGroupNodesResponse();
    }
}
