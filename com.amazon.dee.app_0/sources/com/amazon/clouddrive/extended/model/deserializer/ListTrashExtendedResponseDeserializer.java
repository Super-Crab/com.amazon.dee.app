package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.ListNodesInTrashExtendedResponse;
import com.amazon.clouddrive.model.PaginatedCloudDriveResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.PaginatedCloudDriveResponseFieldDeserializer;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class ListTrashExtendedResponseDeserializer extends AbstractDeserializer<PaginatedCloudDriveResponse, ListNodesInTrashExtendedResponse> {
    public static final JsonDeserializer<ListNodesInTrashExtendedResponse> INSTANCE = new ListTrashExtendedResponseDeserializer();

    /* loaded from: classes11.dex */
    static class ListNodesInTrashExtendedResponseFieldDeserializer extends PaginatedCloudDriveResponseFieldDeserializer {
        ListNodesInTrashExtendedResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.PaginatedCloudDriveResponseFieldDeserializer, com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((PaginatedCloudDriveResponse) obj));
        }

        @Override // com.amazon.clouddrive.model.deserializer.PaginatedCloudDriveResponseFieldDeserializer
        public <U extends PaginatedCloudDriveResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (super.handleField(jsonParser, str, (String) u)) {
                return true;
            }
            if (!(u instanceof ListNodesInTrashExtendedResponse) || !"data".equals(str)) {
                return false;
            }
            ((ListNodesInTrashExtendedResponse) u).setData(ExtendedDeserializers.LIST_NODE_EXTENDED_DESERIALIZER.mo3229deserialize(jsonParser));
            return true;
        }
    }

    private ListTrashExtendedResponseDeserializer() {
        super(new ListNodesInTrashExtendedResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public ListNodesInTrashExtendedResponse mo3156createValue() {
        return new ListNodesInTrashExtendedResponse();
    }
}
