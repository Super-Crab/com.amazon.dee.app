package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.SearchGroupNodesExtendedResponse;
import com.amazon.clouddrive.extended.model.deserializer.SearchGroupNodesResponseDeserializer;
import com.amazon.clouddrive.model.PaginatedCloudDriveResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class SearchGroupNodesExtendedResponseDeserializer extends AbstractDeserializer<PaginatedCloudDriveResponse, SearchGroupNodesExtendedResponse> {
    public static final JsonDeserializer<SearchGroupNodesExtendedResponse> INSTANCE = new SearchGroupNodesExtendedResponseDeserializer();

    /* loaded from: classes11.dex */
    static class SearchGroupNodesExtendedResponseFieldDeserializer extends SearchGroupNodesResponseDeserializer.SearchGroupNodesResponseFieldDeserializer {
        SearchGroupNodesExtendedResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.extended.model.deserializer.SearchKeyResponseDeserializer.SearchKeyResponseFieldDeserializer, com.amazon.clouddrive.model.deserializer.PaginatedCloudDriveResponseFieldDeserializer, com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((PaginatedCloudDriveResponse) obj));
        }

        @Override // com.amazon.clouddrive.extended.model.deserializer.SearchKeyResponseDeserializer.SearchKeyResponseFieldDeserializer, com.amazon.clouddrive.model.deserializer.PaginatedCloudDriveResponseFieldDeserializer
        public <U extends PaginatedCloudDriveResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (super.handleField(jsonParser, str, (String) u)) {
                return true;
            }
            if (!(u instanceof SearchGroupNodesExtendedResponse) || !"groupsData".equals(str)) {
                return false;
            }
            ((SearchGroupNodesExtendedResponse) u).setGroupsData(GroupsDataDeserializer.INSTANCE.mo3229deserialize(jsonParser));
            return true;
        }
    }

    private SearchGroupNodesExtendedResponseDeserializer() {
        super(new SearchGroupNodesExtendedResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public SearchGroupNodesExtendedResponse mo3156createValue() {
        return new SearchGroupNodesExtendedResponse();
    }
}
