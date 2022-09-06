package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.ListNodesExtendedResponse;
import com.amazon.clouddrive.model.PaginatedCloudDriveResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.PaginatedCloudDriveResponseFieldDeserializer;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class ListNodesExtendedResponseDeserializer extends AbstractDeserializer<PaginatedCloudDriveResponse, ListNodesExtendedResponse> {
    public static final JsonDeserializer<ListNodesExtendedResponse> INSTANCE = new ListNodesExtendedResponseDeserializer();

    /* loaded from: classes11.dex */
    static class ListNodesExtendedResponseFieldDeserializer extends PaginatedCloudDriveResponseFieldDeserializer {
        ListNodesExtendedResponseFieldDeserializer() {
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
            if (!(u instanceof ListNodesExtendedResponse) || !"data".equals(str)) {
                return false;
            }
            ((ListNodesExtendedResponse) u).setData(ExtendedDeserializers.LIST_NODE_EXTENDED_DESERIALIZER.mo3229deserialize(jsonParser));
            return true;
        }
    }

    private ListNodesExtendedResponseDeserializer() {
        super(new ListNodesExtendedResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public ListNodesExtendedResponse mo3156createValue() {
        return new ListNodesExtendedResponse();
    }
}
