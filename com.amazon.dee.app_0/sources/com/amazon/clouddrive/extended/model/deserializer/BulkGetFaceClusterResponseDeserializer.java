package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.BulkGetFaceClusterResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class BulkGetFaceClusterResponseDeserializer extends AbstractDeserializer<BulkGetFaceClusterResponse, BulkGetFaceClusterResponse> {
    public static final JsonDeserializer<BulkGetFaceClusterResponse> INSTANCE = new BulkGetFaceClusterResponseDeserializer();

    /* loaded from: classes11.dex */
    static class BulkFaceClusterResponseFieldDeserializer implements JsonFieldDeserializer<BulkGetFaceClusterResponse> {
        BulkFaceClusterResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((BulkGetFaceClusterResponse) obj));
        }

        public <U extends BulkGetFaceClusterResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("facePairs".equals(str)) {
                u.setFaceCluster(new ListDeserializer(FacePairDeserializer.INSTANCE).mo3229deserialize(jsonParser));
                return true;
            } else if (!"errorMap".equals(str)) {
                return false;
            } else {
                u.setErrorMap(ErrorMapDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private BulkGetFaceClusterResponseDeserializer() {
        super(new BulkFaceClusterResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public BulkGetFaceClusterResponse mo3156createValue() {
        return new BulkGetFaceClusterResponse();
    }
}
