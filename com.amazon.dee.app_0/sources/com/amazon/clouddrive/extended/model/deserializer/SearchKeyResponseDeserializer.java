package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.SearchKeyResponse;
import com.amazon.clouddrive.model.PaginatedCloudDriveResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.PaginatedCloudDriveResponseFieldDeserializer;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class SearchKeyResponseDeserializer extends AbstractDeserializer<PaginatedCloudDriveResponse, SearchKeyResponse> {
    public static final JsonDeserializer<SearchKeyResponse> INSTANCE = new SearchKeyResponseDeserializer();

    /* loaded from: classes11.dex */
    static class SearchKeyResponseFieldDeserializer extends PaginatedCloudDriveResponseFieldDeserializer {
        @Override // com.amazon.clouddrive.model.deserializer.PaginatedCloudDriveResponseFieldDeserializer, com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((PaginatedCloudDriveResponse) obj));
        }

        @Override // com.amazon.clouddrive.model.deserializer.PaginatedCloudDriveResponseFieldDeserializer
        public <U extends PaginatedCloudDriveResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (super.handleField(jsonParser, str, (String) u)) {
                return true;
            }
            if (!(u instanceof SearchKeyResponse)) {
                return false;
            }
            if ("data".equals(str)) {
                ((SearchKeyResponse) u).setData(ExtendedDeserializers.LIST_NODE_EXTENDED_DESERIALIZER.mo3229deserialize(jsonParser));
                return true;
            } else if (!"aggregations".equals(str)) {
                return false;
            } else {
                ((SearchKeyResponse) u).setAggregations(SearchKeyResponseAggregationsDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private SearchKeyResponseDeserializer() {
        super(new SearchKeyResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public SearchKeyResponse mo3156createValue() {
        return new SearchKeyResponse();
    }
}
