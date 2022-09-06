package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.GetAccountInfoRequest;
import com.amazon.clouddrive.model.GetAccountInfoResponse;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class GetAccountInfoRequestSerializer implements JsonSerializer<GetAccountInfoRequest> {
    public static final JsonSerializer<GetAccountInfoRequest> INSTANCE = new GetAccountInfoRequestSerializer();

    /* loaded from: classes11.dex */
    static class GetAccountInfoResponseFieldSerializer implements JsonFieldSerializer<GetAccountInfoResponse> {
        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((GetAccountInfoResponseFieldSerializer) ((GetAccountInfoResponse) obj), jsonGenerator);
        }

        public <U extends GetAccountInfoResponse> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("termsOfUse");
            SimpleSerializers.serializeString(u.getTermsOfUse(), jsonGenerator);
            jsonGenerator.writeFieldName("status");
            SimpleSerializers.serializeString(u.getStatus(), jsonGenerator);
        }
    }

    private GetAccountInfoRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(GetAccountInfoRequest getAccountInfoRequest, JsonGenerator jsonGenerator) throws IOException {
        if (getAccountInfoRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeEndObject();
    }
}
