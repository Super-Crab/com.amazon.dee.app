package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.cdasdk.cds.common.PhotoSearchCategory;
import com.amazon.clouddrive.extended.model.RenameFaceClusterResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class RenameFaceClusterResponseDeserializer extends AbstractDeserializer<RenameFaceClusterResponse, RenameFaceClusterResponse> {
    public static final JsonDeserializer<RenameFaceClusterResponse> INSTANCE = new RenameFaceClusterResponseDeserializer();

    /* loaded from: classes11.dex */
    static class RenameFaceClusterResponseFieldDeserializer implements JsonFieldDeserializer<RenameFaceClusterResponse> {
        RenameFaceClusterResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((RenameFaceClusterResponse) obj));
        }

        public <U extends RenameFaceClusterResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (PhotoSearchCategory.CLUSTER_ID.equals(str)) {
                u.setClusterId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
            return false;
        }
    }

    private RenameFaceClusterResponseDeserializer() {
        super(new RenameFaceClusterResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public RenameFaceClusterResponse mo3156createValue() {
        return new RenameFaceClusterResponse();
    }
}
